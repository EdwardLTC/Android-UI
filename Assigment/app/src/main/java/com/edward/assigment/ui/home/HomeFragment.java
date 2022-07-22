package com.edward.assigment.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.edward.assigment.adapter.TaskAdapter;
import com.edward.assigment.model.TaskDAO;
import com.edward.assigment.model.TaskToDo;
import com.edward.assigment.R;
import com.edward.assigment.databinding.FragmentHomeBinding;
import com.edward.assigment.ui.gallery.GalleryFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private TaskAdapter _TaskAdapter;
    private Drawable icon;
    private ColorDrawable background;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TaskDAO _TD = new TaskDAO(getActivity());
        _TaskAdapter = new TaskAdapter(getActivity());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        binding.rclTask.setLayoutManager(linearLayoutManager);

        binding.rclTask.setAdapter(_TaskAdapter);

        icon = ContextCompat.getDrawable(binding.getRoot().getContext(), R.drawable.ic_baseline_delete_sweep_24);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            background = new ColorDrawable(binding.getRoot().getContext().getColor(R.color.purple_200));
        }

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @SuppressLint("ShowToast")
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if (viewHolder instanceof TaskAdapter.TaskViewHolder) {
                    int index = viewHolder.getAdapterPosition();
                    TaskToDo taskTemp = _TaskAdapter.getTaskIndex(index);

                    if (_TaskAdapter.removeElement(index)){
                        Snackbar.make(requireActivity().findViewById(android.R.id.content), "Undo ?? ", Snackbar.LENGTH_LONG).setAction("undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                _TaskAdapter.undoItem(taskTemp);
                            }
                        }).show();
                    }
                    else {
                        Snackbar.make(requireActivity().findViewById(android.R.id.content), "Remove False ", Snackbar.LENGTH_LONG);
                    }

                }
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

                dX = dX / 10;
                View itemView = viewHolder.itemView;
                int backgroundCornerOffset = 20;

                int iconMargin = (itemView.getHeight() - icon.getIntrinsicHeight()) / 2;
                int iconTop = itemView.getTop() + (itemView.getHeight() - icon.getIntrinsicHeight()) / 2;
                int iconBottom = iconTop + icon.getIntrinsicHeight();

                if (dX < 0) {
                    int iconLeft = itemView.getRight() - iconMargin - icon.getIntrinsicWidth();
                    int iconRight = itemView.getRight() - iconMargin;
                    icon.setBounds(iconLeft, iconTop, iconRight, iconBottom);

                    background.setBounds(itemView.getRight() + ((int) dX) - backgroundCornerOffset,
                            itemView.getTop(), itemView.getRight(), itemView.getBottom());
                    background.draw(c);
                    icon.draw(c);
                } else {
                    background.setBounds(0, 0, 0, 0);
                }

            }

            @Override
            public float getSwipeThreshold(@NonNull RecyclerView.ViewHolder viewHolder) {
                return 0.1f;
            }

        }).attachToRecyclerView(binding.rclTask);

        binding.fab.setOnClickListener(view -> {
            NavigationView navigationView = requireActivity().findViewById(R.id.nav_view);
            navigationView.getMenu().getItem(1).setChecked(true);
            FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.nav_host_fragment_content_main, new GalleryFragment());
            fragmentTransaction.commit();
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        Log.d("true", "onDestroyViewHome: ");
        super.onDestroyView();
        binding = null;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        TaskAdapter taskAdapter = new TaskAdapter(context);
        taskAdapter.notifyDataSetChanged();
    }

}
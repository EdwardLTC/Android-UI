package com.edward.assigment.ui.home;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.edward.assigment.Adapter.TaskAdapter;
import com.edward.assigment.Model.TaskToDo;
import com.edward.assigment.R;
import com.edward.assigment.databinding.FragmentHomeBinding;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentHomeBinding binding;
    private TaskAdapter _TaskAdapter;

    private Drawable icon;
    private ColorDrawable background;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        _TaskAdapter= new TaskAdapter(getActivity());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false);
        binding.rclTask.setLayoutManager(linearLayoutManager);

        _TaskAdapter.setData(TempData());
        binding.rclTask.setAdapter(_TaskAdapter);

        icon = ContextCompat.getDrawable(binding.getRoot().getContext(), R.drawable.klee);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            background = new ColorDrawable(binding.getRoot().getContext().getColor(R.color.purple_200));
        }
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if (viewHolder instanceof TaskAdapter.TaskViewHolder){
                    int index = viewHolder.getAdapterPosition();
                    _TaskAdapter.removeElement(index);
                }
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

                dX = dX/10;
                View itemView = viewHolder.itemView;
                int backgroundCornerOffset = 20;

                int iconMargin = (itemView.getHeight() - icon.getIntrinsicHeight()) / 2;
                int iconTop = itemView.getTop() + (itemView.getHeight() - icon.getIntrinsicHeight()) / 2;
                int iconBottom = iconTop + icon.getIntrinsicHeight();

                if(dX < 0) {
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


        return root;
    }
    private ArrayList<TaskToDo> TempData(){
        ArrayList<TaskToDo> m_ListTask = new ArrayList<>();
        m_ListTask.add(new TaskToDo("Task"));
        m_ListTask.add(new TaskToDo("Task"));
        m_ListTask.add(new TaskToDo("Task"));
        m_ListTask.add(new TaskToDo("Task"));
        m_ListTask.add(new TaskToDo("Task"));
        m_ListTask.add(new TaskToDo("Task"));
        return m_ListTask;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onResume() { //solve with database
        super.onResume();
        binding.rclTask.setAdapter(_TaskAdapter);
        Log.d("ehe", "onResume: ");
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
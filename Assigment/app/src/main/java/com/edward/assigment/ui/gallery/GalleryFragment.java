package com.edward.assigment.ui.gallery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.edward.assigment.adapter.TaskAdapter;
import com.edward.assigment.model.TaskToDo;
import com.edward.assigment.R;
import com.edward.assigment.databinding.FragmentGalleryBinding;
import com.edward.assigment.ui.home.HomeFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private TaskAdapter _TaskAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);

        View root = binding.getRoot();
        _TaskAdapter = new TaskAdapter(getActivity());

        binding.AddTask.setOnClickListener(view -> {
            String task = binding.newTask.getText().toString();
            if (!task.isEmpty()){
                if (_TaskAdapter.AddTask(new TaskToDo(task))){
                    binding.newTask.setText("");
                    NavigationView navigationView =   requireActivity().findViewById(R.id.nav_view);
                    navigationView.getMenu().getItem(0).setChecked(true);
                    FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.nav_host_fragment_content_main, new HomeFragment());
                    fragmentTransaction.commit();
                }
                else {
                    Snackbar.make(requireActivity().findViewById(android.R.id.content), "Add Task False", Snackbar.LENGTH_LONG).show();
                }

            }
            else {
                Snackbar.make(requireActivity().findViewById(android.R.id.content), "Your Task is empty", Snackbar.LENGTH_LONG).show();
            }

        });
        return root;
    }

    @Override
    public void onResume() {
        Log.d("test", "onResumeGal: ");
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        Log.d("true", "onDestroyViewGallery: ");
        super.onDestroyView();
        binding = null;
    }
}
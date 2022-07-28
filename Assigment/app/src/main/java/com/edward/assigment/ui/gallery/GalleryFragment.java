package com.edward.assigment.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.edward.assigment.adapter.DialogCalendar;
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

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentGalleryBinding.inflate(inflater, container, false);

        View root = binding.getRoot();
        _TaskAdapter = new TaskAdapter(getActivity());

        binding.AddTask.setOnClickListener(view -> {
            String task = binding.newTask.getText().toString();
            if (!task.isEmpty()){
                if (_TaskAdapter.AddTask(new TaskToDo(task))){
                    binding.newTask.setText("");
                    goToHomeFragment();
                }
                else {
                    Snackbar.make(requireActivity().findViewById(android.R.id.content), "Add Task False", Snackbar.LENGTH_LONG).show();
                }

            }
            else {
                Snackbar.make(requireActivity().findViewById(android.R.id.content), "Your Task is empty", Snackbar.LENGTH_LONG).show();
            }

        });

        binding.calendar.setOnClickListener(view -> {
            DialogFragment dialogFragment = new DialogCalendar();
            dialogFragment.show(requireActivity().getSupportFragmentManager(), "Date");
        });

        binding.icBack.setOnClickListener(view -> goToHomeFragment());

        return root;
    }

    public void goToHomeFragment(){
        NavigationView navigationView =  requireActivity().findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(0).setChecked(true);
        Fragment fragment = new HomeFragment();
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter,R.anim.exit,R.anim.pop_enter,R.anim.pop_exit);
        fragmentTransaction.replace(R.id.nav_host_fragment_content_main, fragment);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
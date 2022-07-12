package com.edward.assigment.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.edward.assigment.Adapter.TaskAdapter;
import com.edward.assigment.MainActivity;
import com.edward.assigment.Model.TaskDAO;
import com.edward.assigment.Model.TaskToDo;
import com.edward.assigment.R;
import com.edward.assigment.databinding.FragmentGalleryBinding;
import com.edward.assigment.ui.home.HomeFragment;

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

        binding.AddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String task = binding.newTask.getText().toString();
                if (!task.isEmpty()){
                    _TaskAdapter.AddTask(new TaskToDo(task));
                    FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.nav_host_fragment_content_main, new HomeFragment());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }

            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
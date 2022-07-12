package com.edward.assigment.Adapter;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.edward.assigment.Model.TaskDAO;
import com.edward.assigment.Model.TaskToDo;
import com.edward.assigment.R;

import java.util.ArrayList;
import java.util.Objects;


public class DialogCustom extends DialogFragment {

    private EditText _EditTex;
    private TaskDAO _TD;

    public static DialogCustom newInstance() {
        return new DialogCustom();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_component, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _TD = new TaskDAO(getActivity());
        _EditTex = view.findViewById(R.id.newTask);

        Button _button = view.findViewById(R.id.fab);
        _button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!_EditTex.getText().toString().isEmpty()) {//solve with database
                     String txt = _EditTex.getText().toString();
                     _TD.insertTask(new TaskToDo(txt));

                }
                dismiss();
            }
        });

//        Objects.requireNonNull(getDialog()).getWindow().setSoftInputMode(
//                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }
}


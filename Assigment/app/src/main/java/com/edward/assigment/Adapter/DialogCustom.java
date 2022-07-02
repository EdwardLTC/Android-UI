package com.edward.assigment.Adapter;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;


import androidx.annotation.Nullable;

import androidx.fragment.app.DialogFragment;

import com.edward.assigment.Model.TaskToDo;
import com.edward.assigment.R;


public class DialogCustom extends DialogFragment {

    private EditText _EditTex;
    private Button _button;
    private TaskAdapter _TaskAdapter;

    public static DialogCustom newInstance() {
        DialogCustom frag = new DialogCustom();
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_component, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        _EditTex = view.findViewById(R.id.newTask);
        _button = view.findViewById(R.id.fab);
        _TaskAdapter = new TaskAdapter(getActivity());

        _button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!_EditTex.getText().toString().isEmpty()) {//solve with database
                    String txt = _EditTex.getText().toString();
                    try {
                        _TaskAdapter.addTask(new TaskToDo(txt));
                        Log.d("tnt", "onClick: ");
                    }catch (Exception e){
                        Log.d(e.toString(), "onClick: ");
                    }

                }
                dismiss();
            }
        });

        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }
}


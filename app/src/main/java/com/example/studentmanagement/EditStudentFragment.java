package com.example.studentmanagement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


import com.example.studentmanagement.R;

public class EditStudentFragment extends Fragment {

    private EditText nameEditText, idEditText;
    private Student student;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_student, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        nameEditText = view.findViewById(R.id.nameEditText);
        idEditText = view.findViewById(R.id.idEditText);

        Bundle bundle = getArguments();
        if (bundle != null) {
            student = (Student) bundle.getSerializable("student");
            nameEditText.setText(student.getName());
            idEditText.setText(student.getStudentId());
        }

        Button saveButton = view.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(v -> {
            student.setName(nameEditText.getText().toString());
            student.setStudentId(idEditText.getText().toString());
            // Cập nhật sinh viên trong danh sách
            ((MainActivity) getActivity()).updateStudent(student);
            Navigation.findNavController(view).popBackStack();
        });
    }
}

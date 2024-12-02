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

public class AddStudentFragment extends Fragment {

    private EditText nameEditText, idEditText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_student, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        nameEditText = view.findViewById(R.id.nameEditText);
        idEditText = view.findViewById(R.id.idEditText);

        Button saveButton = view.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString();
            String id = idEditText.getText().toString();
            // Thêm sinh viên vào danh sách
            Student student = new Student(name, id);
            ((MainActivity) getActivity()).addStudent(student);
            Navigation.findNavController(view).popBackStack();
        });
    }
}

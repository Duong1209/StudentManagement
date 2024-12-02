package com.example.studentmanagement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class MainFragment extends Fragment {

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        // Xử lý sự kiện nhấn nút "Add New Student"
        Button btnAddStudent = view.findViewById(R.id.btnAddStudent);
        btnAddStudent.setOnClickListener(v -> {
            // Điều hướng đến AddStudentFragment (sử dụng NavController)
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_mainFragment_to_addStudentFragment);
        });

        return view;
    }
}

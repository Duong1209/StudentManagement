package com.example.studentmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddEditStudentActivity extends AppCompatActivity {
    private EditText edtName, edtMSSV;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_student);

        edtName = findViewById(R.id.edtName);
        edtMSSV = findViewById(R.id.edtMSSV);
        btnSave = findViewById(R.id.btnSave);

        Intent intent = getIntent();
        String studentData = intent.getStringExtra("student_data");
        int position = intent.getIntExtra("position", -1);

        if (studentData != null) {
            // Nếu là chỉnh sửa, hiển thị thông tin cũ
            String[] parts = studentData.split(" - ");
            edtName.setText(parts[0]);
            edtMSSV.setText(parts[1]);
        }

        btnSave.setOnClickListener(v -> {
            String name = edtName.getText().toString();
            String mssv = edtMSSV.getText().toString();
            if (!name.isEmpty() && !mssv.isEmpty()) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("student_data", name + " - " + mssv);
                resultIntent.putExtra("position", position);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
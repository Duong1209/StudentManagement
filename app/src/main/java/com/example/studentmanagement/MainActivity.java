package com.example.studentmanagement;

import android.view.ContextMenu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.studentmanagement.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView studentListView;
    private ArrayAdapter<Student> adapter;
    private List<Student> studentList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentListView = findViewById(R.id.studentListView);
        studentList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_2, android.R.id.text1, studentList);

        studentListView.setAdapter(adapter);

        // Thiết lập ContextMenu
        registerForContextMenu(studentListView);

        // Đảm bảo OptionMenu được hiển thị
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_new) {
            // Di chuyển đến Fragment để thêm sinh viên mới
            Navigation.findNavController(this, R.id.nav_host_fragment)
                    .navigate(R.id.action_mainFragment_to_addStudentFragment);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Student selectedStudent = studentList.get(info.position);

        if (item.getItemId() == R.id.edit) {
            // Di chuyển đến Fragment để chỉnh sửa sinh viên
            Bundle bundle = new Bundle();
            bundle.putSerializable("student", selectedStudent);
            Navigation.findNavController(this, R.id.nav_host_fragment)
                    .navigate(R.id.action_mainFragment_to_editStudentFragment, bundle);
            return true;
        } else if (item.getItemId() == R.id.remove) {
            studentList.remove(selectedStudent);
            adapter.notifyDataSetChanged();
            return true;
        }
        return super.onContextItemSelected(item);
    }

    public void addStudent(Student student) {
        studentList.add(student);
        adapter.notifyDataSetChanged();
    }

    public void updateStudent(Student student) {
        // Tìm kiếm và cập nhật sinh viên
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getStudentId().equals(student.getStudentId())) {
                studentList.set(i, student);
                adapter.notifyDataSetChanged();
                break;
            }
        }
    }

}

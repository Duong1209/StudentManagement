package com.example.studentmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> students;
    private ArrayAdapter<String> adapter;
    private ListView listView;
    private int selectedStudentPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo danh sách sinh viên
        students = new ArrayList<>();
        students.add("Nguyen Van A - 123456");
        students.add("Le Thi B - 654321");

        // Kết nối ListView và Adapter
        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, students);
        listView.setAdapter(adapter);

        // Đăng ký ContextMenu cho ListView
        registerForContextMenu(listView);

        // Bắt sự kiện chọn item
        listView.setOnItemClickListener((parent, view, position, id) -> {
            selectedStudentPosition = position;
            openContextMenu(view);
        });
    }

    // Tạo OptionMenu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    // Xử lý OptionMenu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_new) {
            // Chuyển đến màn hình thêm sinh viên
            Intent intent = new Intent(this, AddEditStudentActivity.class);
            startActivityForResult(intent, 1);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Tạo ContextMenu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    // Xử lý ContextMenu
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (item.getItemId() == R.id.edit) {
            // Chỉnh sửa sinh viên
            Intent intent = new Intent(this, AddEditStudentActivity.class);
            intent.putExtra("student_data", students.get(info.position));
            intent.putExtra("position", info.position);
            startActivityForResult(intent, 2);
            return true;
        } else if (item.getItemId() == R.id.remove) {
            // Xóa sinh viên
            students.remove(info.position);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Student removed", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onContextItemSelected(item);
    }

    // Nhận kết quả từ AddEditStudentActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            String studentData = data.getStringExtra("student_data");
            int position = data.getIntExtra("position", -1);
            if (requestCode == 1) { // Thêm mới
                students.add(studentData);
            } else if (requestCode == 2 && position >= 0) { // Chỉnh sửa
                students.set(position, studentData);
            }
            adapter.notifyDataSetChanged();
        }
    }
}
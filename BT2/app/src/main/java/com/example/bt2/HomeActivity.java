package com.example.bt2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private ListView listview;
    private Button btnexit;
    private List<Course> UserCourses = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        String id = getIntent().getExtras().getString("ID");

        listview = (ListView) findViewById(R.id.coursesLv);
        TextView idtv = (TextView) findViewById(R.id.userid);
        btnexit = (Button) findViewById(R.id.exitbtn);
        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        idtv.setText(id);

        UserCourses.add(new Course("SE114.P22", "Lập trình mobile", "Th6 tiết 1234", "Nguyễn Tấn Toàn", "B3.22"));
        UserCourses.add(new Course("SE101.P22", "Phương pháp mô hình hóa", "Th3 tiết 123", "Nguyễn Công Hoan", "B4.12"));
        UserCourses.add(new Course("SS006.P24", "Pháp luật đại cương", "Th3 tiết 45", "Nguyễn Viết Sơn ", "B4.14"));


        UserAdapter userAdapter = new UserAdapter(this, UserCourses);
        listview.setAdapter(userAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Course selectedCourse = UserCourses.get(position);
                Intent CourseIntent = new Intent(HomeActivity.this, CourseActivity.class);
                CourseIntent.putExtra("Course", selectedCourse);
                startActivity(CourseIntent);
            }
        });

    }
}
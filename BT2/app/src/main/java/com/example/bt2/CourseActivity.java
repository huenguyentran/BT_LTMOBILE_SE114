package com.example.bt2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CourseActivity extends AppCompatActivity {

    TextView CourseTv;
    Button backbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_course);


        CourseTv = (TextView) findViewById(R.id.courseTV);
        Course selectedItem;
        selectedItem = (Course) getIntent().getSerializableExtra("Course");
        CourseTv.setText(selectedItem.courseId + "\n" + selectedItem.courseName
                + "\n" + selectedItem.teacher + "\n" + selectedItem.room + "\n" + selectedItem.schedule);


        backbtn = (Button) findViewById(R.id.backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
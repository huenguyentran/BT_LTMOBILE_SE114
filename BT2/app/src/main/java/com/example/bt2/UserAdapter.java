package com.example.bt2;

import android.app.Activity;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class UserAdapter extends BaseAdapter {

    private List<Course> UserCourses = new ArrayList<>();
    private Activity activity;
    public UserAdapter(Activity activity, List<Course> list) {
        this.activity = activity;
        UserCourses = list;
    }

    @Override
    public int getCount() {
        return UserCourses.size(); // Đảm bảo trả về số lượng phần tử
    }

    @Override
    public Object getItem(int position) {
        return UserCourses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();

        convertView = layoutInflater.inflate(R.layout.activity_item_course, null);

        TextView courseId, courseName, courseShe;
        courseId = (TextView) convertView.findViewById(R.id.CourseID);
        courseName = (TextView) convertView.findViewById(R.id.CourseName);
        courseShe = (TextView) convertView.findViewById(R.id.CourseShe);

        courseId.setText(UserCourses.get(position).courseId);
        courseName.setText(UserCourses.get(position).courseName);
        courseShe.setText(UserCourses.get(position).schedule);

        return convertView;
    }
}

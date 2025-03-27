package com.example.myapplication;

import static android.content.Intent.getIntent;

import android.app.Activity;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class TodoAdapter extends BaseAdapter {

    private List<TodoItem> TodoList = new ArrayList<>();
    private Activity activity;
    private Boolean isDeletedMode = false;
    public TodoAdapter(Activity activity, List<TodoItem> list) {
        this.activity = activity;
        this.TodoList.clear();
        this.TodoList.addAll(list);
    }

    public void updateData(List<TodoItem> newList) {
        this.TodoList.clear();
        this.TodoList.addAll(newList);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return TodoList.size();
    }

    @Override
    public Object getItem(int position) {
        return TodoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private CheckBox SelectToDeletedCckBox;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();

        convertView = layoutInflater.inflate(R.layout.activity_todo_item, null);

        TextView ItemId, ItemName, ItemShe;
        CheckBox ItemStatus;
        ItemId = (TextView) convertView.findViewById(R.id.ID);
        ItemName = (TextView) convertView.findViewById(R.id.TaskName);
        ItemShe = (TextView) convertView.findViewById(R.id.TaskDate);
        ItemStatus = (CheckBox) convertView.findViewById(R.id.checkBoxStatus);
        SelectToDeletedCckBox = (CheckBox) convertView.findViewById(R.id.DeleteModeCckBox);


        ItemId.setText(String.valueOf(TodoList.get(position).getid()));
        ItemName.setText(TodoList.get(position).getname());
        ItemShe.setText(TodoList.get(position).getDate());
        ItemStatus.setChecked(TodoList.get(position).getstatus());

        ItemStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = ((CheckBox) v).isChecked();
                TodoList.get(position).setstatus(isChecked);
            }
        });

        SelectToDeletedCckBox.setVisibility(isDeletedMode ? View.VISIBLE : View.GONE);
        SelectToDeletedCckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TodoList.get(position).setDeletedMode();
            }
        });
        return convertView;
    }

    public void ChangeDeleteMode() {
        isDeletedMode = !isDeletedMode;
        notifyDataSetChanged(); // Cập nhật lại danh sách
    }
}

package com.example.myapplication;


import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.Console;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView TodoListLV;
    private TodoItem SelectedItem;
    private TodoAdapter todoAdapter;
    private List<TodoItem> TodoList = new ArrayList<>();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private Button DeleteBtn, DeleteModeBtn;
    private boolean isDeletedMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        EdgeToEdge.enable(this);
        super.onCreate(savedInstanceState);

        DeleteBtn = (Button) findViewById(R.id.DeleteBtn);
        DeleteModeBtn = (Button) findViewById(R.id.DeleteModeBtn);
        DeleteBtn.setVisibility(isDeletedMode ? View.VISIBLE : View.GONE);
        DeleteModeBtn.setVisibility(isDeletedMode ? View.VISIBLE : View.GONE);

        TodoListLV = (ListView) findViewById(R.id.TodoListLv);

        TodoList.add(new TodoItem(1, "Soạn nội dung", true, "12-3-2025"));
        TodoList.add(new TodoItem(2, "Thi IELTS", false, "12-4-2025"));
        TodoList.add(new TodoItem(3, "Làm bài tập", false, "29-3-2025"));

        todoAdapter = new TodoAdapter(this, TodoList);
        TodoListLV.setAdapter(todoAdapter);
//        TodoListLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                SelectedItem =  TodoList.get(position);
//            }
//        });

        DeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<TodoItem> itemsToDelete = new ArrayList<>();
                for (TodoItem item : TodoList) {
                    if (item.getIsSelected()) {
                        itemsToDelete.add(item);
                    }
                }
                TodoList.removeAll(itemsToDelete);
                int i = 0;
                for(TodoItem item : TodoList) {
                    i++;
                    item.setId(i);
                }
                todoAdapter.updateData(TodoList);
            }
        });

        DeleteModeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isDeletedMode = false;
                todoAdapter.ChangeDeleteMode();
                DeleteBtn.setVisibility(isDeletedMode ? View.VISIBLE : View.GONE);
                DeleteModeBtn.setVisibility(isDeletedMode ? View.VISIBLE : View.GONE);

                for (TodoItem i: TodoList) {
                    i.RemoveDeletedMode();
                }
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.AddItemOption)
        {
            Intent intent = new Intent(MainActivity.this, AddTodolistActivity.class);
            int num = TodoList.size() + 1;
            intent.putExtra("IdOfNext", num);
            launcher.launch(intent);
        }
        else if(id == R.id.DeleteItemOption)
        {
            isDeletedMode = !isDeletedMode;
            todoAdapter.ChangeDeleteMode();
            DeleteBtn.setVisibility(isDeletedMode ? View.VISIBLE : View.GONE);
            DeleteModeBtn.setVisibility(isDeletedMode ? View.VISIBLE : View.GONE);

            for (TodoItem i: TodoList) {
                i.RemoveDeletedMode();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private final ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == MainActivity.RESULT_OK) {
                        Intent intent = result.getData();
                        TodoItem newItem;
                        newItem = (TodoItem) intent.getSerializableExtra("newItem");
                        TodoList.add(newItem);
                        todoAdapter.updateData(TodoList);
                    }
                }
            }
    );
}
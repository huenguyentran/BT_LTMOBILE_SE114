package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class AddTodolistActivity extends AppCompatActivity {
    private Button addbtn, pickDateBtn, backBtn;
    private TodoItem newitem;
    private int IdOfNext;
    private Calendar calendar;
    private TextView selectedDateTxtview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_todolist);
        addbtn = (Button) findViewById(R.id.addBtn);
        IdOfNext = (int) getIntent().getSerializableExtra("IdOfNext");

        calendar = Calendar.getInstance();

        TextView tvID = (TextView) findViewById(R.id.idTxtView);
        tvID.setText("Task id: " + String.valueOf(IdOfNext));
        pickDateBtn = (Button) findViewById(R.id.pickDateButton);
        EditText nametxt = (EditText) findViewById(R.id.newItemName);
        selectedDateTxtview = (TextView) findViewById(R.id.selectedDateTxt);
        backBtn = (Button) findViewById(R.id.btnBack);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nametxt.getText().length() == 0 || selectedDateTxtview.getText().length() == 0) {
                    return;
                }

                newitem = new TodoItem(IdOfNext, nametxt.getText().toString(), false, selectedDateTxtview.getText().toString());
                Intent intent = new Intent(AddTodolistActivity.this, MainActivity.class);
                intent.putExtra("newItem", newitem);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        pickDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTodolistActivity.this, MainActivity.class);
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });
    }
    private void showDatePickerDialog() {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year1, month1, day1) -> {
                    String selectedDate = day1 + "/" + (month1 + 1) + "/" + year1;
                    selectedDateTxtview.setText(selectedDate);
                },
                year, month, day
        );
        datePickerDialog.show();
    }
}
package com.example.myapplication;

import java.io.Serializable;
import java.util.Date;

public class TodoItem implements Serializable {
    private int id;
    private String name;
    private Boolean status;
    private String date;

    private boolean isSelected = false;
    public boolean getIsSelected(){
        return isSelected;
    }
    public int getid (){ return id; }
    public String getname(){ return name;}
    public Boolean getstatus() {return status;}
    public String getDate() {return date; }
    public void setId(int i) {
        this.id = i;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }
    public void setName(String name) {
        this.name = name;
    }

    public TodoItem(int ID, String taskName, Boolean TaskStatus, String datefinish){
        this.id = ID;
        this.name = taskName;
        this.status = TaskStatus;
        this.date = datefinish;
    }

    public void setstatus(boolean Status) {
        this.status = Status;
    }

    public void setDeletedMode(){
        isSelected = !isSelected;
    }
    public void RemoveDeletedMode(){
        isSelected = false;
    }
}

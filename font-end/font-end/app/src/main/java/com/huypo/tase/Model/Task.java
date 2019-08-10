package com.huypo.tase.Model;

import java.util.ArrayList;
import java.util.Date;

public class Task {
    private String _id;
    private String title;
    private Date deadline;
    private Boolean delete;
    private Boolean done;

    public ArrayList<Item> getItem() {
        return item;
    }

    public void setItem(ArrayList<Item> item) {
        this.item = item;
    }

    private ArrayList<Item> item;


    public Task(String _id, String title, Date deadline, Boolean delete, Boolean done, ArrayList<Item> item) {
        this._id = _id;
        this.title = title;
        this.deadline = deadline;
        this.delete = delete;
        this.done = done;
        this.item = item;
    }

    @Override
    public String toString() {
        return "Task{" +
                "_id='" + _id + '\'' +
                ", title='" + title + '\'' +
                ", deadline=" + deadline +
                ", delete=" + delete +
                ", done=" + done +
                ", item=" + item +
                '}';
    }

    public Task() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Boolean getDelete() {
        return delete;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}

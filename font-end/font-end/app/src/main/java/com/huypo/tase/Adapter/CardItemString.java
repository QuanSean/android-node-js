package com.huypo.tase.Adapter;

import java.util.ArrayList;

public class CardItemString {
    private String mTextResource;
    private String mTitleResource;
    private Integer idTask;
    private ArrayList<String> listItemTask;


    public CardItemString(String title, String text) {
        mTitleResource = title;
        mTextResource = text;
    }


    public Integer getIdTask() {
        return idTask;
    }

    public void setIdTask(Integer idTask) {
        this.idTask = idTask;
    }

    public String getmTextResource() {
        return mTextResource;
    }

    public void setmTextResource(String mTextResource) {
        this.mTextResource = mTextResource;
    }

    public String getmTitleResource() {
        return mTitleResource;
    }

    public void setmTitleResource(String mTitleResource) {
        this.mTitleResource = mTitleResource;
    }

    public ArrayList<String> getListTask() {
        return listItemTask;
    }

    public void setListTask(ArrayList<String> listTask) {
        this.listItemTask = listTask;
    }

    public CardItemString(String title, String text, ArrayList<String> listTask) {
        mTitleResource = title;
        mTextResource = text;
        this.listItemTask = listTask;
    }


    public CardItemString(String title, String text, ArrayList<String> listTask, Integer idTask) {
        mTitleResource = title;
        mTextResource = text;
        this.listItemTask = listTask;
        this.idTask=idTask;
    }

    public String getText() {
        return mTextResource;
    }

    public String getTitle() {
        return mTitleResource;
    }
}

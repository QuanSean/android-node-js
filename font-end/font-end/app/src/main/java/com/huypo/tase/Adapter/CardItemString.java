package com.huypo.tase.Adapter;

import java.util.ArrayList;

public class CardItemString {
    private String mTextResource;
    private String mTitleResource;
    private ArrayList<String> listTask;


    public CardItemString(String title, String text) {
        mTitleResource = title;
        mTextResource = text;
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
        return listTask;
    }

    public void setListTask(ArrayList<String> listTask) {
        this.listTask = listTask;
    }

    public CardItemString(String title, String text, ArrayList<String> listTask) {
        mTitleResource = title;
        mTextResource = text;
        this.listTask = listTask;
    }

    public String getText() {
        return mTextResource;
    }

    public String getTitle() {
        return mTitleResource;
    }
}

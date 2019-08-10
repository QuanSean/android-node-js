package com.huypo.tase.Model;

import java.util.Date;

public class PersonalTable {
    private String txtTableName;
    private String txtTableDescription;
    private Date txtTableDeadline;
    private String token;
    private String idProject;
    private String idTask;

    public PersonalTable(String txtTableName, String txtTableDescription, Date txtTableDeadline, String token, String idProject) {
        this.txtTableName = txtTableName;
        this.txtTableDescription = txtTableDescription;
        this.txtTableDeadline = txtTableDeadline;
        this.token = token;
        this.idProject = idProject;
    }

    public void setTxtTableName(String txtTableName) {
        this.txtTableName = txtTableName;
    }

    public void setTxtTableDescription(String txtTableDescription) {
        this.txtTableDescription = txtTableDescription;
    }

    public void setTxtTableDeadline(Date txtTableDeadline) {
        this.txtTableDeadline = txtTableDeadline;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIdProject() {
        return idProject;
    }

    public void setIdProject(String idProject) {
        this.idProject = idProject;
    }

    public String getIdTask() {
        return idTask;
    }

    public void setIdTask(String idTask) {
        this.idTask = idTask;
    }

    public PersonalTable(String txtTableName, String txtTableDescription, Date txtTableDeadline){

        this.txtTableName = txtTableName;
        this.txtTableDescription = txtTableDescription;
        this.txtTableDeadline = txtTableDeadline;
    }


    public String getTxtTableName() {
        return txtTableName;
    }

    public String getTxtTableDescription() {
        return txtTableDescription;
    }

    public Date getTxtTableDeadline() {
        return txtTableDeadline;
    }
}

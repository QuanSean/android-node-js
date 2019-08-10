package com.huypo.tase.Model;

import java.util.Date;

public class PersonalTable {
    private String txtTableName;
    private String txtTableDescription;
    private Date txtTableDeadline;

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

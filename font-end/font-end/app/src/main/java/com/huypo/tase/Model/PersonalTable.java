package com.huypo.tase.Model;

import java.util.Date;

public class PersonalTable {
    private int imageTable;
    private String txtTableName;
    private String txtTableDescription;
    private Date txtTableDeadline;

    public PersonalTable(int imageTable, String txtTableName, String txtTableDescription, Date txtTableDeadline){
        this.imageTable =imageTable;
        this.txtTableName = txtTableName;
        this.txtTableDescription = txtTableDescription;
        this.txtTableDeadline = txtTableDeadline;
    }

    public int getImageTable() {
        return imageTable;
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

package com.huypo.tase.Activity;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.huypo.tase.Adapter.TableAdapter;
import com.huypo.tase.Model.ListDetails;
import com.huypo.tase.Model.PersonalTable;
import com.huypo.tase.R;

import java.util.ArrayList;

public class MainMenu extends AppCompatActivity {

    private ListView listView;
    private ArrayList<PersonalTable> personalTables;
    private TableAdapter tableAdapter;

    private TextView TableName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        listView = findViewById(R.id.listPersonalTable);
        personalTables = ListDetails.getlist();

        tableAdapter = new TableAdapter(MainMenu.this, personalTables);
        listView.setAdapter(tableAdapter);

        TableName = findViewById(R.id.txtTableName);
    }

    public void btnNewTable(View view){
        final AlertDialog.Builder alert = new AlertDialog.Builder(MainMenu.this);
        View viewtable = getLayoutInflater().inflate(R.layout.table_dialog,null);

        final EditText editTextTable = (EditText)viewtable.findViewById(R.id.editTableName);
//        Button btn_cancel = (Button)viewtable.findViewById(R.id.btnCancel);
        Button btn_create = (Button)viewtable.findViewById(R.id.btnNewTable);

        alert.setView(viewtable);


        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);



        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        alert.show();
    }
}

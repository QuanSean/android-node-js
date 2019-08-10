package com.huypo.tase.Activity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.huypo.tase.Adapter.TableAdapter;
import com.huypo.tase.Model.Item;
import com.huypo.tase.Model.ListDetails;
import com.huypo.tase.Model.PersonalTable;
import com.huypo.tase.Model.Project;
import com.huypo.tase.Model.Respone;
import com.huypo.tase.Model.Task;
import com.huypo.tase.Model.User;
import com.huypo.tase.R;
import com.huypo.tase.Retrofit.IMyService;
import com.huypo.tase.Retrofit.RetrofitClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainMenu extends AppCompatActivity {

     ListView listView;
     ArrayList<PersonalTable> personalTables;



     TextView TableName;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    IMyService iMyService;
    ArrayAdapter arrayAdapter;
    ListView list;
    ArrayList<Project> projectArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Retrofit retrofitClient = RetrofitClient.getInstance();
        iMyService = retrofitClient.create(IMyService.class);
        listView = findViewById(R.id.listPersonalTable);
        personalTables = ListDetails.getlist();
        Date date = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        PersonalTable personalTable= new PersonalTable("Demo"," The technical term for this physical arrangement is codex (in the plural, codices)", calendar.getTime());
        PersonalTable personalTable1= new PersonalTable( "Demo1"," The technical term for this physical arrangement is codex (in the plural, codices)", calendar.getTime());
        PersonalTable personalTable2= new PersonalTable( "Demo1"," The technical term for this physical arrangement is codex (in the plural, codices)", calendar.getTime());
        PersonalTable personalTable3= new PersonalTable( "Demo1"," The technical term for this physical arrangement is codex (in the plural, codices)", calendar.getTime());
        PersonalTable personalTable4= new PersonalTable( "Demo1"," The technical term for this physical arrangement is codex (in the plural, codices)", calendar.getTime());
        PersonalTable personalTable5= new PersonalTable( "Demo1"," The technical term for this physical arrangement is codex (in the plural, codices)", calendar.getTime());
        PersonalTable personalTable6= new PersonalTable( "Demo1"," The technical term for this physical arrangement is codex (in the plural, codices)", calendar.getTime());
        PersonalTable personalTable7= new PersonalTable( "Demo1"," The technical term for this physical arrangement is codex (in the plural, codices)", calendar.getTime());
        PersonalTable personalTable8= new PersonalTable( "Demo1"," The technical term for this physical arrangement is codex (in the plural, codices)", calendar.getTime());
        PersonalTable personalTable9= new PersonalTable( "Demo1"," The technical term for this physical arrangement is codex (in the plural, codices)", calendar.getTime());
        PersonalTable personalTable10= new PersonalTable( "Demo1"," The technical term for this physical arrangement is codex (in the plural, codices)", calendar.getTime());
        PersonalTable personalTable11= new PersonalTable( "Demo1"," The technical term for this physical arrangement is codex (in the plural, codices)", calendar.getTime());
        PersonalTable personalTable12= new PersonalTable( "Demo1"," The technical term for this physical arrangement is codex (in the plural, codices)", calendar.getTime());

//        int imageTable, String txtTableName, String txtTableDescription, Date txtTableDeadline
        ArrayList<PersonalTable> personalTables1 = new ArrayList<>();

        personalTables1.add(personalTable);
        personalTables1.add(personalTable1);
        personalTables1.add(personalTable2);
        personalTables1.add(personalTable3);
        personalTables1.add(personalTable4);
        personalTables1.add(personalTable5);
        personalTables1.add(personalTable6);
        personalTables1.add(personalTable7);
        personalTables1.add(personalTable8);
        personalTables1.add(personalTable9);
        personalTables1.add(personalTable10);
        personalTables1.add(personalTable11);
        personalTables1.add(personalTable12);


        Toast.makeText(MainMenu.this,personalTables1.get(1).toString(), Toast.LENGTH_SHORT).show();

        TableAdapter tableAdapter;


        tableAdapter = new TableAdapter(MainMenu.this, personalTables1);



        listView.setAdapter(tableAdapter);



        TableName = findViewById(R.id.txtTableName);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("User");
        User user = (User) bundle.getSerializable("Info");
        compositeDisposable.add(iMyService.getProject(user.getToken())
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        new Consumer<String>() {
                            @Override
                            public void accept(String reponse) throws Exception {


                                JSONObject jsonObject = new JSONObject(reponse);
                                JSONArray jsonArray = jsonObject.getJSONArray("detail");
                                Project project = new Project();
                                JSONObject demo = new JSONObject();
                                ArrayList<Task> tasks = new ArrayList<>();
                                ArrayList<Item> arrayItem= new ArrayList<>();

                                int aa=0;
                                if (jsonArray.length()==0)
                                {
                                }
                                else
                                {
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        try {
                                            JSONObject oneObject = jsonArray.getJSONObject(i);
                                            demo = jsonArray.getJSONObject(0);
//
//                                            // Pulling items from the array
                                            String _id = oneObject.getString("_id");
                                            String _idUser = oneObject.getString("_idUser");
                                            String title = oneObject.getString("title");
                                            String description = oneObject.getString("description");
                                            Toast.makeText(MainMenu.this,description, Toast.LENGTH_SHORT).show();

//
                                            Boolean done = new Boolean(oneObject.getString("done"));
                                            Date deadline = new SimpleDateFormat("yyyy-MM-dd").parse(oneObject.getString("deadline"));
                                            Boolean deleted = new Boolean(oneObject.getString("deleted"));

                                            JSONArray arrayTask=new JSONArray();

                                            arrayTask = oneObject.getJSONArray("task");
//
                                            if (arrayTask.length()==0)
                                            {

                                            }
                                            else
                                            {
                                                for (int j=0;j<arrayTask.length();j++)
                                                {

//                                                demo = arrayTask.getJSONObject(1);

                                                    JSONObject taskObject = arrayTask.getJSONObject(j);
                                                    Boolean deletedTask = new Boolean(taskObject.getString("delete"));

                                                    if (deletedTask==false)
                                                    {
                                                        String _idTask = taskObject.getString("_id");
                                                        String titleTask = taskObject.getString("title");
                                                        Date deadlineTask = new SimpleDateFormat("yyyy-MM-dd").parse(taskObject.getString("deadline"));
//                                                    Date deadlineTask = new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-01");

                                                        Boolean doneTask = new Boolean(taskObject.getString("done"));
                                                        Item item= new Item(1,"A",true,false);
                                                        arrayItem.add(item);

                                                        Task task= new Task(_idTask,titleTask,deadlineTask,doneTask,false,arrayItem);
                                                        tasks.add(task);
                                                    }
                                                    else {

                                                    }


                                                }
                                            }
//
                                            Project project1 = new Project(_id, _idUser, title, description, done, deadline, deleted, tasks);
                                            projectArrayList.add(project1);

                                        } catch (JSONException e) {
                                            Toast.makeText(MainMenu.this,"Err", Toast.LENGTH_SHORT).show();


                                        }
                                    }
//
                                }


                            }
                        }
                ));
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

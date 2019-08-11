package com.huypo.tase.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.huypo.tase.Adapter.CardItemString;
import com.huypo.tase.Adapter.CardPagerAdapterS;
import com.huypo.tase.Model.Item;
import com.huypo.tase.Model.PersonalTable;
import com.huypo.tase.Model.Task;
import com.huypo.tase.Model.User;
import com.huypo.tase.R;
import com.huypo.tase.Retrofit.IMyService;
import com.huypo.tase.Retrofit.RetrofitClient;
import com.huypo.tase.Utils.ShadowTranformer;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;



public class DashBoard extends AppCompatActivity {
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    IMyService iMyService;

    private ViewPager mViewPager;

    private CardPagerAdapterS mCardAdapter;
    private ShadowTranformer mCardShadowTransformer;

    String titlesText [] = {" Time Table 0", " Time Table 1", " Time Table 2", " Time Table 3", " Time Table 4", " Time Table 5",
            " Time Table 6", " Time Table 7", " Time Table 8", " Time Table 9"};
    String  detailsArray [] = {
            "Time table details radom, Lorem ipsum characters ment for testing of programs and characters or displaying random informations",
            "Time table details radom, Lorem ipsum characters ment for testing of programs and characters or displaying random informations",
            "Time table details radom, Lorem ipsum characters ment for testing of programs and characters or displaying random informations",
            "Time table details radom, Lorem ipsum characters ment for testing of programs and characters or displaying random informations",
            "Time table details radom, Lorem ipsum characters ment for testing of programs and characters or displaying random informations",
            "Time table details radom, Lorem ipsum characters ment for testing of programs and characters or displaying random informations",
            "Time table details radom, Lorem ipsum characters ment for testing of programs and characters or displaying random informations",
            "Time table details radom, Lorem ipsum characters ment for testing of programs and characters or displaying random informations",
            "Time table details radom, Lorem ipsum characters ment for testing of programs and characters or displaying random informations",
            "Time table details radom, Lorem ipsum characters ment for testing of programs and characters or displaying random informations",
    };
    ArrayList<String> a= new ArrayList<>();






    private static Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        Retrofit retrofitClient = RetrofitClient.getInstance();

        iMyService = retrofitClient.create(IMyService.class);

        //get activity form context
        DashBoard.context = getApplicationContext();

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("Project");
        PersonalTable personalTable = (PersonalTable) bundle.getSerializable("Info");




        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mCardAdapter = new CardPagerAdapterS();


        for (int i=0; i<titlesText.length; i++){

        }



        compositeDisposable.add(iMyService.showListTask(personalTable.getToken(), personalTable.getIdProject())
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        new Consumer<String>() {
                            @Override
                            public void accept(String reponse) throws Exception {

                                JSONObject jsonObject = new JSONObject(reponse);
                                JSONArray  detail= jsonObject.getJSONArray("detail");
                                ArrayList<Task> arrayListTask = new ArrayList<>();




                                    for (int i=0;i<detail.length();i++)
                                    {
                                        JSONObject objectTask = detail.getJSONObject(i);
                                        JSONArray taskArray = objectTask.getJSONArray("task");
                                        if (taskArray.length()==0){
                                            TextView a=(TextView) findViewById(R.id.tvTask);
                                            a.setText("Quản lí dự án tốt hơn bằng cách ãy tạo công việc mới");


                                        }
                                        else
                                        {
                                            for (int j=0;j<taskArray.length();j++)
                                            {
                                                JSONObject taskObject = taskArray.getJSONObject(j);
                                                String title=taskObject.getString("title");
                                                String description="";
                                                Boolean done = new Boolean(taskObject.getString("done"));
                                                Boolean delete = new Boolean(taskObject.getString("delete"));
                                                Integer _id=taskObject.getInt("_id");
                                                Date deadline = new SimpleDateFormat("yyyy-MM-dd").parse(taskObject.getString("deadline"));

                                                ArrayList<Item> items= new ArrayList<>();
                                                Task t = new Task(_id,title,deadline,delete,done,items);
                                                arrayListTask.add(t);
//                                                Toast.makeText(DashBoard.this,t.get.toString(), Toast.LENGTH_SHORT).show();
                                            }
//                                            Toast.makeText(DashBoard.this,taskArray.get(0).toString(), Toast.LENGTH_SHORT).show();

                                        }
                                    }

                                    for (Task t: arrayListTask)
                                    {
                                            mCardAdapter.addCardItemS(new CardItemString( t.getTitle(), "",a));
                                    }
                                mCardShadowTransformer = new ShadowTranformer(mViewPager, mCardAdapter);

                                mViewPager.setAdapter(mCardAdapter);
                                mViewPager.setPageTransformer(false, mCardShadowTransformer);
                                mViewPager.setOffscreenPageLimit(3);



//                                for (int i=0; i<titlesText.length; i++){
//
//                                    mCardAdapter.addCardItemS(new CardItemString( titlesText[i], detailsArray[i],a));
//                                }


                            }
                        }
                ));

    }
    public static Context getAppContext() {
        return DashBoard.context;
    }
    
}


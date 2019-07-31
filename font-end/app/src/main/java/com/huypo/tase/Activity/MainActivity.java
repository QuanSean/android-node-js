package com.huypo.tase.Activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.huypo.tase.R;
import com.huypo.tase.Retrofit.IMyService;
import com.huypo.tase.Retrofit.RetrofitClient;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton imgRegister;
    private TextView txtLogin;
    private EditText edt_login_email, edt_login_password;
    private Button btn_login;

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    IMyService iMyService;

    @Override
    protected void onStop(){
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgRegister = findViewById(R.id.imgRegister);
        txtLogin = findViewById(R.id.txtLogin);
        imgRegister.setOnClickListener(this);

        //Init Service
        Retrofit retrofitClient = RetrofitClient.getInstance();
        iMyService = retrofitClient.create(IMyService.class);
        //Init view
        edt_login_email = (EditText)findViewById(R.id.editEmail);
        edt_login_password = (EditText)findViewById(R.id.editPass);

        btn_login = (Button)findViewById(R.id.btnLogin);
        btn_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                loginUser(edt_login_email.getText().toString(),
                        edt_login_password.getText().toString());
            }
        });
    }

    private void loginUser(String email, String password){
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Email cannot be null",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Password cannot be null",Toast.LENGTH_LONG).show();
            return;
        }

        compositeDisposable.add(iMyService.loginUser(email,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String response) throws Exception {
                        Log.d("abc", "-----------respone: "+response);
                        Toast.makeText(MainActivity.this,"" +response,Toast.LENGTH_LONG).show();
                    }
                }));
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v){
        if(v==imgRegister){
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);

            Pair[] pairs = new Pair[1];
            pairs[0] = new Pair<View,String>(txtLogin,"txtLogin");
            ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);
            startActivity(intent, activityOptions.toBundle());
        }
    }
}

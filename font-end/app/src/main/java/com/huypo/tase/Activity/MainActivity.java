package com.huypo.tase.Activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.security.ProviderInstaller;
import com.huypo.tase.Model.Constants;
import com.huypo.tase.Model.Respone;
import com.huypo.tase.R;
import com.huypo.tase.Retrofit.IMyService;
import com.huypo.tase.Retrofit.RetrofitClient;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;
import retrofit2.Retrofit;

import static com.huypo.tase.Model.Validation.validateEmail;
import static com.huypo.tase.Model.Validation.validateFields;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton imgRegister;
    private TextView txtLogin;
    private EditText edt_login_email, edt_login_password;
    private Button btn_login;
    private SharedPreferences mSharedPreferences;


    CompositeDisposable compositeDisposable = new CompositeDisposable();
    IMyService iMyService;

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initSharedPreferences();
        login();
    }
    private void initSharedPreferences() {

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    }
    public void init(){
        imgRegister = findViewById(R.id.imgRegister);
        txtLogin = findViewById(R.id.txtLogin);
        imgRegister.setOnClickListener(this);
        try {
            ProviderInstaller.installIfNeeded(getApplicationContext());
            SSLContext sslContext;
            sslContext = SSLContext.getInstance("TLSv1.2");
            sslContext.init(null, null, null);
            sslContext.createSSLEngine();
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException
                | NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
        //Init Service
        Retrofit retrofitClient = RetrofitClient.getInstance();
        iMyService = retrofitClient.create(IMyService.class);
        //Init view
        edt_login_email = (EditText) findViewById(R.id.editEmail);
        edt_login_password = (EditText) findViewById(R.id.editPass);

        btn_login = (Button) findViewById(R.id.btnLogin);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser(edt_login_email.getText().toString(),
                        edt_login_password.getText().toString());
            }
        });
    }

    private void loginUser(String email, String password) {
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Email cannot be null", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Password cannot be null", Toast.LENGTH_LONG).show();
            return;
        }
        final Intent intent = new Intent(MainActivity.this, DashBoard.class);


        compositeDisposable.add(iMyService.loginUser(email,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse,this::handleError));
//                .subscribe(new Consumer<User>() {
//                               @Override
//                               public void accept(User user) throws Exception {
//                                   if(user !=null){
//                                       Toast.makeText(MainActivity.this, ""+user, Toast.LENGTH_LONG).show();
////                                       startActivity(intent);
//                                   }else {
//                                       Toast.makeText(MainActivity.this, "err", Toast.LENGTH_LONG).show();
//                                   }
//                               }
//                           }
//                ));
    }
    private void login() {

        setError();

        String email = edt_login_email.getText().toString();
        String password = edt_login_password.getText().toString();

        int err = 0;

        if (!validateEmail(email)) {

            err++;
            edt_login_email.setError("Email should be valid !");
        }

        if (!validateFields(password)) {

            err++;
            edt_login_password.setError("Password should not be empty !");
        }

        if (err == 0) {

            loginUser(email,password);

        } else {
            Toast.makeText(this,"Enter Valid Details !",Toast.LENGTH_LONG).show();
        }
    }

    private void setError() {

        edt_login_email.setError(null);
        edt_login_password.setError(null);
    }
    private void handleResponse(Respone response) {

        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(Constants.TOKEN,response.getToken());
//        editor.putString(Constants.EMAIL,response.getMessage());
        editor.apply();

        edt_login_email.setText(null);
        edt_login_password.setText(null);

        Intent intent = new Intent(this, DashBoard.class);
        startActivity(intent);

    }
    private void handleError(Throwable error) {


        if (error instanceof HttpException) {

            try {

                String errorBody = ((HttpException) error).response().errorBody().string();

                Toast.makeText(this,""+errorBody,Toast.LENGTH_LONG).show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this,"Network Error !",Toast.LENGTH_LONG).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        if (v == imgRegister) {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);

            Pair[] pairs = new Pair[1];
            pairs[0] = new Pair<View, String>(txtLogin, "txtLogin");
            ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);
            startActivity(intent, activityOptions.toBundle());
        }
    }
}

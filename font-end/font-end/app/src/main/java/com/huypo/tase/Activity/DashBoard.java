package com.huypo.tase.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.huypo.tase.Adapter.CardItemString;
import com.huypo.tase.Adapter.CardPagerAdapterS;
import com.huypo.tase.Model.PersonalTable;
import com.huypo.tase.Model.User;
import com.huypo.tase.R;
import com.huypo.tase.Retrofit.IMyService;
import com.huypo.tase.Retrofit.RetrofitClient;
import com.huypo.tase.Utils.ShadowTranformer;

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
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        Retrofit retrofitClient = RetrofitClient.getInstance();
        iMyService = retrofitClient.create(IMyService.class);



        context = this;

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("Project");
        PersonalTable personalTable = (PersonalTable) bundle.getSerializable("Info");




        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mCardAdapter = new CardPagerAdapterS();


        for (int i=0; i<titlesText.length; i++){

            mCardAdapter.addCardItemS(new CardItemString( titlesText[i], detailsArray[i]));
        }

        mCardShadowTransformer = new ShadowTranformer(mViewPager, mCardAdapter);

        mViewPager.setAdapter(mCardAdapter);
        mViewPager.setPageTransformer(false, mCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);

        compositeDisposable.add(iMyService.showListTask(personalTable.getToken(), personalTable.getIdProject())
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        new Consumer<String>() {
                            @Override
                            public void accept(String reponse) throws Exception {

                                Toast.makeText(DashBoard.this, reponse, Toast.LENGTH_SHORT).show();

                            }
                        }
                ));

    }
    
}


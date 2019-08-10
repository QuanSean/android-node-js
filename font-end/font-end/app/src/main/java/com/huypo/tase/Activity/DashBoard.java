package com.huypo.tase.Activity;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.huypo.tase.Adapter.CardItemString;
import com.huypo.tase.Adapter.CardPagerAdapterS;
import com.huypo.tase.R;
import com.huypo.tase.Utils.ShadowTranformer;

public class DashBoard extends AppCompatActivity {

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

        context = this;


        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mCardAdapter = new CardPagerAdapterS();


        for (int i=0; i<titlesText.length; i++){

            mCardAdapter.addCardItemS(new CardItemString( titlesText[i], detailsArray[i]));
        }

        mCardShadowTransformer = new ShadowTranformer(mViewPager, mCardAdapter);

        mViewPager.setAdapter(mCardAdapter);
        mViewPager.setPageTransformer(false, mCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);

    }
    
}

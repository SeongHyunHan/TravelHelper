package com.han.seong.travelhelper;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class AddTravel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_travel);

        setUpTabContent();


    }


    public void setUpTabContent(){
        TabHost tabHost = (TabHost)findViewById(R.id.tabHost);
        tabHost.setup();

        TabSpec spec1 = tabHost.newTabSpec("Tab1").setContent(R.id.tab1).setIndicator(getString(R.string.tab1));
        tabHost.addTab(spec1);
        TabSpec spec2 = tabHost.newTabSpec("Tab1").setContent(R.id.tab2).setIndicator(getString(R.string.tab2));
        tabHost.addTab(spec2);
        TabSpec spec3 = tabHost.newTabSpec("Tab1").setContent(R.id.tab3).setIndicator(getString(R.string.tab3));
        tabHost.addTab(spec3);
        TabSpec spec4 = tabHost.newTabSpec("Tab1").setContent(R.id.tab4).setIndicator(getString(R.string.tab4));
        tabHost.addTab(spec4);
        TabSpec spec5 = tabHost.newTabSpec("Tab1").setContent(R.id.tab5).setIndicator(getString(R.string.tab5));
        tabHost.addTab(spec5);

        tabHost.getTabWidget().getChildAt(0).getLayoutParams().height=80;
        tabHost.getTabWidget().getChildAt(1).getLayoutParams().height=80;
        tabHost.getTabWidget().getChildAt(2).getLayoutParams().height=80;
        tabHost.getTabWidget().getChildAt(3).getLayoutParams().height=80;
        tabHost.getTabWidget().getChildAt(4).getLayoutParams().height=80;


    }

}

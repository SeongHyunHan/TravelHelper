package com.han.seong.travelhelper;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

public class AddTravel extends AppCompatActivity {

    private FloatingActionButton addFAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_travel);

        setUpTabContent();

        Toolbar toolbar = (Toolbar) findViewById(R.id.at_toolBar);
        setSupportActionBar(toolbar);

        addFAB = (FloatingActionButton)findViewById(R.id.addFAB);
        addFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }


    public void setUpTabContent(){
        TabHost tabHost = (TabHost)findViewById(R.id.tabHost);
        tabHost.setup();

        TabSpec spec1 = tabHost.newTabSpec("Tab1").setContent(R.id.tab1).setIndicator(getString(R.string.tab1));
        tabHost.addTab(spec1);
        TabSpec spec2 = tabHost.newTabSpec("Tab2").setContent(R.id.tab2).setIndicator(getString(R.string.tab2));
        tabHost.addTab(spec2);
        TabSpec spec3 = tabHost.newTabSpec("Tab3").setContent(R.id.tab3).setIndicator(getString(R.string.tab3));
        tabHost.addTab(spec3);
        TabSpec spec4 = tabHost.newTabSpec("Tab4").setContent(R.id.tab4).setIndicator(getString(R.string.tab4));
        tabHost.addTab(spec4);
        TabSpec spec5 = tabHost.newTabSpec("Tab5").setContent(R.id.tab5).setIndicator(getString(R.string.tab5));
        tabHost.addTab(spec5);

        tabHost.getTabWidget().getChildAt(0).getLayoutParams().height=120;
        tabHost.getTabWidget().getChildAt(1).getLayoutParams().height=120;
        tabHost.getTabWidget().getChildAt(2).getLayoutParams().height=120;
        tabHost.getTabWidget().getChildAt(3).getLayoutParams().height=120;
        tabHost.getTabWidget().getChildAt(4).getLayoutParams().height=120;


    }

    // Setting Option Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_travel_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.about:
                Toast.makeText(this, "About Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.save:
                //need to sql query to save travel info
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}

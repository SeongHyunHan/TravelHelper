package com.han.seong.travelhelper;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.han.seong.travelhelper.vo.TravelVo;

public class MainActivity extends AppCompatActivity {

    private String[] navItems = {"Home", "Setting", "About"};
    private ListView lvNavList;
    private FrameLayout flContainer;
    private DrawerLayout dlDrawer;
    private ActionBarDrawerToggle dtToggle;
    private FloatingActionButton mainFAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        startActivity(new Intent(this, SplashScreen.class));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setLogo(R.drawable.ic_drawer_layout);


        /* Need to Fix so It will Start after Splash Screen
        boolean isTravelExist = false; //SQL query to check if travel exist
        if(!isTravelExist){
            //if there aren't any travel exist then send to new page
            startActivity(new Intent(this, AddTravel.class));
        }*/
        lvNavList=(ListView)findViewById(R.id.activity_main_nav_list);
        flContainer=(FrameLayout)findViewById(R.id.activity_main_container);
        dlDrawer = (DrawerLayout)findViewById(R.id.activity_main_drawer);
        mainFAB = (FloatingActionButton)findViewById(R.id.mainFAB);

        lvNavList.setAdapter(
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, navItems));
        lvNavList.setOnItemClickListener(new DrawerItemClickListener());


        dtToggle = new ActionBarDrawerToggle(this, dlDrawer, R.string.open_drawer, R.string.close_drawer){

            @Override
            public void onDrawerClosed(View drawerView){
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView){
                super.onDrawerOpened(drawerView);
            }
        };

        dlDrawer.post(new Runnable(){
            @Override
            public void run() {
                dtToggle.syncState();
            }
        });
        dlDrawer.setDrawerListener(dtToggle);
        //getActionBar().setDisplayHomeAsUpEnabled(true);

        mainFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddTravel.class));
            }
        });



    }

    private class DrawerItemClickListener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int position, long id){
            switch(position){
                case 0:
                    flContainer.setBackgroundColor(Color.parseColor("#A52A2A"));
                    break;
                case 1:
                    flContainer.setBackgroundColor(Color.parseColor("#5F9EA0"));
            }
        }
    }
}

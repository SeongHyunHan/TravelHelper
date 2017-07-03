package com.han.seong.travelhelper.travelDetail;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TabHost;


import com.han.seong.travelhelper.MainActivity;
import com.han.seong.travelhelper.R;

public class TravelDetail extends AppCompatActivity{

    private String[] navItems = {"Home", "Setting", "About"};
    private ListView lvNavList;
    private FrameLayout flContainer;
    private DrawerLayout dlDrawer;
    private ActionBarDrawerToggle dtToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_detail);

        settingNavigationDrawer();
        setUpTabContent();

        Toolbar toolbar = (Toolbar) findViewById(R.id.dt_ToolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setLogo(R.drawable.ic_drawer_layout);
        toolbar.setTitle("This is Placement for Travel Title");

    }

    public void setUpTabContent(){
        TabHost tabHost = (TabHost)findViewById(R.id.dt_tabHost);
        tabHost.setup();

        TabHost.TabSpec spec1 = tabHost.newTabSpec("Tab1").setContent(R.id.dt_tab1).setIndicator(getString(R.string.tab1));
        tabHost.addTab(spec1);
        TabHost.TabSpec spec2 = tabHost.newTabSpec("Tab2").setContent(R.id.dt_tab2).setIndicator(getString(R.string.tab2));
        tabHost.addTab(spec2);
        TabHost.TabSpec spec3 = tabHost.newTabSpec("Tab3").setContent(R.id.dt_tab3).setIndicator(getString(R.string.tab3));
        tabHost.addTab(spec3);

        tabHost.getTabWidget().getChildAt(0).getLayoutParams().height=120;
        tabHost.getTabWidget().getChildAt(1).getLayoutParams().height=120;
        tabHost.getTabWidget().getChildAt(2).getLayoutParams().height=120;


    }

    //Setting NavigationDrawer
    private void settingNavigationDrawer() {
        lvNavList=(ListView)findViewById(R.id.activity_dt_nav_list);
        dlDrawer = (DrawerLayout)findViewById(R.id.activity_dt_drawer);

        lvNavList.setAdapter(
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, navItems));
        lvNavList.setOnItemClickListener(new TravelDetail.DrawerItemClickListener());

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
    }

    private class DrawerItemClickListener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int position, long id){
            switch(position){
                case 0:

                    break;
                case 1:

            }
        }
    }
    // -------End Navigation Drawer
}

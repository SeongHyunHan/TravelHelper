package com.han.seong.travelhelper;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.facebook.stetho.Stetho;
import com.han.seong.travelhelper.adapter.Main_RecyclerAdapter;
import com.han.seong.travelhelper.realm.Migration;
import com.han.seong.travelhelper.travelDetail.TravelDetail;
import com.han.seong.travelhelper.vo.Travel;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private String[] navItems = {"Home", "Setting", "About"};
    private ListView lvNavList;
    private FrameLayout flContainer;
    private DrawerLayout dlDrawer;
    private ActionBarDrawerToggle dtToggle;
    private Realm mRealm;

    //ToolBar
    @BindView(R.id.toolBar) Toolbar toolbar;

    //Floating Action Button
    private FloatingActionButton mainFAB;

    //Recycler View (Variable)
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<Travel> data;
    private static ArrayList<Integer> removedItems;
    public static View.OnClickListener myOnClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        startActivity(new Intent(this, SplashScreen.class));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                .build());
        ButterKnife.bind(this);

        initRealm();
        setSupportActionBar(toolbar);
        getSupportActionBar().setLogo(R.drawable.ic_drawer_layout);

        settingNavigationDrawer();

        /* Need to Fix so It will Start after Splash Screen
        boolean isTravelExist = false; //SQL query to check if travel exist
        if(!isTravelExist){
            //if there aren't any travel exist then send to new page
            startActivity(new Intent(this, AddTravel.class));
        }*/


        flContainer=(FrameLayout)findViewById(R.id.activity_main_container);
        mainFAB = (FloatingActionButton)findViewById(R.id.mainFAB);
        mainFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddTravel.class));
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        settingCardView();
    }

    //Setting NavigationDrawer
    private void settingNavigationDrawer() {
        lvNavList=(ListView)findViewById(R.id.activity_main_nav_list);
        dlDrawer = (DrawerLayout)findViewById(R.id.activity_main_drawer);

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
    // -------End Navigation Drawer

    // Setting CardView
    private void settingCardView() {
        RealmResults<Travel> realmResults = mRealm.where(Travel.class).findAll();
        if (realmResults.size() == 0) {
            //startActivity(new Intent(this, AddTravel.class));
        }else {
            recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            recyclerView.setHasFixedSize(true);

            layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());

            data = addTravelDataTest();

            removedItems = new ArrayList<Integer>();

            adapter = new Main_RecyclerAdapter(data);
            recyclerView.setAdapter(adapter);
        }

    }

    private ArrayList<Travel> addTravelDataTest() {
        ArrayList<Travel> travelInfo = new ArrayList<Travel>();
        RealmResults<Travel> realmResults = mRealm.where(Travel.class).findAll();

            for (int i = 0; i < realmResults.size(); i++) {
                travelInfo.add(realmResults.get(i));
            }
            return travelInfo;

    }

    // ---------End CardView

    // Setting Option Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.about:
                Toast.makeText(this, "About Clicked", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initRealm(){
        Realm.init(this);
        mRealm = Realm.getDefaultInstance();
    }
}


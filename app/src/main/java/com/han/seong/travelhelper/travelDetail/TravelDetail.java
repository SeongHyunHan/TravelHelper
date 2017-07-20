package com.han.seong.travelhelper.travelDetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TabHost;

import com.han.seong.travelhelper.R;
import com.han.seong.travelhelper.adapter.DT_General_RecyclerAdapter;
import com.han.seong.travelhelper.adapter.DT_Person_RecyclerAdapter;
import com.han.seong.travelhelper.vo.Finance;
import com.han.seong.travelhelper.vo.Person;
import com.han.seong.travelhelper.vo.Travel;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class TravelDetail extends AppCompatActivity{

    private String[] navItems = {"Home", "Setting", "About"};

    @BindView(R.id.td_general_recyclerView) RecyclerView generalRecyclerView;
    @BindView(R.id.td_person_recyclerView) RecyclerView personRecyclerView;
    @BindView(R.id.dt_ToolBar) Toolbar toolbar;
    @BindView(R.id.walletFAB) FloatingActionButton walletFAB;
    @BindView(R.id.activity_dt_nav_list) ListView lvNavList;
    @BindView(R.id.activity_dt_drawer) DrawerLayout dlDrawer;
    private ActionBarDrawerToggle dtToggle;

    private Realm mRealm;
    private Intent intent;
    private String title;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_detail);
        ButterKnife.bind(this);

        RealmConfiguration config = new RealmConfiguration.Builder()
                //.schemaVersion(0)
                //.migration(new Migration())
                .deleteRealmIfMigrationNeeded() //개발중 일때 Realm 객체를 전부 지우고 시작.
                .build();
        mRealm = Realm.getInstance(config);

        settingNavigationDrawer();
        setUpTabContent();

        setSupportActionBar(toolbar);
        getSupportActionBar().setLogo(R.drawable.ic_drawer_layout);
    }

    @Override
    protected void onResume() {
        super.onResume();
        intent = getIntent();
        bundle = intent.getExtras();
        getSupportActionBar().setTitle(bundle.getString("Title"));
        getSupportActionBar().setSubtitle(bundle.getString("Subtitle"));

        title = bundle.getString("realmSearch");

        getFinanceData();
        getPersonData();

        walletFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), AddFinance.class);
                intent1.putExtra("title", title);
                startActivity(intent1);
            }
        });
    }

    //set up tab title
    public void setUpTabContent(){
        TabHost tabHost = (TabHost)findViewById(R.id.dt_tabHost);
        tabHost.setup();

        TabHost.TabSpec spec1 = tabHost.newTabSpec("Tab1").setContent(R.id.dt_tab1).setIndicator(getString(R.string.td_tab1));
        tabHost.addTab(spec1);
        TabHost.TabSpec spec2 = tabHost.newTabSpec("Tab2").setContent(R.id.dt_tab2).setIndicator(getString(R.string.td_tab2));
        tabHost.addTab(spec2);
        TabHost.TabSpec spec3 = tabHost.newTabSpec("Tab3").setContent(R.id.dt_tab3).setIndicator(getString(R.string.td_tab3));
        tabHost.addTab(spec3);

        tabHost.getTabWidget().getChildAt(0).getLayoutParams().height=120;
        tabHost.getTabWidget().getChildAt(1).getLayoutParams().height=120;
        tabHost.getTabWidget().getChildAt(2).getLayoutParams().height=120;
    }

    //Setting NavigationDrawer
    private void settingNavigationDrawer() {
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

    private void getFinanceData(){
        Travel realmResult = mRealm.where(Travel.class).equalTo("title", title).findFirst();
        List<Finance> finances = realmResult.getFinances();
        List<Finance> financeList = new ArrayList<Finance>();
        Boolean exist = false;
        for(int i = 0; i < finances.size(); i++){
                Finance finance = finances.get(i);
                financeList.add(finance);
                exist = true;
        }
        if(exist) {
            generalRecyclerView.setAdapter(new DT_General_RecyclerAdapter(financeList, R.layout.dt_overview_row));
            generalRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            generalRecyclerView.setItemAnimator(new DefaultItemAnimator());
            generalRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        }
    }

    private void getPersonData(){
        Travel realmResult = mRealm.where(Travel.class).equalTo("title", title).findFirst();
        List<Person> personList = realmResult.getPeople();
        if(personList.size() != 0) {
            personRecyclerView.setAdapter(new DT_Person_RecyclerAdapter(personList, R.layout.dt_person_row));
            personRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            personRecyclerView.setItemAnimator(new DefaultItemAnimator());
            personRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        }
    }



}

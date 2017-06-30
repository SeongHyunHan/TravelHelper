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
import android.widget.Spinner;
import android.widget.Toast;

import com.han.seong.travelhelper.adapter.AT_SpinnerAdapter;
import com.han.seong.travelhelper.adapter.MyRecyclerAdapter;
import com.han.seong.travelhelper.sqlite.DBManager;
import com.han.seong.travelhelper.travelDetail.TravelDetail;
import com.han.seong.travelhelper.vo.Travel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String[] navItems = {"Home", "Setting", "About"};
    private ListView lvNavList;
    private FrameLayout flContainer;
    private DrawerLayout dlDrawer;
    private ActionBarDrawerToggle dtToggle;

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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setLogo(R.drawable.ic_drawer_layout);
        DBManager db = new DBManager(getApplicationContext());

        settingNavigationDrawer();
        settingCardView();

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
        myOnClickListener = new MyOnClickListener(this);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        data = addTravelDataTest();

        removedItems = new ArrayList<Integer>();

        adapter = new MyRecyclerAdapter(data);
        recyclerView.setAdapter(adapter);
    }

    private ArrayList<Travel> addTravelDataTest() {
        ArrayList<Travel> travelInfo = new ArrayList<Travel>();
        Travel oneTravel = new Travel();
        oneTravel.setTitle("TestTitle");
        oneTravel.setCountry("TestCountry");
        travelInfo.add(oneTravel);
        travelInfo.add(oneTravel);
        travelInfo.add(oneTravel);
        travelInfo.add(oneTravel);
        travelInfo.add(oneTravel);
        travelInfo.add(oneTravel);
        travelInfo.add(oneTravel);

        return travelInfo;
    }

    private class MyOnClickListener implements View.OnClickListener {
        private final Context context;
        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), TravelDetail.class);
            intent.putExtra("Title", data.get(0).getTitle());
            intent.putExtra("Country", data.get(0).getCountry());
            startActivity(intent);
        }
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
}

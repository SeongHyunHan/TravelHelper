package com.han.seong.travelhelper.travelDetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.han.seong.travelhelper.AddTravel;
import com.han.seong.travelhelper.R;
import com.han.seong.travelhelper.adapter.AF_RecyclerAdapter;
import com.han.seong.travelhelper.adapter.AF_SpinnerAdapter;
import com.han.seong.travelhelper.adapter.Main_RecyclerAdapter;
import com.han.seong.travelhelper.vo.Person;
import com.han.seong.travelhelper.vo.Travel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class AddFinance extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private Realm mRealm;

    @BindView(R.id.af_person_recyclerView) RecyclerView recyclerView;
    @BindView(R.id.af_toolBar) Toolbar toolbar;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static ArrayList<Person> data;

    private int categories[] = {R.drawable.ic_hotel, R.drawable.ic_transit, R.drawable.ic_parking, R.drawable.ic_food, R.drawable.ic_drink, R.drawable.ic_etc };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_finance);
        ButterKnife.bind(this);
        mRealm = Realm.getDefaultInstance();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Payment");

        Spinner spin =(Spinner)findViewById(R.id.af_spinner);
        spin.setOnItemSelectedListener(this);

        AF_SpinnerAdapter af_spinnerAdapter = new AF_SpinnerAdapter(getApplicationContext(), categories);
        spin.setAdapter(af_spinnerAdapter);


    }

    @Override
    protected void onResume() {
        super.onResume();
        settingCardView();
    }

    // Spinner Click Listener
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
                saveToDatabase();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveToDatabase() {
            mRealm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {

                }
            }, new Realm.Transaction.OnSuccess() {
                @Override
                public void onSuccess() {
                    Toast.makeText(AddFinance.this, "Insert Successful", Toast.LENGTH_SHORT).show();
                }
            }, new Realm.Transaction.OnError() {
                @Override
                public void onError(Throwable error) {

                    Toast.makeText(AddFinance.this, "Error!", Toast.LENGTH_SHORT).show();
                }
            });
    }

    private void settingCardView() {
            recyclerView = (RecyclerView) findViewById(R.id.af_person_recyclerView);
            recyclerView.setHasFixedSize(true);

            layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());

            data = addPersonData();

            adapter = new AF_RecyclerAdapter(data, R.layout.af_person_row);
            recyclerView.setAdapter(adapter);

    }

    private ArrayList<Person> addPersonData() {
        ArrayList<Person> peopleInfo = new ArrayList<Person>();
        Travel realmResult = mRealm.where(Travel.class).equalTo("title", "gdfj").findFirst();

        for (int i = 0; i < realmResult.getPeople().size(); i++) {
            peopleInfo.add(realmResult.getPeople().get(i));
        }
        return peopleInfo;

    }
}

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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.han.seong.travelhelper.R;
import com.han.seong.travelhelper.adapter.AF_RecyclerAdapter;
import com.han.seong.travelhelper.adapter.AF_SpinnerAdapter;
import com.han.seong.travelhelper.vo.Finance;
import com.han.seong.travelhelper.vo.PaymentInfo;
import com.han.seong.travelhelper.vo.Person;
import com.han.seong.travelhelper.vo.Travel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmAsyncTask;

public class AddFinance extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private Realm mRealm;

    @BindView(R.id.af_edtPaymentTitle) EditText paymentTitle;
    @BindView(R.id.af_edtDate) EditText paymentDate;
    @BindView(R.id.af_edtPrice) EditText totalPrice;

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
        RealmAsyncTask realmAsyncTask = mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Finance finance = new Finance();
                finance.setPaymentTitle(paymentTitle.getText().toString());

                PaymentInfo payment = new PaymentInfo();
                payment.setPaymentTitle(paymentTitle.getText().toString());

                Travel realmResult = mRealm.where(Travel.class).equalTo("title", "gdfj").findFirst();
                realmResult.getFinances().add(finance);



                   /*
                    try {
                        Travel travel = realm.createObject(Travel.class);
                        long travelId = realm.where(Travel.class).count();
                        travel.setTravelNo((int)travelId+1);
                        travel.setTitle(edt_title.getText().toString());
                        travel.setCountry(tv_country.getText().toString());

                        String myFormat = "MM/dd/yy"; //In which you need put here
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
                        Date startDate = sdf.parse(tv_startDate.getText().toString());
                        travel.setStartDate(startDate);
                        Date endDate = sdf.parse(tv_endDate.getText().toString());
                        travel.setEndDate(endDate);

                        travel.setTotalBudget(totalBudget);
                        travel.setImage("");

                        for (int i = 0; i < peopleList.size(); i++) {
                            Person person = peopleList.get(i);
                            travel.getPeople().add(person);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    */
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

package com.han.seong.travelhelper.travelDetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.han.seong.travelhelper.R;
import com.han.seong.travelhelper.adapter.AF_SpinnerAdapter;

import io.realm.Realm;

public class AddFinance extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private Realm mRealm;

    int categories[] = {R.drawable.ic_hotel, R.drawable.ic_transit};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_finance);

        Spinner spin =(Spinner)findViewById(R.id.af_spinner);
        spin.setOnItemSelectedListener(this);

        AF_SpinnerAdapter af_spinnerAdapter = new AF_SpinnerAdapter(getApplicationContext(), categories);
        spin.setAdapter(af_spinnerAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

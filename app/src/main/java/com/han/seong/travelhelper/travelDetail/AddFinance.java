package com.han.seong.travelhelper.travelDetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.han.seong.travelhelper.R;

import io.realm.Realm;

public class AddFinance extends AppCompatActivity{
    private Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_finance);
    }
}

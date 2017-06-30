package com.han.seong.travelhelper;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

import com.han.seong.travelhelper.adapter.AT_SpinnerAdapter;

public class AddTravel extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private EditText edt_startDate;
    private EditText edt_endDate;
    private FloatingActionButton addFAB;
    String[] countries={"USA", "Canada", "Europe", "Japan", "Korea"};
    int flags[] = {R.drawable.ic_us, R.drawable.ic_canada, R.drawable.ic_europe, R.drawable.ic_japan, R.drawable.ic_korea};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_travel);

        edt_startDate=(EditText)findViewById(R.id.edt_startDate);
        edt_endDate=(EditText)findViewById(R.id.edt_endDate);


        Spinner spin =(Spinner)findViewById(R.id.countrySpinner);
        spin.setOnItemSelectedListener(this);

        AT_SpinnerAdapter at_spinnerAdapter = new AT_SpinnerAdapter(getApplicationContext(), flags, countries);
        spin.setAdapter(at_spinnerAdapter);

        setUpTabContent();
        setUpStartCalendar();
        setUpEndCalendar();

        Toolbar toolbar = (Toolbar) findViewById(R.id.at_toolBar);
        setSupportActionBar(toolbar);

        addFAB = (FloatingActionButton)findViewById(R.id.addFAB);
        addFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id){
        Toast.makeText(getApplicationContext(), countries[position], Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0){

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


    public void setUpStartCalendar(){

        final Calendar myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(edt_startDate, myCalendar);
            }
        };

        edt_startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddTravel.this, date, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    private void setUpEndCalendar() {
        final Calendar myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(edt_endDate, myCalendar);
            }
        };

        edt_endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddTravel.this, date, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }


    private void updateLabel(EditText editText, Calendar myCalendar) {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editText.setText(sdf.format(myCalendar.getTime()));
    }
}

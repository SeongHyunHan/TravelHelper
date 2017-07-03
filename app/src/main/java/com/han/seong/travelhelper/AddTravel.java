package com.han.seong.travelhelper;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

import com.han.seong.travelhelper.adapter.AT_SpinnerAdapter;
import com.han.seong.travelhelper.vo.Person;

public class AddTravel extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private EditText edt_title, edt_startDate, edt_endDate, edt_fName, edt_lName, edt_budget;
    private TextView tv_title, tv_country, tv_startDate, tv_endDate, tv_people;
    private FloatingActionButton addFAB;
    String[] countries={"USA", "Canada", "Europe", "Japan", "Korea"};
    int flags[] = {R.drawable.ic_us, R.drawable.ic_canada, R.drawable.ic_europe, R.drawable.ic_japan, R.drawable.ic_korea};
    ArrayList<Person> peopleList;
    ArrayList<String> adapterInfo;
    ArrayAdapter personAdapter;
    ListView lv_peopleInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_travel);

        edt_title = (EditText)findViewById(R.id.edt_travelTitle);
        edt_startDate = (EditText)findViewById(R.id.edt_startDate);
        edt_endDate = (EditText)findViewById(R.id.edt_endDate);
        tv_title = (TextView)findViewById(R.id.tv_at_travelTitle);
        tv_country = (TextView)findViewById(R.id.tv_at_countryInfo);
        tv_startDate = (TextView)findViewById(R.id.tv_at_startDateInfo);
        tv_endDate = (TextView)findViewById(R.id.tv_at_endDateInfo);
        tv_people = (TextView)findViewById(R.id.tv_at_peopleInfo);

        Spinner spin =(Spinner)findViewById(R.id.countrySpinner);
        spin.setOnItemSelectedListener(this);

        AT_SpinnerAdapter at_spinnerAdapter = new AT_SpinnerAdapter(getApplicationContext(), flags, countries);
        spin.setAdapter(at_spinnerAdapter);

        peopleList = new ArrayList<Person>();
        adapterInfo = new ArrayList<String>();
        lv_peopleInfo = (ListView)findViewById(R.id.lv_at_people);
        personAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, adapterInfo);
        lv_peopleInfo.setAdapter(personAdapter);

        setUpTabContent();
        setUpStartCalendar();
        setUpEndCalendar();

        Toolbar toolbar = (Toolbar) findViewById(R.id.at_toolBar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Add Travel Info");



        addFAB = (FloatingActionButton)findViewById(R.id.addFAB);
        addFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpAlertDialog();
            }
        });


        edt_title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                tv_title.setText(edt_title.getText());
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id){
        tv_country.setText(countries[position]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0){
        tv_country.setText("");
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

        tabHost.getTabWidget().getChildAt(0).getLayoutParams().height=120;
        tabHost.getTabWidget().getChildAt(1).getLayoutParams().height=120;
        tabHost.getTabWidget().getChildAt(2).getLayoutParams().height=120;
        tabHost.getTabWidget().getChildAt(3).getLayoutParams().height=120;
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
                updateLabel(edt_startDate, myCalendar, 0);
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
                updateLabel(edt_endDate, myCalendar, 1);
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


    private void updateLabel(EditText editText, Calendar myCalendar, int i) {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editText.setText(sdf.format(myCalendar.getTime()));
        switch(i){
            case 0:
                tv_startDate.setText(sdf.format(myCalendar.getTime()));
                break;
            case 1:
                tv_endDate.setText(sdf.format(myCalendar.getTime()));
                break;
        }
    }

    public void popUpAlertDialog(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.add_people_dialog, null);
        alertDialog.setView(dialogView);

        edt_fName = (EditText)findViewById(R.id.edt_ap_fName);
        edt_lName = (EditText)findViewById(R.id.edt_ap_lName);
        edt_budget = (EditText)findViewById(R.id.edt_ap_budget);
        
        alertDialog.setTitle("Add Person");
        alertDialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Person p = new Person();
                p.setFirstName("Seong Hyun");
                p.setLastName("Han");
                //if(edt_budget.getText().toString() == null || edt_budget.getText().toString().equals("")){
                    p.setBalance(50.00);
               // }else{
                    //p.setBalance(Double.parseDouble(edt_budget.getText().toString()));
                //}
                p.setOwnBudget(0.00);

                peopleList.add(p);
                String fullInfo = "";
                fullInfo += "Name: " + p.getFirstName() + " " + p.getLastName() + "\nBudget: € " + p.getBalance();
                adapterInfo.add(fullInfo);
                personAdapter.notifyDataSetChanged();

                String name = "";

                for(int i = 0; i < peopleList.size(); i++){
                    name += peopleList.get(i).getFirstName() + " " + peopleList.get(i).getLastName();

                    if(i != peopleList.size()-1){
                        name += ", ";
                    }
                }

                tv_people.setText("" + peopleList.size() + " 명 (" + name + ")");
                Toast.makeText(AddTravel.this, "OK Button Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(AddTravel.this, "Cancel Button Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog dialog = alertDialog.create();
        dialog.show();

    }
}

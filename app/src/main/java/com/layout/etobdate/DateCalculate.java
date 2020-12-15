package com.layout.etobdate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DateCalculate extends AppCompatActivity {

    TextView text;
    Spinner spinner;

    public Integer monthValue;

    public int leapYear(int year){
        int what = 0;
        if(year % 4 ==0 && (year % 100 != 0 || year % 400 ==0)){
            what = 1;
        }else {
            what = 0;
        }
        return what;
    }

    public void birthCalculator(int birthDay, int birthMonth, int birthYear){

        int bd = 0;
        int bm = 0;
        int errorMsg = 0;
        int countDay = 0;

        Calendar c = Calendar.getInstance();
        int currentDay = c.get(Calendar.DAY_OF_MONTH);
        int currentMonth = c.get(Calendar.MONTH) + 1;
        int currentYear = c.get(Calendar.YEAR);

        if(birthMonth != 0){
            bm = 1;
            if (birthMonth == 1 || birthMonth ==3 || birthMonth ==5 || birthMonth==7 || birthMonth==9 || birthMonth==11){
                if(birthDay > 0 && birthDay < 32){
                    bd = 1;
                    countDay = 31;
                }else {
                    errorMsg = 1;
                }
            }
            else if (birthMonth == 4 || birthMonth ==6 || birthMonth ==8 || birthMonth==10 || birthMonth==12){
                if(birthDay > 0 && birthDay < 31){
                    bd = 1;
                    countDay = 30;
                }else {
                    errorMsg = 1;
                }
            }
            else if(birthMonth == 2){
                int leapYearOrNot = leapYear(birthYear);
                if(leapYearOrNot == 1){
                    if(birthDay > 0 && birthDay < 30){
                        bd = 1;
                        countDay = 29;
                    }else {
                        errorMsg = 1;
                    }
                }
                else if(leapYearOrNot == 0){
                    if(birthDay > 0 && birthDay < 29){
                        bd = 1;
                        countDay = 28;
                    }else {
                        errorMsg = 1;
                    }
                }
            }
        }else{
            errorMsg = 1;
        }

        if(errorMsg == 1){
            text.setVisibility(View.VISIBLE);
            text.setText("Properly Enter Birth Date.");
        }else{
            if(birthDay > currentDay){
                currentDay = currentDay + countDay;
                birthMonth = birthMonth + 1;
            }
            if(birthMonth > currentMonth){
                currentMonth = currentMonth + 12;
                birthYear = birthYear + 1;
            }

            int day = currentDay - birthDay;
            int month = currentMonth - birthMonth;
            int year = currentYear - birthYear;

            double totalDay = (365.25*year)+(30.4167*month)+day;
            double totalHour = Math.round(totalDay)*24;

            text.setVisibility(View.VISIBLE);
            text.setText("Age : "+year+" Year "+month+" Month "+day+" Day."+"\nTotal : "+Math.round(totalDay)+" Days.\nTotal : "+Math.round(totalHour)+" Hours.");
        }
    }

    public void Calculate(View view){
        EditText birthday = (EditText)findViewById(R.id.birthday);
        EditText birthyear = (EditText)findViewById(R.id.birthyear);

        try {
            Integer birthdays = Integer.parseInt(birthday.getText().toString());
            Integer birthyears = Integer.parseInt(birthyear.getText().toString());
            Integer birthmonths = monthValue;

            if(birthdays != null && birthdays != null && birthmonths != null){
                birthCalculator(birthdays, birthmonths, birthyears);
            }
        }catch (Exception e){
            Toast.makeText(this, "Properly Enter Birth Date.", Toast.LENGTH_SHORT).show();
        }

        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_calculate);

        text = (TextView)findViewById(R.id.text8);
        spinner = (Spinner)findViewById(R.id.birthmonth);

        //create month list
        List<String> list = new ArrayList<String>();
        list.add("Select Month");
        list.add("January");
        list.add("February");
        list.add("March");
        list.add("April");
        list.add("May");
        list.add("June");
        list.add("July");
        list.add("August");
        list.add("September");
        list.add("October");
        list.add("November");
        list.add("December");

        // add view
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.custom_spinner, list);
        arrayAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                DateCalculate.this.monthValue = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
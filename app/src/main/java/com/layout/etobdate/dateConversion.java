package com.layout.etobdate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class dateConversion extends AppCompatActivity {
    TextView msg1;
    TextView msg2;
    Spinner spinner;

    public Integer monthValue;

    public String stringValue(int value){
        String v = String.valueOf(value);
        Character bangla_number[]={'০','১','২','৩','৪','৫','৬','৭','৮','৯'};
        Character eng_number[]={'0','1','2','3','4','5','6','7','8','9'};
        String stringnum = "";
        char[] character = v.toCharArray();
        for(int i=0; i<character.length; i++){
            Character c =  ' ';
            for(int j=0; j<eng_number.length; j++){
                if(character[i] == eng_number[j]){
                    c = bangla_number[j];
                    break;
                }else{
                    c =  character[i];
                }
            }
            stringnum = stringnum+c;
        }
        return stringnum;
    }

    public int leapYear(int year){
        int leapYearOrNot = 0;
        if ((year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0))) {
            leapYearOrNot = 1;
        }
        return leapYearOrNot;
    }

    public void dateCalculation(int day, int month, int year){
        int y = 0, m = 0, d = 0;
        int  currDay = 0, currMonth = 0, currYear = 0;
        int message1 = 0, message2 = 0;
        String monthName = "";

        if (month > 0 && month < 13) {
            currMonth = 1;
            if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                if (day > 0 && day < 32) {
                    currDay = 1;
                }
            }
            else if (month == 4 || month == 6 || month == 9 || month == 11) {
                if (day > 0 && day < 31) {
                    currDay = 1;
                }
            }
            else if (month == 2) {
                int n = leapYear(year);
                if (n == 1) {
                    if (day > 0 && day < 30) {
                        currDay = 1;
                    }
                }
                else if (n == 0) {
                    if (day > 0 && day < 29) {
                        currDay = 1;
                    }
                }
            }
        }

        if (currDay == 0 || currMonth == 0) {
            message1 = 1;
        }


        //year calculation...
        if (year >= 593) {
            if (month < 5) {
                if(month == 4 && day >  13){
                    y = year - 593;
                }else{
                    y = year - 594;
                }
            }
            else if (month > 4 && month < 13) {
                y = year - 593;
            }
            currYear = 1;
        }
        else {
            message2 = 1;
        }
        //Error message display...

        if(message1 == 1){
            msg1.setVisibility(View.VISIBLE);
            msg1.setText("Enter Correct Date..");
        }
        if(message2 == 1 && message1 == 1){
            msg2.setVisibility(View.VISIBLE);
            msg2.setText("Bangla year not start in english "+ String.valueOf(year)+"s");
        }else if(message1  ==  0 && message2 == 1){
            msg1.setVisibility(View.VISIBLE);
            msg1.setText("Bangla year not start in english "+ String.valueOf(year)+"s");
        }

        //month calculation and  day calculation....
        if (currDay == 1 && currMonth == 1 && currYear == 1) {
            if (month == 1) {
                if (day <= 14) {
                    monthName = "পৌষ";
                    m = 9;
                    d = day + 14 + 2;
                }
                else {
                    monthName = "মাঘ";
                    m = 10;
                    d = day - 14;
                }
            }
            else if (month == 2) {
                if (day <= 13) {
                    monthName = "মাঘ";
                    m = 10;
                    d = day + 13 + 4;
                }
                else {
                    monthName = "ফাল্গুন";
                    m = 11;
                    d = day - 13;
                }
            }
            else if (month == 3) {
                if (day <= 14) {
                    monthName = "ফাল্গুন";
                    m = 11;
                    d = day + 14 + 2;
                }
                else {
                    monthName = "চৈত্র";
                    m = 12;
                    d = day - 14;
                }
            }
            else if (month == 4) {
                if (day <= 13) {
                    monthName = "চৈত্র";
                    m = 12;
                    d = day + 13 + 4;
                }
                else {
                    monthName = "বৈশাখ";
                    m = 1;
                    d = day - 13;
                }
            }
            else if (month == 5) {
                if (day <= 14) {
                    monthName = "বৈশাখ";
                    m = 1;
                    d = day + 13 + 4;
                }
                else {
                    monthName = "জ্যৈষ্ঠ";
                    m = 2;
                    d = day - 14;
                }
            }
            else if (month == 6) {
                if (day <= 14) {
                    monthName = "জ্যৈষ্ঠ";
                    m = 2;
                    d = day + 13 + 4;
                }
                else {
                    monthName = "আষাঢ়";
                    m = 3;
                    d = day - 14;
                }
            }
            else if (month == 7) {
                if (day <= 15) {
                    monthName = "আষাঢ়";
                    m = 3;
                    d = day + 13 + 3;
                }
                else {
                    monthName = "শ্রাবন";
                    m = 4;
                    d = day - 15;
                }
            }
            else if (month == 8) {
                if (day <= 15) {
                    monthName = "শ্রাবন";
                    m = 4;
                    d = day + 13 + 3;
                }
                else {
                    monthName = "ভাদ্র";
                    m = 5;
                    d = day - 15;
                }
            }
            else if (month == 9) {
                if (day <= 15) {
                    monthName = "ভাদ্র";
                    m = 5;
                    d = day + 13 + 3;
                }
                else {
                    monthName = "আশ্বিন";
                    m = 6;
                    d = day - 15;
                }
            }
            else if (month == 10) {
                if (day <= 16) {
                    monthName = "আশ্বিন";
                    m = 6;
                    d = day + 13 + 2;
                }
                else {
                    monthName = "কার্তিক";
                    m = 7;
                    d = day - 16;
                }
            }
            else if (month == 11) {
                if (day <= 15) {
                    monthName = "কার্তিক";
                    m = 7;
                    d = day + 13 + 2;
                }
                else {
                    monthName = "অগ্রহায়ণ";
                    m = 8;
                    d = day - 15;
                }
            }
            else if (month == 12) {
                if (day <= 15) {
                    monthName = "অগ্রহায়ণ";
                    m = 8;
                    d = day + 13 + 2;
                }
                else {
                    monthName = "পৌষ";
                    m = 9;
                    d = day - 15;
                }
            }
            String ddd = stringValue(d);
            String yyy = stringValue(y);

            String publishDate = ddd +" "+monthName+" "+ yyy;
            msg1.setVisibility(View.VISIBLE);
            msg1.setText(publishDate);
        }

    }

    public void show(View view){
        EditText day = (EditText) findViewById(R.id.day);
        EditText year = (EditText) findViewById(R.id.year);

        // textview setvisible invisible
        msg1.setVisibility(View.INVISIBLE);
        msg2.setVisibility(View.INVISIBLE);

        try {
            Integer dd = Integer.parseInt(day.getText().toString());
            Integer mm = monthValue;
            Integer yy = Integer.parseInt((year.getText().toString()));

            if(dd != null && mm != null && yy != null){
                dateCalculation(dd, mm, yy);
            }
        }catch (Exception e){
            Toast.makeText(this,"Enter Full Date...", Toast.LENGTH_SHORT).show();
        }

        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getApplicationWindowToken(),0);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_conversion2);
        //initialize message box...
        msg1 = (TextView) findViewById(R.id.mes1);
        msg2 = (TextView) findViewById(R.id.mes2);
        spinner = (Spinner)findViewById(R.id.month);

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
                dateConversion.this.monthValue = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}

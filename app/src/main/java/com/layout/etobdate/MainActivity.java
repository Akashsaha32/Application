package com.layout.etobdate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //listview set
        ListView nameListView = (ListView) findViewById(R.id.nameListView);
        ArrayList<String> name = new ArrayList<String>();
        name.add("English To Bangla Date Conversion");
        name.add("Age Calculation");
        //set adapter
        ArrayAdapter<String> myarr = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, name);
        nameListView.setAdapter(myarr);

        //go to second activity...
        nameListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    Intent intent = new Intent(getApplicationContext(), dateConversion.class);
                    startActivity(intent);
                }
                else if (position == 1){
                    Intent intent = new Intent(getApplicationContext(), DateCalculate.class);
                    startActivity(intent);
                }
            }
        });
    }
}

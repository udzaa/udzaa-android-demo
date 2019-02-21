package com.udzaa.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.udzaa.sdk.Udzaa;
import com.udzaa.sdk.adapter.LocationEventListener;
import com.udzaa.sdk.model.LocationData;

public class MainActivity extends AppCompatActivity {
    Udzaa udzaa = new Udzaa(); //Create Udzaa object
    TextView textViewLat;
    TextView textViewLong;
    EditText edit_s_key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewLat =  (TextView) findViewById(R.id.textViewLat);
        textViewLong =  (TextView) findViewById(R.id.textViewLong);
        edit_s_key = (EditText) findViewById(R.id.editTextSubKey);
        Button startbutton =  (Button) findViewById(R.id.buttonStart);
        Button stopbutton =   (Button) findViewById(R.id.buttonStop);



        startbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Start Udzaa Service in Background, on Start Button Click.
                udzaa.setContext(MainActivity.this)
                        .setSubscribeerKey(edit_s_key.getText().toString())
                        .setAPIKey("Ap45Ioqw9mQlp45EwrPq") //set unique API key
                        .addListener(new LocationEventListener() {
                            @Override
                            public void onLocationUpdate(LocationData locationData) {
                                textViewLat.setText(locationData.getLatitude());
                                textViewLong.setText(locationData.getLongitude());
                            }
                        })
                        .start();
            }
        });
        stopbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Start Udzaa Service in Background, on Start Button Click.
                udzaa.stop();
            }
        });
    }
}

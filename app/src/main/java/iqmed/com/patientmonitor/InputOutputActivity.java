package iqmed.com.patientmonitor;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

public class InputOutputActivity extends AppCompatActivity {
    Button addIntraVenous;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measure_kidney);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        addIntraVenous = (Button) findViewById(R.id.btnAddIV);
        addIntraVenous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newActivity = new Intent(InputOutputActivity.this, AddDataActivity.class);
                startActivity(newActivity);

            }
        });

    }




    }

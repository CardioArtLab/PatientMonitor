package iqmed.com.patientmonitor;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class AddDataActivity extends AppCompatActivity {
    Button addData, back2main_kidney, addDataAuto;
    EditText startTime,stopTime;
    TimePickerDialog mTimePicker, sTimePicker;
    int hour, minute, hourStop, minuteStop;
    Calendar mcurrentTime,settingTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_intravenous);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        startTime = (EditText) findViewById(R.id.startTime);
        startTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                mcurrentTime = Calendar.getInstance();
                hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                minute = mcurrentTime.get(Calendar.MINUTE);

                mTimePicker = new TimePickerDialog(AddDataActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        startTime.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        stopTime =(EditText) findViewById(R.id.stopTime);
        stopTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                settingTime = Calendar.getInstance();
                hourStop = settingTime.get(Calendar.HOUR_OF_DAY);
                minuteStop = settingTime.get(Calendar.MINUTE);

                sTimePicker = new TimePickerDialog(AddDataActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        stopTime.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hourStop, minuteStop, true);//Yes 24 hour time
                sTimePicker.setTitle("Select Time");
                sTimePicker.show();

            }
        });

        addDataAuto = (Button) findViewById(R.id.btnAuto);
        addDataAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        addData = (Button) findViewById(R.id.btnAdd);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent newActivity = new Intent(AddDataActivity.this, InputOutputActivity.class);
                //startActivity(newActivity);
            }
        });

        back2main_kidney = (Button) findViewById(R.id.btnback);
        back2main_kidney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newActivity = new Intent(AddDataActivity.this, InputOutputActivity.class);
                startActivity(newActivity);
            }
        });

    }
}

package iqmed.com.patientmonitor;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MonitorActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button vital_sign_btn,Input_output_btn;
    String[] Lead={"Lead I","Lead II","Lead III","aVR","aVL","aVF"};
    TextView currentDate,timestamp;
    Calendar calander;
    SimpleDateFormat simpledateformat, timeStamp;
    Spinner ecg_lead;
    public String Date, Time;
    public GraphView ecg,spo2;
    public ArrayAdapter ecglead;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //Graph display
        ecg = (GraphView) findViewById(R.id.graphEcg);
        spo2 = (GraphView) findViewById(R.id.graphSpO2);
        ecg_lead =(Spinner) findViewById(R.id.spinner);
        ecg_lead.setOnItemSelectedListener(this);
        ecglead = new ArrayAdapter(this,android.R.layout.simple_spinner_item,Lead);
        ecglead.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ecg_lead.setAdapter(ecglead);

        currentDate = (TextView) findViewById(R.id.currentdate);
        timestamp = (TextView) findViewById(R.id.timestamp);
        calander = Calendar.getInstance();
        simpledateformat = new SimpleDateFormat("dd-MM-yyyy");
        timeStamp =new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
        Time = simpledateformat.format(calander.getTime());
        Date = simpledateformat.format(calander.getTime());
        currentDate.setText(Date);
        timestamp.setText(Time);



        vital_sign_btn = (Button) findViewById(R.id.vitalbutton);
        vital_sign_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newActivity = new Intent(MonitorActivity.this, RecordVitalActivity.class);
                startActivity(newActivity);

            }
        });

        Input_output_btn = (Button) findViewById(R.id.inputoutputbutton);
        Input_output_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newActivity = new Intent(MonitorActivity.this, InputOutputActivity.class);
                startActivity(newActivity);

            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getApplicationContext(), Lead[i], Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

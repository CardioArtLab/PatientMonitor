package iqmed.com.patientmonitor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import iqmed.com.patientmonitor.interfaces.MainNavigationFragmentListen;

public class MonitorFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    Button vital_sign_btn,Input_output_btn;
    String[] Lead={"Lead I","Lead II","Lead III","aVR","aVL","aVF"};
    TextView currentDate,timestamp;
    Calendar calander;
    SimpleDateFormat simpledateformat, timeStamp;
    Spinner ecg_lead;
    public String Date, Time;
    public GraphView ecg,spo2;
    public ArrayAdapter ecglead;

    int resId;
    MainNavigationFragmentListen gotoFragment;

    public static MonitorFragment newInstance(int resId) {
        Bundle bundle = new Bundle();
        bundle.putInt("resId", resId);
        MonitorFragment fragment = new MonitorFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.activity_monitor, container, false);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        this.resId = getArguments().getInt("resId", 0);
        try {
            gotoFragment = (MainNavigationFragmentListen) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement gotoFragmentListen");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        View view = getView();
        //Graph display
        ecg = (GraphView) view.findViewById(R.id.graphEcg);
        spo2 = (GraphView) view.findViewById(R.id.graphSpO2);

        ecg_lead = (Spinner) view.findViewById(R.id.spinner);
        ecg_lead.setOnItemSelectedListener(this);
        ecglead = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, Lead);
        ecglead.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ecg_lead.setAdapter(ecglead);

        currentDate = (TextView) view.findViewById(R.id.currentdate);
        timestamp = (TextView) view.findViewById(R.id.timestamp);
        calander = Calendar.getInstance();
        simpledateformat = new SimpleDateFormat("dd-MM-yyyy");
        timeStamp = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
        Time = simpledateformat.format(calander.getTime());
        Date = simpledateformat.format(calander.getTime());
        currentDate.setText(Date);
        timestamp.setText(Time);


        vital_sign_btn = (Button) view.findViewById(R.id.vitalbutton);
        vital_sign_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //gotoFragment.gotoFragmentByMenuId(R.id.nav_monitor);
                //Intent newActivity = new Intent(MonitorFragment.this, RecordVitalActivity.class);
                //startActivity(newActivity);

            }
        });

        Input_output_btn = (Button) view.findViewById(R.id.inputoutputbutton);
        Input_output_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoFragment.gotoFragmentByMenuId(R.id.nav_kidney);
                //Intent newActivity = new Intent(MonitorFragment.this, InputOutputActivity.class);
                //startActivity(newActivity);

            }
        });

        // Init Application Toolbar
        Toolbar toolbar = getView().findViewById(R.id.toolbar);
        if (toolbar != null) {
            gotoFragment.initToolbar(toolbar);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getActivity().getApplicationContext(), Lead[i], Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

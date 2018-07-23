package iqmed.com.patientmonitor;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

import iqmed.com.patientmonitor.interfaces.MainNavigationFragmentListen;

public class AddDataFragment extends Fragment {
    Button addData, back2main_kidney, addDataAuto;
    EditText startTime,stopTime;
    TimePickerDialog mTimePicker, sTimePicker;
    int hour, minute, hourStop, minuteStop;
    Calendar mcurrentTime,settingTime;

    int resId;
    MainNavigationFragmentListen gotoFragment;

    public static AddDataFragment newInstance(int resId) {
        Bundle bundle = new Bundle();
        bundle.putInt("resId", resId);
        AddDataFragment fragment = new AddDataFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.activity_add_intravenous, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        View view = getView();

        startTime = (EditText) view.findViewById(R.id.startTime);
        startTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                mcurrentTime = Calendar.getInstance();
                hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                minute = mcurrentTime.get(Calendar.MINUTE);

                mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        startTime.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        stopTime =(EditText) view.findViewById(R.id.stopTime);
        stopTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                settingTime = Calendar.getInstance();
                hourStop = settingTime.get(Calendar.HOUR_OF_DAY);
                minuteStop = settingTime.get(Calendar.MINUTE);

                sTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        stopTime.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hourStop, minuteStop, true);//Yes 24 hour time
                sTimePicker.setTitle("Select Time");
                sTimePicker.show();

            }
        });

        addDataAuto = (Button) view.findViewById(R.id.btnAuto);
        addDataAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        addData = (Button) view.findViewById(R.id.btnAdd);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoFragment.gotoFragmentByMenuId(R.id.nav_kidney);
                //Intent newActivity = new Intent(AddDataActivity.this, InputOutputActivity.class);
                //startActivity(newActivity);
            }
        });

        back2main_kidney = (Button) view.findViewById(R.id.btnback);
        back2main_kidney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoFragment.gotoFragmentByMenuId(R.id.nav_kidney);
                //Intent newActivity = new Intent(AddDataActivity.this, InputOutputActivity.class);
                //startActivity(newActivity);
            }
        });

        // Init Application Toolbar
        Toolbar toolbar = getView().findViewById(R.id.toolbar);
        if (toolbar != null) {
            gotoFragment.initToolbar(toolbar);
        }
    }
}




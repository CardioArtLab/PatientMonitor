package iqmed.com.patientmonitor;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import iqmed.com.patientmonitor.interfaces.MainNavigationFragmentListen;


public class ProfileFragment extends Fragment {

    Button search_btn,Accept_btn;
    private Switch ecgset_btn,urine_btn;
    EditText hospital_number;

    //Bluetooth setting
    int REQUEST_ENABLE_BT = 1;
    CharSequence[] device = {"EKG_1","EKG_2","EKG_3"};
    CharSequence[] type_of_urine ={"Urine Bag","Urinal"};
    String connected_device, type_urination;

    int resId;
    MainNavigationFragmentListen gotoFragment;

    public static ProfileFragment newInstance(int resId) {
        Bundle bundle = new Bundle();
        bundle.putInt("resId", resId);
        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.activity_profile, container, false);
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

        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);

        }
        hospital_number = (EditText) view.findViewById(R.id.txtHospitalNum);

        Accept_btn = (Button) view.findViewById(R.id.btn_accept);
        Accept_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoFragment.gotoFragmentByMenuId(R.id.nav_monitor);
            }
        });

        search_btn = (Button) view.findViewById(R.id.btn_search);
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent newActivity = new Intent(ProfileActivity.this, MonitorActivity.class);
                //startActivity(newActivity);

            }
        });

        ecgset_btn = (Switch) view.findViewById(R.id.EkgSet);
        ecgset_btn.setChecked(false);
        //attach a listener to check for changes in state
        ecgset_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Choose a device");
                    // add a radio button list
                    builder.setSingleChoiceItems(device, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int item) {
                            // user checked an item
                            connected_device = String.valueOf(device[item]);
                        }
                    });
                    // add OK and Cancel buttons
                    builder.setPositiveButton("DONE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // user clicked OK
                            Toast.makeText(getActivity().getBaseContext(),
                                    "You Choose : " + connected_device,
                                    Toast.LENGTH_LONG).show();

                        }
                    });
                    builder.setNegativeButton("SCAN", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {

                        }
                    });

                    // create and show the alert dialog
                    AlertDialog dialog = builder.create();
                    dialog.show();

                }

            }
        });

        urine_btn = (Switch) view.findViewById(R.id.UrineSet);
        urine_btn.setChecked(false);
        //attach a listener to check for changes in state
        urine_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Choose a type of measurement");
                    // add a radio button list
                    builder.setSingleChoiceItems(type_of_urine, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int item) {
                            // user checked an item
                            type_urination = String.valueOf(type_of_urine[item]);
                        }
                    });
                    // add OK and Cancel buttons
                    builder.setPositiveButton("DONE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // user clicked OK
                            Toast.makeText(getActivity().getApplicationContext(),
                                    "You Choose : " + type_urination,
                                    Toast.LENGTH_LONG).show();

                        }
                    });
                    builder.setNegativeButton("SCAN", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {

                        }
                    });
                    // create and show the alert dialog
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });

        // Init Application Toolbar
        Toolbar toolbar = getView().findViewById(R.id.toolbar);
        if (toolbar != null) {
            gotoFragment.initToolbar(toolbar);
        }
    }
}


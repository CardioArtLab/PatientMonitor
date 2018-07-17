package iqmed.com.patientmonitor;

import android.app.AlertDialog;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.Switch;

import com.jraska.console.Console;

import java.util.ArrayList;
import java.util.List;

import me.aflak.bluetooth.Bluetooth;


public class DemoBluetoothFragment extends Fragment {

    private static final String ARG_RESSOURCE_ID = "resId";

    private int resId;
    private Switch rpiSwitch;
    private Switch nibpSwitch;
    private String rpiAddress;
    private String nibpAddress;

    public DemoBluetoothFragment() {
        // Required empty public constructor
    }

    public static DemoBluetoothFragment newInstance(int resId) {
        DemoBluetoothFragment fragment = new DemoBluetoothFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_RESSOURCE_ID, resId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            resId = getArguments().getInt(ARG_RESSOURCE_ID, 0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_demo_bluetooth, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();
        rpiSwitch = view.findViewById(R.id.switch_rpi);
        nibpSwitch = view.findViewById(R.id.switch_nibp);

        // Set event for rpiSwitch
        rpiSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                }
            }
        });
        nibpSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //TODO: show device dialog
                }
            }
        });
        Console.writeLine("hello world");
    }

    private void getDeviceAddressFromDialog(final DeviceDialogModalListener listener) {
        ArrayList<String> devicesNames = new ArrayList<>();
        String address;
        final List<BluetoothDevice> pairDevices = bluetooth.getPairedDevices();
        for(BluetoothDevice device: pairDevices) {
            devicesNames.add(device.getName());
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose Bluetooth devices");
        builder.setSingleChoiceItems(devicesNames.toArray(new CharSequence[]{}), -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                // user checked an item
                listener.setDeviceAddress(pairDevices.get(item).getAddress());
            }
        });
        builder.setPositiveButton("Select", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.beginConnect();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {}
        });
        builder.create().show();
    }

    @Override
    public void onStart() {
        bluetooth.onStart();
        super.onStart();
    }

    @Override
    public void onStop() {
        bluetooth.onStop();
        super.onStop();
    }

    public interface DeviceDialogModalListener {
        void setDeviceAddress(String address);
        void beginConnect();
    }
}

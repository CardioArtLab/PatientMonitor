package iqmed.com.patientmonitor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import iqmed.com.patientmonitor.interfaces.MainNavigationFragmentListen;

public class InputOutputFragment extends Fragment {

    Button addIntraVenous;

    int resId;
    MainNavigationFragmentListen gotoFragment;

    public static InputOutputFragment newInstance(int resId) {
        Bundle bundle = new Bundle();
        bundle.putInt("resId", resId);
        InputOutputFragment fragment = new InputOutputFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.activity_measure_kidney, container, false);
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

        addIntraVenous = (Button) view.findViewById(R.id.btnAddIV);
        addIntraVenous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoFragment.gotoFragmentByMenuId(R.id.nav_add_kidney);
                //Intent newActivity = new Intent(getActivity(), AddDataFragment.class);
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

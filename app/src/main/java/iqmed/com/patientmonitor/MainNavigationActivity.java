package iqmed.com.patientmonitor;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import iqmed.com.patientmonitor.interfaces.MainNavigationFragmentListen;

public class MainNavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainNavigationFragmentListen {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_navigation);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        gotoFragmentByMenuId(R.id.nav_profile);
    }


    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);

        // init ActionBar with selected fragment
        Bundle bundle = fragment.getArguments();
        switch (bundle.getInt("resId", 0)) {
            case R.id.nav_profile:
                //Toast.makeText(this, "nav_profile", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_logconsole) {
            gotoFragmentByMenuId(id);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        gotoFragmentByMenuId(id);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void gotoFragmentByMenuId(int id) {
        Fragment fragment = null;
        try {
            if (id == R.id.nav_profile) {
                fragment = ProfileFragment.newInstance(id);
            } else if (id == R.id.nav_monitor) {
                // fragment = MonitorFragment.newInstance(id);
            } else if (id == R.id.action_logconsole) {
                fragment = DemoBluetoothFragment.newInstance(id);
            }
            if (fragment != null) {
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.flContent, fragment).commit();
            } else {
                Toast.makeText(this, "Not implement yet", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e("cardioart", e.getMessage());
        }
    }

    @Override
    public void initToolbar(Toolbar toolbar) {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();
        }
    }
}

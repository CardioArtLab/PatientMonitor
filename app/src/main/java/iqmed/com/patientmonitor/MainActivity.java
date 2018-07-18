package iqmed.com.patientmonitor;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jraska.console.timber.ConsoleTree;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {
    Button Sign_in;
    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        username = (EditText) findViewById(R.id.txtName);
        password = (EditText) findViewById(R.id.txtPassword);
        Sign_in = (Button) findViewById(R.id.btn_sign_in);

        Sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Submit()) {
                    Intent newActivity = new Intent(MainActivity.this, MainNavigationActivity.class);
                    startActivity(newActivity);
                }
            }
        });

        // Register global logging library
        Timber.plant(new ConsoleTree());
    }


    public boolean Submit() {
        final AlertDialog.Builder adb = new AlertDialog.Builder(this);
        AlertDialog ad = adb.create();
        if (username.getText().length() == 0) {
            ad.setMessage("Please put username ");
            ad.show();
            return false;
        }

        if (password.getText().length() == 0) {
            ad.setMessage("Please put password ");
            ad.show();
            return false;

            return true;
        }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            // launch settings activity
            startActivity(new Intent(MainActivity.this, PreferenceActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    }
}
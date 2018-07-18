package iqmed.com.patientmonitor;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jraska.console.timber.ConsoleTree;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import timber.log.Timber;


public class MainActivity extends AppCompatActivity {
    Button Sign_in;
    EditText username;
    EditText password;
    private String mUsername;
    private String mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("myParseServ")
                .server("http://n66.info:1337/parse")
                .build()
        );

        username = (EditText) findViewById(R.id.txtName);
        password = (EditText) findViewById(R.id.txtPassword);
        Sign_in = (Button) findViewById(R.id.btn_sign_in);

        Sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Submit()) {
                    ParseUser.logInInBackground(username.getText().toString(), password.getText().toString(), new LogInCallback() {
                        public void done(ParseUser user, ParseException e) {
                            if (user != null) {
                                Log.e("Login process:","Online Login Sucessful");
                                Intent newActivity = new Intent(MainActivity.this, MainNavigationActivity.class);
                                startActivity(newActivity);
                            } else {
                                // Login failed. Look at the ParseException to see what happened.
                                Log.e("Login process:","Online Login Failed"+e);
                                validate(username.getText().toString(),password.getText().toString());
                            }
                        }
                    });
                }

            }
        });

        // Register global logging library
        Timber.plant(new ConsoleTree());
    }


    public boolean Submit()
    {
        final AlertDialog.Builder adb = new AlertDialog.Builder(this);
        AlertDialog ad = adb.create();
        if (username.getText().length()==0){
            ad.setMessage("Please insert username ");
            ad.show();
            return false;
        }

        if (password.getText().length()==0){
            ad.setMessage("Please insert password ");
            ad.show();
            return false;
        }
            return true;
        }
        public boolean validate(String username1,String password1) {
            final AlertDialog.Builder adb = new AlertDialog.Builder(this);
            AlertDialog ad = adb.create();
            if ((username1.equals("admin")) && (password1.equals("1234"))){
                    Intent newActivity = new Intent(MainActivity.this, MainNavigationActivity.class);
                    startActivity(newActivity);
                    return true;
                }

                else if ((username1.equals("user")) && (password1.equals("p"))){
                    Intent newActivity = new Intent(MainActivity.this, MainNavigationActivity.class);
                    startActivity(newActivity);
                    return true;
            }
            else {
                ad.setMessage("Incorrect Username or Password");
                ad.show();
                return false;
            }

    }


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


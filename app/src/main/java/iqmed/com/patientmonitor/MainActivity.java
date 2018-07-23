package iqmed.com.patientmonitor;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import android.widget.Toast;

import com.jraska.console.timber.ConsoleTree;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;



import timber.log.Timber;

public class MainActivity extends AppCompatActivity {
    Button Sign_in;
    EditText username, password;
    String Username, Password;
    ParseUser user, currentUser;
    ConnectivityManager connected;
    NetworkInfo network;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //parse fixed user setup
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("myParseServ")
                .server("http://n66.info:1337/parse/")
                .build()
        );

        username = (EditText) findViewById(R.id.txtName);
        password = (EditText) findViewById(R.id.txtPassword);


        Sign_in = (Button) findViewById(R.id.btn_sign_in);
        Sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(Submit()) {

                if(Submit()) {
                    if(validate(username.getText().toString(),password.getText().toString())){
                        Intent newActivity = new Intent(MainActivity.this, MainNavigationActivity.class);
                        startActivity(newActivity);
                    }
                    else {

                    }
                }
                /*if(ConnectionOnline()) {
                  // Log in
                  ParseUser.logInInBackground("admin", "1234",
                          new LogInCallback() {
                              public void done(ParseUser user, ParseException e) {
                                  if (user != null) {
                                      // Hooray! The user is logged in.
                                      currentUser = ParseUser.getCurrentUser();
                                      alertDisplayer("Sucessful Login","Welcome" +  currentUser + "!");

                                  } else {
                                      // Signup failed. Look at the ParseException to see what happened.
                                  }
                              }
                          });
              }else
              {
                  if (SignIn()) {
                      Intent newActivity = new Intent(MainActivity.this, MainNavigationActivity.class);
                      startActivity(newActivity);
                  }

              }*/
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
            ad.setMessage("Please put username ");
            ad.show();
            return false;
        }

        if (password.getText().length()==0){
            ad.setMessage("Please put password ");
            ad.show();
            return false;
        }

        return true;
    }


    public boolean SignIn() {

        Username = username.getText().toString();
        Password = password.getText().toString();

        if(Username.equals("admin") && Password.equals("1234")){
            Toast.makeText(this, "You Signed In", Toast.LENGTH_SHORT).show();
            return true;
        }else{
            Toast.makeText(this, "Invalid Login", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    protected boolean ConnectionOnline() {
        connected = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        network = connected.getActiveNetworkInfo();
        if (network != null && network.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    private void alertDisplayer(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        // don't forget to change the line below with the names of your Activities
                        Intent newActivity = new Intent(MainActivity.this, MainNavigationActivity.class);
                        startActivity(newActivity);
                    }
                });
        AlertDialog ok = builder.create();
        ok.show();
    }


    public boolean validate(String username1,String password1) {
        final AlertDialog.Builder adb = new AlertDialog.Builder(this);
        AlertDialog ad = adb.create();
        if ((username1.equals("admin")) && (password1.equals("1234"))){
            showToast("Login Successful! (Super ADMIN)");
            return true;
        }

        else if ((username1.equals("user")) && (password1.equals("p"))){
            showToast("Login Successful! (Super ADMIN)");
            return true;
        }
        else {
            ParseUser.logInInBackground(username.getText().toString(), password.getText().toString(), new LogInCallback() {
                        public void done(ParseUser user, ParseException e) {
                            if (user != null) {
                                Log.e("Login process:", "Online Login Successful");
                                String type = user.getString("type");
                                showToast("Login Successful! (" + type +")");
                                Log.e("userType:", "successful-" + type);
                                Intent newActivity = new Intent(MainActivity.this, MainNavigationActivity.class);
                                startActivity(newActivity);
                            } else {
                                // Login failed. Look at the ParseException to see what happened.
                                Log.e("Login process:", "Online Login Failed" + e);
                                Incorrect();

                            }
                        }
                    }
            );
                /*ParseQuery<ParseUser> query = ParseUser.getQuery();
                query.whereEqualTo("username", username);
                query.whereEqualTo("password", password);
                query.findInBackground(new FindCallback<ParseUser>() {
                    public void done(List<ParseUser> objects, ParseException e) {
                        if (e == null) {
                            Log.e("Retriving:", "Succesful");
                            // The query was successful.
                            int size = objects.size();
                            String type =objects.get(0).getString("type");
                            if(size == 0) {
                                Log.e("Retriving object:", "There are no object that match the credential");
                                }
                                else {
                                showToast("userType: " + type);
                                Log.e("Retriving object:", "successful");
                                }
                            }
                         else {
                            Log.e("Retriving:", "Failed");
                            // Something went wrong.
                        }
                    }
                });*/


            return false;
        }
    }
    public void Incorrect()
    {
        final AlertDialog.Builder adb = new AlertDialog.Builder(this);
        AlertDialog ad = adb.create();
        ad.setMessage("Incorrect Username or Password");
        ad.show();
    }
    private void showToast(String text) {
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
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

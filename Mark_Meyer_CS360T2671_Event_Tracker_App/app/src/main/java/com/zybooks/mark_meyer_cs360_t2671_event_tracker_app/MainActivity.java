package com.zybooks.mark_meyer_cs360_t2671_event_tracker_app;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    /* Define the UI elements */
    private EditText userName;
    private EditText userPassword;
    private EditText verifyPassword;
    private TextView userAttempts;
    private Button   btn_login;
    private Button   btn_create_acct;
    private ImageButton btn_close_main;
    private ImageButton btn_logout;
    private ImageView deleteEvent;


    /* Number of attempts is held in this counter */
    private int counter = 5;

    /* Flag used for validation */
    boolean isValid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        /* Bind the XML views to Java Code Elements */
        userName = findViewById(R.id.login_name);
        userPassword = findViewById(R.id.login_pw);
        verifyPassword = findViewById(R.id.login_verify_pw);
        userAttempts = findViewById(R.id.user_attempts);
        btn_login = findViewById(R.id.button_to_login);
        btn_create_acct = findViewById(R.id.button_to_create_account);
        btn_close_main = findViewById(R.id.close_app_button);
        btn_logout = findViewById(R.id.logout_button);
    }

    @Override
    protected void onStart () {
        super.onStart();
    }


        /**----------BEGIN LOGIN SCREEN LISTENERS & OPERATIONS---------------
        *-------------------------------------------------------------------*/

    @Override
    protected void onResume() {
        super.onResume();

        /* Set listener on Register button */
        btn_create_acct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* Move to activity allowing user to register */
                Intent intent_to_register = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(intent_to_register);
            }
        });

        /* Describe the logic when the login button is clicked */
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /* Obtain user inputs */
                String user_name = userName.getText().toString();
                String user_password = userPassword.getText().toString();
                String verify_password = verifyPassword.getText().toString();
                Intent intent_events_list;

                /* Check if the user inputs are empty */
                if (user_name.isEmpty() || user_password.isEmpty()) {
                    /* Display a message toast to user to enter the details */
                    Toast.makeText(MainActivity.this, "Please enter name and password!", Toast.LENGTH_LONG).show();

                } else {

                    /* Validate the user inputs */
                    isValid = validate(user_name, user_password, verify_password);

                    /* Validate the user inputs */
                    /* If not valid */
                    if (isValid) {
                        intent_events_list = new Intent(MainActivity.this, EventList.class);
                        startActivity(intent_events_list);

                        Toast.makeText(MainActivity.this, "Login success!", Toast.LENGTH_LONG).show();

                    }
                    /* If not valid */
                    else {
                        /* Decrement the counter */
                        counter--;

                        /* Show the attempts remaining */
                        userAttempts.setText("Attempts Remaining: " + counter);

                        /* Disable the login button if there are no attempts left */
                        if (counter == 0) {
                            btn_login.setEnabled(false);
                            Toast.makeText(MainActivity.this, "You have used all your attempts try again later!", Toast.LENGTH_LONG).show();
                        }
                        /* Display error message */
                        else {
                            Toast.makeText(MainActivity.this, "Incorrect credentials, please try again!", Toast.LENGTH_LONG).show();
                        }

                    }

                }
                finish();
            }
        });

        btn_close_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent closing_register = new Intent(getApplicationContext(), MainActivity.class);
                closing_register.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                closing_register.putExtra("EXIT", true);
                startActivity(closing_register);

                finish();
                System.exit(0);
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logout = new Intent(getApplicationContext(), MainActivity.class);
                logout.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                logout.putExtra("EXIT", true);
                startActivity(logout);

                finish();
                System.exit(0);
            }
        });


        // exit app if X button is pressed on another activity
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }
    }

    private boolean validate(String user_name, String user_password, String verify_password){

        if (user_password.equals(verify_password)) {

            if(RegistrationActivity.credentials != null){
                if(user_name.equals(RegistrationActivity.credentials.getUsername()) && user_password.equals(RegistrationActivity.credentials.getPassword())){
                    return true;
                }
            } else {
                /* Display a message toast to user to enter the details */
                Toast.makeText(MainActivity.this, "Please enter name and password!", Toast.LENGTH_LONG).show();
            }
        }
        return false;
    }
    /**----------END LOGIN SCREEN LISTENERS & OPERATIONS------------------
     *-------------------------------------------------------------------*/

}
package com.zybooks.mark_meyer_cs360_t2671_event_tracker_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {

    /* Define the UI elements */
    private EditText et_userName;
    private EditText et_userPassword;
    private EditText et_verifyPassword;
    private Button btn_Register;
    private ImageButton btn_close_Register;

    /* Create an object of the class Credentials */
    protected static UserCredentials credentials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        /* Bind the UI elements with the XML layout elements */
        et_userName = findViewById(R.id.reg_username);
        et_userPassword = findViewById(R.id.reg_pw);
        et_verifyPassword = findViewById(R.id.reg_verify_pw);
        btn_Register = findViewById(R.id.button_to_register);
        btn_close_Register = findViewById((R.id.close_reg_button));

    }

    @Override
    protected void onStart() {
        super.onStart();

        /* A listener for click events on the Register Button */
        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /* Obtain inputs from fields */
                String registeredName = et_userName.getText().toString();
                String registeredPassword = et_userPassword.getText().toString();
                String verifiedPassword = et_verifyPassword.getText().toString();
                boolean validation = validate(registeredName, registeredPassword, verifiedPassword);

                /* Check if fields are valid */
                if (validation) {
                    try {
                        /* Add credentials to database */
                        credentials = new UserCredentials(-1, registeredName, registeredPassword, true);
                        Toast.makeText(RegistrationActivity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();

                        /* Go to Login Activity to allow user to re-enter credentials to Login */
                        Intent intent_to_main_screen = new Intent(RegistrationActivity.this, MainActivity.class);
                        startActivity(intent_to_main_screen);

                        /* Add user to database */
                        Database user_db = new Database(RegistrationActivity.this);
                        boolean success = user_db.addUser(credentials);
                        Toast.makeText(RegistrationActivity.this, "User added: " + success + ", Please login.", Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        Toast.makeText(RegistrationActivity.this, "Error creating user", Toast.LENGTH_SHORT).show();
                        credentials = new UserCredentials(-1, "error", "error", false);
                    }

                } else {
                    return;
                }

                finish();
            }
        });

        btn_close_Register.setOnClickListener(new View.OnClickListener() {
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
    }

    /* Function for validating the input credentials from the user */
    boolean validate (String name, String password, String verify_pass)
    {
        /* Check if the name is empty and password field is 8 characters long */
        if (name.isEmpty() || password.isEmpty() || (password.length() < 6) || !(password.equals(verify_pass))) {
            Toast.makeText(this, "Registration failed, verify the passwords match, and ensure password is more than 5 characters long!",
                    Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
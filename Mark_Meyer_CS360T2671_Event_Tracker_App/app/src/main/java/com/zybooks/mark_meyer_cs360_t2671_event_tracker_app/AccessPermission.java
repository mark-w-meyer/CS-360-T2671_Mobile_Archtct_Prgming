package com.zybooks.mark_meyer_cs360_t2671_event_tracker_app;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.app.Activity;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.telephony.SmsManager;

import android.util.Log;
import android.view.Menu;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

// src: https://www.tutorialspoint.com/android/android_sending_sms.htm
public class AccessPermission extends Activity {
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    public Button   btn_send;
    public Button   btn_cancel;
    public EditText et_phone_no;
    public EditText et_message;
    public EditText nameSave;
    public EditText noteSave;
    public String   phone;
    public String   message;
    public String   saveName;
    public String   saveDate;
    public String   saveTime;
    public String   saveNotes;
    public TextView dateSave;
    public TextView timeSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.access_permission);

        btn_send    = (Button) findViewById(R.id.ok_button);
        btn_cancel = (Button) findViewById(R.id.cancel_button);
        et_phone_no = (EditText) findViewById(R.id.editText);
        et_message  = (EditText) findViewById(R.id.editText2);

        btn_send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendSMSMessage();

                setContentView(R.layout.activity_event_edit);

                nameSave = findViewById(R.id.eventName);
                dateSave = findViewById(R.id.date_view);
                timeSave = findViewById(R.id.time_display);
                noteSave = findViewById(R.id.addEventNotes);

                saveName = nameSave.getText().toString();
                saveDate = dateSave.getText().toString();
                saveTime = timeSave.getText().toString();
                saveNotes = noteSave.getText().toString();

                Intent intent_send_sms = new Intent(getApplicationContext(), EventList.class);
                intent_send_sms.putExtra("savedName", saveName);
                intent_send_sms.putExtra("savedDate", saveDate);
                intent_send_sms.putExtra("savedTime", saveTime);
                intent_send_sms.putExtra("savedNote", saveNotes);

                startActivity(intent_send_sms);

            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Permission Cancelled.",
                        Toast.LENGTH_LONG).show();

                Intent intent_cancel = new Intent(AccessPermission.this, EventList.class);
                startActivity(intent_cancel);
            }
        });
    }

    protected void sendSMSMessage() {
        phone = et_phone_no.getText().toString();
        message = et_message.getText().toString();

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        } else {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phone, null, message, null, null);
            Toast.makeText(getApplicationContext(), "SMS sent.",
                    Toast.LENGTH_LONG).show();
        }
    }
}
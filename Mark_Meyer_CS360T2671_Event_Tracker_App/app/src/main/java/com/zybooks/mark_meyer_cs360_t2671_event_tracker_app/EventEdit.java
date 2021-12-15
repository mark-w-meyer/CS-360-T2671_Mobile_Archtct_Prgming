package com.zybooks.mark_meyer_cs360_t2671_event_tracker_app;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class EventEdit extends AppCompatActivity {

    // Define the variable of CalendarView type
    // and TextView type;
    public CalendarView calendarView;
    public TextView tv_date_view;
    public TextView tv_time_view;
    public TextView tv_submit_edit;
    public EditText et_notes_edit;
    public EditText et_name_edit;
    public EditText tv_date_display;
    public TimePicker tp_timePicker;
    public ImageButton btn_back_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);

        calendarView = (CalendarView) findViewById(R.id.calendarView);
        tv_time_view = findViewById(R.id.time_display);
        et_notes_edit = findViewById(R.id.addEventNotes);
        et_name_edit = findViewById(R.id.eventName);
        tp_timePicker = findViewById(R.id.timePicker);
        tv_date_view = findViewById(R.id.date_view);
        tv_date_display = findViewById(R.id.sched_event_stamp1);
        tv_submit_edit = findViewById(R.id.submit_date);
        btn_back_edit = findViewById(R.id.back_Button);

        tp_timePicker.setIs24HourView(false);
        tp_timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int min) {
                if (min < 10) {
                    String minute = "0" + String.valueOf(min);
                    String timeCapture = hour + ":" + minute;
                    String colorTime = "<font color='#FFFFFF'>" + timeCapture + "</font>";
                    tv_time_view.setText(Html.fromHtml("Time: "+ colorTime, Html.FROM_HTML_MODE_LEGACY ));
                } else {
                    String timeCapture = hour + ":" + min;
                    String colorTime = "<font color='#FFFFFF'>" + timeCapture + "</font>";
                    tv_time_view.setText(Html.fromHtml("Time: "+ colorTime, Html.FROM_HTML_MODE_LEGACY ));
                }


            }
        });

        // calendar function
        // credit: https://www.geeksforgeeks.org/android-creating-a-calendar-view-app/
        // By ID we can use each component, id is assigned in xml file

        // Add Listener to calendar
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            // Get the value of DAYS, MONTH, YEARS
            public void onSelectedDayChange(@NonNull CalendarView view,
                                            int year,
                                            int month,
                                            int dayOfMonth)
            {
                // Store value of date as string
                // Add 1 to month because month because index starts at 0
                String Date =  (month + 1) + "-" + dayOfMonth + "-" + year;

                // set this date in TextView for Display
                tv_date_view.setText(Date);
            }
        });

        tv_submit_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setContentView(R.layout.access_permission);
                Intent intent_to_send_sms = new Intent(EventEdit.this, AccessPermission.class);
                startActivity(intent_to_send_sms);

            }
        });

        btn_back_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_back_to_list = new Intent(EventEdit.this, EventList.class);
                startActivity(intent_back_to_list);
            }
        });

    }
}
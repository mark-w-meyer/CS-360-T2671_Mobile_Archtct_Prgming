package com.zybooks.mark_meyer_cs360_t2671_event_tracker_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class EventList extends AppCompatActivity {

    private EditText    card_edit_position;
    private EditText    card_add_position;
    private ImageButton btn_add_event;
    private ImageButton btn_event_edit;
    private ImageButton btn_delete;

    private EventAdapter eventAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager rvLayoutManager;
    private ArrayList<EventManager> eventList;
    private EventManager event_mgr;

    Database event_db;

    // Format Date and Time for default card values
    SimpleDateFormat sdfDate = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault());
    SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm", Locale.getDefault());

    String currentDate;
    String currentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        event_db = new Database(EventList.this);
        eventList = new ArrayList<>();

        btn_add_event  = findViewById(R.id.add_event_icon);
        btn_delete = findViewById(R.id.delete_button1);
        btn_event_edit = findViewById(R.id.three_dots_button);
        card_edit_position = findViewById(R.id.edittext_remove);
        card_add_position = findViewById(R.id.edittext_insert);

        buildRecyclerView();
        exampleList();
        setButtons();

    }


    @Override
    protected void onStart () {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    public void insertItem(int position) {
        currentDate = sdfDate.format(new Date());
        currentTime = sdfTime.format(new Date());
        int initialSize = eventList.size();

        if ( (position > -1) && (position < eventList.size() + 1) ) {
            eventList.add(position, new EventManager(position, "new event", currentDate, currentTime, "notes here"));
            eventAdapter.notifyItemRangeInserted(initialSize, eventList.size() - 1);

            event_db = new Database(getApplicationContext());
            event_db.addEvent( new EventManager(position, "new event", currentDate, currentTime, "notes here") );
        } else {
            Toast.makeText(EventList.this, "Enter a card position", Toast.LENGTH_SHORT).show();
        }
    }

    public void removeItem(int position) {
        int initialSize = eventList.size();

        if ( (position > -1) && (position < eventList.size()) ) {
            eventList.remove(position);
            eventAdapter.notifyItemRangeRemoved(position, eventList.size() - 1);

            //event_db.deleteEvent(eventList.get(position));
        } else {
            Toast.makeText(EventList.this, "Enter a card position", Toast.LENGTH_SHORT).show();
        }

    }
/*
    public void changeName(int position, String text) {
        eventList.get(position).setE_name(text);
        eventAdapter.notifyItemChanged(position);
    }

    public void changeDate(int position, String text) {
        eventList.get(position).setE_date(text);
        eventAdapter.notifyItemChanged(position);
    }

    public void changeTime(int position, String text) {
        eventList.get(position).setE_time(text);
        eventAdapter.notifyItemChanged(position);
    }

    public void changeNotes(int position, String text) {
        eventList.get(position).setE_notes(text);
        eventAdapter.notifyItemChanged(position);
    }
*/
    public void toEditActivity() {
        Intent intent_edit = new Intent(EventList.this, EventEdit.class);
        startActivity(intent_edit);
    }

    public void exampleList() {
        insertItem(0);
    }

    protected void setButtons() {

        btn_add_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(card_add_position.getText().toString());
                insertItem(position);
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(card_edit_position.getText().toString());
                removeItem(position);
            }
        });

        btn_event_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(card_edit_position.getText().toString());
                toEditActivity();
            }
        });
    }

    public void buildRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        rvLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        eventAdapter = new EventAdapter(this, eventList);

        recyclerView.setLayoutManager(rvLayoutManager);
        recyclerView.setAdapter(eventAdapter);

    }

}
package com.zybooks.mark_meyer_cs360_t2671_event_tracker_app;

/*
* RecyclerView.Adapter
* RecyclerView.ViewHolder
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.ClientInfoStatus;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private final Context ctx;
    private final ArrayList<EventManager> eventList;
    private AdapterView.OnItemClickListener itemlistener;

    Database db;
    EventManager e_mgr;

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        itemlistener = (AdapterView.OnItemClickListener) listener;
    }

    void onItemClick(int position) {

    }

    public class EventViewHolder extends RecyclerView.ViewHolder {

        public EditText et_eventName;
        public EditText et_notes;
        public EditText card_index;
        public TextView tv_dateStamp;
        public TextView tv_timeStamp;
        public ImageView iv_cardImage;
        public ImageButton btn_edit_event;
        public ImageButton btn_delete_event;

        public EventViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            iv_cardImage = itemView.findViewById(R.id.card);
            et_eventName = itemView.findViewById(R.id.event_name_display);
            tv_dateStamp = itemView.findViewById(R.id.sched_event_stamp1);
            tv_timeStamp = itemView.findViewById(R.id.sched_time_stamp1);
            et_notes = itemView.findViewById(R.id.multi_text_box);
            card_index = itemView.findViewById(R.id.card_pos);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }

    public EventAdapter(Context ctx, ArrayList<EventManager> eventList) {
        this.ctx = ctx;
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public EventAdapter.EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardlist_layout, parent, false);
        EventViewHolder evHolder = new EventViewHolder(v, (OnItemClickListener) itemlistener);
        return evHolder;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull EventAdapter.EventViewHolder holder, int position) {
        EventManager event_mgr = eventList.get(position);

        holder.tv_dateStamp.setText(event_mgr.getE_date());
        holder.tv_timeStamp.setText(event_mgr.getE_time());
        holder.et_eventName.setText(event_mgr.getE_name());
        holder.et_notes.setText(event_mgr.getE_notes());

        holder.iv_cardImage.setImageDrawable(ctx.getResources().getDrawable(R.drawable.card_image, null));
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

}

package com.vivianaranha.mapsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

public class BusNotificationsActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    public BusNotificationsAdapter notificationsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_notifications);

        recyclerView = findViewById(R.id.bus_notifications_list);
        notificationsAdapter = new BusNotificationsAdapter();
        recyclerView.setAdapter(notificationsAdapter);

    }
}

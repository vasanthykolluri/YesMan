package com.rock.yesman;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EventsListActivity extends Activity {
	
	private final int REQUEST_CODE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_list);
        
    }
    
    public void CreateEvent(View v) {
		Intent i = new Intent(this, CreateEventActivity.class);
		startActivityForResult(i, REQUEST_CODE);
    }
}

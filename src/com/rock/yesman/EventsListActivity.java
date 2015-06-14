package com.rock.yesman;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
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
    
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_events_list, menu);
		return true;
	}
    
    public void onFriendsListClick(MenuItem mi) {
    	Intent i = new Intent(this, FriendsListActivity.class);
		startActivity(i);
    }
}

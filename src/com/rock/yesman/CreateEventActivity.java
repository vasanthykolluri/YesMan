package com.rock.yesman;

import java.util.Currency;
import java.util.Date;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.parse.Parse;
import com.rock.yesman.fragments.DatePickerFragment;
import com.rock.yesman.fragments.TimePickerFragment;
import com.rock.yesman.models.Event;
import android.util.Log;

public class CreateEventActivity extends FragmentActivity  {

	private EditText etEventId;
	private EditText etEventName;
	private EditText etStartDate;
	private EditText etPlace;
	private EditText etTime;

	private final int RESULT_CODE = 20;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//Parse.enableLocalDatastore(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_event);
		etEventId = (EditText) findViewById(R.id.etEventId);
		etEventName = (EditText) findViewById(R.id.etEventName);
//		etStartDate = (EditText) findViewById(R.id.etStartDate);
		etPlace = (EditText) findViewById(R.id.etPlace);
//		etTime = (EditText) findViewById(R.id.etTime);

	}

	public void showTimePickerDialog(View v) {
	    DialogFragment newFragment = new TimePickerFragment();
	    newFragment.show(getSupportFragmentManager(), "timePicker");
	}
	
	public void showDatePickerDialog(View v) {
	    DialogFragment newFragment = new DatePickerFragment();
	    newFragment.show(getSupportFragmentManager(), "datePicker");
	}
	
	public void SubmitEvent(View v) {

		String eventId = etEventId.getText().toString();
		String eventName = etEventName.getText().toString();
		// Date startDate = (Date) etStartDate.getText();

		Date startDate = new Date(System.currentTimeMillis());

		String place = etPlace.getText().toString();
		
		// Time time = (Time) etTime.getText();
		Time startTime = new Time();
		startTime.setToNow();

		Event newevent = new Event(eventId, eventName, place, startDate, startTime);
		//newevent.put(eventName, newevent);

		Log.d("VK", "Created new event");
		newevent.saveInBackground();
		Log.d("VK","saved event");
		setResult(RESULT_CODE);
		finish();
	}

}

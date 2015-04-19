package com.rock.yesman;

import java.util.Date;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.format.Time;
import android.view.View;
import android.widget.EditText;

import com.parse.Parse;
import com.rock.yesman.fragments.DatePickerFragment;
import com.rock.yesman.fragments.TimePickerFragment;
import com.rock.yesman.models.Event;

public class CreateEventActivity extends FragmentActivity  {

	private EditText etEventId;
	private EditText etEventName;
	private EditText etStartDate;
	private EditText etPlace;
	private EditText etTime;

	private final int RESULT_CODE = 20;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Parse.enableLocalDatastore(this);
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
		Date startDate = (Date) etStartDate.getText();
		String place = etPlace.getText().toString();
		Time time = (Time) etTime.getText();
		Event newevent = new Event(eventId, eventName, startDate, place, time);
		newevent.put(eventName, newevent);
		newevent.saveInBackground();
		setResult(RESULT_CODE);
		finish();
	}

}
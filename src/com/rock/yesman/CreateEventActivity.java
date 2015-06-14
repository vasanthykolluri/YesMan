package com.rock.yesman;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.SaveCallback;
import com.rock.yesman.fragments.DatePickerFragment;
import com.rock.yesman.fragments.TimePickerFragment;
import com.rock.yesman.models.Event;
import com.rock.yesman.networking.MyCustomSender;

public class CreateEventActivity extends FragmentActivity implements
		MyDialogFragmentListener {

	private EditText etEventId;
	private EditText etEventName;
	private EditText etPlace;
	private TextView tvTime;
	private TextView tvDate;

	private String objectId;

	private final int RESULT_CODE = 20;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Parse.enableLocalDatastore(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_event);
		etEventId = (EditText) findViewById(R.id.etEventId);
		etEventName = (EditText) findViewById(R.id.etEventName);
		etPlace = (EditText) findViewById(R.id.etPlace);
		tvTime = (TextView) findViewById(R.id.tvTime);
		tvDate = (TextView) findViewById(R.id.tvDate);

	}

	public void showTimePickerDialog(View v) {
		DialogFragment newFragment = new TimePickerFragment();
		newFragment.show(getSupportFragmentManager(), "timePicker");
	}

	public void showDatePickerDialog(View v) {
		DialogFragment newFragment = new DatePickerFragment();
		newFragment.show(getSupportFragmentManager(), "datePicker");
	}

	public void onReturnTime(String Time) {
		// Log.i("onReturnValue", "Got value " + foo + " back from Dialog!");
		tvTime.setText(Time);
	}

	public void onReturnDate(String Date) {
		// Log.i("onReturnValue", "Got value " + foo + " back from Dialog!");
		tvDate.setText(Date);
	}

	public void submitEvent(View v) {

		String eventId = etEventId.getText().toString();
		String eventName = etEventName.getText().toString();
		// Date startDate = (Date) etStartDate.getText();
		String datepick = (String) tvDate.getText();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date startDate = null;
		try {
			startDate = dateFormat.parse(datepick);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Date d = new Date(System.currentTimeMillis());

		String place = etPlace.getText().toString();

		// Time time = (Time) etTime.getText();
		Time startTime = new Time();
		startTime.setToNow();

		final Event newevent = new Event(eventId, eventName, place, d,
				startTime);
		// newevent.put(eventName, newevent);

		Log.d("VK", "Created new event");
		// newevent.saveInBackground();

		newevent.saveInBackground(new SaveCallback() {
			@Override
			public void done(com.parse.ParseException e) {
				// TODO Auto-generated method stub
				if (e == null) {
					Log.d("OBJECT ID RETRIEVED", "The object id is: "
							+ newevent.getObjectId());
					objectId = newevent.getObjectId();
				} else {
					// The save failed.
					Log.d("No Obj ID", "User update error: " + e);
				}

			}

		});

		Log.d("VK", "saved event");
		setResult(RESULT_CODE);

		Log.d("VK", "Sending EVENT_ADD_REQ to friend");
		MyCustomSender.sendEventAddReq("anirudh", "Anirudh", "anirudh",
				"Anirudh", eventId, eventName, objectId);


		finish();
	}
}

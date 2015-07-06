package com.rock.yesman;

import java.util.Arrays;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.rock.yesman.models.Event;
import com.rock.yesman.models.EventAddReq;
import com.rock.yesman.networking.MyCustomSender;

public class HandleEventAddReqActivity extends Activity implements
		OnClickListener {

	Button accept;
	Button decline;

	boolean click = true;

	private EventAddReq eventAddReq;
	private TextView tvEventAddReq;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.popup_eventaddreq);
		tvEventAddReq = (TextView) findViewById(R.id.tvGroupAddReq);

		eventAddReq = (EventAddReq) getIntent().getSerializableExtra(
				"eventAddReq");
		String message = eventAddReq.getSenderName()
				+ " wants to add you to event " + eventAddReq.getEventName();

		setTitle("YesMan!");
		tvEventAddReq.setText(message);

		accept = (Button) findViewById(R.id.btnAccept);
		accept.setOnClickListener(this);
		decline = (Button) findViewById(R.id.btnDecline);
		decline.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.btnAccept) {
			MyCustomSender.sendEventAddReqResp(eventAddReq, true);
			ParseQuery<Event> query = ParseQuery.getQuery("Event");
			query.getInBackground(eventAddReq.getObjectId(),
					new GetCallback<Event>() {

						@Override
						public void done(Event event, ParseException e) {

							// TODO Auto-generated method stub
							if (e == null) {
								event.add("acceptedUsers", Arrays
										.asList(eventAddReq.getReceiverId()));
								event.saveInBackground();

							}

							else {
								String place = "FAIL";
								Log.d("EVENT OBJ PLACE RETRIEVE",
										"FAIL RETRIEVAL");
							}

						}

					});
		}

		// Add user to group in Parse db
		// YesManApp.getParseClient().addUserGroup(eventAddReq.getReceiverId(),
		// eventAddReq.getEventId());
		else if (v.getId() == R.id.btnDecline) {
			MyCustomSender.sendEventAddReqResp(eventAddReq, false);
			ParseQuery<Event> query = ParseQuery.getQuery("Event");
			query.getInBackground(eventAddReq.getObjectId(),
					new GetCallback<Event>() {

						@Override
						public void done(Event event, ParseException e) {

							// TODO Auto-generated method stub
							if (e == null) {
								event.add("declinedUsers", Arrays
										.asList(eventAddReq.getReceiverId()));
								event.saveInBackground();
							}

							else {
								String place = "FAIL";
								Log.d("EVENT OBJ PLACE RETRIEVE",
										"FAIL RETRIEVAL");
							}

						}

					});

		}

		finish();
	}
}
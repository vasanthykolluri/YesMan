package com.rock.yesman;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ShowEventAddReqRespActivity extends Activity implements OnClickListener {

	TextView tvTrackReqResponse;
	Button btnOK;
	String friendName;
	String eventName;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		Boolean response = getIntent().getBooleanExtra("response", false);
		friendName = getIntent().getStringExtra("friendName");
		eventName = getIntent().getStringExtra("eventName");

		setContentView(R.layout.popup_eventaddreqresp);
		tvTrackReqResponse = (TextView) findViewById(R.id.tvTrackReqResp);
		btnOK = (Button) findViewById(R.id.btnOK);
		if (response == true) {
			tvTrackReqResponse.setText("Yayy!!!" + friendName + " wants to join event " + eventName);
		} else {
			tvTrackReqResponse.setText("Oops!!!" + friendName + " doesn't want to join event " + eventName);
		}
	}

	@Override
	public void onClick(View v) {
		finish();
	}

}
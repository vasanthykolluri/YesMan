package com.rock.yesman.networking;

import org.json.JSONException;
import org.json.JSONObject;

import com.rock.yesman.YesManApp;
import com.rock.yesman.HandleEventAddReqActivity;
import com.rock.yesman.ShowEventAddReqResp;
import com.rock.yesman.models.EventAddReq;
import com.rock.yesman.models.EventAddReqResp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyCustomReceiver extends BroadcastReceiver {
	private static final String TAG = "MyCustomReceiver";

	public static final String intentActionEventAddReq = "EVENT_ADD_REQ";
	public static final String intentActionEventAddReqResp = "EVENT_ADD_REQ_RESP";

	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context.getApplicationContext(),
				"MyCustomReceiver - onReceive", Toast.LENGTH_LONG).show();
		try {
			if (intent == null) {
				Log.d(TAG, "Receiver intent null");
			} else {
				String action = intent.getAction();
				Log.d(TAG, "got action " + action);
				if (action.equals(intentActionEventAddReq)) {
					String channel = intent.getExtras().getString(
							"com.parse.Channel");
					// Filter on user's channel
					if (channel.equals(MyUtils.getChannelName(YesManApp.userName))) {
						JSONObject json = new JSONObject(intent.getExtras()
								.getString("com.parse.Data"));

						Log.d(TAG, "got action " + action + " on channel "
								+ channel);
						EventAddReq eventAddReq = EventAddReq.fromJson(json
								.getJSONObject("eventAddReq"));

						// Handle push notification by invoking activity
						// directly
						Intent pupInt = new Intent(context,
								HandleEventAddReqActivity.class);
						pupInt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						pupInt.putExtra("eventAddReq", eventAddReq);
						context.getApplicationContext().startActivity(pupInt);

						// Handle push notification by sending a local
						// broadcast
						// to which the activity
						// subscribes to
						// LocalBroadcastManager.getInstance(context)
						// .sendBroadcast(
						// new Intent(intentActionTrackReq));
					}
				} else if (action.equals(intentActionEventAddReqResp)) {
					String channel = intent.getExtras().getString(
							"com.parse.Channel");
					JSONObject json = new JSONObject(intent.getExtras()
							.getString("com.parse.Data"));

					Log.d(TAG, "got action " + action + " on channel "
							+ channel);
					// Filter on user's channel
					if (channel.equals(MyUtils.getChannelName(YesManApp
							.userName))) {

						EventAddReqResp eventAddReqResp = EventAddReqResp
								.fromJson(json.getJSONObject("eventAddReqResp"));

						// Handle push notification by invoking activity
						// directly

//						if (acceptFlag == true) {
//							// Add buddy to db
//							FlickrClientApp.getRestClient().addBuddy(
//									buddyLocation.getName(),
//									buddyLocation.getImgUrl(),
//									buddyLocation.getCity());
//						}

						Intent pupRespInt = new Intent(context,
								ShowEventAddReqResp.class);
						pupRespInt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						pupRespInt.putExtra("response",
								eventAddReqResp.getResponse());
						pupRespInt.putExtra("friendName",
								eventAddReqResp.getSenderName());
						pupRespInt.putExtra("eventName",
								eventAddReqResp.getEventName());
						context.getApplicationContext().startActivity(
								pupRespInt);
					}
				}
			}
		} catch (JSONException e) {
			Log.d(TAG, "JSONException: " + e.getMessage());
		}
	}
}
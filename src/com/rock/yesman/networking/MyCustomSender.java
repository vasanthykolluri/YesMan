package com.rock.yesman.networking;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.rock.yesman.models.EventAddReq;
import com.rock.yesman.models.EventAddReqResp;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.SendCallback;

public class MyCustomSender {
	private static final String TAG = "MyCustomSender";

	public static void sendEventAddReq(String senderId, String senderName,
			String receiverId, String receiverName, String eventId,
			String eventName) {

		JSONObject obj;
		try {
			obj = new JSONObject();
			obj.put("alert", "Hello " + receiverName + "," + senderName
					+ " here...");
			obj.put("action", MyCustomReceiver.intentActionEventAddReq);
			EventAddReq eventAddReq = new EventAddReq(senderId, senderName,
					receiverId, receiverName, eventId, eventName);
			obj.put("eventAddReq", EventAddReq.toJson(eventAddReq));

			ParsePush push = new ParsePush();
			ParseQuery query = ParseInstallation.getQuery();

			// Push the notification to Android users
			query.whereEqualTo("deviceType", "android");
			push.setQuery(query);
			// Push the notification to a specific user's channel
			push.setChannel(MyUtils.getChannelName(receiverId));
			push.setData(obj);
			push.sendInBackground(new SendCallback() {

				@Override
				public void done(ParseException arg0) {
					// Toast.makeText(C(),
					// "Sent GROUP_ADD_REQ", Toast.LENGTH_LONG).show();
				}

			});
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public static void sendEventAddReqResp(EventAddReq eventAddReq,
			boolean response) {

		Log.d(TAG,
				eventAddReq.getSenderId() + " " + eventAddReq.getSenderName());
		Log.d(TAG,
				eventAddReq.getReceiverId() + " "
						+ eventAddReq.getReceiverName());

		JSONObject obj;
		try {
			obj = new JSONObject();
			obj.put("action", MyCustomReceiver.intentActionEventAddReqResp);
			EventAddReqResp eventAddReqResp = new EventAddReqResp(
					eventAddReq.getReceiverId(), eventAddReq.getReceiverName(),
					eventAddReq.getSenderId(), eventAddReq.getSenderName(),
					eventAddReq.getEventId(), eventAddReq.getEventName(),
					response);
			obj.put("alert",
					"FlickIt! Response from " + eventAddReqResp.getSenderName());
			obj.put("eventAddReqResp", EventAddReqResp.toJson(eventAddReqResp));

			Log.d(TAG, "Resp Sender" + eventAddReqResp.getSenderId() + " "
					+ eventAddReqResp.getSenderName());
			Log.d(TAG, "Resp rcvr" + eventAddReqResp.getReceiverId() + " "
					+ eventAddReqResp.getReceiverName());

			ParsePush push = new ParsePush();
			ParseQuery query = ParseInstallation.getQuery();
			query.whereEqualTo("deviceType", "android");
			push.setQuery(query);
			// Send response on sender's channel
			push.setChannel(MyUtils.getChannelName(eventAddReqResp
					.getReceiverId()));
			push.setData(obj);
			push.sendInBackground(new SendCallback() {

				@Override
				public void done(ParseException arg0) {
					// Toast.makeText(getApplicationContext(),
					// "Done with sending", Toast.LENGTH_LONG).show();
				}
			});
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
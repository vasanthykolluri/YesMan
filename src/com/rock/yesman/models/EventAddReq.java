package com.rock.yesman.models;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class EventAddReq implements Serializable {

	private static final long serialVersionUID = 9157292014655616918L;

	private String senderId;
	private String senderName;
	private String receiverId;
	private String receiverName;
	private String eventId;
	private String eventName;
	private String objectId;

	public EventAddReq() {
		// TODO Auto-generated constructor stub
	}

	public EventAddReq(String senderId, String senderName, String receiverId,
			String receiverName, String eventId, String eventName,
			String objectId) {
		this.senderId = senderId;
		this.senderName = senderName;
		this.receiverId = receiverId;
		this.receiverName = receiverName;
		this.eventId = eventId;
		this.eventName = eventName;
		this.objectId = objectId;
	}

	public String getSenderId() {
		return senderId;
	}

	public String getSenderName() {
		return senderName;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public String getEventId() {
		return eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public String getObjectId() {
		return objectId;
	}

	public static EventAddReq fromJson(JSONObject jsonObject) {
		EventAddReq eventAddReq = new EventAddReq();

		try {
			eventAddReq.senderId = jsonObject.getString("senderId");
			eventAddReq.senderName = jsonObject.getString("senderName");
			eventAddReq.receiverId = jsonObject.getString("receiverId");
			eventAddReq.receiverName = jsonObject.getString("receiverName");
			eventAddReq.eventId = jsonObject.getString("eventId");
			eventAddReq.eventName = jsonObject.getString("eventName");
			eventAddReq.objectId = jsonObject.getString("objectId");
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}

		return eventAddReq;
	}

	public static JSONObject toJson(EventAddReq eventAddReq) {
		JSONObject jsonObject = new JSONObject();

		try {
			jsonObject.put("senderId", eventAddReq.senderId);
			jsonObject.put("senderName", eventAddReq.senderName);
			jsonObject.put("receiverId", eventAddReq.receiverId);
			jsonObject.put("receiverName", eventAddReq.receiverName);
			jsonObject.put("eventId", eventAddReq.eventId);
			jsonObject.put("eventName", eventAddReq.eventName);
			jsonObject.put("objectId", eventAddReq.objectId);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}

		return jsonObject;
	}
}

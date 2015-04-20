package com.rock.yesman.models;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class EventAddReqResp implements Serializable {

	private static final long serialVersionUID = -4832201350462873804L;

	private String senderId;
	private String senderName;
	private String receiverId;
	private String receiverName;
	private String eventId;
	private String eventName;
	private Boolean response;

	public EventAddReqResp() {
		// TODO Auto-generated constructor stub
	}

	public EventAddReqResp(String senderId, String senderName,
			String receiverId, String receiverName, String eventId,
			String eventName, Boolean response) {
		this.senderId = senderId;
		this.senderName = senderName;
		this.receiverId = receiverId;
		this.receiverName = receiverName;
		this.eventId = eventId;
		this.eventName = eventName;
		this.response = response;
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

	public Boolean getResponse() {
		return response;
	}

	public static EventAddReqResp fromJson(JSONObject jsonObject) {
		EventAddReqResp eventAddReqResp = new EventAddReqResp();

		try {
			eventAddReqResp.senderId = jsonObject.getString("senderId");
			eventAddReqResp.senderName = jsonObject.getString("senderName");
			eventAddReqResp.receiverId = jsonObject.getString("receiverId");
			eventAddReqResp.receiverName = jsonObject.getString("receiverName");
			eventAddReqResp.eventId = jsonObject.getString("eventId");
			eventAddReqResp.eventName = jsonObject.getString("eventName");
			eventAddReqResp.response = jsonObject.getBoolean("response");
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}

		return eventAddReqResp;
	}

	public static JSONObject toJson(EventAddReqResp eventAddRegResp) {
		JSONObject jsonObject = new JSONObject();

		try {
			jsonObject.put("senderId", eventAddRegResp.senderId);
			jsonObject.put("senderName", eventAddRegResp.senderName);
			jsonObject.put("receiverId", eventAddRegResp.receiverId);
			jsonObject.put("receiverName", eventAddRegResp.receiverName);
			jsonObject.put("eventId", eventAddRegResp.eventId);
			jsonObject.put("eventName", eventAddRegResp.eventName);
			jsonObject.put("response", eventAddRegResp.response);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}

		return jsonObject;
	}
}

package com.rock.yesman.models;

import java.util.Date;

import android.text.format.Time;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Event")
public class Event extends ParseObject {
	// subclass should have a public default constructor
	public Event() {
		super();
	}

	public Event(String eventId, String name, Date startDate, String place, Time time) {
		put("eventId", eventId);
		put("name", name);
		put("startDate", startDate);
		put("place", place);
		put("time", time);
	}

	public String getObjectId() {
		return getString("objectId");
	}

	public String getEventId() {
		return getString("eventId");
	}

	public String getName() {
		return getString("name");
	}

	public Date getStartDate() {
		// TODO Auto-generated method stub
		return getDate("startDate");
	}

	public String getPlace() {
		return getString("place");
	}

	public Date getCreatedAt() {
		// TODO Auto-generated method stub
		return getDate("createdAt");
	}

	public Date getUpdatedAt() {
		// TODO Auto-generated method stub
		return getDate("updatedAt");
	}
	
	public String getEventTime() {
		return getString("time");
	}
}

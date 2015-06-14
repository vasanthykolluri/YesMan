package com.rock.yesman.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.rock.yesman.R;
import com.rock.yesman.models.Friend;

public class FriendListAdapter extends ArrayAdapter<Friend> {

	public FriendListAdapter(Context context, List<Friend> friends) {
		super(context, 0, friends);

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Friend friend = getItem(position);
		
		// Find or inflate the template
		View v = null;
		if (convertView == null) {
			LayoutInflater inflator = LayoutInflater.from(getContext());
			v = inflator.inflate(R.layout.friend_item, parent, false);
		} else {
			v = convertView;
		}
		
		TextView tvFirstName = (TextView) v.findViewById(R.id.tvFirstName);
		TextView tvLastName = (TextView) v.findViewById(R.id.tvLastName);
		
		// ToDo: Remove hardcoded values
		tvFirstName.setText(friend.getFirstName());
		tvLastName.setText(friend.getLastName());

		// Return the completed view to render on screen
		return v;
	}
}
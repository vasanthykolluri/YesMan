package com.rock.yesman.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.activeandroid.annotation.Table;
import com.rock.yesman.R;

public class CustomCursorAdapter extends CursorAdapter {

	private LayoutInflater mInflater;

	public CustomCursorAdapter(Context context, Cursor c, int flags) {
		super(context, c, flags);
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		Log.d("VK", "Cursor column names" + cursor.getString(0) + ":" + cursor.getString(1) + ":" + cursor.getString(2) + ":" + cursor.getString(3));
		TextView tvFirstName = (TextView) view.findViewById(R.id.tvFirstName);
		TextView tvLastName = (TextView) view.findViewById(R.id.tvLastName);
		
		// Column#2 - Full Name
		tvFirstName.setText(cursor.getString(2));
		tvLastName.setText(cursor.getString(3));
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		return mInflater.inflate(R.layout.friend_item, parent, false);
	}

}
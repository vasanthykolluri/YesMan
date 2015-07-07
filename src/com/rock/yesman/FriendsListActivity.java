package com.rock.yesman;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.rock.yesman.adapters.CustomCursorAdapter;
import com.rock.yesman.adapters.FriendListAdapter;
import com.rock.yesman.models.Friend;

public class FriendsListActivity extends Activity {

	private FriendListAdapter aFriends;
	private ListView lvFriends;
	private ArrayList<Friend> friendsList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("VK", "FriendsListActivity: On Create");

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friends_list);

		Log.d("VK", "FriendsListActivity: Inflated xml");

		friendsList = new ArrayList<Friend>();
		aFriends = new FriendListAdapter(this, friendsList);

		lvFriends = (ListView) findViewById(R.id.lvFriendsList);
		// lvFriends.setAdapter(aFriends);

		Cursor c = MyPopulateFriendsList();
		Log.d("VK", "FriendsListActivity: Cursor Count:" + c.getCount());

		CustomCursorAdapter cAdapter = new CustomCursorAdapter(
				getApplicationContext(), c, 0);
		lvFriends.setAdapter(cAdapter);

		populateFriendsList();
	}

	private void populateFriendsList() {

		// Clear the adapter
		aFriends.clear();

		// ToDo: Remove hardcoded values
		Friend f1 = new Friend("Prasanthi", "Relangi");
		Friend f2 = new Friend("Jalaja", "Padma");
		Friend f3 = new Friend("Anirudh", "Kasturi");
		aFriends.add(f1);
		aFriends.add(f2);
		aFriends.add(f3);
	}

	private Cursor MyPopulateFriendsList() {
		Log.d("VK", "FriendsListActivity: MyPopulateFriendsList Entry");

		Uri uri = ContactsContract.Contacts.CONTENT_URI;
		Log.d("VK", "FriendsListActivity: MyPopulateFriendsList uri:" + uri);

		String[] projection = new String[] { ContactsContract.Contacts._ID,
				ContactsContract.Contacts.LOOKUP_KEY,
				ContactsContract.Contacts.DISPLAY_NAME,
				ContactsContract.Contacts.DISPLAY_NAME_ALTERNATIVE };

		String selection = ContactsContract.Contacts.HAS_PHONE_NUMBER
				+ " = '1'";
		String[] selectionArgs = null;
		String sortOrder = ContactsContract.Contacts.DISPLAY_NAME
				+ " COLLATE LOCALIZED ASC";
		Log.d("VK",
				"FriendsListActivity: MyPopulateFriendsList - Got the sortorder");

		return getContentResolver().query(uri, projection, selection,
				selectionArgs, sortOrder);
	}
}

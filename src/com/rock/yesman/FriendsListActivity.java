package com.rock.yesman;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.LogOutCallback;
import com.rock.yesman.adapters.FriendListAdapter;
import com.rock.yesman.models.Friend;

public class FriendsListActivity extends Activity {

	private FriendListAdapter aFriends;
	private ListView lvFriends;
	private ArrayList<Friend> friends;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("VK", "FriendsListActivity: On Create");

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friends_list);

		Log.d("VK", "FriendsListActivity: Inflated xml");

		friends = new ArrayList<Friend>();
		aFriends = new FriendListAdapter(this, friends);

		lvFriends = (ListView) findViewById(R.id.lvFriendsList);
		lvFriends.setAdapter(aFriends);

		// ToDo: Remove hardcoded values
		addMyFriends();
		populateFriendsList();
	}

	private void populateFriendsList() {
		//aFriends.clear();

		aFriends.addAll(friends);
	}

	private void addMyFriends() {
		Friend f1 = new Friend("Prasanthi", "Relangi");
		Friend f2 = new Friend("Jalaja", "Padma");
		Friend f3 = new Friend("Anirudh", "Kasturi");
		friends.add(f1);
		friends.add(f2);
		friends.add(f3);
		Toast.makeText(getApplicationContext(), "Added Friends",
				Toast.LENGTH_LONG);
	}
}

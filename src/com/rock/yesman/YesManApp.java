package com.rock.yesman;

import java.sql.Date;

import android.content.Context;
import android.util.Log;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.PushService;
import com.rock.yesman.models.Event;
import com.rock.yesman.networking.MyUtils;

public class YesManApp extends com.activeandroid.app.Application {

	private static Context context;
	ParseInstallation parseInstallation;

	// private static User owner;
	// private static ParseClient parseClient;

	public static final String userName = "anirudh";

	@Override
	public void onCreate() {
		super.onCreate();
		Log.d("VK", "YesManApp: On Create");

		// Get the owner info
		// setAppOwner();
		// parseClient = new ParseClient();

		// Create global configuration and initialize ImageLoader with this
		// configuration
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
				.cacheInMemory().cacheOnDisc().build();
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				getApplicationContext()).defaultDisplayImageOptions(
				defaultOptions).build();
		ImageLoader.getInstance().init(config);

		// Register app with Parse
		Log.d("VK", "YesManApp: Registering Parse");

		registerParse();
		Log.d("VK", "YesManApp: Registered Parse");
	}

	private void registerParse() {

		// Enable Local Datastore.
		Parse.enableLocalDatastore(this);

		// Register your parse models
		ParseObject.registerSubclass(Event.class);

		Log.d("VK", "YesManApp: Registered Event Class");

		// Add your initialization code here
		Parse.initialize(this, "2BvUMyswSQbifaPen8xdrEOnc39Y24TL7SqnXGSS",
				"rq5esmKjCAfwrmApLATvQnNu67w5PFmYcbB4KAla");
		Log.d("VK", "YesManApp: Parse init done");

		// PushService.setDefaultPushCallback(this, EventsListActivity.class);

		setParseInstallation();
		setAppOwner();
		ParsePush.subscribeInBackground(userName);

		parseInstallation.saveInBackground();
	}

	// public static ParseClient getParseClient() {
	// return parseClient;
	// }

	// public static User getAppOwner() {
	// return owner;
	// }

	private void setParseInstallation() {
		this.parseInstallation = ParseInstallation.getCurrentInstallation();
	}

	private ParseInstallation getParseInstallation() {
		return parseInstallation;
	}

	private void setAppOwner() {
		Log.d("VK", "YesManApp: Set App Owner");

		ParseInstallation.getCurrentInstallation().put("userName", userName);
		// Subscribe to receiving on specific channels
		// PushService.subscribe(context,
		// MyUtils.getChannelName(userId),
		// PhotosActivity.class);
	}
}

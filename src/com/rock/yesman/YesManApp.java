package com.rock.yesman;

import android.content.Context;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.parse.Parse;
import com.parse.ParseInstallation;

public class YesManApp  extends com.activeandroid.app.Application {

	private static Context context;
	//private static User owner;
	//private static ParseClient parseClient;

	public static final String userName = "Vasanthy";

	@Override
	public void onCreate() {
		super.onCreate();

		// Get the owner info
//		setAppOwner();
//		parseClient = new ParseClient();

		// Create global configuration and initialize ImageLoader with this
		// configuration
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
				.cacheInMemory().cacheOnDisc().build();
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				getApplicationContext()).defaultDisplayImageOptions(
				defaultOptions).build();
		ImageLoader.getInstance().init(config);

		// Register app with Parse
		registerParse();
		
	}

	private void registerParse() {

		// Enable Local Datastore.
		Parse.enableLocalDatastore(this);
		
		// Register your parse models
		// ParseObject.registerSubclass(Settings.class);

		// Add your initialization code here
		Parse.initialize(this, "2BvUMyswSQbifaPen8xdrEOnc39Y24TL7SqnXGSS",
				"rq5esmKjCAfwrmApLATvQnNu67w5PFmYcbB4KAla");
		ParseInstallation parseInstallation = ParseInstallation
				.getCurrentInstallation();
	}

//	public static ParseClient getParseClient() {
//		return parseClient;
//	}

//	public static User getAppOwner() {
//		return owner;
//	}
}

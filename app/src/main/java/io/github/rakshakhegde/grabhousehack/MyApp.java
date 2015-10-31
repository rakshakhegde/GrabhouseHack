package io.github.rakshakhegde.grabhousehack;

import android.app.Application;

import com.drivemode.android.typeface.TypefaceHelper;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.parse.Parse;
import com.parse.ParseInstallation;

/**
 * Created by Rakshak.R.Hegde on 10/31/2015.
 */
public class MyApp extends Application {
	public static final String FONT_NAME = "museo.ttf";

	@Override
	public void onCreate() {
		super.onCreate();
		// Enable Local Datastore
		Parse.enableLocalDatastore(this);

		Parse.initialize(this, "tNWxfthiYfeg6R1ity9k1Ps5TCsW2J9GATNKtbxi", "CYIFz0vSkC1qdCnMwimlGdocBaTPU0d6iu4JLj5r");
		ParseInstallation.getCurrentInstallation().saveInBackground();

		TypefaceHelper.initialize(this);

		Fresco.initialize(this);
	}

	@Override
	public void onTerminate() {
		TypefaceHelper.destroy();
		super.onTerminate();
	}
}

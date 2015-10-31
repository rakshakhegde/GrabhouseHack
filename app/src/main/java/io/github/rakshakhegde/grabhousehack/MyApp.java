package io.github.rakshakhegde.grabhousehack;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Rakshak.R.Hegde on 10/31/2015.
 */
public class MyApp extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		// Enable Local Datastore
		Parse.enableLocalDatastore(this);

		Parse.initialize(this, "tNWxfthiYfeg6R1ity9k1Ps5TCsW2J9GATNKtbxi", "CYIFz0vSkC1qdCnMwimlGdocBaTPU0d6iu4JLj5r");
	}
}

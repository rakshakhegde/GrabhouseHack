package io.github.rakshakhegde.grabhousehack;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Rakshak.R.Hegde on 11/1/2015.
 */
@ParseClassName("House")
public class OpenHousePOJO extends ParseObject {
	public static final String PARSE_CLASS = "House";
	public boolean shortlisted; // false by default
	public OpenHousePOJO() {}
}

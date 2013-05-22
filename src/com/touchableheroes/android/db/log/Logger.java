package com.touchableheroes.android.db.log;

import android.util.Log;


/**
 * @author Andreas Siebert, ask@touchableheroes.com
 */
public class Logger {

	private static final String TAG = "db-drafts";

	public static void debug(final String msg) {
		Log.d( TAG, msg);
	}

	public static void info(final String msg) {
		Log.d( TAG,  msg);
	}

	public static void error(final String msg) {
		Log.e( TAG,  msg);
	}

	public static void critical(final String msg) {
		Log.e( TAG, "CRITICAL ERROR: " + msg);
	}

	public static void exception(final String msg, final Throwable ex) {
		Log.e( TAG, msg, ex);
	}

	public static boolean isDebug() {
		return false;
	}
}
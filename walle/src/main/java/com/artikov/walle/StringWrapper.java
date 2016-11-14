package com.artikov.walle;

import android.content.Context;
import android.support.annotation.StringRes;

/**
 * Date: 14/11/2016
 * Time: 1:25
 *
 * @author Artur Artikov
 */
public class StringWrapper {
	public static final StringWrapper EMPTY = new StringWrapper("");

	@StringRes
	private int mStringResId;
	private String mString;

	public StringWrapper(@StringRes int stringResId) {
		mStringResId = stringResId;
		mString = "";
	}

	public StringWrapper(String mString) {
		mStringResId = -1;
		mString = mString;
	}

	public String getString(Context context) {
		return mStringResId != -1 ? context.getString(mStringResId) : mString;
	}
}

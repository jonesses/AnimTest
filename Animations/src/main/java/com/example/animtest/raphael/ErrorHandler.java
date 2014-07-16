package com.example.animtest.raphael;

import android.content.Context;
import android.widget.Toast;

public class ErrorHandler {
	public static void handleErrMsg(String msg, Context myContext) {
		Toast.makeText(myContext, msg, Toast.LENGTH_LONG).show();
	}

	public static void handleErrMsg(int msgResId, Context myContext) {
		Toast.makeText(myContext, msgResId, Toast.LENGTH_LONG).show();
	}
}

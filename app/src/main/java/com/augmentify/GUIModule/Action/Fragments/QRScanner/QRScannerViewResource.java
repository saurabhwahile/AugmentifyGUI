package com.augmentify.GUIModule.Action.Fragments.QRScanner;

import android.content.Context;

import com.augmentify.GUIModule.Utils.Utils;

public class QRScannerViewResource
{
	static Context context;
	public static final float backgroundDimAmount = 0.5f;
	
	public static class Title
	{
		public static final String text = "Scan"; 
		public static final int fontSize = 28;
		public static final String fontColor = "#777777";
	}
	
	public static class CameraView
	{
		public static final int height = (int) Utils.getPixelsFromDp(240, context);
		public static final int width = (int) Utils.getPixelsFromDp(240, context);
	}
	
	public static class Padding
	{
		public static final int left = (int) Utils.getPixelsFromDp(8, context);
		public static final int top = (int) Utils.getPixelsFromDp(2, context);
		public static final int right = (int) Utils.getPixelsFromDp(8, context);
		public static final int bottom = (int) Utils.getPixelsFromDp(2, context);
	}
}

package com.augmentify.GUIModule.MainLayouts;

import android.content.Context;

import com.augmentify.GUIModule.Utils.Utils;

public class ActionCardLayoutResource
{
	public static Context context;
	public static void setContext(Context context)
	{
		ActionCardLayoutResource.context = context;
	}
	public static final class ActionViewLayout
	{
		public static class Margin
		{
			public static final int left = (int) Utils.getPixelsFromDp(2, context);
			public static final int top = 0;
			public static final int right = (int) Utils.getPixelsFromDp(2, context);
			public static final int bottom = 0;
		}
	}
}

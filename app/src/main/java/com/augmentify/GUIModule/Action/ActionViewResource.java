package com.augmentify.GUIModule.Action;

import android.content.Context;
import android.graphics.Typeface;

import com.augmentify.GUIModule.Utils.Utils;

public class ActionViewResource
{
	public static Context context;

	public static void setContext(Context context)
	{
		ActionViewResource.context = context;
	}

	public static class Commons
	{
		public static final int fontSize = 18;

		public static class Image
		{
			public static final int width = (int) Utils.getPixelsFromDp(36,
					context);
			public static final int height = (int) Utils.getPixelsFromDp(36,
					context);
		}
	}

	public static class Scan extends Commons
	{
		public static String text = "Scan";
		public static final Typeface font = Typeface.createFromAsset(
				context.getAssets(), "Fonts/Neris-Black.otf");

		public static final class Layout
		{
			public static class Margin
			{
				public static final int left = (int) Utils.getPixelsFromDp(2,
						context);
				public static final int top = (int) Utils.getPixelsFromDp(6,
						context);
				public static final int right = 0;
				public static final int bottom = (int) Utils.getPixelsFromDp(4,
						context);
			}
		}
	}

	public static class Add extends Commons
	{
		public static String text = "Add";
		public static final Typeface font = Typeface.createFromAsset(
				context.getAssets(), "Fonts/Neris-Black.otf");

		public static final class Layout
		{
			public static final class Margin
			{
				public static final int left = 0;
				public static final int top = (int) Utils.getPixelsFromDp(6,
						context);
				public static final int right = (int) Utils.getPixelsFromDp(2,
						context);
				public static final int bottom = (int) Utils.getPixelsFromDp(4,
						context);
			}
		}
	}

	public static class Explorer extends Commons
	{
		public static class Image
		{
			public static final int width = (int) Utils.getPixelsFromDp(48,
					context);
			public static final int height = (int) Utils.getPixelsFromDp(48,
					context);
		}
	}

	public static final class Bulletin extends Commons
	{
		public static final String text = "Requests";

		public static final class Number
		{
			public static final String fontColor = "#000000";
		}

		public static class Margin
		{
			public static final int left = (int) Utils.getPixelsFromDp(8,
					context);
			public static final int top = (int) Utils.getPixelsFromDp(6,
					context);
			public static final int right = (int) Utils.getPixelsFromDp(6,
					context);
			public static final int bottom = (int) Utils.getPixelsFromDp(4,
					context);
		}
	}

	public static final class Profile extends Commons
	{
		public static final String text = "    Admin";

		public static class Margin
		{
			public static final int left = (int) Utils.getPixelsFromDp(2,
					context);
			public static final int top = (int) Utils.getPixelsFromDp(6,
					context);
			public static final int right = 0;
			public static final int bottom = (int) Utils.getPixelsFromDp(4,
					context);
		}
	}

}

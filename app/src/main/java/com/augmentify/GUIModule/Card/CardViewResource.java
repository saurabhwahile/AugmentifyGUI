package com.augmentify.GUIModule.Card;

import android.content.Context;

import com.augmentify.GUIModule.Utils.Utils;

public class CardViewResource
{
	public static Context context;
	public static void setContext(Context context)
	{
		CardViewResource.context = context;
	}
	
	public static class Commons
	{
		public static final class Image
		{
			public static final int width = (int) Utils.getPixelsFromDp(16, context);
			public static final int height = (int) Utils.getPixelsFromDp(16, context);
			public static final class Padding
			{
				public static final int left = 0;
				public static final int top = 0;
				public static final int right = 0;
				public static final int bottom = 0;
			}
		}
	}
	
	public static final String backgroundColor = "#FFFFFF";
	public static class Margin
	{
		public static final int left = 0;
		public static final int top = 0;
		public static final int right = 0;
		public static final int bottom = (int) Utils.getPixelsFromDp(4, context);;
	}
	
	public static class Title
	{
		public static final String fontColor = "#000000";
		public static final float fontSize = 14;
		public static class Padding
		{
			public static final int left = (int) Utils.getPixelsFromDp(4, context);
			public static final int top = 0;
			public static final int right = 0;
			public static final int bottom = 0;
		}
	}
	
	public static class Date
	{
		public static final String fontColor = "#777777";
		public static final float fontSize = 10;
		public static class Padding
		{
			public static final int left = (int) Utils.getPixelsFromDp(12, context);
			public static final int top = 0;
			public static final int right = 0;
			public static final int bottom = 0;
		}
	}
	
	public static class HorizontalSeparator
	{
		public static final String backgroundColor = "#262626";
		public static final int height = (int) Utils.getPixelsFromDp(1, context);
		public static class Padding
		{
			public static final int left = 0;
			public static final int top = 0;
			public static final int right = 0;
			public static final int bottom = 0;
		}
		
		public static class Margin
		{
			public static final int left = (int) Utils.getPixelsFromDp(2, context);
			public static final int top = 0;
			public static final int right = 0;
			public static final int bottom = 0;
		}
	}
	
	public static class SmileyLike extends Commons
	{
		public static final String fontColor = "#444444";
		public static final float fontSize = 12;
		
		public static class Padding
		{
			public static final int left = (int) Utils.getPixelsFromDp(4, context);
			public static final int top = 0;
			public static final int right = 0;
			public static final int bottom = 0;
		}
	}
	
	public static class Comment extends Commons
	{
		public static final String fontColor = "#444444";
		public static final float fontSize = 12;
		
		public static class Padding
		{
			public static final int left = (int) Utils.getPixelsFromDp(4, context);
			public static final int top = 0;
			public static final int right = 0;
			public static final int bottom = 0;
		}
	}
	
	public static class Details extends Commons
	{
		public static class Padding
		{
			public static final int left = (int) Utils.getPixelsFromDp(4, context);
			public static final int top = 0;
			public static final int right = 0;
			public static final int bottom = 0;
		}
	}

    public static class PullToRefresh extends Commons
    {
        public static class PullToRefreshText
        {
            public static final String text = "Pull To Refresh";
            public static final String fontColor = "#777777";
            public static final float fontSize = 12;
        }

        public static class Padding
        {
            public static final int left = (int) Utils.getPixelsFromDp(4, context);
            public static final int top = 0;
            public static final int right = 0;
            public static final int bottom = 0;
        }
    }
	
	public static class Layout
	{
		public static class Padding
		{
			public static final int left = (int) Utils.getPixelsFromDp(5, context);
			public static final int top = (int) Utils.getPixelsFromDp(5, context);
			public static final int right = (int) Utils.getPixelsFromDp(5, context);
			public static final int bottom = 0;
		}
	}
}

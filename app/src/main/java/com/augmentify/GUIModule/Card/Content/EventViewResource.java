package com.augmentify.GUIModule.Card.Content;

import android.content.Context;

import com.augmentify.GUIModule.Utils.Utils;

public class EventViewResource
{
	public static Context context;
	public static void setContext(Context context)
	{
		EventViewResource.context = context;
	}

    static class Commons
    {
        public static final String fontColor = "#444444";
        public static final float fontSize = 12;

        public static final class Layout
        {
            public static class Margin
            {
                public static final int left = 0;
                public static final int top = (int) Utils.getPixelsFromDp(4, EventViewResource.context);
                public static final int right = 0;
                public static final int bottom = 0;
            }
        }

        public static final class Image
        {
            public static final int height = (int) Utils.getPixelsFromDp(16, EventViewResource.context);
            public static final int width = (int) Utils.getPixelsFromDp(16, EventViewResource.context);

            public static class Padding
            {
                public static final int left = 0;
                public static final int top = 0;
                public static final int right = 0;
                public static final int bottom = 0;
            }
        }

        public static class Padding
        {
            public static final int left = (int) Utils.getPixelsFromDp(4, EventViewResource.context);
            public static final int top = 0;
            public static final int right = 0;
            public static final int bottom = 0;
        }
    }


	public static class Padding
	{
		public static final int left = (int) Utils.getPixelsFromDp(4, context);
		public static final int top = (int) Utils.getPixelsFromDp(4, context);
		public static final int right = (int) Utils.getPixelsFromDp(4, context);
		public static final int bottom = (int) Utils.getPixelsFromDp(4, context);
	}
	
	public static class Time extends Commons
	{
		
	}
	
	public static class Venue extends Commons
	{
		
	}
	
	public static class Description extends Commons 
	{

	}
}

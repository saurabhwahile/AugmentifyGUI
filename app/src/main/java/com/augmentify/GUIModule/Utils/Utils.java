package com.augmentify.GUIModule.Utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.TypedValue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils
{
	public static String textClipper(String text, int size,
			int numberOfEndingDots)
	{
		if (text.length() < size)
		{
			return text;
		}
		StringBuffer clippedText = new StringBuffer(text.substring(0, size));
		for (int character = clippedText.length() - 1; character >= clippedText
				.length() - numberOfEndingDots; character--)
		{
			clippedText.setCharAt(character, '.');
		}
		return clippedText.toString();
	}
	
	public static float getPixelsFromDp(int densityPixel, Context context)
	{
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, densityPixel,
				context.getResources().getDisplayMetrics());
	}
	
	public static final String DEFAULT_DB_DATE_FORMAT = "yyyyMMddHHmmss";
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			DEFAULT_DB_DATE_FORMAT);

	public static long formatDateAsLong(Calendar calender)
	{
		return Long.parseLong(dateFormat.format(calender.getTime()));
	}

	public static Calendar getCalendarFromFormattedLong(long longDate)
	{
		try
		{
			Calendar calender = Calendar.getInstance();
			calender.setTime(dateFormat.parse(String.valueOf(longDate)));
			return calender;
		}
		catch (ParseException e)
		{
			return null;
		}
	}

	public static final String DEFAULT_DATE_FORMAT = "MM/dd/yyyy";

	public static String getFormattedDate(Calendar calendar)
	{
		return getFormattedDate(DEFAULT_DATE_FORMAT, calendar.getTime());
	}

	public static String getFormattedDate(Date date)
	{
		return getFormattedDate(DEFAULT_DATE_FORMAT, date);
	}

	public static String getFormattedDate(String format, Calendar calendar)
	{
		return getFormattedDate(format, calendar.getTime());
	}

	public static String getFormattedDate(String format, Date date)
	{
		return new SimpleDateFormat(format).format(date);
	}
    public static String getRealPathFromURI(Context context, Uri contentUri)
    {
        Cursor cursor = null;
        try
        {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri,  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        finally
        {
            if (cursor != null)
            {
                cursor.close();
            }
        }
    }
}

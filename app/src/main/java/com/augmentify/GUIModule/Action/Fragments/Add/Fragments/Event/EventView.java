package com.augmentify.GUIModule.Action.Fragments.Add.Fragments.Event;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.TypedValue;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.augmentify.DataModule.Controller.Controller;
import com.augmentify.DataModule.Objects.Card.Event;
import com.augmentify.DataModule.Objects.Image;
import com.augmentify.GUIModule.Action.Fragments.Add.Fragments.FragmentController;
import com.augmentify.GUIModule.Utils.DisplayUtils;
import com.augmentify.R;
import com.augmentify.Resource;

import java.util.Calendar;

public class EventView extends RelativeLayout
{
	Context context;

	public EventView(Context context)
	{
		super(context);
		this.context = context;

		EventViewResource.setContext(context);
	}

    public static final int SELECT_IMAGE = 0;

	static LayoutParams params;

	TextView title;
	View horizontalSeparator;
	EditText name;
	DialogFragment datePickerFragment;
	RelativeLayout dateLayout;
	ImageView dateImage;
	static TextView date;
	DialogFragment timePickerFragment;
	RelativeLayout timeLayout;
	ImageView timeImage;
	static TextView time;
	EditText venue;
	EditText description;
    Spinner visibility;
	ImageView displayPicturePreview;
	RelativeLayout displayPictureLayout;
	ImageView displayPictureImage;
	TextView displayPicture;
	public RelativeLayout createEventLayout;
	public ImageView createEventImage;
	public static TextView createEvent;

	public void build()
	{
		title = new TextView(context);
		title.setId(Resource.id++);
		title.setText(EventViewResource.Title.text);
		title.setTextSize(TypedValue.COMPLEX_UNIT_SP,
				EventViewResource.Title.fontSize);
		title.setTextColor(Color.parseColor(EventViewResource.Title.fontColor));
		params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		params.setMargins(EventViewResource.Title.Margin.left,
				EventViewResource.Title.Margin.top,
				EventViewResource.Title.Margin.right,
				EventViewResource.Title.Margin.bottom);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		this.addView(title, params);

		horizontalSeparator = new View(context);
		horizontalSeparator.setId(Resource.id++);
		horizontalSeparator
				.setBackgroundResource(R.drawable.horizontal_separator);
		params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		params.height = EventViewResource.HorizontalSeparator.height;
		params.addRule(RelativeLayout.BELOW, title.getId());
		this.addView(horizontalSeparator, params);

		name = new EditText(context);
		name.setId(Resource.id++);
		name.setHint(EventViewResource.Name.text);
		name.setHintTextColor(Color
				.parseColor(EventViewResource.Name.hintFontColor));
		name.setTextSize(TypedValue.COMPLEX_UNIT_SP,
				EventViewResource.Name.fontSize);
		params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.BELOW, horizontalSeparator.getId());
		this.addView(name, params);

		dateLayout = new RelativeLayout(context);
		dateLayout.setId(Resource.id++);

		dateImage = new ImageView(context);
		dateImage.setId(Resource.id++);
		dateImage.setImageResource(R.drawable.card_event_date);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		params.height = EventViewResource.Date.Image.height;
		params.width = EventViewResource.Date.Image.width;
		dateLayout.addView(dateImage, params);

		date = new TextView(context);
		date.setId(Resource.id++);
		date.setText("Date");
		date.setTextSize(TypedValue.COMPLEX_UNIT_SP,
				EventViewResource.Date.fontSize);
		date.setTextColor(Color.parseColor(EventViewResource.Date.fontColor));
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.RIGHT_OF, dateImage.getId());
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		dateLayout.addView(date, params);

		datePickerFragment = new DatePickerFragment();
		dateLayout
				.setOnTouchListener(new DisplayUtils.OnTouchButtonBackgroundSet());
		dateLayout.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				datePickerFragment.show(
						((Activity) context).getFragmentManager(),
						"DatePickerDialog");
			}
		});
		dateLayout.setBackgroundResource(R.drawable.button);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.BELOW, name.getId());
		this.addView(dateLayout, params);

		timeLayout = new RelativeLayout(context);
		timeLayout.setId(Resource.id++);

		timeImage = new ImageView(context);
		timeImage.setId(Resource.id++);
		timeImage.setImageResource(R.drawable.card_event_time);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.height = EventViewResource.Time.Image.height;
		params.width = EventViewResource.Time.Image.width;
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		timeLayout.addView(timeImage, params);

		time = new TextView(context);
		time.setId(Resource.id++);
		time.setText("Time");
		time.setTextSize(TypedValue.COMPLEX_UNIT_SP,
				EventViewResource.Time.fontSize);
		time.setTextColor(Color.parseColor(EventViewResource.Time.fontColor));
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.RIGHT_OF, timeImage.getId());
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		timeLayout.addView(time, params);
		
		timePickerFragment = new TimePickerFragment();
		timeLayout
				.setOnTouchListener(new DisplayUtils.OnTouchButtonBackgroundSet());
		timeLayout.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				timePickerFragment.show(
						((Activity) context).getFragmentManager(),
						"TimePickerDialog");
			}
		});
		timeLayout.setBackgroundResource(R.drawable.button);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.RIGHT_OF, dateLayout.getId());
		params.addRule(RelativeLayout.BELOW, name.getId());
		this.addView(timeLayout, params);

		venue = new EditText(context);
		venue.setId(Resource.id++);
		venue.setHint(EventViewResource.Venue.text);
		venue.setHintTextColor(Color
				.parseColor(EventViewResource.Venue.hintFontColor));
		venue.setTextSize(TypedValue.COMPLEX_UNIT_SP,
				EventViewResource.Venue.fontSize);
		params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.BELOW, dateLayout.getId());
		this.addView(venue, params);

		description = new EditText(context);
		description.setId(Resource.id++);
		description.setHint(EventViewResource.Description.text);
		description.setHintTextColor(Color
				.parseColor(EventViewResource.Description.hintFontColor));
		description.setTextSize(TypedValue.COMPLEX_UNIT_SP,
				EventViewResource.Description.fontSize);
		params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.BELOW, venue.getId());
		this.addView(description, params);

        visibility = new Spinner(context);
        visibility.setId(Resource.id++);
        visibility.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, EventViewResource.Visibilty.choices));
        params = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.BELOW, description.getId());
        this.addView(visibility, params);

		displayPicturePreview = new ImageView(context);
		displayPicturePreview.setId(Resource.id++);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.addRule(RelativeLayout.BELOW, visibility.getId());
		this.addView(displayPicturePreview, params);

		displayPictureLayout = new RelativeLayout(context);
		displayPictureLayout.setId(Resource.id++);

		displayPictureImage = new ImageView(context);
		displayPictureImage.setId(Resource.id++);
		displayPictureImage.setImageResource(R.drawable.card_event_image);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.height = EventViewResource.DisplayPicture.Image.height;
		params.width = EventViewResource.DisplayPicture.Image.width;
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		displayPictureLayout.addView(displayPictureImage, params);

		displayPicture = new TextView(context);
		displayPicture.setId(Resource.id++);
		displayPicture.setText(EventViewResource.DisplayPicture.text);
		displayPicture.setTextSize(TypedValue.COMPLEX_UNIT_SP,
				EventViewResource.DisplayPicture.fontSize);
		displayPicture.setTextColor(Color
				.parseColor(EventViewResource.DisplayPicture.fontColor));
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.RIGHT_OF, displayPictureImage.getId());
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		displayPictureLayout.addView(displayPicture, params);

		displayPictureLayout
				.setOnTouchListener(new DisplayUtils.OnTouchButtonBackgroundSet());
		displayPictureLayout.setBackgroundResource(R.drawable.button);
        displayPictureLayout.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent imagePickerIntent = new Intent(Intent.ACTION_PICK);
                imagePickerIntent.setType("image/*");
                EventViewFragment.newInstance().startActivityForResult(imagePickerIntent, SELECT_IMAGE);
        }


    });
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.BELOW, displayPicturePreview.getId());
		this.addView(displayPictureLayout, params);

		createEventLayout = new RelativeLayout(context);
		createEventLayout.setId(Resource.id++);

		createEventImage = new ImageView(context);
		createEventImage.setId(Resource.id++);
		createEventImage.setImageResource(R.drawable.card_event_image);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.height = EventViewResource.CreateEvent.Image.height;
		params.width = EventViewResource.CreateEvent.Image.width;
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		createEventLayout.addView(createEventImage, params);

		createEvent = new TextView(context);
		createEvent.setId(Resource.id++);
		createEvent.setText(EventViewResource.CreateEvent.text);
		createEvent.setTextSize(TypedValue.COMPLEX_UNIT_SP,
				EventViewResource.CreateEvent.fontSize);
		createEvent.setTextColor(Color
				.parseColor(EventViewResource.CreateEvent.fontColor));
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.RIGHT_OF, createEventImage.getId());
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		createEventLayout.addView(createEvent, params);

		createEventLayout
				.setOnTouchListener(new DisplayUtils.OnTouchButtonBackgroundSet());
		createEventLayout.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if(buildDataFromView())
				{

                    createEventLayout.setEnabled(false);
                    if(image_path!=null)
                    {
                        Image eventImage = new Image(context)
                        {
                            @Override
                            public void onStatusChange(Status status)
                            {
                                switch(status)
                                {
                                    case CREATE_OK:
                                    {
                                        eventData.image_id = this.id;
                                        eventData.create();
                                    }
                                }
                            }
                        };
                        eventImage.setCreateRequestParams(Image.RESOURCE_TYPE.IMAGE, image_path);
                        eventImage.create();
                    }
                    else
                    {
                        eventData.create();
                    }
				}
			}
		});
		createEventLayout.setBackgroundResource(R.drawable.button);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		params.addRule(RelativeLayout.BELOW, displayPictureLayout.getId());
		this.addView(createEventLayout, params);

		this.setId(Resource.id++);
	}

	Event eventData;
    public String image_path;

	public boolean buildDataFromView()
	{
		if (name.getText().toString().matches(""))
		{
			Toast.makeText(context, "Event Name?", Toast.LENGTH_SHORT).show();
			return false;
		}
		if (description.getText().toString().matches(""))
		{
			Toast.makeText(context, "Event Description?", Toast.LENGTH_SHORT).show();
			return false;
		}
		if (date.getText().toString().matches("Date"))
		{
			Toast.makeText(context, "Event Date?", Toast.LENGTH_SHORT).show();
			return false;
		}
		if (time.getText().toString().matches("Time"))
		{
			Toast.makeText(context, "Event Time?", Toast.LENGTH_SHORT).show();
			return false;
		}
		if (venue.getText().toString().matches(""))
		{
			Toast.makeText(context, "Event Venue?", Toast.LENGTH_SHORT).show();
			return false;
		}
        int visibilitySelected = 1;
        for(String visibilityChoice:EventViewResource.Visibilty.choices)
        {
            if(visibilityChoice.matches(visibility.getSelectedItem().toString()))
            {
                break;
            }
            visibilitySelected++;
        }
        eventData = new Event(context)
        {
            @Override
            public void onStatusChange(Status status)
            {
                switch (status)
                {
                    case ERROR_NETWORK:
                    {
                        Toast.makeText(context, "Network Unavailable", Toast.LENGTH_SHORT).show();
                    }
                    break;
                    case CREATE_OK:
                    {
                        Toast.makeText(context, "Created", Toast.LENGTH_SHORT).show();
                        FragmentController.dismiss(FragmentController.Fragment.EVENT_VIEW_FRAGMENT);
                    }
                    break;
                }
                createEventLayout.setEnabled(true);
            }
        };

        eventData.setCreateRequestParams(
                name.getText().toString(),
                description.getText().toString(),
                venue.getText().toString(),
                date.getText().toString()+" "+time.getText().toString(),
                null,
                visibilitySelected
        );
		return true;
	}

	public static class DatePickerFragment extends DialogFragment implements
			DatePickerDialog.OnDateSetListener
	{
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState)
		{
			// Use the current date as the default date in the picker
			final Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH);
			int day = calendar.get(Calendar.DAY_OF_MONTH);

			// Create a new instance of DatePickerDialog and return it
			return new DatePickerDialog(getActivity(), this, year, month, day);
		}

		public void onDateSet(DatePicker view, int year, int month, int day)
		{
			Calendar calendar = Calendar.getInstance();
			calendar.set(year, month, day);

			java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance();
			dateFormat.setCalendar(calendar);
			date.setText(dateFormat.format(calendar.getTime()));
		}
	}

	public static class TimePickerFragment extends DialogFragment implements
			TimePickerDialog.OnTimeSetListener
	{

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState)
		{
			// Use the current eventTime as the default values for the picker
			final Calendar c = Calendar.getInstance();
			int hour = c.get(Calendar.HOUR_OF_DAY);
			int minute = c.get(Calendar.MINUTE);
			
			// Create a new instance of TimePickerDialog and return it
			return new TimePickerDialog(getActivity(), this, hour, minute,
					DateFormat.is24HourFormat((Activity)Controller.context));
			
		}

		public void onTimeSet(TimePicker view, int hourOfDay, int minute)
		{
			time.setText(Integer.toString(hourOfDay)+":"+Integer.toString(minute));
		}
	}
}

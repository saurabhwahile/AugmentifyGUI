package com.augmentify.GUIModule.Action.Fragments.Add;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.augmentify.GUIModule.Action.Fragments.Add.Fragments.FragmentController;
import com.augmentify.GUIModule.Utils.DisplayUtils;
import com.augmentify.R;
import com.augmentify.Resource;

public class AddView extends RelativeLayout
{
	Context context;
	public AddView(Context context)
	{
		super(context);
		this.context = context;
		AddViewResource.context = context;

		FragmentController.setActivity((Activity) context);
	}

	RelativeLayout statusLayout;
	ImageView statusImage;
	TextView status;

	RelativeLayout eventLayout;
	ImageView eventImage;
	TextView event;

	RelativeLayout messageLayout;
	ImageView messageImage;
	TextView message;

	RelativeLayout minimapsLayout;
	ImageView minimapsImage;
	TextView minimaps;

	RelativeLayout widgetLayout;
	ImageView widgetImage;
	TextView widget;

	LayoutParams params;

	public void build()
	{
		statusLayout = new RelativeLayout(context);

		statusImage = new ImageView(context);
		statusImage.setImageResource(R.drawable.action_add_status);
		statusImage.setId(Resource.id++);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		statusLayout.addView(statusImage, params);

		status = new TextView(context);
		status.setText(AddViewResource.Status.text);
		status.setTextSize(TypedValue.COMPLEX_UNIT_SP,
				AddViewResource.Status.fontSize);
		status.setTextColor(Color.parseColor(AddViewResource.Status.fontColor));
		status.setId(Resource.id++);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.addRule(RelativeLayout.BELOW, statusImage.getId());
		statusLayout.addView(status, params);
		statusLayout
				.setOnTouchListener(new DisplayUtils.OnTouchButtonBackgroundSet());
		statusLayout.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				FragmentController
						.initialize(FragmentController.Fragment.SAY_VIEW_FRAGMENT);
				FragmentController
						.show(FragmentController.Fragment.SAY_VIEW_FRAGMENT);
				com.augmentify.GUIModule.Action.Fragments.FragmentController
						.dismiss(com.augmentify.GUIModule.Action.Fragments.FragmentController.Fragment.ADD_VIEW_FRAGMENT);
			}

		});
		statusLayout.setBackgroundResource(R.drawable.button);
		statusLayout.setId(Resource.id++);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.setMargins(AddViewResource.Status.Margins.left,
				AddViewResource.Status.Margins.top,
				AddViewResource.Status.Margins.right,
				AddViewResource.Status.Margins.bottom);
		params.height = AddViewResource.Status.Layout.height;
		params.width = AddViewResource.Status.Layout.width;
		this.addView(statusLayout, params);

		eventLayout = new RelativeLayout(context);

		eventImage = new ImageView(context);
		eventImage.setImageResource(R.drawable.action_add_event);
		eventImage.setId(Resource.id++);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		eventLayout.addView(eventImage, params);

		event = new TextView(context);
		event.setText(AddViewResource.Event.text);
		event.setTextSize(TypedValue.COMPLEX_UNIT_SP,
				AddViewResource.Event.fontSize);
		event.setTextColor(Color.parseColor(AddViewResource.Event.fontColor));
		event.setId(Resource.id++);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.addRule(RelativeLayout.BELOW, eventImage.getId());
		eventLayout.addView(event, params);
		eventLayout
				.setOnTouchListener(new DisplayUtils.OnTouchButtonBackgroundSet());
		eventLayout.setBackgroundResource(R.drawable.button);
		eventLayout.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				FragmentController
						.initialize(FragmentController.Fragment.EVENT_VIEW_FRAGMENT);
				FragmentController
						.show(FragmentController.Fragment.EVENT_VIEW_FRAGMENT);
				com.augmentify.GUIModule.Action.Fragments.FragmentController
						.dismiss(com.augmentify.GUIModule.Action.Fragments.FragmentController.Fragment.ADD_VIEW_FRAGMENT);
			}

		});
		eventLayout.setId(Resource.id++);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.RIGHT_OF, statusLayout.getId());
		params.setMargins(AddViewResource.Event.Margins.left,
				AddViewResource.Event.Margins.top,
				AddViewResource.Event.Margins.right,
				AddViewResource.Event.Margins.bottom);
		params.height = AddViewResource.Event.Layout.height;
		params.width = AddViewResource.Event.Layout.width;
		this.addView(eventLayout, params);

		messageLayout = new RelativeLayout(context);

		messageImage = new ImageView(context);
		messageImage.setImageResource(R.drawable.action_add_message);
		messageImage.setId(Resource.id++);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		messageLayout.addView(messageImage, params);

		message = new TextView(context);
		message.setText(AddViewResource.Message.text);
		message.setTextSize(TypedValue.COMPLEX_UNIT_SP,
				AddViewResource.Message.fontSize);
		message.setTextColor(Color
				.parseColor(AddViewResource.Message.fontColor));
		message.setId(Resource.id++);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.BELOW, messageImage.getId());
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		messageLayout.addView(message, params);
		messageLayout
				.setOnTouchListener(new DisplayUtils.OnTouchButtonBackgroundSet());
		messageLayout.setBackgroundResource(R.drawable.button);
		messageLayout.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v)
			{
				
			}
			
		});
		messageLayout.setId(Resource.id++);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.BELOW, statusLayout.getId());
		params.setMargins(AddViewResource.Message.Margins.left,
				AddViewResource.Message.Margins.top,
				AddViewResource.Message.Margins.right,
				AddViewResource.Message.Margins.bottom);
		params.height = AddViewResource.Message.Layout.height;
		params.width = AddViewResource.Message.Layout.width;
		this.addView(messageLayout, params);

		minimapsLayout = new RelativeLayout(context);

		minimapsImage = new ImageView(context);
		minimapsImage.setImageResource(R.drawable.action_add_minimap);
		minimapsImage.setId(Resource.id++);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		minimapsLayout.addView(minimapsImage, params);

		minimaps = new TextView(context);
		minimaps.setText(AddViewResource.Minimaps.text);
		minimaps.setTextSize(TypedValue.COMPLEX_UNIT_SP,
				AddViewResource.Minimaps.fontSize);
		minimaps.setTextColor(Color
				.parseColor(AddViewResource.Minimaps.fontColor));
		minimaps.setId(Resource.id++);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.BELOW, minimapsImage.getId());
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		minimapsLayout.addView(minimaps, params);
		minimapsLayout
				.setOnTouchListener(new DisplayUtils.OnTouchButtonBackgroundSet());
		minimapsLayout.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				FragmentController
						.initialize(FragmentController.Fragment.MINIMAP_VIEW_FRAGMENT);
				FragmentController
						.show(FragmentController.Fragment.MINIMAP_VIEW_FRAGMENT);
				com.augmentify.GUIModule.Action.Fragments.FragmentController
						.dismiss(com.augmentify.GUIModule.Action.Fragments.FragmentController.Fragment.ADD_VIEW_FRAGMENT);
			}

		});
		minimapsLayout.setBackgroundResource(R.drawable.button);
		minimapsLayout.setId(Resource.id++);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.BELOW, statusLayout.getId());
		params.addRule(RelativeLayout.RIGHT_OF, messageLayout.getId());
		params.setMargins(AddViewResource.Minimaps.Margins.left,
				AddViewResource.Minimaps.Margins.top,
				AddViewResource.Minimaps.Margins.right,
				AddViewResource.Minimaps.Margins.bottom);
		params.height = AddViewResource.Minimaps.Layout.height;
		params.width = AddViewResource.Minimaps.Layout.width;
		this.addView(minimapsLayout, params);

		widgetLayout = new RelativeLayout(context);

		widgetImage = new ImageView(context);
		widgetImage.setImageResource(R.drawable.action_explorer);
		widgetImage.setId(Resource.id++);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		widgetLayout.addView(widgetImage, params);

		widget = new TextView(context);
		widget.setText(AddViewResource.Widget.text);
		widget.setTextSize(TypedValue.COMPLEX_UNIT_SP,
				AddViewResource.Widget.fontSize);
		widget.setTextColor(Color.parseColor(AddViewResource.Widget.fontColor));
		widget.setId(Resource.id++);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.BELOW, widgetImage.getId());
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		widgetLayout.addView(widget, params);
		widgetLayout
				.setOnTouchListener(new DisplayUtils.OnTouchButtonBackgroundSet());
		widgetLayout.setBackgroundResource(R.drawable.button);
		widgetLayout.setId(Resource.id++);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.BELOW, minimapsLayout.getId());
		params.setMargins(AddViewResource.Widget.Margins.left,
				AddViewResource.Widget.Margins.top,
				AddViewResource.Widget.Margins.right,
				AddViewResource.Widget.Margins.bottom);
		params.height = AddViewResource.Widget.Layout.height;
		params.width = AddViewResource.Widget.Layout.width;
		this.addView(widgetLayout, params);

		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		this.setLayoutParams(params);

		this.setId(Resource.id++);
	}
}

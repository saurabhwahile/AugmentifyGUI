package com.augmentify.GUIModule.Action.Fragments.Add.Fragments.Say;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.augmentify.DataModule.Objects.Card.Say;
import com.augmentify.DataModule.Objects.DataObject.Status;
import com.augmentify.GUIModule.Utils.DisplayUtils;
import com.augmentify.R;
import com.augmentify.Resource;

public class SayView extends RelativeLayout
{
	Context context;
	public SayView(Context context)
	{
		super(context);
		this.context = context;
		SayViewResource.context = context;
	}

	TextView title;
	View horizontalSeparator;
	EditText say;
	RelativeLayout createSayLayout;
	ImageView createSayImage;
	static TextView createSay;
	
	LayoutParams params;
	
	public void build()
	{
		title = new TextView(context);
		title.setId(Resource.id++);
		title.setText(SayViewResource.Title.text);
		title.setTextSize(TypedValue.COMPLEX_UNIT_SP,
				SayViewResource.Title.fontSize);
		title.setTextColor(Color.parseColor(SayViewResource.Title.fontColor));
		params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		params.setMargins(SayViewResource.Title.Margin.left,
				SayViewResource.Title.Margin.top,
				SayViewResource.Title.Margin.right,
				SayViewResource.Title.Margin.bottom);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		this.addView(title, params);

		horizontalSeparator = new View(context);
		horizontalSeparator.setId(Resource.id++);
		horizontalSeparator
				.setBackgroundResource(R.drawable.horizontal_separator);
		params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		params.height = SayViewResource.HorizontalSeparator.height;
		params.addRule(RelativeLayout.BELOW, title.getId());
		this.addView(horizontalSeparator, params);
		
		say = new EditText(context);
		say.setId(Resource.id++);
		say.setHint(SayViewResource.Say.text);
		say.setHintTextColor(Color
				.parseColor(SayViewResource.Say.hintFontColor));
		say.setTextSize(TypedValue.COMPLEX_UNIT_SP,
				SayViewResource.Say.fontSize);
		params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.BELOW, horizontalSeparator.getId());
		this.addView(say, params);
		
		createSayLayout = new RelativeLayout(context);
		createSayLayout.setId(Resource.id++);

		createSayImage = new ImageView(context);
		createSayImage.setId(Resource.id++);
		createSayImage.setImageResource(R.drawable.card_event_image);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.height = SayViewResource.CreateSay.Image.height;
		params.width = SayViewResource.CreateSay.Image.width;
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		createSayLayout.addView(createSayImage, params);

		createSay = new TextView(context);
		createSay.setId(Resource.id++);
		createSay.setText(SayViewResource.CreateSay.text);
		createSay.setTextSize(TypedValue.COMPLEX_UNIT_SP,
				SayViewResource.CreateSay.fontSize);
		createSay.setTextColor(Color
				.parseColor(SayViewResource.CreateSay.fontColor));
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.RIGHT_OF, createSayImage.getId());
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		createSayLayout.addView(createSay, params);

		createSayLayout
				.setOnTouchListener(new DisplayUtils.OnTouchButtonBackgroundSet());
		createSayLayout.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if(buildDataFromView())
				{
					//Say.create(sayData);
				}
			}
		});
		createSayLayout.setBackgroundResource(R.drawable.button);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		params.addRule(RelativeLayout.BELOW, say.getId());
		this.addView(createSayLayout, params);
	}
	
	Say sayData;
	
	public boolean buildDataFromView()
	{
		if (say.getText().toString().matches(""))
		{
			Toast.makeText(context, "Say Something!", Toast.LENGTH_SHORT).show();
			return false;
		}
		
		sayData = new Say();
		sayData.say = say.getText().toString();
		return true;
	}
	
	public static void changeCreateStatus(Status status)
	{
		onChangeCreateStatus(status);
	}

	public static void onChangeCreateStatus(Status status)
	{
		switch (status)
		{
			case ERROR:
			{
				createSay.setText("Retry");
			}
				break;
			case LOADING:
			{
				createSay.setText("Saying...");
			}
				break;
			case READ_OK:
			{
				createSay.setText("Said");
			}
				break;
			case START:
			{
				createSay.setText("Say");
			}
				break;
		}
	}
}

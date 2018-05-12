package com.augmentify.GUIModule.Card.Fragments.Details;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.augmentify.DataModule.Objects.DataObject;
import com.augmentify.GUIModule.Card.CardViewFactory;
import com.augmentify.GUIModule.Card.Fragments.Details.Content.EventView;
import com.augmentify.R;
import com.augmentify.Resource;

import java.util.Date;

public class DetailsView extends RelativeLayout
{
	Context context;
    CardViewFactory cardViewFactory;
	public DetailsView(Context context)
	{
		super(context);
		this.context = context;
		DetailsViewResource.setContext(context);
        cardViewFactory = new CardViewFactory(context, this);
	}
	
	public TextView title;
	public TextView date;
	public View horizontalSeparator;
	public RelativeLayout decidedContent;
    public CardViewFactory.Smike smike;
    public CardViewFactory.Details details;
	
	static LayoutParams params;
	
	public void build()
	{
		title = new TextView(context);
		title.setTextSize(TypedValue.COMPLEX_UNIT_SP, DetailsViewResource.Title.fontSize);
		title.setText("Title");
		title.setTextColor(Color.parseColor(DetailsViewResource.Title.fontColor));
		title.setPadding(DetailsViewResource.Title.Padding.left,
				DetailsViewResource.Title.Padding.top,
				DetailsViewResource.Title.Padding.right,
				DetailsViewResource.Title.Padding.bottom);		
		title.setId(Resource.id++);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		this.addView(title, params);
		
		date = new TextView(context);
		date.setTextSize(TypedValue.COMPLEX_UNIT_SP, DetailsViewResource.Date.fontSize);
		Date d = new Date();
		date.setText(d.toString().substring(0, 10));
		date.setTextColor(Color.parseColor(DetailsViewResource.Date.fontColor));
		date.setPadding(DetailsViewResource.Date.Padding.left,
				DetailsViewResource.Date.Padding.top,
				DetailsViewResource.Date.Padding.right,
				DetailsViewResource.Date.Padding.bottom);
		date.setId(Resource.id++);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.addRule(RelativeLayout.BELOW, title.getId());
		this.addView(date, params);

		horizontalSeparator = new View(context);
		horizontalSeparator.setBackgroundResource(R.drawable.horizontal_separator);
		horizontalSeparator.setPadding(
				DetailsViewResource.HorizontalSeparator.Padding.left,
				DetailsViewResource.HorizontalSeparator.Padding.top,
				DetailsViewResource.HorizontalSeparator.Padding.right,
				DetailsViewResource.HorizontalSeparator.Padding.bottom);
		horizontalSeparator.setId(Resource.id++);
		params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.BELOW, date.getId());
		params.height = 3;
		this.addView(horizontalSeparator, params);
		
		decidedContent = decideContent();
		params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.BELOW, horizontalSeparator.getId());
		this.addView(decidedContent, params);

        //smike = new CardViewFactory.Smike(context, this, 0, 0, 0,decidedContent.getId());
        //details = new CardViewFactory.Details(context, this, 0,0,0,decidedContent.getId());
		
		this.setBackgroundColor(Color.TRANSPARENT);
		this.setId(Resource.id++);
	}
	
	public RelativeLayout decideContent()
	{
		EventView event = new EventView(context);
		event.build();
		return event;
	}

    public void refresh(DataObject data)
    {
        ((EventView)decidedContent).refresh(data);
    }
}

package com.augmentify.GUIModule.Card.Content;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.augmentify.DataModule.Objects.Card.Say;
import com.augmentify.DataModule.Objects.DataObject;
import com.augmentify.GUIModule.Card.CardViewFactory;
import com.augmentify.GUIModule.Utils.Utils;
import com.augmentify.Resource;

public class SayView extends RelativeLayout implements Content
{
	Context context;
    CardViewFactory cardViewFactory;
	public SayView(Context context)
	{
		super(context);
		this.context = context;
        cardViewFactory = new CardViewFactory(context, this);
		SayResource.setContext(context);
	}

    public CardViewFactory.Title title;
    public CardViewFactory.Date date;
    public CardViewFactory.HorizontalSeparator horizontalSeparator;
	public TextView say;

	LayoutParams params;

	public void build()
	{
        title = new CardViewFactory.Title(context, this, 0, 0, 0, 0);
        date = new CardViewFactory.Date(context, this, 0, 0, 0, title.getId());
        horizontalSeparator = new CardViewFactory.HorizontalSeparator(context, this, date.getId());

		say = new TextView(context);
		say.setText(Utils.textClipper("Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat", 64, 3));
		say.setTextSize(TypedValue.COMPLEX_UNIT_SP, SayResource.Say.fontSize);
		say.setTextColor(Color.parseColor(SayResource.Say.fontColor));
		say.setPadding(SayResource.Say.Padding.left,
				SayResource.Say.Padding.top,
				SayResource.Say.Padding.right,
				SayResource.Say.Padding.bottom);
		say.setId(Resource.id++);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.BELOW, horizontalSeparator.getId());
		this.addView(say, params);

		this.setId(Resource.id++);
	}

    @Override
    public void refresh(DataObject dataObject)
    {
        Say sayData = (Say)dataObject;
        say.setText(sayData.say);
    }

    @Override
	public Content.CardType getCardType()
	{
		return Content.CardType.SAY;
	}
}

package com.augmentify.GUIModule.Card.Fragments.Details.Content;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.augmentify.DataModule.Objects.Card.Event;
import com.augmentify.DataModule.Objects.Card.QRCode;
import com.augmentify.DataModule.Objects.DataObject;
import com.augmentify.GUIModule.Card.Content.EventViewFactory;
import com.augmentify.GUIModule.Card.Fragments.FragmentController;
import com.augmentify.GUIModule.Utils.DisplayUtils;
import com.augmentify.R;
import com.augmentify.Resource;

public class EventView extends RelativeLayout implements Content
{
	Context context;
    EventViewFactory eventViewFactory;
	public EventView(Context context)
	{
		super(context);
		this.context = context;
        eventViewFactory = new EventViewFactory(context, this);
		EventViewResource.context = context;
	}

	ImageView displayPicture;
	RelativeLayout joinLayout;
	ImageView joinImage;
	TextView join;
    public EventViewFactory.Time eventTime;
    public EventViewFactory.Venue eventVenue;
    public EventViewFactory.Description eventDescription;

	static LayoutParams params;

	@Override
	public void build()
	{
		displayPicture = new ImageView(context);
		displayPicture.setBackgroundResource(R.drawable.card_shadow);
		displayPicture.setPadding(EventViewResource.DisplayPicture.Padding.left,
				EventViewResource.DisplayPicture.Padding.top,
				EventViewResource.DisplayPicture.Padding.right,
				EventViewResource.DisplayPicture.Padding.bottom);
		displayPicture.setId(Resource.id++);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		params.setMargins(EventViewResource.DisplayPicture.Margin.left,
				EventViewResource.DisplayPicture.Margin.top,
				EventViewResource.DisplayPicture.Margin.right,
				EventViewResource.DisplayPicture.Margin.bottom);
		params.height = EventViewResource.DisplayPicture.height;
		params.width = EventViewResource.DisplayPicture.width;
		this.addView(displayPicture, params);

		joinLayout = new RelativeLayout(context);
		joinLayout.setId(Resource.id++);

		joinImage = new ImageView(context);
		joinImage.setImageResource(R.drawable.card_smikes);
		joinImage.setPadding(EventViewResource.Join.Image.Padding.left,
				EventViewResource.Join.Image.Padding.top,
				EventViewResource.Join.Image.Padding.right,
				EventViewResource.Join.Image.Padding.bottom);
		joinImage.setId(Resource.id++);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		params.addRule(RelativeLayout.ALIGN_LEFT);
		params.height = EventViewResource.Join.Image.height;
		params.width = EventViewResource.Join.Image.width;
		joinLayout.addView(joinImage, params);

		join = new TextView(context);
		join.setText("Join   ");
		join.setTextSize(TypedValue.COMPLEX_UNIT_SP,
				EventViewResource.Join.fontSize);
		join.setTextColor(Color.parseColor(EventViewResource.Join.fontColor));
		join.setPadding(EventViewResource.Join.Padding.left,
				EventViewResource.Join.Padding.top,
				EventViewResource.Join.Padding.right,
				EventViewResource.Join.Padding.bottom);
		join.setId(Resource.id++);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		params.addRule(RelativeLayout.ALIGN_RIGHT);
		params.addRule(RelativeLayout.RIGHT_OF, joinImage.getId());
		joinLayout.addView(join, params);

		joinLayout.setBackgroundResource(R.drawable.button);
		joinLayout
				.setOnTouchListener(new DisplayUtils.OnTouchButtonBackgroundSet());
		joinLayout.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				//FragmentController.show(FragmentController.Fragment.SHOW_QR_VIEW_FRAGMENT);
			}
		});
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.setMargins(EventViewResource.Join.Margin.left,
				EventViewResource.Join.Margin.top,
				EventViewResource.Join.Margin.right,
				EventViewResource.Join.Margin.bottom);
		params.addRule(RelativeLayout.RIGHT_OF, displayPicture.getId());
		this.addView(joinLayout, params);

        eventTime =  new EventViewFactory.Time(context, this, 0, 0, 0, displayPicture.getId());
        eventVenue = new EventViewFactory.Venue(context, this, 0, 0, 0, eventTime.getId());
        eventDescription = new EventViewFactory.Description(context, this, 0, 0, 0, eventVenue.getId());

		this.setId(Resource.id++);
	}

    @Override
    public void refresh(DataObject data)
    {
        final com.augmentify.DataModule.Objects.Card.Event event = (com.augmentify.DataModule.Objects.Card.Event)data;
        String time = "";
        if (event.time_from != null)
        {
            time = "From " + event.time_from;
        }
        if (event.time_to != null)
        {
            time = time + " To " + event.time_to;
        }
        eventTime.time.setText(time);
        eventDescription.description.setText(((Event) data).description);
        eventVenue.venue.setText(((Event) data).location);
        if(((Event) data).has_joined)
        {
            join.setText("Joined");
            joinLayout.setOnClickListener(new OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    QRCode qrCode = new QRCode(context)
                    {
                        @Override
                        public void onStatusChange(Status status)
                        {
                            switch (status)
                            {
                                case READ_OK:
                                {
                                    FragmentController.show(FragmentController.Fragment.SHOW_QR_VIEW_FRAGMENT, this);
                                }
                            }
                        }
                    };
                    qrCode.setReadRequestParams(QRCode.RESOURCE_TYPE.QR, event.qr);
                    qrCode.read();
                }
            });
        }
        else
        {
            joinLayout.setOnClickListener
                    (
                    new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Event.Join eventJoin = new Event.Join(context)
                            {
                                @Override
                                public void onStatusChange(Status status)
                                {
                                    switch (status)
                                    {
                                        case CREATE_OK:
                                        {
                                            join.setText("Show QR");
                                        }
                                    }
                                }
                            };
                            eventJoin.setCreateRequestParams(Event.Join.RESOURCE_TYPE.EVENT_JOIN_DETAIL, event.id);
                            eventJoin.create();
                        }
                    }
            );
        }
    }


	@Override
	public CardType getCardType()
	{
		return CardType.EVENT;
	}

}

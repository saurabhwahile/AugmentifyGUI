package com.augmentify.GUIModule.Card.Content;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import com.augmentify.DataModule.Objects.Card.Comment;
import com.augmentify.DataModule.Objects.Card.Event;
import com.augmentify.DataModule.Objects.DataObject;
import com.augmentify.GUIModule.Card.CardViewFactory;
import com.augmentify.GUIModule.Card.Fragments.FragmentController;
import com.augmentify.Resource;

public class EventView extends RelativeLayout implements Content
{
	Context context;
    CardViewFactory cardViewFactory;
    EventViewFactory eventViewFactory;
	public EventView(Context context)
	{
		super(context);
		this.context = context;
		EventViewResource.setContext(context);
        cardViewFactory = new CardViewFactory(context, this);
        eventViewFactory = new EventViewFactory(context, this);
	}

    public CardViewFactory.Title title;
    public CardViewFactory.Date date;
    public CardViewFactory.HorizontalSeparator horizontalSeparator;
    public EventViewFactory.Time eventTime;
    public EventViewFactory.Venue eventVenue;
    public EventViewFactory.Description eventDescription;
    public CardViewFactory.Smike smike;
    public CardViewFactory.Comment comment;
    public CardViewFactory.Details details;
    public CardViewFactory.HorizontalSeparator horizontalSeparator2;

	static LayoutParams params;

    @Override
	public void build()
	{
        title =  new CardViewFactory.Title(context, this,0, 0, 0, 0);
        date = new CardViewFactory.Date(context, this, 0, 0, 0, title.getId());
        horizontalSeparator = new CardViewFactory.HorizontalSeparator(context, this, date.getId());
        eventTime = new EventViewFactory.Time(context, this, 0, 0, 0, horizontalSeparator.getId());
        eventVenue = new EventViewFactory.Venue(context, this, 0, 0, 0, eventTime.getId());
		eventDescription = new EventViewFactory.Description(context, this, 0, 0, 0, eventVenue.getId());
        horizontalSeparator2 = new CardViewFactory.HorizontalSeparator(context, this, eventDescription.getId());
        smike = new CardViewFactory.Smike(context, this, 0, 0, 0, horizontalSeparator2.getId());
        comment = new CardViewFactory.Comment(context, this, 0, 0, smike.getId(), horizontalSeparator2.getId());
        details = new CardViewFactory.Details(context, this, 0, 0, 0, horizontalSeparator2.getId());

		this.setPadding(EventViewResource.Padding.left,
				EventViewResource.Padding.top,
				EventViewResource.Padding.right,
				EventViewResource.Padding.bottom);
		this.setId(Resource.id++);
	}

    @Override
    public void refresh(DataObject data)
    {
        final Event event = (Event)data;
        String time = "";
        if (event.time_from != null)
        {
            time = "From " + event.time_from;
        }
        if (event.time_to != null)
        {
            time = time + " To " + event.time_to;
        }

        comment.setOnClickListener(
                new OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        Comment comment = new Comment(context)
                        {
                            @Override
                            public void onStatusChange(Status status)
                            {
                                switch (status)
                                {
                                    case READ_OK:
                                    {
                                        FragmentController.show(FragmentController.Fragment.COMMENTS_VIEW_FRAGMENT, this);
                                    }
                                    break;
                                }
                            }
                        };
                        comment.setReadRequestParams(Comment.RESOURCE_TYPE.EVENT_COMMENTS, event.id);
                        comment.read();
                    }
                }
        );

        details.setOnClickListener(
                new OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        Event eventDetails = new Event(context)
                        {
                            @Override
                            public void onStatusChange(Status status)
                            {
                                switch (status)
                                {
                                    case READ_OK:
                                    {
                                        FragmentController.show(FragmentController.Fragment.DETAILS_VIEW_FRAGMENT, this);
                                    }
                                    break;
                                }
                            }
                        };

                        eventDetails.setReadRequestParams(Event.RESOURCE_TYPE.EVENT_DETAIL, event.id);
                        eventDetails.read();
                    }
                }
        );

        eventTime.time.setText(time);
        eventDescription.description.setText(((Event) data).description);
        eventVenue.venue.setText(((Event) data).location);
        title.setText(event.name);
    }

	@Override
	public Content.CardType getCardType()
	{
		return Content.CardType.EVENT;
	}
}

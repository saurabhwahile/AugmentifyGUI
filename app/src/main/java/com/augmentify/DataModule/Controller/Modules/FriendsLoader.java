package com.augmentify.DataModule.Controller.Modules;

import android.content.Context;
import android.util.Log;

import com.augmentify.DataModule.Controller.Controller;
import com.augmentify.DataModule.Objects.Card.Event;
import com.augmentify.DataModule.Objects.DataObject.Status;
import com.augmentify.DataModule.Objects.Feed.FriendsFeed;
import com.augmentify.Debug;
import com.augmentify.GUIModule.Card.Content.Content;

import java.util.ArrayList;
import java.util.List;

public class FriendsLoader implements Runnable
{
	Context context;
	public FriendsLoader(Context context)
	{
		this.context = context;
	}

    FriendsFeed friendsFeed;
	@Override
	public void run()
	{
        friendsFeed = new FriendsFeed(context)
        {
            @Override
            public void onStatusChange(Status status)
            {
                FriendsLoader.this.changeStatus(status);
            }
        };
        friendsFeed.read();
	}


	public void changeStatus(Status status)
	{
		onStatusChange(status);
	}
	
	public void onStatusChange(Status status)
	{
		switch(status)
        {
            case READ_OK:
            {
                remove();
                build();

                try
                {
                    Controller.viewRootHandle.tabsController.friendsFragment.friendsLayout.cardViewScrollView.onRefreshOver();
                }
                catch (Exception e)
                {
                    Log.d(Debug.TAG.GUI, "Nothing To Clear");
                }
            }
            break;
            case ERROR:
            {
                Controller.viewRootHandle.tabsController.friendsFragment.friendsLayout.cardViewScrollView.onRefreshOver();
            }
        }
	}

    List<Event> events;
	public void build()
	{
        events = new ArrayList<Event>();
        for (FriendsFeed feed:friendsFeed.friendsFeedWrapper.objects)
        {
            Event event = new Event(context)
            {
                @Override
                public void onStatusChange(Status status)
                {
                    switch (status)
                    {
                        case READ_OK:
                        {
                            (Controller.viewRootHandle.tabsController.friendsFragment.friendsLayout.cardViewScrollView.cardViewLayout.addCard(Content.CardType.EVENT).decidedContent).refresh(this);
                        }
                        break;
                        case ERROR:
                        {

                        }
                        break;
                    }
                }
            };
            event.setReadRequestParams(Event.RESOURCE_TYPE.EVENT_DETAIL, feed.feed.sources.event);
            if(events.contains(event))
            {
                event = null;
                continue;
            }
            event.read();
            events.add(event);
        }
	}

	public void remove()
	{
        try
        {
            Controller.viewRootHandle.tabsController.friendsFragment.friendsLayout.cardViewScrollView.cardViewLayout.cardViewCollection
                    .removeAllViews();
            events.clear();
        }
        catch (Exception e)
        {
            Log.d(Debug.TAG.GUI, "Nothing To Clear");
        }
	}
}

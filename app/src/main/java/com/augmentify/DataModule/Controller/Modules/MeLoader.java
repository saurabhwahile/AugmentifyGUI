package com.augmentify.DataModule.Controller.Modules;

import android.content.Context;
import android.util.Log;

import com.augmentify.DataModule.Controller.Controller;
import com.augmentify.DataModule.Objects.Card.Event;
import com.augmentify.DataModule.Objects.DataObject;
import com.augmentify.DataModule.Objects.Feed.NotificationFeed;
import com.augmentify.DataModule.Objects.Feed.RequestFeed;
import com.augmentify.Debug;
import com.augmentify.GUIModule.Card.Content.Content;

/**
 * Created by Saurabh on 08/02/2015.
 */
public class MeLoader implements Runnable
{
    public static enum ME_CONTENT_TYPE{
        NOTIFICATION,
        REQUEST,
        MY_EVENTS,
        JOINED_EVENTS
    }

    Context context;
    public MeLoader(Context context)
    {
        this.context = context;
        this.type = ME_CONTENT_TYPE.NOTIFICATION;
    }


    ME_CONTENT_TYPE type;
    public void setType(ME_CONTENT_TYPE type)
    {
        this.type = type;
    }

    NotificationFeed notificationFeed;
    RequestFeed requestFeed;
    Event events;
    @Override
    public void run()
    {
        switch(type)
        {
            case NOTIFICATION:
            {
                notificationFeed = new NotificationFeed(context)
                {
                    @Override
                    public void onStatusChange(Status status)
                    {
                        MeLoader.this.changeStatus(status);
                    }
                };
                notificationFeed.read();
            }
            break;
            case REQUEST:
            {
                requestFeed = new RequestFeed(context)
                {
                    @Override
                    public void onStatusChange(Status status)
                    {
                        MeLoader.this.changeStatus(status);
                    }
                };
                requestFeed.read();
            }
            break;
            case MY_EVENTS:
            {
                events = new Event(context)
                {
                    @Override
                    public void onStatusChange(Status status)
                    {
                        MeLoader.this.changeStatus(status);
                    }
                };
                events.setReadRequestParams(Event.RESOURCE_TYPE.MY_EVENTS_FEED);
                events.read();

            }
            break;
            case JOINED_EVENTS:
            {
                events = new Event(context)
                {
                    @Override
                    public void onStatusChange(Status status)
                    {
                        MeLoader.this.changeStatus(status);
                    }
                };
                events.setReadRequestParams(Event.RESOURCE_TYPE.JOINED_EVENTS_FEED);
                events.read();
            }
            break;
        }
    }

    public void changeStatus(DataObject.Status status)
    {
        onStatusChange(status);
    }

    public void onStatusChange(DataObject.Status status)
    {
        switch(status)
        {
            case READ_OK:
            {
                remove();
                build();

                try
                {
                    Controller.viewRootHandle.tabsController.meFragment.meLayout.cardViewScrollView.onRefreshOver();
                }
                catch (Exception e)
                {
                    Log.d(Debug.TAG.GUI, "Nothing To Clear");
                }
            }
            break;
            case ERROR:
            {
                Controller.viewRootHandle.tabsController.meFragment.meLayout.cardViewScrollView.onRefreshOver();
            }
        }
    }

    public void build()
    {
        switch (type)
        {
            case NOTIFICATION:
            {
                for(NotificationFeed notification: notificationFeed.notificationWrapper.notification_set)
                {
                    (Controller.viewRootHandle.tabsController.meFragment.meLayout.cardViewScrollView.cardViewLayout.addCard(Content.CardType.NOTIFICATION).decidedContent).refresh(notification);
                }
            }
            break;
            case REQUEST:
            {
                for(RequestFeed request: requestFeed.requestWrapper.request_set)
                {
                    (Controller.viewRootHandle.tabsController.meFragment.meLayout.cardViewScrollView.cardViewLayout.addCard(Content.CardType.REQUEST).decidedContent).refresh(request);
                }
            }
            break;
            case MY_EVENTS:
            case JOINED_EVENTS:
            {
                   for(Event event: events.eventWrapper.objects)
                   {
                       (Controller.viewRootHandle.tabsController.meFragment.meLayout.cardViewScrollView.cardViewLayout.addCard(Content.CardType.EVENT).decidedContent).refresh(event);
                   }
            }
            break;
        }
    }

    public void remove()
    {
        try
        {
            Controller.viewRootHandle.tabsController.meFragment.meLayout.cardViewScrollView.cardViewLayout.cardViewCollection
                    .removeAllViews();
        }
        catch (Exception e)
        {
            Log.d(Debug.TAG.GUI, "Nothing To Clear");
        }
    }
}

package com.augmentify.DataModule.Controller.Modules;

import android.content.Context;
import android.util.Log;

import com.augmentify.DataModule.Controller.Controller;
import com.augmentify.DataModule.Objects.Card.Event;
import com.augmentify.DataModule.Objects.DataObject;
import com.augmentify.DataModule.Objects.Feed.ExploreFeed;
import com.augmentify.DataModule.Objects.User.Profile;
import com.augmentify.DataModule.Urls;
import com.augmentify.Debug;
import com.augmentify.GUIModule.Card.Content.Content;

public class ExploreLoader implements Runnable
{
    Context context;
    public ExploreLoader(Context context)
    {
        this.context = context;
    }

    String key = "";
    public void setKey(String key)
    {
        this.key = key;
    }

    ExploreFeed exploreFeed;
    @Override
    public void run()
    {
        exploreFeed = new ExploreFeed(context)
        {
            @Override
            public void onStatusChange(Status status)
            {
                ExploreLoader.this.changeStatus(status);
            }
        };
        exploreFeed.setReadRequestParams(key);
        exploreFeed.read();
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
                    Controller.viewRootHandle.tabsController.exploreFragment.exploreLayout.cardViewScrollView.onRefreshOver();
                }
                catch (Exception e)
                {
                    Log.d(Debug.TAG.GUI, "Nothing To Clear");
                }
            }
            break;
            case ERROR:
            {
                Controller.viewRootHandle.tabsController.exploreFragment.exploreLayout.cardViewScrollView.onRefreshOver();
            }
        }
    }

    public void build()
    {
        for (ExploreFeed explore: exploreFeed.exploreWrapper.search_set)
        {
            if(explore.result.label.compareTo(Urls.REQUEST_KEY.EVENT) == 0)
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
                                (Controller.viewRootHandle.tabsController.exploreFragment.exploreLayout.cardViewScrollView.cardViewLayout.addCard(Content.CardType.EVENT).decidedContent).refresh(this);
                            }
                            break;
                            case ERROR:
                            {

                            }
                            break;
                        }
                    }
                };
                event.setReadRequestParams(Event.RESOURCE_TYPE.EVENT_DETAIL, explore.result.id);
                event.read();
            }
            else
            if(explore.result.label.compareTo("user")==0)
            {
                Profile profile = new Profile(context)
                {
                    @Override
                    public void onStatusChange(Status status)
                    {
                        switch (status)
                        {
                            case READ_OK:
                            {
                                (Controller.viewRootHandle.tabsController.exploreFragment.exploreLayout.cardViewScrollView.cardViewLayout.addCard(Content.CardType.PROFILE).decidedContent).refresh(this);
                            }
                            break;
                            case ERROR:
                            {

                            }
                            break;
                        }
                    }
                };
                profile.setReadRequestParams(Profile.RESOURCE_TYPE.PROFILE_DETAIL, Integer.parseInt(explore.result.id));
                profile.read();
            }
        }
    }

    public void remove()
    {
        try
        {
            Controller.viewRootHandle.tabsController.exploreFragment.exploreLayout.cardViewScrollView.cardViewLayout.cardViewCollection
                    .removeAllViews();
        }
        catch (Exception e)
        {
            Log.d(Debug.TAG.GUI, "Nothing To Clear");
        }
    }
}

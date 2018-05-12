package com.augmentify.GUIModule.Card.Content;

import android.content.Context;
import android.widget.RelativeLayout;

import com.augmentify.DataModule.Objects.DataObject;
import com.augmentify.DataModule.Objects.Feed.NotificationFeed;
import com.augmentify.GUIModule.Card.CardViewFactory;
import com.augmentify.Resource;

/**
 * Created by Saurabh on 06/02/2015.
 */
public class NotificationView extends RelativeLayout implements Content
{
    Context context;
    CardViewFactory cardViewFactory;
    public NotificationView(Context context)
    {
        super(context);
        this.context = context;
        cardViewFactory = new CardViewFactory(context, this);
        NotificationViewResource.setContext(context);
    }

    public CardViewFactory.Title title;

    static LayoutParams params;

    @Override
    public void build()
    {
        title =  new CardViewFactory.Title(context, this, 0, 0, 0, 0);

        this.setPadding(NotificationViewResource.Padding.left,
                NotificationViewResource.Padding.top,
                NotificationViewResource.Padding.right,
                NotificationViewResource.Padding.bottom);
        this.setId(Resource.id++);
    }

    @Override
    public void refresh(DataObject data)
    {
        NotificationFeed notification = (NotificationFeed)data;
        String text = "";

        this.title.setText(notification.notification.type);
    }

    @Override
    public Content.CardType getCardType()
    {
        return CardType.NOTIFICATION;
    }
}

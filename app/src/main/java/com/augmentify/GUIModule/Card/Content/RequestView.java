package com.augmentify.GUIModule.Card.Content;

import android.content.Context;
import android.widget.RelativeLayout;

import com.augmentify.DataModule.Objects.DataObject;
import com.augmentify.DataModule.Objects.Feed.RequestFeed;
import com.augmentify.GUIModule.Card.CardViewFactory;
import com.augmentify.Resource;

/**
 * Created by Saurabh on 06/02/2015.
 */
public class RequestView extends RelativeLayout implements Content
{
    Context context;
    CardViewFactory cardViewFactory;
    public RequestView(Context context)
    {
        super(context);
        this.context = context;
        cardViewFactory = new CardViewFactory(context, this);
        RequestViewResource.setContext(context);
    }

    public CardViewFactory.Title title;
    public CardViewFactory.Button yes;
    public CardViewFactory.Button no;

    static LayoutParams params;

    @Override
    public void build()
    {
        title =  new CardViewFactory.Title(context, this,0, 0, 0, 0);
        no = new CardViewFactory.Button(context, this, 0, 0, 0, title.getId());
        params = (LayoutParams)no.getLayoutParams();
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        no.setLayoutParams(params);
        yes = new CardViewFactory.Button(context, this, no.getId(), 0, 0, title.getId());

        this.setPadding(EventViewResource.Padding.left,
                EventViewResource.Padding.top,
                EventViewResource.Padding.right,
                EventViewResource.Padding.bottom);
        this.setId(Resource.id++);
    }

    @Override
    public void refresh(DataObject data)
    {
        RequestFeed requestData = (RequestFeed)data;
        title.setText(requestData.request.type);
    }

    @Override
    public Content.CardType getCardType()
    {
        return CardType.REQUEST;
    }
}

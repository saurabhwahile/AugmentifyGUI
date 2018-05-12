package com.augmentify.GUIModule.Card.Content;

import android.content.Context;
import android.widget.RelativeLayout;

import com.augmentify.DataModule.Objects.DataObject;
import com.augmentify.DataModule.Objects.User.Profile;
import com.augmentify.GUIModule.Card.CardViewFactory;

/**
 * Created by Saurabh on 17/08/2014.
 */
public class ProfileView extends RelativeLayout implements Content
{
    Context context;
    CardViewFactory cardViewFactory;
    ProfileViewFactory profileViewFactory;
    public ProfileView(Context context)
    {
        super(context);
        this.context = context;
        ProfileViewResource.setContext(context);
        cardViewFactory = new CardViewFactory(context, this);
        profileViewFactory = new ProfileViewFactory(context, this);

    }

    public CardViewFactory.Title title;
    public CardViewFactory.Date date;
    public CardViewFactory.HorizontalSeparator horizontalSeparator;
    public ProfileViewFactory.AddFriend addFriend;
    @Override
    public void build()
    {
        title = new CardViewFactory.Title(context, this, 0, 0, 0, 0);
        horizontalSeparator = new CardViewFactory.HorizontalSeparator(context, this, title.getId());

        addFriend = new ProfileViewFactory.AddFriend(context, this, 0, 0, 0, horizontalSeparator.getId());
    }

    @Override
    public void refresh(DataObject data)
    {
        Profile profileData = (Profile)data;
        this.title.setText(profileData.user.first_name);
    }

    @Override
    public CardType getCardType()
    {
        return CardType.PROFILE;
    }
}

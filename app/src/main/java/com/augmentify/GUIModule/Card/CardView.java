package com.augmentify.GUIModule.Card;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import com.augmentify.GUIModule.Card.Content.Content;
import com.augmentify.GUIModule.Card.Content.Content.CardType;
import com.augmentify.GUIModule.Card.Content.EventView;
import com.augmentify.GUIModule.Card.Content.NotificationView;
import com.augmentify.GUIModule.Card.Content.ProfileView;
import com.augmentify.GUIModule.Card.Content.RequestView;
import com.augmentify.GUIModule.Card.Content.SayView;
import com.augmentify.R;
import com.augmentify.Resource;

public class CardView extends RelativeLayout
{
	static Context context;
	public CardView(Context context)
	{
		super(context);
		CardView.context = context;
	}

	public Content decidedContent;

	static LayoutParams params;

	public void build(CardType cardType)
	{
		decidedContent = (Content) decideContent(cardType);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		this.addView((View) decidedContent, params);
		
		this.setBackgroundResource(R.drawable.card_shadow);
		this.setId(Resource.id++);
	}
	
	protected static RelativeLayout decideContent(CardType cardType)
	{
		switch(cardType)
		{
			case EVENT:
			{
				EventView cardEventView = new EventView(context);
				cardEventView.build();
				return cardEventView;
			}
			case SAY:
			{
				SayView cardSayView = new SayView(context);
				cardSayView.build();
				return cardSayView;
			}
            case PROFILE:
            {
                ProfileView profileView = new ProfileView(context);
                profileView.build();
                return profileView;
            }
            case REQUEST:
            {
                RequestView requestView = new RequestView(context);
                requestView.build();
                return requestView;
            }
            case NOTIFICATION:
            {
                NotificationView notificationView = new NotificationView(context);
                notificationView.build();
                return notificationView;
            }
			default:
			{
				EventView cardEventView = new EventView(context);
				cardEventView.build();
				return cardEventView;
			}
		}
	}
}

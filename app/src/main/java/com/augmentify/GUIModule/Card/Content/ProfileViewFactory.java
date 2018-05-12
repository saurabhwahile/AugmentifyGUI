package com.augmentify.GUIModule.Card.Content;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.augmentify.GUIModule.Card.CardViewResource;
import com.augmentify.GUIModule.Utils.DisplayUtils;
import com.augmentify.R;
import com.augmentify.Resource;

/**
 * Created by Saurabh on 17/08/2014.
 */
public class ProfileViewFactory
{
    Context context;
    RelativeLayout addTo;
    public ProfileViewFactory(Context context, RelativeLayout addTo)
    {
        this.context = context;
        this.addTo = addTo;
    }

    static RelativeLayout.LayoutParams params;

    public static class AddFriend extends RelativeLayout
    {
        public RelativeLayout addFriendLayout;
        public ImageView addFriendImage;
        public TextView addFriend;
        public AddFriend(Context context, RelativeLayout addTo, int leftOf, int above, int rightOf, int below)
        {
            super(context);
            addFriendImage = new ImageView(context);
            addFriendImage.setImageResource(R.drawable.card_smikes);
            addFriendImage.setPadding(CardViewResource.Details.Image.Padding.left,
                    CardViewResource.Details.Image.Padding.top,
                    CardViewResource.Details.Image.Padding.right,
                    CardViewResource.Details.Image.Padding.bottom);
            addFriendImage.setId(Resource.id++);
            params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.CENTER_VERTICAL);
            params.height = CardViewResource.Details.Image.height;
            params.width = CardViewResource.Details.Image.width;
            this.addView(addFriendImage, params);

            addFriend = new TextView(context);
            addFriend.setText("Add Friend");
            addFriend.setId(Resource.id++);
            params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.CENTER_VERTICAL);
            params.addRule(RelativeLayout.RIGHT_OF, addFriendImage.getId());
            this.addView(addFriend, params);

            this.setBackgroundResource(R.drawable.button);
            this.setOnTouchListener(new DisplayUtils.OnTouchButtonBackgroundSet());
            params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.LEFT_OF, leftOf);
            params.addRule(RelativeLayout.ABOVE, above);
            params.addRule(RelativeLayout.RIGHT_OF, rightOf);
            params.addRule(RelativeLayout.BELOW, below);
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

            this.setId(Resource.id++);
            addTo.addView(this, params);
        }
    }
}

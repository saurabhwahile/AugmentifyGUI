package com.augmentify.GUIModule.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.augmentify.GUIModule.Utils.DisplayUtils;
import com.augmentify.R;
import com.augmentify.Resource;

/**
 * Created by Saurabh on 07/02/2015.
 */
public class FragmentViewFactory
{
    static RelativeLayout.LayoutParams params;

    public static class DialogMenuButton extends RelativeLayout
    {
        public ImageView dialogMenuButtonImage;
        public TextView dialogMenuButton;

        public DialogMenuButton(Context context, RelativeLayout addTo, int leftOf, int above, int rightOf, int below)
        {
            super(context);
            FragmentViewFactoryResource.context = context;

            dialogMenuButtonImage = new ImageView(context);
            dialogMenuButtonImage.setImageResource(R.drawable.action_add_event);
            dialogMenuButtonImage.setId(Resource.id++);
            params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.CENTER_HORIZONTAL);
            params.addRule(RelativeLayout.CENTER_VERTICAL);
            this.addView(dialogMenuButtonImage, params);

            dialogMenuButton = new TextView(context);
            dialogMenuButton.setText(FragmentViewFactoryResource.DialogMenuButton.text);
            dialogMenuButton.setTextSize(TypedValue.COMPLEX_UNIT_SP,
                    FragmentViewFactoryResource.DialogMenuButton.fontSize);
            dialogMenuButton.setTextColor(Color.parseColor(FragmentViewFactoryResource.DialogMenuButton.fontColor));
            dialogMenuButton.setId(Resource.id++);
            params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.CENTER_HORIZONTAL);
            params.addRule(RelativeLayout.BELOW, dialogMenuButtonImage.getId());
            this.addView(dialogMenuButton, params);
            this.setOnTouchListener(new DisplayUtils.OnTouchButtonBackgroundSet());
            this.setBackgroundResource(R.drawable.button);
            this.setId(Resource.id++);
            params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.LEFT_OF, leftOf);
            params.addRule(RelativeLayout.ABOVE, above);
            params.addRule(RelativeLayout.RIGHT_OF, rightOf);
            params.addRule(RelativeLayout.BELOW, below);
            params.height = FragmentViewFactoryResource.DialogMenuButton.Layout.height;
            params.width = FragmentViewFactoryResource.DialogMenuButton.Layout.width;

            addTo.addView(this, params);
        }
    }
}

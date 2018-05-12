package com.augmentify.GUIModule.Card;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.augmentify.GUIModule.Utils.DisplayUtils;
import com.augmentify.R;
import com.augmentify.Resource;

/**
 * Created by Saurabh on 03/08/2014.
 */
public class CardViewFactory
{
    Context context;
    RelativeLayout addTo;
    public CardViewFactory(Context context, RelativeLayout addTo)
    {
        this.addTo = addTo;
        this.context = context;
    }

    static RelativeLayout.LayoutParams params;

    public static class Title extends TextView
    {
        public Title(Context context, RelativeLayout addTo, int leftOf, int above, int rightOf, int below)
        {
            super(context);
            this.setTextSize(TypedValue.COMPLEX_UNIT_SP, CardViewResource.Title.fontSize);
            this.setText("Title");
            this.setTextColor(Color.parseColor(CardViewResource.Title.fontColor));
            this.setPadding(CardViewResource.Title.Padding.left,
                    CardViewResource.Title.Padding.top,
                    CardViewResource.Title.Padding.right,
                    CardViewResource.Title.Padding.bottom);
            this.setId(Resource.id++);
            params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.LEFT_OF, leftOf);
            params.addRule(RelativeLayout.ABOVE, above);
            params.addRule(RelativeLayout.RIGHT_OF, rightOf);
            params.addRule(RelativeLayout.BELOW, below);
            addTo.addView(this, params);
        }
    }

    public static class Date extends TextView
    {
        public Date(Context context, RelativeLayout addTo, int leftOf, int above, int rightOf, int below)
        {
            super(context);
            this.setTextSize(TypedValue.COMPLEX_UNIT_SP, CardViewResource.Date.fontSize);
            this.setText("This is this");
            this.setTextColor(Color.parseColor(CardViewResource.Date.fontColor));
            this.setPadding(CardViewResource.Date.Padding.left,
                    CardViewResource.Date.Padding.top,
                    CardViewResource.Date.Padding.right,
                    CardViewResource.Date.Padding.bottom);
            this.setId(Resource.id++);
            params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.LEFT_OF, leftOf);
            params.addRule(RelativeLayout.ABOVE, above);
            params.addRule(RelativeLayout.RIGHT_OF, rightOf);
            params.addRule(RelativeLayout.BELOW, below);
            addTo.addView(this, params);
        }
    }

    public static class Smike extends RelativeLayout
    {
        public ImageView smikeImage;
        public TextView smike;
        public Smike(Context context, RelativeLayout addTo, int leftOf, int above, int rightOf, int below)
        {
            super(context);
            smikeImage = new ImageView(context);
            smikeImage.setImageResource(R.drawable.card_smikes);
            smikeImage.setPadding(CardViewResource.SmileyLike.Image.Padding.left,
                    CardViewResource.SmileyLike.Image.Padding.top,
                    CardViewResource.SmileyLike.Image.Padding.right,
                    CardViewResource.SmileyLike.Image.Padding.bottom);
            smikeImage.setId(Resource.id++);
            params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.CENTER_VERTICAL);
            params.addRule(RelativeLayout.ALIGN_LEFT);
            params.height = CardViewResource.SmileyLike.Image.height;
            params.width = CardViewResource.SmileyLike.Image.width;
            this.addView(smikeImage, params);

            smike = new TextView(context);
            smike.setText("..");
            smike.setTextSize(TypedValue.COMPLEX_UNIT_SP, CardViewResource.SmileyLike.fontSize);
            smike.setTextColor(Color
                    .parseColor(CardViewResource.SmileyLike.fontColor));
            smike.setPadding(CardViewResource.SmileyLike.Padding.left,
                    CardViewResource.SmileyLike.Padding.top,
                    CardViewResource.SmileyLike.Padding.right,
                    CardViewResource.SmileyLike.Padding.bottom);
            smike.setId(Resource.id++);
            params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.CENTER_VERTICAL);
            params.addRule(RelativeLayout.ALIGN_RIGHT);
            params.addRule(RelativeLayout.RIGHT_OF, smikeImage.getId());
            this.addView(smike, params);

            this.setBackgroundResource(R.drawable.button);
            this.setOnTouchListener(new DisplayUtils.OnTouchButtonBackgroundSet());
            params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.LEFT_OF, leftOf);
            params.addRule(RelativeLayout.ABOVE, above);
            params.addRule(RelativeLayout.RIGHT_OF, rightOf);
            params.addRule(RelativeLayout.BELOW, below);
            addTo.addView(this, params);

            this.setId(Resource.id++);
        }
    }

    public static class Comment extends RelativeLayout
    {
        public ImageView commentImage;
        public TextView comment;

        public Comment(Context context, RelativeLayout addTo, int leftOf, int above, int rightOf, int below)
        {
            super(context);
            commentImage = new ImageView(context);
            commentImage.setImageResource(R.drawable.card_comment);
            commentImage.setPadding(CardViewResource.Comment.Image.Padding.left,
                    CardViewResource.Comment.Image.Padding.top,
                    CardViewResource.Comment.Image.Padding.right,
                    CardViewResource.Comment.Image.Padding.bottom);
            commentImage.setId(Resource.id++);
            params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.CENTER_VERTICAL);
            params.addRule(RelativeLayout.ALIGN_LEFT);
            params.height = CardViewResource.Comment.Image.height;
            params.width = CardViewResource.Comment.Image.width;
            this.addView(commentImage, params);

            comment = new TextView(context);
            comment.setText("2");
            comment.setTextSize(TypedValue.COMPLEX_UNIT_SP, CardViewResource.Comment.fontSize);
            comment.setTextColor(Color
                    .parseColor(CardViewResource.Comment.fontColor));
            comment.setPadding(CardViewResource.Comment.Padding.left,
                    CardViewResource.Comment.Padding.top,
                    CardViewResource.Comment.Padding.right,
                    CardViewResource.Comment.Padding.bottom);
            comment.setId(Resource.id++);
            params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.CENTER_VERTICAL);
            params.addRule(RelativeLayout.ALIGN_RIGHT);
            params.addRule(RelativeLayout.RIGHT_OF, commentImage.getId());
            this.addView(comment, params);

            this.setBackgroundResource(R.drawable.button);
            this.setOnTouchListener(new DisplayUtils.OnTouchButtonBackgroundSet());
            params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.LEFT_OF, leftOf);
            params.addRule(RelativeLayout.ABOVE, above);
            params.addRule(RelativeLayout.RIGHT_OF, rightOf);
            params.addRule(RelativeLayout.BELOW, below);

            this.setId(Resource.id++);
            addTo.addView(this, params);
        }
    }

    public static class Details extends RelativeLayout
    {
        public RelativeLayout detailsLayout;
        public ImageView detailsImage;
        public Details(Context context, RelativeLayout addTo, int leftOf, int above, int rightOf, int below)
        {
            super(context);
            detailsImage = new ImageView(context);
            detailsImage.setImageResource(R.drawable.card_details);
            detailsImage.setPadding(CardViewResource.Details.Image.Padding.left,
                    CardViewResource.Details.Image.Padding.top,
                    CardViewResource.Details.Image.Padding.right,
                    CardViewResource.Details.Image.Padding.bottom);
            detailsImage.setId(Resource.id++);
            params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.CENTER_VERTICAL);
            params.addRule(RelativeLayout.CENTER_HORIZONTAL);
            params.height = CardViewResource.Details.Image.height;
            params.width = CardViewResource.Details.Image.width;
            this.addView(detailsImage, params);

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

    public static class HorizontalSeparator extends View
    {
        public HorizontalSeparator(Context context, RelativeLayout addTo, int below)
        {
            super(context);
            this.setMinimumHeight(3);
            this.setBackgroundResource(R.drawable.horizontal_separator);
            this.setPadding(
                    CardViewResource.HorizontalSeparator.Padding.left,
                    CardViewResource.HorizontalSeparator.Padding.top,
                    CardViewResource.HorizontalSeparator.Padding.right,
                    CardViewResource.HorizontalSeparator.Padding.bottom);
            this.setId(Resource.id++);
            params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.BELOW, below);
            addTo.addView(this, params);
        }
    }

    public static class Button extends RelativeLayout
    {
        public ImageView buttonImage;
        public Button(Context context, RelativeLayout addTo, int leftOf, int above, int rightOf, int below)
        {
            super(context);
            buttonImage = new ImageView(context);
            buttonImage.setImageResource(R.drawable.card_details);
            buttonImage.setPadding(CardViewResource.Details.Image.Padding.left,
                    CardViewResource.Details.Image.Padding.top,
                    CardViewResource.Details.Image.Padding.right,
                    CardViewResource.Details.Image.Padding.bottom);
            buttonImage.setId(Resource.id++);
            params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.CENTER_VERTICAL);
            params.addRule(RelativeLayout.CENTER_HORIZONTAL);
            params.height = CardViewResource.Details.Image.height;
            params.width = CardViewResource.Details.Image.width;
            this.addView(buttonImage, params);

            this.setBackgroundResource(R.drawable.button);
            this.setOnTouchListener(new DisplayUtils.OnTouchButtonBackgroundSet());
            params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.LEFT_OF, leftOf);
            params.addRule(RelativeLayout.ABOVE, above);
            params.addRule(RelativeLayout.RIGHT_OF, rightOf);
            params.addRule(RelativeLayout.BELOW, below);

            this.setId(Resource.id++);
            addTo.addView(this, params);
        }
    }
}

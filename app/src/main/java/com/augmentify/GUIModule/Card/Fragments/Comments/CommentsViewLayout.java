package com.augmentify.GUIModule.Card.Fragments.Comments;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.augmentify.DataModule.Objects.Card.Comment;
import com.augmentify.DataModule.Objects.DataObject;
import com.augmentify.DataModule.Objects.User.Profile;
import com.augmentify.GUIModule.Utils.DisplayUtils;
import com.augmentify.R;
import com.augmentify.Resource;

public class CommentsViewLayout extends RelativeLayout
{
	Context context;

	public CommentsViewLayout(Context context)
	{
		super(context);
		this.context = context;
		CommentsViewResource.context = context;
	}

	TextView title;
	ImageView titleImage;
	RelativeLayout titleLayout;
	View horizontalSeparator;
	RelativeLayout newCommentLayout;
	EditText newComment;
	RelativeLayout addNewCommentLayout;
	ImageView addNewCommentImage;

	CommentsView commentsView;
	ScrollView commentsViewScrollView;
	LinearLayout commentsViewLayout;

	static LayoutParams params;

	public void build()
	{
		titleLayout = new RelativeLayout(context);
		titleLayout.setId(Resource.id++);
		
		titleImage = new ImageView(context);
		titleImage.setId(Resource.id++);
		titleImage.setImageResource(R.drawable.card_smikes);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		params.height = CommentsViewResource.Layout.Title.Image.height;
		params.width = CommentsViewResource.Layout.Title.Image.width;
		titleLayout.addView(titleImage, params);
		
		title = new TextView(context);
		title.setId(Resource.id++);
		title.setText("Title");
		title.setTextSize(CommentsViewResource.Layout.Title.fontSize);
		title.setPadding(CommentsViewResource.Layout.Title.Padding.left,
				CommentsViewResource.Layout.Title.Padding.top,
				CommentsViewResource.Layout.Title.Padding.right,
				CommentsViewResource.Layout.Title.Padding.bottom);
		params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.RIGHT_OF, titleImage.getId());
		titleLayout.addView(title, params);
		
		params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		this.addView(titleLayout, params);
		
		horizontalSeparator = new View(context);
		horizontalSeparator.setId(Resource.id++);
		horizontalSeparator
				.setBackgroundResource(R.drawable.horizontal_separator);
		params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		params.height = CommentsViewResource.Layout.HorizontalSeparator.height;
		params.addRule(RelativeLayout.BELOW, titleLayout.getId());
		this.addView(horizontalSeparator, params);
		
		
		commentsViewLayout = new LinearLayout(context);
		commentsViewLayout.setOrientation(LinearLayout.VERTICAL);
		commentsViewLayout.setId(Resource.id++);

        //addComment from 0 to 25

		commentsViewScrollView = new ScrollView(context);
		commentsViewScrollView.setId(Resource.id++);
		commentsViewScrollView.addView(commentsViewLayout);
		commentsViewScrollView.post(new Runnable()
		{
			public void run()
			{
				commentsViewScrollView.fullScroll(View.FOCUS_DOWN);
			}
		});
		commentsViewScrollView.setVerticalScrollBarEnabled(false);
		commentsViewScrollView.setOverScrollMode(ScrollView.OVER_SCROLL_NEVER);
		params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		params.height = CommentsViewResource.Layout.height;
		params.addRule(RelativeLayout.BELOW, horizontalSeparator.getId());
		this.addView(commentsViewScrollView, params);

		newCommentLayout = new RelativeLayout(context);
		newCommentLayout.setId(Resource.id++);
		
		newComment = new EditText(context)
		{
			@Override
			protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
			{
				super.onMeasure(widthMeasureSpec, heightMeasureSpec);

				setMeasuredDimension(
						getMeasuredWidth() - addNewCommentLayout.getMeasuredWidth(),
						getMeasuredHeight());
			}
		};
		newComment.setId(Resource.id++);
		params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		newCommentLayout.addView(newComment, params);

		addNewCommentLayout = new RelativeLayout(context);
		addNewCommentLayout.setId(Resource.id++);

		addNewCommentImage = new ImageView(context);
		addNewCommentImage.setId(Resource.id++);
		addNewCommentImage.setImageResource(R.drawable.card_smikes);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.width = CommentsViewResource.Layout.AddComment.Image.width;
		params.height = CommentsViewResource.Layout.AddComment.Image.height;
		addNewCommentLayout.addView(addNewCommentImage, params);

		addNewCommentLayout.setBackgroundResource(R.drawable.button);
		addNewCommentLayout
				.setOnTouchListener(new DisplayUtils.OnTouchButtonBackgroundSet());
		addNewCommentLayout.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View view)
			{

			}
		});
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		newCommentLayout.addView(addNewCommentLayout, params);

		newCommentLayout.setPadding(CommentsViewResource.Layout.AddComment.Padding.left,
				CommentsViewResource.Layout.AddComment.Padding.top,
				CommentsViewResource.Layout.AddComment.Padding.right,
				CommentsViewResource.Layout.AddComment.Padding.bottom);
		
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.BELOW, commentsViewScrollView.getId());
		this.addView(newCommentLayout, params);
		
		this.setPadding(CommentsViewResource.Layout.Padding.left,
				CommentsViewResource.Layout.Padding.top,
				CommentsViewResource.Layout.Padding.right,
				CommentsViewResource.Layout.Padding.bottom);
		
		this.setId(Resource.id++);
	}

	public CommentsView addComment(String user, String comment)
	{
		commentsView = new CommentsView(context);
        commentsView.build();
        commentsView.user.setText(user);
        commentsView.comment.setText(comment);

		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		commentsViewLayout.addView(commentsView, params);

		return commentsView;
	}

    public void refresh(DataObject data)
    {
        Comment comment = (Comment)data;
        for(final Comment c: comment.comments.objects)
        {
            Profile profile = new Profile(context)
            {
                @Override
                public void onStatusChange(Status status)
                {
                    switch(status)
                    {
                        case READ_OK:
                        {
                            addComment(this.user.username, c.comment);
                        }
                        break;
                    }
                }
            };
            profile.setReadRequestParams(Profile.RESOURCE_TYPE.PROFILE_DETAIL, c.user);
            profile.read();
        }
    }
}

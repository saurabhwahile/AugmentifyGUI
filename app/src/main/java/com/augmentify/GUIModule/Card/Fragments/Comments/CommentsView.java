package com.augmentify.GUIModule.Card.Fragments.Comments;

import android.content.Context;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.augmentify.R;
import com.augmentify.Resource;

public class CommentsView extends RelativeLayout
{
	Context context;

	public CommentsView(Context context)
	{
		super(context);
		this.context = context;
	}

	RelativeLayout commentLayout;
	ImageView userImage;
	TextView user;
	TextView comment;

	LayoutParams params;

	public void build()
	{
		commentLayout = new RelativeLayout(context);

		userImage = new ImageView(context);
		userImage.setId(Resource.id++);
		userImage.setImageResource(R.drawable.card_comment);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.height = CommentsViewResource.User.Image.height;
		params.width = CommentsViewResource.User.Image.width;
		this.addView(userImage, params);

		user = new TextView(context);
		user.setId(Resource.id++);
		user.setText("User");
		user.setTextSize(TypedValue.COMPLEX_UNIT_SP,
				CommentsViewResource.User.fontSize);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.RIGHT_OF, userImage.getId());
		params.addRule(RelativeLayout.ALIGN_TOP, userImage.getId());
		this.addView(user, params);

		comment = new TextView(context);
		comment.setId(Resource.id++);
		comment.setText("This Is A Sample Comment And This Has Been Modified To Showcase To Show A Large Comment");
		comment.setTextSize(TypedValue.COMPLEX_UNIT_SP,
				CommentsViewResource.Comment.fontSize);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.BELOW, user.getId());
		params.addRule(RelativeLayout.RIGHT_OF, userImage.getId());
		this.addView(comment, params);

		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		this.setPadding(CommentsViewResource.Comment.Padding.left,
				CommentsViewResource.Comment.Padding.top,
				CommentsViewResource.Comment.Padding.right,
				CommentsViewResource.Comment.Padding.bottom);
		this.setLayoutParams(params);
		this.setId(Resource.id++);
	}
}

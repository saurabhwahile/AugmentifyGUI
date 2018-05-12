package com.augmentify.GUIModule.Action.Fragments.Add.Fragments.Minimap;

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

public class MinimapView extends RelativeLayout
{
	Context context;

	public MinimapView(Context context)
	{
		super(context);
		this.context = context;

		MinimapViewResource.context = context;
	}
	
	static LayoutParams params;

	TextView title;
	View horizontalSeparator;
	RelativeLayout getImageLayout;
	ImageView getImageImage;
	TextView getImage;
	
	ImageView selectedImage;
	
	RelativeLayout leftLayout;
	ImageView leftImage;
	TextView left;
	RelativeLayout forwardLayout;
	ImageView forwardImage;
	TextView forward;
	RelativeLayout rightLayout;
	ImageView rightImage;
	TextView right;
	RelativeLayout backwardLayout;
	ImageView backwardImage;
	TextView backward;
	RelativeLayout previousLayout;
	ImageView previousImage;
	TextView previous;
	RelativeLayout nextLayout;
	ImageView nextImage;
	TextView next;	
	
	public void build()
	{
		title = new TextView(context);
		title.setId(Resource.id++);
		title.setText(MinimapViewResource.Title.text);
		title.setTextSize(TypedValue.COMPLEX_UNIT_SP,
				MinimapViewResource.Title.fontSize);
		title.setTextColor(Color.parseColor(MinimapViewResource.Title.fontColor));
		params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		params.setMargins(MinimapViewResource.Title.Margin.left,
				MinimapViewResource.Title.Margin.top,
				MinimapViewResource.Title.Margin.right,
				MinimapViewResource.Title.Margin.bottom);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		this.addView(title, params);

		horizontalSeparator = new View(context);
		horizontalSeparator.setId(Resource.id++);
		horizontalSeparator
				.setBackgroundResource(R.drawable.horizontal_separator);
		params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		params.height = MinimapViewResource.HorizontalSeparator.height;
		params.addRule(RelativeLayout.BELOW, title.getId());
		this.addView(horizontalSeparator, params);
		
		getImageLayout = new RelativeLayout(context);
		getImageLayout.setId(Resource.id++);

		getImageImage = new ImageView(context);
		getImageImage.setId(Resource.id++);
		getImageImage.setImageResource(R.drawable.card_event_date);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.height = MinimapViewResource.GetImage.Image.height;
		params.width = MinimapViewResource.GetImage.Image.width;
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		getImageLayout.addView(getImageImage, params);

		getImage = new TextView(context);
		getImage.setId(Resource.id++);
		getImage.setText(MinimapViewResource.GetImage.text);
		getImage.setTextSize(TypedValue.COMPLEX_UNIT_SP,
				MinimapViewResource.GetImage.fontSize);
		getImage.setTextColor(Color.parseColor(MinimapViewResource.GetImage.fontColor));
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.RIGHT_OF, getImageImage.getId());
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		getImageLayout.addView(getImage, params);

		getImageLayout
				.setOnTouchListener(new DisplayUtils.OnTouchButtonBackgroundSet());
		getImageLayout.setBackgroundResource(R.drawable.button);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.addRule(RelativeLayout.BELOW, horizontalSeparator.getId());
		this.addView(getImageLayout, params);
		
		selectedImage = new ImageView(context);
		selectedImage.setId(Resource.id++);
		selectedImage.setImageResource(R.drawable.card_event_date);
		params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.addRule(RelativeLayout.BELOW, getImageLayout.getId());
		this.addView(selectedImage, params);
		
		forwardLayout = new RelativeLayout(context);
		forwardLayout.setId(Resource.id++);

		forwardImage = new ImageView(context);
		forwardImage.setId(Resource.id++);
		forwardImage.setImageResource(R.drawable.card_details);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.height = MinimapViewResource.Forward.Image.height;
		params.width = MinimapViewResource.Forward.Image.width;
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		forwardLayout.addView(forwardImage, params);

		forward = new TextView(context);
		forward.setId(Resource.id++);
		forward.setText(MinimapViewResource.Forward.text);
		forward.setTextSize(TypedValue.COMPLEX_UNIT_SP,
				MinimapViewResource.Forward.fontSize);
		forward.setTextColor(Color.parseColor(MinimapViewResource.Forward.fontColor));
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.RIGHT_OF, forwardImage.getId());
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		forwardLayout.addView(forward, params);

		forwardLayout
				.setOnTouchListener(new DisplayUtils.OnTouchButtonBackgroundSet());
		forwardLayout.setBackgroundResource(R.drawable.button);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.addRule(RelativeLayout.BELOW, selectedImage.getId());
		this.addView(forwardLayout, params);
		
		leftLayout = new RelativeLayout(context);
		leftLayout.setId(Resource.id++);

		leftImage = new ImageView(context);
		leftImage.setId(Resource.id++);
		leftImage.setImageResource(R.drawable.card_comment);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.height = MinimapViewResource.Left.Image.height;
		params.width = MinimapViewResource.Left.Image.width;
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		leftLayout.addView(leftImage, params);

		left = new TextView(context);
		left.setId(Resource.id++);
		left.setText("Left");
		left.setTextSize(TypedValue.COMPLEX_UNIT_SP,
				MinimapViewResource.Left.fontSize);
		left.setTextColor(Color.parseColor(MinimapViewResource.Left.fontColor));
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.RIGHT_OF, leftImage.getId());
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		leftLayout.addView(left, params);

		leftLayout
				.setOnTouchListener(new DisplayUtils.OnTouchButtonBackgroundSet());
		leftLayout.setBackgroundResource(R.drawable.button);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		params.addRule(RelativeLayout.BELOW, forwardLayout.getId());
		this.addView(leftLayout, params);
		
		rightLayout = new RelativeLayout(context);
		rightLayout.setId(Resource.id++);

		rightImage = new ImageView(context);
		rightImage.setId(Resource.id++);
		rightImage.setImageResource(R.drawable.card_details);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.height = MinimapViewResource.Right.Image.height;
		params.width = MinimapViewResource.Right.Image.width;
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		rightLayout.addView(rightImage, params);

		right = new TextView(context);
		right.setId(Resource.id++);
		right.setText("Right");
		right.setTextSize(TypedValue.COMPLEX_UNIT_SP,
				MinimapViewResource.Right.fontSize);
		right.setTextColor(Color.parseColor(MinimapViewResource.Right.fontColor));
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.RIGHT_OF, rightImage.getId());
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		rightLayout.addView(right, params);

		rightLayout
				.setOnTouchListener(new DisplayUtils.OnTouchButtonBackgroundSet());
		rightLayout.setBackgroundResource(R.drawable.button);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		params.addRule(RelativeLayout.BELOW, forwardLayout.getId());
		this.addView(rightLayout, params);
		
		backwardLayout = new RelativeLayout(context);
		backwardLayout.setId(Resource.id++);

		backwardImage = new ImageView(context);
		backwardImage.setId(Resource.id++);
		backwardImage.setImageResource(R.drawable.action_add);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.height = MinimapViewResource.Backward.Image.height;
		params.width = MinimapViewResource.Backward.Image.width;
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		backwardLayout.addView(backwardImage, params);

		backward = new TextView(context);
		backward.setId(Resource.id++);
		backward.setText("Backward");
		backward.setTextSize(TypedValue.COMPLEX_UNIT_SP,
				MinimapViewResource.Backward.fontSize);
		backward.setTextColor(Color.parseColor(MinimapViewResource.Backward.fontColor));
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.RIGHT_OF, backwardImage.getId());
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		backwardLayout.addView(backward, params);

		backwardLayout
				.setOnTouchListener(new DisplayUtils.OnTouchButtonBackgroundSet());
		backwardLayout.setBackgroundResource(R.drawable.button);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.addRule(RelativeLayout.BELOW, leftLayout.getId());
		this.addView(backwardLayout, params);
		
		previousLayout = new RelativeLayout(context);
		previousLayout.setId(Resource.id++);

		previousImage = new ImageView(context);
		previousImage.setId(Resource.id++);
		previousImage.setImageResource(R.drawable.card_comment);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.height = MinimapViewResource.Previous.Image.height;
		params.width = MinimapViewResource.Previous.Image.width;
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		previousLayout.addView(previousImage, params);

		previous = new TextView(context);
		previous.setId(Resource.id++);
		previous.setText("Previous");
		previous.setTextSize(TypedValue.COMPLEX_UNIT_SP,
				MinimapViewResource.Previous.fontSize);
		previous.setTextColor(Color.parseColor(MinimapViewResource.Previous.fontColor));
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.RIGHT_OF, previousImage.getId());
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		previousLayout.addView(previous, params);

		previousLayout
				.setOnTouchListener(new DisplayUtils.OnTouchButtonBackgroundSet());
		previousLayout.setBackgroundResource(R.drawable.button);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		params.addRule(RelativeLayout.BELOW, backwardLayout.getId());
		this.addView(previousLayout, params);
		
		nextLayout = new RelativeLayout(context);
		nextLayout.setId(Resource.id++);

		nextImage = new ImageView(context);
		nextImage.setId(Resource.id++);
		nextImage.setImageResource(R.drawable.card_comment);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.height = MinimapViewResource.Next.Image.height;
		params.width = MinimapViewResource.Next.Image.width;
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		nextLayout.addView(nextImage, params);

		next = new TextView(context);
		next.setId(Resource.id++);
		next.setText("Next");
		next.setTextSize(TypedValue.COMPLEX_UNIT_SP,
				MinimapViewResource.Next.fontSize);
		next.setTextColor(Color.parseColor(MinimapViewResource.Next.fontColor));
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.RIGHT_OF, nextImage.getId());
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		nextLayout.addView(next, params);

		nextLayout
				.setOnTouchListener(new DisplayUtils.OnTouchButtonBackgroundSet());
		nextLayout.setBackgroundResource(R.drawable.button);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		params.addRule(RelativeLayout.BELOW, backwardLayout.getId());
		this.addView(nextLayout, params);
	}
}

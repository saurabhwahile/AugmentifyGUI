package com.augmentify.GUIModule.Action;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.augmentify.DataModule.Controller.Controller;
import com.augmentify.GUIModule.Action.Fragments.FragmentController;
import com.augmentify.GUIModule.Utils.DisplayUtils;
import com.augmentify.GUIModule.Utils.Utils;
import com.augmentify.R;
import com.augmentify.Resource;

public class ActionView extends RelativeLayout
{
	Context context;

	public ActionView(Context context)
	{
		super(context);
		this.context = context;

		ActionViewResource.setContext(context);
		FragmentController.setActivity((Activity) context);
		FragmentController.initializeFragments();
	}

	RelativeLayout scanLayout;
	ImageView scanImage;
	TextView scan;
	RelativeLayout addLayout;
	ImageView addImage;
	TextView add;
	ImageView explorer;

	static LayoutParams params;

	public static enum Build
	{
		FRIENDS, EXPLORE, ME
	}

	public static Build buildState;

	public static void setBuildState(Build BuildId)
	{
		buildState = BuildId;
	}

	public void build()
	{

		switch (buildState)
		{
			case FRIENDS:
			{
				buildActionView();
			}
				break;
			case EXPLORE:
			{
				buildSearchView();
			}
				break;
			case ME:
			{
				buildMeView();
			}
				break;
		}

		this.setBackgroundColor(Color.TRANSPARENT);
		this.setBackgroundResource(R.drawable.action_bar_background_bottom);

		this.setId(Resource.id++);
	}

	void buildActionView()
	{
		scanLayout = new RelativeLayout(context);

		scanImage = new ImageView(context);
		scanImage.setImageResource(R.drawable.action_scan);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		scanImage.setAdjustViewBounds(true);
		scanImage.setMaxHeight(ActionViewResource.Scan.Image.height);
		scanImage.setMaxWidth(ActionViewResource.Scan.Image.width);
		scanImage.setScaleType(ScaleType.FIT_XY);
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		scanImage.setId(Resource.id++);
		scanLayout.addView(scanImage, params);

		scan = new TextView(context);
		scan.setTextSize(TypedValue.COMPLEX_UNIT_SP,
				ActionViewResource.Scan.fontSize);
		scan.setText(ActionViewResource.Scan.text);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		params.addRule(RelativeLayout.RIGHT_OF, scanImage.getId());
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		scan.setId(Resource.id++);
		scanLayout.addView(scan, params);

		scanLayout.setBackgroundResource(R.drawable.button);
		scanLayout
				.setOnTouchListener(new DisplayUtils.OnTouchButtonBackgroundSet());
		scanLayout.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				FragmentController
						.show(FragmentController.Fragment.QR_SCANNER_FRAGMENT);
			}
		});

		scanLayout.setId(Resource.id++);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		params.setMargins(ActionViewResource.Scan.Layout.Margin.left,
				ActionViewResource.Scan.Layout.Margin.top,
				ActionViewResource.Scan.Layout.Margin.right,
				ActionViewResource.Scan.Layout.Margin.bottom);
		this.addView(scanLayout, params);

		addLayout = new RelativeLayout(context);

		addImage = new ImageView(context);
		addImage.setImageResource(R.drawable.action_add);
		addImage.setAdjustViewBounds(true);
		addImage.setMaxHeight(ActionViewResource.Add.Image.height);
		addImage.setMaxWidth(ActionViewResource.Add.Image.width);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		addImage.setId(Resource.id++);
		addLayout.addView(addImage, params);

		add = new TextView(context);
		add.setTextSize(TypedValue.COMPLEX_UNIT_SP,
				ActionViewResource.Add.fontSize);
		add.setText(ActionViewResource.Add.text);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.LEFT_OF, addImage.getId());
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		add.setId(Resource.id++);
		addLayout.addView(add, params);

		addLayout.setBackgroundResource(R.drawable.button);
		addLayout
				.setOnTouchListener(new DisplayUtils.OnTouchButtonBackgroundSet());
		addLayout.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				FragmentController
						.show(FragmentController.Fragment.ADD_VIEW_FRAGMENT);
			}
		});

		addLayout.setId(Resource.id++);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		params.setMargins(ActionViewResource.Add.Layout.Margin.left,
				ActionViewResource.Add.Layout.Margin.top,
				ActionViewResource.Add.Layout.Margin.right,
				ActionViewResource.Add.Layout.Margin.bottom);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		this.addView(addLayout, params);

		explorer = new ImageView(context);
		explorer.setImageResource(R.drawable.action_explorer);
		explorer.setAdjustViewBounds(true);
		explorer.setMaxHeight(ActionViewResource.Explorer.Image.height);
		explorer.setMaxWidth(ActionViewResource.Explorer.Image.width);
		explorer.setBackgroundResource(R.drawable.button);
		explorer.setOnTouchListener(new DisplayUtils.OnTouchButtonBackgroundSet());
		explorer.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View view)
			{

			}
		});
		explorer.setId(Resource.id++);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		this.addView(explorer, params);
	}

	public RelativeLayout searchLayout;
	public EditText searchTerm;
	ImageView searchButton;

	void buildSearchView()
	{
		searchLayout = new RelativeLayout(context);

		searchTerm = new EditText(context)
		{
			@Override
			protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
			{
				super.onMeasure(widthMeasureSpec, heightMeasureSpec);

				setMeasuredDimension(width - searchButton.getMeasuredWidth()
						- (int) Utils.getPixelsFromDp(24, context),
						getMeasuredHeight());
			}
		};

		searchTerm.setBackgroundColor(Color.TRANSPARENT);
		searchTerm.setFocusable(false);
		searchTerm.setOnFocusChangeListener(new OnFocusChangeListener()
		{
			@Override
			public void onFocusChange(View view, boolean isFocused)
			{
				if (isFocused)
				{
					searchLayout
							.setBackgroundResource(R.drawable.button_pressed);
				}
				else
				{
					searchLayout.setBackgroundResource(R.drawable.button);
				}
			}

		});

		searchTerm.setOnTouchListener(new OnTouchListener()
		{
			@Override
			public boolean onTouch(View v, MotionEvent event)
			{
				searchTerm.setFocusable(true);
				searchTerm.setFocusableInTouchMode(true);
				return false;
			}
		});

		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		searchTerm.setId(Resource.id++);
		searchLayout.addView(searchTerm, params);

		searchButton = new ImageView(context);
		searchButton.setLongClickable(false);
		searchButton.setBackgroundResource(R.drawable.action_search_button);
		searchButton.setOnTouchListener(new OnTouchListener()
		{
			@Override
			public boolean onTouch(View view, MotionEvent event)
			{
				if (event.getAction() == MotionEvent.ACTION_UP
						|| event.getAction() == MotionEvent.ACTION_CANCEL)
				{
					view.setBackgroundResource(R.drawable.action_search_button);
				}
				else
				{
					view.setBackgroundResource(R.drawable.action_search_button_pressed);
				}
				return false;
			}
		});
        searchButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Controller.exploreLoader.setKey(searchTerm.getText().toString());
                Controller.buildExplore();
            }
        });
		searchButton.setId(Resource.id++);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		searchLayout.addView(searchButton, params);

		searchLayout.setId(Resource.id++);
		searchLayout.setBackgroundResource(R.drawable.button);
		this.addView(searchLayout);
	}

	RelativeLayout bulletinLayout;
	TextView bulletinNumber;
	TextView bulletin;

	RelativeLayout profileLayout;
	ImageView profileImage;
	TextView profile;

	public void buildMeView()
	{
		bulletinLayout = new RelativeLayout(context);

		bulletinNumber = new TextView(context);
		bulletinNumber.setTextSize(TypedValue.COMPLEX_UNIT_SP,
				ActionViewResource.Bulletin.fontSize);
		bulletinNumber.setText("2");

		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		bulletinNumber.setId(Resource.id++);
		bulletinLayout.addView(bulletinNumber, params);

		bulletin = new TextView(context);
		bulletin.setTextSize(TypedValue.COMPLEX_UNIT_SP,
				ActionViewResource.Bulletin.fontSize);
		bulletin.setText(ActionViewResource.Bulletin.text);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		/*params.setMargins(ActionViewResource.Bulletin.Margin.left,
				ActionViewResource.Bulletin.Margin.top,
				ActionViewResource.Bulletin.Margin.right,
				ActionViewResource.Bulletin.Margin.bottom);*/
		//params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		//params.addRule(RelativeLayout.RIGHT_OF, bulletinNumber.getId());
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		bulletin.setId(Resource.id++);
		bulletinLayout.addView(bulletin, params);

		bulletinLayout.setBackgroundResource(R.drawable.button);
		bulletinLayout
				.setOnTouchListener(new DisplayUtils.OnTouchButtonBackgroundSet());
		bulletinLayout.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				FragmentController
						.show(FragmentController.Fragment.BULLETIN_VIEW_FRAGMENT);
			}
		});

		bulletinLayout.setId(Resource.id++);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		params.setMargins(ActionViewResource.Bulletin.Margin.left,
				ActionViewResource.Bulletin.Margin.top,
				ActionViewResource.Bulletin.Margin.right,
				ActionViewResource.Bulletin.Margin.bottom);
		this.addView(bulletinLayout, params);

		profileLayout = new RelativeLayout(context);

		profileImage = new ImageView(context);
		profileImage.setImageResource(R.drawable.action_scan);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		profileImage.setAdjustViewBounds(true);
		profileImage.setMaxHeight(ActionViewResource.Profile.Image.height);
		profileImage.setMaxWidth(ActionViewResource.Profile.Image.width);
		profileImage.setScaleType(ScaleType.FIT_XY);
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		profileImage.setId(Resource.id++);
		profileLayout.addView(profileImage, params);

		profile = new TextView(context);
		profile.setTextSize(TypedValue.COMPLEX_UNIT_SP,
				ActionViewResource.Profile.fontSize);
		profile.setText(ActionViewResource.Profile.text);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		//params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		//params.addRule(RelativeLayout.RIGHT_OF, profileImage.getId());
		//params.addRule(RelativeLayout.CENTER_VERTICAL);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		profile.setId(Resource.id++);
		profileLayout.addView(profile, params);

		profileLayout.setBackgroundResource(R.drawable.button);
		profileLayout
				.setOnTouchListener(new DisplayUtils.OnTouchButtonBackgroundSet());
		profileLayout.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				FragmentController
						.show(FragmentController.Fragment.ADMIN_VIEW_FRAGMENT);
			}
		});

		profileLayout.setId(Resource.id++);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);

		params.setMargins(ActionViewResource.Profile.Margin.left,
				ActionViewResource.Profile.Margin.top,
				ActionViewResource.Profile.Margin.right,
				ActionViewResource.Profile.Margin.bottom);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		params.addRule(RelativeLayout.RIGHT_OF, bulletinLayout.getId());
		this.addView(profileLayout, params);

	}

	public int width;
	public int height;

	boolean isActionLayoutParamsSet = false;
	boolean isMeLayoutParamsSet = false;

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		width = getMeasuredWidth();
		height = getMeasuredHeight();

		if (addLayout != null && !isActionLayoutParamsSet)
		{
			params = (LayoutParams) scanLayout.getLayoutParams();
			params.width = width / 3;
			scanLayout.setLayoutParams(params);

			params = (LayoutParams) addLayout.getLayoutParams();
			params.width = width / 3;
			addLayout.setLayoutParams(params);

			params = (LayoutParams) explorer.getLayoutParams();
			params.width = (int) (width / 3.2);
			explorer.setLayoutParams(params);

			isActionLayoutParamsSet = true;
		}

		if (bulletinLayout != null && !isMeLayoutParamsSet)
		{
			params = (LayoutParams) bulletinLayout.getLayoutParams();
			params.width = (int) (width / 2.2);
			params.height = profileLayout.getMeasuredHeight();
			bulletinLayout.setLayoutParams(params);

			params = (LayoutParams) profileLayout.getLayoutParams();
			params.width = width / 2;
			profileLayout.setLayoutParams(params);

		}

		setMeasuredDimension(width, height);
	}
}

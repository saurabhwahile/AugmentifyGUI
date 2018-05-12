package com.augmentify.GUIModule.Card.Fragments.BulkSearchAndAdd;

import android.content.Context;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.augmentify.DataModule.Controller.Controller;
import com.augmentify.DataModule.Objects.Image;
import com.augmentify.GUIModule.Utils.Utils;
import com.augmentify.R;
import com.augmentify.Resource;

/**
 * Created by Saurabh on 21/02/2015.
 */
public class BulkSearchAndAddView extends RelativeLayout
{
    Context context;
    public BulkSearchAndAddView(Context context)
    {
        super(context);
        this.context = context;
    }

    public static enum SEARCH_TYPE
    {
        USER,
        EVENT
    }

    RelativeLayout searchLayout;
    EditText searchTerm;
    LayoutParams params;
    ImageView searchButton;

    public void build()
    {
        searchLayout = new RelativeLayout(context);

        searchTerm = new EditText(context)
        {
            /*@Override
            protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
            {
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);

                setMeasuredDimension(width - searchButton.getMeasuredWidth()
                                - (int) Utils.getPixelsFromDp(24, context),
                        getMeasuredHeight());
            }*/
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
}

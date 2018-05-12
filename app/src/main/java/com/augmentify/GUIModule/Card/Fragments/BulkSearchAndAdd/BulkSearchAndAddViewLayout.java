package com.augmentify.GUIModule.Card.Fragments.BulkSearchAndAdd;

import android.content.Context;
import android.graphics.Color;
import android.widget.RelativeLayout;

import com.augmentify.Resource;

/**
 * Created by Saurabh on 21/02/2015.
 */
public class BulkSearchAndAddViewLayout extends RelativeLayout
{
    Context context;
    public BulkSearchAndAddViewLayout(Context context)
    {
        super(context);
        this.context = context;
    }

    public BulkSearchAndAddView bulkSearchAndAddView;
    static RelativeLayout.LayoutParams params;

    public void build()
    {
        bulkSearchAndAddView = new BulkSearchAndAddView(context);
        bulkSearchAndAddView.build();

        params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        this.addView(bulkSearchAndAddView, params);

        this.setBackgroundColor(Color.TRANSPARENT);
        this.setId(Resource.id++);
    }
}

package com.augmentify.GUIModule.Action.Fragments.Admin;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import com.augmentify.DataModule.Controller.Controller;
import com.augmentify.DataModule.Controller.Modules.MeLoader;
import com.augmentify.GUIModule.Action.Fragments.Add.Fragments.FragmentController;
import com.augmentify.GUIModule.Fragments.FragmentViewFactory;
import com.augmentify.Resource;

/**
 * Created by Saurabh on 07/02/2015.
 */
public class AdminView extends RelativeLayout
{
    Context context;
    public AdminView(Context context)
    {
        super(context);
        this.context = context;
        AdminViewResource.context = context;

        FragmentController.setActivity((Activity) context);
    }

    FragmentViewFactory.DialogMenuButton myEvents;
    FragmentViewFactory.DialogMenuButton joinedEvents;

    RelativeLayout.LayoutParams params;

    public void build()
    {
        myEvents = new FragmentViewFactory.DialogMenuButton(context, this, 0, 0, 0, 0);
        myEvents.dialogMenuButton.setText(AdminViewResource.MyEvents.text);
        myEvents.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Controller.meLoader.setType(MeLoader.ME_CONTENT_TYPE.MY_EVENTS);
                Controller.buildMe();
            }
        });
        joinedEvents = new FragmentViewFactory.DialogMenuButton(context, this, 0, 0, myEvents.getId(), 0);
        joinedEvents.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Controller.meLoader.setType(MeLoader.ME_CONTENT_TYPE.JOINED_EVENTS);
                Controller.buildMe();
            }
        });
        params = (RelativeLayout.LayoutParams)joinedEvents.getLayoutParams();
        params.setMargins(AdminViewResource.JoinedEvents.Margins.left,
                AdminViewResource.JoinedEvents.Margins.top,
                AdminViewResource.JoinedEvents.Margins.right,
                AdminViewResource.JoinedEvents.Margins.bottom);
        joinedEvents.setLayoutParams(params);
        joinedEvents.dialogMenuButton.setText(AdminViewResource.JoinedEvents.text);
        this.setId(Resource.id++);
    }
}


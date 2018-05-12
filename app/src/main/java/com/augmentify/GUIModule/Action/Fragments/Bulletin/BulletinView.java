package com.augmentify.GUIModule.Action.Fragments.Bulletin;

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
 * Created by Saurabh on 06/02/2015.
 */
public class BulletinView extends RelativeLayout
{
    Context context;
    public BulletinView(Context context)
    {
        super(context);
        this.context = context;
        BulletinViewResource.context = context;

        FragmentController.setActivity((Activity) context);
    }

    FragmentViewFactory.DialogMenuButton notification;
    FragmentViewFactory.DialogMenuButton request;

    RelativeLayout.LayoutParams params;

    public void build()
    {
        notification = new FragmentViewFactory.DialogMenuButton(context, this, 0, 0, 0, 0);
        notification.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Controller.meLoader.setType(MeLoader.ME_CONTENT_TYPE.NOTIFICATION);
                Controller.buildMe();
            }

        });
        notification.dialogMenuButton.setText(BulletinViewResource.Notification.text);


        request = new FragmentViewFactory.DialogMenuButton(context, this, 0, 0, notification.getId(), 0);
        request.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Controller.meLoader.setType(MeLoader.ME_CONTENT_TYPE.REQUEST);
                Controller.buildMe();
            }
        });
        params = (RelativeLayout.LayoutParams) request.getLayoutParams();
        params.setMargins(BulletinViewResource.Request.Margins.left,
                BulletinViewResource.Request.Margins.top,
                BulletinViewResource.Request.Margins.right,
                BulletinViewResource.Request.Margins.bottom);
        request.setLayoutParams(params);
        request.dialogMenuButton.setText(BulletinViewResource.Request.text);
        this.setId(Resource.id++);
    }
}

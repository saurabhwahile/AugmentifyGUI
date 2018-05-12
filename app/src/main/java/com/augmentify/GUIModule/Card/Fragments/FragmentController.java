package com.augmentify.GUIModule.Card.Fragments;

import android.app.Activity;
import android.app.FragmentManager;

import com.augmentify.DataModule.Objects.DataObject;
import com.augmentify.GUIModule.Card.Fragments.BulkSearchAndAdd.BulkSearchAndAddViewFragment;
import com.augmentify.GUIModule.Card.Fragments.Comments.CommentsViewFragment;
import com.augmentify.GUIModule.Card.Fragments.Details.DetailsViewFragment;
import com.augmentify.GUIModule.Card.Fragments.ShowQR.ShowQRViewFragment;

public class FragmentController
{
	public static FragmentManager fragmentManager;
	public static void setActivity(Activity activity)
	{
		FragmentController.fragmentManager = activity.getFragmentManager();
	}

	public static enum Fragment
	{
        COMMENTS_VIEW_FRAGMENT,
		DETAILS_VIEW_FRAGMENT,
		SHOW_QR_VIEW_FRAGMENT,
        BULK_SEARCH_AND_ADD_FRAGMENT
	}

	static CommentsViewFragment commentsViewFragment;
	static DetailsViewFragment detailsViewFragment;
	static ShowQRViewFragment showQRViewFragment;
    static BulkSearchAndAddViewFragment bulkSearchAndAddViewFragment;

	public static void show(Fragment fragmentID, DataObject data)
	{
		switch (fragmentID)
		{
			case COMMENTS_VIEW_FRAGMENT:
			{
                commentsViewFragment = CommentsViewFragment.newInstance();
                commentsViewFragment.refresh(data);
				commentsViewFragment.show(fragmentManager, "CommentsViewFragment");
			}
				break;
			case DETAILS_VIEW_FRAGMENT:
			{
                detailsViewFragment = DetailsViewFragment.newInstance();
                detailsViewFragment.refresh(data);
				detailsViewFragment.show(fragmentManager, "DetailsViewFragment");
			}
			break;
			case SHOW_QR_VIEW_FRAGMENT:
			{
                showQRViewFragment = ShowQRViewFragment.newInstance();
                showQRViewFragment.refresh(data);
				showQRViewFragment.show(fragmentManager, "ShowQRViewFragment");
			}
			break;
            case BULK_SEARCH_AND_ADD_FRAGMENT:
            {
                bulkSearchAndAddViewFragment = BulkSearchAndAddViewFragment.newInstance();
                bulkSearchAndAddViewFragment.refresh(data);
                bulkSearchAndAddViewFragment.show(fragmentManager, "ShowQRViewFragment");
            }
            break;
		}
	}
	
	public static void dismiss(Fragment fragmentID)
	{
		switch (fragmentID)
		{
			case COMMENTS_VIEW_FRAGMENT:
			{
				commentsViewFragment.dismiss();
			}
				break;
			case DETAILS_VIEW_FRAGMENT:
			{
				detailsViewFragment.dismiss();
			}
			break;
			case SHOW_QR_VIEW_FRAGMENT:
			{
				showQRViewFragment.dismiss();
			}
			break;
            case BULK_SEARCH_AND_ADD_FRAGMENT:
            {
                bulkSearchAndAddViewFragment.dismiss();
            }
            break;
		}
	}
}




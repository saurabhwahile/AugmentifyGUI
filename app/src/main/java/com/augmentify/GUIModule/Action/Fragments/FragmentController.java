package com.augmentify.GUIModule.Action.Fragments;

import android.app.Activity;
import android.app.FragmentManager;

import com.augmentify.GUIModule.Action.Fragments.Add.AddViewFragment;
import com.augmentify.GUIModule.Action.Fragments.Admin.AdminViewFragment;
import com.augmentify.GUIModule.Action.Fragments.Bulletin.BulletinViewFragment;
import com.augmentify.GUIModule.Action.Fragments.QRScanner.QRScannerDialogFragment;

public class FragmentController
{
	public static FragmentManager fragmentManager;
	public static void setActivity(Activity activity)
	{
		FragmentController.fragmentManager = activity.getFragmentManager();
	}

	public static enum Fragment
	{
		QR_SCANNER_FRAGMENT,
		ADD_VIEW_FRAGMENT,
		REQUEST_VIEW_FRAGMENT,
        BULLETIN_VIEW_FRAGMENT,
        ADMIN_VIEW_FRAGMENT
	}

	public static QRScannerDialogFragment qrScannerDialog;
    public static AddViewFragment addViewFragment;
	public static BulletinViewFragment bulletinViewFragment;
    public static AdminViewFragment adminViewFragment;
	public static void initializeFragments()
	{
		for (Fragment fragment : Fragment.values())
		{
			initialize(fragment);
		}
	}

	public static void initialize(Fragment fragmentID)
	{
		switch (fragmentID)
		{
			case QR_SCANNER_FRAGMENT:
			{
				qrScannerDialog = QRScannerDialogFragment.newInstance();
			}
				break;
			case ADD_VIEW_FRAGMENT:
			{
				addViewFragment = AddViewFragment.newInstance();
			}
				break;
			case BULLETIN_VIEW_FRAGMENT:
			{
				bulletinViewFragment = BulletinViewFragment.newInstance();
			}
				break;
            case ADMIN_VIEW_FRAGMENT:
            {
                adminViewFragment = AdminViewFragment.newInstance();
            }
		}
	}

	public static void show(Fragment fragmentID)
	{
		switch (fragmentID)
		{
			case QR_SCANNER_FRAGMENT:
			{
				qrScannerDialog.show(fragmentManager, "QRScanner");
			}
			break;
			case ADD_VIEW_FRAGMENT:
			{
				addViewFragment.show(fragmentManager, "AddViewFragment");
			}
			break;
			case BULLETIN_VIEW_FRAGMENT:
			{
				bulletinViewFragment.show(fragmentManager, "RequestViewFragment");
			}
			break;
            case ADMIN_VIEW_FRAGMENT:
            {
                adminViewFragment.show(fragmentManager, "AdminViewFragment");
            }
            break;

		}
	}
	
	public static void dismiss(Fragment fragmentID)
	{
		switch (fragmentID)
		{
			case QR_SCANNER_FRAGMENT:
			{
				qrScannerDialog.dismiss();
			}
			break;
			case ADD_VIEW_FRAGMENT:
			{
				addViewFragment.dismiss();
			}
			break;
			case BULLETIN_VIEW_FRAGMENT:
			{
				bulletinViewFragment.dismiss();
			}
			break;
            case ADMIN_VIEW_FRAGMENT:
            {
                adminViewFragment.dismiss();
            }
            break;
		}
	}
}

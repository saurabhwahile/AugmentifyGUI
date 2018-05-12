package com.augmentify.GUIModule.Action.Fragments.Add.Fragments.Event;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.ScrollView;

import com.augmentify.GUIModule.Utils.Utils;
import com.augmentify.R;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class EventViewFragment extends DialogFragment
{
    static EventViewFragment newInstance;

    public static EventViewFragment newInstance()
    {
        if (newInstance == null)
        {
            newInstance = new EventViewFragment();
            newInstance.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
            newInstance.setStyle(DialogFragment.STYLE_NO_FRAME, 0);
        }
        return newInstance;
    }

    EventView eventView;
    ScrollView eventViewScrollView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        eventView = new EventView(this.getActivity());
        eventView.build();

        eventViewScrollView = new ScrollView(this.getActivity());
        eventViewScrollView.setPadding(EventViewResource.Padding.left,
                EventViewResource.Padding.top, EventViewResource.Padding.right,
                EventViewResource.Padding.bottom);
        eventViewScrollView.addView(eventView);

        this.getDialog().getWindow()
                .setBackgroundDrawableResource(R.drawable.card_shadow);
        this.getDialog()
                .getWindow()
                .setFlags(LayoutParams.FLAG_DIM_BEHIND,
                        LayoutParams.FLAG_DIM_BEHIND);
        this.getDialog().getWindow().getAttributes().dimAmount = EventViewResource.backgroundDimAmount;
        this.getDialog().setCanceledOnTouchOutside(true);

        return eventViewScrollView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == EventView.SELECT_IMAGE && resultCode == Activity.RESULT_OK)
        {
            try
            {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getActivity().getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                eventView.displayPictureImage.setImageBitmap(selectedImage);
                eventView.image_path = Utils.getRealPathFromURI(getActivity().getApplicationContext(), imageUri);
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }
    }
}

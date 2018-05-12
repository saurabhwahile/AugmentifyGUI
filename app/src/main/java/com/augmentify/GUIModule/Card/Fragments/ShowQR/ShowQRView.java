package com.augmentify.GUIModule.Card.Fragments.ShowQR;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.augmentify.DataModule.Objects.Card.QRCode;
import com.augmentify.DataModule.Objects.DataObject;
import com.augmentify.DataModule.Objects.Image;
import com.augmentify.GUIModule.Card.Fragments.Details.DetailsViewResource;
import com.augmentify.GUIModule.Utils.DisplayUtils;
import com.augmentify.R;
import com.augmentify.Resource;

public class ShowQRView extends RelativeLayout
{
	Context context;
	public ShowQRView(Context context)
	{
		super(context);
		this.context = context;
		ShowQRViewResource.context = context;
	}
	
	public TextView title;
	public View horizontalSeparator;
	public ImageView qrImage;
	
	public RelativeLayout qrSaveLayout;
	public ImageView qrSaveImage;
	public TextView qrSave;
	
	static LayoutParams params;
	
	public void build()
	{
		title = new TextView(context);
		title.setTextSize(TypedValue.COMPLEX_UNIT_SP, ShowQRViewResource.Title.fontSize);
		title.setText("Title");
		title.setTextColor(Color.parseColor(ShowQRViewResource.Title.fontColor));
		title.setPadding(ShowQRViewResource.Title.Padding.left,
				ShowQRViewResource.Title.Padding.top,
				ShowQRViewResource.Title.Padding.right,
				ShowQRViewResource.Title.Padding.bottom);		
		title.setId(Resource.id++);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		this.addView(title, params);
		
		horizontalSeparator = new View(context);
		horizontalSeparator.setBackgroundResource(R.drawable.horizontal_separator);
		horizontalSeparator.setPadding(
				DetailsViewResource.HorizontalSeparator.Padding.left,
				DetailsViewResource.HorizontalSeparator.Padding.top,
				DetailsViewResource.HorizontalSeparator.Padding.right,
				DetailsViewResource.HorizontalSeparator.Padding.bottom);
		horizontalSeparator.setId(Resource.id++);
		params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.BELOW, title.getId());
		params.height = 3;
		this.addView(horizontalSeparator, params);
		
		qrImage = new ImageView(context);
		qrImage.setId(Resource.id++);
		qrImage.setBackgroundResource(R.drawable.card_shadow);
		qrImage.setImageResource(R.drawable.action_add);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.addRule(RelativeLayout.BELOW, horizontalSeparator.getId());
		this.addView(qrImage, params);
		
		qrSaveLayout = new RelativeLayout(context);
		qrSaveLayout.setId(Resource.id++);

		qrSaveImage = new ImageView(context);
		qrSaveImage.setImageResource(R.drawable.card_smikes);
		qrSaveImage.setPadding(ShowQRViewResource.QRSave.Image.Padding.left,
				ShowQRViewResource.QRSave.Image.Padding.top,
				ShowQRViewResource.QRSave.Image.Padding.right,
				ShowQRViewResource.QRSave.Image.Padding.bottom);
		qrSaveImage.setId(Resource.id++);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		params.addRule(RelativeLayout.ALIGN_LEFT);
		params.height = ShowQRViewResource.QRSave.Image.height;
		params.width = ShowQRViewResource.QRSave.Image.width;
		qrSaveLayout.addView(qrSaveImage, params);

		qrSave = new TextView(context);
		qrSave.setText("QRSave   ");
		qrSave.setTextSize(TypedValue.COMPLEX_UNIT_SP,
				ShowQRViewResource.QRSave.fontSize);
		qrSave.setTextColor(Color.parseColor(ShowQRViewResource.QRSave.fontColor));
		qrSave.setPadding(ShowQRViewResource.QRSave.Padding.left,
				ShowQRViewResource.QRSave.Padding.top,
				ShowQRViewResource.QRSave.Padding.right,
				ShowQRViewResource.QRSave.Padding.bottom);
		qrSave.setId(Resource.id++);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		params.addRule(RelativeLayout.ALIGN_RIGHT);
		params.addRule(RelativeLayout.RIGHT_OF, qrSaveImage.getId());
		qrSaveLayout.addView(qrSave, params);

		qrSaveLayout.setBackgroundResource(R.drawable.button);
		qrSaveLayout
				.setOnTouchListener(new DisplayUtils.OnTouchButtonBackgroundSet());
		qrSaveLayout.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View view)
			{

			}
		});
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.setMargins(ShowQRViewResource.QRSave.Margin.left,
				ShowQRViewResource.QRSave.Margin.top, 
				ShowQRViewResource.QRSave.Margin.right,
				ShowQRViewResource.QRSave.Margin.bottom);
		params.addRule(RelativeLayout.BELOW, qrImage.getId());
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		this.addView(qrSaveLayout, params);
		
		this.setBackgroundColor(Color.TRANSPARENT);
		this.setId(Resource.id++);
	}

    public void refresh(DataObject data)
    {
        QRCode qrCode = (QRCode)data;
        Image qrImage = new Image(context)
        {
            @Override
            public void onStatusChange(Status status)
            {
                switch(status)
                {
                    case READ_OK:
                    {
                        ShowQRView.this.qrImage.setImageBitmap(image);
                    }
                    break;
                }

            }
        };
        qrImage.setReadRequestParams(Image.RESOURCE_TYPE.QR_IMAGE, qrCode.image);
        qrImage.read();
    }
}

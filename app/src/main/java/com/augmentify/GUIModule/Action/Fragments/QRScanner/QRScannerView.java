package com.augmentify.GUIModule.Action.Fragments.QRScanner;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.os.Handler;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.augmentify.DataModule.Objects.Card.Event;
import com.augmentify.DataModule.Objects.QRScan;
import com.augmentify.GUIModule.Card.Fragments.FragmentController;
import com.augmentify.R;
import com.augmentify.Resource;

import net.sourceforge.zbar.Config;
import net.sourceforge.zbar.Image;
import net.sourceforge.zbar.ImageScanner;
import net.sourceforge.zbar.Symbol;
import net.sourceforge.zbar.SymbolSet;

public class QRScannerView extends RelativeLayout
{
	private Camera camera;
	private QRCamera cameraPreview;
	private Handler autoFocusHandler;
	private ImageScanner scanner;

	private boolean barcodeScanned = false;
	private boolean cameraRolling = true;

	private Context context;
	static
	{
		System.loadLibrary("iconv");
	}

	public static String scanResult;

	//boolean scanStatus = false;

	public QRScannerView(Context context)
	{
		super(context);
		this.context = context;
		QRScannerViewResource.context = context;
		
		autoFocusHandler = new Handler();
		
		scanner = new ImageScanner();
		scanner.setConfig(0, Config.X_DENSITY, 3);
		scanner.setConfig(0, Config.Y_DENSITY, 3);
	}

	TextView title;

	ImageView scanButton;
	TextView scanTextView;

	LayoutParams params;

	public void buildQRScannerView()
	{
		title = new TextView(context);
		title.setText(QRScannerViewResource.Title.text);
		title.setTextSize(TypedValue.COMPLEX_UNIT_SP, QRScannerViewResource.Title.fontSize);
		title.setTextColor(Color.parseColor(QRScannerViewResource.Title.fontColor));
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		title.setId(Resource.id++);
		this.addView(title, params);
		
		camera = getCameraInstance();
		cameraPreview = new QRCamera(context, camera, previewCB, autoFocusCB);
		cameraPreview.setId(Resource.id++);
		params = new LayoutParams(QRScannerViewResource.CameraView.width,
				QRScannerViewResource.CameraView.height);
		params.addRule(RelativeLayout.BELOW, title.getId());
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		this.addView(cameraPreview, params);
		
		scanButton = new ImageView(context);
		scanButton.setImageResource(R.drawable.action_scan_dialog_scan);
		scanButton.setOnTouchListener(new OnTouchListener()
		{
			@Override
			public boolean onTouch(View view, MotionEvent event)
			{
				if (event.getAction() == MotionEvent.ACTION_UP
						|| event.getAction() == MotionEvent.ACTION_CANCEL)
				{
					scanButton.setImageResource(R.drawable.action_scan_dialog_scan);
				}
				else
				{
					scanButton.setImageResource(R.drawable.action_scan_dialog_scan_pressed);
				}
				return false;
			}
		});
		scanButton.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				camera.setOneShotPreviewCallback(previewCB);
				if (barcodeScanned)
				{
					barcodeScanned = false;
					// camera.setPreviewCallbackWithBuffer(previewCB);
					camera.startPreview();
					cameraRolling = true;
					camera.autoFocus(autoFocusCB);
				}
			}
		});
		scanButton.setId(Resource.id++);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.BELOW, cameraPreview.getId());
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		this.addView(scanButton, params);

		scanTextView = new TextView(context);
		scanTextView.setText("Press Scan");
		scanTextView.setId(Resource.id++);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.BELOW, scanButton.getId());
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		this.addView(scanTextView, params);
		
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		this.setLayoutParams(params);
		
		this.setPadding(QRScannerViewResource.Padding.left,
				QRScannerViewResource.Padding.top,
				QRScannerViewResource.Padding.right,
				QRScannerViewResource.Padding.bottom);
		
		this.setId(Resource.id++);
	}

	public void onPause()
	{
		releaseCamera();
	}

	public void onResume()
	{
		if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
		{
			title.setVisibility(View.VISIBLE);
		}
		else
		{
			title.setVisibility(View.GONE);
		}
		if (savedCameraPreviewID != -1)
		{
			camera = getCameraInstance();
			cameraPreview = new QRCamera(context, camera, previewCB, autoFocusCB);
			cameraPreview.setId(savedCameraPreviewID);
			this.addView(cameraPreview, savedCameraPreviewIndex, params);
		}
		cameraRolling = true;
	}

	/** A safe way to get an instance of the Camera object. */
	public static Camera getCameraInstance()
	{
		Camera camera = null;
		try
		{
			camera = Camera.open();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return camera;
	}

	int savedCameraPreviewID = -1;
	int savedCameraPreviewIndex = -1;

	private void releaseCamera()
	{
		if (camera != null)
		{
			cameraRolling = false;
			camera.setPreviewCallback(null);
			cameraPreview.getHolder().removeCallback(cameraPreview);
			camera.release();
			camera = null;
			savedCameraPreviewID = cameraPreview.getId();
			savedCameraPreviewIndex = this.indexOfChild(cameraPreview);
			this.removeView(cameraPreview);
			cameraPreview = null;
			//camera.setPreviewCallbackWithBuffer(null);
		}
	}

	private Runnable doAutoFocus = new Runnable()
	{
		public void run()
		{
			if (cameraRolling)
			{
				camera.autoFocus(autoFocusCB);
			}
		}
	};

	PreviewCallback previewCB = new PreviewCallback()
	{
		public void onPreviewFrame(byte[] data, Camera camera)
		{
			Camera.Parameters parameters = camera.getParameters();
			Size size = parameters.getPreviewSize();

			Image barcode = new Image(size.width, size.height, "Y800");
			barcode.setData(data);

			int result = scanner.scanImage(barcode);

			if (result != 0)
			{
				cameraRolling = false;
				camera.setPreviewCallback(null);
				camera.stopPreview();

				scanResult = "";
				SymbolSet symbolSet = scanner.getResults();
				for (Symbol symbol : symbolSet)
				{
					scanResult = scanResult + symbol.getData();
					barcodeScanned = true;
				}
                onQRCodeScanned(scanResult);

			}
		}
	};

	// Mimic continuous auto-focusing
	AutoFocusCallback autoFocusCB = new AutoFocusCallback()
	{
		public void onAutoFocus(boolean success, Camera camera)
		{
			camera.setOneShotPreviewCallback(previewCB);
			autoFocusHandler.postDelayed(doAutoFocus, 1000);
		}
	};

    void onQRCodeScanned(String qrCode)
    {
        QRScan qr = new QRScan(context)
        {
            @Override
            public void onStatusChange(Status status)
            {
                switch (status)
                {
                    case READ_OK:
                    {
                        onQRProcessed(this);
                    }
                    break;
                }
            }
        };
        qr.setReadRequestParams(QRScan.RESOURCE_TYPE.QR_SCAN, qrCode);
        qr.read();
    }

    void onQRProcessed(QRScan qrScan)
    {
        if(qrScan.label.equals(QRScan.QR_SCAN_TYPE.EVENT))
        {
            Event event = new Event(context)
            {
                @Override
                public void onStatusChange(Status status)
                {
                    switch (status)
                    {
                        case READ_OK:
                        {
                            FragmentController.show(FragmentController.Fragment.DETAILS_VIEW_FRAGMENT, this);
                        }
                        break;
                    }
                }
            };
            event.setReadRequestParams(Event.RESOURCE_TYPE.EVENT_DETAIL, qrScan.event);
            event.read();
        }

        if(qrScan.label.equals(QRScan.QR_SCAN_TYPE.EVENT_JOINED))
        {
            
        }
    }
}
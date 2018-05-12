package com.augmentify.GUIModule.Action.Fragments.QRScanner;

import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;
import java.util.List;

/** A basic Camera preview class */
public class QRCamera extends SurfaceView implements SurfaceHolder.Callback
{
	private SurfaceHolder holder;
	private Camera camera;
	// private PreviewCallback previewCallback;
	private AutoFocusCallback autoFocusCallback;

	public QRCamera(Context context)
	{
		super(context);
	}
	
	public QRCamera(Context context, Camera camera, PreviewCallback previewCb,
			AutoFocusCallback autoFocusCb)
	{
		super(context);
		this.camera = camera;
		// previewCallback = previewCb;
		autoFocusCallback = autoFocusCb;
		/*
		 * Set camera to continuous focus if supported, otherwise use software
		 * auto-focus. Only works for API level >=9.
		 */
		Camera.Parameters parameters = camera.getParameters();
		for (String f : parameters.getSupportedFocusModes())
		{
			if (f == Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)
			{
				parameters.setFocusMode(Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
				autoFocusCallback = null;
				camera.setParameters(parameters);
				break;
			}
		}

		// Install a SurfaceHolder.Callback so we get notified when the
		// underlying surface is created and destroyed.
		holder = getHolder();
		holder.addCallback(this);

		// deprecated setting, but required on Android versions prior to 3.0
		// holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	public void surfaceCreated(SurfaceHolder holder)
	{
		// The Surface has been created, now tell the camera where to draw the
		// preview.
		try
		{
			camera.setPreviewDisplay(holder);
		}
		catch (IOException e)
		{
			Log.d("DBG", "Error setting camera preview: " + e.getMessage());
			Log.d("DBG", "Wait What?");
		}
		catch (NullPointerException e)
		{
			Log.d("DBG", "Error setting camera preview: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void surfaceDestroyed(SurfaceHolder holder)
	{
		// Camera preview released in activity
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height)
	{
		/*
		 * If your preview can change or rotate, take care of those events here.
		 * Make sure to stop the preview before resizing or reformatting it.
		 */
		if (holder.getSurface() == null)
		{
			// preview surface does not exist
			return;
		}

		// stop preview before making changes
		try
		{
			camera.stopPreview();
		}
		catch (Exception e)
		{
			// ignore: tried to stop a non-existent preview
		}

		try
		{
			// Hard code camera surface rotation 90 degs to match Activity view
			// in portrait
			if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
			{
				camera.setDisplayOrientation(90);
			}

			Camera.Parameters cameraParameters = camera.getParameters();
			List<Camera.Size> cameraPreviewSizes = cameraParameters.getSupportedPreviewSizes();
			
			int bestCameraPreviewSizeIndex;
			for(bestCameraPreviewSizeIndex=0; bestCameraPreviewSizeIndex<cameraPreviewSizes.size(); bestCameraPreviewSizeIndex++)
			{
				if(cameraPreviewSizes.get(bestCameraPreviewSizeIndex).height == height)
				{
					break;
				}
			}
			
			if(bestCameraPreviewSizeIndex == cameraPreviewSizes.size())
			{
				bestCameraPreviewSizeIndex--;
			}
			
			cameraParameters.setPreviewSize(cameraPreviewSizes.get(bestCameraPreviewSizeIndex).width, cameraPreviewSizes.get(bestCameraPreviewSizeIndex).height);
			
			for (String f : cameraParameters.getSupportedFocusModes())
			{
				if (f == Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)
				{
					cameraParameters.setFocusMode(Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
					autoFocusCallback = null;
					break;
				}
			}
			
			camera.setParameters(cameraParameters);
			
			camera.setPreviewDisplay(holder);
			// camera.setPreviewCallbackWithBuffer(previewCallback);
			camera.startPreview();
			camera.autoFocus(autoFocusCallback);
		}
		catch (Exception e)
		{
			Log.d("DBG", "Error starting camera preview: " + e.getMessage());
		}
	}
}
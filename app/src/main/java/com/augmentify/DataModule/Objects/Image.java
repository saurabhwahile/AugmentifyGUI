package com.augmentify.DataModule.Objects;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.augmentify.DataModule.Controller.Controller;
import com.augmentify.DataModule.Settings;
import com.augmentify.DataModule.Urls;
import com.augmentify.Debug;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by Saurabh on 14/09/2014.
 */
public class Image implements DataObject
{
    @Expose
    public String date_created;

    @Expose
    public int id;

    @Expose
    public String image_path;

    @Expose
    public String summary;

    @Expose
    public String title;

    @Expose
    public int visibility;

    void copyFields(Image image)
    {
        this.date_created = image.date_created;
        this.id = image.id;
        this.image_path = image.image_path;
        this.summary = image.summary;
        this.title = image.title;
        this.visibility = image.visibility;
        this.image = image.image;
        this.image_path = image.image_path;
    }

    Status status;
    String requestUrl;
    RESOURCE_TYPE resourceType;
    public String response;
    public Bitmap image;

    public static enum RESOURCE_TYPE
    {
        QR_IMAGE,
        IMAGE
    }

    Context context;
    public Image(Context context)
    {
        this.context = context;
    }

    public void setReadRequestParams(RESOURCE_TYPE resourceType, String image_path)
    {
        this.resourceType = resourceType;
        this.image_path = image_path;
    }

    @Override
    public void refresh()
    {
        switch (resourceType)
        {
            case QR_IMAGE:
            {

            }
            break;
            case IMAGE:
            {
                copyFields(Controller.dataObjectBuilder.fromJson(response, Image.class));
            }
            break;
        }
    }

    @Override
    public void read()
    {
        Uri.Builder uriBuilder = Uri.parse("http://192.168.1.10:8080")
                .buildUpon()
                .appendPath(image_path);
        /*
                .appendQueryParameter(Urls.REQUEST_KEY.FORMAT, "json")
                .appendQueryParameter(Urls.REQUEST_KEY.USERNAME, Settings.Account.username)
                .appendQueryParameter(Urls.REQUEST_KEY.API_KEY, Settings.Account.apiKey);
        */
        //uriBuilder.appendPath("");

        switch (resourceType)
        {
            case QR_IMAGE:
            {

            }
            break;
            case IMAGE:
            {
                   uriBuilder.appendQueryParameter(Urls.REQUEST_KEY.FORMAT, "json")
                           .appendQueryParameter(Urls.REQUEST_KEY.USERNAME, Settings.Account.username)
                           .appendQueryParameter(Urls.REQUEST_KEY.API_KEY, Settings.Account.apiKey);
            }
            break;

        }

        requestUrl = uriBuilder.build().toString();
        Log.d(Debug.TAG.NET, requestUrl);

        ImageRequest request = new ImageRequest(requestUrl,
                new Response.Listener<Bitmap>()
                {
                    @Override
                    public void onResponse(Bitmap response)
                    {
                        image = response;
                        refresh();
                        changeStatus(Status.READ_OK);
                    }
                }, 0, 0, null, null);
        Controller.networkRequestQueue.add(request);
    }

    public void setCreateRequestParams(RESOURCE_TYPE resourceType,
                                       String image_path)
    {
        this.resourceType = resourceType;
        this.image_path = image_path;
    }

    @Override
    public void create()
    {
        Uri.Builder uriBuilder = Uri.parse(Urls.SERVER)
                .buildUpon()
                .appendPath(Urls.API)
                .appendPath(Urls.VERSION)
                .appendQueryParameter(Urls.REQUEST_KEY.FORMAT, "json")
                .appendQueryParameter(Urls.REQUEST_KEY.USERNAME, Settings.Account.username)
                .appendQueryParameter(Urls.REQUEST_KEY.API_KEY, Settings.Account.apiKey)
                .appendPath(Urls.PATH.IMAGE)
                .appendPath("");

        requestUrl = uriBuilder.build().toString();
        Log.d(Debug.TAG.NET, requestUrl);

        final Image data = new Image(context);
        data.copyFields(this);
        StringRequest request = new StringRequest(Request.Method.POST, requestUrl,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String serverResponse)
                    {
                        response = serverResponse;
                        refresh();
                        changeStatus(Status.CREATE_OK);
                    }
                }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                if (error.networkResponse.statusCode == 500)
                {
                    changeStatus(Status.ERROR_NETWORK);
                }
                changeStatus(Status.ERROR);
                System.out.println("Error [" + new String(error.networkResponse.data) + "]");
            }
        })
        {
            HttpEntity httpEntity;
            @Override
            public byte[] getBody() throws AuthFailureError
            {
                MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
                multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
                File imageFile = new File(image_path);
                multipartEntityBuilder.addTextBody("json", new GsonBuilder()
                        .excludeFieldsWithoutExposeAnnotation().create()
                        .toJson(data));
                multipartEntityBuilder.addBinaryBody("image", imageFile, ContentType.create("image/jpeg"), imageFile.getName());
                httpEntity = multipartEntityBuilder.build();

                try
                {
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    httpEntity.writeTo(bos);
                    return bos.toByteArray();
                }
                catch (UnsupportedEncodingException e)
                {
                    e.printStackTrace();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                return null;
            }
            @Override
            public String getBodyContentType()
            {
                return httpEntity.getContentType().getValue();
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Controller.networkRequestQueue.add(request);
    }

    @Override
    public void delete()
    {

    }

    @Override
    public void onStatusChange(Status status)
    {
        
    }

    @Override
    public void changeStatus(DataObject.Status status)
    {
        if(this.status == status)
        {
            return;
        }
        this.status = status;
        this.onStatusChange(status);
    }
}

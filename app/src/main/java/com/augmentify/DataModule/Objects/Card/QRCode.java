package com.augmentify.DataModule.Objects.Card;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.augmentify.DataModule.Controller.Controller;
import com.augmentify.DataModule.Objects.DataObject;
import com.augmentify.DataModule.Objects.Meta;
import com.augmentify.DataModule.SQLCache;
import com.augmentify.DataModule.Settings;
import com.augmentify.DataModule.Urls;
import com.augmentify.Debug;
import com.google.gson.annotations.Expose;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by Saurabh on 30/08/2014.
 */
public class QRCode implements DataObject
{

    @Expose
    public String data;
    @Expose
    public String image;

    public class QRCodes
    {
        @Expose
        Meta meta;
        @Expose
        public List<QRCode> objects;
    }

    void copyFields(QRCode qrCode)
    {
        this.data = qrCode.data;
        this.image = qrCode.image;
    }

    public static enum RESOURCE_TYPE
    {
        QR,
        QR_DETAIL
    }

    public String id;
    Status status;
    String requestUrl;
    RESOURCE_TYPE requestType;
    public JSONObject response;
    SQLCache qrCodeCache;
    public QRCodes qrCodes;
    Context context;

    public QRCode(Context context)
    {
        this.context = context;
        qrCodeCache = new SQLCache(context, Urls.PATH.QR);
    }

    @Override
    public void refresh()
    {
        switch(requestType)
        {
            case QR:
            {
                qrCodes = Controller.dataObjectBuilder.fromJson(response.toString(), QRCodes.class);
                copyFields(qrCodes.objects.get(0));
            }
            break;
            case QR_DETAIL:
            {
                copyFields(Controller.dataObjectBuilder.fromJson(response.toString(), QRCode.class));
            }
            break;
        }
    }


    public void setReadRequestParams(RESOURCE_TYPE requestType, String qrId)
    {
        this.id = qrId;
        this.requestType = requestType;
    }

    @Override
    public void read()
    {
        Uri.Builder uriBuilder = Uri.parse(Urls.SERVER)
                .buildUpon()
                .appendPath(Urls.API)
                .appendPath(Urls.VERSION)
                .appendPath(Urls.PATH.QR)
                .appendQueryParameter(Urls.REQUEST_KEY.FORMAT, "json")
                .appendQueryParameter(Urls.REQUEST_KEY.USERNAME, Settings.Account.username)
                .appendQueryParameter(Urls.REQUEST_KEY.API_KEY, Settings.Account.apiKey);

        switch(requestType)
        {
            case QR:
            {
                uriBuilder.appendQueryParameter(Urls.REQUEST_KEY.QR, id);
            }
            break;
            case QR_DETAIL:
            {
                uriBuilder.appendPath(id);
            }
            break;
        }

        uriBuilder.appendPath("");
        requestUrl = uriBuilder.build().toString();
        Log.d(Debug.TAG.NET, requestUrl);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, requestUrl, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject serverResponse)
                    {
                        response = serverResponse;
                        refresh();
                        changeStatus(DataObject.Status.READ_OK);
                    }
                }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                changeStatus(DataObject.Status.ERROR);
                Log.d(Debug.TAG.NET, error.toString());
            }
        });
        Controller.networkRequestQueue.add(request);
    }

    @Override
    public void create()
    {

    }

    @Override
    public void delete()
    {

    }

    @Override
    public void onStatusChange(DataObject.Status status)
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

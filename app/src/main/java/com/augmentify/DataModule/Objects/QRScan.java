package com.augmentify.DataModule.Objects;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.augmentify.DataModule.Controller.Controller;
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
public class QRScan implements DataObject
{
    public static final class QR_SCAN_TYPE
    {
        public static final String EVENT_JOINED = "JOINED";
        public static final String EVENT = "EVENT";
    }

    @Expose
    String qr;
    @Expose
    public String label;
    @Expose
    public String event;
    @Expose
    public String user;

    public class QRCodes
    {
        @Expose
        public List<ScanResult> objects;
    }

    public class ScanResult
    {
        @Expose
        public QRScan scan_result;
    }

    void copyFields(QRScan qrScan)
    {
        this.label = qrScan.label;
        this.event = qrScan.event;
    }

    public static enum RESOURCE_TYPE
    {
        QR_SCAN,
        QR_SCAN_DETAIL
    }

    public int id;
    public String qrCode;
    DataObject.Status status;
    String requestUrl;
    RESOURCE_TYPE requestType;
    public JSONObject response;
    SQLCache qrScanCache;
    public QRCodes qrCodes;
    Context context;

    public QRScan(Context context)
    {
        this.context = context;
        qrScanCache = new SQLCache(context, Urls.PATH.QR_SCAN);
    }

    @Override
    public void refresh()
    {
        switch(requestType)
        {
            case QR_SCAN:
            {
                qrCodes = Controller.dataObjectBuilder.fromJson(response.toString(), QRCodes.class);
                copyFields(qrCodes.objects.get(0).scan_result);
            }
            break;
            case QR_SCAN_DETAIL:
            {
                qrCodes = Controller.dataObjectBuilder.fromJson(response.toString(), QRCodes.class);
                copyFields(qrCodes.objects.get(0).scan_result);
            }
            break;
        }
    }


    public void setReadRequestParams(RESOURCE_TYPE requestType, String qrCode)
    {
        this.qrCode = qrCode;
        this.requestType = requestType;
    }

    public void setReadRequestParams(RESOURCE_TYPE requestType, String qrCode, int id)
    {
        this.id = id;
        this.qrCode = qrCode;
        this.requestType = requestType;
    }

    @Override
    public void read()
    {
        Uri.Builder uriBuilder = Uri.parse(Urls.SERVER)
                .buildUpon()
                .appendPath(Urls.API)
                .appendPath(Urls.VERSION)
                .appendQueryParameter(Urls.REQUEST_KEY.FORMAT, "json")
                .appendQueryParameter(Urls.REQUEST_KEY.USERNAME, Settings.Account.username)
                .appendQueryParameter(Urls.REQUEST_KEY.API_KEY, Settings.Account.apiKey);

        switch(requestType)
        {
            case QR_SCAN:
            {
                uriBuilder.appendQueryParameter(Urls.REQUEST_KEY.QR, qrCode)
                        .appendPath(Urls.PATH.QR_SCAN);
            }
            break;
            case QR_SCAN_DETAIL:
            {
                uriBuilder.appendQueryParameter(Urls.REQUEST_KEY.QR, qrCode)
                        .appendPath(Urls.PATH.QR_SCAN)
                        .appendPath(String.valueOf(id));
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

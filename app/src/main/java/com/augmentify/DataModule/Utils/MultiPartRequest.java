package com.augmentify.DataModule.Utils;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * Created by Saurabh on 28/11/2014.
 */
public class MultiPartRequest extends Request<String>
{

    //private MultipartEntity entity = new MultipartEntity();
    private MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
    private HttpEntity entity;

    private static final String FILE_PART_NAME = "image";
    private static final String STRING_PART_NAME = "json";

    private final Response.Listener<String> mListener;
    private final File mFilePart;
    private final String mStringPart;

    public MultiPartRequest(String url, Response.ErrorListener errorListener, Response.Listener<String> listener, File file, String stringPart)
    {
        super(Method.POST, url, errorListener);

        mListener = listener;
        mFilePart = file;
        mStringPart = stringPart;
        buildMultipartEntity();
    }

    private void buildMultipartEntity()
    {
        entityBuilder.addTextBody(STRING_PART_NAME, mStringPart);
        entityBuilder.addBinaryBody(FILE_PART_NAME, mFilePart, ContentType.create("image/jpeg"), mFilePart.getName());
        entity = entityBuilder.build();
    }

    @Override
    public String getBodyContentType()
    {
        return entity.getContentType().getValue();
    }

    @Override
    public byte[] getBody() throws AuthFailureError
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try
        {
            entity.writeTo(bos);
        }
        catch (IOException e)
        {
            VolleyLog.e("IOException writing to ByteArrayOutputStream");
        }
        return bos.toByteArray();
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response)
    {
        return Response.success("Uploaded", getCacheEntry());
    }

    @Override
    protected void deliverResponse(String response)
    {
        mListener.onResponse(response);
    }
}

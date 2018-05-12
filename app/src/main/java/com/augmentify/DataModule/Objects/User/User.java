package com.augmentify.DataModule.Objects.User;

import android.content.Context;

import com.google.gson.annotations.Expose;

/**
 * Created by Saurabh on 26/07/2014.
 */
public class User
{
    @Expose
    public String id;
    @Expose
    public String username;
    @Expose
    public String password;
    @Expose
    public String email;
    @Expose
    public String first_name;
    @Expose
    public String last_name;

    public User(Context context)
    {

    }


}

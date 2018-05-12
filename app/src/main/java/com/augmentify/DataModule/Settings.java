package com.augmentify.DataModule;

import android.content.Context;

import com.augmentify.DataModule.Objects.User.Profile;

/**
 * Created by Saurabh on 26/07/2014.
 */
public class Settings
{
    public static class SettingsException extends Exception
    {
        public SettingsException(String message, String error)
        {
            super(message+error);
        }
    }

    public static class Account
    {
        public static String username;
        public static String apiKey;
        public static String id;
        public static String firstName;
        public static String lastName;

        static SQLCache accountCache;

        static final String EXCEPTION_ERROR_MESSAGE = "Failed To Load Account Cache. Failure At=";
        public static void load(Context context) throws SettingsException
        {
            accountCache = new SQLCache(context, "settings_account");
            loadCache();
        }

        public static void loadCache() throws SettingsException
        {
            try
            {
                Account.username = accountCache.get(Urls.REQUEST_KEY.USERNAME);
                Account.apiKey = accountCache.get(Urls.REQUEST_KEY.API_KEY);
                Account.id = accountCache.get(Urls.REQUEST_KEY.ID);
                if(apiKey==null)
                {
                    throw new SettingsException(EXCEPTION_ERROR_MESSAGE, Urls.REQUEST_KEY.API_KEY);
                }
            }
            catch (SQLCache.KeyNotFoundException e)
            {
                throw new SettingsException(EXCEPTION_ERROR_MESSAGE, e.key);
            }
        }

        public static void set(String username, String apiKey)
        {
            Account.username = username;
            Account.apiKey = apiKey;
            accountCache.set(Urls.REQUEST_KEY.USERNAME, username);
            accountCache.set(Urls.REQUEST_KEY.API_KEY, apiKey);
        }

        public static void set(Profile profile)
        {
            Account.id = profile.user.id;
            accountCache.set(Urls.REQUEST_KEY.ID, Account.id);
        }

        public static void delete()
        {
            accountCache.delete(Urls.REQUEST_KEY.USERNAME);
            accountCache.delete(Urls.REQUEST_KEY.API_KEY);
        }

    }

    public static void load(Context context) throws SettingsException
    {
        Account.load(context);
    }

}

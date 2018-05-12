package com.augmentify.GUIModule.Fragments.Login;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.augmentify.DataModule.Controller.Controller;
import com.augmentify.DataModule.Objects.User.ApiKey;
import com.augmentify.DataModule.Objects.User.Profile;
import com.augmentify.DataModule.Settings;
import com.augmentify.Debug;
import com.augmentify.GUIModule.Fragments.FragmentController;
import com.augmentify.GUIModule.Utils.DisplayUtils;
import com.augmentify.R;
import com.augmentify.Resource;

/**
 * Created by Saurabh on 26/07/2014.
 */
public class LoginView extends RelativeLayout
{
    Context context;
    public LoginView(Context context)
    {
        super(context);
        this.context = context;
        LoginViewResource.setContext(context);
    }

    TextView title;
    View horizontalSeparator;
    EditText username;
    EditText password;
    RelativeLayout loginLayout;
    ImageView loginImage;
    TextView login;
    View loginHorizontalSeparator;
    RelativeLayout registerLayout;
    ImageView registerImage;
    TextView register;

    static LayoutParams params;

    ApiKey apiKey;
    Profile profile;

    public void build()
    {
        title = new TextView(context);
        title.setTextSize(TypedValue.COMPLEX_UNIT_SP, LoginViewResource.Title.fontSize);
        title.setText(LoginViewResource.Title.text);
        title.setTextColor(Color.parseColor(LoginViewResource.Title.fontColor));
        title.setPadding(LoginViewResource.Title.Padding.left,
                LoginViewResource.Title.Padding.top,
                LoginViewResource.Title.Padding.right,
                LoginViewResource.Title.Padding.bottom);
        title.setId(Resource.id++);
        params = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        this.addView(title, params);

        horizontalSeparator = new View(context);
        horizontalSeparator.setId(Resource.id++);
        horizontalSeparator
                .setBackgroundResource(R.drawable.horizontal_separator);
        params = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        params.height = LoginViewResource.HorizontalSeparator.height;
        params.addRule(RelativeLayout.BELOW, title.getId());
        this.addView(horizontalSeparator, params);

        username = new EditText(context);
        username.setId(Resource.id++);
        username.setHint(LoginViewResource.Username.text);
        username.setHintTextColor(Color
                .parseColor(LoginViewResource.Username.hintFontColor));
        username.setTextSize(TypedValue.COMPLEX_UNIT_SP,
                LoginViewResource.Username.fontSize);
        params = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.BELOW, horizontalSeparator.getId());
        this.addView(username, params);

        password = new EditText(context);
        password.setId(Resource.id++);
        password.setHint(LoginViewResource.Password.text);
        password.setHintTextColor(Color
                .parseColor(LoginViewResource.Password.hintFontColor));
        password.setTextSize(TypedValue.COMPLEX_UNIT_SP,
                LoginViewResource.Password.fontSize);
        params = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.BELOW, username.getId());
        this.addView(password, params);

        loginLayout = new RelativeLayout(context);
        loginLayout.setId(Resource.id++);

        loginImage = new ImageView(context);
        loginImage.setId(Resource.id++);
        loginImage.setImageResource(R.drawable.card_event_image);
        params = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        params.height = LoginViewResource.Login.Image.height;
        params.width = LoginViewResource.Login.Image.width;
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        loginLayout.addView(loginImage, params);

        login = new TextView(context);
        login.setId(Resource.id++);
        login.setText(LoginViewResource.Login.text);
        login.setTextSize(TypedValue.COMPLEX_UNIT_SP,
                LoginViewResource.Login.fontSize);
        login.setTextColor(Color
                .parseColor(LoginViewResource.Login.fontColor));
        params = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.RIGHT_OF, loginImage.getId());
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        loginLayout.addView(login, params);

        loginLayout
                .setOnTouchListener(new DisplayUtils.OnTouchButtonBackgroundSet());
        loginLayout.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(buildDataFromView())
                {
                    apiKey.read();
                }
            }
        });
        loginLayout.setBackgroundResource(R.drawable.button);
        params = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.addRule(RelativeLayout.BELOW, password.getId());
        this.addView(loginLayout, params);

        loginHorizontalSeparator = new View(context);
        loginHorizontalSeparator.setId(Resource.id++);
        loginHorizontalSeparator
                .setBackgroundResource(R.drawable.horizontal_separator);
        params = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        params.height = LoginViewResource.HorizontalSeparator.height;
        params.addRule(RelativeLayout.BELOW, loginLayout.getId());
        this.addView(loginHorizontalSeparator, params);

        registerLayout = new RelativeLayout(context);
        registerLayout.setId(Resource.id++);

        registerImage = new ImageView(context);
        registerImage.setId(Resource.id++);
        registerImage.setImageResource(R.drawable.card_event_image);
        params = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        params.height = LoginViewResource.Register.Image.height;
        params.width = LoginViewResource.Register.Image.width;
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        registerLayout.addView(registerImage, params);

        register = new TextView(context);
        register.setId(Resource.id++);
        register.setText(LoginViewResource.Register.text);
        register.setTextSize(TypedValue.COMPLEX_UNIT_SP,
                LoginViewResource.Register.fontSize);
        register.setTextColor(Color
                .parseColor(LoginViewResource.Register.fontColor));
        params = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.RIGHT_OF, registerImage.getId());
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        registerLayout.addView(register, params);

        registerLayout
                .setOnTouchListener(new DisplayUtils.OnTouchButtonBackgroundSet());
        registerLayout.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FragmentController.show(FragmentController.Fragment.REGISTER_VIEW_FRAGMENT);
            }
        });
        registerLayout.setBackgroundResource(R.drawable.button);
        params = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.addRule(RelativeLayout.BELOW, loginHorizontalSeparator.getId());
        this.addView(registerLayout, params);
        
        this.setBackgroundColor(Color.TRANSPARENT);
        this.setId(Resource.id++);
    }

    boolean buildDataFromView()
    {
        if(username.getText().toString().matches(""))
        {
            username.setHintTextColor(Color.parseColor(LoginViewResource.Username.hintErrorFontColor));
            return false;
        }
        Log.d(Debug.TAG.GUI, username.getText().toString());
        if(password.getText().toString().matches(""))
        {
            password.setHintTextColor(Color.parseColor(LoginViewResource.Password.hintErrorFontColor));
            return false;
        }

        apiKey = new ApiKey(context)
        {
            @Override
            public void onStatusChange(Status status)
            {
                switch(status)
                {
                    case READ_OK:
                    {
                        FragmentController.dismiss(FragmentController.Fragment.LOGIN_VIEW_FRAGMENT);
                        Settings.Account.set(this.username, this.key);
                        profile.setReadRequestParams(Profile.RESOURCE_TYPE.PROFILE, 0);
                        profile.read();
                        Controller.build();
                    }
                    break;
                    case ERROR_AUTHENTICATION:
                    {
                        Toast.makeText(context, "Wrong Username/Password", Toast.LENGTH_SHORT).show();
                    }
                    break;
                    case ERROR_NETWORK:
                    {
                        Toast.makeText(context, "Network Unavailable or Invalid Username/Password", Toast.LENGTH_SHORT).show();
                    }
                    break;
                    case ERROR:
                    {
                        Toast.makeText(context, "Generic Error", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
            }
        };

        profile = new Profile(context)
        {
            @Override
            public void onStatusChange(Status status)
            {
                switch(status)
                {
                    case READ_OK:
                    {
                        Settings.Account.set(this);
                    }
                    break;
                }
            }
        };

        apiKey.setReadRequestParams(ApiKey.RESOURCE_TYPE.API_KEY, username.getText().toString(), password.getText().toString(), 0);
        return true;
    }
}

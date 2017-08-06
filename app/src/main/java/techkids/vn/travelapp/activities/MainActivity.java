package techkids.vn.travelapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import techkids.vn.travelapp.R;
import techkids.vn.travelapp.models.ProfileModel;

public class MainActivity extends AppCompatActivity {
    private ProfileModel profileModel = new ProfileModel();
    private static final String TAG = MainActivity.class.toString();
    CallbackManager callbackManager;
    LoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.khaibao();
        this.loginButtonOnClick();
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);              //Gọi lại hàm request để đổ dữ liệu
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void khaibao() {
        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("public_profile", "email"));
    }

    private void loginButtonOnClick() {
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.d(TAG, String.format("JSON_profile: %s", response.getJSONObject().toString()));
                        try {
                            profileModel.setId(object.getString("id"));
                            profileModel.setMail(object.getString("email"));
                            profileModel.setName(object.getString("name"));

                            EventBus.getDefault().postSticky(profileModel); //Chuyển dữ liệu profile sang Home Screen Activity
                            Intent myIntent = new Intent(MainActivity.this, HomeScreenActivity.class);
                            startActivity(myIntent);
                            Log.d(TAG, String.format("show: %s, %s, %s", profileModel.getId(), profileModel.getMail(), profileModel.getName()));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                graphRequest.setParameters(parameters);
                graphRequest.executeAsync();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    @Override
    protected void onStart() {      //log out facbook khi onStart
        LoginManager.getInstance().logOut();
        super.onStart();
    }
}

package apps.cohen.bali.login;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;


import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author alex
 * @date 2015-05-06
 */
public class FacebookLogin implements FacebookCallback<LoginResult> {
    private final Listener mListener;
    private CallbackManager mFacebookCallback;

    public FacebookLogin(Fragment fragment, LoginButton button, Listener listener) {
        mListener = listener;
        mFacebookCallback = CallbackManager.Factory.create();
        // Other app specific specialization
        button.setReadPermissions("public_profile", "email");
        // If using in a fragment
        button.setFragment(fragment);
        button.registerCallback(mFacebookCallback, this);

        if (AccessToken.getCurrentAccessToken() != null) {
            requestUserInfo(AccessToken.getCurrentAccessToken());
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mFacebookCallback.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onSuccess(LoginResult loginResult) {
        Log.d("FacebookCallback", "onSuccess");

        requestUserInfo(loginResult.getAccessToken());
    }

    @Override
    public void onCancel() {
        Log.d("FacebookCallback", "onCancel");
    }

    @Override
    public void onError(FacebookException e) {
        Log.e("FacebookCallback", e.getMessage(), e);
    }

    private void requestUserInfo(AccessToken accessToken) {
        GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {

            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {

                try {
                    mListener.onFacebookLogin(object.getString("email"), object.getString("first_name"), object.getString("last_name"));
                } catch (JSONException e) {
                    Log.e("FacebookCallback", e.getMessage(), e);
                }

            }
        });
        request.executeAsync();
    }

    public interface Listener {
        void onFacebookLogin(String email, String firstName, String lastName);
    }
}

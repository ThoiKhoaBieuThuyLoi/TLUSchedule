package vn.lcsoft.luongchung.tluschedule;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import vn.lcsoft.luongchung.models.User;

public class LoginChat extends AppCompatActivity {

    DatabaseReference mDatabase;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_chat);
        addControlls();
        getInformationUserLogin();




        //kiểm tra đăng nhập hay chưa
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if (isLoggedIn) {
            Intent intent = new Intent(LoginChat.this, ChatMain.class);
            startActivity(intent);
        }
    }


    private void getInformationUserLogin() {
        GraphRequest graphRequest =GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {}
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email");
        graphRequest.setParameters(parameters);
        graphRequest.executeAsync();

    }
    private void addControlls() {
        TextView txtly=findViewById(R.id.txtLuuY);
        txtly.setText(getString(R.string.khoaname));
        TextView txtDN=findViewById(R.id.txtDangNhap);
        Typeface font = Typeface.createFromAsset(this.getAssets(),"fonts/fontmain.ttf");
        txtDN.setTypeface(font);
        txtly.setTypeface(font);
        FacebookSdk.sdkInitialize(getApplicationContext());
        mDatabase = FirebaseDatabase.getInstance().getReference();
        callbackManager = CallbackManager.Factory.create();
        loginButton = findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("public_profile", "email", "user_birthday", "user_friends"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(LoginChat.this,getString(R.string.dangnhapthanhcong), Toast.LENGTH_LONG).show();
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                User us;
                                String email = "null";
                                String id = "null" ;
                                String birthday =  "null";
                                String name = "null";
                                String gender ="null";
                                try {
                                    email = object.getString("email");
                                } catch (Exception e) {

                                }
                                try {
                                    birthday = object.getString("birthday");
                                } catch (Exception e) {

                                }
                                try {
                                    id = object.getString("id");
                                } catch (Exception e) {

                                }
                                try {
                                    name = object.getString("name");
                                } catch (Exception e) {

                                }
                                try {
                                    gender = object.getString("gender");
                                } catch (Exception e) {

                                }
                                us = new User(
                                        id,
                                        name,
                                        email,
                                        gender,
                                        birthday,
                                        FirebaseInstanceId.getInstance().getToken()==null?"null":FirebaseInstanceId.getInstance().getToken()
                                );
                                mDatabase.child("User").child(id).setValue(us);
                                if( !id.equals("null")){
                                    SharedPreferences sharedPreferences= getSharedPreferences("userfacebook",MODE_PRIVATE);
                                    SharedPreferences.Editor editor=sharedPreferences.edit();
                                    editor.putString("id",id);
                                    editor.commit();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                request.setParameters(parameters);
                request.executeAsync();

                Intent intent_ttd=new Intent(LoginChat.this,ChatMain.class);
                startActivity(intent_ttd);
            }
            @Override
            public void onCancel() { }
            @Override
            public void onError(FacebookException e) {
                Toast.makeText(LoginChat.this,getString(R.string.dangnhapkhongthanhcong), Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}

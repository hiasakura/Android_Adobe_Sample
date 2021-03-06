package net.hiroasak.shoppingdemoex;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

import com.adobe.mobile.*;
import com.google.android.gms.gcm.GcmPubSub;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by h_asakura on 2015/10/31.
 * プッシュメッセージのToken生成用クラス
 */
public class RegistrationIntentService extends IntentService {

    private static final String TAG = "RegIntentService";
    private static final String[] TOPICS = {"global"};
    private String SENDER_ID ="265054887078";

    public GoogleCloudMessaging gcm;
    private static final String PROPERTY_REG_ID = "registration_id";
    private static final String PROPERTY_APP_VERSION = "appVersion";

    public RegistrationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        try {
            InstanceID instanceID = InstanceID.getInstance(this);
            String token = instanceID.getToken(SENDER_ID, GoogleCloudMessaging.INSTANCE_ID_SCOPE);

            // GCMサーバにプロジェクトを保存
            // gcm.register(SENDER_ID);

            // Adobeサーバにtokenを保存
            sendRegistrationToServer(token);

            sharedPreferences.edit().putBoolean(Const.SENT_TOKEN_TO_SERVER, true).apply();
        } catch (Exception e) {
            Log.d(TAG, "Failed to complete token refresh", e);
            sharedPreferences.edit().putBoolean(Const.SENT_TOKEN_TO_SERVER, false).apply();
        }
        Intent registrationComplete = new Intent(Const.REGISTRATION_COMPLETE);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }

    // Tokenの登録
    private void sendRegistrationToServer(String token) {
        Config.setPushIdentifier(token);

        Log.i(TAG, "GCM Registration Token: " + token);
        Log.i(TAG, "UserID: " + Config.getUserIdentifier());
        Log.i(TAG, "MCID: " + Visitor.getMarketingCloudId());
        HashMap contextData = new HashMap<String, Object>();

        // Tokenはdemdexに送られているが検証用に取得しておく
        contextData.put("mcid", Visitor.getMarketingCloudId());
        contextData.put("tokeninfo", token);

        Analytics.trackAction("generateToken", contextData);

    }
}
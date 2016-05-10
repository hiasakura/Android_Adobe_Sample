package net.hiroasak.shoppingdemoex;
import android.content.Intent;
import android.app.Activity;
import android.content.Context;
import android.content.ComponentName;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by h_asakura on 2015/09/26.
 * プッシュメッセージの受信用クラス
 */
//public class GcmBroadcastReceiver {
public class GcmBroadcastReceiver extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ComponentName comp = new ComponentName(context.getPackageName(), GcmIntentService.class.getName());
        startWakefulService(context, (intent.setComponent(comp)));
        setResultCode(Activity.RESULT_OK);

        // DeepLink対応
        Bundle extra = intent.getExtras();
        if(extra != null) {
            String link = extra.getString("adb_deeplink");
            String target = extra.getString("target");
            String msg = extra.getString("msg");
            String sound = extra.getString("sound");
            String from = extra.getString("from");
        }
    }
}


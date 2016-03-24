package net.hiroasak.shoppingdemoex;

import com.adobe.mobile.*;
import android.content.BroadcastReceiver; import android.content.Context;
import android.content.Intent;

/**
 * Created by h_asakura on 2015/09/26.
 * 獲得リンク計測用クラス
 */
public class GPBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context c, Intent i) {
        Analytics.processReferrer(c, i);
    }
}

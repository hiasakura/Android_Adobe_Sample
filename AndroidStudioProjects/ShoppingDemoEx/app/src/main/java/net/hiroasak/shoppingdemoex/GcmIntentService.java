package net.hiroasak.shoppingdemoex;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by h_asakura on 2015/09/26.
 * プッシュメッセージの表示用クラス
 */
public class GcmIntentService extends IntentService{

    private static final String SENDER_ID = "265054887078";
    private static final String TAG = "GcmIntentService";
    private static final String TICKER = "Special Offer";
    private static final String TITLE = "New Message From Hirofumi Apps!";
    private int number = 0;

    public GcmIntentService() {
        super(SENDER_ID);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Bundle extras = intent.getExtras();

        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        String messageType = gcm.getMessageType(intent);

        if (!extras.isEmpty()) {

            Intent notificationIntent = new Intent(this, TopActivity.class);
            PendingIntent pendingIntent =
                    PendingIntent.getActivity(this, 0, notificationIntent, 0);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
            builder.setContentIntent(pendingIntent);
            builder.setTicker(TICKER);
            builder.setContentTitle(TITLE);
            builder.setContentText(extras.getString("message"));
            builder.setContentInfo("info");
            builder.setWhen(System.currentTimeMillis());
            builder.setContentIntent(pendingIntent);
            builder.setSmallIcon(android.R.drawable.ic_menu_info_details);
            Notification notification = builder.build();

            NotificationManager notificationManager = (NotificationManager)
                    this.getSystemService(Context.NOTIFICATION_SERVICE);
            notification.flags = Notification.FLAG_AUTO_CANCEL;
            notificationManager.notify(1, notification);

            Log.d(TAG,"messageType: " + messageType + ",body:" + extras.toString());

        }
        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }
}
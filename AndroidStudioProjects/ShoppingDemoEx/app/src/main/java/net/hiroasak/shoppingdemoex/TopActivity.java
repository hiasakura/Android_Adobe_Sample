package net.hiroasak.shoppingdemoex;
import com.adobe.mobile.*;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.Gravity;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.webkit.WebView;
import android.provider.Settings;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;

import android.support.v4.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;


import java.util.*;


/** プッシュメッセージ用 **/
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import android.util.Log;

/**
 * Created by h_asakura on 2015/09/15.
 * Main Activityクラス
 */
public class TopActivity extends Activity implements View.OnClickListener,LocationListener{

    // 商品情報生成
    private Boolean isData = ItemData.createItemData();
    private String sportMsg = "スポーツしようぜ！";
    private String foodMsg = "食欲の秋！";
    private String fashionMsg = "いろんな服を着よう！";

    /** プッシュメッセージ用 **/
    private int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private String THIS_CLASS ="TopActivity";

    /** ロケーション管理用 **/
    private LocationManager mLocationManager;


    @Override
    public void onCreate(Bundle bundle){

        super.onCreate(bundle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Adobe計測用コード
        Config.setContext(this.getApplicationContext());
//        String vid = Visitor.getMarketingCloudId();
//        Config.setUserIdentifier(vid);

        // 2016.09/13 Tracking Code
        String trackingId = Analytics.getTrackingIdentifier();

        Config.setDebugLogging(true);

        // Intentが存在した場合の処理
        Bundle extra = getIntent().getExtras();
        if(extra != null) {
            String link = extra.getString(Const.PUSH_LINK_NAME);
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
            startActivity(i);
        }

        // 位置情報の設定処理
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        // レイアウト生成
        TableLayout layout = new TableLayout(this);
        layout.setStretchAllColumns(true);
        layout.setLayoutParams(Util.createParam(Const.MP, Const.WC));
        layout.setBackgroundColor(Color.WHITE);
        setContentView(layout);

        // リンク生成
        TableRow tableRow1 = new TableRow(this);
        tableRow1.addView(makeImage(R.drawable.sports_cate, Const.TAG_CATE_SPORTS));
        tableRow1.addView(makeText(sportMsg, Const.TAG_CATE_SPORTS));
        tableRow1.setGravity(Gravity.CENTER_VERTICAL);

        TableRow tableRow2 = new TableRow(this);
        tableRow2.addView(makeImage(R.drawable.food_cate, Const.TAG_CATE_FOOD));
        tableRow2.addView(makeText(foodMsg, Const.TAG_CATE_FOOD));
        tableRow2.setGravity(Gravity.CENTER_VERTICAL);

        TableRow tableRow3 = new TableRow(this);
        tableRow3.addView(makeImage(R.drawable.clothes_cate, Const.TAG_CATE_CLOTHES));
        tableRow3.addView(makeText(fashionMsg, Const.TAG_CATE_CLOTHES));
        tableRow3.setGravity(Gravity.CENTER_VERTICAL);

        // テーブル情報セット
        layout.addView(tableRow1, Util.createParam(Const.WC, Const.WC));
        layout.addView(tableRow2, Util.createParam(Const.WC, Const.WC));
        layout.addView(tableRow3, Util.createParam(Const.WC, Const.WC));

        HashMap<String, Object> contextData = new HashMap<String, Object>();
        contextData.put("prop1", this.getLocalClassName());
        contextData.put("eVar2", trackingId);
        Analytics.trackState(this.getLocalClassName(), contextData);

        /** プッシュメッセージ用処理 */
        if (checkPlayServices()) {
            Intent intent = new Intent(this, RegistrationIntentService.class);
            startService(intent);
        }
    }

    @Override
    public void onClick(View v){
        Intent intent = null;

        // スポーツ商品一覧
        if (Const.TAG_CATE_SPORTS.equals(v.getTag())) {
            intent = new Intent(this, ItemSportsList.class);
            intent.putExtra(Const.ITEM_KEY_CATE, v.getTag().toString());
            startActivityForResult(intent, Const.REQUEST_TEXT);

        // 食品商品一覧
        } else if (Const.TAG_CATE_FOOD.equals(v.getTag())) {
            intent = new Intent(this, ItemFoodList.class);
            intent.putExtra(Const.ITEM_KEY_CATE, v.getTag().toString());
            startActivityForResult(intent, Const.REQUEST_TEXT);

        // ファッション商品一覧
        } else if (Const.TAG_CATE_CLOTHES.equals(v.getTag())) {
            intent = new Intent(this, ItemFashionList.class);
            intent.putExtra(Const.ITEM_KEY_CATE, v.getTag().toString());
            startActivityForResult(intent, Const.REQUEST_TEXT);

        // 外部リンク
        } else if (Const.TAG_EX_BANNER.equals(v.getTag())) {

            HashMap contextData = new HashMap<String, Object>();
            contextData.put("&&events", "event1");
            contextData.put("prop3", "Adobe_Marketing_Cloud_Link");
            Analytics.trackAction("clickAdLink", contextData);

            WebView webView = Util.requestWebView(this);
            setContentView(webView);
            webView.loadUrl(Const.WEB_VIEW_ADOBE_URL);
        }
    }

    @Override
    public void onResume() {

        // ロケーション取得判定
/*
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
        }
        else{
            if (mLocationManager != null) {
                mLocationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        0,
                        0,
                        this);
                Location location = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                onLocationChanged(location);
            }

        }
*/
        super.onResume();
        Config.collectLifecycleData(this);
    }

    @Override
    public void onPause() {

        // ロケーション取得判定
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
        }
        else{
            if (mLocationManager != null) {
                mLocationManager.removeUpdates(this);
            }
        }

        super.onPause();
        Config.pauseCollectingLifecycleData();
    }

    // テキスト生成
    private TextView makeText(String text, String tag) {
        TextView textview = Util.createText(this, text, tag, true);
        textview.setOnClickListener(this);
        return textview;
    }

    // ボタン生成
    private ImageButton makeImage(int id,String tag) {
        ImageButton imgButton = Util.createImage(this, id, tag, true);
        imgButton.setOnClickListener(this);
        return imgButton;
    }

    // プッシュメッセージ用サービス判定
    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                Log.i(THIS_CLASS, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }

    // ロケーション取得用の実装
    @Override
    public void onLocationChanged(Location location) {
        HashMap<String, Object> contextData = new HashMap<String, Object>();
        contextData.put("prop6", String.valueOf(location.getLatitude()));
        contextData.put("prop7", String.valueOf(location.getLongitude()));
        Analytics.trackAction("LocationTraffic", contextData);
        Analytics.trackLocation(location, contextData);

    }

    @Override
    public void onProviderDisabled(String provider) {
    }
    @Override
    public void onProviderEnabled(String provider) {
    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        switch (status) {
            case LocationProvider.AVAILABLE:
                break;
            case LocationProvider.OUT_OF_SERVICE:
                break;
            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                break;
        }
    }
}
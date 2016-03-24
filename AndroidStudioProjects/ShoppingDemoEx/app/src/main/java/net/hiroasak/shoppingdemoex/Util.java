package net.hiroasak.shoppingdemoex;

import android.app.Activity;
import android.content.Intent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TextView;
import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.Toast;

/**
 * Created by h_asakura on 2015/09/23.
 * Utilityクラス
 */
public class Util {

    // レイアウト設定
    public static TableLayout.LayoutParams createParam(int w, int h) {
        return new TableLayout.LayoutParams(w, h);
    }

    // TOPに戻る処理
    public static void backTopActivity(Activity activity) {
        Intent intent = new Intent(activity, TopActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        activity.startActivity(intent);
    }

    // テキスト生成共通処理
    public static TextView createText(Activity a, String text, String tag, Boolean isClickable) {
        TextView textview = new TextView(a);
        textview.setClickable(isClickable);
        textview.setBackgroundColor(Color.WHITE);
        textview.setText(text);
        textview.setTag(tag);
        textview.setTypeface(Typeface.DEFAULT);
        return textview;
    }

    // ボタン生成共通処理
    public static ImageButton createImage(Activity a, int id, String tag, Boolean isClickable) {
        ImageButton imgButton = new ImageButton(a);
        imgButton.setImageResource(id);
        imgButton.setClickable(isClickable);
        imgButton.setBackgroundColor(Color.WHITE);
        imgButton.setTag(tag);
        imgButton.setPadding(25, 25, 25, 25);
        return imgButton;
    }

    // WebView生成処理
    public static WebView requestWebView(Activity a) {
        WebView webView = new WebView(a);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String url) {
            }

        });

        return webView;
    }

    // 待機処理
    public static void waitCall(long time){
        try {
            Thread.sleep(time);
        }catch(InterruptedException e){

        }
    }
}
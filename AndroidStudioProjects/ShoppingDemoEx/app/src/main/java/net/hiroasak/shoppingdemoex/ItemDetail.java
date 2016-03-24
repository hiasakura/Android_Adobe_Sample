package net.hiroasak.shoppingdemoex;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.adobe.mobile.*;

import java.util.HashMap;

/**
 * Created by h_asakura on 2015/09/16.
 * 商品詳細用ページクラス
 */
public class ItemDetail extends Activity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle bundle) {

        super.onCreate(bundle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // 戻り値設定
        setResult(Activity.RESULT_OK);

        // インテントからのパラメータ取得
        String prdId = "";
        ItemData itm = new ItemData();
        Bundle extras = getIntent().getExtras();
        if (extras != null) prdId = extras.getString(Const.INTENT_KEY_PRODUCT);

        // レイアウト生成
        TableLayout layout = new TableLayout(this);
        layout.setBackgroundColor(Color.WHITE);
        layout.setColumnStretchable(2, true);
        setContentView(layout);

        // 商品画像処理
        String pics01 = prdId + Const.IMG_FILE_LEFT;
        int picId01 = getResources().getIdentifier(pics01, Const.DIR_IMAGE, getPackageName());
        Bitmap bitmap01 = BitmapFactory.decodeResource(getResources(), picId01);
        ImageView itemImg01 = new ImageView(this);
        itemImg01.setImageBitmap(bitmap01);

        String pics02 = prdId + Const.IMG_FILE_RIGHT;
        int picId02 = getResources().getIdentifier(pics02, Const.DIR_IMAGE, getPackageName());
        Bitmap bitmap02 = BitmapFactory.decodeResource(getResources(), picId02);
        ImageView itemImg02 = new ImageView(this);
        itemImg02.setImageBitmap(bitmap02);

        TableRow tableRow0 = new TableRow(this);
        tableRow0.addView(itemImg01);
        tableRow0.addView(itemImg02);
        tableRow0.setGravity(Gravity.LEFT);

        // テキスト情報
        TableRow.LayoutParams rowLayout = new TableRow.LayoutParams();
        rowLayout.span = 2;

        TableRow tableRow1 = new TableRow(this);
        TableRow tableRow2 = new TableRow(this);
        TableRow tableRow3 = new TableRow(this);
        tableRow1.addView(makeText(itm.getItemData(prdId,Const.ITEM_KEY_TITLE)),rowLayout);
        tableRow2.addView(makeText(itm.getItemData(prdId,Const.ITEM_KEY_PRICE)),rowLayout);
        tableRow3.addView(makeText(itm.getItemData(prdId,Const.ITEM_KEY_ARRIVAL)),rowLayout);
        tableRow3.setGravity(Gravity.CENTER_VERTICAL);

        // 購入ボタン
        TableRow tableRow4 = new TableRow(this);
        tableRow4.addView(makeImage(R.drawable.dnp_nav_buy_now, prdId), rowLayout);
        tableRow4.setGravity(Gravity.CENTER_VERTICAL);

        // テーブル設定
        layout.addView(tableRow0, Util.createParam(Const.WC, Const.WC));
        layout.addView(tableRow1, Util.createParam(Const.WC, Const.WC));
        layout.addView(tableRow2, Util.createParam(Const.WC, Const.WC));
        layout.addView(tableRow3, Util.createParam(Const.WC, Const.WC));
        layout.addView(tableRow4, Util.createParam(Const.WC, Const.WC));

        HashMap<String, Object> contextData = new HashMap<String, Object>();
        contextData.put("prop1", this.getLocalClassName());
        contextData.put("eVar2", this.getLocalClassName());
        contextData.put("&&events ", "event2");
        contextData.put("&&products", ";"+prdId+";1;"+itm.getItemData(prdId,Const.ITEM_KEY_PRICE));
        Analytics.trackState(this.getLocalClassName(), contextData);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, PurchaseActivity.class);
        intent.putExtra(Const.INTENT_KEY_PRODUCT, v.getTag().toString());
        startActivityForResult(intent, Const.REQUEST_TEXT);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Config.collectLifecycleData(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Config.pauseCollectingLifecycleData();
    }

    // テキスト生成
    private TextView makeText(String text){
        TextView textview = Util.createText(this, text, null, false);
        return textview;
    }

    // ボタン生成
    private ImageButton makeImage(int id,String tag){
        ImageButton imgButton = Util.createImage(this, id, tag, false);
        imgButton.setOnClickListener(this);
        return imgButton;
    }
}

package net.hiroasak.shoppingdemoex;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.view.KeyEvent;
import com.adobe.mobile.*;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by h_asakura on 2015/09/15.
 * 購入完了用ページクラス
 */
public class PurchaseActivity extends Activity implements View.OnClickListener{

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Disable Back key
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onCreate(Bundle bundle){

        super.onCreate(bundle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // 戻り値設定
        setResult(Activity.RESULT_OK);

        // インテントからのパラメータ取得
        String prdId = "";
        String prdPrice = "";
        String prdCategory = "";
        Bundle extras = getIntent().getExtras();
        ItemData itm = new ItemData();
        if (extras != null) prdId = extras.getString(Const.INTENT_KEY_PRODUCT);
        prdPrice = itm.getItemData(prdId,Const.ITEM_KEY_PRICE);
        prdCategory = itm.getItemData(prdId,Const.ITEM_KEY_CATE);

        // レイアウト生成
        TableLayout layout = new TableLayout(this);
        layout.setStretchAllColumns(true);
        layout.setLayoutParams(Util.createParam(Const.MP, Const.WC));
        layout.setBackgroundColor(Color.WHITE);
        setContentView(layout);

        // リンク生成
        TableRow tableRow1 = new TableRow(this);
        tableRow1.addView(makeText("Thank You！", Const.TAG_THANKS_TEXT));
        tableRow1.addView(makeImage(R.drawable.thankyou, Const.TAG_THANKS_IMG));
        tableRow1.setGravity(Gravity.CENTER_VERTICAL);

        TableRow tableRow2 = new TableRow(this);
        TableRow.LayoutParams rowLayout = new TableRow.LayoutParams();
        rowLayout.span = 2;
        tableRow2.addView(makeImage(R.drawable.back, Const.TAG_BACK_TOP), rowLayout);
        tableRow2.setGravity(Gravity.CENTER_VERTICAL);

        // テーブル情報セット
        layout.addView(tableRow1, Util.createParam(Const.MP, Const.WC));
        layout.addView(tableRow2, Util.createParam(Const.MP, Const.WC));

        Analytics.trackState(this.getLocalClassName(), null);

        HashMap<String, Object> pageData = new HashMap<String, Object>();
        HashMap<String, Object> clickData = new HashMap<String, Object>();
        pageData.put("prop1", this.getLocalClassName());
        pageData.put("eVar2", this.getLocalClassName());
        clickData.put("&&events ", "purchase");
        clickData.put("purchaseid", UUID.randomUUID().toString());
        clickData.put("&&products", ";" + prdId + ";1;" + prdPrice + "");
        Analytics.trackState(this.getLocalClassName(), pageData);
        Analytics.trackAction("purchase", clickData);
    }

    @Override
    public void onClick(View v){
        Util.backTopActivity(this);
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
    private TextView makeText(String text, String tag){
        TextView textview = Util.createText(this, text, null, true);
        textview.setOnClickListener(this);
        return textview;
    }

    // ボタン生成
    private ImageButton makeImage(int id,String tag){
        ImageButton imgButton = Util.createImage(this, id, tag, true);
        imgButton.setOnClickListener(this);
        return imgButton;
    }

}
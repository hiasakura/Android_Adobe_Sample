package net.hiroasak.shoppingdemoex;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.adobe.mobile.*;

import java.util.HashMap;

/**
 * Created by h_asakura on 2015/09/15.
 * Fashion商品一覧クラス
 */
public class ItemFashionList extends Activity implements View.OnClickListener{

    @Override
    public void onCreate(Bundle bundle) {

        super.onCreate(bundle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // 戻り値設定
        setResult(Activity.RESULT_OK);

        // Target通知用リクエスト
        Config.setContext(this.getApplicationContext());
        TargetLocationRequest viewedMbox = Target.createRequest(Const.MBOX_CATE_FASHION_VIEWED, Const.MBOX_SUCCESS_TEXT, null);
        Target.loadRequest(viewedMbox, new Target.TargetCallback<String>() {@Override public void call(String item) {}});

        // インテントからのパラメータ取得
        String cateName = "";
        Bundle extras = getIntent().getExtras();
        if (extras != null) cateName = extras.getString(Const.ITEM_KEY_CATE);

        // レイアウト生成
        TableLayout layout = new TableLayout(this);
        layout.setColumnStretchable(2, true);
        layout.setBackgroundColor(Color.WHITE);
        setContentView(layout);

        // 商品情報取得
        ItemData itm = new ItemData();

        // リンク生成
        TableRow tableRow1 = new TableRow(this);
        tableRow1.addView(makeImage(R.drawable.item_eyegalss, Const.PRD_ID_300001));
        tableRow1.addView(makeText(itm.getItemData(Const.PRD_ID_300001, Const.ITEM_KEY_MSG), Const.PRD_ID_300001));
        tableRow1.setGravity(Gravity.CENTER_VERTICAL);
        layout.addView(tableRow1, Util.createParam(Const.MP, Const.WC));

        HashMap<String, Object> contextData = new HashMap<String, Object>();
        contextData.put("prop1", this.getLocalClassName());
        contextData.put("prop2", cateName);
        contextData.put("eVar2", this.getLocalClassName());
        Analytics.trackState(this.getLocalClassName(), contextData);
    }

    @Override
    public void onClick(View v){
        Intent intent = new Intent(this, ItemDetail.class);
        intent.putExtra(Const.INTENT_KEY_PRODUCT, v.getTag().toString());
        startActivityForResult(intent, Const.REQUEST_TEXT);
    }

    @Override
    public void onResume() {
        super.onResume();
        Config.collectLifecycleData(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        Config.pauseCollectingLifecycleData();
    }

    // テキスト生成
    private TextView makeText(String text, String tag){
        TextView textview = Util.createText(this, text, tag, true);
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

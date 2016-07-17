package net.hiroasak.shoppingdemoex;
import java.util.HashMap;
/**
 * Created by h_asakura on 2015/09/23.
 * 商品情報定義用クラス
 */
public class ItemData {

    private static HashMap<String, HashMap<String, String>> ItemDataInfo = new HashMap<String, HashMap<String, String>>();
    private static HashMap<String, String> ItemDetail101 = new HashMap<String, String>();
    private static HashMap<String, String> ItemDetail102 = new HashMap<String, String>();
    private static HashMap<String, String> ItemDetail103 = new HashMap<String, String>();
    private static HashMap<String, String> ItemDetail201 = new HashMap<String, String>();
    private static HashMap<String, String> ItemDetail202 = new HashMap<String, String>();
    private static HashMap<String, String> ItemDetail203 = new HashMap<String, String>();
    private static HashMap<String, String> ItemDetail301 = new HashMap<String, String>();
    private static HashMap<String, String> ItemDetail302 = new HashMap<String, String>();
    private static HashMap<String, String> ItemDetail303 = new HashMap<String, String>();
    public static Boolean createItemData() {

        /*スポーツ関連*/
        // 商品101
        ItemDetail101.put(Const.ITEM_KEY_CATE, "スポーツ");
        ItemDetail101.put(Const.ITEM_KEY_MSG, "超頑丈なヘルメット!!");
        ItemDetail101.put(Const.ITEM_KEY_TITLE, "素晴らしいヘルメットが完成しました！");
        ItemDetail101.put(Const.ITEM_KEY_PRICE, "¥4,200");
        ItemDetail101.put(Const.ITEM_KEY_ARRIVAL, "発送予定：1週間以内");
        ItemDataInfo.put(Const.PRD_ID_100001, ItemDetail101);

        /*食品関連*/
        // 商品201
        ItemDetail201.put(Const.ITEM_KEY_CATE, "食品");
        ItemDetail201.put(Const.ITEM_KEY_MSG, "スーパーバナナ!");
        ItemDetail201.put(Const.ITEM_KEY_TITLE, "ただのバナナではありません");
        ItemDetail201.put(Const.ITEM_KEY_PRICE, "¥600");
        ItemDetail201.put(Const.ITEM_KEY_ARRIVAL, "発送予定：当日中");
        ItemDataInfo.put(Const.PRD_ID_200001, ItemDetail201);

        /*ファッション関連*/
        // 商品301
        ItemDetail301.put(Const.ITEM_KEY_CATE, "ファッション");
        ItemDetail301.put(Const.ITEM_KEY_MSG, "パリで大人気のメガネが日本に！");
        ItemDetail301.put(Const.ITEM_KEY_TITLE, "個性的なメガネをつけませんか？");
        ItemDetail301.put(Const.ITEM_KEY_PRICE, "¥32,600");
        ItemDetail301.put(Const.ITEM_KEY_ARRIVAL, "発送予定：3日以内");
        ItemDataInfo.put(Const.PRD_ID_300001, ItemDetail301);

        return true;
    }

    public String getItemData(String prdId, String key){
        HashMap<String, String> itemDetail = this.ItemDataInfo.get(prdId);
        return itemDetail.get(key);
    }
}

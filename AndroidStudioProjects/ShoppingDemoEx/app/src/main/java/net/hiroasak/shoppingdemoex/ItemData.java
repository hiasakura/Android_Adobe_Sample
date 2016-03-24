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

        // 商品102
        ItemDetail102.put(Const.ITEM_KEY_CATE, "スポーツ");
        ItemDetail102.put(Const.ITEM_KEY_MSG, "靴ぐらいちゃんと履きましょう");
        ItemDetail102.put(Const.ITEM_KEY_TITLE, "魔法のような靴です！");
        ItemDetail102.put(Const.ITEM_KEY_PRICE, "¥29,800");
        ItemDetail102.put(Const.ITEM_KEY_ARRIVAL, "発送予定：1ヶ月以内");
        ItemDataInfo.put(Const.PRD_ID_100002, ItemDetail102);

        // 商品103
        ItemDetail103.put(Const.ITEM_KEY_CATE, "スポーツ");
        ItemDetail103.put(Const.ITEM_KEY_MSG, "スキーは嫌いですか?");
        ItemDetail103.put(Const.ITEM_KEY_TITLE, "スキーの練習を始める方へ");
        ItemDetail103.put(Const.ITEM_KEY_PRICE, "¥12,700");
        ItemDetail103.put(Const.ITEM_KEY_ARRIVAL, "発送予定：3週間以内");
        ItemDataInfo.put(Const.PRD_ID_100003, ItemDetail103);

        /*食品関連*/
        // 商品201
        ItemDetail201.put(Const.ITEM_KEY_CATE, "食品");
        ItemDetail201.put(Const.ITEM_KEY_MSG, "スーパーバナナ!");
        ItemDetail201.put(Const.ITEM_KEY_TITLE, "ただのバナナではありません");
        ItemDetail201.put(Const.ITEM_KEY_PRICE, "¥600");
        ItemDetail201.put(Const.ITEM_KEY_ARRIVAL, "発送予定：当日中");
        ItemDataInfo.put(Const.PRD_ID_200001, ItemDetail201);

        // 商品202
        ItemDetail202.put(Const.ITEM_KEY_CATE, "食品");
        ItemDetail202.put(Const.ITEM_KEY_MSG, "脂がのった最高級まぐろ");
        ItemDetail202.put(Const.ITEM_KEY_TITLE, "一本釣りしました！");
        ItemDetail202.put(Const.ITEM_KEY_PRICE, "¥440,000");
        ItemDetail202.put(Const.ITEM_KEY_ARRIVAL, "発送予定：1週間以内");
        ItemDataInfo.put(Const.PRD_ID_200002, ItemDetail202);

        // 商品203
        ItemDetail203.put(Const.ITEM_KEY_CATE, "食品");
        ItemDetail203.put(Const.ITEM_KEY_MSG, "疲労回復に最適な梅干し");
        ItemDetail203.put(Const.ITEM_KEY_TITLE, "大事に育てられた最高級品です");
        ItemDetail203.put(Const.ITEM_KEY_PRICE, "¥790");
        ItemDetail203.put(Const.ITEM_KEY_ARRIVAL, "発送予定：翌日以内");
        ItemDataInfo.put(Const.PRD_ID_200003, ItemDetail203);

        /*ファッション関連*/
        // 商品301
        ItemDetail301.put(Const.ITEM_KEY_CATE, "ファッション");
        ItemDetail301.put(Const.ITEM_KEY_MSG, "パリで大人気のメガネが日本に！");
        ItemDetail301.put(Const.ITEM_KEY_TITLE, "個性的なメガネをつけませんか？");
        ItemDetail301.put(Const.ITEM_KEY_PRICE, "¥32,600");
        ItemDetail301.put(Const.ITEM_KEY_ARRIVAL, "発送予定：3日以内");
        ItemDataInfo.put(Const.PRD_ID_300001, ItemDetail301);

        // 商品302
        ItemDetail302.put(Const.ITEM_KEY_CATE, "ファッション");
        ItemDetail302.put(Const.ITEM_KEY_MSG, "この秋はおしゃれにきめよう！");
        ItemDetail302.put(Const.ITEM_KEY_TITLE, "素敵な帽子でおしゃれしよう");
        ItemDetail302.put(Const.ITEM_KEY_PRICE, "¥20,000");
        ItemDetail302.put(Const.ITEM_KEY_ARRIVAL, "発送予定：3日以内");
        ItemDataInfo.put(Const.PRD_ID_300002, ItemDetail302);

        // 商品303
        ItemDetail303.put(Const.ITEM_KEY_CATE, "ファッション");
        ItemDetail303.put(Const.ITEM_KEY_MSG, "話題のあの商品がお買い得！");
        ItemDetail303.put(Const.ITEM_KEY_TITLE, "目立つこと間違い無し");
        ItemDetail303.put(Const.ITEM_KEY_PRICE, "¥18,900");
        ItemDetail303.put(Const.ITEM_KEY_ARRIVAL, "発送予定：2週間内");
        ItemDataInfo.put(Const.PRD_ID_300003, ItemDetail303);

        return true;
    }

    public String getItemData(String prdId, String key){
        HashMap<String, String> itemDetail = this.ItemDataInfo.get(prdId);
        return itemDetail.get(key);
    }
}

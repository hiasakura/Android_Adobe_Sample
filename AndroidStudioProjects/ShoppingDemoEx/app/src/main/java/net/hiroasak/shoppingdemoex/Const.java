package net.hiroasak.shoppingdemoex;

import android.view.ViewGroup;

/**
 * Created by h_asakura on 2015/09/23.
 * 定数クラス
 */
public class Const {

    public final static int REQUEST_TEXT = 0;
    public final static int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
    public final static int MP = ViewGroup.LayoutParams.MATCH_PARENT;

    /* ボタンタグ */
    public final static String TAG_CATE_SPORTS  = "SPORTS";
    public final static String TAG_CATE_FOOD    = "FOOD";
    public final static String TAG_CATE_CLOTHES = "CLOTHES";
    public final static String TAG_THANKS_TEXT  = "THANKS_TEXT";
    public final static String TAG_THANKS_IMG   = "THANKS_IMG";
    public final static String TAG_BACK_TOP     = "BACK_TOP";
    public final static String TAG_EX_BANNER    = "EX_BANNER";

    public final static String IMG_FILE_LEFT    = "01";
    public final static String IMG_FILE_RIGHT   = "02";

    public final static String DIR_IMAGE = "drawable";

    /* 製品情報 */
    // スポーツ
    public final static String PRD_ID_100001 = "p100001";
    public final static String PRD_ID_100002 = "p100002";
    public final static String PRD_ID_100003 = "p100003";
    // 食品
    public final static String PRD_ID_200001 = "p200001";
    public final static String PRD_ID_200002 = "p200002";
    public final static String PRD_ID_200003 = "p200003";
    // 洋服
    public final static String PRD_ID_300001 = "p300001";
    public final static String PRD_ID_300002 = "p300002";
    public final static String PRD_ID_300003 = "p300003";

    // キー情報
    public final static String INTENT_KEY_PRODUCT   = "prdId";
    public final static String ITEM_KEY_CATE        = "category";
    public final static String ITEM_KEY_MSG         = "msg";
    public final static String ITEM_KEY_TITLE       = "title";
    public final static String ITEM_KEY_PRICE       = "price";
    public final static String ITEM_KEY_ARRIVAL     = "arrival";

    // WebView URL
    public final static String WEB_VIEW_ADOBE_URL =  "http://www.adobe.com/jp/creativecloud.html";

    // Target用変数
    public final static String MBOX_SUCCESS_TEXT = "SUCCESS";
    public final static String MBOX_TOP_SPORTS_TEXT_TEST = "topSportsMsgLocation";
    public final static String MBOX_TOP_FOOD_TEXT_TEST = "topFoodsMsgLocation";
    public final static String MBOX_TOP_FASHION_TEXT_TEST = "topFashionsMsgLocation";
    public final static String MBOX_CATE_SPORTS_VIEWED = "ViewedSportsCateLocation";
    public final static String MBOX_CATE_FOOD_VIEWED = "ViewedFoodCateLocation";
    public final static String MBOX_CATE_FASHION_VIEWED = "ViewedFashionCateLocation";

    public static final String SENT_TOKEN_TO_SERVER = "sentTokenToServer";
    public static final String REGISTRATION_COMPLETE = "registrationComplete";
}

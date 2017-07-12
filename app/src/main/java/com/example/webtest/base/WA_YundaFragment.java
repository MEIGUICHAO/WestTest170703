package com.example.webtest.base;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.webtest.R;
import com.example.webtest.Utils.CacheUtils;
import com.example.webtest.Utils.ConstantUtils;
import com.example.webtest.Utils.DateUtils;
import com.example.webtest.Utils.UIUtils;
import com.example.webtest.io.WA_Parameters;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * @author z.h
 * @desc 存放基本业务逻辑&Js调用本地方法的接口函数
 */
public class WA_YundaFragment extends WA_BaseFragment {

    protected WebView listWeb;
    protected WebView detailWeb;
    private String logicStr;
    private String myAmount = "";
    private int[] fiboArr;
    private HashMap<String, Integer> buyPositionAcountMap;
    private HashMap<String, Integer> buyNumAcountMap;
    private HashMap<String, String> templePositionAcountMap;
    private HashMap<String, String> templeNumAcountMap;

    private HashMap<String, Integer> buyFtPositionAcountMap;
    private HashMap<String, Integer> buyFtNumAcountMap;
    private HashMap<String, String> templeFtPositionAcountMap;
    private HashMap<String, String> templeFtNumAcountMap;

    protected String ScCurrentCTrem;
    protected String ScLastCTrem;
    protected String FtCurrentCTrem;
    protected String FtLastCTrem;
    protected boolean IS_SC = false;
    protected boolean SET_BEGIN_AMOUNT = false;
    protected boolean CAN_PAY = false;
    protected boolean COMPARE_BEGIN = false;
    protected boolean FIRST_TIME = true;

    protected double BEGIN_AMOUMT = 0.00;
    protected double COMPARE_AMOUMT = 0.00;
    protected double END_AMOUMT;
    private String completeJs;
    protected int allCount;
    protected int beginInt = ConstantValue.BIGGER_INT;




    protected enum SearchType {
        All, Shop, Mall
    }

    private Handler handler = new Handler();

    /**
     * Function：选择商品所在的商铺类型(天猫或淘宝)
     */
    protected String selectSearchType(boolean isTMall) {
        String str = "var sortType= doGetTextByCN(\"s-input-tab-txt\");" + "if(!" + isTMall + "){" + "if(sortType!=\"天猫\"){" + "doClickByCN(\"s-input-tab-txt\",1);" + "doClickByCN(\"all\",2);" + "doClickByCN(\"s-input-tab-txt\",2);" + "}}else{" + "if(sortType!=\"宝贝\"){" + "doClickByCN(\"s-input-tab-txt\",1);" + "doClickByCN(\"mall\",2);" + "doClickByCN(\"s-input-tab-txt\",2);" + "}}";
        return str;
    }

    /**
     * Function：点击进入搜索(BelongTo Step1)
     *
     * @param buyPositionList
     * @param buyNumList
     * @param buyDifList
     * @param typeBlank
     */
    protected void doEnterSearchPage(final ArrayList<String> buyPositionList, final ArrayList<String> buyNumList, final ArrayList<String> buyDifList, final String amount, final boolean IS_Auto, final int typeBlank) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                enterSearchPage(listWeb,detailWeb, buyPositionList, buyNumList, buyDifList, amount, IS_Auto, typeBlank);
            }
        });
    }

    protected void goSc() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                goSc(listWeb,detailWeb);
            }
        });
    }

    protected void goFt() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                goFt(listWeb,detailWeb);
            }
        });
    }

    /**
     * Function：选择商铺类型(BelongTo Step2)
     */
    protected void doSelectStoreType(final WA_Parameters parameter) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                selectStoreType(listWeb, parameter.getIsTMall());
            }
        });
    }

    /**
     * Function：进行商品搜索(BelongTo Step2)
     */
    protected void doSearch(final WA_Parameters parameter) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                searchFor(listWeb, parameter.getKeywordStr());
            }
        });
    }

    /**
     * Function：首次进行商品浏览(BelongTo Step3)
     */
    protected void doScan(final WA_Parameters parameter) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                scanGoods(listWeb, parameter.getTitleStr());
            }
        }, 4000);

    }

    /**
     * Function：根据销量排序(BelongTo Step4)
     */
    protected void doOrderBySellAmount() {

        handler.post(new Runnable() {
            @Override
            public void run() {
                orderBySellAmount(listWeb);
            }
        });
    }

    /**
     * Function：若当前页中不存在该商铺则翻页，同时另一个页面进行随机商品浏览，浏览时长随机(BelongTo Step5)
     */
    protected void doFlipAndScan(final WA_Parameters parameter, final int randomTime) {
        // 跳转到下一页
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getNextPage(listWeb);
            }
        }, 2000);

        // 在当前页查找，若没查到则翻到下一页递归查找
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                scanGoods(listWeb, parameter.getTitleStr());
            }
        }, 5000 + randomTime * 1000);
    }

    /**
     * Function：不翻页，在当前页进行随机商品浏览，浏览时长随机(BelongTo Step5)
     */
    protected void doScanForLongTime(final WA_Parameters parameter, final int randomTime) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                scanGoods(listWeb, parameter.getTitleStr());
            }
        }, 5000 + randomTime * 1000);

    }

    /**
     * Function：关闭提示框(BelongTo Step5)
     */
    protected void doAlertHide() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                alertHide(detailWeb);
            }
        }, 2000);
    }

    /**
     * Function：根据给定的URL进入执行商铺(BelongTo Step5)
     */
    protected void doEnterShop(final String url) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                enterShop(detailWeb, url);
            }
        });

    }

    /**
     * Function：选择商品SKU
     */
    protected void doSelectSku() {

    }


    /**
     * 点击进入搜索页面(主页面)
     */
    private void enterSearchPage(final WebView webView, final WebView detailWeb, ArrayList<String> buyPositionList, ArrayList<String> buyNumList, ArrayList<String> buyDifList, String amount, boolean IS_Auto, int typeBlank) {
        logicStr = "";
//        if (!Utils.isCurrentInTimeScope(13, 20, 4, 0)) {
//            return;
//        }

        if (fiboArr == null) {
            fiboArr = new int[8];
            fiboArr[0] = fiboArr[1] = 1;
            for (int i = 2; i < 7; i++) {
                fiboArr[i] = fiboArr[i - 1] + fiboArr[i - 2];
            }
            fiboArr[7] = 13;
        }

        ArrayList<String> buylist = new ArrayList<String>();
        ArrayList<String> buydiflist = new ArrayList<String>();
        ArrayList<Integer> smallerList = new ArrayList<Integer>();
        ArrayList<Integer> smallerDifList = new ArrayList<Integer>();

        if (null == buyPositionAcountMap) {
            buyPositionAcountMap = new HashMap<String, Integer>();
        }
        if (null == buyNumAcountMap) {
            buyNumAcountMap = new HashMap<String, Integer>();
        }
        if (null == templePositionAcountMap) {
            templePositionAcountMap = new HashMap<String, String>();
        }
        if (null == templeNumAcountMap) {
            templeNumAcountMap = new HashMap<String, String>();
        }
        templePositionAcountMap.clear();
        templeNumAcountMap.clear();

        if (null == buyFtPositionAcountMap) {
            buyFtPositionAcountMap = new HashMap<String, Integer>();
        }
        if (null == buyFtNumAcountMap) {
            buyFtNumAcountMap = new HashMap<String, Integer>();
        }
        if (null == templeFtPositionAcountMap) {
            templeFtPositionAcountMap = new HashMap<String, String>();
        }
        if (null == templeFtNumAcountMap) {
            templeFtNumAcountMap = new HashMap<String, String>();
        }
        templeFtPositionAcountMap.clear();
        templeFtNumAcountMap.clear();


        for (int i = 0; i < buyPositionList.size(); i++) {
            buylist.add(buyPositionList.get(i));
            if (i % 2 != 0) {
                buyAcountMap(buyPositionList, i, true);
                String[] split = buyPositionList.get(i).split(UIUtils.getString(R.string.splitNum));
                int smarllInt = Integer.parseInt(split[2]);
                smallerList.add(smarllInt);
            }
        }

        for (int i = 0; i < buyNumList.size(); i++) {
            buylist.add(buyNumList.get(i));
            if (i % 2 != 0) {
                buyAcountMap(buyNumList, i, false);
                String[] split = buyNumList.get(i).split(UIUtils.getString(R.string.splitNum));
                int smarllInt = Integer.parseInt(split[2]);
                smallerList.add(smarllInt);
            }
        }

        for (int i = 0; i < buyDifList.size(); i++) {
            buydiflist.add(buyDifList.get(i));
            if (i % 2 != 0) {
                String[] split = buyDifList.get(i).split(UIUtils.getString(R.string.splitNum));
                int smarllInt = Integer.parseInt(split[2]);
                smallerDifList.add(smarllInt);
            }
        }

        int[] smarllerInts = sortIntList(smallerList);
        int[] smarllerFifInts = sortIntList(smallerDifList);

        if (buydiflist.size() > 0) {
            getMethod(buydiflist, amount, "100", IS_Auto, typeBlank, 0, true);
            logicStr = logicStr + "commitData(1000);";
        }
        if (buyPositionList.size() > 0) {
            if (ConstantUtils.isCUSTOM()) {
                getMethod(buyPositionList, amount, "100", IS_Auto, typeBlank, 0, true);
            } else {
                getMethod(buyPositionList, amount, "100", IS_Auto, typeBlank, 0, true);
            }
            logicStr = logicStr + "commitData(1000);";
        }
        if (buyNumList.size() > 0) {
            if (ConstantUtils.isCUSTOM()) {
                getMethod(buyNumList, amount, "3500", IS_Auto, typeBlank, 0, false);
            } else {
                getMethod(buyNumList, amount, "3500", IS_Auto, typeBlank, 0, false);
            }
            logicStr = logicStr + "commitData(4500);";
        }
        completeJs = doAutoTest(logicStr);
        if (CAN_PAY) {
            UIUtils.postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadUrl(detailWeb, completeJs);
                }
            }, 5500);

            UIUtils.postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadUrl(webView, completeJs);
                }
            }, 500);

        } else {
            loadUrl(detailWeb, completeJs);
        }

        resetBuyAcountMap();
    }

    private void resetBuyAcountMap() {
        if (IS_SC) {

            Iterator positionIter = buyPositionAcountMap.entrySet().iterator();
            ArrayList<String> positionKeys = new ArrayList<String>();
            while (positionIter.hasNext()) {
                Map.Entry entry = (Map.Entry) positionIter.next();
                String key = (String) entry.getKey();
                if (TextUtils.isEmpty(templePositionAcountMap.get(key))) {
                    positionKeys.add(key);
                }
            }

            Iterator numIter = buyNumAcountMap.entrySet().iterator();
            ArrayList<String> numKeys = new ArrayList<String>();
            while (numIter.hasNext()) {
                Map.Entry entry = (Map.Entry) numIter.next();
                String key = (String) entry.getKey();
                if (TextUtils.isEmpty(templeNumAcountMap.get(key))) {
                    numKeys.add(key);
                }
            }

            for (int i = 0; i < positionKeys.size(); i++) {
                Log.d(TAG, "positionKeys: " + positionKeys.get(i));
                buyPositionAcountMap.remove(positionKeys.get(i));
            }
            for (int i = 0; i < numKeys.size(); i++) {
                Log.d(TAG, "numKeys: " + numKeys.get(i));
                buyNumAcountMap.remove(numKeys.get(i));
            }
        } else {

            Iterator positionIter = buyFtPositionAcountMap.entrySet().iterator();
            ArrayList<String> positionKeys = new ArrayList<String>();
            while (positionIter.hasNext()){
                Map.Entry entry = (Map.Entry) positionIter.next();
                String key = (String) entry.getKey();
                if (TextUtils.isEmpty(templeFtPositionAcountMap.get(key))) {
                    positionKeys.add(key);
                }
            }

            Iterator numIter = buyFtNumAcountMap.entrySet().iterator();
            ArrayList<String> numKeys = new ArrayList<String>();
            while (numIter.hasNext()){
                Map.Entry entry = (Map.Entry) numIter.next();
                String key = (String) entry.getKey();
                if (TextUtils.isEmpty(templeFtNumAcountMap.get(key))) {
                    numKeys.add(key);
                }
            }

            for (int i = 0; i < positionKeys.size(); i++) {
                Log.d(TAG, "positionFtKeys: " + positionKeys.get(i));
                buyFtPositionAcountMap.remove(positionKeys.get(i));
            }
            for (int i = 0; i < numKeys.size(); i++) {
                Log.d(TAG, "numFtKeys: " + numKeys.get(i));
                buyFtNumAcountMap.remove(numKeys.get(i));
            }
        }

    }

    private void buyAcountMap(ArrayList<String> mBuyList, int i, boolean isPosition) {
        ArrayList<String> buyList = new ArrayList<String>();
        for (int j = 0; j < mBuyList.size(); j++) {
            String[] split = mBuyList.get(j).split(UIUtils.getString(R.string.splitNum));
            buyList.add(split[0] + UIUtils.getString(R.string.splitNum) + split[1]);
        }
        if (isPosition) {

            if (IS_SC) {
                templePositionAcountMap.put(buyList.get(i), i + "");
                templePositionAcountMap.put(buyList.get(i - 1), i + "");
            } else {
                templeFtPositionAcountMap.put(buyList.get(i), i + "");
                templeFtPositionAcountMap.put(buyList.get(i - 1), i + "");
            }

            if (IS_SC) {
                if (null != buyPositionAcountMap.get(buyList.get(i))) {
                    int integer = buyPositionAcountMap.get(buyList.get(i));
//                    if (!ScLastCTrem.equals(ScCurrentCTrem)) {
//                    }
                    integer = getInteger(integer);
                    buyPositionAcountMap.put(buyList.get(i), integer);
                    buyPositionAcountMap.put(buyList.get(i - 1), integer);
                    Log.d(TAG, "buyPosition: " + buyList.get(i));
                    Log.d(TAG, "buyPosition: " + buyList.get(i - 1));
                    Log.d(TAG, "buyInteger: " + integer);
                } else {
                    buyPositionAcountMap.put(buyList.get(i), 0);
                    buyPositionAcountMap.put(buyList.get(i - 1), 0);

                    Log.d(TAG, "buyPosition: " + buyList.get(i));
                    Log.d(TAG, "buyPosition: " + buyList.get(i - 1));
                }
            } else {
                if (null != buyFtPositionAcountMap.get(buyList.get(i))) {
                    int integer = buyFtPositionAcountMap.get(buyList.get(i));
//                    if (!FtLastCTrem.equals(FtCurrentCTrem)) {
//                    }
                    integer = getInteger(integer);

                    buyFtPositionAcountMap.put(buyList.get(i), integer);
                    buyFtPositionAcountMap.put(buyList.get(i - 1), integer);
                    Log.d(TAG, "buyFtPosition: " + buyList.get(i));
                    Log.d(TAG, "buyFtPosition: " + buyList.get(i - 1));
                    Log.d(TAG, "buyFtInteger: " + integer);
                } else {
                    buyFtPositionAcountMap.put(buyList.get(i), 0);
                    buyFtPositionAcountMap.put(buyList.get(i - 1), 0);
                    Log.d(TAG, "buyFtPosition: " + buyList.get(i));
                    Log.d(TAG, "buyFtPosition: " + buyList.get(i - 1));
                }
            }
        } else {
            if (IS_SC) {
                templeNumAcountMap.put(buyList.get(i), i + "");
                templeNumAcountMap.put(buyList.get(i - 1), i + "");
            } else {
                templeFtNumAcountMap.put(buyList.get(i), i + "");
                templeFtNumAcountMap.put(buyList.get(i - 1), i + "");
            }

            if (IS_SC) {


                if (null != buyNumAcountMap.get(buyList.get(i))) {
                    int integer = buyNumAcountMap.get(buyList.get(i));

//                    if (!ScLastCTrem.equals(ScCurrentCTrem)) {
//                    }
                    integer = getInteger(integer);
                    buyNumAcountMap.put(buyList.get(i), integer);
                    buyNumAcountMap.put(buyList.get(i - 1), integer);

                    Log.d(TAG, "buyNum: " + buyList.get(i));
                    Log.d(TAG, "buyNum: " + buyList.get(i-1));
                    Log.d(TAG, "buyNum: " + integer);
                } else {
                    buyNumAcountMap.put(buyList.get(i), 0);
                    buyNumAcountMap.put(buyList.get(i - 1), 0);

                    Log.d(TAG, "buyNum: " + buyList.get(i));
                    Log.d(TAG, "buyNum: " + buyList.get(i-1));
                }
            } else {


                if (null != buyFtNumAcountMap.get(buyList.get(i))) {
                    int integer = buyFtNumAcountMap.get(buyList.get(i));
//                    if (!FtLastCTrem.equals(FtCurrentCTrem)) {
//                    }
                    integer = getInteger(integer);
                    buyFtNumAcountMap.put(buyList.get(i), integer);
                    buyFtNumAcountMap.put(buyList.get(i - 1), integer);

                    buyFtPositionAcountMap.put(buyList.get(i - 1), integer);
                    Log.d(TAG, "buyFtNum: " + buyList.get(i));
                    Log.d(TAG, "buyFtNum: " + buyList.get(i-1));
                    Log.d(TAG, "buyFtNum: " + integer);
                } else {
                    buyFtNumAcountMap.put(buyList.get(i), 0);
                    buyFtNumAcountMap.put(buyList.get(i - 1), 0);

                    Log.d(TAG, "buyFtNum: " + buyList.get(i));
                    Log.d(TAG, "buyFtNum: " + buyList.get(i-1));
                }
            }

        }

    }

    private int getInteger(int integer) {
        if ((IS_SC && DateUtils.isInRange(9, 4, 23, 59)) ||
                (!IS_SC && (DateUtils.isInRange(13, 4, 23, 59))) ||
                (!IS_SC && DateUtils.isInRange(0, 0, 4, 10))) {
            integer = integer + 1;
            if (integer > 6) {
                integer = 0;
            }
        }
        return integer;
    }

    private void getMethod(ArrayList<String> buylist, String amount, String time, boolean IS_AUTO, int typeBlank, int smarllerInts, boolean isPosition) {
        ArrayList<String> buyPositionList = new ArrayList<String>();
        for (int j = 0; j < buylist.size(); j++) {
            String[] split = buylist.get(j).split(UIUtils.getString(R.string.splitNum));
            buyPositionList.add(split[0] + UIUtils.getString(R.string.splitNum) + split[1]);
        }
        for (int i = 0; i < buyPositionList.size(); i++) {
            String[] split = buyPositionList.get(i).split(UIUtils.getString(R.string.splitNum));
            int num = (Integer.parseInt(split[0]) - 1) * 10 + Integer.parseInt(split[1]);
            if (IS_AUTO) {
//                int fabInt = (ConstantUtils.getFabInt(ConstantUtils.isSc(), ConstantUtils.isCUSTOM(), typeBlank)-1);
//                if (fabInt < 0) {
//                    fabInt = 0;
//                }
//                if (ConstantUtils.isCUSTOM()) {
//                    myAmount = ConstantUtils.originAmount * 2*fiboArr[fabInt] + "";
//                } else {
//                    myAmount = ConstantUtils.originAmount * fiboArr[fabInt] + "";
//                }
//                int money = Integer.parseInt(myAmount);
//
                int position = 0;
//                if (isPosition) {
//                    if (IS_SC) {
//                        position = buyPositionAcountMap.get(buyPositionList.get(i));
//                    } else {
//                        position = buyFtPositionAcountMap.get(buyPositionList.get(i));
//                    }
//                    Log.d(TAG, "getMethod: " + buyPositionList.get(i));
//                } else {
//                    if (IS_SC) {
//                        position = buyNumAcountMap.get(buyPositionList.get(i));
//                    } else {
//                        position = buyFtNumAcountMap.get(buyPositionList.get(i));
//
//                    }
//                    Log.d(TAG, "getMethod: " + buyPositionList.get(i));
//                }
                Log.d(TAG, "getMethodPosition: " + position);


                //TODO
                int money = 10;
//                int money = fiboArr[position];
//                if (i % 2 == 0) {
//                    money = money * 2;
//                }

                logicStr = logicStr + "selectNumRange(" + num + "," + money + "," + time + ");";
//                if (i % 2 == 0) {
//                }
            } else {
                logicStr = logicStr + "selectNumRange(" + num + "," + amount + "," + time + ");";
            }
        }
    }

//    private void getCustomAmount(ArrayList<String> buyPositionList, int typeBlank) {
//        int[] ints = new int[buyPositionList.size()];
//        for (int i = 0; i < buyPositionList.size(); i++) {
//            String[] split = buyPositionList.get(i).split(UIUtils.getString(R.string.splitNum));
//            ints[i] = Integer.parseInt(split[2]);
//        }
//        sortInt(ints);
//        myAmount = getCustomAmount(ints[0], typeBlank);
//    }

    private String getCustomAmount(int anInt, int typeBlank) {
        int sameInt = ConstantUtils.autoSame10;
        String mAmount = "";
        switch (typeBlank) {

            case ConstantValue.TYPE_BLANK_20:
                sameInt = ConstantUtils.autoSame20;
                break;
            case ConstantValue.TYPE_BLANK_15:
                sameInt = ConstantUtils.autoSame15;
                break;
            case ConstantValue.TYPE_BLANK_10:
                sameInt = ConstantUtils.autoSame10;
                break;
        }
//        if ((anInt - sameInt) / 2 <= 1) {
//            mAmount = 1 + "";
//        } else {
//            double floor = Math.floor((anInt - sameInt) / 2);
//            mAmount = (floor + "").replace(".0", "");
//        }

//        mAmount = (anInt - sameInt)＋"";


        if (fiboArr == null) {
            fiboArr = new int[7];
            fiboArr[0] = fiboArr[1] = 1;
            for (int i = 2; i < 7; i++) {
                fiboArr[i] = fiboArr[i - 1] + fiboArr[i - 2];
            }
//
//            fiboArr[7] = fiboArr[8] = 1;
//            for (int i = 9; i < 14; i++) {
//                fiboArr[i] = fiboArr[i - 1] + fiboArr[i - 2];
//            }
//
//            fiboArr[14] = fiboArr[15] = 1;
//            for (int i = 16; i < 21; i++) {
//                fiboArr[i] = fiboArr[i - 1] + fiboArr[i - 2];
//            }
//
//            fiboArr[21] = fiboArr[22] = 1;
//            for (int i = 23; i < 28; i++) {
//                fiboArr[i] = fiboArr[i - 1] + fiboArr[i - 2];
//            }
//
//            fiboArr[28] = fiboArr[29] = 1;
//            for (int i = 30; i < fiboArr.length; i++) {
//                fiboArr[i] = fiboArr[i - 1] + fiboArr[i - 2];
//            }
        }
        try {
            mAmount = fiboArr[anInt - sameInt] * ConstantUtils.getOriginAmount() + "";
        } catch (Exception e) {

//            Log.d(TAG, "exception!!!: anInt" + anInt);
//            Log.d(TAG, "sameInt!!!: sameInt" + sameInt);
//            Log.d(TAG, "exception!!!: typeBlank" + typeBlank);
//             Log.d(TAG, "exception!!!: anInt" + anInt);
//            Log.d(TAG, "exception!!!: typeBlank" + typeBlank);
            mAmount = "";
        }
        return mAmount;

    }

    private String getLotteryAmount(int str, final int sameInt) {
        int amount = ConstantUtils.originAmount;
        String StrAmount = amount + "";

        if (str >= sameInt && str <= (sameInt + 2)) {
            StrAmount = amount + "";
        } else if (str == (sameInt + 3)) {
            StrAmount = amount * 2 + "";
        } else if (str == (sameInt + 4)) {
            StrAmount = amount * 3 + "";
        } else if (str == (sameInt + 5)) {
            StrAmount = amount * 5 + "";
        } else if (str >= (sameInt + 6) && str <= (sameInt + 7)) {
            StrAmount = amount * 8 + "";
        } else if (str >= (sameInt + 8) && str <= (sameInt + 13)) {
            StrAmount = amount * 16 + "";
        } else if (str >= (sameInt + 14) && str <= (sameInt + 16)) {
            StrAmount = amount * 10 + "";
        } else if (str >= (sameInt + 17) && str <= (sameInt + 20)) {
            StrAmount = amount * 20 + "";
        } else {
            StrAmount = "";
        }

//        if (str >= sameInt && str <= (sameInt + 4)) {
//            StrAmount = amount + "";
//        } else if (str >= (sameInt + 5) && str <= (sameInt + 6)) {
//            StrAmount = amount * 2 + "";
//        } else if (str >= (sameInt + 7) && str <= (sameInt + 8)) {
//            StrAmount = amount * 3 + "";
//        } else if (str >= (sameInt + 9) && str <= (sameInt + 10)) {
//            StrAmount = amount * 5 + "";
//        } else if (str >= (sameInt + 11) && str <= (sameInt + 12)) {
//            StrAmount = amount * 8 + "";
//        } else if (str >= (sameInt + 13) && str <= (sameInt + 15)) {
//            StrAmount = amount * 13 + "";
//        } else if (str >= (sameInt + 16) && str <= (sameInt + 17)) {
//            StrAmount = amount * 21 + "";
//        } else {
//            StrAmount = "";
//        }
        return StrAmount;
    }

    private void goSc(WebView webView, WebView detailWeb) {
        String logicStr = "goSc();";
        String completeJs = doAutoTest(logicStr);
        String logicCompareStr = "goCompareSc();";
        String completeCompareJs = doAutoTest(logicCompareStr);
        loadUrl(webView, completeJs);
        loadUrl(detailWeb, completeCompareJs);
    }

    private void goFt(WebView webView, WebView detailWeb) {
        String logicStr = "goFt();";
        String completeJs = doAutoTest(logicStr);
        String logicCompareStr = "goCompareFt();";
        String completeCompareJs = doAutoTest(logicCompareStr);
        loadUrl(webView, completeJs);
        loadUrl(detailWeb, completeCompareJs);
    }


    /**
     * 选择店铺类型
     */
    private void selectStoreType(WebView webView, boolean isTMall) {
        // 拼接业务逻辑
//		String logicStr = selectSearchType(isTMall);
        String logicStr = "doComfir();";
        String completeJs = doAutoTest(logicStr);
        loadUrl(webView, completeJs);
    }

    /**
     * 输入搜索内容，然后查找
     */
    private void searchFor(WebView webView, String keywordStr) {
        // 拼接业务逻辑
        String logicStr = "doInputByCN(\"J_autocomplete\",\"" + keywordStr + "\",2);" + "doClickByCN(\"icons-search\",4);";

        String completeJs = doAutoTest(logicStr);
        loadUrl(webView, completeJs);
    }

    /**
     * 点击筛选按钮
     */
    private void filterGoods(WebView webView) {
        // 拼接业务逻辑
        String logicStr = "doTapByRI(\"J_Sift\");";

        String completeJs = doAutoTest(logicStr);
        loadUrl(webView, completeJs);
    }

    /**
     * 确定筛选条件
     */
    private void confirmFilter(WebView webView) {
        // 拼接业务逻辑
        String logicStr = "doClickByRI(\"J_SiftCommit\",2);";

        String completeJs = doAutoTest(logicStr);
        loadUrl(webView, completeJs);
    }

    /**
     * 按销量优先排序
     */
    private void orderBySellAmount(WebView webView) {
        // 拼接业务逻辑
        String logicStr = "doTapByParentCN(\"sort-tab\",\"sort\");";

        String completeJs = doAutoTest(logicStr);
        loadUrl(webView, completeJs);
    }

    /**
     * 浏览商铺
     */
    private void scanGoods(WebView webView, String titleStr) {
        // 拼接业务逻辑
        String logicStr = "var currentPage=doGetTextByCNByInner(\"currentPage\");" + "var totalSize=getSize(\"list-item\");"
                // +"localMethod.JI_showToast(totalSize);"
                // + "localMethod.JI_showToast(currentPage);"
                + "doTapForScanGoodsByTitle(\"list-item\",\"d-title\",\"" + titleStr + "\",currentPage,totalSize);";

        String completeJs = doAutoTest(logicStr);
        loadUrl(webView, completeJs);
    }

    /**
     * 关闭提示框
     */
    private void alertHide(WebView webView) {
        // 拼接业务逻辑
        String logicStr = "doClickByCN(\"btn-hide\",2);";

        String completeJs = doAutoTest(logicStr);
        loadUrl(webView, completeJs);
    }

    /**
     * 进入目标商铺
     */
    private void enterShop(WebView webView, String url) {
        webView.loadUrl("https:" + url);
    }

    private void skuSelect(WebView webView) {
        // 拼接业务逻辑
        String logicStr = "doTapByCN02(); ";

        String completeJs = doAutoTest(logicStr);
        loadUrl(webView, completeJs);
    }

    /**
     * 翻页
     */
    private void getNextPage(WebView mWebView) {
        String logicStr = "doTapByCN(\"J_PageNext\"); ";

        String completeJs = doAutoTest(logicStr);
        loadUrl(mWebView, completeJs);
    }

    /* 暴露给JavaScript脚本调用的方法* */
    public class LocalMethod {
        Context mContext;
        private WA_Parameters parameter;

        public LocalMethod(Context c, WA_Parameters parameter) {
            this.mContext = c;
            this.parameter = parameter;
        }

        public WA_Parameters getParameter() {
            return parameter;
        }

        public void setParameter(WA_Parameters parameter) {
            this.parameter = parameter;
        }

        @JavascriptInterface
        public void JI_showToast(String content) {
            Toast.makeText(mContext, content, Toast.LENGTH_SHORT).show();
        }

        @JavascriptInterface
        public void JI_LOG(String content) {
//
//            if (FIRST_TIME) {
//                FIRST_TIME = false;
//                SET_BEGIN_AMOUNT = true;
//            }
            BigDecimal bigDeimal = new BigDecimal(Double.parseDouble(content));
//            if (IS_SC && DateUtils.isInRange(8, 50, 9, 0)) {
//                SET_BEGIN_AMOUNT = true;
//            }
            if (SET_BEGIN_AMOUNT) {
                BEGIN_AMOUMT = bigDeimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                SET_BEGIN_AMOUNT = false;
                CAN_PAY = true;
            }
            if (CAN_PAY&&(bigDeimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() - BEGIN_AMOUMT) > ConstantValue.EARN_PART) {
                BEGIN_AMOUMT = bigDeimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                CAN_PAY = false;
                COMPARE_BEGIN = true;
            }
            Log.e(TAG, "JI_LOG: " + content + ",BEGIN_AMOUMT:" + BEGIN_AMOUMT + ",allCount:" + allCount + ",beginInt:" + beginInt);

            if (!TextUtils.isEmpty(content)) {
                CacheUtils.putCache(getActivity(), "BEGIN_AMOUMT", content + ",BEGIN_AMOUMT:" + BEGIN_AMOUMT);
            }
//			Toast.makeText(mContext, content, Toast.LENGTH_SHORT).show();
        }


        @JavascriptInterface
        public void COMPARE_AMOUNT(String content) {

            if (FIRST_TIME) {
                FIRST_TIME = false;
                COMPARE_BEGIN = true;
            }
            BigDecimal bigDeimal = new BigDecimal(Double.parseDouble(content));
            double currentAmount = bigDeimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            if (!CAN_PAY && currentAmount > COMPARE_AMOUMT) {
                COMPARE_AMOUMT = currentAmount;
            }
            if (COMPARE_BEGIN) {
                COMPARE_AMOUMT = bigDeimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                COMPARE_BEGIN = false;
            }
            if ((COMPARE_AMOUMT - bigDeimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()) > ConstantValue.BEGIN_PART&&!CAN_PAY) {
                SET_BEGIN_AMOUNT = true;
            }
            Log.e(TAG, "JI_LOG: " + content + ",COMPARE_AMOUMT:" + COMPARE_AMOUMT + ",allCount:" + allCount + ",beginInt:" + beginInt);
            if (!TextUtils.isEmpty(content)) {
                CacheUtils.putCache(getActivity(), "COMPARE_AMOUMT", content + ",COMPARE_AMOUMT:" + COMPARE_AMOUMT);
            }
//			Toast.makeText(mContext, content, Toast.LENGTH_SHORT).show();
        }

        @JavascriptInterface
        public void JI_scrollView() {
            listWeb.scrollBy(0, 1800);
        }

        @JavascriptInterface
        public void JI_doGetNextPage(int randomTime) {
            doFlipAndScan(parameter, randomTime);
        }

        @JavascriptInterface
        public void JI_doScanCurrentPage(int randomTime) {

            doScanForLongTime(parameter, randomTime);
        }

        @JavascriptInterface
        public void JI_doCloseAlert() {
            doAlertHide();
        }

        @JavascriptInterface
        public void JI_doEnterShop(String url) {
            doEnterShop(url);
        }

        @JavascriptInterface
        public void JI_createLog(String infoStr) throws IOException {
            createLog(infoStr);
        }
    }


    private int[] sortInt(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }

    private int[] sortIntList(ArrayList<Integer> list) {
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }
}

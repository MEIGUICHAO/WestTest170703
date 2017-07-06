package com.example.webtest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.webtest.Utils.CacheUtils;
import com.example.webtest.Utils.ConstantUtils;
import com.example.webtest.Utils.DateUtils;
import com.example.webtest.Utils.UIUtils;
import com.example.webtest.Utils.Utils;
import com.example.webtest.base.ConstantValue;
import com.example.webtest.base.WA_YundaFragment;
import com.example.webtest.bean.MyBean;
import com.example.webtest.io.WA_Parameters;
import com.example.webtest.volley.VolleyManager;
import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author z.h
 * @desc 自动化Fragment主调页面
 */
public class WA_MainFragment extends WA_YundaFragment implements View.OnClickListener {
    private static final String ARG_CODE = "WebAutoFragment";
    private static final String BASIC_JS_PATH = "basic_inject.js";
    private static final String LOGIC_JS_PATH = "logic_inject.js";
    @Bind(R.id.btn_change_lottory)
    Button btnChangeLottory;
    @Bind(R.id.et_amount)
    EditText etAmount;
    @Bind(R.id.btn_amount_rise)
    Button btnAmountRise;
    @Bind(R.id.btn_amount_discount)
    Button btnAmountDiscount;
    @Bind(R.id.et_blank)
    EditText etBlank;
    @Bind(R.id.et_allcount)
    EditText etAllcount;
    @Bind(R.id.et_blank20)
    EditText etBlank20;
    @Bind(R.id.et_blank15)
    EditText etBlank15;
    @Bind(R.id.et_blank10)
    EditText etBlank10;
    @Bind(R.id.btn_resetdata)
    Button btnResetdata;
    @Bind(R.id.btn_custom)
    Button btnCustom;
    @Bind(R.id.btn_sc)
    Button btnSc;
    @Bind(R.id.btn_ft)
    Button btnFt;
    @Bind(R.id.btn_go)
    Button btnGo;
    @Bind(R.id.wa_webview_list)
    WebView waWebviewList;
    @Bind(R.id.tv_amount)
    TextView tvAmount;


    private Button startBtn;
    private Button btn_sc;
    private Button btn_ft;
    private LocalMethod mLocalMethod;
    private WA_Parameters parameter;
    private String injectJS;


    @Bind(R.id.et_no)
    EditText etNo;
    @Bind(R.id.et_date)
    EditText etDate;
    @Bind(R.id.btn_rise)
    Button btnRise;
    @Bind(R.id.btn_discount)
    Button btnDiscount;
    @Bind(R.id.et_none)
    EditText etNone;
    @Bind(R.id.btn_normal)
    Button btnNormal;
    @Bind(R.id.btn_big)
    Button btnBig;
    @Bind(R.id.btn_get)
    Button btnGet;
    @Bind(R.id.btn_caculate)
    Button btnCaculate;
    @Bind(R.id.tv_result)
    TextView tvResult;
    @Bind(R.id.btn_change)
    Button btnChange;
    @Bind(R.id.btn_useful)
    Button btnUseful;
    @Bind(R.id.btn_learn)
    Button btnLearn;
    @Bind(R.id.tv_learning)
    TextView tvLearning;
    @Bind(R.id.tv_learn_result)
    TextView tvLearnResult;
    @Bind(R.id.et_less)
    EditText etLess;
    @Bind(R.id.et_bigger)
    EditText etBigger;
    @Bind(R.id.et_caculate)
    EditText etCaculate;
    @Bind(R.id.btn_check)
    Button btnCheck;
    @Bind(R.id.et_10none)
    EditText et10none;
    @Bind(R.id.et_20none)
    EditText et20none;
    @Bind(R.id.et_10mutiple)
    EditText et10mutiple;
    @Bind(R.id.btn_reset)
    Button btnReset;
    @Bind(R.id.tv_learn_result2)
    TextView tvLearnResult2;
    @Bind(R.id.et_dif)
    EditText etDif;
    @Bind(R.id.et_same)
    EditText etSame;
    @Bind(R.id.tv_learn_result3)
    TextView tvLearnResult3;
    @Bind(R.id.tv_allcount)
    TextView tvAllcount;
    private String myurl = "http://api.caipiaokong.com/lottery/?name=bjpks&format=json&uid=533810&token=8ecc79c616cc9475d246369db1165d9466df61e7&format=json2&date=";
    //    private String myurlxyft = "http://api.caipiaokong.com/lottery/?name=xyft&format=json&uid=533810&token=8ecc79c616cc9475d246369db1165d9466df61e7&format=json2&date=";
    private String myurlxyft = "http://api.kaijiangtong.com/lottery/?name=xyft&format=json2&uid=533810&token=8ecc79c616cc9475d246369db1165d9466df61e7&date=";
    //    private String myurlpk10 = "http://api.caipiaokong.com/lottery/?name=bjpks&format=json&uid=533810&token=8ecc79c616cc9475d246369db1165d9466df61e7&format=json2&date=";
    private String myurlpk10 = "http://api.kaijiangtong.com/lottery/?name=bjpks&format=json2&uid=533810&token=8ecc79c616cc9475d246369db1165d9466df61e7&date=";
    private View view;


    private String TAG = "TAG";
    private String LogTag = "SCBuyMap";


    private int[] numNoneNormal = {0, 10, 15, 20, 25, 33, 35, 40, 43, 45, 48, 50, 53, 55, 58, 60};
    private int[] numNoneBig = {65, 70, 75, 80, 85, 90, 95, 100, 105, 110, 115, 120};
    private int[] useful = {10, 15, 25, 35};
    private int learnOriginNo;
    private long learnBeanId;
    private String learnResultStr2;
    private String learnResultStr;
    private MainActivity mActivity;
    private String none10;
    private String none20;
    private String mutiple10;
    private HashMap<String, Integer> stringIntegerHashMap;
    private HashMap<Integer, String> originalMap;
    private HashMap<Integer, String> originalSameNunMap;
    //    private HashMap<Integer, String> originalDisplayMap;
//    private HashMap<Integer, String> originalDisplaySameNunMap;
    private HashMap<Integer, Integer> baseMap;
    private HashMap<Integer, Integer> baseSameNumMap;
    private HashMap<Integer, String> glMap;
    //    private HashMap<Integer, String> learnBeanMap;
//    private HashMap<Integer, String> learn120BeanMap;
    private Gson gson;
    private String learnResultStr3;
    private String learnResultStr4;
    private String amount;
    private String cTerm;
    private boolean IS_GSON_RETRY = false;
    private String amountStr = "";


    public int[] getNumMax() {
        return numMax;
    }

    private int[] numMax = {240};

    public int[] getNumLimit() {
        return numLimit;
    }

    private int[] numLimit = {20};

    private int[] numMaxCompare = {200, 320, 450, 550, 750, 950, 1150, 1350, 1550};
    private int[] num152535 = {35, 25, 15, 18, 20, 23, 28, 30, 33, 38, 40, 45, 50, 55, 65, 70, 75, 100, 120};

    private int num13, num14, num12, num15, num11, num16, num0, num1, num2, num3, num4, num5, num6, num7, num8, num9, num10, num17, num18, num19, num20, num21, num22, num23, num24, num25, num26, num27;
    private int learnnum13, learnnum14, learnnum12, learnnum15, learnnum11, learnnum16, learnnum0, learnnum1, learnnum2, learnnum3, learnnum4, learnnum5, learnnum6, learnnum7, learnnum8, learnnum9, learnnum10, learnnum17, learnnum18, learnnum19, learnnum20, learnnum21, learnnum22, learnnum23, learnnum24, learnnum25, learnnum26, learnnum27;
    private int learn200num13, learn200num14, learn200num20012, learn200num15, learn200num11, learn200num16, learn200num0, learn200num1, learn200num2, learn200num3, learn200num4, learn200num5, learn200num6, learn200num7, learn200num8, learn200num9, learn200num10, learn200num17, learn200num18, learn200num19, learn200num20, learn200num21, learn200num22, learn200num23, learn200num24, learn200num25, learn200num26, learn200num27;

    private int nonenum13, nonenum14, nonenum12, nonenum15, nonenum11, nonenum16, nonenum0, nonenum1, nonenum2, nonenum3, nonenum4, nonenum5, nonenum6, nonenum7, nonenum8, nonenum9, nonenum10, nonenum17, nonenum18, nonenum19, nonenum20, nonenum21, nonenum22, nonenum23, nonenum24, nonenum25, nonenum26, nonenum27;

    private int originnum13, originnum14, originnum12, originnum15, originnum11, originnum16, originnum0, originnum1, originnum2, originnum3, originnum4, originnum5, originnum6, originnum7, originnum8, originnum9, originnum10, originnum17, originnum18, originnum19, originnum20, originnum21, originnum22, originnum23, originnum24, originnum25, originnum26, originnum27;
    private int learn500num13, learn500num14, learn500num20012, learn500num15, learn500num11, learn500num16, learn500num0, learn500num1, learn500num2, learn500num3, learn500num4, learn500num5, learn500num6, learn500num7, learn500num8, learn500num9, learn500num10, learn500num17, learn500num18, learn500num19, learn500num20, learn500num21, learn500num22, learn500num23, learn500num24, learn500num25, learn500num26, learn500num27;
    private int learn30num13, learn30num14, learn30num20012, learn30num15, learn30num11, learn30num16, learn30num0, learn30num1, learn30num2, learn30num3, learn30num4, learn30num5, learn30num6, learn30num7, learn30num8, learn30num9, learn30num10, learn30num17, learn30num18, learn30num19, learn30num20, learn30num21, learn30num22, learn30num23, learn30num24, learn30num25, learn30num26, learn30num27;
    private int learn80num13, learn80num14, learn80num20012, learn80num15, learn80num11, learn80num16, learn80num0, learn80num1, learn80num2, learn80num3, learn80num4, learn80num5, learn80num6, learn80num7, learn80num8, learn80num9, learn80num10, learn80num17, learn80num18, learn80num19, learn80num20, learn80num21, learn80num22, learn80num23, learn80num24, learn80num25, learn80num26, learn80num27;


    private int[] LearnNums = {learnnum0, learnnum1, learnnum2, learnnum3, learnnum4, learnnum5, learnnum6, learnnum7, learnnum8, learnnum9};
    private int[] Nums = {num0, num1, num2, num3, num4, num5, num6, num7, num8, num9};
    private int[] NumsNone = {nonenum0, nonenum1, nonenum2, nonenum3, nonenum4, nonenum5, nonenum6, nonenum7, nonenum8, nonenum9};
    private int[] NumsOrigin = {originnum0, originnum1, originnum2, originnum3, originnum4, originnum5, originnum6, originnum7, originnum8, originnum9};
    private int[] Learn200Nums = {learn200num0, learn200num1, learn200num2, learn200num3, learn200num4, learn200num5, learn200num6, learn200num7, learn200num8, learn200num9};
    private int[] Learn500Nums = {learn500num0, learn500num1, learn500num2, learn500num3, learn500num4, learn500num5, learn500num6, learn500num7, learn500num8, learn500num9};
    private int none3num13, none3num14, none3num12, none3num15, none3num11, none3num16, none3num0, none3num1, none3num2, none3num3, none3num4, none3num5, none3num6, none3num7, none3num8, none3num9, none3num10, none3num17, none3num18, none3num19, none3num20, none3num21, none3num22, none3num23, none3num24, none3num25, none3num26, none3num27;

    private int[] Nums3None = {none3num0, none3num1, none3num2, none3num3, none3num4, none3num5, none3num6, none3num7, none3num8, none3num9};


    private int[] MONTHNAME = {Calendar.JANUARY, Calendar.FEBRUARY, Calendar.MARCH, Calendar.APRIL, Calendar.MAY, Calendar.JUNE,
            Calendar.JULY, Calendar.AUGUST, Calendar.SEPTEMBER, Calendar.OCTOBER, Calendar.NOVEMBER, Calendar.DECEMBER,};


    private int year;
    private int month;
    private int day;
    private static int urlNUm = 4;
    private ArrayList<String> urlsList;
    private String refreshUrl;

    public boolean isFIRST_TIME() {
        return FIRST_TIME;
    }

    private boolean FIRST_TIME = true;
    private ArrayList<MyBean.ListBean> mList;
    private int myNo;
    private String str;
    private String noStr;
    private int originNo;
    private String numNoneStr;
    private int numNone;
    private int numDif;
    private int numSame;
    private int meiyou = -1;
    private String strNo;
    private int numNo;
    private String numNoStr;
    private String resultStr = "";

    private String Url;

    private boolean IS_POLLING = false;

    private List oneList = new ArrayList<Integer>();
    private List twoList = new ArrayList<Integer>();
    private List threeList = new ArrayList<Integer>();
    private List fourList = new ArrayList<Integer>();
    private List fiveList = new ArrayList<Integer>();
    private List sixList = new ArrayList<Integer>();
    private List seventList = new ArrayList<Integer>();
    private List eightList = new ArrayList<Integer>();
    private List nineList = new ArrayList<Integer>();
    private List tenList = new ArrayList<Integer>();
    private List[] numList = {oneList, twoList, threeList, fourList, fiveList, sixList, seventList, eightList, nineList, tenList};
    private int SET_TEXT_RESULT = 0x1988;

    private int SelectedLimitPosition = 0;
    private int caculateNum = 5;
    private int learnPositon;
    private int pollingTime = 300;


    private int learn120num13, learn120num14, learn120num20012, learn120num15, learn120num11, learn120num16, learn120num0, learn120num1, learn120num2, learn120num3, learn120num4, learn120num5, learn120num6, learn120num7, learn120num8, learn120num9, learn120num10, learn120num17, learn120num18, learn120num19, learn120num20, learn120num21, learn120num22, learn120num23, learn120num24, learn120num25, learn120num26, learn120num27;
    private int[] Learn120Nums = {learn120num0, learn120num1, learn120num2, learn120num3, learn120num4, learn120num5, learn120num6, learn120num7, learn120num8, learn120num9};

    private int learn360num13, learn360num14, learn360num20012, learn360num15, learn360num11, learn360num16, learn360num0, learn360num1, learn360num2, learn360num3, learn360num4, learn360num5, learn360num6, learn360num7, learn360num8, learn360num9, learn360num10, learn360num17, learn360num18, learn360num19, learn360num20, learn360num21, learn360num22, learn360num23, learn360num24, learn360num25, learn360num26, learn360num27;
    private int[] Learn360Nums = {learn360num0, learn360num1, learn360num2, learn360num3, learn360num4, learn360num5, learn360num6, learn360num7, learn360num8, learn360num9};

    private int learn800num13, learn800num14, learn800num20012, learn800num15, learn800num11, learn800num16, learn800num0, learn800num1, learn800num2, learn800num3, learn800num4, learn800num5, learn800num6, learn800num7, learn800num8, learn800num9, learn800num10, learn800num17, learn800num18, learn800num19, learn800num20, learn800num21, learn800num22, learn800num23, learn800num24, learn800num25, learn800num26, learn800num27;
    private int[] Learn800Nums = {learn800num0, learn800num1, learn800num2, learn800num3, learn800num4, learn800num5, learn800num6, learn800num7, learn800num8, learn800num9};


    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {


            if (msg.what == SET_TEXT_RESULT) {
                if (mList.size() > 0) {
                    tvResult.setText(mList.get(0).getCTerm());
                }
                tvLearnResult.setText(learnResultStr);
                tvLearnResult2.setText(learnResultStr2);
                tvLearnResult3.setText(learnResultStr3);
                tvAllcount.setText(learnResultStr4);
            } else if (msg.what == SC_TIMMER) {
                Log.e(TAG, "time: " + DateUtils.formatDate(System.currentTimeMillis()));
                autoDealSc(false);
            } else {
                if ((msg.what + 1) == urlsList.size()) {
                    FIRST_TIME = false;
                    setAmountData();
                    tvResult.setText(resultStr);
                    tvLearnResult.setText(learnResultStr);
                    btnCaculate.setClickable(true);
                    parseList();
                }
                refreshData(urlsList.get(msg.what), msg.what);
            }

            super.handleMessage(msg);
        }
    };


    private String date;
    private ArrayList<String> difList;
    private ArrayList<String> buyPositionList;
    private ArrayList<String> buyNumList;
    private ArrayList<String> buyDifList;
    private String splitStr = "数字：";
    protected WebView waWebviewDetail;


    /**
     * 通过静态方法实例化自动化Fragment
     */
    public static void start(Activity mContext, int containerRsID) {
        WA_MainFragment mCurrentFragment = WA_MainFragment.getInstence();
        mContext.getFragmentManager().beginTransaction().replace(containerRsID, mCurrentFragment).commit();
    }

    private static WA_MainFragment getInstence() {
        WA_MainFragment webAutoFragment = new WA_MainFragment();
        return webAutoFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (null != bundle) {
            parameter = (WA_Parameters) bundle.getSerializable(ARG_CODE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wa_fragment_main, container, false);
        ButterKnife.bind(this, view);
        findViews(view);
        initData();
        setListener(view);

        mActivity = (MainActivity) getActivity();
        etDate.setText(mActivity.getStrTime());

        stringIntegerHashMap = new HashMap<String, Integer>();
        originalMap = new HashMap<Integer, String>();
        originalSameNunMap = new HashMap<Integer, String>();
//        originalDisplayMap = new HashMap<Integer, String>();
//        originalDisplaySameNunMap = new HashMap<Integer, String>();
        baseMap = new HashMap<Integer, Integer>();
        baseSameNumMap = new HashMap<Integer, Integer>();
        glMap = new HashMap<Integer, String>();
        difList = new ArrayList<String>();
        buyPositionList = new ArrayList<String>();
        buyNumList = new ArrayList<String>();
        buyDifList = new ArrayList<String>();
        gson = new Gson();

        Bundle arguments = getArguments();
        if (null != arguments) {
            IS_SC = arguments.getBoolean("issc");
        }

        if (IS_SC) {
            etNo.setHint("sc");
            Url = myurlpk10;
        } else {
            etNo.setHint("ft");
            Url = myurlxyft;
        }


        btnGet.setOnClickListener(this);
        btnRise.setOnClickListener(this);
        btnDiscount.setOnClickListener(this);
        btnCaculate.setOnClickListener(this);
        btnNormal.setOnClickListener(this);
        btnBig.setOnClickListener(this);
        btnChange.setOnClickListener(this);
        btnUseful.setOnClickListener(this);
        btnChange.setVisibility(View.GONE);
        btnLearn.setOnClickListener(this);
        btnCheck.setOnClickListener(this);
        btnReset.setOnClickListener(this);

        btnChangeLottory.setOnClickListener(this);
        btnAmountDiscount.setOnClickListener(this);
        btnAmountRise.setOnClickListener(this);
        btnResetdata.setOnClickListener(this);
        btnCustom.setOnClickListener(this);

        setAmountData();

        return view;
    }

    private void setAmountData() {
        if (!TextUtils.isEmpty(CacheUtils.getCache(getActivity(), "COMPARE_AMOUMT"))) {
            amountStr = CacheUtils.getCache(getActivity(), "COMPARE_AMOUMT");
        }
        if (!TextUtils.isEmpty(CacheUtils.getCache(getActivity(), "COMPARE_AMOUMT"))) {
            amountStr = amountStr + "\n" + CacheUtils.getCache(getActivity(), "BEGIN_AMOUMT");

        }
        tvAmount.setText(amountStr);

    }

    private void findViews(View view) {
        listWeb = (WebView) view.findViewById(R.id.wa_webview_list);
        initWebView(listWeb);
        listWeb.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        listWeb.getSettings().setLoadWithOverviewMode(true);
        detailWeb = (WebView) view.findViewById(R.id.wa_webview_detail);
        startBtn = (Button) view.findViewById(R.id.btn_go);
        btn_sc = (Button) view.findViewById(R.id.btn_sc);
        btn_ft = (Button) view.findViewById(R.id.btn_ft);
        btn_sc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goSc();
            }
        });

        btn_ft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goFt();
            }
        });

    }

    private void initWebView(WebView web) {
        //支持javascript
        web.getSettings().setJavaScriptEnabled(true);
        // 设置可以支持缩放
        web.getSettings().setSupportZoom(true);
        // 设置出现缩放工具
        web.getSettings().setBuiltInZoomControls(true);
        //扩大比例的缩放
        web.getSettings().setUseWideViewPort(true);
        //自适应屏幕
        web.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        web.getSettings().setLoadWithOverviewMode(true);
    }

    /**
     * 初始化两个不同功用的WebView
     */
    private void initData() {
        etAllcount.setText(ConstantValue.AllCountCoordinate + "");
        etBlank10.setText(ConstantValue.autoSame10 + "");
        etBlank15.setText(ConstantValue.autoSame15 + "");
        etBlank20.setText(ConstantValue.autoSame20 + "");
        initListWeb();
        initDetailWeb();
        //deleteLog();
        injectJS = getJsFromFile(getActivity(), BASIC_JS_PATH) + getJsFromFile(getActivity(), LOGIC_JS_PATH);
    }

    private void initListWeb() {
        WebSettings webSetting = listWeb.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setDefaultTextEncodingName("utf-8");
        webSetting.setLoadWithOverviewMode(true);
        webSetting.setAllowFileAccess(true);

        webSetting.setJavaScriptEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setAllowFileAccess(true);// 设置允许访问文件数据
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSetting.setDomStorageEnabled(true);
        webSetting.setDatabaseEnabled(true);

        listWeb.loadUrl(ConstantValue.webUrl);
        listWeb.setWebViewClient(new MyListWebViewClient());
        mLocalMethod = new LocalMethod(getActivity(), parameter);
        listWeb.addJavascriptInterface(mLocalMethod, "localMethod");
    }

    private void initDetailWeb() {
        WebSettings webSetting = detailWeb.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setDefaultTextEncodingName("utf-8");
        webSetting.setLoadWithOverviewMode(true);
        webSetting.setAllowFileAccess(true);

        webSetting.setJavaScriptEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setAllowFileAccess(true);// 设置允许访问文件数据
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSetting.setDomStorageEnabled(true);
        webSetting.setDatabaseEnabled(true);

        detailWeb.setWebViewClient(new MyDetailWebViewClient());

        detailWeb.loadUrl(ConstantValue.webUrl);
        mLocalMethod = new LocalMethod(getActivity(), parameter);
        detailWeb.addJavascriptInterface(mLocalMethod, "localMethod");
    }

    private void setListener(View view) {

        //TODO

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showComfirDialog(false);
                listWeb.reload();
                detailWeb.reload();
            }
        });
    }

    @Override
    public void onClick(View view) {


        learnResultStr = "";
        learnResultStr2 = "";

        switch (view.getId()) {
            case R.id.btn_change_lottory:
                if (IS_SC) {
                    IS_SC = false;
                } else {
                    IS_SC = true;
                }
                onClick(btnReset);
                break;
            case R.id.btn_reset:
                FIRST_TIME = true;

                if (!TextUtils.isEmpty(etLess.getText().toString())) {
                    urlNUm = Integer.parseInt(etLess.getText().toString());
                } else {
                    urlNUm = 4;
                }
                if (IS_SC) {
                    etNo.setHint("sc");
                    Url = myurlpk10;
                } else {
                    etNo.setHint("ft");
                    Url = myurlxyft;
                }
                break;

            case R.id.btn_change:
                if (IS_SC) {
                    IS_SC = false;
                    etNo.setHint("ft");
                    Url = myurlxyft;
                } else {
                    IS_SC = true;
                    etNo.setHint("sc");
                    Url = myurlpk10;
                }
                break;
            case R.id.btn_get:
                if (!TextUtils.isEmpty(etDate.getText().toString())) {
                    String[] split = etDate.getText().toString().split("-");
                    year = Integer.parseInt(split[0]);
                    month = Integer.parseInt(split[1]);
                    day = Integer.parseInt(split[2]);
//                    day = day - 1;
                    if (FIRST_TIME) {
                        initUrls();
//                        day = day + 1;
                        if (month >= 10) {

                            if (day < 10) {
                                Url = Url + year + month + "0" + day;
                            } else {
                                Url = Url + year + month + day;
                            }
                        } else {


                            if (day < 10) {
                                Url = Url + year + "0" + month + "0" + day;
                            } else {
                                Url = Url + year + "0" + month + day;
                            }
                        }
                        FIRST_TIME = false;
                    }
                    getDataFromNet();
                }
                break;
            case R.id.btn_rise:
                strNo = etNo.getText().toString();

                if (!TextUtils.isEmpty(etCaculate.getText().toString())) {
                    caculateNum = Integer.parseInt(etCaculate.getText().toString());
                }
                if (!TextUtils.isEmpty(strNo)) {
                    try {
                        numNo = Integer.parseInt(strNo);
                    } catch (Exception e) {
                        numNoStr = "0";
                        numNo = Integer.parseInt(strNo);
                    }
                    strNo = (numNo + caculateNum) + "";
                    etNo.setText(strNo);
                }
                onClick(btnCaculate);
                break;
            case R.id.btn_discount:

                if (!TextUtils.isEmpty(etCaculate.getText().toString())) {
                    caculateNum = Integer.parseInt(etCaculate.getText().toString());
                }
                strNo = etNo.getText().toString();
                if (!TextUtils.isEmpty(strNo)) {

                    try {
                        numNo = Integer.parseInt(strNo);
                    } catch (Exception e) {
                        numNoStr = "0";
                        numNo = Integer.parseInt(strNo);
                    }
                    numNo = Integer.parseInt(strNo);
                    strNo = (numNo - caculateNum) + "";
                    if ((numNo - caculateNum) < 0) {
                        strNo = "0";
                    }
                    etNo.setText(strNo);
                }
                onClick(btnCaculate);
                break;
            case R.id.btn_amount_rise:
                amount = etAmount.getText().toString();
                etAmount.setText((Integer.parseInt(amount) + 1) + "");
                ConstantUtils.setOriginAmount(etAmount.getText().toString());


                break;
            case R.id.btn_amount_discount:
                amount = etAmount.getText().toString();
                if (amount.equals("1")) {
                    return;
                }
                etAmount.setText((Integer.parseInt(amount) - 1) + "");
                ConstantUtils.setOriginAmount(etAmount.getText().toString());
                break;
            case R.id.btn_normal:
                if (meiyou == -1 || meiyou == (numNoneNormal.length - 1)) {
                    meiyou = 0;
                } else {
                    meiyou = meiyou + 1;
                }
                try {
                    etNone.setText(numNoneNormal[meiyou] + "");
                } catch (Exception e) {
                    meiyou = 0;
                }
                break;
            case R.id.btn_big:
                if (meiyou == -1 || meiyou == (numNoneBig.length - 1)) {
                    meiyou = 0;
                } else {
                    meiyou = meiyou + 1;
                }

                try {
                    etNone.setText(numNoneBig[meiyou] + "");

                } catch (Exception e) {
                    meiyou = 0;
                }
                break;
            case R.id.btn_useful:
                if (meiyou == -1 || meiyou == (useful.length - 1)) {
                    meiyou = 0;
                } else {
                    meiyou = meiyou + 1;
                }

                try {
                    etNone.setText(useful[meiyou] + "");

                } catch (Exception e) {
                    meiyou = 0;
                }
                break;
            case R.id.btn_caculate:
                stringIntegerHashMap.clear();
//                learn120BeanMap.clear();
                originalMap.clear();
                originalSameNunMap.clear();
//                originalDisplayMap.clear();
//                originalDisplaySameNunMap.clear();
                baseMap.clear();
                baseSameNumMap.clear();
                glMap.clear();
//                learnBeanMap.clear();

                if (!TextUtils.isEmpty(et10mutiple.getText().toString())) {
                    mutiple10 = et10mutiple.getText().toString();
                } else {
                    mutiple10 = "0";
                }
                if (!TextUtils.isEmpty(et10none.getText().toString())) {
                    none10 = et10none.getText().toString();
                } else {
                    none10 = "1";
                }
                if (!TextUtils.isEmpty(et20none.getText().toString())) {
                    none20 = et20none.getText().toString();
                } else {
                    none20 = "1";
                }
                if (mList.size() == 0) {
                    UIUtils.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            getDataFromNet();
                        }
                    }, 3000);

                } else if (SelectedLimitPosition != -1) {
                    getSelectedResult(SelectedLimitPosition);
                    return;
                }


                break;
            //TODO 点击事件
            case R.id.btn_learn:
                showComfirDialog(true);
                break;
            case R.id.btn_resetdata:
                showResetDialog();
                break;
            case R.id.btn_custom:
                if (btnCustom.getText().toString().equals(getResources().getString(R.string.custom))) {
                    btnCustom.setText("class");
                } else {
                    btnCustom.setText(getResources().getString(R.string.custom));
                }
                break;

        }

    }

    private void showResetDialog() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Reset" + " Go?");
        builder.setTitle("Tips");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                resetDealData();
            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }

    /**
     * ListWebView加载完注入基本JS函数
     */
    private class MyListWebViewClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            view.loadUrl("javascript:" + injectJS);
            super.onPageFinished(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }
    }

    /**
     * DetailWebView加载完注入基本JS函数并且关闭对话框
     */
    private class MyDetailWebViewClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            view.loadUrl("javascript:" + injectJS);
            doAlertHide();
            super.onPageFinished(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }
    }


    public void getSelectedResult(final int limitPositon) {
        SelectedLimitPosition = limitPositon;
        if (limitPositon == -1) {
            onClick(btnCaculate);
            return;
        }


        if (TextUtils.isEmpty(etNo.getText().toString())) {
            noStr = "0";
        } else {
            noStr = etNo.getText().toString();
        }

        originNo = Integer.parseInt(noStr);
        allCount = 0;

        new Thread(new Runnable() {


            @Override
            public void run() {
                btnCaculate.setClickable(false);
                resultStr = "";
                learnResultStr = "";
                learnResultStr2 = "";
                learnResultStr3 = "";
                learnResultStr4 = "";
                numNoneStr = etNone.getText().toString();
                numNoStr = etNo.getText().toString();


                if (TextUtils.isEmpty(numNoneStr)) {
                    numNoneStr = "0";
                }
                if (TextUtils.isEmpty(numNoStr)) {
                    numNoStr = "0";
                }
                numNone = Integer.parseInt(numNoneStr);

                try {
                    numNo = Integer.parseInt(numNoStr);
                } catch (Exception e) {
                    numNoStr = "0";
                    numNo = Integer.parseInt(numNoStr);
                }
                resultStr = mList.get(numNo).getCTerm() + "\n";
                resultStr = resultStr + "all:" + (mList.size() - numNo) + "\n";

                resultStr = resultStr + "**********************************" + "\n";
                for (int i = 0; i < 10; i++) {
                    caculateCompareNoneResult(i, true);
                }
                //TODO
                if (TextUtils.isEmpty(etNone.getText().toString())) {
                    numNone = 15;
                } else {
                    numNone = Integer.parseInt(etNone.getText().toString());
                }

                if (TextUtils.isEmpty(etDif.getText().toString())) {
                    numDif = 43;
                } else {
                    numDif = Integer.parseInt(etDif.getText().toString());
                }

                if (TextUtils.isEmpty(etSame.getText().toString())) {
                    numSame = 28;
                } else {
                    numSame = Integer.parseInt(etSame.getText().toString());
                }

                if (TextUtils.isEmpty(etNo.getText().toString())) {
                    noStr = "0";
                } else {
                    noStr = etNo.getText().toString();
                }
                for (int k = 150; k > 0; k--) {
                    originNo = k + Integer.parseInt(noStr);
                    for (int i = 0; i < 10; i++) {
                        try {
                            caculateCompareNoneResult(i, false);
                        } catch (Exception e) {
//                            ToastUtils.showShortToast(getActivity(), "出错，请重试");
                        }
                    }
                }
                difList.clear();
                buyNumList.clear();
                buyPositionList.clear();
                buyDifList.clear();
                for (int i = 1; i <= 10; i++) {
                    ArrayList<String> sameList = new ArrayList<String>();
                    ArrayList<String> sameNumList = new ArrayList<String>();
                    for (int j = 0; j < 10; j++) {

                        if (!TextUtils.isEmpty(originalMap.get(i * 10 + j))) {
                            resultStr = resultStr + originalMap.get(i * 10 + j) +
                                    "----------------------------------------" + "\n";
                            if (baseMap.get(i * 10 + j) >= numDif) {
                                difList.add(originalMap.get(i * 10 + j));
                            }

                            if (baseMap.get(i * 10 + j) >= numSame) {
                                sameList.add(originalMap.get(i * 10 + j));
                            }


                        }


                        if (null != originalSameNunMap.get(i * 10 + j)) {
                            if (null != baseSameNumMap.get(i * 10 + j) && baseSameNumMap.get(i * 10 + j) >= numSame) {
                                sameNumList.add(originalSameNunMap.get(i * 10 + j));
                            }
                        }

                        if (!TextUtils.isEmpty(glMap.get(i * 10 + j))) {
                            String result = glMap.get(i * 10 + j);
                            String[] split = result.split("%");

                        }
                    }
                    //TODO
                    if (sameList.size() >= 2) {

                        if (TextUtils.isEmpty(etBlank.getText().toString())) {
                            for (int j = 0; j < sameList.size(); j++) {
                                learnResultStr2 = learnResultStr2 + sameList.get(j) + "\n" + UIUtils.getString(R.string.line) + "\n";
                                buyPositionList.add(sameList.get(j));
                            }
                            allCount = allCount + sameList.size();

                        } else {
                            getBlankBuyResult(sameList, true, false);

                        }
                    }
                    if (sameNumList.size() >= 2) {

                        if (TextUtils.isEmpty(etBlank.getText().toString())) {

                            for (int j = 0; j < sameNumList.size(); j++) {
                                learnResultStr3 = learnResultStr3 + sameNumList.get(j) + "\n" + UIUtils.getString(R.string.line) + "\n";
                                buyNumList.add(sameNumList.get(j));
                            }
                            allCount = allCount + sameNumList.size();

                        } else {
                            getBlankBuyResult(sameNumList, false, false);

                        }
                    }


                }


                if (difList.size() >= 3) {
                    learnResultStr2 = learnResultStr2 + "\n" + "不同位置:" + "\n";
                    getBlankBuyResult(difList, false, true);
//                    for (int i = 0; i < difList.size(); i++) {
//                        learnResultStr2 = learnResultStr2 + difList.get(i) + "\n" + UIUtils.getString(R.string.line) + "\n";
//                        buyDifList.add(difList.get(i));
//                    }
//                    allCount = allCount + difList.size();
                }

                learnResultStr4 = "allCount:" + allCount + "      allNum:" + glMap.size();

                if (difList.size() >= 2) {
                    learnResultStr3 = learnResultStr3 + "\n" + "不同位置:" + "\n";
                    for (int i = 0; i < difList.size(); i++) {
                        learnResultStr3 = learnResultStr3 + difList.get(i) + "\n" + UIUtils.getString(R.string.line) + "\n";
                    }
                }

                Message message = myHandler.obtainMessage();
                message.what = SET_TEXT_RESULT;
                myHandler.sendMessage(message);
                btnCaculate.setClickable(true);
            }


        }).start();
    }

    //TODO
    private void getBlankBuyResult(ArrayList<String> sameList, boolean IsSamePositon, boolean isDifNum) {
//        if (sameList.size() > 2) {
//            return;
//        }
        int blank = Integer.parseInt(etBlank.getText().toString());
        int[] ints = new int[sameList.size()];
        for (int j = 0; j < sameList.size(); j++) {
            String[] split = sameList.get(j).split(splitStr);
            ints[j] = Integer.parseInt(split[2]);
        }
        String[] sortStr = sortList(sameList, ints);
        int[] mInts = sortInt(ints);
        int length = mInts.length;
        if ((mInts[length - 1] - mInts[length - 2]) >= blank || (mInts[length - 1] - mInts[length - 2]) >= blank) {
//            if ((mInts[length - 1] - mInts[length - 2]) < 30) {
//            }
            if (isDifNum) {

                for (int j = length - 1; j >= length - 3; j--) {
                    learnResultStr3 = learnResultStr3 + sortStr[j] + "\n" + UIUtils.getString(R.string.line) + "\n";
                    buyDifList.add(sortStr[j]);
                }
            } else {
                if (mInts[length - 1] >= beginInt) {
                    allCount = allCount + 2;
                    for (int j = length - 1; j >= length - 2; j--) {
                        if (IsSamePositon) {
                            learnResultStr2 = learnResultStr2 + sortStr[j] + "\n" + UIUtils.getString(R.string.line) + "\n";
                            buyPositionList.add(sortStr[j]);
                        } else {
                            learnResultStr3 = learnResultStr3 + sortStr[j] + "\n" + UIUtils.getString(R.string.line) + "\n";
                            buyNumList.add(sortStr[j]);
                        }
                    }
                }
            }
        }
//        sortList(ints);
//        if ((ints[1] - ints[0]) >= blank || (ints[0] - ints[1]) >= blank) {
//            allCount = allCount + sameList.size();
//            for (int j = 0; j < 2; j++) {
//                if (IsSamePositon) {
//                    learnResultStr2 = learnResultStr2 + sameList.get(j) + "\n" + UIUtils.getString(R.string.line) + "\n";
//                    buyPositionList.add(sameList.get(j));
//                } else {
//                    learnResultStr3 = learnResultStr3 + sameList.get(j) + "\n" + UIUtils.getString(R.string.line) + "\n";
//                    buyNumList.add(sameList.get(j));
//                }
//            }
//        }

    }

    //TODO
    private void caculateCompareNoneResult(int i, boolean IS_GET_RECENT_RECORD) {

        resetNum();
        learnPositon = (i + 1);
        generalResult(numList[i]);

        limit(Nums, numList[i], 200);
        limit(LearnNums, numList[i], 2000);
        limit(Learn500Nums, numList[i], 500);


        limit(Learn120Nums, numList[i], 120);
        limit(Learn360Nums, numList[i], 360);
        limit(Learn800Nums, numList[i], 800);
        resultCompareNone(IS_GET_RECENT_RECORD);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }


    private void generalResult(List<Integer> list) {


        for (int i = originNo; i < list.size(); i++) {
            switch (list.get(i) - 1) {
                case 0:
                    NumsOrigin[0]++;
                    break;
                case 1:
                    NumsOrigin[1]++;
                    break;
                case 2:
                    NumsOrigin[2]++;
                    break;
                case 3:
                    NumsOrigin[3]++;
                    break;
                case 4:
                    NumsOrigin[4]++;
                    break;
                case 5:
                    NumsOrigin[5]++;
                    break;
                case 6:
                    NumsOrigin[6]++;
                    break;
                case 7:
                    NumsOrigin[7]++;
                    break;
                case 8:
                    NumsOrigin[8]++;
                    break;
                case 9:
                    NumsOrigin[9]++;
                    break;
                case 10:
                    NumsOrigin[10]++;
                    break;
                case 11:
                    NumsOrigin[11]++;
                    break;
                case 12:
                    NumsOrigin[12]++;
                    break;
                case 13:
                    NumsOrigin[13]++;
                    break;
                case 14:
                    NumsOrigin[14]++;
                    break;
                case 15:
                    NumsOrigin[15]++;
                    break;
                case 16:
                    NumsOrigin[16]++;
                    break;
                case 17:
                    NumsOrigin[17]++;
                    break;
                case 18:
                    NumsOrigin[18]++;
                    break;
                case 19:
                    NumsOrigin[19]++;
                    break;
                case 20:
                    NumsOrigin[20]++;
                    break;
                case 21:
                    NumsOrigin[21]++;
                    break;
                case 22:
                    NumsOrigin[22]++;
                    break;
                case 23:
                    NumsOrigin[23]++;
                    break;
                case 24:
                    NumsOrigin[24]++;
                    break;
                case 25:
                    NumsOrigin[25]++;
                    break;
                case 26:
                    NumsOrigin[26]++;
                    break;
                case 27:
                    NumsOrigin[27]++;
                default:
                    break;
            }
        }
        for (int i = originNo + numNone; i < list.size(); i++) {
            switch (list.get(i) - 1) {

                case 0:
                    NumsNone[0]++;
                    break;
                case 1:
                    NumsNone[1]++;
                    break;
                case 2:
                    NumsNone[2]++;
                    break;
                case 3:
                    NumsNone[3]++;
                    break;
                case 4:
                    NumsNone[4]++;
                    break;
                case 5:
                    NumsNone[5]++;
                    break;
                case 6:
                    NumsNone[6]++;
                    break;
                case 7:
                    NumsNone[7]++;
                    break;
                case 8:
                    NumsNone[8]++;
                    break;
                case 9:
                    NumsNone[9]++;
                    break;
                case 10:
                    NumsNone[10]++;
                    break;
                case 11:
                    NumsNone[11]++;
                    break;
                case 12:
                    NumsNone[12]++;
                    break;
                case 13:
                    NumsNone[13]++;
                    break;
                case 14:
                    NumsNone[14]++;
                    break;
                case 15:
                    NumsNone[15]++;
                    break;
                case 16:
                    NumsNone[16]++;
                    break;
                case 17:
                    NumsNone[17]++;
                    break;
                case 18:
                    NumsNone[18]++;
                    break;
                case 19:
                    NumsNone[19]++;
                    break;
                case 20:
                    NumsNone[20]++;
                    break;
                case 21:
                    NumsNone[21]++;
                    break;
                case 22:
                    NumsNone[22]++;
                    break;
                case 23:
                    NumsNone[23]++;
                    break;
                case 24:
                    NumsNone[24]++;
                    break;
                case 25:
                    NumsNone[25]++;
                    break;
                case 26:
                    NumsNone[26]++;
                    break;
                case 27:
                    NumsNone[27]++;
                default:
                    break;
            }
        }


        for (int i = originNo + numNone + 1; i < list.size(); i++) {
            switch (list.get(i) - 1) {

                case 0:
                    Nums3None[0]++;
                    break;
                case 1:
                    Nums3None[1]++;
                    break;
                case 2:
                    Nums3None[2]++;
                    break;
                case 3:
                    Nums3None[3]++;
                    break;
                case 4:
                    Nums3None[4]++;
                    break;
                case 5:
                    Nums3None[5]++;
                    break;
                case 6:
                    Nums3None[6]++;
                    break;
                case 7:
                    Nums3None[7]++;
                    break;
                case 8:
                    Nums3None[8]++;
                    break;
                case 9:
                    Nums3None[9]++;
                    break;
                case 10:
                    Nums3None[10]++;
                    break;
                case 11:
                    Nums3None[11]++;
                    break;
                case 12:
                    Nums3None[12]++;
                    break;
                case 13:
                    Nums3None[13]++;
                    break;
                case 14:
                    Nums3None[14]++;
                    break;
                case 15:
                    Nums3None[15]++;
                    break;
                case 16:
                    Nums3None[16]++;
                    break;
                case 17:
                    Nums3None[17]++;
                    break;
                case 18:
                    Nums3None[18]++;
                    break;
                case 19:
                    Nums3None[19]++;
                    break;
                case 20:
                    Nums3None[20]++;
                    break;
                case 21:
                    Nums3None[21]++;
                    break;
                case 22:
                    Nums3None[22]++;
                    break;
                case 23:
                    Nums3None[23]++;
                    break;
                case 24:
                    Nums3None[24]++;
                    break;
                case 25:
                    Nums3None[25]++;
                    break;
                case 26:
                    Nums3None[26]++;
                    break;
                case 27:
                    Nums3None[27]++;
                default:
                    break;
            }
        }


    }


    private void resultCompareNone(boolean IS_GET_RECENT_RECORD) {
        for (int i = 0; i < 10; i++) {

            if ((NumsOrigin[i] - NumsNone[i]) == 0) {

                if (Nums[i] != 0) {
                    if (IS_GET_RECENT_RECORD) {
                        putRecentCount(i);
                    } else {
                        getResult(i);
                    }
                }
            }
        }


    }

    private void putRecentCount(int i) {
        stringIntegerHashMap.put(learnPositon + "_" + (i + 1), NumsOrigin[i]);

    }

    private void getResult(int i) {
        if (stringIntegerHashMap.get(learnPositon + "_" + (i + 1)) == NumsOrigin[i] && (Nums3None[i] != NumsOrigin[i])) {

            if (TextUtils.isEmpty(etNo.getText().toString())) {
                noStr = "0";
            } else {
                noStr = etNo.getText().toString();
            }


            float num = (float) (NumsOrigin[i] - Learn800Nums[i]) / 800;
            DecimalFormat df = new DecimalFormat("#.00%");//格式化小数
            String s = df.format(num);//返回的是String类型
            Log.i("strTime", "DecimalFormat: " + s);

            String gailv = ((mList.size() - numNo)) + ":" + df.format((float) NumsOrigin[i] / (mList.size() - numNo)) + "_2000:" + df.format((float) (NumsOrigin[i] - LearnNums[i]) / 2000) +
                    "_800:" + df.format((float) (NumsOrigin[i] - Learn800Nums[i]) / 800) + "_500:" + df.format((float) (NumsOrigin[i] - Learn500Nums[i]) / 500) + "_360:" + df.format((float) (NumsOrigin[i] - Learn360Nums[i]) / 360) +
                    "_200:" + df.format((float) (NumsOrigin[i] - Nums[i]) / 200) + "_120:" + df.format((float) (NumsOrigin[i] - Learn120Nums[i]) / 120);

            String glResult = df.format((float) NumsOrigin[i] / (mList.size() - numNo)) + df.format((float) (NumsOrigin[i] - LearnNums[i]) / 2000) +
                    df.format((float) (NumsOrigin[i] - Learn800Nums[i]) / 800) + df.format((float) (NumsOrigin[i] - Learn500Nums[i]) / 500) + df.format((float) (NumsOrigin[i] - Learn360Nums[i]) / 360) +
                    df.format((float) (NumsOrigin[i] - Nums[i]) / 200) + df.format((float) (NumsOrigin[i] - Learn120Nums[i]) / 120);

            glMap.put(learnPositon * 10 + i, glResult);
            //TODO
//			originalDisplayMap.put(learnPositon * 10 + i, "位置：" + "(" + learnPositon + ")    " + "数字：" + "(" + +(i + 1) + ")    " + "   none：" + (numNone + originNo - Integer.parseInt(noStr)) + "\n"
//					+ gailv + "\n");
            originalMap.put(learnPositon * 10 + i, learnPositon + "数字：" + (i + 1) + "数字：" + (numNone + originNo - Integer.parseInt(noStr)));

            baseMap.put(learnPositon * 10 + i, (numNone + originNo - numNo));
//			originalDisplaySameNunMap.put((i + 1) * 10 + (learnPositon - 1), "位置：" + "(" + learnPositon + ")    " + "数字：" + "(" + +(i + 1) + ")    " + "   none：" + (numNone + originNo - Integer.parseInt(noStr)) + "\n"
//					+ gailv + "\n");
            originalSameNunMap.put((i + 1) * 10 + (learnPositon - 1), learnPositon + "数字：" + (i + 1) + "数字：" + (numNone + originNo - Integer.parseInt(noStr)));
            baseSameNumMap.put((i + 1) * 10 + (learnPositon - 1), (numNone + originNo - numNo));
//
        }
    }


    private void gsonParse(String result) {
        Gson gson = new Gson();
        MyBean pcOriginBean = gson.fromJson(result, MyBean.class);
        TAG = pcOriginBean.getList().get(0).getCTerm();
        mList.addAll(pcOriginBean.getList());

    }

    private void resetList() {

        if (mList == null) {
            mList = new ArrayList<MyBean.ListBean>();
        }
        mList.clear();
        for (int i = 0; i < numList.length; i++) {
            numList[i].clear();
        }
    }


    private void resetNum() {
        for (int i = 0; i < Nums.length; i++) {
            Nums[i] = 0;
        }
        for (int i = 0; i < NumsNone.length; i++) {
            NumsNone[i] = 0;
        }
        for (int i = 0; i < NumsOrigin.length; i++) {
            NumsOrigin[i] = 0;
        }
        for (int i = 0; i < LearnNums.length; i++) {
            LearnNums[i] = 0;
        }
        for (int i = 0; i < Learn200Nums.length; i++) {
            Learn200Nums[i] = 0;
        }
        for (int i = 0; i < Learn500Nums.length; i++) {
            Learn500Nums[i] = 0;
        }

        for (int i = 0; i < Nums3None.length; i++) {
            Nums3None[i] = 0;
        }

        for (int i = 0; i < Learn120Nums.length; i++) {
            Learn120Nums[i] = 0;
        }
        for (int i = 0; i < Learn360Nums.length; i++) {
            Learn360Nums[i] = 0;
        }
        for (int i = 0; i < Learn800Nums.length; i++) {
            Learn800Nums[i] = 0;
        }

    }


    private String lashMoth(int daycount) {
        int maxDay;
        maxDay = Utils.getMaxDay(year, month);
        if (month == 1) {
//            return Url + (year - 1) + "12" + (maxDay + daycount) + "";

            if ((maxDay + daycount) >= 10) {
                return Url + (year - 1) + "12" + (maxDay + daycount) + "";
            } else {
                return Url + (year - 1) + "12" + "0" + (maxDay + daycount) + "";
            }
        } else {
            if ((month - 1) < 10) {
                if ((maxDay + daycount) > 10) {
                    return Url + year + "0" + (month - 1) + (maxDay + daycount) + "";
                } else {
                    return Url + year + "0" + (month - 1) + "0" + (maxDay + daycount) + "";
                }
            } else {

                if ((maxDay + daycount) >= 10) {
                    return Url + year + (month - 1) + (maxDay + daycount) + "";
                } else {
                    return Url + year + (month - 1) + "0" + (maxDay + daycount) + "";
                }

            }
        }
    }


    private void initUrls() {
        urlsList = new ArrayList<String>();

        for (int i = 1; i <= urlNUm; i++) {
            if ((day - i) <= 0) {
                urlsList.add(lashMoth(day - i));

            }
            if (month < 10) {
                if ((day - i) < 10 && (day - i) > 0) {
                    refreshUrl = Url + year + "0" + month + "0" + (day - i) + "";
                    urlsList.add(refreshUrl);
                }
                if ((day - i) >= 10) {

                    refreshUrl = Url + year + "0" + month + (day - i) + "";
                    urlsList.add(refreshUrl);
                }
            } else {
                if ((day - i) < 10 && (day - i) > 0) {
                    refreshUrl = Url + year + month + "0" + (day - i) + "";
                    urlsList.add(refreshUrl);
                }
                if ((day - i) >= 10) {

                    refreshUrl = Url + year + month + (day - i) + "";
                    urlsList.add(refreshUrl);
                }

            }
        }
    }

    private void getDataFromNet() {
        resultStr = "";
        learnResultStr = "";
        learnResultStr2 = "";
        learnResultStr3 = "";
        learnResultStr4 = "";
        tvLearnResult.setText("");
        tvLearnResult2.setText("");
        tvLearnResult3.setText("");
        btnCaculate.setClickable(false);


        VolleyManager.newInstance().StrRequest(TAG, Request.Method.GET, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                resetList();
                try {
                    gsonParse(response);
                    IS_GSON_RETRY = false;
                    Toast.makeText(getActivity(), "成功", Toast.LENGTH_LONG).show();
                    afterGetData();
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "请求频率过高", Toast.LENGTH_LONG).show();
                    CacheUtils.putCache(getActivity(), Url, "");
                    if (IS_SC && DateUtils.isInRange(9, 10, 23, 59)) {
                        IS_GSON_RETRY = true;
                        onClick(btnCaculate);
                        UIUtils.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                getDataFromNet();
                            }
                        }, 3000);
                    } else if (!IS_SC) {
                        if (DateUtils.isInRange(13, 20, 23, 59) || DateUtils.isInRange(0, 0, 4, 10)) {
                            IS_GSON_RETRY = true;
                            onClick(btnCaculate);
                            UIUtils.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    getDataFromNet();
                                }
                            }, 3000);
                        }
                    }
                    afterGetData();

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "失败", Toast.LENGTH_LONG).show();
                if (IS_AUTO_GET) {
                    UIUtils.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            autoDealSc(true);
                        }
                    }, 1500);
                }
                Log.e(TAG, "onErrorResponse: " + error.toString());
            }
        });


    }

    private void afterGetData() {
        if (!IS_GSON_RETRY) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < urlsList.size(); i++) {
                        Message message = myHandler.obtainMessage();
                        message.what = i;
                        if (TextUtils.isEmpty(CacheUtils.getCache(getActivity(), urlsList.get(i)))) {
                            try {
                                Thread.sleep(1200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        myHandler.sendEmptyMessage(i);
                    }
                }
            }).start();
        }
    }

    private void refreshData(final String mUrl, final int mI) {

        if (IS_SC) {
            date = mUrl.replace(myurlpk10, "date:");
        } else {
            date = mUrl.replace(myurlxyft, "date:");
        }

        if (TextUtils.isEmpty(CacheUtils.getCache(getActivity(), mUrl))) {


            VolleyManager.newInstance().StrRequest(TAG, Request.Method.GET, mUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {


                    try {
                        resultStr = resultStr + date + "\n";
                        refreshgsonParse(response);
                        CacheUtils.putCache(getActivity(), mUrl, response);
                    } catch (Exception e) {
                        resultStr = resultStr + "ERROE:" + date;
                        Toast.makeText(getActivity(), mI + "解析失败请求频率过高", Toast.LENGTH_LONG).show();
                        CacheUtils.putCache(getActivity(), mUrl, "");
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getActivity(), mI + "失败", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            resultStr = resultStr + date + "\n";
            refreshgsonParse(CacheUtils.getCache(getActivity(), mUrl));

        }


        if (IS_AUTO_GET) {
//            goScOrFt();
            autoDealMethod();
            IS_AUTO_GET = false;
        }


    }

    private void parseList() {

        for (int i = 0; i < mList.size(); i++) {

            String lottery_nums = mList.get(i).getCTermResult();
            String[] split = lottery_nums.split(",");
            for (int j = 0; j < split.length; j++) {
                numList[j].add(Integer.parseInt(split[j]));
            }
        }
    }


    private void refreshgsonParse(String response) {
        Gson gson = new Gson();
        MyBean pcBean = gson.fromJson(response, MyBean.class);
        mList.addAll(pcBean.getList());
    }


    private void limit(int[] nums, List<Integer> list, int nummax) {

        for (int i = originNo + nummax; i < list.size(); i++) {
            switch (list.get(i) - 1) {

                case 0:
                    nums[0]++;
                    break;
                case 1:
                    nums[1]++;
                    break;
                case 2:
                    nums[2]++;
                    break;
                case 3:
                    nums[3]++;
                    break;
                case 4:
                    nums[4]++;
                    break;
                case 5:
                    nums[5]++;
                    break;
                case 6:
                    nums[6]++;
                    break;
                case 7:
                    nums[7]++;
                    break;
                case 8:
                    nums[8]++;
                    break;
                case 9:
                    nums[9]++;
                    break;
                case 10:
                    nums[10]++;
                    break;
                case 11:
                    nums[11]++;
                    break;
                case 12:
                    nums[12]++;
                    break;
                case 13:
                    nums[13]++;
                    break;
                case 14:
                    nums[14]++;
                    break;
                case 15:
                    nums[15]++;
                    break;
                case 16:
                    nums[16]++;
                case 17:
                    nums[17]++;
                    break;
                case 18:
                    nums[18]++;
                    break;
                case 19:
                    nums[19]++;
                    break;
                case 20:
                    nums[20]++;
                    break;
                case 21:
                    nums[21]++;
                    break;
                case 22:
                    nums[22]++;
                    break;
                case 23:
                    nums[23]++;
                    break;
                case 24:
                    nums[24]++;
                    break;
                case 25:
                    nums[25]++;
                    break;
                case 26:
                    nums[26]++;
                    break;
                case 27:
                    nums[27]++;
                default:
                    break;
            }
        }
    }


    protected void showComfirDialog(final boolean is_auto) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (is_auto) {
            if (btnLearn.getText().toString().equals(getResources().getString(R.string.AutoDeal))) {
                builder.setMessage("auto Deal?");
            } else {
                builder.setMessage("autoDeal cancel?");
            }
        } else {
            String type = "FT";
            if (IS_SC) {
                type = "SC";
            }
            builder.setMessage(type + " Go?");
        }
        builder.setTitle("Tips");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                if (is_auto) {

                    if (btnLearn.getText().toString().equals(getResources().getString(R.string.AutoDeal))) {
                        initScTimer();
                        btnLearn.setText("AutoCancel");
                    } else {
                        btnLearn.setText(getResources().getString(R.string.AutoDeal));
                        cancelScAutoDeal();
                    }
                } else {
                    doEnterSearchPage(buyPositionList, buyNumList, buyDifList, etAmount.getText().toString(), false, ConstantValue.TYPE_BLANK_20);
                }
            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();

    }


    private int SC_TIMMER = 2007;
    private Timer timer;
    private TimerTask task;

    protected void initScTimer() {
        if (null == timer) {
            timer = new Timer(true);
        }

        if (null == task) {
            task = new TimerTask() {
                public void run() {
                    Message msg = new Message();
                    msg.what = SC_TIMMER;
                    myHandler.sendMessage(msg);
                }
            };

//            timer.schedule(task, 1000, 66*1000);
            timer.schedule(task, 1000, 5 * 30 * 1000);
//            timer.schedule(task, 1000, 5*30*1000);
//            timer.schedule(task, 1000, 30 * 1000);
        }
    }

    private void cancelScAutoDeal() {
        timer.cancel();
        task.cancel();
        timer = null;
        task = null;
    }

    private boolean IS_AUTO_GET = false;


    private void autoDealSc(boolean netError) {
//        Log.e(TAG, "范围内: "+Utils.isCurrentInTimeScope(13, 20, 0, 35));
//        Log.e(TAG, "范围内1: " + DateUtils.isInRange(13, 0, 23, 59));
//        Log.e(TAG, "范围内2: " + DateUtils.isInRange(0, 0, 4, 00));
//        Log.e(TAG, "autoDealSc: ");
//        etSame.setText("15");
        IS_AUTO_GET = true;

        if (!netError) {
            onClick(btnChangeLottory);
            goScOrFt();
        }
        onClick(btnGet);
    }

    private void goScOrFt() {
        if (DateUtils.isInRange(8, 50, 9, 00) || DateUtils.isInRange(12, 50, 13, 00) || DateUtils.isInRange(16, 50, 17, 00)
                || DateUtils.isInRange(20, 50, 21, 00) || DateUtils.isInRange(2, 50, 3, 00)) {
            onClick(startBtn);
        }
        if (IS_SC) {
            if (DateUtils.isInRange(8, 50, 23, 59)) {
                goSc();
            }
        } else {

            if (DateUtils.isInRange(13, 0, 23, 59) || DateUtils.isInRange(0, 0, 4, 00)) {
                goFt();
            }
        }
    }

    private void autoDealMethod() {
        if (IS_SC && !DateUtils.isInRange(9, 10, 23, 59)) {
            if (DateUtils.isInRange(0, 1, 0, 10)) {
                etDate.setText(DateUtils.getToDay());
            }
            Log.e(TAG, "scRange not");

            return;
        } else if (!IS_SC) {
            if (!DateUtils.isInRange(13, 0, 23, 59) && !DateUtils.isInRange(0, 0, 4, 10)) {
                Log.e(TAG, "ftRange not");
                return;
            }
        }
        try {
            Log.e(TAG, "CTrem: " + mList.get(0).getCTerm());
            if (IS_SC) {
                ScCurrentCTrem = mList.get(0).getCTerm();
                if (TextUtils.isEmpty(ScLastCTrem)) {
                    ScLastCTrem = mList.get(0).getCTerm();
                }
            } else {
                FtCurrentCTrem = mList.get(0).getCTerm();
                if (TextUtils.isEmpty(FtLastCTrem)) {
                    FtLastCTrem = mList.get(0).getCTerm();
                }
            }
        } catch (Exception e) {

        }

//        setCustomDealData(ConstantValue.autoCustomSame20 + "", ConstantValue.autoCustomSame15 + "", ConstantValue.autoCustomSame10 + "", true, ConstantValue.CustomCoordinate);
        ConstantUtils.setIsSc(IS_SC);
        setDealData(0, ConstantValue.autoSame20 + "", ConstantValue.autoSame15 + "", ConstantValue.autoSame10 + "", false, ConstantValue.ClassCoordinate);
//        delayDeal(ConstantValue.autoBlank20, ConstantValue.autoSame20 + ConstantUtils.getFabInt(IS_SC, false, ConstantValue.TYPE_BLANK_20), ConstantValue.TYPE_BLANK_20, 0);
//        delayDeal(ConstantValue.autoBlank15, ConstantValue.autoSame15 + ConstantUtils.getFabInt(IS_SC, false, ConstantValue.TYPE_BLANK_15), ConstantValue.TYPE_BLANK_15, 16);
        delayDeal(ConstantValue.BIGGER_INT,ConstantValue.autoBlank10, ConstantValue.autoSame10 + ConstantUtils.getFabInt(IS_SC, false, ConstantValue.TYPE_BLANK_10), ConstantValue.TYPE_BLANK_10, 2);
//        delayDeal(ConstantValue.BEGIN_INT,ConstantValue.autoBlank10, ConstantValue.autoCustomSame10 + ConstantUtils.getFabInt(IS_SC, false, ConstantValue.TYPE_BLANK_10), ConstantValue.TYPE_BLANK_10, 20);
//        setDealData(48, ConstantValue.autoCustomSame20 + "", ConstantValue.autoCustomSame15 + "", ConstantValue.autoCustomSame10 + "", true, ConstantValue.ClassCoordinate);
//        delayDeal(ConstantValue.autoBlank20, ConstantValue.autoCustomSame20 + ConstantUtils.getFabInt(IS_SC, true, ConstantValue.TYPE_BLANK_20), ConstantValue.TYPE_BLANK_20, 50);
//        delayDeal(ConstantValue.autoBlank15, ConstantValue.autoCustomSame15 + ConstantUtils.getFabInt(IS_SC, true, ConstantValue.TYPE_BLANK_15), ConstantValue.TYPE_BLANK_15, 66);
//        delayDeal(ConstantValue.autoBlank10, ConstantValue.autoCustomSame10 + ConstantUtils.getFabInt(IS_SC, true, ConstantValue.TYPE_BLANK_10), ConstantValue.TYPE_BLANK_10, 82);


//        setDealData(38);
//        delayDeal(ConstantValue.autoBlank20, ConstantValue.autoSame20, ConstantValue.TYPE_BLANK_20, 40);
//        delayDeal(ConstantValue.autoBlank15, ConstantValue.autoSame15, ConstantValue.TYPE_BLANK_15, 56);
//        delayDeal(ConstantValue.autoBlank10, ConstantValue.autoSame10, ConstantValue.TYPE_BLANK_10, 72);

    }

    private void setDealData(int time, final String same20, final String same15, final String same10, final boolean iscustom, final int coordinate) {
        UIUtils.postDelayed(new Runnable() {
            @Override
            public void run() {
                setCustomDealData(same20 + "", same15 + "", same10 + "", iscustom, coordinate);
            }
        }, time * 1000);
    }

    private void delayDeal(final int beginInt, final int blank, final int samem, final int type, final int time) {
        UIUtils.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (time == 0) {
                    checkDanger();
                    UIUtils.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if (allCount > ConstantValue.dangerCoordinnate) {
                                DANGER = true;
                            } else {
                                DANGER = false;
                            }

                            Log.e(TAG, "checkDangerAllCount: " + allCount);
                            dealType(0, blank, samem, type,beginInt);
                        }
                    }, 2000);
                } else {
                    dealType(0, blank, samem, type,beginInt);
                }
            }
        }, time * 1000);
    }

    private boolean DANGER = false;

    private void checkDanger() {


        etBlank.setText(10 + "");
        etSame.setText(ConstantValue.autoSame10 + "");
        etDif.setText("100");
        onClick(btnCaculate);
    }

    private void setCustomDealData(String num, String num2, String num3, boolean custom, int coordinate) {
        ConstantUtils.setAutoSame20(num);
        ConstantUtils.setAutoSame15(num2);
        ConstantUtils.setAutoSame10(num3);
        ConstantUtils.setAllCountCoordinate(coordinate + "");
        ConstantUtils.setCUSTOM(custom);
    }


    private void dealType(final int time, int blank, final int same, final int blanktype, int mBeginInt) {
        if (mList.size() < 1) {
            UIUtils.postDelayed(new Runnable() {
                @Override
                public void run() {
                    getDataFromNet();
                }
            }, 3000);
            return;
        }
        cTerm = mList.get(0).getCTerm();
        etBlank.setText(blank + "");
        etSame.setText(same + "");
        etDif.setText("100");
        this.beginInt = mBeginInt;
        UIUtils.postDelayed(new Runnable() {
            @Override
            public void run() {
                onClick(btnCaculate);

            }
        }, 2000 + time);
        UIUtils.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "time: " + blanktype + "-----" + "allCount:" + allCount + "-----" + "same:" + same + "beginInt:" + beginInt);
                Log.e(TAG, "checkDanger: " + DANGER);
                if (IS_SC) {
                    LogTag = "SCBuyMap";
                } else {
                    LogTag = "FTBuyMap";
                }
                if (!ConstantUtils.isCUSTOM() && blanktype == ConstantValue.TYPE_BLANK_10 && !TextUtils.isEmpty(learnResultStr2) && !TextUtils.isEmpty(learnResultStr3)) {
                    Log.e(TAG, LogTag + "---beginInt:" + beginInt + "\n" + "position: " + "\n" + learnResultStr2 + "num: " + "\n" + learnResultStr3);
//                    Log.e(TAG, "num: " + "\n" + learnResultStr3);
                }
//                if (!DANGER) {
//                    if (IS_SC) {
//                        if (allCount > ConstantValue.BeginCoordinate) {
//                            if (ConstantUtils.isCUSTOM()) {
//                                ConstantUtils.setScBiggerBigin(true,blanktype);
//                                ConstantUtils.setScBiggerFab(blanktype, cTerm);
//                            } else {
//                                ConstantUtils.setScBigin(true,blanktype);
//                                ConstantUtils.setScFab(blanktype,cTerm);
//                            }
//                        } else if (allCount < ConstantValue.EndCoordinate) {
//                            if (ConstantUtils.isCUSTOM()) {
//                                ConstantUtils.setScBigin(false, blanktype);
//                            } else {
//                                ConstantUtils.setScBiggerBigin(false, blanktype);
//                            }
//                        } else if (ConstantUtils.isScBiggerBigin(blanktype)||ConstantUtils.isScBigin(blanktype)){
//                            if (ConstantUtils.isCUSTOM()) {
//                                ConstantUtils.setScBiggerFab(blanktype, cTerm);
//                            } else {
//                                ConstantUtils.setScFab(blanktype,cTerm);
//                            }
//                        }
//                    } else {
//
//                        if (allCount > ConstantValue.BeginCoordinate) {
//
//                            if (ConstantUtils.isCUSTOM()) {
//                                ConstantUtils.setFtBiggreBigin(true,blanktype);
//                                ConstantUtils.setFtBiggerFab(blanktype, cTerm);
//                            } else {
//                                ConstantUtils.setFtBigin(true,blanktype);
//                                ConstantUtils.setFtFab(blanktype, cTerm);
//                            }
//                        } else if (allCount < ConstantValue.EndCoordinate) {
//
//                            if (ConstantUtils.isCUSTOM()) {
//                                ConstantUtils.setFtBiggreBigin(false, blanktype);
//                            } else {
//                                ConstantUtils.setFtBigin(false, blanktype);
//                            }
//                        } else if (ConstantUtils.isFtBiggreBigin(blanktype)||ConstantUtils.isFtBigin(blanktype)){
//                            if (ConstantUtils.isCUSTOM()) {
//                                ConstantUtils.setFtBiggerFab(blanktype, cTerm);
//                            } else {
//                                ConstantUtils.setFtFab(blanktype, cTerm);
//                            }
//                        }
//                    }
//                }


//                if ((!ConstantUtils.isCUSTOM()&&IS_SC&&ConstantUtils.isScBigin(blanktype))||(!ConstantUtils.isCUSTOM()&&!IS_SC&&ConstantUtils.isFtBigin(blanktype))
//                        ||(ConstantUtils.isCUSTOM()&&IS_SC&&ConstantUtils.isScBiggerBigin(blanktype))||(ConstantUtils.isCUSTOM()&&!IS_SC&&ConstantUtils.isFtBiggreBigin(blanktype))) {

                UIUtils.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        doEnterSearchPage(buyPositionList, buyNumList, buyDifList, etAmount.getText().toString(), true, blanktype);
                    }
                }, 5000 + time);
//                }
            }
        }, 5000 + time);
    }


    private String[] sortList(ArrayList<String> sameList, int[] arr) {
        String[] strArr = new String[sameList.size()];
        for (int i = 0; i < sameList.size(); i++) {
            strArr[i] = sameList.get(i);
        }

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    String str = strArr[j];
                    strArr[j] = strArr[j + 1];
                    strArr[j + 1] = str;
                }
            }
        }
        return strArr;
    }


    private void resetDealData() {
        ConstantUtils.setAutoSame10(etBlank10.getText().toString());
        ConstantUtils.setAutoSame15(etBlank15.getText().toString());
        ConstantUtils.setAutoSame20(etBlank20.getText().toString());
        ConstantUtils.setAllCountCoordinate(etAllcount.getText().toString());
        ConstantUtils.setOriginAmount(etAmount.getText().toString());

        if (btnCustom.getText().toString().equals(getResources().getString(R.string.custom))) {
            ConstantUtils.setCUSTOM(true);
        } else {
            ConstantUtils.setCUSTOM(false);
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

}

package com.cmsc.cmmusic.common.demo;

import java.io.File;
import java.net.URLEncoder;
import java.util.Hashtable;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.cmsc.cmmusic.common.CMMusicCallback;
import com.cmsc.cmmusic.common.CPManagerInterface;
import com.cmsc.cmmusic.common.ExclusiveManagerInterface;
import com.cmsc.cmmusic.common.FullSongManagerInterface;
import com.cmsc.cmmusic.common.Logger;
import com.cmsc.cmmusic.common.MusicQueryInterface;
import com.cmsc.cmmusic.common.MvManagerInterface;
import com.cmsc.cmmusic.common.OnlineListenerMusicInterface;
import com.cmsc.cmmusic.common.PaymentManagerInterface;
import com.cmsc.cmmusic.common.RingbackManagerInterface;
import com.cmsc.cmmusic.common.SongRecommendManagerInterface;
import com.cmsc.cmmusic.common.UserManagerInterface;
import com.cmsc.cmmusic.common.VibrateRingManagerInterface;
import com.cmsc.cmmusic.common.data.AccountResult;
import com.cmsc.cmmusic.common.data.AlbumListRsp;
import com.cmsc.cmmusic.common.data.ChartListRsp;
import com.cmsc.cmmusic.common.data.CrbtListRsp;
import com.cmsc.cmmusic.common.data.CrbtOpenCheckRsp;
import com.cmsc.cmmusic.common.data.CrbtPrelistenRsp;
import com.cmsc.cmmusic.common.data.DownloadResult;
import com.cmsc.cmmusic.common.data.LoginResult;
import com.cmsc.cmmusic.common.data.MVMonthPolicy;
import com.cmsc.cmmusic.common.data.MusicInfoResult;
import com.cmsc.cmmusic.common.data.MusicListRsp;
import com.cmsc.cmmusic.common.data.OwnRingRsp;
import com.cmsc.cmmusic.common.data.PayPolicy;
import com.cmsc.cmmusic.common.data.PaymentResult;
import com.cmsc.cmmusic.common.data.QueryResult;
import com.cmsc.cmmusic.common.data.RechargeResult;
import com.cmsc.cmmusic.common.data.RegistResult;
import com.cmsc.cmmusic.common.data.RegistRsp;
import com.cmsc.cmmusic.common.data.Result;
import com.cmsc.cmmusic.common.data.SingerInfoRsp;
import com.cmsc.cmmusic.common.data.SongRecommendResult;
import com.cmsc.cmmusic.common.data.StreamRsp;
import com.cmsc.cmmusic.common.data.TagListRsp;
import com.cmsc.cmmusic.common.data.TransferResult;
import com.cmsc.cmmusic.common.data.UserResult;
import com.cmsc.cmmusic.init.InitCmmInterface;
import com.cmsc.cmmusic.init.SmsLoginInfoRsp;

public class CMMusicDemo extends Activity implements OnClickListener {
    private final static String LOG_TAG = "CMMusicDemo";

    private ProgressDialog dialog;

    private long requestTime;

    private UIHandler mUIHandler = new UIHandler();

    private class UIHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            long responseTime = System.currentTimeMillis() - requestTime;

            switch (msg.what) {
            case 0:
                if (msg.obj == null) {
                    hideProgressBar();
                    Toast.makeText(CMMusicDemo.this, "结果 = null", Toast.LENGTH_SHORT).show();
                    return;
                }
                new AlertDialog.Builder(CMMusicDemo.this).setTitle("结果").setMessage((msg.obj).toString())
                        .setPositiveButton("确认", null).show();
                break;
            }
            hideProgressBar();
            if (null != dialog) {
                dialog.dismiss();
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cmmusic_demo);

        InitCmmInterface.initSDK(this);

        final TextView logText = (TextView) this.findViewById(R.id.logText);
        Button getLogButton = (Button) this.findViewById(R.id.get_log);
        getLogButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                logText.setText(Logger.getPreferencesLog(CMMusicDemo.this));
            }
        });

        Button cleanLogButton = (Button) this.findViewById(R.id.clean_log);
        cleanLogButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.cleanPreferencesLog(CMMusicDemo.this);
                logText.setText("");
            }
        });

        Button initButton = (Button) this.findViewById(R.id.initButton);
        initButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // if (!InitCmmInterface.initCheck(CMMusicDemo.this)) {
                dialog = ProgressDialog.show(CMMusicDemo.this, null, "请稍候……", true, false);
                requestTime = System.currentTimeMillis();
                new Thread(new T1()).start();
                // } else {
                // new
                // AlertDialog.Builder(CMMusicDemo.this).setTitle("init").setMessage("已初始化过")
                // .setPositiveButton("确认", null).show();
                // }
            }
        });

        // CP专属包月
        Button cpButton = (Button) this.findViewById(R.id.cp);
        cpButton.setOnClickListener(new OnClickListener() {
            String[] strs = new String[] { "CP按次全曲下载", "CP按次振铃下载", "CP专属全曲下载", "CP专属振铃下载", "查询CP专属包月订购关系", "CP专属包月订购",
                    "CP专属包月退订" };

            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(CMMusicDemo.this).setTitle("CP专属包月类")
                        .setItems(strs, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                case 0:
                                    showParameterDialog("musicId", new ParameterCallback() {
                                        @Override
                                        public void callback(final String musicId) {
                                            Log.i("TAG", "musicId = " + musicId);
                                            CPManagerInterface.getCPFullSongTimeDownloadUrl(CMMusicDemo.this, musicId,
                                                    new CMMusicCallback<DownloadResult>() {
                                                        @Override
                                                        public void operationResult(DownloadResult downloadResult) {
                                                            if (null != downloadResult) {
                                                                new AlertDialog.Builder(CMMusicDemo.this)
                                                                        .setTitle("getCPFullSongDownloadUrlByNet")
                                                                        .setMessage(downloadResult.toString())
                                                                        .setPositiveButton("确认", null).show();
                                                            }

                                                            Log.d(LOG_TAG, "vRing Download result is " + downloadResult);
                                                        }
                                                    });
                                        }
                                    });
                                    break;
                                case 1:
                                    showParameterDialog("musicId", new ParameterCallback() {
                                        @Override
                                        public void callback(final String musicId) {
                                            Log.i("TAG", "musicId = " + musicId);
                                            CPManagerInterface.queryCPVibrateRingTimeDownloadUrl(CMMusicDemo.this,
                                                    musicId, new CMMusicCallback<DownloadResult>() {
                                                        @Override
                                                        public void operationResult(DownloadResult downloadResult) {
                                                            if (null != downloadResult) {
                                                                new AlertDialog.Builder(CMMusicDemo.this)
                                                                        .setTitle("queryCPVibrateRingDownloadUrl")
                                                                        .setMessage(downloadResult.toString())
                                                                        .setPositiveButton("确认", null).show();
                                                            }

                                                            Log.d(LOG_TAG, "vRing Download result is " + downloadResult);
                                                        }
                                                    });
                                        }
                                    });
                                    break;

                                case 2:
                                    showParameterDialog(new String[] { "serviceId", "musicId", "codeRate" },
                                            new ParameterCallbacks() {
                                                @Override
                                                public void callback(final String[] parameters) {
                                                    Log.i("TAG", "parameters = " + parameters);
                                                    // showProgressBar("数据加载中...");
                                                    CPManagerInterface.queryCPFullSongDownloadUrl(CMMusicDemo.this,
                                                            parameters[0], parameters[1], parameters[2],
                                                            new CMMusicCallback<DownloadResult>() {

                                                                @Override
                                                                public void operationResult(DownloadResult ret) {
                                                                    if (null != ret) {
                                                                        new AlertDialog.Builder(CMMusicDemo.this)
                                                                                .setTitle("queryCPFullSongDownloadUrl")
                                                                                .setMessage(ret.toString())
                                                                                .setPositiveButton("确认", null).show();
                                                                    }
                                                                }

                                                            });
                                                }
                                            });

                                    break;

                                case 3:
                                    showParameterDialog(new String[] { "serviceId", "musicId", "codeRate" },
                                            new ParameterCallbacks() {
                                                @Override
                                                public void callback(final String[] parameters) {
                                                    Log.i("TAG", "parameters = " + parameters);
                                                    // showProgressBar("数据加载中...");

                                                    // DownloadResult t
                                                    // = null;
                                                    // t =
                                                    CPManagerInterface.queryCPVibrateRingDownloadUrl(CMMusicDemo.this,
                                                            parameters[0], parameters[1], parameters[2],
                                                            new CMMusicCallback<DownloadResult>() {

                                                                @Override
                                                                public void operationResult(DownloadResult ret) {
                                                                    if (null != ret) {
                                                                        new AlertDialog.Builder(CMMusicDemo.this)
                                                                                .setTitle(
                                                                                        "queryCPVibrateRingDownloadUrl")
                                                                                .setMessage(ret.toString())
                                                                                .setPositiveButton("确认", null).show();
                                                                        // mUIHandler
                                                                        // .obtainMessage(
                                                                        // 0,
                                                                        // ret)
                                                                        // .sendToTarget();
                                                                    }
                                                                }

                                                            });
                                                }
                                            });
                                    break;

                                case 4:
                                    showParameterDialog("serviceId", new ParameterCallback() {
                                        @Override
                                        public void callback(final String serviceId) {
                                            Log.i("TAG", "serviceId = " + serviceId);
                                            showProgressBar("数据加载中...");
                                            new Thread() {
                                                @Override
                                                public void run() {
                                                    QueryResult t = CPManagerInterface.queryCPMonth(CMMusicDemo.this,
                                                            serviceId);
                                                    mUIHandler.obtainMessage(0, t).sendToTarget();
                                                }
                                            }.start();
                                        }
                                    });
                                    break;

                                case 5:
                                    showParameterDialog(new String[] { "serviceId", "definedseq" },
                                            new ParameterCallbacks() {
                                                @Override
                                                public void callback(final String[] parameters) {
                                                    Log.i("TAG", "serviceId = " + parameters[0]);
                                                    CPManagerInterface.openCPMonth(CMMusicDemo.this, parameters[0],
                                                            parameters[1], new CMMusicCallback<Result>() {
                                                                @Override
                                                                public void operationResult(Result result) {
                                                                    if (null != result) {
                                                                        new AlertDialog.Builder(CMMusicDemo.this)
                                                                                .setTitle("openCpMonth")
                                                                                .setMessage(result.toString())
                                                                                .setPositiveButton("确认", null).show();
                                                                    }

                                                                    Log.d(LOG_TAG, "ret is " + result);
                                                                }
                                                            });
                                                }
                                            });
                                    break;

                                case 6:
                                    showParameterDialog("serviceId", new ParameterCallback() {
                                        @Override
                                        public void callback(final String serviceId) {
                                            Log.i("TAG", "serviceId = " + serviceId);
                                            showProgressBar("数据加载中...");
                                            new Thread() {
                                                @Override
                                                public void run() {
                                                    Result t = CPManagerInterface.cancelCPMonth(CMMusicDemo.this,
                                                            serviceId);
                                                    mUIHandler.obtainMessage(0, t).sendToTarget();
                                                }
                                            }.start();
                                        }
                                    });
                                    break;
                                }
                            }
                        }).create().show();
            }
        });

        // MV
        Button mvButton = (Button) this.findViewById(R.id.mvDownload);
        mvButton.setOnClickListener(new OnClickListener() {
            String[] strs = new String[] { "MV包月开通", "MV点播", "MV包月退订", "MV包月关系查询" };

            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(CMMusicDemo.this).setTitle("MV类")
                        .setItems(strs, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                case 0:
                                    showParameterDialog(new String[] { "serviceId", "serviceId" },
                                            new ParameterCallbacks() {
                                                @Override
                                                public void callback(final String[] parameters) {
                                                    Log.i("TAG", "parameters = " + parameters);
                                                    MvManagerInterface.openMvMonth(CMMusicDemo.this, parameters,
                                                            new CMMusicCallback<Result>() {
                                                                @Override
                                                                public void operationResult(Result result) {
                                                                    if (null != result) {
                                                                        new AlertDialog.Builder(CMMusicDemo.this)
                                                                                .setTitle("openMvMonthByNet")
                                                                                .setMessage(result.toString())
                                                                                .setPositiveButton("确认", null).show();
                                                                    }
                                                                }
                                                            });
                                                }
                                            });
                                    break;
                                case 1:
                                    showParameterDialog("mvId", new ParameterCallback() {
                                        @Override
                                        public void callback(final String parameter) {
                                            Log.i("TAG", "parameter = " + parameter);
                                            MvManagerInterface.mvDownload(CMMusicDemo.this, parameter,
                                                    new CMMusicCallback<DownloadResult>() {
                                                        @Override
                                                        public void operationResult(DownloadResult result) {
                                                            if (null != result) {
                                                                new AlertDialog.Builder(CMMusicDemo.this)
                                                                        .setTitle("mvDownloadByNet")
                                                                        .setMessage(result.toString())
                                                                        .setPositiveButton("确认", null).show();
                                                            }
                                                        }
                                                    });
                                        }
                                    });
                                    break;

                                case 2:
                                    showParameterDialog("serviceId", new ParameterCallback() {
                                        @Override
                                        public void callback(final String parameter) {
                                            Log.i("TAG", "parameters = " + parameter);
                                            showProgressBar("数据加载中...");
                                            new Thread() {
                                                @Override
                                                public void run() {
                                                    Result t = MvManagerInterface.cancelMvMonth(CMMusicDemo.this,
                                                            parameter);
                                                    mUIHandler.obtainMessage(0, t).sendToTarget();
                                                }
                                            }.start();
                                        }
                                    });

                                    break;

                                case 3:
                                    showParameterDialog(new String[] { "serviceId", "serviceId" },
                                            new ParameterCallbacks() {
                                                @Override
                                                public void callback(final String[] parameters) {
                                                    Log.i("TAG", "parameters = " + parameters);
                                                    showProgressBar("数据加载中...");
                                                    new Thread() {
                                                        @Override
                                                        public void run() {
                                                            MVMonthPolicy t = MvManagerInterface.getMvMonthQuery(
                                                                    CMMusicDemo.this, parameters);
                                                            mUIHandler.obtainMessage(0, t).sendToTarget();
                                                        }
                                                    }.start();
                                                }
                                            });
                                    break;
                                }
                            }
                        }).create().show();
            }
        });

        // 个性化彩铃
        Button ownRingback = (Button) this.findViewById(R.id.ownRingback);
        ownRingback.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                RingbackManagerInterface.buyOwnRingback(CMMusicDemo.this, new CMMusicCallback<Result>() {
                    @Override
                    public void operationResult(Result downloadResult) {
                        if (null != downloadResult) {
                            new AlertDialog.Builder(CMMusicDemo.this).setTitle("buyOwnRingbackByNet")
                                    .setMessage(downloadResult.toString()).setPositiveButton("确认", null).show();
                        }

                        Log.d(LOG_TAG, "vRing Download result is " + downloadResult);
                    }
                });
            }
        });

        // 个性化彩铃包月
        Button ownRingMonthBtn = (Button) this.findViewById(R.id.own_ring_month);
        ownRingMonthBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                RingbackManagerInterface.ownRingMonth(CMMusicDemo.this, new CMMusicCallback<Result>() {

                    @Override
                    public void operationResult(Result result) {

                        if (null != result) {
                            new AlertDialog.Builder(CMMusicDemo.this).setTitle("OwnRingMonth")
                                    .setMessage(result.toString()).setPositiveButton("确认", null).show();
                        }

                        Log.i(LOG_TAG, "OwnRingMonth is " + result);
                    }
                });
            }
        });

        // 非彩铃用户一键订购个性化彩铃包月
        Button keyOrderOwnRingMonthBtn = (Button) this.findViewById(R.id.key_order_own_ring_month);
        keyOrderOwnRingMonthBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                RingbackManagerInterface.keyOrderOwnRingMonth(CMMusicDemo.this, new CMMusicCallback<Result>() {

                    @Override
                    public void operationResult(Result result) {

                        if (null != result) {
                            new AlertDialog.Builder(CMMusicDemo.this).setTitle("keyOrderOwnRingMonth")
                                    .setMessage(result.toString()).setPositiveButton("确认", null).show();
                        }

                        Log.i(LOG_TAG, "keyOrderOwnRingMonth is " + result);
                    }
                });
            }
        });

        // 第三方支付系列接口
        Button payment = (Button) this.findViewById(R.id.payment);
        payment.setOnClickListener(new OnClickListener() {
            String[] strs = new String[] { "检查用户是否已注册", "查询用户信息", "修改用户信息", "用户余额查询", "用户消费记录查询", "用户转账记录查询",
                    "获取支付商列表", "用户账户充值", "包月业务订购", "查询业务策略", "全曲下载", "振铃下载", "转账" };

            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(CMMusicDemo.this).setTitle("第三方支付系列接口")
                        .setItems(strs, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                case 0:
                                    // 检查用户是否已注册
                                    showParameterDialog("accountName", new ParameterCallback() {
                                        @Override
                                        public void callback(final String accountName) {
                                            Log.w("TAG", "accountName = " + accountName);
                                            showProgressBar("数据加载中...");
                                            new Thread() {
                                                @Override
                                                public void run() {
                                                    RegistRsp r = PaymentManagerInterface.checkMember(CMMusicDemo.this,
                                                            accountName);

                                                    mUIHandler.obtainMessage(0, r).sendToTarget();
                                                }
                                            }.start();
                                        }
                                    });

                                    break;
                                case 1:
                                    // 查询用户信息
                                    showParameterDialog(new String[] { "UID", "accountName" },
                                            new ParameterCallbacks() {
                                                @Override
                                                public void callback(final String[] parameters) {
                                                    Log.i("TAG", "parameters = " + parameters);
                                                    showProgressBar("数据加载中...");
                                                    new Thread() {
                                                        @Override
                                                        public void run() {
                                                            UserResult t = PaymentManagerInterface.queryMember(
                                                                    CMMusicDemo.this, parameters[0], parameters[1]);

                                                            mUIHandler.obtainMessage(0, t).sendToTarget();
                                                        }
                                                    }.start();
                                                }
                                            });
                                    break;
                                case 2:
                                    // 修改用户信息
                                    showParameterDialog("UID", new ParameterCallback() {
                                        @Override
                                        public void callback(final String UID) {
                                            Log.w("TAG", "UID = " + UID);
                                            showProgressBar("数据加载中...");
                                            new Thread() {
                                                @Override
                                                public void run() {
                                                    UserResult r = PaymentManagerInterface.updateMember(
                                                            CMMusicDemo.this, UID, null);

                                                    mUIHandler.obtainMessage(0, r).sendToTarget();
                                                }
                                            }.start();
                                        }
                                    });
                                    break;
                                case 3:
                                    // 用户余额查询
                                    showParameterDialog(new String[] { "UID", "type" }, new ParameterCallbacks() {
                                        @Override
                                        public void callback(final String[] parameters) {
                                            Log.i("TAG", "parameters = " + parameters);
                                            showProgressBar("数据加载中...");
                                            new Thread() {
                                                @Override
                                                public void run() {
                                                    AccountResult t = PaymentManagerInterface.queryAccount(
                                                            CMMusicDemo.this, parameters[0], parameters[1]);

                                                    mUIHandler.obtainMessage(0, t).sendToTarget();
                                                }
                                            }.start();
                                        }
                                    });
                                    break;
                                case 4:
                                    // 用户消费记录查询
                                    showParameterDialog(new String[] { "UID", "type", "tradeNum", "startTime",
                                            "endTime" }, new ParameterCallbacks() {
                                        @Override
                                        public void callback(final String[] parameters) {
                                            Log.i("TAG", "parameters = " + parameters);
                                            showProgressBar("数据加载中...");
                                            new Thread() {
                                                @Override
                                                public void run() {
                                                    PaymentResult t = PaymentManagerInterface.getPaymentResult(
                                                            CMMusicDemo.this, parameters[0], parameters[1],
                                                            parameters[2], parameters[3], parameters[4]);

                                                    mUIHandler.obtainMessage(0, t).sendToTarget();
                                                }
                                            }.start();
                                        }
                                    });
                                    break;
                                case 5:
                                    // 用户转账记录查询
                                    showParameterDialog(new String[] { "UID", "type", "startTime", "endTime" },
                                            new ParameterCallbacks() {
                                                @Override
                                                public void callback(final String[] parameters) {
                                                    Log.i("TAG", "parameters = " + parameters);
                                                    showProgressBar("数据加载中...");
                                                    new Thread() {
                                                        @Override
                                                        public void run() {
                                                            PaymentResult t = PaymentManagerInterface
                                                                    .getTransferResult(CMMusicDemo.this, parameters[0],
                                                                            parameters[1], parameters[2], parameters[3]);

                                                            mUIHandler.obtainMessage(0, t).sendToTarget();
                                                        }
                                                    }.start();
                                                }
                                            });
                                    break;
                                case 6:
                                    // 获取支付商列表
                                    showProgressBar("数据加载中...");
                                    new Thread() {
                                        @Override
                                        public void run() {
                                            String t = PaymentManagerInterface.getPayments(CMMusicDemo.this);

                                            mUIHandler.obtainMessage(0, t).sendToTarget();
                                        }
                                    }.start();
                                    break;
                                case 7:
                                    // 用户账户充值
                                    showParameterDialog(new String[] { "UID", "type", "amount" },
                                            new ParameterCallbacks() {
                                                @Override
                                                public void callback(final String[] parameters) {
                                                    Log.i("TAG", "parameters = " + parameters);
                                                    showProgressBar("数据加载中...");
                                                    new Thread() {
                                                        @Override
                                                        public void run() {
                                                            RechargeResult t = PaymentManagerInterface
                                                                    .getRechargeResult(CMMusicDemo.this, parameters[0],
                                                                            parameters[1], parameters[2]);

                                                            mUIHandler.obtainMessage(0, t).sendToTarget();
                                                        }
                                                    }.start();
                                                }
                                            });
                                    break;
                                case 8:
                                    // 包月业务订购
                                    showParameterDialog(new String[] { "UID", "type", "amount" },
                                            new ParameterCallbacks() {
                                                @Override
                                                public void callback(final String[] parameters) {
                                                    Log.i("TAG", "parameters = " + parameters);
                                                    showProgressBar("数据加载中...");
                                                    new Thread() {
                                                        @Override
                                                        public void run() {
                                                            Result t = PaymentManagerInterface.getOrderOpenResult(
                                                                    CMMusicDemo.this, parameters[0], parameters[1],
                                                                    parameters[2], new CMMusicCallback<Result>() {
                                                                        @Override
                                                                        public void operationResult(Result result) {
                                                                            if (null != result) {
                                                                                new AlertDialog.Builder(
                                                                                        CMMusicDemo.this)
                                                                                        .setTitle("getOrderOpenResult")
                                                                                        .setMessage(result.toString())
                                                                                        .setPositiveButton("确认", null)
                                                                                        .show();
                                                                            }

                                                                            Log.d(LOG_TAG, "ret is " + result);
                                                                        }
                                                                    });

                                                            mUIHandler.obtainMessage(0, t).sendToTarget();
                                                        }
                                                    }.start();
                                                }
                                            });
                                    break;
                                case 9:
                                    // 查询业务策略
                                    showParameterDialog(new String[] { "UID", "musicId" }, new ParameterCallbacks() {
                                        @Override
                                        public void callback(final String[] parameters) {
                                            Log.i("TAG", "parameters = " + parameters);
                                            showProgressBar("数据加载中...");
                                            new Thread() {
                                                @Override
                                                public void run() {
                                                    PayPolicy t = PaymentManagerInterface.getPayPolicy(
                                                            CMMusicDemo.this, parameters[0], parameters[1]);

                                                    mUIHandler.obtainMessage(0, t).sendToTarget();
                                                }
                                            }.start();
                                        }
                                    });
                                    break;
                                case 10:
                                    // 全曲下载
                                    showParameterDialog(new String[] { "UID", "musicId" }, new ParameterCallbacks() {
                                        @Override
                                        public void callback(final String[] parameters) {
                                            Log.i("TAG", "parameters = " + parameters);
                                            showProgressBar("数据加载中...");

                                            // DownloadRsp t =
                                            // null;
                                            PaymentManagerInterface.getFullSongDownload(CMMusicDemo.this,
                                                    parameters[0], parameters[1], "600907002000068284",
                                                    new CMMusicCallback<DownloadResult>() {

                                                        @Override
                                                        public void operationResult(DownloadResult ret) {
                                                            if (null != ret) {
                                                                new AlertDialog.Builder(CMMusicDemo.this)
                                                                        .setTitle("getFullSongDownload")
                                                                        .setMessage(ret.toString())
                                                                        .setPositiveButton("确认", null).show();

                                                                mUIHandler.obtainMessage(0, ret).sendToTarget();
                                                            }

                                                            Log.d(LOG_TAG, "ret is " + ret);
                                                        }

                                                    });

                                        }
                                    });
                                    break;
                                case 11:
                                    // 振铃下载
                                    showParameterDialog(new String[] { "UID", "musicId" }, new ParameterCallbacks() {
                                        @Override
                                        public void callback(final String[] parameters) {
                                            Log.i("TAG", "parameters = " + parameters);
                                            showProgressBar("数据加载中...");

                                            // DownloadRsp t =
                                            // null;
                                            PaymentManagerInterface.getVibrateDownload(CMMusicDemo.this, parameters[0],
                                                    parameters[1], "600902000004940315",
                                                    new CMMusicCallback<DownloadResult>() {

                                                        @Override
                                                        public void operationResult(DownloadResult ret) {
                                                            if (null != ret) {
                                                                new AlertDialog.Builder(CMMusicDemo.this)
                                                                        .setTitle("getVibrateDownload")
                                                                        .setMessage(ret.toString())
                                                                        .setPositiveButton("确认", null).show();
                                                                mUIHandler.obtainMessage(0, ret).sendToTarget();
                                                            }

                                                            Log.d(LOG_TAG, "ret is " + ret);
                                                        }

                                                    });
                                        }
                                    });
                                    break;

                                case 12:
                                    showParameterDialog("UID", new ParameterCallback() {
                                        @Override
                                        public void callback(final String UID) {
                                            Log.i("TAG", "UID = " + UID);
                                            PaymentManagerInterface.transfer(CMMusicDemo.this, false, UID,
                                                    new CMMusicCallback<Result>() {
                                                        @Override
                                                        public void operationResult(Result result) {
                                                            if (null != result) {
                                                                new AlertDialog.Builder(CMMusicDemo.this)
                                                                        .setTitle("regist")
                                                                        .setMessage(
                                                                                ((TransferResult) result).toString())
                                                                        .setPositiveButton("确认", null).show();
                                                            }

                                                            Log.d(LOG_TAG, "ret is " + result);
                                                        }
                                                    });
                                        }
                                    });

                                    break;
                                }

                                // hideProgressBar();
                            }
                        }).create().show();
            }
        });

        // 登录
        Button loginOrder = (Button) this.findViewById(R.id.loginOrder);
        loginOrder.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                PaymentManagerInterface.login(CMMusicDemo.this, false, new CMMusicCallback<Result>() {
                    @Override
                    public void operationResult(Result result) {
                        if (null != result) {
                            new AlertDialog.Builder(CMMusicDemo.this).setTitle("regist")
                                    .setMessage(((LoginResult) result).toString()).setPositiveButton("确认", null).show();
                        }

                        Log.d(LOG_TAG, "ret is " + result);
                    }
                });
            }
        });

        // 注册
        Button regist = (Button) this.findViewById(R.id.regist);
        regist.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                PaymentManagerInterface.regist(CMMusicDemo.this, false, new CMMusicCallback<RegistResult>() {
                    @Override
                    public void operationResult(RegistResult result) {
                        if (null != result) {
                            new AlertDialog.Builder(CMMusicDemo.this).setTitle("regist")
                                    .setMessage((result).toString()).setPositiveButton("确认", null).show();
                        }

                        Log.d(LOG_TAG, "ret is " + result);
                    }
                });
            }
        });

        // 赠送全曲
        Button giveFullSongByNet = (Button) this.findViewById(R.id.giveFullSongByNet);
        giveFullSongByNet.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showParameterDialog(new String[] { "receivemdn", "musicId" }, new ParameterCallbacks() {
                    @Override
                    public void callback(final String[] parameters) {
                        Log.i("TAG", "parameters = " + parameters);
                        showProgressBar("数据加载中...");

                        FullSongManagerInterface.giveFullSong(CMMusicDemo.this, parameters[0], parameters[1],
                                new CMMusicCallback<Result>() {
                                    @Override
                                    public void operationResult(Result ret) {
                                        mUIHandler.obtainMessage(0, ret).sendToTarget();
                                    }
                                });

                    }
                });
            }
        });

        // 赠送振铃
        Button giveVibrateRingByNet = (Button) this.findViewById(R.id.giveVibrateRingByNet);
        giveVibrateRingByNet.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showParameterDialog(new String[] { "receivemdn", "musicId" }, new ParameterCallbacks() {
                    @Override
                    public void callback(final String[] parameters) {
                        Log.i("TAG", "parameters = " + parameters);
                        showProgressBar("数据加载中...");

                        VibrateRingManagerInterface.giveVibrateRing(CMMusicDemo.this, parameters[0], parameters[1],
                                new CMMusicCallback<Result>() {

                                    @Override
                                    public void operationResult(Result ret) {
                                        mUIHandler.obtainMessage(0, ret).sendToTarget();
                                    }
                                });

                    }
                });
            }
        });

        // 短信验证码登录
        Button login = (Button) this.findViewById(R.id.login);
        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("LOG_KeyOrder2", "-------1");
                UserManagerInterface.smsAuthLogin(CMMusicDemo.this, new CMMusicCallback<Result>() {
                    @Override
                    public void operationResult(Result result) {
                        if (null != result) {
                            new AlertDialog.Builder(CMMusicDemo.this).setTitle("login").setMessage(result.toString())
                                    .setPositiveButton("确认", null).show();
                        }

                        Log.d(LOG_TAG, "ret is " + result);
                    }
                });
            }
        });

        // 索要彩铃
        Button crbtAskfor = (Button) this.findViewById(R.id.crbtAskfor);
        crbtAskfor.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showParameterDialog(new String[] { "receivemdn", "musicId", "validCode" }, new ParameterCallbacks() {
                    @Override
                    public void callback(final String[] parameters) {
                        Log.i("TAG", "parameters = " + parameters);
                        showProgressBar("数据加载中...");
                        new Thread() {
                            @Override
                            public void run() {
                                Result t = RingbackManagerInterface.getCrbtAskFor(CMMusicDemo.this, parameters[0],
                                        parameters[1], parameters[2]);
                                mUIHandler.obtainMessage(0, t).sendToTarget();
                            }
                        }.start();
                    }
                });
            }
        });

        // 短信登录验证
        Button smsAuthLoginValidate = (Button) this.findViewById(R.id.smsAuthLoginValidate);
        smsAuthLoginValidate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressBar("数据加载中...");
                new Thread() {
                    @Override
                    public void run() {
                        super.run();

                        SmsLoginInfoRsp result = UserManagerInterface.smsAuthLoginValidate(CMMusicDemo.this);

                        mUIHandler.obtainMessage(0, result).sendToTarget();
                    }
                }.start();
            }
        });

        // 根据手机号查询是否开通彩铃
        Button crbtOpenCheck = (Button) this.findViewById(R.id.crbtOpenCheck);
        crbtOpenCheck.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showParameterDialog("手机号", new ParameterCallback() {
                    @Override
                    public void callback(final String phoneNum) {
                        Log.i("TAG", "phoneNum = " + phoneNum);
                        showProgressBar("数据加载中...");
                        new Thread() {
                            @Override
                            public void run() {
                                super.run();

                                CrbtOpenCheckRsp result = RingbackManagerInterface.crbtOpenCheck(CMMusicDemo.this,
                                        phoneNum);

                                mUIHandler.obtainMessage(0, result).sendToTarget();

                            }
                        }.start();
                    }
                });
            }
        });

        // 查询歌手信息
        // Button singerInfo = (Button) this.findViewById(R.id.singerInfo);
        // singerInfo.setOnClickListener(new OnClickListener() {
        // @Override
        // public void onClick(View v) {
        // showParameterDialog("歌手ID", new ParameterCallback() {
        // @Override
        // public void callback(final String singerId) {
        // Log.i("TAG", "singerId = " + singerId);
        // showProgressBar("数据加载中...");
        // new Thread() {
        // @Override
        // public void run() {
        // super.run();
        //
        // SingerInfoRsp result = MusicQueryInterface
        // .getSingerInfo(CMMusicDemo.this,
        // singerId);
        //
        // mUIHandler.obtainMessage(0, result)
        // .sendToTarget();
        //
        // }
        // }.start();
        // }
        // });
        // }
        // });

        // 歌曲ID查询专辑信息
        // Button albumListbymusic = (Button) this
        // .findViewById(R.id.albumListbymusic);
        // albumListbymusic.setOnClickListener(new OnClickListener() {
        // @Override
        // public void onClick(View v) {
        // showParameterDialog("歌曲ID", new ParameterCallback() {
        // @Override
        // public void callback(final String musicId) {
        // Log.i("TAG", "musicId = " + musicId);
        // showProgressBar("数据加载中...");
        // new Thread() {
        // @Override
        // public void run() {
        // super.run();
        //
        // AlbumListRsp result = MusicQueryInterface
        // .getAlbumsByMusicId(CMMusicDemo.this,
        // musicId, 1, 5);
        //
        // mUIHandler.obtainMessage(0, result)
        // .sendToTarget();
        //
        // }
        // }.start();
        // }
        // });
        // }
        // });

        // 歌曲ID查询歌曲信息
        // Button musicQuerybymusic = (Button) this
        // .findViewById(R.id.musicQuerybymusic);
        // musicQuerybymusic.setOnClickListener(new OnClickListener() {
        // @Override
        // public void onClick(View v) {
        // showParameterDialog("歌曲ID", new ParameterCallback() {
        //
        // @Override
        // public void callback(final String musicId) {
        // Log.i("TAG", "musicId = " + musicId);
        // showProgressBar("数据加载中...");
        // new Thread() {
        // @Override
        // public void run() {
        // super.run();
        //
        // MusicInfoResult result = MusicQueryInterface
        // .getMusicInfoByMusicId(
        // CMMusicDemo.this, musicId);
        //
        // mUIHandler.obtainMessage(0, result)
        // .sendToTarget();
        //
        // }
        // }.start();
        // }
        // });
        // }
        // });

        Button btnDel = (Button) this.findViewById(R.id.deletesong);
        btnDel.setOnClickListener(this);

        Button btnFull = (Button) this.findViewById(R.id.fullsong);
        btnFull.setOnClickListener(this);

        // 赠送彩铃
        Button giveRingback = (Button) this.findViewById(R.id.giveRingback);
        giveRingback.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showParameterDialog("musicId", new ParameterCallback() {
                    @Override
                    public void callback(final String musicId) {
                        Log.i("TAG", "musicId = " + musicId);
                        RingbackManagerInterface.giveRingBack(CMMusicDemo.this, musicId, new CMMusicCallback<Result>() {
                            @Override
                            public void operationResult(Result result) {
                                if (null != result) {
                                    new AlertDialog.Builder(CMMusicDemo.this).setTitle("giveRingBack")
                                            .setMessage(result.toString()).setPositiveButton("确认", null).show();
                                }

                                Log.d(LOG_TAG, "ret is " + result);
                            }
                        });
                    }
                });
            }
        });

        // 开通彩铃
        Button openRingback = (Button) this.findViewById(R.id.openRingback);
        openRingback.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                RingbackManagerInterface.openRingback(CMMusicDemo.this, new CMMusicCallback<Result>() {
                    @Override
                    public void operationResult(Result result) {
                        if (null != result) {
                            new AlertDialog.Builder(CMMusicDemo.this).setTitle("openRingback")
                                    .setMessage(result.toString()).setPositiveButton("确认", null).show();
                        }

                        Log.d(LOG_TAG, "ret is " + result);
                    }
                });
            }
        });

        // 开通彩铃（无界面）
        // Button openRingbackByC = (Button) this
        // .findViewById(R.id.openRingbackByC);
        // openRingbackByC.setOnClickListener(new OnClickListener() {
        // @Override
        // public void onClick(View v) {
        // new Thread() {
        // @Override
        // public void run() {
        // Result t = RingbackManagerInterface
        // .openRingback(CMMusicDemo.this);
        //
        // mUIHandler.obtainMessage(0, t).sendToTarget();
        // }
        // }.start();
        // }
        // });

        // 歌曲查詢类
        Button musicQuery = (Button) this.findViewById(R.id.musicQuery);
        musicQuery.setOnClickListener(new OnClickListener() {
            String[] strs = new String[] { "获取榜单信息", "获取榜单音乐信息", "获取专辑信息", "获取专辑音乐信息", "获取歌手音乐信息", "获取标签信息",
                    "获取标签音乐信息", "关键字搜索歌曲", "歌曲ID查询歌曲信息", "歌曲ID查询专辑信息", "查询歌手信息" };

            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(CMMusicDemo.this).setTitle("歌曲查询类")
                        .setItems(strs, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                case 0:
                                    showProgressBar("数据加载中...");
                                    new Thread() {
                                        @Override
                                        public void run() {
                                            super.run();

                                            ChartListRsp c = MusicQueryInterface.getChartInfo(CMMusicDemo.this, 1, 10);

                                            mUIHandler.obtainMessage(0, c).sendToTarget();

                                        }
                                    }.start();

                                    break;
                                case 1:
                                    showParameterDialog("chartCode", new ParameterCallback() {
                                        @Override
                                        public void callback(final String chartCode) {
                                            Log.i("TAG", "chartCode = " + chartCode);
                                            showProgressBar("数据加载中...");
                                            new Thread() {
                                                @Override
                                                public void run() {
                                                    MusicListRsp m = MusicQueryInterface.getMusicsByChartId(
                                                            CMMusicDemo.this, chartCode, 1, 5);

                                                    mUIHandler.obtainMessage(0, m).sendToTarget();
                                                }
                                            }.start();
                                        }
                                    });

                                    break;
                                case 2:
                                    showProgressBar("数据加载中...");
                                    new Thread() {
                                        @Override
                                        public void run() {
                                            AlbumListRsp a = MusicQueryInterface.getAlbumsBySingerId(CMMusicDemo.this,
                                                    "235", 1, 5);

                                            mUIHandler.obtainMessage(0, a).sendToTarget();
                                        }
                                    }.start();
                                    break;
                                case 3:
                                    showParameterDialog("专辑ID", new ParameterCallback() {
                                        @Override
                                        public void callback(final String albumId) {
                                            Log.w("TAG", "albumId = " + albumId);
                                            showProgressBar("数据加载中...");
                                            new Thread() {
                                                @Override
                                                public void run() {
                                                    MusicListRsp m = MusicQueryInterface.getMusicsByAlbumId(
                                                            CMMusicDemo.this, albumId, 1, 5);

                                                    mUIHandler.obtainMessage(0, m).sendToTarget();
                                                }
                                            }.start();
                                        }
                                    });
                                    break;
                                case 4:
                                    showProgressBar("数据加载中...");
                                    new Thread() {
                                        @Override
                                        public void run() {
                                            MusicListRsp m = MusicQueryInterface.getMusicsBySingerId(CMMusicDemo.this,
                                                    "235", 1, 5);

                                            mUIHandler.obtainMessage(0, m).sendToTarget();

                                        }
                                    }.start();
                                    break;
                                case 5:
                                    showProgressBar("数据加载中...");
                                    new Thread() {
                                        @Override
                                        public void run() {
                                            TagListRsp t = MusicQueryInterface.getTags(CMMusicDemo.this, "10", 1, 5);

                                            mUIHandler.obtainMessage(0, t).sendToTarget();
                                        }
                                    }.start();
                                    break;
                                case 6:
                                    showProgressBar("数据加载中...");
                                    new Thread() {
                                        @Override
                                        public void run() {
                                            MusicListRsp t = MusicQueryInterface.getMusicsByTagId(CMMusicDemo.this,
                                                    "100", 1, 5);

                                            mUIHandler.obtainMessage(0, t).sendToTarget();

                                        }
                                    }.start();
                                    break;
                                case 7:
                                    showParameterDialog(new String[] { "关键字", "关键字类型" }, new ParameterCallbacks() {
                                        @Override
                                        public void callback(final String[] parameters) {
                                            Log.i("TAG", "parameters = " + parameters);
                                            showProgressBar("数据加载中...");
                                            new Thread() {
                                                @Override
                                                public void run() {
                                                    MusicListRsp t = null;
                                                    t = MusicQueryInterface.getMusicsByKey(CMMusicDemo.this,
                                                            URLEncoder.encode(parameters[0]), parameters[1], 1, 5);
                                                    mUIHandler.obtainMessage(0, t).sendToTarget();
                                                    Log.d("tag", "reponse:" + t.toString());
                                                }
                                            }.start();
                                        }
                                    });
                                    break;
                                case 8:
                                    showParameterDialog("歌曲ID", new ParameterCallback() {
                                        @Override
                                        public void callback(final String musicId) {
                                            Log.i("TAG", "musicId = " + musicId);
                                            showProgressBar("数据加载中...");
                                            new Thread() {
                                                @Override
                                                public void run() {
                                                    super.run();

                                                    MusicInfoResult result = MusicQueryInterface.getMusicInfoByMusicId(
                                                            CMMusicDemo.this, musicId);

                                                    mUIHandler.obtainMessage(0, result).sendToTarget();

                                                }
                                            }.start();
                                        }
                                    });

                                    break;

                                case 9:
                                    showParameterDialog("歌曲ID", new ParameterCallback() {
                                        @Override
                                        public void callback(final String musicId) {
                                            Log.i("TAG", "musicId = " + musicId);
                                            showProgressBar("数据加载中...");
                                            new Thread() {
                                                @Override
                                                public void run() {
                                                    super.run();

                                                    AlbumListRsp result = MusicQueryInterface.getAlbumsByMusicId(
                                                            CMMusicDemo.this, musicId, 1, 5);

                                                    mUIHandler.obtainMessage(0, result).sendToTarget();

                                                }
                                            }.start();
                                        }
                                    });

                                    break;

                                case 10:
                                    showParameterDialog("歌手ID", new ParameterCallback() {
                                        @Override
                                        public void callback(final String singerId) {
                                            Log.i("TAG", "singerId = " + singerId);
                                            showProgressBar("数据加载中...");
                                            new Thread() {
                                                @Override
                                                public void run() {
                                                    super.run();

                                                    SingerInfoRsp result = MusicQueryInterface.getSingerInfo(
                                                            CMMusicDemo.this, singerId);

                                                    mUIHandler.obtainMessage(0, result).sendToTarget();

                                                }
                                            }.start();
                                        }
                                    });

                                    break;
                                }

                                // hideProgressBar();
                            }
                        }).create().show();
            }
        });

        // 非彩铃用户一键订购彩铃
        Button buyRingBackByOpenRingBack = (Button) this.findViewById(R.id.buyRingBackByOpenRingBack);
        buyRingBackByOpenRingBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showParameterDialog("musicId", new ParameterCallback() {
                    @Override
                    public void callback(final String musicId) {
                        Log.i("TAG", "musicId = " + musicId);
                        RingbackManagerInterface.buyRingBackByOpenRingBack(CMMusicDemo.this, musicId,
                                new CMMusicCallback<Result>() {
                                    @Override
                                    public void operationResult(Result ret) {
                                        if (null != ret) {
                                            new AlertDialog.Builder(CMMusicDemo.this)
                                                    .setTitle("buyRingBackByOpenRingBack").setMessage(ret.toString())
                                                    .setPositiveButton("确认", null).show();
                                        }
                                    }
                                });
                    }
                });
            }
        });

        // 非特级会员一键订购彩铃
        Button buyRingBackByOpenMember = (Button) this.findViewById(R.id.buyRingBackByOpenMember);
        buyRingBackByOpenMember.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showParameterDialog("musicId", new ParameterCallback() {
                    @Override
                    public void callback(final String musicId) {
                        Log.i("TAG", "musicId = " + musicId);
                        RingbackManagerInterface.buyRingBackByOpenMember(CMMusicDemo.this, musicId,
                                new CMMusicCallback<Result>() {
                                    @Override
                                    public void operationResult(Result ret) {
                                        if (null != ret) {
                                            new AlertDialog.Builder(CMMusicDemo.this)
                                                    .setTitle("buyRingBackByOpenMember").setMessage(ret.toString())
                                                    .setPositiveButton("确认", null).show();
                                        }
                                    }
                                });
                    }
                });
            }
        });

        // 彩铃订购
        Button buyRingback = (Button) this.findViewById(R.id.buyRingback);
        buyRingback.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showParameterDialog("musicId", new ParameterCallback() {
                    @Override
                    public void callback(final String musicId) {
                        Log.i("TAG", "musicId = " + musicId);
                        RingbackManagerInterface.buyRingBack(CMMusicDemo.this, musicId, new CMMusicCallback<Result>() {
                            @Override
                            public void operationResult(Result ret) {
                                if (null != ret) {
                                    new AlertDialog.Builder(CMMusicDemo.this).setTitle("buyRingBack")
                                            .setMessage(ret.toString()).setPositiveButton("确认", null).show();
                                }
                            }
                        });
                    }
                });
            }
        });

        // 振铃下载
        Button vRing = (Button) this.findViewById(R.id.vRing);
        vRing.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showParameterDialog("musicId", new ParameterCallback() {
                    @Override
                    public void callback(final String musicId) {
                        Log.i("TAG", "musicId = " + musicId);
                        VibrateRingManagerInterface.queryVibrateRingDownloadUrl(CMMusicDemo.this, musicId,
                                new CMMusicCallback<DownloadResult>() {
                                    @Override
                                    public void operationResult(DownloadResult downloadResult) {
                                        if (null != downloadResult) {
                                            new AlertDialog.Builder(CMMusicDemo.this)
                                                    .setTitle("queryVibrateRingDownloadUrl")
                                                    .setMessage(downloadResult.toString())
                                                    .setPositiveButton("确认", null).show();
                                        }

                                        Log.d(LOG_TAG, "vRing Download result is " + downloadResult);
                                    }
                                });
                    }
                });

            }
        });

        // 开通会员
        Button openMem = (Button) this.findViewById(R.id.openMem);
        openMem.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                UserManagerInterface.openMember(CMMusicDemo.this, new CMMusicCallback<Result>() {
                    @Override
                    public void operationResult(Result ret) {
                        if (null != ret) {
                            new AlertDialog.Builder(CMMusicDemo.this).setTitle("openMember").setMessage(ret.toString())
                                    .setPositiveButton("确认", null).show();
                        }
                    }
                });
            }
        });

        // 获取在线听歌地址
        Button onlineLse = (Button) this.findViewById(R.id.onlineLse);
        onlineLse.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showParameterDialog(new String[] { "musicId", "codeRate" }, new ParameterCallbacks() {
                    @Override
                    public void callback(final String[] parameters) {
                        Log.i("TAG", "parameters = " + parameters);
                        showProgressBar("数据加载中...");
                        new Thread() {
                            @Override
                            public void run() {
                                StreamRsp s = OnlineListenerMusicInterface.getStream(CMMusicDemo.this, parameters[0],
                                        parameters[1]);
                                mUIHandler.obtainMessage(0, s).sendToTarget();
                            }
                        }.start();
                    }
                });
            }
        });

        // 获取彩铃试听地址
        Button crbtPrelisten = (Button) this.findViewById(R.id.crbtPrelisten);
        crbtPrelisten.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showParameterDialog("musicId", new ParameterCallback() {
                    @Override
                    public void callback(final String musicId) {
                        Log.i("TAG", "musicId = " + musicId);
                        showProgressBar("数据加载中...");
                        new Thread() {
                            @Override
                            public void run() {
                                super.run();

                                CrbtPrelistenRsp c = RingbackManagerInterface.getCrbtPrelisten(CMMusicDemo.this,
                                        musicId);

                                mUIHandler.obtainMessage(0, c).sendToTarget();

                            }
                        }.start();
                    }
                });
            }
        });

        // 查询个人铃音库
        Button crbtBox = (Button) this.findViewById(R.id.crbtBox);
        crbtBox.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressBar("数据加载中...");
                new Thread() {
                    @Override
                    public void run() {
                        CrbtListRsp c = RingbackManagerInterface.getCrbtBox(CMMusicDemo.this);

                        mUIHandler.obtainMessage(0, c).sendToTarget();

                    }
                }.start();
                // hideProgressBar();
            }
        });

        // 设置默认铃音
        Button setDefaultCrbt = (Button) this.findViewById(R.id.setDefaultCrbt);
        setDefaultCrbt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showParameterDialog("crbtId", new ParameterCallback() {
                    @Override
                    public void callback(final String crbtId) {
                        Log.i("TAG", "crbtId = " + crbtId);
                        showProgressBar("数据加载中...");
                        new Thread() {
                            @Override
                            public void run() {
                                Result c = RingbackManagerInterface.setDefaultCrbt(CMMusicDemo.this, crbtId);

                                mUIHandler.obtainMessage(0, c).sendToTarget();
                            }
                        }.start();
                    }
                });
            }
        });

        // 手机号查询默认铃音
        Button getDefaultCrbt = (Button) this.findViewById(R.id.getDefaultCrbt);
        getDefaultCrbt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showParameterDialog("msisdn", new ParameterCallback() {
                    @Override
                    public void callback(final String msisdn) {
                        Log.i("TAG", "msisdn = " + msisdn);
                        showProgressBar("数据加载中...");
                        new Thread() {
                            @Override
                            public void run() {
                                Result c = RingbackManagerInterface.getDefaultCrbt(CMMusicDemo.this, msisdn);

                                mUIHandler.obtainMessage(0, c).sendToTarget();
                            }
                        }.start();
                    }
                });
            }
        });

        // 振铃试听地址
        Button ringPrelisten = (Button) this.findViewById(R.id.ringPrelisten);
        ringPrelisten.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showParameterDialog("musicId", new ParameterCallback() {
                    @Override
                    public void callback(final String musicId) {
                        Log.i("TAG", "musicId = " + musicId);
                        showProgressBar("数据加载中...");
                        new Thread() {
                            @Override
                            public void run() {
                                DownloadResult c = VibrateRingManagerInterface.getRingPrelisten(CMMusicDemo.this,
                                        musicId);

                                mUIHandler.obtainMessage(0, c).sendToTarget();
                            }
                        }.start();
                    }
                });
            }
        });

        // 专属按次
        Button exclusive_net = (Button) this.findViewById(R.id.exclusive_net);
        exclusive_net.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showParameterDialog(new String[] { "serviceId", "definedseq" }, new ParameterCallbacks() {
                    @Override
                    public void callback(final String[] parameters) {
                        Log.i("TAG", "serviceId = " + parameters[0]);
                        ExclusiveManagerInterface.exclusiveOnce(CMMusicDemo.this, parameters[0], parameters[1],
                                new CMMusicCallback<Result>() {
                                    @Override
                                    public void operationResult(Result result) {
                                        if (null != result) {
                                            new AlertDialog.Builder(CMMusicDemo.this).setTitle("exclusiveOnce")
                                                    .setMessage(result.toString()).setPositiveButton("确认", null).show();
                                        }

                                        Log.d(LOG_TAG, "ret is " + result);
                                    }
                                });
                    }
                });
            }
        });

        // 关联歌曲推荐
        Button associateSongRecommendBtn = (Button) this.findViewById(R.id.sr_associate_song_recommend);
        associateSongRecommendBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showParameterDialog("contentId", new ParameterCallback() {
                    @Override
                    public void callback(final String contentId) {
                        Log.i("TAG", "contentId = " + contentId);

                        SongRecommendManagerInterface.associateSongRecommend(CMMusicDemo.this, contentId,
                                new CMMusicCallback<SongRecommendResult>() {

                                    @Override
                                    public void operationResult(SongRecommendResult ret) {
                                        if (null != ret) {
                                            new AlertDialog.Builder(CMMusicDemo.this).setTitle("contentIds")
                                                    .setMessage(ret.toString()).setPositiveButton("确认", null).show();
                                        }
                                    }
                                });
                    }
                });
            }

        });

        // 关联歌手歌曲推荐
        Button associateSingerSongRecommendBtn = (Button) this.findViewById(R.id.sr_associate_singer_song_recommend);
        associateSingerSongRecommendBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showParameterDialog("contentId", new ParameterCallback() {
                    @Override
                    public void callback(final String contentId) {
                        Log.i("TAG", "contentId = " + contentId);
                        SongRecommendManagerInterface.associateSingerSongRecommend(CMMusicDemo.this, contentId,
                                new CMMusicCallback<SongRecommendResult>() {

                                    @Override
                                    public void operationResult(SongRecommendResult ret) {
                                        if (null != ret) {
                                            new AlertDialog.Builder(CMMusicDemo.this).setTitle("contentIds")
                                                    .setMessage(ret.toString()).setPositiveButton("确认", null).show();
                                        }
                                    }
                                });
                    }
                });
            }

        });

        // 偏好歌曲推荐
        Button likeSongRecommendBtn = (Button) this.findViewById(R.id.sr_like_song_recommend);
        likeSongRecommendBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SongRecommendManagerInterface.likeSongRecommend(CMMusicDemo.this,
                        new CMMusicCallback<SongRecommendResult>() {

                            @Override
                            public void operationResult(SongRecommendResult ret) {
                                if (null != ret) {
                                    new AlertDialog.Builder(CMMusicDemo.this).setTitle("contentIds")
                                            .setMessage(ret.toString()).setPositiveButton("确认", null).show();
                                }
                            }
                        });
            }
        });

        // 基于用户的协同过滤推荐
        Button dealSongRecommendBtn = (Button) this.findViewById(R.id.sr_deal_song_recommend);
        dealSongRecommendBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressBar("请稍候...");
                SongRecommendManagerInterface.likeSongRecommend(CMMusicDemo.this,
                        new CMMusicCallback<SongRecommendResult>() {

                            @Override
                            public void operationResult(SongRecommendResult ret) {
                                if (null != ret) {
                                    new AlertDialog.Builder(CMMusicDemo.this).setTitle("contentIds")
                                            .setMessage(ret.toString()).setPositiveButton("确认", null).show();
                                }
                            }
                        });
            }
        });

        // 无界面接口
        Button noUIButton = (Button) this.findViewById(R.id.noUI);
        noUIButton.setOnClickListener(new OnClickListener() {
            String[] strs = new String[] { "彩铃订购（无界面）", "获取彩铃信息", "赠送彩铃（无界面）", "专属按次订购（无界面）", "获取专属业务信息",
                    "非彩铃用户一键订购彩铃（无界面）", "非特级会员一键订购彩铃（无界面）", "查询全曲下载策略", "全曲下载（无界面）", "查询振铃下载策略", "振铃下载（无界面）",
                    "CP专属包月订购（无界面）", "CP专属振铃按次下载（无界面）", "CP专属全曲按次下载（无界面）", "个性化彩铃包月订购（无界面）", "非彩铃用户一键订购个性化彩铃包月（无界面）",
                    "个性化彩铃订购（无界面）", "个性彩铃包月关系查询", "个性化彩铃文字合成铃音", "个性化彩铃铃音文件上传" };

            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(CMMusicDemo.this).setTitle("无界面接口")
                        .setItems(strs, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                case 0:
                                    showParameterDialog(new String[] { "musicId", "bizCode" },
                                            new ParameterCallbacks() {
                                                @Override
                                                public void callback(final String[] parameters) {
                                                    Log.i("TAG", "musicId = " + parameters[0]);
                                                    RingbackManagerInterface.buyRingback(CMMusicDemo.this,
                                                            parameters[0], parameters[1],
                                                            new CMMusicCallback<Result>() {
                                                                @Override
                                                                public void operationResult(Result ret) {
                                                                    if (null != ret) {
                                                                        new AlertDialog.Builder(CMMusicDemo.this)
                                                                                .setTitle("buyRingback")
                                                                                .setMessage(ret.toString())
                                                                                .setPositiveButton("确认", null).show();
                                                                    }
                                                                }
                                                            });
                                                }
                                            });

                                    break;
                                case 1:
                                    showParameterDialog("crbtId", new ParameterCallback() {
                                        @Override
                                        public void callback(final String crbtId) {
                                            Log.i("TAG", "crbtId = " + crbtId);
                                            showProgressBar("数据加载中...");
                                            new Thread() {
                                                @Override
                                                public void run() {
                                                    String t = RingbackManagerInterface.getRingbackInfo(
                                                            CMMusicDemo.this, crbtId);

                                                    mUIHandler.obtainMessage(0, t).sendToTarget();
                                                }
                                            }.start();
                                        }
                                    });
                                    break;

                                case 2:
                                    showParameterDialog(new String[] { "musicId", "phoneNum" },
                                            new ParameterCallbacks() {
                                                @Override
                                                public void callback(String[] parameters) {
                                                    Log.i("TAG", "musicId = " + parameters[0]);
                                                    RingbackManagerInterface.giveRingbackByCustom(CMMusicDemo.this,
                                                            parameters[0], parameters[1],
                                                            new CMMusicCallback<Result>() {
                                                                @Override
                                                                public void operationResult(Result result) {
                                                                    if (null != result) {
                                                                        new AlertDialog.Builder(CMMusicDemo.this)
                                                                                .setTitle("giveRingbackByCustom")
                                                                                .setMessage(result.toString())
                                                                                .setPositiveButton("确认", null).show();
                                                                    }

                                                                    Log.d(LOG_TAG, "ret is " + result);
                                                                }
                                                            });
                                                }
                                            });

                                    break;
                                case 3:
                                    showParameterDialog(new String[] { "contentId", "serviceId", "definedseq" },
                                            new ParameterCallbacks() {
                                                @Override
                                                public void callback(final String[] parameters) {
                                                    Log.i("TAG", "serviceId = " + parameters[0]);
                                                    ExclusiveManagerInterface.exclusiveTimesOrder(CMMusicDemo.this,
                                                            parameters[0], parameters[1], parameters[2],
                                                            new CMMusicCallback<Result>() {
                                                                @Override
                                                                public void operationResult(Result result) {
                                                                    if (null != result) {
                                                                        new AlertDialog.Builder(CMMusicDemo.this)
                                                                                .setTitle("exclusiveTimesOrder")
                                                                                .setMessage(result.toString())
                                                                                .setPositiveButton("确认", null).show();
                                                                    }

                                                                    Log.d(LOG_TAG, "ret is " + result);
                                                                }
                                                            });
                                                }
                                            });
                                    break;
                                case 4:
                                    showParameterDialog("contentId", new ParameterCallback() {
                                        @Override
                                        public void callback(final String contentId) {
                                            Log.i("TAG", "contentId = " + contentId);
                                            showProgressBar("数据加载中...");
                                            new Thread() {
                                                @Override
                                                public void run() {
                                                    String t = ExclusiveManagerInterface.getServiceEx(CMMusicDemo.this,
                                                            contentId);

                                                    mUIHandler.obtainMessage(0, t).sendToTarget();
                                                }
                                            }.start();
                                        }
                                    });
                                    break;
                                case 5:
                                    showParameterDialog(new String[] { "musicId", "bizCode" },
                                            new ParameterCallbacks() {
                                                @Override
                                                public void callback(final String[] parameters) {
                                                    Log.i("TAG", "musicId = " + parameters[0]);
                                                    RingbackManagerInterface.buyRingbackByOpenRingBack(
                                                            CMMusicDemo.this, parameters[0], parameters[1],
                                                            new CMMusicCallback<Result>() {
                                                                @Override
                                                                public void operationResult(Result ret) {
                                                                    if (null != ret) {
                                                                        new AlertDialog.Builder(CMMusicDemo.this)
                                                                                .setTitle("buyRingbackByOpenRingBack")
                                                                                .setMessage(ret.toString())
                                                                                .setPositiveButton("确认", null).show();
                                                                    }
                                                                }
                                                            });
                                                }
                                            });

                                    break;
                                case 6:
                                    showParameterDialog(new String[] { "musicId", "bizCode" },
                                            new ParameterCallbacks() {
                                                @Override
                                                public void callback(final String[] parameters) {
                                                    Log.i("TAG", "musicId = " + parameters[0]);
                                                    RingbackManagerInterface.buyRingbackByOpenMember(CMMusicDemo.this,
                                                            parameters[0], parameters[1],
                                                            new CMMusicCallback<Result>() {
                                                                @Override
                                                                public void operationResult(Result ret) {
                                                                    if (null != ret) {
                                                                        new AlertDialog.Builder(CMMusicDemo.this)
                                                                                .setTitle("buyRingbackByOpenMember")
                                                                                .setMessage(ret.toString())
                                                                                .setPositiveButton("确认", null).show();
                                                                    }
                                                                }
                                                            });
                                                }
                                            });

                                    break;

                                case 7:
                                    showParameterDialog("musicId", new ParameterCallback() {
                                        @Override
                                        public void callback(final String musicId) {
                                            Log.i("TAG", "musicId = " + musicId);
                                            showProgressBar("数据加载中...");
                                            new Thread() {
                                                @Override
                                                public void run() {
                                                    String t = FullSongManagerInterface.getFullSongPolicy(
                                                            CMMusicDemo.this, musicId);

                                                    mUIHandler.obtainMessage(0, t).sendToTarget();
                                                }
                                            }.start();
                                        }
                                    });
                                    break;

                                case 8:
                                    showParameterDialog(new String[] { "musicId", "bizCode", "saleBizCode", "bizType",
                                            "codeRate" }, new ParameterCallbacks() {
                                        @Override
                                        public void callback(String[] parameters) {
                                            Log.i("TAG", "musicId = " + parameters[0]);
                                            FullSongManagerInterface.getFullSongDownloadUrl(CMMusicDemo.this,
                                                    parameters[0], parameters[1], parameters[2], parameters[3],
                                                    parameters[4], new CMMusicCallback<DownloadResult>() {
                                                        @Override
                                                        public void operationResult(DownloadResult result) {
                                                            if (null != result) {
                                                                new AlertDialog.Builder(CMMusicDemo.this)
                                                                        .setTitle("getFullSongDownloadUrl")
                                                                        .setMessage(result.toString())
                                                                        .setPositiveButton("确认", null).show();
                                                            }

                                                            Log.d(LOG_TAG, "ret is " + result);
                                                        }
                                                    });
                                        }
                                    });

                                    break;
                                case 9:
                                    showParameterDialog("musicId", new ParameterCallback() {
                                        @Override
                                        public void callback(final String musicId) {
                                            Log.i("TAG", "musicId = " + musicId);
                                            showProgressBar("数据加载中...");
                                            new Thread() {
                                                @Override
                                                public void run() {
                                                    String t = VibrateRingManagerInterface.getVibrateRingPolicy(
                                                            CMMusicDemo.this, musicId);

                                                    mUIHandler.obtainMessage(0, t).sendToTarget();
                                                }
                                            }.start();
                                        }
                                    });
                                    break;

                                case 10:
                                    showParameterDialog(new String[] { "musicId", "bizCode", "saleBizCode", "bizType",
                                            "codeRate" }, new ParameterCallbacks() {
                                        @Override
                                        public void callback(String[] parameters) {
                                            Log.i("TAG", "musicId = " + parameters[0]);
                                            VibrateRingManagerInterface.getVibrateRingDownloadUrl(CMMusicDemo.this,
                                                    parameters[0], parameters[1], parameters[2], parameters[3],
                                                    parameters[4], new CMMusicCallback<DownloadResult>() {
                                                        @Override
                                                        public void operationResult(DownloadResult result) {
                                                            if (null != result) {
                                                                new AlertDialog.Builder(CMMusicDemo.this)
                                                                        .setTitle("getVibrateRingDownloadUrl")
                                                                        .setMessage(result.toString())
                                                                        .setPositiveButton("确认", null).show();
                                                            }

                                                            Log.d(LOG_TAG, "ret is " + result);
                                                        }
                                                    });
                                        }
                                    });

                                    break;

                                case 11:
                                    showParameterDialog(new String[] { "serviceId", "definedseq" },
                                            new ParameterCallbacks() {
                                                @Override
                                                public void callback(final String[] parameters) {
                                                    Log.i("TAG", "serviceId = " + parameters[0]);
                                                    CPManagerInterface.openCpMonth(CMMusicDemo.this, parameters[0],
                                                            parameters[1], new CMMusicCallback<Result>() {
                                                                @Override
                                                                public void operationResult(Result ret) {
                                                                    if (null != ret) {
                                                                        new AlertDialog.Builder(CMMusicDemo.this)
                                                                                .setTitle("openCpMonth")
                                                                                .setMessage(ret.toString())
                                                                                .setPositiveButton("确认", null).show();
                                                                    }
                                                                }
                                                            });
                                                }
                                            });

                                    break;

                                case 12:
                                    showParameterDialog(new String[] { "musicId", "codeRate" },
                                            new ParameterCallbacks() {
                                                @Override
                                                public void callback(final String[] parameters) {
                                                    Log.i("TAG", "musicId = " + parameters[0]);
                                                    CPManagerInterface.getCPVibrateRingTimeDownloadUrl(
                                                            CMMusicDemo.this, parameters[0], parameters[1],
                                                            new CMMusicCallback<DownloadResult>() {
                                                                @Override
                                                                public void operationResult(DownloadResult ret) {
                                                                    if (null != ret) {
                                                                        new AlertDialog.Builder(CMMusicDemo.this)
                                                                                .setTitle(
                                                                                        "getCPVibrateRingTimeDownloadUrl")
                                                                                .setMessage(ret.toString())
                                                                                .setPositiveButton("确认", null).show();
                                                                    }
                                                                }
                                                            });
                                                }
                                            });

                                    break;

                                case 13:
                                    showParameterDialog(new String[] { "musicId", "codeRate" },
                                            new ParameterCallbacks() {
                                                @Override
                                                public void callback(final String[] parameters) {
                                                    Log.i("TAG", "musicId = " + parameters[0]);
                                                    CPManagerInterface.getCpFullSongTimeDownloadUrl(CMMusicDemo.this,
                                                            parameters[0], parameters[1],
                                                            new CMMusicCallback<DownloadResult>() {
                                                                @Override
                                                                public void operationResult(DownloadResult ret) {
                                                                    if (null != ret) {
                                                                        new AlertDialog.Builder(CMMusicDemo.this)
                                                                                .setTitle(
                                                                                        "getCpFullSongTimeDownloadUrl")
                                                                                .setMessage(ret.toString())
                                                                                .setPositiveButton("确认", null).show();
                                                                    }
                                                                }
                                                            });
                                                }
                                            });

                                    break;
                                case 14:
                                    showParameterDialog(new String[] { "monthType" }, new ParameterCallbacks() {
                                        @Override
                                        public void callback(final String[] parameters) {
                                            Log.i("TAG", "monthType = " + parameters[0]);
                                            RingbackManagerInterface.ownRingMonth(CMMusicDemo.this, parameters[0],
                                                    new CMMusicCallback<Result>() {
                                                        @Override
                                                        public void operationResult(Result ret) {
                                                            if (null != ret) {
                                                                new AlertDialog.Builder(CMMusicDemo.this)
                                                                        .setTitle("ownRingMonth")
                                                                        .setMessage(ret.toString())
                                                                        .setPositiveButton("确认", null).show();
                                                            }
                                                        }
                                                    });
                                        }
                                    });

                                    break;

                                case 15:
                                    showParameterDialog(new String[] { "monthType" }, new ParameterCallbacks() {
                                        @Override
                                        public void callback(final String[] parameters) {
                                            Log.i("TAG", "monthType = " + parameters[0]);
                                            RingbackManagerInterface.keyOrderOwnRingMonth(CMMusicDemo.this,
                                                    parameters[0], new CMMusicCallback<Result>() {
                                                        @Override
                                                        public void operationResult(Result ret) {
                                                            if (null != ret) {
                                                                new AlertDialog.Builder(CMMusicDemo.this)
                                                                        .setTitle("keyOrderOwnRingMonth")
                                                                        .setMessage(ret.toString())
                                                                        .setPositiveButton("确认", null).show();
                                                            }
                                                        }
                                                    });
                                        }
                                    });

                                    break;

                                case 16:
                                    showParameterDialog(new String[] { "crbtId" }, new ParameterCallbacks() {
                                        @Override
                                        public void callback(final String[] parameters) {
                                            Log.i("TAG", "crbtId = " + parameters[0]);
                                            RingbackManagerInterface.orderOwnRingback(CMMusicDemo.this, parameters[0],
                                                    new CMMusicCallback<Result>() {
                                                        @Override
                                                        public void operationResult(Result ret) {
                                                            if (null != ret) {
                                                                new AlertDialog.Builder(CMMusicDemo.this)
                                                                        .setTitle("orderOwnRingback")
                                                                        .setMessage(ret.toString())
                                                                        .setPositiveButton("确认", null).show();
                                                            }
                                                        }
                                                    });
                                        }
                                    });

                                    break;

                                case 17:
                                    showProgressBar("数据加载中...");
                                    new Thread() {
                                        @Override
                                        public void run() {
                                            Result t = RingbackManagerInterface.isOwnRingMonthUser(CMMusicDemo.this);

                                            mUIHandler.obtainMessage(0, t).sendToTarget();
                                        }
                                    }.start();

                                    break;

                                case 18:
                                    showParameterDialog(new String[] { "content", "bgMusicId", "sex", "ringName" },
                                            new ParameterCallbacks() {
                                                @Override
                                                public void callback(final String[] parameters) {
                                                    Log.i("TAG", "content = " + parameters[0]);

                                                    showProgressBar("数据加载中...");
                                                    new Thread() {
                                                        @Override
                                                        public void run() {
                                                            OwnRingRsp t = RingbackManagerInterface
                                                                    .createTextOwnRingback(CMMusicDemo.this,
                                                                            parameters[0], parameters[1],
                                                                            parameters[2], parameters[3]);

                                                            mUIHandler.obtainMessage(0, t).sendToTarget();
                                                        }
                                                    }.start();
                                                }
                                            });

                                    break;

                                case 19:
                                    showParameterDialog(new String[] { "filepath", "ringName", "audioType" },
                                            new ParameterCallbacks() {
                                                @Override
                                                public void callback(final String[] parameters) {
                                                    Log.i("TAG", "filepath = " + parameters[0]);
                                                    new Thread() {
                                                        @Override
                                                        public void run() {
                                                            OwnRingRsp t = RingbackManagerInterface.uploadOwnRingback(
                                                                    CMMusicDemo.this, new File(parameters[0]),
                                                                    parameters[1], Integer.parseInt(parameters[2]));

                                                            mUIHandler.obtainMessage(0, t).sendToTarget();
                                                        }
                                                    }.start();
                                                }
                                            });

                                    break;
                                }
                            }
                        }).create().show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_cmmusic_demo, menu);
        return true;
    }

    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()) {
        case R.id.deletesong:
            showParameterDialog("crbtId", new ParameterCallback() {

                @Override
                public void callback(final String crbtId) {
                    Log.i("TAG", "crbtId = " + crbtId);
                    showProgressBar("数据加载中...");
                    new Thread() {
                        @Override
                        public void run() {
                            Result t = RingbackManagerInterface.deletePersonRing(CMMusicDemo.this, crbtId);

                            mUIHandler.obtainMessage(0, t).sendToTarget();
                        }
                    }.start();
                }
            });
            break;
        // 全曲下载
        case R.id.fullsong:
            showParameterDialog("musicId", new ParameterCallback() {
                @Override
                public void callback(final String musicId) {
                    Log.i("TAG", "musicId = " + musicId);
                    FullSongManagerInterface.getFullSongDownloadUrl(CMMusicDemo.this, musicId,
                            new CMMusicCallback<DownloadResult>() {
                                @Override
                                public void operationResult(final DownloadResult downloadResult) {
                                    if (null != downloadResult) {
                                        new AlertDialog.Builder(CMMusicDemo.this)
                                                .setTitle("getFullSongDownloadUrlByNet")
                                                .setMessage(downloadResult.toString()).setPositiveButton("确认", null)
                                                .show();
                                    }

                                    Log.d(LOG_TAG, "FullSong Download result is " + downloadResult);
                                }
                            });
                }
            });

            break;
        }
    }

    class T1 extends Thread {
        @Override
        public void run() {
            super.run();
            Looper.prepare();
            // if (!InitCmmInterface.initCheck(CMMusicDemo.this)) {
            Hashtable<String, String> b = InitCmmInterface.initCmmEnv(CMMusicDemo.this);
            Message m = new Message();
            m.what = 0;
            m.obj = b;
            mUIHandler.sendMessage(m);
            // } else {
            // if (null != dialog) {
            // dialog.dismiss();
            // }
            //
            // Toast.makeText(CMMusicDemo.this, "已初始化过",
            // Toast.LENGTH_LONG).show();
            // }
            Looper.loop();
        }
    }

    private ProgressDialog mProgress = null;

    void showProgressBar(final String msg) {
        Log.d(LOG_TAG, "showProgressBar invoked!");

        mUIHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mProgress == null) {
                    mProgress = new ProgressDialog(CMMusicDemo.this);
                    mProgress.setMessage(msg);
                    mProgress.setIndeterminate(false);
                    mProgress.setCancelable(false);
                    mProgress.show();
                }
            }
        });
    }

    void hideProgressBar() {
        Log.d(LOG_TAG, "hideProgressBar invoked!");
        mUIHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mProgress != null) {
                    mProgress.dismiss();
                    mProgress = null;
                }
            }
        });
    }

    void showParameterDialog(String title, final ParameterCallback callback) {
        View view = View.inflate(CMMusicDemo.this, R.layout.parameter_dialog, null);
        final EditText edt = (EditText) view.findViewById(R.id.editText1);
        new AlertDialog.Builder(CMMusicDemo.this).setTitle(title).setView(view).setMessage("请输入参数:" + title)
                .setNegativeButton("取消", null).setPositiveButton("确认", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String parameter = edt.getText().toString();
                        if (callback != null) {
                            callback.callback(parameter);
                        }
                    }
                }).show();
    }

    void showParameterDialog(String[] titles, final ParameterCallbacks callback) {
        String title = getStrForArray(titles);
        final MyGroupView view = new MyGroupView(CMMusicDemo.this);
        for (int i = 0; i < titles.length; i++) {
            EditText paramEdt = new EditText(CMMusicDemo.this);
            paramEdt.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
            paramEdt.setHint(titles[i]);
            view.addView(paramEdt);

        }
        new AlertDialog.Builder(CMMusicDemo.this).setTitle(title).setView(view).setMessage("请输入参数:" + title)
                .setNegativeButton("取消", null).setPositiveButton("确认", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int count = view.getChildCount();
                        String[] parameters = new String[count];
                        for (int i = 0; i < count; i++) {
                            View child = view.getChildAt(i);
                            if (child instanceof EditText) {
                                EditText et = (EditText) child;
                                parameters[i] = et.getText().toString();
                            }
                        }
                        if (callback != null) {
                            callback.callback(parameters);
                        }
                    }
                }).show();
    }

    interface ParameterCallback {
        void callback(String parameter);
    }

    interface ParameterCallbacks {
        void callback(String[] parameters);
    }

    String getStrForArray(String[] strs) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            sb.append(strs[i]);
            if (i < strs.length - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            InitCmmInterface.exitApp();
            finish();

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

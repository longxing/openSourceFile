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
                    Toast.makeText(CMMusicDemo.this, "��� = null", Toast.LENGTH_SHORT).show();
                    return;
                }
                new AlertDialog.Builder(CMMusicDemo.this).setTitle("���").setMessage((msg.obj).toString())
                        .setPositiveButton("ȷ��", null).show();
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
                dialog = ProgressDialog.show(CMMusicDemo.this, null, "���Ժ򡭡�", true, false);
                requestTime = System.currentTimeMillis();
                new Thread(new T1()).start();
                // } else {
                // new
                // AlertDialog.Builder(CMMusicDemo.this).setTitle("init").setMessage("�ѳ�ʼ����")
                // .setPositiveButton("ȷ��", null).show();
                // }
            }
        });

        // CPר������
        Button cpButton = (Button) this.findViewById(R.id.cp);
        cpButton.setOnClickListener(new OnClickListener() {
            String[] strs = new String[] { "CP����ȫ������", "CP������������", "CPר��ȫ������", "CPר����������", "��ѯCPר�����¶�����ϵ", "CPר�����¶���",
                    "CPר�������˶�" };

            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(CMMusicDemo.this).setTitle("CPר��������")
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
                                                                        .setPositiveButton("ȷ��", null).show();
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
                                                                        .setPositiveButton("ȷ��", null).show();
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
                                                    // showProgressBar("���ݼ�����...");
                                                    CPManagerInterface.queryCPFullSongDownloadUrl(CMMusicDemo.this,
                                                            parameters[0], parameters[1], parameters[2],
                                                            new CMMusicCallback<DownloadResult>() {

                                                                @Override
                                                                public void operationResult(DownloadResult ret) {
                                                                    if (null != ret) {
                                                                        new AlertDialog.Builder(CMMusicDemo.this)
                                                                                .setTitle("queryCPFullSongDownloadUrl")
                                                                                .setMessage(ret.toString())
                                                                                .setPositiveButton("ȷ��", null).show();
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
                                                    // showProgressBar("���ݼ�����...");

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
                                                                                .setPositiveButton("ȷ��", null).show();
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
                                            showProgressBar("���ݼ�����...");
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
                                                                                .setPositiveButton("ȷ��", null).show();
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
                                            showProgressBar("���ݼ�����...");
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
            String[] strs = new String[] { "MV���¿�ͨ", "MV�㲥", "MV�����˶�", "MV���¹�ϵ��ѯ" };

            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(CMMusicDemo.this).setTitle("MV��")
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
                                                                                .setPositiveButton("ȷ��", null).show();
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
                                                                        .setPositiveButton("ȷ��", null).show();
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
                                            showProgressBar("���ݼ�����...");
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
                                                    showProgressBar("���ݼ�����...");
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

        // ���Ի�����
        Button ownRingback = (Button) this.findViewById(R.id.ownRingback);
        ownRingback.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                RingbackManagerInterface.buyOwnRingback(CMMusicDemo.this, new CMMusicCallback<Result>() {
                    @Override
                    public void operationResult(Result downloadResult) {
                        if (null != downloadResult) {
                            new AlertDialog.Builder(CMMusicDemo.this).setTitle("buyOwnRingbackByNet")
                                    .setMessage(downloadResult.toString()).setPositiveButton("ȷ��", null).show();
                        }

                        Log.d(LOG_TAG, "vRing Download result is " + downloadResult);
                    }
                });
            }
        });

        // ���Ի��������
        Button ownRingMonthBtn = (Button) this.findViewById(R.id.own_ring_month);
        ownRingMonthBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                RingbackManagerInterface.ownRingMonth(CMMusicDemo.this, new CMMusicCallback<Result>() {

                    @Override
                    public void operationResult(Result result) {

                        if (null != result) {
                            new AlertDialog.Builder(CMMusicDemo.this).setTitle("OwnRingMonth")
                                    .setMessage(result.toString()).setPositiveButton("ȷ��", null).show();
                        }

                        Log.i(LOG_TAG, "OwnRingMonth is " + result);
                    }
                });
            }
        });

        // �ǲ����û�һ���������Ի��������
        Button keyOrderOwnRingMonthBtn = (Button) this.findViewById(R.id.key_order_own_ring_month);
        keyOrderOwnRingMonthBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                RingbackManagerInterface.keyOrderOwnRingMonth(CMMusicDemo.this, new CMMusicCallback<Result>() {

                    @Override
                    public void operationResult(Result result) {

                        if (null != result) {
                            new AlertDialog.Builder(CMMusicDemo.this).setTitle("keyOrderOwnRingMonth")
                                    .setMessage(result.toString()).setPositiveButton("ȷ��", null).show();
                        }

                        Log.i(LOG_TAG, "keyOrderOwnRingMonth is " + result);
                    }
                });
            }
        });

        // ������֧��ϵ�нӿ�
        Button payment = (Button) this.findViewById(R.id.payment);
        payment.setOnClickListener(new OnClickListener() {
            String[] strs = new String[] { "����û��Ƿ���ע��", "��ѯ�û���Ϣ", "�޸��û���Ϣ", "�û�����ѯ", "�û����Ѽ�¼��ѯ", "�û�ת�˼�¼��ѯ",
                    "��ȡ֧�����б�", "�û��˻���ֵ", "����ҵ�񶩹�", "��ѯҵ�����", "ȫ������", "��������", "ת��" };

            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(CMMusicDemo.this).setTitle("������֧��ϵ�нӿ�")
                        .setItems(strs, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                case 0:
                                    // ����û��Ƿ���ע��
                                    showParameterDialog("accountName", new ParameterCallback() {
                                        @Override
                                        public void callback(final String accountName) {
                                            Log.w("TAG", "accountName = " + accountName);
                                            showProgressBar("���ݼ�����...");
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
                                    // ��ѯ�û���Ϣ
                                    showParameterDialog(new String[] { "UID", "accountName" },
                                            new ParameterCallbacks() {
                                                @Override
                                                public void callback(final String[] parameters) {
                                                    Log.i("TAG", "parameters = " + parameters);
                                                    showProgressBar("���ݼ�����...");
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
                                    // �޸��û���Ϣ
                                    showParameterDialog("UID", new ParameterCallback() {
                                        @Override
                                        public void callback(final String UID) {
                                            Log.w("TAG", "UID = " + UID);
                                            showProgressBar("���ݼ�����...");
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
                                    // �û�����ѯ
                                    showParameterDialog(new String[] { "UID", "type" }, new ParameterCallbacks() {
                                        @Override
                                        public void callback(final String[] parameters) {
                                            Log.i("TAG", "parameters = " + parameters);
                                            showProgressBar("���ݼ�����...");
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
                                    // �û����Ѽ�¼��ѯ
                                    showParameterDialog(new String[] { "UID", "type", "tradeNum", "startTime",
                                            "endTime" }, new ParameterCallbacks() {
                                        @Override
                                        public void callback(final String[] parameters) {
                                            Log.i("TAG", "parameters = " + parameters);
                                            showProgressBar("���ݼ�����...");
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
                                    // �û�ת�˼�¼��ѯ
                                    showParameterDialog(new String[] { "UID", "type", "startTime", "endTime" },
                                            new ParameterCallbacks() {
                                                @Override
                                                public void callback(final String[] parameters) {
                                                    Log.i("TAG", "parameters = " + parameters);
                                                    showProgressBar("���ݼ�����...");
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
                                    // ��ȡ֧�����б�
                                    showProgressBar("���ݼ�����...");
                                    new Thread() {
                                        @Override
                                        public void run() {
                                            String t = PaymentManagerInterface.getPayments(CMMusicDemo.this);

                                            mUIHandler.obtainMessage(0, t).sendToTarget();
                                        }
                                    }.start();
                                    break;
                                case 7:
                                    // �û��˻���ֵ
                                    showParameterDialog(new String[] { "UID", "type", "amount" },
                                            new ParameterCallbacks() {
                                                @Override
                                                public void callback(final String[] parameters) {
                                                    Log.i("TAG", "parameters = " + parameters);
                                                    showProgressBar("���ݼ�����...");
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
                                    // ����ҵ�񶩹�
                                    showParameterDialog(new String[] { "UID", "type", "amount" },
                                            new ParameterCallbacks() {
                                                @Override
                                                public void callback(final String[] parameters) {
                                                    Log.i("TAG", "parameters = " + parameters);
                                                    showProgressBar("���ݼ�����...");
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
                                                                                        .setPositiveButton("ȷ��", null)
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
                                    // ��ѯҵ�����
                                    showParameterDialog(new String[] { "UID", "musicId" }, new ParameterCallbacks() {
                                        @Override
                                        public void callback(final String[] parameters) {
                                            Log.i("TAG", "parameters = " + parameters);
                                            showProgressBar("���ݼ�����...");
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
                                    // ȫ������
                                    showParameterDialog(new String[] { "UID", "musicId" }, new ParameterCallbacks() {
                                        @Override
                                        public void callback(final String[] parameters) {
                                            Log.i("TAG", "parameters = " + parameters);
                                            showProgressBar("���ݼ�����...");

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
                                                                        .setPositiveButton("ȷ��", null).show();

                                                                mUIHandler.obtainMessage(0, ret).sendToTarget();
                                                            }

                                                            Log.d(LOG_TAG, "ret is " + ret);
                                                        }

                                                    });

                                        }
                                    });
                                    break;
                                case 11:
                                    // ��������
                                    showParameterDialog(new String[] { "UID", "musicId" }, new ParameterCallbacks() {
                                        @Override
                                        public void callback(final String[] parameters) {
                                            Log.i("TAG", "parameters = " + parameters);
                                            showProgressBar("���ݼ�����...");

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
                                                                        .setPositiveButton("ȷ��", null).show();
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
                                                                        .setPositiveButton("ȷ��", null).show();
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

        // ��¼
        Button loginOrder = (Button) this.findViewById(R.id.loginOrder);
        loginOrder.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                PaymentManagerInterface.login(CMMusicDemo.this, false, new CMMusicCallback<Result>() {
                    @Override
                    public void operationResult(Result result) {
                        if (null != result) {
                            new AlertDialog.Builder(CMMusicDemo.this).setTitle("regist")
                                    .setMessage(((LoginResult) result).toString()).setPositiveButton("ȷ��", null).show();
                        }

                        Log.d(LOG_TAG, "ret is " + result);
                    }
                });
            }
        });

        // ע��
        Button regist = (Button) this.findViewById(R.id.regist);
        regist.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                PaymentManagerInterface.regist(CMMusicDemo.this, false, new CMMusicCallback<RegistResult>() {
                    @Override
                    public void operationResult(RegistResult result) {
                        if (null != result) {
                            new AlertDialog.Builder(CMMusicDemo.this).setTitle("regist")
                                    .setMessage((result).toString()).setPositiveButton("ȷ��", null).show();
                        }

                        Log.d(LOG_TAG, "ret is " + result);
                    }
                });
            }
        });

        // ����ȫ��
        Button giveFullSongByNet = (Button) this.findViewById(R.id.giveFullSongByNet);
        giveFullSongByNet.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showParameterDialog(new String[] { "receivemdn", "musicId" }, new ParameterCallbacks() {
                    @Override
                    public void callback(final String[] parameters) {
                        Log.i("TAG", "parameters = " + parameters);
                        showProgressBar("���ݼ�����...");

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

        // ��������
        Button giveVibrateRingByNet = (Button) this.findViewById(R.id.giveVibrateRingByNet);
        giveVibrateRingByNet.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showParameterDialog(new String[] { "receivemdn", "musicId" }, new ParameterCallbacks() {
                    @Override
                    public void callback(final String[] parameters) {
                        Log.i("TAG", "parameters = " + parameters);
                        showProgressBar("���ݼ�����...");

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

        // ������֤���¼
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
                                    .setPositiveButton("ȷ��", null).show();
                        }

                        Log.d(LOG_TAG, "ret is " + result);
                    }
                });
            }
        });

        // ��Ҫ����
        Button crbtAskfor = (Button) this.findViewById(R.id.crbtAskfor);
        crbtAskfor.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showParameterDialog(new String[] { "receivemdn", "musicId", "validCode" }, new ParameterCallbacks() {
                    @Override
                    public void callback(final String[] parameters) {
                        Log.i("TAG", "parameters = " + parameters);
                        showProgressBar("���ݼ�����...");
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

        // ���ŵ�¼��֤
        Button smsAuthLoginValidate = (Button) this.findViewById(R.id.smsAuthLoginValidate);
        smsAuthLoginValidate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressBar("���ݼ�����...");
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

        // �����ֻ��Ų�ѯ�Ƿ�ͨ����
        Button crbtOpenCheck = (Button) this.findViewById(R.id.crbtOpenCheck);
        crbtOpenCheck.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showParameterDialog("�ֻ���", new ParameterCallback() {
                    @Override
                    public void callback(final String phoneNum) {
                        Log.i("TAG", "phoneNum = " + phoneNum);
                        showProgressBar("���ݼ�����...");
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

        // ��ѯ������Ϣ
        // Button singerInfo = (Button) this.findViewById(R.id.singerInfo);
        // singerInfo.setOnClickListener(new OnClickListener() {
        // @Override
        // public void onClick(View v) {
        // showParameterDialog("����ID", new ParameterCallback() {
        // @Override
        // public void callback(final String singerId) {
        // Log.i("TAG", "singerId = " + singerId);
        // showProgressBar("���ݼ�����...");
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

        // ����ID��ѯר����Ϣ
        // Button albumListbymusic = (Button) this
        // .findViewById(R.id.albumListbymusic);
        // albumListbymusic.setOnClickListener(new OnClickListener() {
        // @Override
        // public void onClick(View v) {
        // showParameterDialog("����ID", new ParameterCallback() {
        // @Override
        // public void callback(final String musicId) {
        // Log.i("TAG", "musicId = " + musicId);
        // showProgressBar("���ݼ�����...");
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

        // ����ID��ѯ������Ϣ
        // Button musicQuerybymusic = (Button) this
        // .findViewById(R.id.musicQuerybymusic);
        // musicQuerybymusic.setOnClickListener(new OnClickListener() {
        // @Override
        // public void onClick(View v) {
        // showParameterDialog("����ID", new ParameterCallback() {
        //
        // @Override
        // public void callback(final String musicId) {
        // Log.i("TAG", "musicId = " + musicId);
        // showProgressBar("���ݼ�����...");
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

        // ���Ͳ���
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
                                            .setMessage(result.toString()).setPositiveButton("ȷ��", null).show();
                                }

                                Log.d(LOG_TAG, "ret is " + result);
                            }
                        });
                    }
                });
            }
        });

        // ��ͨ����
        Button openRingback = (Button) this.findViewById(R.id.openRingback);
        openRingback.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                RingbackManagerInterface.openRingback(CMMusicDemo.this, new CMMusicCallback<Result>() {
                    @Override
                    public void operationResult(Result result) {
                        if (null != result) {
                            new AlertDialog.Builder(CMMusicDemo.this).setTitle("openRingback")
                                    .setMessage(result.toString()).setPositiveButton("ȷ��", null).show();
                        }

                        Log.d(LOG_TAG, "ret is " + result);
                    }
                });
            }
        });

        // ��ͨ���壨�޽��棩
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

        // ������ԃ��
        Button musicQuery = (Button) this.findViewById(R.id.musicQuery);
        musicQuery.setOnClickListener(new OnClickListener() {
            String[] strs = new String[] { "��ȡ����Ϣ", "��ȡ��������Ϣ", "��ȡר����Ϣ", "��ȡר��������Ϣ", "��ȡ����������Ϣ", "��ȡ��ǩ��Ϣ",
                    "��ȡ��ǩ������Ϣ", "�ؼ�����������", "����ID��ѯ������Ϣ", "����ID��ѯר����Ϣ", "��ѯ������Ϣ" };

            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(CMMusicDemo.this).setTitle("������ѯ��")
                        .setItems(strs, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                case 0:
                                    showProgressBar("���ݼ�����...");
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
                                            showProgressBar("���ݼ�����...");
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
                                    showProgressBar("���ݼ�����...");
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
                                    showParameterDialog("ר��ID", new ParameterCallback() {
                                        @Override
                                        public void callback(final String albumId) {
                                            Log.w("TAG", "albumId = " + albumId);
                                            showProgressBar("���ݼ�����...");
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
                                    showProgressBar("���ݼ�����...");
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
                                    showProgressBar("���ݼ�����...");
                                    new Thread() {
                                        @Override
                                        public void run() {
                                            TagListRsp t = MusicQueryInterface.getTags(CMMusicDemo.this, "10", 1, 5);

                                            mUIHandler.obtainMessage(0, t).sendToTarget();
                                        }
                                    }.start();
                                    break;
                                case 6:
                                    showProgressBar("���ݼ�����...");
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
                                    showParameterDialog(new String[] { "�ؼ���", "�ؼ�������" }, new ParameterCallbacks() {
                                        @Override
                                        public void callback(final String[] parameters) {
                                            Log.i("TAG", "parameters = " + parameters);
                                            showProgressBar("���ݼ�����...");
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
                                    showParameterDialog("����ID", new ParameterCallback() {
                                        @Override
                                        public void callback(final String musicId) {
                                            Log.i("TAG", "musicId = " + musicId);
                                            showProgressBar("���ݼ�����...");
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
                                    showParameterDialog("����ID", new ParameterCallback() {
                                        @Override
                                        public void callback(final String musicId) {
                                            Log.i("TAG", "musicId = " + musicId);
                                            showProgressBar("���ݼ�����...");
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
                                    showParameterDialog("����ID", new ParameterCallback() {
                                        @Override
                                        public void callback(final String singerId) {
                                            Log.i("TAG", "singerId = " + singerId);
                                            showProgressBar("���ݼ�����...");
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

        // �ǲ����û�һ����������
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
                                                    .setPositiveButton("ȷ��", null).show();
                                        }
                                    }
                                });
                    }
                });
            }
        });

        // ���ؼ���Աһ����������
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
                                                    .setPositiveButton("ȷ��", null).show();
                                        }
                                    }
                                });
                    }
                });
            }
        });

        // ���嶩��
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
                                            .setMessage(ret.toString()).setPositiveButton("ȷ��", null).show();
                                }
                            }
                        });
                    }
                });
            }
        });

        // ��������
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
                                                    .setPositiveButton("ȷ��", null).show();
                                        }

                                        Log.d(LOG_TAG, "vRing Download result is " + downloadResult);
                                    }
                                });
                    }
                });

            }
        });

        // ��ͨ��Ա
        Button openMem = (Button) this.findViewById(R.id.openMem);
        openMem.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                UserManagerInterface.openMember(CMMusicDemo.this, new CMMusicCallback<Result>() {
                    @Override
                    public void operationResult(Result ret) {
                        if (null != ret) {
                            new AlertDialog.Builder(CMMusicDemo.this).setTitle("openMember").setMessage(ret.toString())
                                    .setPositiveButton("ȷ��", null).show();
                        }
                    }
                });
            }
        });

        // ��ȡ���������ַ
        Button onlineLse = (Button) this.findViewById(R.id.onlineLse);
        onlineLse.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showParameterDialog(new String[] { "musicId", "codeRate" }, new ParameterCallbacks() {
                    @Override
                    public void callback(final String[] parameters) {
                        Log.i("TAG", "parameters = " + parameters);
                        showProgressBar("���ݼ�����...");
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

        // ��ȡ����������ַ
        Button crbtPrelisten = (Button) this.findViewById(R.id.crbtPrelisten);
        crbtPrelisten.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showParameterDialog("musicId", new ParameterCallback() {
                    @Override
                    public void callback(final String musicId) {
                        Log.i("TAG", "musicId = " + musicId);
                        showProgressBar("���ݼ�����...");
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

        // ��ѯ����������
        Button crbtBox = (Button) this.findViewById(R.id.crbtBox);
        crbtBox.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressBar("���ݼ�����...");
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

        // ����Ĭ������
        Button setDefaultCrbt = (Button) this.findViewById(R.id.setDefaultCrbt);
        setDefaultCrbt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showParameterDialog("crbtId", new ParameterCallback() {
                    @Override
                    public void callback(final String crbtId) {
                        Log.i("TAG", "crbtId = " + crbtId);
                        showProgressBar("���ݼ�����...");
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

        // �ֻ��Ų�ѯĬ������
        Button getDefaultCrbt = (Button) this.findViewById(R.id.getDefaultCrbt);
        getDefaultCrbt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showParameterDialog("msisdn", new ParameterCallback() {
                    @Override
                    public void callback(final String msisdn) {
                        Log.i("TAG", "msisdn = " + msisdn);
                        showProgressBar("���ݼ�����...");
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

        // ����������ַ
        Button ringPrelisten = (Button) this.findViewById(R.id.ringPrelisten);
        ringPrelisten.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showParameterDialog("musicId", new ParameterCallback() {
                    @Override
                    public void callback(final String musicId) {
                        Log.i("TAG", "musicId = " + musicId);
                        showProgressBar("���ݼ�����...");
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

        // ר������
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
                                                    .setMessage(result.toString()).setPositiveButton("ȷ��", null).show();
                                        }

                                        Log.d(LOG_TAG, "ret is " + result);
                                    }
                                });
                    }
                });
            }
        });

        // ���������Ƽ�
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
                                                    .setMessage(ret.toString()).setPositiveButton("ȷ��", null).show();
                                        }
                                    }
                                });
                    }
                });
            }

        });

        // �������ָ����Ƽ�
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
                                                    .setMessage(ret.toString()).setPositiveButton("ȷ��", null).show();
                                        }
                                    }
                                });
                    }
                });
            }

        });

        // ƫ�ø����Ƽ�
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
                                            .setMessage(ret.toString()).setPositiveButton("ȷ��", null).show();
                                }
                            }
                        });
            }
        });

        // �����û���Эͬ�����Ƽ�
        Button dealSongRecommendBtn = (Button) this.findViewById(R.id.sr_deal_song_recommend);
        dealSongRecommendBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressBar("���Ժ�...");
                SongRecommendManagerInterface.likeSongRecommend(CMMusicDemo.this,
                        new CMMusicCallback<SongRecommendResult>() {

                            @Override
                            public void operationResult(SongRecommendResult ret) {
                                if (null != ret) {
                                    new AlertDialog.Builder(CMMusicDemo.this).setTitle("contentIds")
                                            .setMessage(ret.toString()).setPositiveButton("ȷ��", null).show();
                                }
                            }
                        });
            }
        });

        // �޽���ӿ�
        Button noUIButton = (Button) this.findViewById(R.id.noUI);
        noUIButton.setOnClickListener(new OnClickListener() {
            String[] strs = new String[] { "���嶩�����޽��棩", "��ȡ������Ϣ", "���Ͳ��壨�޽��棩", "ר�����ζ������޽��棩", "��ȡר��ҵ����Ϣ",
                    "�ǲ����û�һ���������壨�޽��棩", "���ؼ���Աһ���������壨�޽��棩", "��ѯȫ�����ز���", "ȫ�����أ��޽��棩", "��ѯ�������ز���", "�������أ��޽��棩",
                    "CPר�����¶������޽��棩", "CPר�����尴�����أ��޽��棩", "CPר��ȫ���������أ��޽��棩", "���Ի�������¶������޽��棩", "�ǲ����û�һ���������Ի�������£��޽��棩",
                    "���Ի����嶩�����޽��棩", "���Բ�����¹�ϵ��ѯ", "���Ի��������ֺϳ�����", "���Ի����������ļ��ϴ�" };

            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(CMMusicDemo.this).setTitle("�޽���ӿ�")
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
                                                                                .setPositiveButton("ȷ��", null).show();
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
                                            showProgressBar("���ݼ�����...");
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
                                                                                .setPositiveButton("ȷ��", null).show();
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
                                                                                .setPositiveButton("ȷ��", null).show();
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
                                            showProgressBar("���ݼ�����...");
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
                                                                                .setPositiveButton("ȷ��", null).show();
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
                                                                                .setPositiveButton("ȷ��", null).show();
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
                                            showProgressBar("���ݼ�����...");
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
                                                                        .setPositiveButton("ȷ��", null).show();
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
                                            showProgressBar("���ݼ�����...");
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
                                                                        .setPositiveButton("ȷ��", null).show();
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
                                                                                .setPositiveButton("ȷ��", null).show();
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
                                                                                .setPositiveButton("ȷ��", null).show();
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
                                                                                .setPositiveButton("ȷ��", null).show();
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
                                                                        .setPositiveButton("ȷ��", null).show();
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
                                                                        .setPositiveButton("ȷ��", null).show();
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
                                                                        .setPositiveButton("ȷ��", null).show();
                                                            }
                                                        }
                                                    });
                                        }
                                    });

                                    break;

                                case 17:
                                    showProgressBar("���ݼ�����...");
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

                                                    showProgressBar("���ݼ�����...");
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
                    showProgressBar("���ݼ�����...");
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
        // ȫ������
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
                                                .setMessage(downloadResult.toString()).setPositiveButton("ȷ��", null)
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
            // Toast.makeText(CMMusicDemo.this, "�ѳ�ʼ����",
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
        new AlertDialog.Builder(CMMusicDemo.this).setTitle(title).setView(view).setMessage("���������:" + title)
                .setNegativeButton("ȡ��", null).setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {

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
        new AlertDialog.Builder(CMMusicDemo.this).setTitle(title).setView(view).setMessage("���������:" + title)
                .setNegativeButton("ȡ��", null).setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {

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

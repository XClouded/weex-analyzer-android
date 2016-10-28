package com.taobao.weex.analyzer.core;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;
import android.widget.Toast;

import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.analyzer.R;
import com.taobao.weex.analyzer.utils.SDKUtils;

import java.util.Locale;

/**
 * Description:
 * <p>
 * Created by rowandjj(chuyi)<br/>
 * Date: 2016/10/25<br/>
 * Time: 下午5:09<br/>
 */

public class RemoteDebugManager {

    private static RemoteDebugManager sManager;

    private boolean isEnabled = false;

    private static final String sRemoteUrlTemplate = "ws://%s:8088/debugProxy/native";
    private String mServerIP = null;


    private RemoteDebugManager() {
    }


    public static RemoteDebugManager getInstance() {
        if (sManager == null) {
            synchronized (RemoteDebugManager.class) {
                if (sManager == null) {
                    sManager = new RemoteDebugManager();
                }
            }
        }
        return sManager;
    }


    public void toggle(Context context) {
        try {
            isEnabled = !isEnabled;
            if (isEnabled) {
                startRemoteJSDebug(context);
            } else {
                stopRemoteJSDebug(context);
            }
        } catch (Exception e) {

        }
    }

    public void requestDebugServer(final Context context, final boolean autoStart) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final EditText editText = new EditText(context);

        if (mServerIP != null) {
            editText.setHint(mServerIP);
        } else {
            editText.setHint("127.0.0.1");
        }

        builder.setView(editText);
        builder.setTitle("Debug server ip configuration");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mServerIP = editText.getText().toString();
                if (autoStart) {
                    startRemoteJSDebug(context);
                }
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void startRemoteJSDebug(Context context) {
        if (SDKUtils.isEmulator()) {
            WXEnvironment.sRemoteDebugProxyUrl = String.format(Locale.CHINA, sRemoteUrlTemplate, "127.0.0.1");
        } else {
            if (mServerIP != null) {
                WXEnvironment.sRemoteDebugProxyUrl = String.format(Locale.CHINA, sRemoteUrlTemplate, mServerIP);
            } else {
                requestDebugServer(context, true);
                isEnabled = false;
                return;
            }
        }

        WXSDKEngine.reload();
        Toast.makeText(context, context.getString(R.string.wxt_opened), Toast.LENGTH_SHORT).show();
    }

    private void stopRemoteJSDebug(Context context) {
        //todo
        WXEnvironment.sRemoteDebugMode = false;
        WXEnvironment.sRemoteDebugProxyUrl = "";
        WXSDKEngine.reload();
        Toast.makeText(context, context.getString(R.string.wxt_closed), Toast.LENGTH_SHORT).show();

    }


}

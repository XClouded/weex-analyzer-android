package com.taobao.weex.analyzer.core;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import com.taobao.weex.WXSDKInstance;

import java.util.Locale;

/**
 * Description:
 * <p>
 * Created by rowandjj(chuyi)<br/>
 * Date: 2016/10/25<br/>
 * Time: 下午2:45<br/>
 */

public class JSExceptionCatcher {


    private JSExceptionCatcher() {
    }


    public static void catchException(@Nullable Context context, @Nullable WXSDKInstance instance, @Nullable String errCode, @Nullable String msg) {
        if (context == null) {
            return;
        }

        if(errCode == null && msg == null){
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("JS Exception");
        builder.setMessage(String.format(Locale.CHINA,"errorCode : %s\nerrorMsg : %s\n",TextUtils.isEmpty(errCode) ? "unknown" : errCode, TextUtils.isEmpty(msg) ? "unknown" : msg));
        builder.setPositiveButton("okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }


}

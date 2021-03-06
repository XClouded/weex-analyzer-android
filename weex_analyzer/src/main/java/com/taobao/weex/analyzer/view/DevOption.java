package com.taobao.weex.analyzer.view;

import android.support.annotation.DrawableRes;

/**
 * Description:
 * <p>
 * Created by rowandjj(chuyi)<br/>
 * Date: 2016/11/4<br/>
 * Time: 下午3:40<br/>
 */

public class DevOption {
    public String optionName;
    @DrawableRes public int iconRes;
    public OnOptionClickListener listener;
    public boolean isOverlayView;

    public DevOption(){
    }

    public DevOption(String optionName,int iconRes){
        this.optionName = optionName;
        this.iconRes = iconRes;
    }

    public DevOption(String optionName,int iconRes, OnOptionClickListener listener){
        this(optionName,iconRes);
        this.listener = listener;
    }

    public DevOption(String optionName, int iconRes, OnOptionClickListener listener, boolean isOverlayView) {
        this.optionName = optionName;
        this.iconRes = iconRes;
        this.listener = listener;
        this.isOverlayView = isOverlayView;
    }

    public interface OnOptionClickListener {
        void onOptionClick();
    }
}

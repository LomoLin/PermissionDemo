package com.lomo.permissiondemo;

import android.os.Handler;

/**
 * Created by Lomo on 2017/8/17.
 */

public class PermissionHandler extends Handler {
    public interface OnAuthorizedListener {
        void onSuccess();
        void onFail();
    }

    //是否授予权限
    private int mEnablePermission = 0;
    //指定一段时间执行操作
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (null != mListener) {
                if (mEnablePermission != 3) {
                    mListener.onFail();
                } else {
                    mListener.onSuccess();
                }
            }
        }
    };

    private OnAuthorizedListener mListener;

    /**
     * 点击需要授权的按钮
     */
    public void clicked() {
        mEnablePermission |= 1;
    }

    /**
     * 页面重新载入
     */
    public void resumed() {
        mEnablePermission = 0;
    }

    /**
     * 页面进入onPause方法
     */
    public void paused() {
        mEnablePermission |= 2;
    }

    public void setListener(OnAuthorizedListener listener) {
        mListener = listener;
    }

    /**
     * 执行判断操作
     *
     * @param millseconds
     */
    public void postMethod(int millseconds) {
        postDelayed(mRunnable, millseconds);
    }

    /**
     * 移除操作
     */
    public void removeCallback() {
        removeCallbacks(mRunnable);
    }
}

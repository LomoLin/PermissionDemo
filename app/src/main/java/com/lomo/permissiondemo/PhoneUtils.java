package com.lomo.permissiondemo;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * Created by Lomo on 2017/8/17.
 */

public class PhoneUtils {

    public static boolean hasSimCard(Context context) {
        boolean result = true;
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        int simState = telephonyManager.getSimState();
        switch (simState) {
            //没有SIM卡
            case TelephonyManager.SIM_STATE_ABSENT:
                result = false;
                break;
            case TelephonyManager.SIM_STATE_UNKNOWN:
                result = false;
                break;
        }

        return result;
    }
}

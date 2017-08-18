package com.lomo.permissiondemo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

    private PermissionHandler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnTest).setOnClickListener(this);
        mHandler = new PermissionHandler();
        mHandler.setListener(new PermissionHandler.OnAuthorizedListener() {
            @Override
            public void onSuccess() {
                //授权成功
            }

            @Override
            public void onFail() {
                //授权失败
                Toast.makeText(MainActivity.this, "请开启拨打电话的权限", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHandler.resumed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mHandler.paused();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallback();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnTest:
                mHandler.clicked();
                if (PhoneUtils.hasSimCard(MainActivity.this) || Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                    Uri uri = Uri.parse("tel:15008001234");
                    Intent intent = new Intent(Intent.ACTION_CALL, uri);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "请插入SIM卡", Toast.LENGTH_SHORT).show();
                }
                mHandler.postMethod(500);
                break;
        }
    }
}

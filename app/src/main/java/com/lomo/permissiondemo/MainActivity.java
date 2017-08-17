package com.lomo.permissiondemo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity implements View.OnClickListener {

    private PermissionHandler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHandler = new PermissionHandler();
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
                Uri uri = Uri.parse("tel:15008001234");
                Intent intent = new Intent(Intent.ACTION_CALL, uri);
                startActivity(intent);
                mHandler.postMethod(500);
                break;
        }
    }
}

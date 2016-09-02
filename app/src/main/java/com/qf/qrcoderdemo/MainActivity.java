package com.qf.qrcoderdemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.zxing.activity.CaptureActivity;
import com.zxing.util.QRcodeUtil;

public class MainActivity extends AppCompatActivity {

    private EditText et;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText) findViewById(R.id.et);
        iv = (ImageView) findViewById(R.id.iv);
    }

    public void btnClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                //生成二维码
                String str = et.getText().toString();
                Bitmap bitmap = QRcodeUtil.btnGenRawCode(this, str);
                iv.setImageBitmap(bitmap);
                break;
            case R.id.btn2:
                //扫一扫
                Intent intent = new Intent(this, CaptureActivity.class);
                startActivityForResult(intent, 0x001);
                break;
        }
    }

    /**
     * 获取扫码结果
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0x001 && resultCode == RESULT_OK) {
            String result = data.getStringExtra("result");
            Log.d("print", "扫码结果：" + result);
        }
    }
}

package com.android.s19110299;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE = "com.android.s19110299.extra.MESSAGE";
    private EditText txtTienGui;
    private EditText txtLaiSuat;
    private EditText txtKiHan;

    public static final int TEXT_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTienGui = findViewById(R.id.txttiengui);
        txtLaiSuat = findViewById(R.id.txtlaisuat);
        txtKiHan = findViewById(R.id.txtkihan);
        txtTienGui.requestFocus();

    }
    @Override
    public void onActivityResult(int requestCode,
                                 int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                String []reply = data.getStringArrayExtra(ResultActivity.EXTRA_REPLY);
                txtTienGui.setText(String.valueOf(reply[0]));
                txtLaiSuat.setText(String.valueOf(reply[1]));
                txtKiHan.setText(String.valueOf(reply[2]));
            }
        }
    }

    public void btnKetQua(View view) {
        //Log.d(LOG_TAG, "cl");
        Intent intent = new Intent(this, ResultActivity.class);
        if(TextUtils.isEmpty(txtTienGui.getText())){
            txtTienGui.setError( "Vui lòng điền vào trường này");
        }
        else if(TextUtils.isEmpty(txtLaiSuat.getText())){
            txtLaiSuat.setError( "Vui lòng điền vào trường này");
        }
        else if(TextUtils.isEmpty(txtKiHan.getText())){
            txtKiHan.setError( "Vui lòng điền vào trường này");
        }
        else{
            String Strmoneysent = getText(txtTienGui);
            String Strinterest_rate = getText(txtLaiSuat);
            String Strperiod = getText(txtKiHan);
            intent.putExtra(EXTRA_MESSAGE, new String[]{Strmoneysent,Strinterest_rate,Strperiod});
            //startActivity(intent);
            startActivityForResult(intent, TEXT_REQUEST);
        }
    }
    private static String getText(EditText opEditText) {
        return opEditText.getText().toString();
    }
}
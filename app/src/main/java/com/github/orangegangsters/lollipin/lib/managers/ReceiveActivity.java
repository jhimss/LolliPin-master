package com.github.orangegangsters.lollipin.lib.managers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.orangegangsters.lollipin.lib.R;
import com.github.orangegangsters.lollipin.lib.barcode.BarcodeCaptureActivity;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

public class ReceiveActivity extends Activity implements View.OnClickListener {

    private TextView mToolbarText;
    private ImageView mBack;
    private EditText scanResult;
    private ImageView scan;
    private ImageView copyScanResult;

    private static final String LOG_TAG = ReceiveActivity.class.getSimpleName();
    private static final int BARCODE_READER_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);

        initView();
    }

    private void initView() {

        mToolbarText = (TextView) findViewById(R.id.toolbar_title);
        mToolbarText.setText("RECEIVE");
        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(this);
        scan = (ImageView) findViewById(R.id.scan);
        scan.setOnClickListener(this);
        scanResult=(EditText)findViewById(R.id.push_button);
        copyScanResult=(ImageView)findViewById(R.id.copy);
        copyScanResult.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.back) {
            onBackPressed();
        }
        else if(i == R.id.scan){
            Intent intent = new Intent(getApplicationContext(), BarcodeCaptureActivity.class);
            startActivityForResult(intent, BARCODE_READER_REQUEST_CODE);
        }
        else if(i == R.id.copy){

                String scanCopy = scanResult.getText().toString();
               if( scanCopy != null && !scanCopy.toString().isEmpty()){
                   copyToClipboard(scanCopy);
            }
            else{
                   Toast.makeText(this, "Scan Code", Toast.LENGTH_SHORT).show();
               }


        }
    }

    private void copyToClipboard(String scanCopy) {
        int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager)
                    getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(scanCopy);
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager)
                    getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData
                    .newPlainText("Scanned", scanCopy);
            clipboard.setPrimaryClip(clip);
        }
        Toast toast = Toast.makeText(getApplicationContext(),
                "Copied", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM | Gravity.RIGHT, 50, 50);
        toast.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BARCODE_READER_REQUEST_CODE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    Barcode barcode = data.getParcelableExtra(BarcodeCaptureActivity.BarcodeObject);
                    Point[] p = barcode.cornerPoints;
                    scanResult.setText(barcode.displayValue);
                } else scanResult.setText(R.string.no_barcode_captured);
            } else Log.e(LOG_TAG, String.format(getString(R.string.barcode_error_format),
                    CommonStatusCodes.getStatusCodeString(resultCode)));
        } else super.onActivityResult(requestCode, resultCode, data);
    }
}



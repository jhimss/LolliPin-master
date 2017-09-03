package com.github.orangegangsters.lollipin.lib.managers;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.orangegangsters.lollipin.lib.R;
import com.github.orangegangsters.lollipin.lib.enums.KeyboardButtonEnum;
import com.github.orangegangsters.lollipin.lib.interfaces.KeyboardButtonClickedListener;
import com.github.orangegangsters.lollipin.lib.views.KeyboardView;

/**
 * Created by Himanshu on 02-09-2017.
 */

public class SendActivity extends Activity implements KeyboardButtonClickedListener, View.OnClickListener {

    public static final String TAG = EnterPinActivity.class.getSimpleName();
    protected KeyboardView mKeyboardView;
    protected ImageView mForwardImageView;
    protected String mPinCode;protected String mOldPinCode;
    private TextView mToolbarText;
    private ImageView mBack;

    /**
     * First creation
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getContentView());
        initLayout(getIntent());
    }

    /**
     * If called in singleTop mode
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        initLayout(intent);
    }

    private void initLayout(Intent intent) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD_MR1) {
            //Animate if greater than 2.3.3
            overridePendingTransition(R.anim.nothing, R.anim.nothing);
        }


        mPinCode = "";
        mOldPinCode = "";
        mKeyboardView = (KeyboardView) this.findViewById(R.id.pin_code_keyboard_view);
        mKeyboardView.setKeyboardButtonClickedListener(this);
        mForwardImageView=(ImageView)this.findViewById(R.id.pin_code_fingerprint_imageview);
        mForwardImageView.setOnClickListener(this);
        mToolbarText=(TextView)findViewById(R.id.toolbar_title);
        mToolbarText.setText("SEND");
        mBack=(ImageView)findViewById(R.id.back);
        mBack.setOnClickListener(this);

    }


    @Override
    public void onKeyboardClick(KeyboardButtonEnum keyboardButtonEnum) {
            int value = keyboardButtonEnum.getButtonValue();

            if (value == KeyboardButtonEnum.BUTTON_CLEAR.getButtonValue()) {
                if (!mPinCode.isEmpty()) {
                    setPinCode(mPinCode.substring(0, mPinCode.length() - 1));
                } else {
                    setPinCode("");
                }
            } else {
                setPinCode(mPinCode + value);
            }
    }

    @Override
    public void onRippleAnimationEnd() {

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    public void setPinCode(String pinCode) {
        mPinCode = pinCode;
    }



    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.back)
            onBackPressed();
    }
    public int getContentView() {
        return R.layout.layout_calculation;
    }


}


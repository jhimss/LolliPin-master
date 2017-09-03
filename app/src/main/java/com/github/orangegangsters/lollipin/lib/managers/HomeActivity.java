package com.github.orangegangsters.lollipin.lib.managers;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.github.orangegangsters.lollipin.lib.adapter.ListAdapter;
import com.github.orangegangsters.lollipin.lib.R;
import com.github.orangegangsters.lollipin.lib.bean.TransactionBean;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends Activity implements View.OnClickListener {
    private LinearLayout send, receive, buy;
    private SlidingUpPanelLayout mLayout;
    ListView mTransactionList;
    ListAdapter adapter;
    public ProgressBar p,p1,p2,p3,p4,p5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        toolbar.setTitle("LedgerEX");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.black));
        toolbar.setNavigationIcon(R.drawable.menuicon);

        //views

        send = (LinearLayout) findViewById(R.id.send);
        send.setOnClickListener(this);
        receive = (LinearLayout) findViewById(R.id.receive);
        receive.setOnClickListener(this);
        buy = (LinearLayout) findViewById(R.id.sell);
        buy.setOnClickListener(this);

        mTransactionList = (ListView) findViewById(R.id.list);
        setValuesToListView();

        p=(ProgressBar)findViewById(R.id.progressBar0);
        p1=(ProgressBar)findViewById(R.id.progressBar2);
        p2=(ProgressBar)findViewById(R.id.progressBar3);
        p3=(ProgressBar)findViewById(R.id.progressBar4);
        p4=(ProgressBar)findViewById(R.id.progressBar5);

        p.setProgress(90);
        p1.setProgress(40);
        p2.setProgress(50);
        p3.setProgress(5);
        p4.setProgress(10);


        p1.getIndeterminateDrawable().setColorFilter(Color.parseColor("#0D5080"),
                android.graphics.PorterDuff.Mode.MULTIPLY);

    }

    private void setValuesToListView() {
        List<TransactionBean> beanList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            TransactionBean profileBean = new TransactionBean();
            profileBean.date = "28 July 2017  |  8:36 AM ";
            profileBean.rates = "0.084316    ETH";
            beanList.add(profileBean);
        }
        adapter = new ListAdapter(this, beanList, 11);
        mTransactionList.setAdapter(adapter);
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.send) {

            Intent splash_intent = new Intent(this, SendActivity.class);
            splash_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(splash_intent);

        } else if (i == R.id.receive) {

            Intent splash_intent = new Intent(this, ReceiveActivity.class);
            splash_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(splash_intent);
        }
    }
}

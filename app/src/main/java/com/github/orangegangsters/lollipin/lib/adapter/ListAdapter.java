package com.github.orangegangsters.lollipin.lib.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.github.orangegangsters.lollipin.lib.R;
import com.github.orangegangsters.lollipin.lib.bean.TransactionBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MURARI on 02-09-2017.
 */

public class ListAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    private int mIntnt;
    private List<TransactionBean> mHistory = new ArrayList<TransactionBean>() ;

    public ListAdapter(Context context, List<TransactionBean> mHistory, int i){
        this.context=context;
        this.mIntnt=i;
        this.mHistory=mHistory;
    }
    @Override
    public int getCount() {
        return mHistory.size();
    }

    @Override
    public Object getItem(int position) {
        return mHistory.get(position);
    }

    @Override
    public long
    getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(inflater==null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.transaction_raw, null);

        TextView date=(TextView)convertView.findViewById(R.id.date);
        TextView rate=(TextView)convertView.findViewById(R.id.rate);

        TransactionBean n=mHistory.get(position);
        date.setText(n.date);
        rate.setText(n.rates);
        return convertView;
    }
}
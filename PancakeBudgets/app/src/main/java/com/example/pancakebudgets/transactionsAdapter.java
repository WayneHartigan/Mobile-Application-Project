package com.example.pancakebudgets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class transactionsAdapter extends BaseAdapter {

    LayoutInflater mInflater;
    String[] transactionNames;
    String[] transactionPrices;
    String[] transactionDates;
    String[] transactionCat;

    public transactionsAdapter(Context c, String[] name, String[] price, String[] date, String[] cat){
        transactionNames = name;
        transactionPrices = price;
        transactionDates = date;
        transactionCat = cat;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return transactionNames.length;
    }

    @Override
    public Object getItem(int position) {
        return transactionNames[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = mInflater.inflate(R.layout.transactions_detail, null);
        TextView transactionNamesTV = (TextView) v.findViewById(R.id.transactionNamesTV);
        TextView transactionDatesTV = (TextView) v.findViewById(R.id.transactionDatesTV);
        TextView transactionCatTV = (TextView) v.findViewById(R.id.transactionCatTV);
        TextView transactionPriceTV = (TextView) v.findViewById(R.id.transactionPriceTV);

        String name = transactionNames[position];
        String price = transactionPrices[position];
        String date = transactionDates[position];
        String cat = transactionCat[position];
        String priceEuro = "€" + price;

        transactionNamesTV.setText(name);
        transactionDatesTV.setText(date);
        transactionCatTV.setText(cat);
        transactionPriceTV.setText(priceEuro);

        return v;
    }
}

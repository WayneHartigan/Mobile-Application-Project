package com.example.pancakebudgets;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class TransactionList extends ArrayAdapter<Transaction> {
    private Activity context;
    private List<Transaction> transactionList;

    public TransactionList(Activity context, List<Transaction> transactionList){
        super(context, R.layout.transactions_detail, transactionList);
        this.context = context;
        this.transactionList = transactionList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.transactions_detail, null, true);
        TextView transactionNamesTV = (TextView) listViewItem.findViewById(R.id.transactionNamesTV);
        TextView transactionDatesTV = (TextView) listViewItem.findViewById(R.id.transactionDatesTV);
        TextView transactionCatTV = (TextView) listViewItem.findViewById(R.id.transactionCatTV);
        TextView transactionPriceTV = (TextView) listViewItem.findViewById(R.id.transactionPriceTV);

        Transaction transaction = transactionList.get(position);

        transactionNamesTV.setText(transaction.getTransactionName());
        transactionDatesTV.setText(transaction.getTransactionDate());
        transactionCatTV.setText(transaction.getTransactionCategory());
        transactionPriceTV.setText(transaction.getTransactionPrice());

        return listViewItem;
    }
}

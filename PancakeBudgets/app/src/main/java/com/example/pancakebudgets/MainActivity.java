package com.example.pancakebudgets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ListView transListView;
    String[] transactionNames;
    String[] transactionPrices;
    String[] transactionDates;
    String[] transactionCat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        transListView = (ListView) findViewById(R.id.transListView);
        transactionNames = res.getStringArray(R.array.transactionNames);
        transactionPrices = res.getStringArray(R.array.transactionPrices);
        transactionDates = res.getStringArray(R.array.transactionDates);
        transactionCat = res.getStringArray(R.array.transactionCat);

        transactionsAdapter transactionsAdapter = new transactionsAdapter(this,
                transactionNames,
                transactionPrices,
                transactionDates,
                transactionCat);

        transListView.setAdapter(transactionsAdapter);



        Context context = getApplicationContext();
        CharSequence text = "Click balance to change currency";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();


        Button addTransBtn = (Button) findViewById(R.id.addTransBtn);
        Button addFundsBtn = (Button) findViewById(R.id.addFundsBtn);
        TextView balanceDisplay = (TextView) findViewById(R.id.balanceDisplay);
        ListView transListView = (ListView) findViewById(R.id.transListView);

        addTransBtn.setOnClickListener(this);
        addFundsBtn.setOnClickListener(this);
        balanceDisplay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addTransBtn:
                Intent addTrans = new Intent(this, addTrans.class);
                startActivity(addTrans);
                break;

            case R.id.addFundsBtn:
                Intent addFunds = new Intent(this, addFunds.class);
                startActivity(addFunds);
                break;

            case R.id.balanceDisplay:
                TextView balanceDisplay = (TextView) findViewById(R.id.balanceDisplay);
                String balanceDisString = balanceDisplay.getText().toString();
                if (balanceDisString.contains("€")){
                    String numString = balanceDisString.substring(1);
                    double balance = Double.parseDouble(numString);
                    double exchangeRate = 0.86/1;
                    double newBalance = balance * exchangeRate;
                    String output = "£" + String.format("%.2f", newBalance);
                    balanceDisplay.setText(output);
                }
                else if (balanceDisString.contains("£")){
                    String numString = balanceDisString.substring(1);
                    double balance = Double.parseDouble(numString);
                    double exchangeRate = 1.28/1;
                    double newBalance = balance * exchangeRate;
                    String output = "$" + String.format("%.2f", newBalance);
                    balanceDisplay.setText(output);
                }
                else if (balanceDisString.contains("$")){
                    String numString = balanceDisString.substring(1);
                    double balance = Double.parseDouble(numString);
                    double exchangeRate1 = 1/1.28;
                    double exchangeRate2 = 1/0.86;
                    double backToPound = balance * exchangeRate1;
                    double newBalance = backToPound * exchangeRate2;
                    String output = "€" + String.format("%.2f", newBalance);
                    balanceDisplay.setText(output);
                }
                break;
        }
    }
}

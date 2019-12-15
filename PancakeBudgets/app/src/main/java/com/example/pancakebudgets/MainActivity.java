package com.example.pancakebudgets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity{

    ListView transListView;

    List<Transaction> transactionList;

    DatabaseReference databaseTransaction;
    DatabaseReference databaseBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_main);


        databaseTransaction = FirebaseDatabase.getInstance().getReference("transaction");
        databaseBalance = FirebaseDatabase.getInstance().getReference("balance");

        transListView = (ListView) findViewById(R.id.transListView);

        transactionList = new ArrayList<>();

        Context context = getApplicationContext();
        CharSequence text = "Click balance to change currency";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        TextView languageBtn = (TextView) findViewById(R.id.languageBtn);
        Button addTransBtn = (Button) findViewById(R.id.addTransBtn);
        Button addFundsBtn = (Button) findViewById(R.id.addFundsBtn);
        TextView balanceDisplay = (TextView) findViewById(R.id.balanceDisplay);

        languageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangeLanguageDialog();
            }
        });

        addTransBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transView();
            }
        });

        addFundsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fundsView();
            }
        });

        balanceDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeCurrency();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseTransaction.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                transactionList.clear();

                for(DataSnapshot transSnapshot : dataSnapshot.getChildren()){
                    Transaction transaction = transSnapshot.getValue(Transaction.class);

                    transactionList.add(transaction);

                }

                TransactionList adapter = new TransactionList(MainActivity.this, transactionList);
                transListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseBalance.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    String balance = dataSnapshot.getValue(String.class);
                    dataSnapshot.getKey();
                    double balanceDouble = Double.parseDouble(balance);
                    TextView balanceDisplay = (TextView) findViewById(R.id.balanceDisplay);
                    balanceDisplay.setText("€" + String.format("%.2f", balanceDouble));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void transView(){
        Intent addTrans = new Intent(this, addTrans.class);
        startActivity(addTrans);
    }

    private void fundsView(){
        Intent addFunds = new Intent(this, addFunds.class);
        startActivity(addFunds);
    }

    private void changeCurrency(){
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
    }

    private void showChangeLanguageDialog(){
        final String[] languageItems = {"English", "Polish"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        mBuilder.setTitle("Choose Language...");
        mBuilder.setSingleChoiceItems(languageItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (i == 0){
                    setLocale("en");
                    recreate();
                }
                else if (i == 1){
                    setLocale("pl");
                    recreate();
                }
            }
        });

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }

    private void setLocale(String lang){
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();
    }

    private void loadLocale(){
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang", "");
        setLocale(language);
    }

}

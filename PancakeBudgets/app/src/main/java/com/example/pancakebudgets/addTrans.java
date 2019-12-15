package com.example.pancakebudgets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addTrans extends AppCompatActivity{

    EditText transactionNameET;
    EditText transactionDateET;
    EditText transactionPriceET;
    Spinner categorySpinner;
    Button submitTrans;
    Button cancelTransBtn;

    DatabaseReference databaseTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trans);

        databaseTransaction = FirebaseDatabase.getInstance().getReference("transaction");


        transactionNameET = (EditText) findViewById(R.id.transactionNameET);
        transactionDateET = (EditText) findViewById(R.id.transactionDateET);
        transactionPriceET = (EditText) findViewById(R.id.transactionPriceET);
        categorySpinner = (Spinner) findViewById(R.id.categorySpinner);
        submitTrans = (Button) findViewById(R.id.newTransBtn);
        cancelTransBtn = (Button) findViewById(R.id.cancelTransBtn);

        submitTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTransaction();
            }
        });
        cancelTransBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelTransaction();
            }
        });
    }

    private void addTransaction(){
        String transName = transactionNameET.getText().toString().trim();
        String transDate = transactionDateET.getText().toString().trim();
        String transPrice = transactionPriceET.getText().toString().trim();
        String transCat = categorySpinner.getSelectedItem().toString();
        if (!TextUtils.isEmpty(transPrice)){
           String transId = databaseTransaction.push().getKey();
           Transaction trans = new Transaction(transId, transName, transDate, transPrice, transCat);
           databaseTransaction.child(transId).setValue(trans);

           Toast.makeText(this, "Transaction Added!", Toast.LENGTH_LONG).show();
           Intent backToHome = new Intent(this, MainActivity.class);
           startActivity(backToHome);

        }
        else{
            Toast.makeText(this, "You need to enter a price!", Toast.LENGTH_LONG).show();
        }

    }
    private void cancelTransaction(){
        Intent cancelTrans = new Intent(this, MainActivity.class);
        startActivity(cancelTrans);
    }

}

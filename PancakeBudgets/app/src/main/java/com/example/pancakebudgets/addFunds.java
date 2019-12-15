package com.example.pancakebudgets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class addFunds extends AppCompatActivity{

    EditText newBalance;
    DatabaseReference databaseBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_funds);

        databaseBalance = FirebaseDatabase.getInstance().getReference("balance");

        newBalance = (EditText) findViewById(R.id.newBalance);
        Button newBalanceBtn = (Button) findViewById(R.id.newBalanceBtn);
        final Button cancelBalanceBtn = (Button) findViewById(R.id.cancelBalanceBtn);

        newBalanceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBalance();
            }
        });
        cancelBalanceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelBalance();
            }
        });
    }

    private void addBalance(){
        String newBalanceTotal = newBalance.getText().toString().trim();

        if (!TextUtils.isEmpty(newBalanceTotal)){
            databaseBalance.setValue(newBalanceTotal);
            Toast.makeText(this, "Funds Added!", Toast.LENGTH_LONG).show();
            Intent backToHome = new Intent(this, MainActivity.class);
            startActivity(backToHome);
        }
        else{
            Toast.makeText(this, "Add Balance Field Empty!", Toast.LENGTH_LONG).show();
        }

    }

    private void cancelBalance(){
        Intent backToHome = new Intent(this, MainActivity.class);
        startActivity(backToHome);
    }



}

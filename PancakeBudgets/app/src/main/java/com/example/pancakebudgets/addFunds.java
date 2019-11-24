package com.example.pancakebudgets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class addFunds extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_funds);

        Button newBalanceBtn = (Button) findViewById(R.id.newBalanceBtn);
        Button cancelBalanceBtn = (Button) findViewById(R.id.cancelBalanceBtn);

        newBalanceBtn.setOnClickListener(this);
        cancelBalanceBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.newBalanceBtn:
                Intent backToHome = new Intent(this, MainActivity.class);
                startActivity(backToHome);
                break;

            case R.id.cancelBalanceBtn:
                Intent cancelTrans = new Intent(this, MainActivity.class);
                startActivity(cancelTrans);
                break;
        }

    }
}

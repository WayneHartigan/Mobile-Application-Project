package com.example.pancakebudgets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class addTrans extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trans);

        Button submitTrans = (Button) findViewById(R.id.newTransBtn);
        Button cancelTransBtn = (Button) findViewById(R.id.cancelTransBtn);

        submitTrans.setOnClickListener(this);
        cancelTransBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.newTransBtn:
                Intent backToHome = new Intent(this, MainActivity.class);
                startActivity(backToHome);
                break;

            case R.id.cancelTransBtn:
                Intent cancelTrans = new Intent(this, MainActivity.class);
                startActivity(cancelTrans);
                break;
        }

    }
}

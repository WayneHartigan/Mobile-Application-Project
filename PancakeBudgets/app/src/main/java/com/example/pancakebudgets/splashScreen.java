package com.example.pancakebudgets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class splashScreen extends AppCompatActivity {

    Animation from_bottom;
    Animation from_top;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread myThread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(1750);
                    Intent main_screen = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(main_screen);
                    finish();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        myThread.start();

        TextView splashScreenText = (TextView) findViewById(R.id.splashScreenText);
        ImageView splashScreenImage = (ImageView) findViewById(R.id.splashScreenImage);
        from_bottom = AnimationUtils.loadAnimation(this, R.anim.from_bottom);
        from_top = AnimationUtils.loadAnimation(this, R.anim.from_top);

        splashScreenText.setAnimation(from_bottom);
        splashScreenImage.setAnimation(from_top);
    }
}

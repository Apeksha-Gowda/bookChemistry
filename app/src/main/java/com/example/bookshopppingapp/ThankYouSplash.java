package com.example.bookshopppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class ThankYouSplash extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 3000;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_thankyou);

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(ThankYouSplash.this,LoginActivity.class);
                ThankYouSplash.this.startActivity(mainIntent);
                ThankYouSplash.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}

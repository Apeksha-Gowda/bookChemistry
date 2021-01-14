package com.example.bookshopppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;


public class MenuActivity extends AppCompatActivity {

    CarouselView carouselView;
    Button comic,mystery,horror,fairyTale;
    int[] sampleImages = {R.drawable.banner1, R.drawable.banner2, R.drawable.banner3};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        carouselView = findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);

        comic = (Button) findViewById(R.id.comic);
        mystery = (Button) findViewById(R.id.mystery);
        horror = (Button) findViewById(R.id.horror);
        fairyTale = (Button) findViewById(R.id.fairytale);

        comic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ProductMainActivity.class);
                startActivity(intent);
            }
        });
        mystery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MysteryProducts.class);
                startActivity(intent);
            }
        });
        horror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HorrorProducts.class);
                startActivity(intent);
            }
        });
        fairyTale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FairyTaleProducts.class);
                startActivity(intent);
            }
        });

    }
    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };

}

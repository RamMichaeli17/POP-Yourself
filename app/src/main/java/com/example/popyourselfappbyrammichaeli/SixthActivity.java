package com.example.popyourselfappbyrammichaeli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class SixthActivity extends AppCompatActivity {
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixth);
        ratingBar= findViewById(R.id.rating_bar);
        Button create = findViewById(R.id.create_another);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if(ratingBar.getRating()<4)
                    Toast.makeText(SixthActivity.this, getString(R.string.low_rating), Toast.LENGTH_SHORT).show();
                else if(ratingBar.getRating()>=4)
                    Toast.makeText(SixthActivity.this, getString(R.string.high_rating), Toast.LENGTH_SHORT).show();


            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SixthActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}
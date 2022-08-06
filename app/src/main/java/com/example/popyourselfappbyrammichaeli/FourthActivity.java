package com.example.popyourselfappbyrammichaeli;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.OutputStream;
import java.util.Objects;

public class FourthActivity extends AppCompatActivity {
    EditText name_of_pop;
    boolean clicked = false;
    int prevClick = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        RelativeLayout relativeLayout = findViewById(R.id.pop_layout);

        Button saveBtn = findViewById(R.id.save);

        name_of_pop = findViewById(R.id.name_of_pop_input);
        Button submitBtn = findViewById(R.id.submit_btn);
        TextView output_name_of_pop = findViewById(R.id.name_of_pop_output);

        GridLayout gridLayout = findViewById(R.id.grid);
        TableRow tableRow = findViewById(R.id.table_row);

        TextView general = findViewById(R.id.txt_view_2);
        HorizontalScrollView horizontalScrollView = findViewById(R.id.main_scroll_view);


        Button clearBtn = findViewById(R.id.clear_btn);
        Button finishBtn = findViewById(R.id.finish_btn);

        ImageButton imageButton1 = findViewById(R.id.first);
        ImageButton imageButton2 = findViewById(R.id.second);
        ImageButton imageButton3 = findViewById(R.id.third);
        ImageButton imageButton4 = findViewById(R.id.forth);

        ImageView imageView_eyebrows = findViewById(R.id.female_finish1);
        ImageView imageView_hair = findViewById(R.id.female_finish2);
        ImageView imageView_shirt = findViewById(R.id.female_finish3);
        ImageView imageView_pants = findViewById(R.id.female_finish4);
        ImageView imageView_accessory = findViewById(R.id.female_finish5);



        for (int i=0;i<gridLayout.getChildCount();i++) {

            CardView cardView = (CardView) gridLayout.getChildAt(i);
            int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LinearLayout linearLayout = (LinearLayout) cardView.getChildAt(0);
                    String item = ((TextView) linearLayout.getChildAt(1)).getText().toString();
                    general.setText(item);
                    if (!clicked) {
                        output_name_of_pop.setVisibility(View.GONE);
                        general.setVisibility(View.VISIBLE);
                        horizontalScrollView.setVisibility(View.VISIBLE);
                        clicked = true;
                        prevClick = finalI;
                    } else if (clicked && prevClick!=finalI) {
                        output_name_of_pop.setVisibility(View.GONE);
                        general.setVisibility(View.VISIBLE);
                        horizontalScrollView.setVisibility(View.VISIBLE);
                        prevClick = finalI;
                    }
                    else {
                        general.setVisibility(View.GONE);
                        horizontalScrollView.setVisibility(View.GONE);
                        clicked = false;
                    }

                    switch (item) {
                        case "Hair":
                        case "שיער":
                            imageButton1.setImageResource(R.drawable.female_hair_med11_black2_01);
                            imageButton2.setImageResource(R.drawable.female_hair_med2_black2_front);
                            imageButton3.setImageResource(R.drawable.female_hair_long3_black2_front_01);
                            imageButton4.setImageResource(R.drawable.female_hair_long1_black2_01);
                            break;
                        case "Eyebrows":
                        case "גבות":
                            imageButton1.setImageResource(R.drawable.female_brows1_black_01);
                            imageButton2.setImageResource(R.drawable.female_brows4_black_01);
                            imageButton3.setImageResource(R.drawable.female_brows5_black_01);
                            imageButton4.setImageResource(R.drawable.female_brows7_black_01);
                            break;
                        case "Shirt":
                        case "חולצה":
                            imageButton1.setImageResource(R.drawable.female_top_leatherjacket_white_a_01);
                            imageButton2.setImageResource(R.drawable.female_top_hoodie_green_a_01);
                            imageButton3.setImageResource(R.drawable.female_top_buttondown_denim_a_01);
                            imageButton4.setImageResource(R.drawable.female_top_tshirt_freddyblueprint_black_a_01);
                            break;
                        case "Pants":
                        case "מכנסיים":
                            imageButton1.setImageResource(R.drawable.female_bottom_skirt1_pink_a_01);
                            imageButton2.setImageResource(R.drawable.female_bottom_leggings2_black_a_01);
                            imageButton3.setImageResource(R.drawable.female_bottom_jeans5_blackwhite_a_01);
                            imageButton4.setImageResource(R.drawable.female_bottom_jeans1_a_01);
                            break;
                        case "Accessory":
                        case "אביזר":
                            imageButton1.setImageResource(R.drawable.glasses_3_blue_01);
                            imageButton2.setImageResource(R.drawable.glasses_2_gold_01);
                            imageButton3.setImageResource(R.drawable.flower_01);
                            imageButton4.setImageResource(R.drawable.f_fringe_scarf_green);
                            break;
                    }

                }
            });
        }

        for (int i=0;i<tableRow.getChildCount();i++) {
            ImageButton imageButton = (ImageButton) tableRow.getChildAt(i);
            int finalI = i;
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (general.getText().toString()) {
                        case "Hair":
                        case "שיער":
                            imageView_hair.setImageDrawable(imageButton.getDrawable());
                            imageView_hair.setVisibility(View.VISIBLE);
                            break;
                        case "Eyebrows":
                        case "גבות":
                            imageView_eyebrows.setImageDrawable(imageButton.getDrawable());
                            imageView_eyebrows.setVisibility(View.VISIBLE);
                            break;
                        case "Shirt":
                        case "חולצה":
                            imageView_shirt.setImageDrawable(imageButton.getDrawable());
                            imageView_shirt.setVisibility(View.VISIBLE);
                            break;
                        case "Pants":
                        case "מכנסיים":
                            imageView_pants.setImageDrawable(imageButton.getDrawable());
                            imageView_pants.setVisibility(View.VISIBLE);
                            break;
                        case "Accessory":
                        case "אביזר":
                            imageView_accessory.setImageDrawable(imageButton.getDrawable());
                            imageView_accessory.setVisibility(View.VISIBLE);
                            break;
                    }
                }
            });
        }

        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FourthActivity.this,FifthActivity.class);
                startActivity(intent);
            }
        });


        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                output_name_of_pop.setVisibility(View.GONE);
                gridLayout.setVisibility(View.VISIBLE);
                imageView_eyebrows.setVisibility(View.GONE);
                imageView_hair.setVisibility(View.GONE);
                imageView_shirt.setVisibility(View.GONE);
                imageView_pants.setVisibility(View.GONE);
                imageView_accessory.setVisibility(View.GONE);
            }
        });
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = name_of_pop.getText().toString();
                output_name_of_pop.setVisibility(View.VISIBLE);
                output_name_of_pop.setText(name);
                horizontalScrollView.setVisibility(View.GONE);
                general.setVisibility(View.GONE);
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                gridLayout.setVisibility(View.GONE);
                horizontalScrollView.setVisibility(View.GONE);
                general.setVisibility(View.GONE);
                output_name_of_pop.setVisibility(View.VISIBLE);
                relativeLayout.setBackground(getDrawable(R.drawable.wallpar234));
                relativeLayout.setDrawingCacheEnabled(true);
                relativeLayout.buildDrawingCache();
                Bitmap bitmap = relativeLayout.getDrawingCache();
                saveImage(bitmap);
                relativeLayout.setBackground(null);
                gridLayout.setVisibility(View.VISIBLE);
                Toast.makeText(FourthActivity.this, getString(R.string.saved_successfully), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void saveImage(Bitmap bitmap) {
        OutputStream fos;
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                ContentResolver resolver = getContentResolver();
                ContentValues contentValues = new ContentValues();
                contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, "Image" + ".jpg");
                contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg");
                Uri imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                fos = resolver.openOutputStream(Objects.requireNonNull(imageUri));
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                Objects.requireNonNull(fos);
            }
        } catch (Exception e) {
            Log.d("error", e.toString());
        }
    }

}
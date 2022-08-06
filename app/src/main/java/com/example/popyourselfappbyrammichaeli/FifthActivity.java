package com.example.popyourselfappbyrammichaeli;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.InputType;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;


public class FifthActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private CheckBox checkBox;
    String theInput;
    LinearLayout linearLayout;
    int spinnerValue=0;
    RadioGroup radioGroup;
    RadioButton radioButton1;
    RadioButton radioButton2;
    EditText addressEditText;
    TextView selfTextView;

    AwesomeValidation awesomeValidation;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);

        LoadingDialog loadingDialog = new LoadingDialog(FifthActivity.this);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this,R.id.name_ed, RegexTemplate.NOT_EMPTY,R.string.name_wrong);
        awesomeValidation.addValidation(this,R.id.phone_ed,"05{1}[0-9]{8}$",R.string.phone_wrong);
        awesomeValidation.addValidation(this,R.id.email_ed, Patterns.EMAIL_ADDRESS,R.string.email_wrong);
        awesomeValidation.addValidation(this,R.id.address,RegexTemplate.NOT_EMPTY,R.string.name_wrong);
        awesomeValidation.addValidation(this,R.id.card_ed,RegexTemplate.NOT_EMPTY,R.string.card_wrong);





        Button button = findViewById(R.id.submit_btn);

        radioGroup = findViewById(R.id.radio_group);
        radioButton1 = findViewById(R.id.radio_one);
        radioButton2 = findViewById(R.id.radio_two);
        addressEditText = findViewById(R.id.address);
        selfTextView = findViewById(R.id.self_pickup);

        checkBox = findViewById(R.id.check_box_mails);

        Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.numbers, R.layout.my_selected_item);
        adapter.setDropDownViewResource(R.layout.my_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        linearLayout = findViewById(R.id.mails_layout);

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked())
                    spinner.setVisibility(View.VISIBLE);
                else {
                    spinner.setVisibility(View.GONE);
                    linearLayout.removeAllViews();

                }


            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(awesomeValidation.validate()) {
                    if (radioButton1.isChecked() || radioButton2.isChecked()) {
                        loadingDialog.startLoadingAnimation();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                loadingDialog.dismissDialog();
                            }
                        },1000);
                        Intent intent = new Intent(FifthActivity.this, SixthActivity.class);
                        startActivity(intent);
                    } else
                        Toast.makeText(FifthActivity.this, getString(R.string.choose_way), Toast.LENGTH_SHORT).show();
                }

            }
        });



    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if ((position > 0) && (position < 4)) {
            Toast.makeText(parent.getContext(), position+"", Toast.LENGTH_SHORT).show();
            spinnerValue=position;
            addToLayout();
        }
        else if (position == 4) {

            AlertDialog.Builder alert= new AlertDialog.Builder(this);

            final EditText edittext = new EditText(this);
            alert.setView(edittext);
            edittext.setInputType(InputType.TYPE_CLASS_NUMBER);
            //alert.setMessage(getString(R.string.alert_topic));
            alert.setTitle(getString(R.string.alert_topic));



            alert.setPositiveButton(getString(R.string.alert_approve), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    theInput = edittext.getText().toString();
                    if(isNumeric(theInput)){
                        spinnerValue= Integer.parseInt(theInput);
                        addToLayout();
                    }
                    else
                        Toast.makeText(FifthActivity.this, R.string.alert_invalid, Toast.LENGTH_SHORT).show();
                }
            });

            alert.setNegativeButton(getString(R.string.alert_dissapprove), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    // what ever you want to do with No option.
                }
            });

            alert.show();
        }

    }
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @SuppressLint("ResourceAsColor")
    public void addToLayout()
    {

        linearLayout = findViewById(R.id.mails_layout);
        linearLayout.removeAllViews();


        for(int i=0;i<spinnerValue;i++)
        {
            EditText editText = new EditText(FifthActivity.this);
            editText.setVisibility(View.VISIBLE);
            editText.setTextSize(25);
            editText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
            linearLayout.addView(editText);
            editText.setBackgroundResource(R.drawable.custom_input);
            editText.setHintTextColor(R.color.blue_special);
            editText.setHint(R.string.email_address);
            editText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.custom_email_icon, 0, 0, 0);
            editText.setCompoundDrawablePadding(12);
        }

    }



    public  void checkButton(View v) {
        
        if(radioButton1.isChecked())
        {
            Toast.makeText(this, radioButton1.getText(), Toast.LENGTH_SHORT).show();
            addressEditText.setVisibility(View.VISIBLE);
            selfTextView.setVisibility(View.GONE);
        }
        if(radioButton2.isChecked()) {
            Toast.makeText(this, radioButton2.getText(), Toast.LENGTH_SHORT).show();
            selfTextView.setVisibility(View.VISIBLE);
            addressEditText.setVisibility(View.GONE);
        }
        
    }

}
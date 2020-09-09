package com.emilypyhuang.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView resultText;

    private RadioButton maleButton;
    private RadioButton femaleButton;

    private EditText ageText;
    private EditText feetText;
    private EditText inchesText;
    private EditText weightText;

    private Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setupButtonClickListener();
    }

    private void findViews() {
        resultText = findViewById(R.id.text_view_result);
        //resultText.setText("Woohoo, I can update my TextView.");

        maleButton = findViewById(R.id.radio_button_male);
        femaleButton = findViewById(R.id.radio_button_female);

        ageText = findViewById(R.id.edit_text_age);
        feetText = findViewById(R.id.edit_text_feet);
        inchesText = findViewById(R.id.edit_text_inches);
        weightText = findViewById(R.id.edit_text_weight);

        calculateButton = findViewById(R.id.button_calculate);
    }

    private void setupButtonClickListener() {
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "Hello Detective!", Toast.LENGTH_LONG).show();
                int age = Integer.parseInt(ageText.getText().toString());
                
                if(age >= 18){
                    displayResult(calculateBMI());
                }
                else{
                    displayGuidance(calculateBMI());
                }
            }
        });
    }

    private double calculateBMI() {
        int feet = Integer.parseInt(feetText.getText().toString());
        int inches = Integer.parseInt(inchesText.getText().toString());
        int weight = Integer.parseInt(weightText.getText().toString());

        inches += feet * 12;
        double meters = inches * 0.0254;
        DecimalFormat df = new DecimalFormat("0.00");
        double bmi = Double.parseDouble(df.format(weight / (meters * meters)));
        return bmi;
    }

    private void displayResult(double bmi) {
        String result;

        if (bmi < 18.5) {
            result = "Your BMI is " + bmi + " , and you are underweight.";
        } else if (bmi > 18.5 && bmi <= 24.9) {
            result = "Your BMI is " + bmi + " , and you are a healthy weight.";
        } else {
            result = "Your BMI is " + bmi + " , and you are overweight.";
        }

        resultText.setText(result);
    }

    private void displayGuidance(double bmi) {
        String result;

        if(maleButton.isChecked()){
            //Display boy guidance
            result = bmi + " - As you are under 18, please consult with your doctor for the healthy range for boys.";
        }
        else if(femaleButton.isChecked()){
            result = bmi + " - As you are under 18, please consult with your doctor for the healthy range for girls.";
        }
        else {
            result = bmi + " - As you are under 18, please consult with your doctor for the healthy range.";
        }
        resultText.setText(result);
    }

}
package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button One, Two, Three, Four, Five, Six, Seven, Eight, Nine, Zero;

    private Button Minus, Plus, Division, Multiply, Result;

    private TextView Formula, EndResult;

    private  char Action;

    private double ResultValue= Double.NaN;

    private double Value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        SetupView();

        View.OnClickListener numberClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                Formula.setText(Formula.getText().toString() + button.getText().toString());
            }
        };
        One.setOnClickListener(numberClickListener);
        Two.setOnClickListener(numberClickListener);
        Three.setOnClickListener(numberClickListener);
        Four.setOnClickListener(numberClickListener);
        Five.setOnClickListener(numberClickListener);
        Six.setOnClickListener(numberClickListener);
        Seven.setOnClickListener(numberClickListener);
        Eight.setOnClickListener(numberClickListener);
        Nine.setOnClickListener(numberClickListener);
        Zero.setOnClickListener(numberClickListener);

        View.OnClickListener actionClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                calculate();
                Action = button.getText().charAt(0);
                Formula.setText(String.valueOf(ResultValue) + Action);
                EndResult.setText(null);
            }
        };
        Plus.setOnClickListener(actionClickListener);
        Minus.setOnClickListener(actionClickListener);
        Division.setOnClickListener(actionClickListener);
        Multiply.setOnClickListener(actionClickListener);

        Result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
                Action = '=';
                EndResult.setText(String.valueOf(ResultValue));
                Formula.setText(null);
            }
        });

    }

    private void SetupView(){
        One = (Button) findViewById(R.id.One);
        Two = (Button) findViewById(R.id.Two);
        Three = (Button) findViewById(R.id.Three);
        Four = (Button) findViewById(R.id.Four);
        Five = (Button) findViewById(R.id.Five);
        Six = (Button) findViewById(R.id.Six);
        Seven = (Button) findViewById(R.id.Seven);
        Eight = (Button) findViewById(R.id.Eight);
        Nine = (Button) findViewById(R.id.Nine);
        Zero = (Button) findViewById(R.id.Zero);
        Minus = (Button) findViewById(R.id.Minus);
        Plus = (Button) findViewById(R.id.Plus);
        Division = (Button) findViewById(R.id.Division);
        Multiply = (Button) findViewById(R.id.Multiply);
        Result = (Button) findViewById(R.id.Result);
        Formula = (TextView) findViewById(R.id.Formula);
        EndResult = (TextView) findViewById(R.id.EndResult);
    }
    private void calculate(){
        if(Double.isNaN(ResultValue)){
            String textFormula = Formula.getText().toString();
            int index = textFormula.indexOf(Action);
            if (index != -1){
                String numberValue = textFormula.substring(index + 1);
                Value = Double.parseDouble(numberValue);
                switch (Action){
                    case '/':
                        if (Value == 0){
                            ResultValue = 0.0;
                        } else {
                            ResultValue/= Value;
                        }
                        break;
                    case '*':
                        ResultValue *= Value;
                        break;
                    case '+':
                        ResultValue += Value;
                        break;
                    case '-':
                        ResultValue -= Value;
                        break;
                    case '=':
                        ResultValue = Value;
                        break;

                }

            } else {
                ResultValue = Double.parseDouble(Formula.getText().toString());
            }
            EndResult.setText(String.valueOf(ResultValue));
            Formula.setText("");
        }
    }
}
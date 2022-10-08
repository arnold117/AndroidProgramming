package com.arnold.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addButton = (Button) findViewById(R.id.add);
        Button subButton = (Button) findViewById(R.id.sub);
        Button mulButton = (Button) findViewById(R.id.mul);
        Button divButton = (Button) findViewById(R.id.div);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText firstNumber = (EditText) findViewById(R.id.num_1);
                EditText secondNumber = (EditText) findViewById(R.id.num_2);
                TextView result = (TextView) findViewById(R.id.result);

                int num1 = Integer.parseInt(firstNumber.getText().toString());
                int num2 = Integer.parseInt(secondNumber.getText().toString());
                int sum = num1 + num2;

                result.setText(Integer.toString(sum));
            }
        });

        subButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText firstNumber = (EditText) findViewById(R.id.num_1);
                EditText secondNumber = (EditText) findViewById(R.id.num_2);
                TextView result = (TextView) findViewById(R.id.result);

                int num1 = Integer.parseInt(firstNumber.getText().toString());
                int num2 = Integer.parseInt(secondNumber.getText().toString());
                int sub = num1 - num2;

                result.setText(Integer.toString(sub));
            }
        });

        mulButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText firstNumber = (EditText) findViewById(R.id.num_1);
                EditText secondNumber = (EditText) findViewById(R.id.num_2);
                TextView result = (TextView) findViewById(R.id.result);

                int num1 = Integer.parseInt(firstNumber.getText().toString());
                int num2 = Integer.parseInt(secondNumber.getText().toString());
                int mul = num1 * num2;

                result.setText(Integer.toString(mul));
            }
        });

        divButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText firstNumber = (EditText) findViewById(R.id.num_1);
                EditText secondNumber = (EditText) findViewById(R.id.num_2);
                TextView result = (TextView) findViewById(R.id.result);

                int num1 = Integer.parseInt(firstNumber.getText().toString());
                int num2 = Integer.parseInt(secondNumber.getText().toString());
                int div = num1 / num2;

                result.setText(Integer.toString(div));
            }
        });
    }
}
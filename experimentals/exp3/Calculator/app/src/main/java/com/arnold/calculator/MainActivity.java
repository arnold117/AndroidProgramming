package com.arnold.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private int offset;

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

                double num1 = Double.parseDouble(firstNumber.getText().toString());
                double num2 = Double.parseDouble(secondNumber.getText().toString());
                double sum = num1 + num2;
                String msg = Double.toString(num1) + " + " + Double.toString(num2) + " = " + Double.toString(sum) + "\n";

                result.append(msg);
                offset=result.getLineCount()*result.getLineHeight();
                if(offset>result.getHeight()){
                    result.scrollTo(0,offset-result.getHeight());
                }
            }
        });

        subButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText firstNumber = (EditText) findViewById(R.id.num_1);
                EditText secondNumber = (EditText) findViewById(R.id.num_2);
                TextView result = (TextView) findViewById(R.id.result);

                double num1 = Double.parseDouble(firstNumber.getText().toString());
                double num2 = Double.parseDouble(secondNumber.getText().toString());
                double sub = num1 - num2;
                String msg = Double.toString(num1) + " - " + Double.toString(num2) + " = " + Double.toString(sub) + "\n";

                result.append(msg);
                offset=result.getLineCount()*result.getLineHeight();
                if(offset>result.getHeight()){
                    result.scrollTo(0,offset-result.getHeight());
                }
            }
        });

        mulButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText firstNumber = (EditText) findViewById(R.id.num_1);
                EditText secondNumber = (EditText) findViewById(R.id.num_2);
                TextView result = (TextView) findViewById(R.id.result);

                double num1 = Double.parseDouble(firstNumber.getText().toString());
                double num2 = Double.parseDouble(secondNumber.getText().toString());
                double mul = num1 * num2;
                String msg = Double.toString(num1) + " * " + Double.toString(num2) + " = " + Double.toString(mul) + "\n";

                result.append(msg);
                offset=result.getLineCount()*result.getLineHeight();
                if(offset>result.getHeight()){
                    result.scrollTo(0,offset-result.getHeight());
                }
            }
        });

        divButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText firstNumber = (EditText) findViewById(R.id.num_1);
                EditText secondNumber = (EditText) findViewById(R.id.num_2);
                TextView result = (TextView) findViewById(R.id.result);

                double num1 = Double.parseDouble(firstNumber.getText().toString());
                double num2 = Double.parseDouble(secondNumber.getText().toString());
                double div = num1 / num2;
                String msg = Double.toString(num1) + " / " + Double.toString(num2) + " = " + Double.toString(div) + "\n";

                result.append(msg);
                offset=result.getLineCount()*result.getLineHeight();
                if(offset>result.getHeight()){
                    result.scrollTo(0,offset-result.getHeight());
                }
            }
        });
    }
}
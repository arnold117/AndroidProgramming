package nchu.bme.addtest;
        import androidx.appcompat.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //对加法按钮进行监听
        Button add1 = (Button) findViewById(R.id.add);
        add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = ((EditText) findViewById(R.id.num1)).getText().toString();
                double a = Double.parseDouble(s1.trim());
                String s2 = ((EditText) findViewById(R.id.num2)).getText().toString();
                double b =  Double.parseDouble(s2.trim());
                double c = a + b;
                TextView res1 = (TextView) findViewById(R.id.res);
                res1.setText(Double.toString(a)+"+"+Double.toString(b)+"="+Double.toString(c));
            }
        });
        //对减法按钮进行监听
        Button sub1 = (Button) findViewById(R.id.sub);
        sub1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = ((EditText) findViewById(R.id.num1)).getText().toString();
                double a = Double.parseDouble(s1.trim());
                String s2 = ((EditText) findViewById(R.id.num2)).getText().toString();
                double b =  Double.parseDouble(s2.trim());
                double c = a - b;
                TextView res1 = (TextView) findViewById(R.id.res);
                res1.setText(Double.toString(a)+"-"+Double.toString(b)+"="+Double.toString(c));
            }
        });
        //对乘法按钮进行监听
        Button mul1 = (Button) findViewById(R.id.mul);
        mul1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = ((EditText) findViewById(R.id.num1)).getText().toString();
                double a = Double.parseDouble(s1.trim());
                String s2 = ((EditText) findViewById(R.id.num2)).getText().toString();
                double b =  Double.parseDouble(s2.trim());
                double c = a * b;
                TextView res1 = (TextView) findViewById(R.id.res);
                res1.setText(Double.toString(a)+"*"+Double.toString(b)+"="+Double.toString(c));
            }
        });
        //对除法按钮进行监听
        Button div1 = (Button) findViewById(R.id.div);
        div1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = ((EditText) findViewById(R.id.num1)).getText().toString();
                double a = Double.parseDouble(s1.trim());
                String s2 = ((EditText) findViewById(R.id.num2)).getText().toString();
                double b =  Double.parseDouble(s2.trim());
                double c = a / b;
                TextView res1 = (TextView) findViewById(R.id.res);
                res1.setText(Double.toString(a)+"/"+Double.toString(b)+"="+Double.toString(c));
            }
        });

    }
}
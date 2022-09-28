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
        Button add1 = (Button) findViewById(R.id.add);
        add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = ((EditText) findViewById(R.id.num1)).getText().toString();
                int a = Integer.parseInt(s1.trim());
                String s2 = ((EditText) findViewById(R.id.num2)).getText().toString();
                int b = Integer.parseInt(s2.trim());
                int c = a + b;
                TextView res1 = (TextView) findViewById(R.id.res);
                res1.setText(Integer.toString(c));
            }
        });
    }
}
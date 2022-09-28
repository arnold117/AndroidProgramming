package nchu.bme.saveaddress;
import android.app.Activity;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
public class AddressActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        Intent intent = getIntent();//获取Intent对象
        Bundle bundle = intent.getExtras();//获取传递的Bundle信息
        TextView name = (TextView) findViewById(R.id.name);//获取显示姓名的TextView组件
        name.setText(bundle.getString("name"));//获取输入的姓名并显示到TextView组件中
        TextView phone = (TextView) findViewById(R.id.phone);//获取显示手机号码的TextView组件
        phone.setText(bundle.getString("phone"));//获取输入的电话号码并显示到TextView组件中
        TextView site = (TextView) findViewById(R.id.site);//获取显示地址的TextView组件
        //获取输入的地址并显示到TextView组件中
        site.setText(bundle.getString("site1")
                + bundle.getString("site2") + bundle.get("site3"));
    }
}
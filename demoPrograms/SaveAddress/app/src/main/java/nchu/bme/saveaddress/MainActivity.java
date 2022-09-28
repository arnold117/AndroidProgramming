package nchu.bme.saveaddress;

import android.app.Activity;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.btn);  //获取保存按钮
        btn.setOnClickListener(new View.OnClickListener() {  //为按钮添加单击监听事件
            @Override
            public void onClick(View v) {
                //获取输入的所在地区
                String site1 = ((EditText) findViewById(R.id.et_site1)).getText().toString();
                //获取输入的所在街道
                String site2 = ((EditText) findViewById(R.id.et_site2)).getText().toString();
                //获取输入的详细地址
                String site3 = ((EditText) findViewById(R.id.et_site3)).getText().toString();
                //获取输入的用户信息
                String name = ((EditText) findViewById(R.id.et_name)).getText().toString();
                //获取输入的手机号码
                String phone = ((EditText) findViewById(R.id.et_phone)).getText().toString();
                //获取输入的邮箱
                String email= ((EditText) findViewById(R.id.et_email)).getText().toString();
                if (!"".equals(site1) && !"".equals(site2) && !"".equals(site3)&&
                        !"".equals(name) && !"".equals(phone) &&!"".equals(email) ) {
                    //将输入的信息保存到Bundle中，通过Intent传递到另一个Activity当中并显示出来
                    Intent intent = new Intent(MainActivity.this, AddressActivity.class);
                    //创建并实例化一个Bundle对象
                    Bundle bundle = new Bundle();
                    bundle.putCharSequence("name", name);//保存姓名
                    bundle.putCharSequence("phone", phone);//保存手机号码
                    bundle.putCharSequence("site1", site1);//保存所在地区信息
                    bundle.putCharSequence("site2", site2);//保存所在街道信息
                    bundle.putCharSequence("site3", site3);//保存详细地址信息
                    intent.putExtras(bundle);//将Bundle对象添加到Intent对象中
                    startActivity(intent);//启动Activity
                }else {
                    Toast.makeText(MainActivity.this,
                            "请将收货地址填写完整！",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
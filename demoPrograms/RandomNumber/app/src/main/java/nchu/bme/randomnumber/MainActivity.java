package nchu.bme.randomnumber;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;

public class MainActivity extends Activity {
    BinderService binderService;   //声明BinderService
    //文本框组件ID
    int[] tvid = {R.id.textView1, R.id.textView2, R.id.textView3, R.id.textView4, R.id.textView5,
            R.id.textView6, R.id.textView7};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_random = (Button) findViewById(R.id.btn);  //获取随机选号按钮
        btn_random.setOnClickListener(new View.OnClickListener() {  //单击按钮，获取随机彩票号码
            @Override
            public void onClick(View v) {
                List number = binderService.getRandomNumber();  //获取BinderService类中的随机数数组
                for (int i = 0; i < number.size(); i++) {  //遍历数组并显示
                    TextView tv = (TextView) findViewById(tvid[i]);  //获取文本框组件对象
                    String strNumber = number.get(i).toString();     //将获取的号码转为String类型
                    tv.setText(strNumber);  //显示生成的随机号码
                }
            }
        });
    }

    @Override
    protected void onStart() {  //设置启动Activity时与后台Service进行绑定
        super.onStart();
        Intent intent = new Intent(this, BinderService.class);  //创建启动Service的Intent
        bindService(intent, conn, BIND_AUTO_CREATE);           //绑定指定Service
    }

    @Override
    protected void onStop() {  //设置关闭Activity时解除与后台Service的绑定
        super.onStop();
        unbindService(conn);    //解除绑定Service
    }

    private ServiceConnection conn = new ServiceConnection() {  //设置与后台Service进行通讯
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binderService = ((BinderService.MyBinder) service).getService();  //获取后台Service信息
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };
}

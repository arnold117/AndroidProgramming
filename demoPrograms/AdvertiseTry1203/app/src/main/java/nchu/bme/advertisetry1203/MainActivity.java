package nchu.bme.advertisetry1203;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements Runnable {
    private ImageView iv; // 声明一个显示广告图片的ImageView对象
    private Handler handler; // 声明一个Handler对象
    private int[] path = new int[] { R.drawable.img01, R.drawable.img02,
            R.drawable.img03, R.drawable.img04, R.drawable.img05,
            R.drawable.img06 }; // 保存广告图片的数组
    private String[] title = new String[] { "编程词典系列产品", "高效开发", "快乐分享", "用户人群",
            "快速学习", "全方位查询" }; // 保存显示标题的数组

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.imageView1); // 获取显示广告图片的ImageView
        // 实例化一个Handler对象
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // 更新UI
                TextView tv = (TextView) findViewById(R.id.textView1); // 获取TextView组件
                if (msg.what == 0x101) {
                    tv.setText(msg.getData().getString("title")); // 设置标题
                    iv.setImageResource(path[msg.arg1]); // 设置要显示的图片
                }
                super.handleMessage(msg);
            }

        };
        Thread t = new Thread(this); // 创建新线程
        t.start(); // 开启线程
    }

    @Override
    public void run() {
        int index = 0;
        while (!Thread.currentThread().isInterrupted()) {
            index = new Random().nextInt(path.length); // 产生一个随机数
            Message m = handler.obtainMessage(); // 获取一个Message
            m.arg1 = index; // 保存要显示广告图片的索引值
            Bundle bundle = new Bundle(); // 获取Bundle对象
            m.what = 0x101; // 设置消息标识
            bundle.putString("title", title[index]); // 保存标题
            m.setData(bundle); // 将Bundle对象保存到Message中
            handler.sendMessage(m); // 发送消息

            try {
                Thread.sleep(2000); // 线程休眠2秒钟
            } catch (InterruptedException e) {
                e.printStackTrace(); // 输出异常信息
            }

        }
    }
}
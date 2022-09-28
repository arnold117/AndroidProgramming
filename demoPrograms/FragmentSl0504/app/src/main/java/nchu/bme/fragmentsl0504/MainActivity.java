package nchu.bme.fragmentsl0504;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取布局文件的第一个导航图片
        ImageView imageView1 = (ImageView) findViewById(R.id.image1);
        //获取布局文件的第二个导航图片
        ImageView imageView2 = (ImageView) findViewById(R.id.image2);
        //获取布局文件的第三个导航图片
        ImageView imageView3 = (ImageView) findViewById(R.id.image3);
        //获取布局文件的第四个导航图片
        ImageView imageView4 = (ImageView) findViewById(R.id.image4);
        imageView1.setOnClickListener(l);//为第一个导航图片添加单机事件
        imageView2.setOnClickListener(l);//为第二个导航图片添加单机事件
        imageView3.setOnClickListener(l);//为第三个导航图片添加单机事件
        imageView4.setOnClickListener(l);//为第四个导航图片添加单机事件
    }
    //创建单机事件监听器
    View.OnClickListener l = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentManager fm = getFragmentManager();   // 获取Fragment
            FragmentTransaction ft = fm.beginTransaction(); // 开启一个事务
            Fragment f = null; //为Fragment初始化
            switch (v.getId()) {    //通过获取点击的id判断点击了哪个张图片
                case R.id.image1:
                    f = new WeChat_Fragment(); //创建第一个Fragment
                    break;
                case R.id.image2:
                    f = new Message_Fragment();//创建第二个Fragment
                    break;
                case R.id.image3:
                    f = new Find_Fragment();//创建第三个Fragment
                    break;
                case R.id.image4:
                    f = new Me_Fragment();//创建第四个Fragment
                    break;
                default:
                    break;
            }
            ft.replace(R.id.fragment, f); //替换Fragment
            ft.commit(); //提交事务
        }
    };
}

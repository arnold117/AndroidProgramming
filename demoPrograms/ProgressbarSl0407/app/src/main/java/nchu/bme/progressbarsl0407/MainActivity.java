package nchu.bme.progressbarsl0407;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.util.logging.LogRecord;
public class MainActivity extends Activity {
    private ProgressBar horizonP;            //水平进度条
    private int mProgressStatus = 0;        //完成进度
    private Handler mHandler;            //声明一个用于处理消息的Handler类的对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //设置全屏显示
        horizonP = (ProgressBar) findViewById(R.id.progressBar1);    //获取水平进度条
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x111) {
                    horizonP.setProgress(mProgressStatus);    //更新进度
                } else {
                    Toast.makeText(MainActivity.this,
                            "耗时操作已经完成", Toast.LENGTH_SHORT).show();
                    horizonP.setVisibility(View.GONE);    //设置进度条不显示，并且不占用空间
                }
            }
        };
        new Thread(new Runnable() {
            public void run() {
                while (true) {		//循环获取耗时操作完成的百分比，直到耗时操作结束
                    mProgressStatus = doWork();    	//获取耗时操作完成的百分比
                    Message m = new Message();		//创建并实例化一个消息对象
                    if (mProgressStatus < 100) {		//当完成进度不到100时表示耗时任务未完成
                        m.what = 0x111;				//设置代表耗时操作未完成的消息代码
                        mHandler.sendMessage(m);    //发送信息
                    } else {						//当完成进度到达100时表示耗时操作完成
                        m.what = 0x110; 			//设置代表耗时操作已经完成的消息代码
                        mHandler.sendMessage(m);   	//发送消息
                        break;					//退出循环
                    }
                }
            }
            //模拟一个耗时操作
            private int doWork() {
                mProgressStatus += (int)(Math.random() * 10);    	//改变完成进度
                try {
                    Thread.sleep(500);       		 	//线程休眠500毫秒
                } catch (InterruptedException e) {
                    e.printStackTrace();					//输出异常信息
                }
                return mProgressStatus;    				//返回新的进度
            }
        }).start();    									//开启一个线程

    }
}





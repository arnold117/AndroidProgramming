package nchu.bme.shakechangebg;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Message;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    // 两次检测的时间间隔
    private static final int INTERVAL_TIME = 500;
    // 上次检测时间
    private long lastTime;
    //定义sensor管理器
    private SensorManager sensorManager;
    //监听器
    private sensorListener sensor;
    //判断消息值
    public static int flag = 0;
    //显示图片的布局
    private static LinearLayout linearLayout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = (LinearLayout) findViewById(R.id.activity_main);
        linearLayout.setBackgroundResource(R.mipmap.img1);
    }

    /**
     * 获取焦点注册监听器
     */
    @Override
    protected void onResume() {
        super.onResume();
        register();
    }

    /**
     * 注册监听器方法
     */
    private void register() {
        if (sensorManager == null) {
            // 获取传感器管理服务
            sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        }
        // 监听器
        sensor = new sensorListener();
        // 加速度传感器
        sensorManager.registerListener(sensor,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                // 还有SENSOR_DELAY_UI、SENSOR_DELAY_FASTEST、SENSOR_DELAY_GAME等，
                // 根据不同应用，需要的反应速率不同，具体根据实际情况设定
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    /**
     * 界面停止注销监听器
     */
    @Override
    protected void onPause() {
        sensorManager.unregisterListener(sensor);
        super.onPause();
    }

    /**
     * 销毁时删除消息
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeMessages(0);
    }

    /**
     * 接收更换图片消息
     */
    public Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //根据晃动接收消息，更换图片
            switch (flag) {
                case 0:
                    linearLayout.setBackgroundResource(R.mipmap.img1);
                    break;
                case 1:
                    linearLayout.setBackgroundResource(R.mipmap.img2);
                    break;
                case 2:
                    linearLayout.setBackgroundResource(R.mipmap.img3);
                    break;
                case 3:
                    linearLayout.setBackgroundResource(R.mipmap.img4);
                    break;
            }

        }

    };

    private class sensorListener implements SensorEventListener {
        @Override
        public void onAccuracyChanged(Sensor arg0, int arg1) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            // 现在检测时间
            long currentUpdateTime = System.currentTimeMillis();
            // 两次检测的时间间隔
            long timeInterval = currentUpdateTime - lastTime;
            // 判断是否达到了检测时间间隔
            if (timeInterval < INTERVAL_TIME)
                return;
            // 现在的时间变成last时间
            lastTime = currentUpdateTime;
            int sensorType = event.sensor.getType();
            // values[0]:X轴，values[1]：Y轴，values[2]：Z轴
            float[] values = event.values;
            if (sensorType == Sensor.TYPE_ACCELEROMETER) {
                if ((Math.abs(values[0]) > 12 || Math.abs(values[1]) > 12 || Math
                        .abs(values[2]) > 12)) {
                    flag++;                         //摇晃一次该值加1用于更换图片
                    if(flag>3) flag = 0;            //0~3一共四张图片，该值大于3时从第一张开始
                    mHandler.sendEmptyMessage(0);  //发送更换图片的消息
                }
            }
        }
    }
}

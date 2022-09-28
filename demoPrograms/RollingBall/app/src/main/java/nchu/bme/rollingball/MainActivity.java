package nchu.bme.rollingball;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;


public class MainActivity extends Activity {
    SensorManager sensorManager = null;     //定义传感器管理
    Sensor sensor = null;                    //定义传感器
    private boolean init = false;
    private int container_width = 0;       //容器的宽度
    private int container_height = 0;      //容器的高度
    private int ball_width = 0;             //小球宽度
    private int ball_height = 0;            //小球高度
    private BallView ball;                   //小球
    private float ballX = 100;              //小球默认显示的X坐标
    private float ballY = 100;              //小球默认显示的Y坐标


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        //获取传感器管理服务
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //获取重力感应传感器
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);

    }
    //设置小球移动坐标的方法
    void moveTo(float x, float y) {
        ballX += x;      //重新赋值
        ballY += y;
        if (ballX < 0) {    //初始化x坐标
            ballX = 0;
        }
        if (ballY < 0) {     //初始化y坐标
            ballY = 0;
        }
        //设置小球X坐标的边界
        if (ballX > container_width - ball_width) {
            ballX = container_width - ball_width;
        }
        //设置小球Y坐标的边界
        if (ballY > container_height - ball_height) {
            ballY = container_height - ball_height;
        }
        //小球移动位置
        ball.moveTo((int) ballX, (int) ballY);
        Log.v("ball", "ball x=" + ballX + " ball y=" + ballY);
    }

    //注册传感器监听
    public void register() {
        sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_GAME);
    }

    //注销传感器监听器
    public void unregister() {
        sensorManager.unregisterListener(listener);
    }
    SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub

        }
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (!init)
                return;
            //获取传感器X与Y轴的值*3用于加快小球移动速度
            float x = event.values[SensorManager.DATA_X] * 3;
            float y = event.values[SensorManager.DATA_Y] * 3;
            float z = event.values[SensorManager.DATA_Z];
            moveTo(-x, y);
        }

    };

    //窗口焦点发生改变触发该方法
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        // TODO Auto-generated method stub
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && !init) {
            init();         //调用初始化方法
            init = true;   //初始化标记
        }
    }


    //初始化方法
    public void init() {
        View container = findViewById(R.id.ball_container);     //获取小球移动容器也就是外层的帧布局
        container_width = container.getWidth();                 //获取容器宽度
        container_height = container.getHeight();               //获取容器高度
        ball = (BallView) findViewById(R.id.ball);               //获取小球
        ball_width = ball.getWidth();                            //获取小球宽度
        ball_height = ball.getHeight();                          //获取小球高度
        moveTo(0, 0);                                              //初始化小球移动坐标
    }

    //界面销毁注销传感器监听器
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        unregister();
    }

    //界面停止注销传感器监听器
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        unregister();
    }

    //再次进入界面注册传感器监听器
    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        super.onRestart();
        register();
    }

    //获取焦点注册传感器监听器
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        register();
    }


}
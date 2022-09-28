package nchu.bme.lightsensor;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    // 定义传感器管理
    SensorManager sensorManager;
    //定义布局管理器
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取传感器管理服务
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //获取布局管理器
        relativeLayout = (RelativeLayout) findViewById(R.id.relativelayout);
    }

    @Override
    public void onResume() {
        super.onResume();
        // 注册/监听方光线感器
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),
                SensorManager.SENSOR_DELAY_GAME);
    }

    /**
     * 监听光线传感器变化
     */
    @Override
    public void onSensorChanged(SensorEvent event) {
        //获取传感器值
        float[] values = event.values;
        //获取传感器类型
        int sensorType = event.sensor.getType();
        switch (sensorType) {
            case Sensor.TYPE_LIGHT:
                //获取窗口管理属性
                WindowManager.LayoutParams lp = this.getWindow().getAttributes();
                //计算屏幕亮度
                lp.screenBrightness = Float.valueOf(values[0]) * (1f / 255f);
                this.getWindow().setAttributes(lp);     //设置屏幕亮度
                if (values[0] == 0) {                      //如果光照值为0
                    //更换黑夜背景图片
                    relativeLayout.setBackgroundResource(R.drawable.night);
                } else {
                    //否则更换白天图片
                    relativeLayout.setBackgroundResource(R.drawable.day);
                }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

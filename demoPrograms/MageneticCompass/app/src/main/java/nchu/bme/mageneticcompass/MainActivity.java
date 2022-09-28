package nchu.bme.mageneticcompass;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends Activity{
    private SensorManager sm;
    //需要两个Sensor
    private Sensor aSensor;
    private Sensor mSensor;
    float[] accelerometerValues = new float[3];
    float[] magneticFieldValues = new float[3];
    private static final String TAG = "sensor";
    ImageView pointer;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pointer  = (ImageView) findViewById(R.id.pointer);
        sm = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        aSensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensor = sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        //调用更新显示数据的方法
        calculateOrientation();
    }
    //再次强调：注意activity暂停的时候释放
    public void onPause(){
        sm.unregisterListener(myListener);
        super.onPause();
    }
    //再次进入界面注册监听器
    @Override
    protected void onRestart() {
        sm.registerListener(myListener, aSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(myListener, mSensor,SensorManager.SENSOR_DELAY_NORMAL);
        super.onRestart();
    }
    //启动界面注册监听器
    @Override
    protected void onStart() {
        sm.registerListener(myListener, aSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(myListener, mSensor,SensorManager.SENSOR_DELAY_NORMAL);
        super.onStart();
    }
    //创建传感器监听器
    SensorEventListener myListener = new SensorEventListener() {
        public void onSensorChanged(SensorEvent sensorEvent) {
            //获取磁场传感器值
            if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
                magneticFieldValues = sensorEvent.values;
            //获取加速度传感器值
            if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
                accelerometerValues = sensorEvent.values;
            //调用更新显示数据的方法
            calculateOrientation();
        }
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
    };
    //更新显示数据的方法
    private void calculateOrientation() {
        float[] values = new float[3];
        float[] R = new float[9];
        //获取旋转矩阵
        SensorManager.getRotationMatrix(R, null, accelerometerValues, magneticFieldValues);
        //获取方向
        SensorManager.getOrientation(R, values);
        // 要经过一次数据格式的转换，转换为度
        values[0] = (float) Math.toDegrees(values[0]);
        pointer.setRotation(values[0]);         //设置勺子旋转角度
    }
}
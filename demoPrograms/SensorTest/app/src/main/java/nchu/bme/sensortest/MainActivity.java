package nchu.bme.sensortest;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    EditText textGRAVITY, textLIGHT;  //传感器输出信息的编辑框
    private SensorManager sensorManager;  //定义传感器管理
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textGRAVITY = (EditText) findViewById(R.id.textGRAVITY); //获取重力传感器输出信息的编辑框
        textLIGHT = (EditText) findViewById(R.id.textLIGHT);     //获取光线传感器输出信息的编辑框
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);  //获取传感器管理
    }

    @Override
    protected void onResume() {
        super.onResume();
        //为重力传感器注册监听器
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY),
                SensorManager.SENSOR_DELAY_GAME);
        //为光线传感器注册监听器
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),
                SensorManager.SENSOR_DELAY_GAME);
    }
    @Override
    protected void onPause() {  //取消注册监听器
        sensorManager.unregisterListener(this);
        super.onPause();
    }

    @Override
    protected void onStop() {  //取消注册监听器
        sensorManager.unregisterListener(this);
        super.onStop();
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] values = event.values;  //获取X、Y、Z三轴的输出信息
        int sensorType = event.sensor.getType();  //获取传感器类型
        switch (sensorType) {
            case Sensor.TYPE_GRAVITY:
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("X轴横向重力值:");
                stringBuilder.append(values[0]);
                stringBuilder.append("\nY轴纵向重力值:");
                stringBuilder.append(values[1]);
                stringBuilder.append("\nZ轴向上重力值:");
                stringBuilder.append(values[2]);
                textGRAVITY.setText(stringBuilder.toString());
                break;
            case Sensor.TYPE_LIGHT:
                stringBuilder = new StringBuilder();
                stringBuilder.append("光的强度值:");
                stringBuilder.append(values[0]);
                textLIGHT.setText(stringBuilder.toString());
                break;
        }
    }

   @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

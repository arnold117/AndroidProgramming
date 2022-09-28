package nchu.bme.compasstest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.view.View;

//import nchu.bme.R;

/**
 * Created by Administrator on 2016/4/21.
 */
public class PointerView extends View implements SensorEventListener {
    //定义指针位图
    private Bitmap pointer = null;
    //定义传感器三轴的输出信息
    private float[] allValue;
    //定义传感器管理
    private SensorManager sensorManager;

    public PointerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        pointer = BitmapFactory.decodeResource(super.getResources(),
                R.drawable.pointer); //获取要绘制的指针位图
        //获取传感器管理
        sensorManager = (SensorManager) context
                .getSystemService(Context.SENSOR_SERVICE);
        //为磁场传感器注册监听器
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                SensorManager.SENSOR_DELAY_GAME);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) { //如果是磁场传感器
            float value[] = event.values; //获取磁场传感器三轴的输出信息
            allValue = value; // 保存输出信息
            super.postInvalidate(); // 刷新界面
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint(); //创建画笔
        if (allValue != null) { //传感器三轴输出信息不为空
            float x = allValue[0]; //获取x轴坐标
            float y = allValue[1]; //获取y轴坐标
            canvas.save();         //保存Canvas的状态
            canvas.restore(); // 重置绘图对象
            // 以屏幕中心点作为旋转中心
            canvas.translate(super.getWidth() / 2, super.getHeight() / 2);
            // 判断y轴为0时的旋转角度
            if (y == 0 && x > 0) {
                canvas.rotate(90);    // 旋转角度为90度
            } else if (y == 0 && x < 0) {
                canvas.rotate(270);    // 旋转角度为270度
            } else {
                //通过三角函数tanh()方法计算旋转角度
                if (y >= 0) {
                    canvas.rotate((float) Math.tanh(x / y) * 90);
                } else {
                    canvas.rotate(180 + (float) Math.tanh(x / y) * 90);
                }
            }
        }
        //绘制指针
        canvas.drawBitmap(this.pointer, -this.pointer.getWidth() / 2,
                -this.pointer.getHeight() / 2, p);

    }
}


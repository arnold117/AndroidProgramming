package nchu.bme.catchmetry0303;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int h;
    private int w;
    private DrawCircle drawCircle;
    private RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        w = wm.getDefaultDisplay().getWidth();  //获取屏幕的宽
        h = wm.getDefaultDisplay().getHeight();  //获取屏幕的高
        layout = (RelativeLayout) findViewById(R.id.root);
        drawCircle = new DrawCircle(this);  //初始化画布
        drawCircle.setMinimumHeight(h);      //设置画布的高
        drawCircle.setMinimumWidth(w);      //设置画布的宽
        //通知view组件重绘
        drawCircle.invalidate();
        drawCircle.setBackgroundResource(R.mipmap.background);
        layout.addView(drawCircle);   //将画布添加到布局里
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (MotionEvent.ACTION_DOWN == event.getAction()) {
            float x = event.getX();
            float y = event.getY();
            //判断点的是不是圆内
            if ((drawCircle.getOldValues()[0] + 80 > x && x > drawCircle.getOldValues()[0] - 80)
                    && (drawCircle.getOldValues()[1] + 80 > y && y > drawCircle.getOldValues()[1] - 80)) {
                //判断随机生成的坐标在不在当前圆内
                Random random = new Random();
                int sendX, sendY;
                do {
                    sendX = random.nextInt(w - 80);
                    sendY = random.nextInt(h - 80);
                } while ((sendX < 80 && sendY < 200)&& (drawCircle.getOldValues()[1] + 80 > sendY && sendY > drawCircle.getOldValues()[1] - 80));
                drawCircle.setXY(sendX, sendY);  //根据X与Y的值花圆
            }

        }

        return super.onTouchEvent(event);
    }

}

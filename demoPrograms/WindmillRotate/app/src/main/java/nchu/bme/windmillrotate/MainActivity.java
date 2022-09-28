package nchu.bme.windmillrotate;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
public class MainActivity extends AppCompatActivity {
    float x1;
    float x2;
    float y1;
    float y2;
    int i = 100;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //绑定控件的id
        imageView = (ImageView) findViewById(R.id.image_bg);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //获取手势落下时的点的坐标
            x1 = event.getX();
            y1 = event.getY();

        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            //获取手指移动的点
            x2 = event.getX();
            y2 = event.getY();
            int p = (int) (x2 - x1);  //获取两点的差值
            i = i + p / 10; //获取i的值
            imageView.setRotation(i);  //设置图拍的旋转角度
        }
        return super.onTouchEvent(event);
    }
}

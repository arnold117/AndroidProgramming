package nchu.bme.wearhat;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取相对局管理器
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        final HatView hat = new HatView(MainActivity.this); //创建并实例化HatView类
        //为帽子添加触摸事件监听
        hat.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hat.bitmapX = event.getX(); //设置帽子显示位置的X坐标
                hat.bitmapY = event.getY(); //设置帽子显示位置的Y坐标
                hat.invalidate();               //重绘hat组件
                return true;
            }
        });
        relativeLayout.addView(hat);            //将hat添加到布局管理器中
    }
}

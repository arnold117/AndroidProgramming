package nchu.bme.exp7_viewflipper;

import androidx.appcompat.app.AppCompatActivity;

import android.gesture.Gesture;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {
    ViewFlipper flipper;
    GestureDetector detector;
    Animation[] animation=new Animation[5];
    final  int distance=50;
    private  int[] images=new int[]{R.drawable.i1,R.drawable.i2,R.drawable.i3,R.drawable.i4,R.drawable.i5};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        detector =new GestureDetector(this,this);
        flipper=(ViewFlipper)findViewById(R.id.flipper);
        for(int i=0;i<images.length;i++)
        {
            ImageView imageView=new ImageView(this);
            imageView.setImageResource(images[i]);
            flipper.addView(imageView);
        }
        flipper.setInAnimation(this,R.anim.slide_in_right);
        flipper.setOutAnimation(this,R.anim.slide_out_left);
        flipper.setFlipInterval(3000);
        flipper.startFlipping();
        animation[0]=AnimationUtils.loadAnimation(this,R.anim.slide_in_left);
        animation[1]=AnimationUtils.loadAnimation(this,R.anim.slide_out_left);
        animation[2]=AnimationUtils.loadAnimation(this,R.anim.slide_in_right);
        animation[3]=AnimationUtils.loadAnimation(this,R.anim.slide_out_right);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            //从右向左滑动
            if (e1.getX() - e2.getX() > distance) {
                //为flipper设置切换的动画效果
                flipper.setInAnimation(animation[2]);
                flipper.setOutAnimation(animation[1]);
                flipper. showNext();
                return true;
                //从左向右滑动
            } else if (e2.getX() - e1.getX() > distance) {
                //为flipper设置切换的动画
                flipper.setInAnimation(animation[0]);
                flipper.setOutAnimation(animation[3]);
                flipper. showPrevious();
                return true;
            }
            return false;
        }
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            //将该Activity上的触碰事件交给GestureDetector处理
            return detector.onTouchEvent(event);

    }
}
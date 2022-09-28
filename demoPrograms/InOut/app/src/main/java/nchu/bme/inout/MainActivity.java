package nchu.bme.inout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;
public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    ViewFlipper flipper;                            //定义ViewFlipper
    GestureDetector detector;                       //定义手势检测器
    Animation[] animation = new Animation[2];       //定义动画数组，为ViewFlipper指定切换动画
    final int distance = 50;                       //定义手势动作两点之间最小距离
    //定义图片数组
    private int[] images = new int[]{
            R.drawable.img01, R.drawable.img02, R.drawable.img03,
            R.drawable.img04, R.drawable.img05, R.drawable.img06,
            R.drawable.img07, R.drawable.img08, R.drawable.img09,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        detector = new GestureDetector(this, this);             //创建手势检测器
        flipper = (ViewFlipper) findViewById(R.id.flipper);      //获取ViewFlipper
        for (int i = 0; i < images.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(images[i]);
            flipper.addView(imageView); //加载图片
        }
        //初始化动画数组
        animation[0] = AnimationUtils.loadAnimation(this, R.anim.anim_alpha_in);  //淡入动画
        animation[1] = AnimationUtils.loadAnimation(this, R.anim.anim_alpha_out); //淡出动画
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
        //为flipper设置切换的动画效果
        flipper.setInAnimation(animation[0]);
        flipper.setOutAnimation(animation[1]);
        //如果第一个触点事件的X坐标到第二个触点事件的X坐标的距离超过distance就是从右向左滑动
        if (e1.getX() - e2.getX() > distance) {
            flipper.showPrevious();
            return true;
            //如果第二个触点事件的X坐标到第一个触点事件的X坐标的距离超过distance就是从左向右滑动
        } else if (e2.getX() - e1.getX() > distance) {
            flipper.showNext();
            return true;
        }
        return false;

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //将当前Activity上的触碰事件交给GestureDetector处理
        return detector.onTouchEvent(event);
    }
}
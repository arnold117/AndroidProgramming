package nchu.bme.neteasemusic;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.media.MediaPlayer;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView img_needle, img_play, img_cd;         //定义图片控件
    private int Play = 0;                                     //定义播放标记0为默认没有播放
    ObjectAnimator animator;                                   //定义cd碟片的旋转属性动画
    private boolean firstPlay=false;                        //第一次播放标记
    private MediaPlayer mp = new MediaPlayer();               //创建音乐播放器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img_needle = (ImageView) findViewById(R.id.img_needle);         //获取显示磁头的图片控件
        img_play = (ImageView) findViewById(R.id.img_play);              //获取播放按钮图片控件
        img_cd = (ImageView) findViewById(R.id.img_cd);                    //获取cd圆盘图片控件
        img_play.setBackgroundResource(R.drawable.ic_play);             //设置播放按钮背景图片
        img_play.setOnClickListener(this);                              //为播放按钮图片设置单击事件
        initRotation();                                                      //进行cd碟片旋转动画的初始化
        mp= MediaPlayer.create(getApplicationContext(),R.raw.sugar);       //加载音乐文件
        mp.setLooping(true);                                                //重复播放音乐
    }

    //单击事件方法
    @Override
    public void onClick(View v) {
        if (Play == 0) {
            mp.start();                                                 //播放音乐
            img_play.setBackgroundResource(R.drawable.ic_pause);        //更换为暂停图片背景
            Rotateleft();                                                 //调用播放音乐磁头向左旋转
            Play = 1;                                                       //修改播放标记为暂停状态
            if (firstPlay==false){                                         //判断是第一次播放
                animator.start();                                           //启动cd碟片旋转动画
                firstPlay=true;                                             //修改为非第一次播放
            }else {
                animator.resume();                                          //继续上次暂停位置旋转
            }

        } else {
            if (Play == 1) {
                img_play.setBackgroundResource(R.drawable.ic_play);        //更换为播放图片背景
                Rotateright();                                                 //调用暂停音乐磁头向右旋转
                Play = 0;                                                       //修改播放标记为暂停状态
                animator.pause();                                               //暂停cd碟片的旋转动画
                mp.pause();                                                  //暂停播放音乐
            }
        }
    }

    //播放音乐时磁头向左旋转动画
    public void Rotateleft() {
        RotateAnimation left = new RotateAnimation(0, 30, Animation.RELATIVE_TO_PARENT, 0.05f, Animation.RELATIVE_TO_PARENT, 0.05f);             //创建旋转动画
        left.setDuration(1000);                                         //设置动画持续时间
        left.setFillAfter(true);                                        //停留旋转后位置
        img_needle.startAnimation(left);                                //开始向左旋转动画
    }

    //暂停音乐播放时磁头向右旋转动画
    public void Rotateright() {
        RotateAnimation right = new RotateAnimation(30, 0, Animation.RELATIVE_TO_PARENT, 0.05f, Animation.RELATIVE_TO_PARENT, 0.05f);
        right.setDuration(1000);                                         //设置动画持续时间
        right.setFillAfter(true);                                        //停留旋转后位置
        img_needle.startAnimation(right);                                //暂停向右旋转动画
    }
    //cd碟片的旋转动画
    public void initRotation() {
        animator = ObjectAnimator.ofFloat(img_cd, "rotation", 0f, 360.0f);
        animator.setDuration(10000);                                    //设置动画每一圈的持续时间
        animator.setInterpolator(new LinearInterpolator());             //不停顿
        animator.setRepeatCount(-1);                                    //设置动画重复次数
        animator.setRepeatMode(ValueAnimator.RESTART);                  //动画重复模式
    }
    @Override
    protected void onDestroy() {                                        //界面销毁时销毁音乐资源
        if(mp != null)
            mp.release();
        super.onDestroy();
    }
}

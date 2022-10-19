package com.arnold.neteaseemu;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

public class MainActivity extends AppCompatActivity {
    private ImageView img_needle, img_play, img_cd;
    private int play = 0;
    ObjectAnimator animator;
    private boolean firstPlay = false;
    private MediaPlayer mp = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img_needle = findViewById(R.id.img_needle);
        img_play = findViewById(R.id.img_play);
        img_cd = findViewById(R.id.img_cd);
        img_play.setBackgroundResource(R.drawable.ic_play);
        img_play.setOnClickListener(this::onClick);
        initRotate();
        mp = MediaPlayer.create(getApplicationContext(), R.raw.hanasakuodometachi);
        mp.setLooping(true);
    }

    public void onClick(View v) {
        if (play == 0) {
            mp.start();                                                 //播放音乐
            img_play.setBackgroundResource(R.drawable.ic_pause);        //更换为暂停图片背景
            rotateLeft();                                                 //调用播放音乐磁头向左旋转
            play = 1;                                                       //修改播放标记为暂停状态
            if (!firstPlay){                                         //判断是第一次播放
                animator.start();                                           //启动cd碟片旋转动画
                firstPlay=true;                                             //修改为非第一次播放
            }else {
                animator.resume();                                          //继续上次暂停位置旋转
            }

        } else {
            if (play == 1) {
                img_play.setBackgroundResource(R.drawable.ic_play);        //更换为播放图片背景
                rotateRight();                                                 //调用暂停音乐磁头向右旋转
                play = 0;                                                       //修改播放标记为暂停状态
                animator.pause();                                               //暂停cd碟片的旋转动画
                mp.pause();                                                  //暂停播放音乐
            }
        }
    }

    public void rotateLeft() {
        RotateAnimation left = new RotateAnimation(0, 30, Animation.RELATIVE_TO_PARENT, 0.05f, Animation.RELATIVE_TO_PARENT, 0.05f);             //创建旋转动画
        left.setDuration(1000);                                         //设置动画持续时间
        left.setFillAfter(true);                                        //停留旋转后位置
        img_needle.startAnimation(left);                                //开始向左旋转动画
    }

    //暂停音乐播放时磁头向右旋转动画
    public void rotateRight() {
        RotateAnimation right = new RotateAnimation(30, 0, Animation.RELATIVE_TO_PARENT, 0.05f, Animation.RELATIVE_TO_PARENT, 0.05f);
        right.setDuration(1000);                                         //设置动画持续时间
        right.setFillAfter(true);                                        //停留旋转后位置
        img_needle.startAnimation(right);                                //暂停向右旋转动画
    }

    private void initRotate() {
        animator = ObjectAnimator.ofFloat(img_cd, "rotation", 0f, 360f);
        animator.setDuration(10000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(-1);
        animator.setRepeatMode(ValueAnimator.RESTART);
    }

    @Override
    protected void onDestroy() {                                        //界面销毁时销毁音乐资源
        if(mp != null)
            mp.release();
        super.onDestroy();
    }
}
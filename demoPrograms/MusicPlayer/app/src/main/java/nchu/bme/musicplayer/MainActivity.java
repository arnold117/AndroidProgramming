package nchu.bme.musicplayer;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends Activity {
    private MediaPlayer player;            //定义MediaPlayer对象
    private boolean isPause = false;        //定义是否暂停
   // private File file;                       //定义要播放的音频文件


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //获取“播放/暂停”按钮
        final ImageButton btn_play = (ImageButton) findViewById(R.id.btn_play);
        //获取“停止”按钮
        final ImageButton btn_stop = (ImageButton) findViewById(R.id.btn_stop);
        //file = new File("/sdcard/music.mp3");   //获取要播放的音频文件

       // if (file.exists()) {  //如果音频文件存在
            //创建MediaPlayer对象,并解析要播放的音频文件
            player = MediaPlayer.create(this, R.raw.seasons);
        //} else {
            //提示音频文件不存在
        //    Toast.makeText(MainActivity.this, "要播放的音频文件不存在！", Toast.LENGTH_SHORT).show();
       //     return;
     //   }

        //为MediaPlayer添加完成事件监听器，实现当音频播放完毕后，重新开始播放音频
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                play();                               //调用play()方法，实现播放功能
            }
        });

        btn_play.setOnClickListener(new View.OnClickListener() {  //实现继续播放与暂停播放
            @Override
            public void onClick(View v) {
                if (player.isPlaying() && !isPause) {         //如果音频处于播放状态
                    player.pause();                            //暂停播放
                    isPause = true;                           //设置为暂停状态
                    //更换为播放图标
                    ((ImageButton) v).setImageDrawable(getResources()
                            .getDrawable(R.drawable.play, null));
                } else {
                    player.start();                           //继续播放
                    // 更换为暂停图标
                    ((ImageButton) v).setImageDrawable(getResources()
                            .getDrawable(R.drawable.pause, null));
                    isPause = false;                         //设置为播放状态
                }
            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() { //单击停止按钮，实现停止播放音频
            @Override
            public void onClick(View v) {
                player.stop();                    //停止播放
                //更换为播放图标
                btn_play.setImageDrawable(getResources()
                        .getDrawable(R.drawable.play, null));
            }
        });
    }

    private void play() {  //创建play()，实现音频播放功能
        try {
            player.reset();                                         //重置MediaPlayer对象
            //player = MediaPlayer.create(this, R.raw.seasons);         //重新设置要播放的音频
            //player = MediaPlayer.create(this, R.raw.seasons);
            player.prepare();                                       //预加载音频
            player.start();                                         //开始播放
        } catch (Exception e) {
            e.printStackTrace();                                    //输出异常信息
        }
    }
    //当前Activity销毁时,停止正在播放的音频,并释放MediaPlayer所占用的资源
    @Override
    protected void onDestroy() {
        if (player.isPlaying()) {             //如果音频处于播放状态
            player.stop();                    //停止音频的播放
        }
        player.release();                    //释放资源
        super.onDestroy();
    }

}
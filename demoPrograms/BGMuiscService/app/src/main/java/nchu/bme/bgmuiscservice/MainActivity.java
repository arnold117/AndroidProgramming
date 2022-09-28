package nchu.bme.bgmuiscservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import androidx.annotation.RequiresApi;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ImageButton btn_play = (ImageButton) findViewById(R.id.btn_play);//获取“播放/停止”按钮

        //启动服务与停止服务，实现播放背景音乐与停止播放背景音乐
        btn_play.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if (MusicService.isplay == false) {   //判断音乐播放的状态
                    //启动服务，从而实现播放背景音乐
                    startService(new Intent(MainActivity.this, MusicService.class));
                    //更换播放背景音乐图标
                    ((ImageButton) v).setImageDrawable(getResources().getDrawable(R.drawable.play, null));
                } else {
                    //停止服务，从而实现停止播放背景音乐
                    stopService(new Intent(MainActivity.this, MusicService.class));
                    //更换停止背景音乐图标
                    ((ImageButton) v).setImageDrawable(getResources().getDrawable(R.drawable.stop, null));
                }
            }
        });
    }

    @Override
    protected void onStart() {  //实现进入界面时，启动背景音乐服务
        //启动服务，从而实现播放背景音乐
        startService(new Intent(MainActivity.this, MusicService.class));
        super.onStart();
    }

}
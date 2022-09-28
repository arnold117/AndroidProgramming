package com.mingrisoft;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class MusicService extends Service {
    public MusicService() {
    }
    static boolean isplay; //定义当前播放状态
    MediaPlayer player;    //MediaPlayer对象

    @Override
    public IBinder onBind(Intent intent) {  //必须实现的绑定方法
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public void onCreate() {
        player = MediaPlayer.create(this, R.raw.music); //创建MediaPlayer对像并加载播放的音乐文件
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) { //实现音乐的播放
        if (!player.isPlaying()) {  //如果没有播放音乐
            player.start();  //播放音乐
            isplay = player.isPlaying();  //当前状态正在播放音乐
        }
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {  //停止音乐的播放
        player.stop();   //停止音频的播放
        isplay = player.isPlaying();  //当前状态没有播放音乐
        player.release();  //释放资源
        super.onDestroy();
    }
}

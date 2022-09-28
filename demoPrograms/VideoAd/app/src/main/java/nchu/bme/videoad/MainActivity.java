package nchu.bme.videoad;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.VideoView;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private VideoView videoView;
    public static final String VIDEO_NAME = "baikal.mp4";   //加载的登录视频
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView = (VideoView) findViewById(R.id.videoView);
        imageView = (ImageView) findViewById(R.id.image_ad);  //广告图片
        // 拼接完整路径
        File videoFile = getFileStreamPath(VIDEO_NAME);  //读取视频文件
        if (!videoFile.exists()) {
            videoFile = copyVideoFile();
        }
        playVideo(videoFile);   //播放视频
        Button start = (Button) findViewById(R.id.start);
        start.setOnClickListener(this);
        Button pause = (Button) findViewById(R.id.pause);
        pause.setOnClickListener(this);
    }
    //@NonNull
    private File copyVideoFile() {
        File videoFile;  //定义文件
        try {
            FileOutputStream fos = openFileOutput(VIDEO_NAME, MODE_PRIVATE);        //输出字节文件流
            InputStream in = getResources().openRawResource(R.raw.baikal);   //输入字节文件流
            byte[] buff = new byte[1024];       //装换成byte字节
            int len = 0;
            while ((len = in.read(buff)) != -1) {  //开始转换
                fos.write(buff, 0, len);
            }
        } catch (FileNotFoundException e) {   //跑出没有找到文件异常
            e.printStackTrace();
        } catch (IOException e) {              //跑出io流异常
            e.printStackTrace();
        }
        videoFile = getFileStreamPath(VIDEO_NAME);   //获取视频文件的路径
        if (!videoFile.exists())
            throw new RuntimeException("视频文件有问题，你确定你有baikal.mp4 RES /原文件夹吗？");
        return videoFile;            //返回视频文件
    }
    /**
     * 播放音乐
     * */
    private void playVideo(File videoFile) {
        videoView.setVideoPath(videoFile.getPath());  //获取视频的路径
        videoView.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));  //给视频设置布局，填充整个父级控件
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoView.stopPlayback();   //停止视频播放
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 开始播放
            case R.id.start:
                videoView.start();   //播放视频
                imageView.setVisibility(View.INVISIBLE);//隐藏图片
                break;

            // 暂停
            case R.id.pause:
                videoView.pause();   //暂停视频
                imageView.setVisibility(View.VISIBLE);//显示图片
                break;
            default:
                break;
        }
    }
}

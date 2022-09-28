package com.example.android_1;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;
import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO;
//录像时不可照相，好气
//解决第一次预览对焦
//解决android不能储存
//闪光灯不好用，没有系统相机照的好，曝光时间有问题

//接下来把录像，和照相分开，做一个碎片，没做成
public class MainActivity extends Activity  implements SurfaceHolder.Callback{
    private static final String TAG ="MainActivity" ;
    private static String a;
    private Camera camera;                                         //定义相机对象
    private boolean isPreview = false;                            //定义非预览状态
    private MediaRecorder mediaRecorder;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    private    SurfaceHolder sh;
    private   SurfaceView sv;
    private boolean isRecording = false;
    boolean change=true;//摄像头
    boolean change_2=false;//闪光灯
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (!Environment.getExternalStorageState().equals(  //判断手机是否安装SD卡
                Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, "请安装SD卡！", Toast.LENGTH_SHORT).show(); // 提示安装SD卡
        }

        //获取SurfaceView组件，用于显示相机预览
        sv = (SurfaceView) findViewById(R.id.surfaceView);
        sh = sv.getHolder();                            //获取SurfaceHolder对象

        //设置该SurfaceHolder自己不维护缓冲
        sh.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);//surfaceview不维护自己的缓冲区，等待屏幕渲染引擎将内容推送到用户面
        sh.addCallback(this);//添加回调

        ImageButton takePicture = findViewById(R.id.takephoto);  //获取“拍照”按钮
        ImageButton captureButton = (ImageButton) findViewById(R.id.button_capture);
        Button button_flash = (Button) findViewById(R.id.button_flash);
        Button button_changecamera = (Button) findViewById(R.id.button_changecamera);

        captureButton.setOnClickListener( //实现录像预览功能
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isRecording) {
                            // stop recording and release camera
                            camera.stopPreview();        //停止预览
                            isPreview = false;         //设置为非预览状态
                            mediaRecorder.stop();  // 停止录像
                            Toast.makeText(MainActivity.this, "照片保存至：" + a, Toast.LENGTH_LONG).show();
                            releaseMediaRecorder(); // 释放madiaRecorder
                            captureButton.setImageResource(R.drawable.capture);//切换到准备录制图片
                            takePicture.setVisibility(View.VISIBLE);//恢复拍照按钮可见性
                            button_changecamera.setVisibility(View.VISIBLE);//恢复前后置摄像头切换按钮的可见性
                            isRecording = false;
                            resetCamera(); //调用重新预览resetCamera()方法
                        } else {
                            if (prepareVideoRecorder()) {
                                mediaRecorder.start();
                                // inform the user that recording has started
                                captureButton.setImageResource(R.drawable.capture_1);
                       //         takePicture.setVisibility(View.GONE);//录像时不可拍照
                                button_changecamera.setVisibility(View.GONE);//录像时摄像头不可切换
                                isRecording = true;
                            } else {
                                // prepare didn't work, release the camera
                                releaseMediaRecorder();
                                // inform user
                            }
                        }
                    }
                }
        );
        button_changecamera.setOnClickListener(new View.OnClickListener() {//转换前后置摄像头，问题切换的if语句不能放里面
            @Override
            public void onClick(View v) {
                    if(change==true) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                            for (int i = 0; i < Camera.getNumberOfCameras(); i++) {
                                Camera.CameraInfo info = new Camera.CameraInfo();//获取照相机的信息
                                Camera.getCameraInfo(i, info);
                                change_2=false;
                                button_flash.setText("闪光灯已关");
                                button_flash.setVisibility(View.GONE);
                                if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {//找到前置摄像头
                                    button_changecamera.setText("前置摄像头");
                                    camera.stopPreview();
                                    camera.release();
                                    camera = null;
                                    camera = Camera.open(i);
                                }
                            }
                        }
                        change=!change;
                    }
                    else{
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                            for (int i = 0; i < Camera.getNumberOfCameras(); i++) {
                                Camera.CameraInfo info = new Camera.CameraInfo();//获取照相机的信息
                                Camera.getCameraInfo(i, info);
                                if (info.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {//找到后置摄像头
                                    button_changecamera.setText("后置摄像头");
                                    button_flash.setVisibility(View.VISIBLE);
                                    camera.stopPreview();
                                    camera.release();
                                    camera = null;
                                    camera = Camera.open(i);
                                }
                            }
                        }
                        change=!change;
                    }
                    if (camera == null) {
                        camera = Camera.open();//找不到打开默认主摄像头
                    }
                        try {
                            camera.setPreviewDisplay(sh);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        camera.startPreview();
                        camera.autoFocus(null);                        //设置自动对焦
                    isPreview = true;                                //设置为预览状态
                }
        });
        button_flash.setOnClickListener(new View.OnClickListener() {//闪光灯开启
            @Override
            public void onClick(View v) {
                if(!change_2){
                if (camera != null) {                                     //相机不为空
                    Camera.Parameters parameters = camera.getParameters();    //获取相机参数
                    parameters = camera.getParameters();
                    parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);//开启
                    camera.setParameters(parameters);
                    button_flash.setText("闪光灯已开");
                }
            }
                else {
                    if (camera != null) {                                     //相机不为空
                        Camera.Parameters parameters = camera.getParameters();    //获取相机参数
                        parameters = camera.getParameters();
                        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);//关闭
                        camera.setParameters(parameters);
                        button_flash.setText("闪光灯已关");
                    }
                }
                change_2=!change_2;
            }
        });
        //实现相机拍照功能
        takePicture.setOnClickListener(new View.OnClickListener() {//照相
            @Override
            public void onClick(View v) {
                if (camera != null) {                                     //相机不为空
                    camera.autoFocus(null);                        //设置自动对焦
                    Camera.Parameters parameters = camera.getParameters();    //获取相机参数
                    parameters = camera.getParameters();
                    parameters.setFlashMode(Camera.Parameters.FLASH_MODE_AUTO);//开启
                    camera.setParameters(parameters);
                    camera.takePicture(null, null, jpeg);                //进行拍照
                }
            }
        });
    }//初始化到此结束
    private boolean prepareVideoRecorder(){//正常6步走
        //camera = Camera.open();
        mediaRecorder = new MediaRecorder();
        // Step 1: Unlock and set camera to MediaRecorder
        camera.unlock();
        mediaRecorder.setCamera(camera);
        // Step 2: Set sources
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        // Step 3: Set a CamcorderProfile (requires API Level 8 or higher)
        mediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH));
        // Step 4: Set output file
        mediaRecorder.setOutputFile(getOutputMediaFile(MEDIA_TYPE_VIDEO).toString());
        // Step 5: Set the preview output
        mediaRecorder.setPreviewDisplay(sh.getSurface());
        // Step 6: Prepare configured MediaRecorder
        try {
            mediaRecorder.prepare();
        } catch (IllegalStateException e) {
            Log.d(TAG, "IllegalStateException preparing MediaRecorder: " + e.getMessage());//这个没什么意思，有没有无所谓
            releaseMediaRecorder();
            return false;
        } catch (IOException e) {
            Log.d(TAG, "IOException preparing MediaRecorder: " + e.getMessage());
            releaseMediaRecorder();
            return false;
        }
        return true;
    }
    //实现将照片保存到系统图库中
    final Camera.PictureCallback jpeg = new Camera.PictureCallback() {  //照片回调函数
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            // 根据拍照所得的数据创建位图
            final Bitmap bm = BitmapFactory.decodeByteArray(data, 0, data.length);
            camera.stopPreview();        //停止预览
            isPreview = false;         //设置为非预览状态
            //将获取当前系统时间设置为照片名称
                 File  file= getOutputMediaFile(MEDIA_TYPE_IMAGE);
            try {  //保存拍到的图片
                FileOutputStream fos = new FileOutputStream(file);  //创建一个文件输出流对象
                //将图片内容压缩为JPEG格式输出到输出流对象中
                bm.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                //将缓冲区中的数据全部写出到输出流中
                fos.flush();
                fos.close();                                        //关闭文件输出流对象
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //将照片插入到系统图库
            try {
                MediaStore.Images.Media.insertImage(MainActivity.this.getContentResolver(),
                        file.getAbsolutePath(), file.getName(), null);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            // 最后通知图库更新
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri uri = Uri.fromFile(file);
            intent.setData(uri);
            MainActivity.this.sendBroadcast(intent);//这个广播的目的就是更新图库
            Toast.makeText(MainActivity.this, "照片保存至：" + file, Toast.LENGTH_LONG).show();
            resetCamera(); //调用重新预览resetCamera()方法

        }
    };
    private static File getOutputMediaFile(int type){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory(), "/DCIM/Camera");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.
        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_"+ timeStamp + ".jpg");
        } else if(type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "VID_"+ timeStamp + ".mp4");
        } else {
            return null;
        }
        a=mediaFile.getName();
        return mediaFile;
    }
    private void resetCamera() {     //创建resetCamera()方法，实现重新预览功能
        if (!isPreview) {            //如果为非预览模式
            camera.startPreview();    //开启预览
            isPreview = true;
        }
    }
    private void releaseMediaRecorder(){
        if (mediaRecorder != null) {
            mediaRecorder.reset();   // clear recorder configuration
            mediaRecorder.release(); // release the recorder object
            mediaRecorder = null;
            camera.lock();           // lock camera for later use
        }
    }
    protected void onPause() {  //当暂停Activity时，停止预览并释放资源
        if (camera != null) {        //如果相机不为空
            camera.stopPreview();    //停止预览
            releaseMediaRecorder();
            camera.release();        //释放资源
        }
        super.onPause();
    }
    /*surfaceHolder他是系统提供的一个用来设置surfaceView的一个对象，而它通过surfaceView.getHolder()这个方法来获得。
     Camera提供一个setPreviewDisplay(SurfaceHolder)的方法来连接*/
        //SurfaceHolder.Callback,这是个holder用来显示surfaceView 数据的接口,他必须实现以下3个方法
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // TODO Auto-generated method stub
    }
    public void surfaceCreated(SurfaceHolder sh) {
        // TODO Auto-generated method stub
        //当surfaceview创建时开启相机
        if (!isPreview) {
            camera = Camera.open(0);                           // 打开相机
            isPreview = true;                                //设置为预览状态
        }
        try {
            camera.setPreviewDisplay(sh);                   // 设置用于显示预览的SurfaceView
            Camera.Parameters parameters = camera.getParameters();    //获取相机参数
            parameters.setPictureFormat(PixelFormat.JPEG);    //指定图片为JPEG图片
            parameters.set("jpeg-quality", 100);            //设置图片的质量
            parameters. setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);//在开机即预览情况下，图片模糊，用于解决对焦不清问题,不好用
            camera.setParameters(parameters);                //重新设置相机参数
            camera.startPreview();                            //开始预览
           //camera.autoFocus(null);//好用
        } catch (IOException e) {                           //输出异常信息
            e.printStackTrace();
        }
    }
    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        //当surfaceview关闭时，关闭预览并释放资源
        camera.stopPreview();
        camera.release();
        camera = null;
        sh = null;
        sv = null;
    }
}

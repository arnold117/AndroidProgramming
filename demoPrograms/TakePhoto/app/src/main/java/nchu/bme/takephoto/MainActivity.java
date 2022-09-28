package nchu.bme.takephoto;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends Activity {
    private Camera camera;                                         //定义相机对象
    private boolean isPreview = false;                            //定义非预览状态
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (!Environment.getExternalStorageState().equals(  //判断手机是否安装SD卡
                Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, "请安装SD卡！", Toast.LENGTH_SHORT).show(); // 提示安装SD卡
        }

        //获取SurfaceView组件，用于显示相机预览
        SurfaceView sv = (SurfaceView) findViewById(R.id.surfaceView);
        final SurfaceHolder sh = sv.getHolder();                            //获取SurfaceHolder对象
        //设置该SurfaceHolder自己不维护缓冲
        sh.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        ImageButton preview = (ImageButton) findViewById(R.id.preview);        //获取“预览”按钮
        ImageButton takePicture = (ImageButton) findViewById(R.id.takephoto);  //获取“拍照”按钮

        preview.setOnClickListener(new View.OnClickListener() {  //实现相机预览功能
            @Override
            public void onClick(View v) {
                // 如果相机为非预览模式，则打开相机
                if (!isPreview) {
                    camera = Camera.open();                           // 打开相机
                    isPreview = true;                                //设置为预览状态
                }
                try {
                    camera.setPreviewDisplay(sh);                   // 设置用于显示预览的SurfaceView
                    Camera.Parameters parameters = camera.getParameters();    //获取相机参数
                    parameters.setPictureFormat(PixelFormat.JPEG);    //指定图片为JPEG图片
                    parameters.set("jpeg-quality", 80);            //设置图片的质量
                    camera.setParameters(parameters);                //重新设置相机参数
                    camera.startPreview();                            //开始预览
                    camera.autoFocus(null);                        //设置自动对焦
                } catch (IOException e) {                           //输出异常信息
                    e.printStackTrace();
                }
            }
        });

        //实现相机拍照功能
        takePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (camera != null) {                                     //相机不为空
                    camera.takePicture(null, null, jpeg);                //进行拍照
                }
            }
        });
    }


    //实现将照片保存到系统图库中
    final Camera.PictureCallback jpeg = new Camera.PictureCallback() {  //照片回调函数
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            // 根据拍照所得的数据创建位图
            final Bitmap bm = BitmapFactory.decodeByteArray(data, 0,
                    data.length);
            camera.stopPreview();        //停止预览
            isPreview = false;         //设置为非预览状态
            //获取sd卡根目录
            File appDir = new File(Environment.getExternalStorageDirectory(), "/DCIM/Camera/");
            if (!appDir.exists()) {      //如果该目录不存在
                appDir.mkdir();          //就创建该目录

            }
            //将获取当前系统时间设置为照片名称
            String fileName = System.currentTimeMillis() + ".jpg";
            File file = new File(appDir, fileName);  //创建文件对象

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
                        file.getAbsolutePath(), fileName, null);
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

    private void resetCamera() {     //创建resetCamera()方法，实现重新预览功能
        if (!isPreview) {            //如果为非预览模式
            camera.startPreview();    //开启预览
            isPreview = true;
        }
    }

    @Override
    protected void onPause() {  //当暂停Activity时，停止预览并释放资源
        if (camera != null) {        //如果相机不为空
            camera.stopPreview();    //停止预览
            camera.release();        //释放资源
        }
        super.onPause();
    }


}

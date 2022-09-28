package nchu.bme.wearhat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class HatView extends View {
    public float bitmapX; // 帽子显示位置的X坐标
    public float bitmapY; // 帽子显示位置的Y坐标
    public HatView(Context context) {//重写构造方法
        super(context);
        bitmapX = 65; // 设置帽子的默认显示位置的X坐标
        bitmapY = 0; // 设置帽子的默认显示位置的Y坐标
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint(); //创建Paint对象
        //根据图片生成位图对象
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.hat);
        canvas.drawBitmap(bitmap, bitmapX, bitmapY, paint); // 绘制帽子
        if (bitmap.isRecycled()) { // 判断图片是否回收
            bitmap.recycle(); // 强制回收图片
        }
    }
}


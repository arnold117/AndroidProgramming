package nchu.bme.followrabit;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
public class RabbitView extends View {
    public float bitmapX; 						// 兔子显示位置的X坐标
    public float bitmapY; 						// 兔子显示位置的Y坐标
    public RabbitView(Context context) { 			// 重写构造方法
        super(context);
        bitmapX = 210; 							// 设置兔子的默认显示位置的X坐标
        bitmapY = 130; 							// 设置兔子的默认显示位置的Y坐标
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint(); 					// 创建并实例化Paint的对象
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),
                R.mipmap.rabbit); 					// 根据图片生成位图对象
        canvas.drawBitmap(bitmap, bitmapX, bitmapY, paint); // 绘制小兔子
        if (bitmap.isRecycled()) { 					// 判断图片是否回收
            bitmap.recycle();					// 强制回收图片
        }
    }
}

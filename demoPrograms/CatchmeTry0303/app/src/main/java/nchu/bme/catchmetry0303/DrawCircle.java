package nchu.bme.catchmetry0303;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2016/11/21.
 */

public class DrawCircle extends View {
    Paint paint;
    private float x=300,y=300,ox,oy;
    public DrawCircle(Context context) {
        super(context);
    }

    public DrawCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint = new Paint();   //初始化画笔
        paint.setColor(Color.RED);// 设置红色
        paint.setAntiAlias(true);// 设置画笔的锯齿效果。 true是去除，大家一看效果就明白了
        canvas.drawCircle(x, y, 80, paint);  // 大圆
        Bitmap bitmap=null;                         //Bitmap对象
        ox = x;
        oy = y;
    }

    /**
     *此方法用于接收MainActivity传递过来的坐标点
     * */
    public void setXY(float x,float y){
        this.x = x;
        this.y = y;
        invalidate();
    }
    public Float[] getOldValues(){
        return new Float[]{ox,oy};
    }
}

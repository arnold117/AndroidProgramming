package nchu.bme.ecgtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;

import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.util.ArrayList;

public class XDTView extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder holder = null; //控制对象
    //折现的颜色
    protected int mLineColor = Color.parseColor("#76f112");
    //画笔
    protected Paint mPaint;
    //心电图折现
    protected Path mPath;
    //画线点坐标
    int x=0;
    int y=0;
    int k=0;
    //网格颜色
    protected int mGridColor = Color.parseColor("#1b4200");
    //小网格颜色
    protected int mSGridColor = Color.parseColor("#092100");
    //背景颜色
    protected int mBackgroundColor = Color.BLACK;
    //自身的大小
    protected int mWidth,mHeight;

    //网格宽度
    protected int mGridWidth = 50;
    //小网格的宽度
    protected int mSGridWidth = 10;
    private ArrayList<Point> ps = new ArrayList<>();

    public XDTView(Context context, AttributeSet attr) {
        super(context, attr);
        // TODO Auto-generated constructor stub
        holder = getHolder();
        holder.addCallback(this);
        mPaint = new Paint();
        mPath = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = w;
        mHeight = h;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        // TODO Auto-generated method stub

    }
    //SurfaceView创建时候调用
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        new Thread(new MyLoop()).start();
    }
    //绘制背景
    private void initBackground(Canvas canvas) {
        canvas.drawColor(mBackgroundColor);
        //画小网格
        //竖线个数
        int vSNum = mWidth /mSGridWidth;
        //横线个数
        int hSNum = mHeight/mSGridWidth;
        mPaint.setColor(mSGridColor);
        mPaint.setStrokeWidth(2);
        //画竖线
        for(int i = 0;i<vSNum+1;i++){
            canvas.drawLine(i*mSGridWidth,0,i*mSGridWidth,mHeight,mPaint);
        }
        //画横线
        for(int i = 0;i<hSNum+1;i++){
            canvas.drawLine(0,i*mSGridWidth,mWidth,i*mSGridWidth,mPaint);
        }
        //竖线个数
        int vNum = mWidth / mGridWidth;
        //横线个数
        int hNum = mHeight / mGridWidth;
        mPaint.setColor(mGridColor);
        mPaint.setStrokeWidth(2);
        //画竖线
        for(int i = 0;i<vNum+1;i++){
            canvas.drawLine(i*mGridWidth,0,i*mGridWidth,mHeight,mPaint);
        }
        //画横线
        for(int i = 0;i<hNum+1;i++){
            canvas.drawLine(0,i*mGridWidth,mWidth,i*mGridWidth,mPaint);
        }

    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub

    }

    public void doDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.draw(canvas);
        initBackground(canvas);
        // 重置path
        mPath.reset();
        //用path模拟一个心电图样式
        mPath.moveTo(0, mHeight/2);
        for (int i=0;i<ps.size();i++){
            int x =  ps.get(i).x;
            int y = ps.get(i).y;
            mPath.lineTo(x,y);
        }
        //设置画笔style
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(mLineColor);
        mPaint.setStrokeWidth(5);
        //绘制线
        canvas.drawPath(mPath, mPaint);
    }


    //子线程任务
    class MyLoop implements Runnable {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            while (true) {//true执行方法false停止线程任务
                try {
                    //判断什么时候波动
                    k ++;
                    if(k%20==0){
                        //随机获取高度坐标
                        y=(int)(Math.random()*mHeight/2);
                        //随机增加x坐标
                        x+=10+(int)(Math.random()*50);
                    }else {
                        //高度坐标的半
                        y=mHeight/2;
                    }
                    //添加坐标到集合
                    ps.add(new Point(x,y));
                    //增加坐标
                    x++;
                    //删除以前生成的坐标实现移动
                    rovm();
                    //获得Canvas对象
                    Canvas c = holder.lockCanvas();
                    //主要绘制方法
                    doDraw(c);
                    //绘制完成后调用更新界面
                    holder.unlockCanvasAndPost(c);
                    Thread.sleep(30);
                } catch (Exception e) {
                }
            }
        }
    }
    //删除以前生成的坐标
    private void rovm() {
        for (int i=0;i<ps.size();i++){
            int x =ps.get(i).x;
            int y = ps.get(i).y;
            if(x==0){
                //移除坐标点
                ps.remove(i);
            }else {
                x--;
                //减少x轴坐标
                ps.set(i,new Point(x,y));
            }
        }
    }

}


package nchu.bme.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {
    final int NOTIFYID = 0x123;            //通知的ID

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取通知管理器服务，用于发送通知
        final NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // 创建一个Notification对象
        Notification.Builder notification = new Notification.Builder(this);
        // 设置打开该通知，该通知自动消失
        notification.setAutoCancel(true);
        // 设置通知的图标
        notification.setSmallIcon(R.drawable.packet);
        // 设置通知内容的标题
        notification.setContentTitle("奖励百万红包！！！");
        // 设置通知内容
        notification.setContentText("点击查看详情！");
        //设置使用系统默认的声音、默认震动
        notification.setDefaults(Notification.DEFAULT_SOUND
                | Notification.DEFAULT_VIBRATE);
        //设置发送时间
        notification.setWhen(System.currentTimeMillis());
        // 创建一个启动其他Activity的Intent
        Intent intent = new Intent(MainActivity.this
                , DetailActivity.class);
        PendingIntent pi = PendingIntent.getActivity(
                MainActivity.this, 0, intent, 0);
        //设置通知栏点击跳转
        notification.setContentIntent(pi);
        //发送通知
        notificationManager.notify(NOTIFYID, notification.build());
    }
}

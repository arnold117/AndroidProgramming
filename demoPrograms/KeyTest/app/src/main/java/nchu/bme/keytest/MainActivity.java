package nchu.bme.keytest;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

public class MainActivity extends Activity {
    private long exitTime = 0; //退出时间变量值
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //判断是否单击了返回按键
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();         //创建并调用退出方法
            return true;   //拦截返回键
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) { //计算按键时间差是否大于两秒
            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0); //销毁强制退出
        }
    }

}
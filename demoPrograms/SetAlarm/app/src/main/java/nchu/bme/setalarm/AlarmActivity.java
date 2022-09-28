package nchu.bme.setalarm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AlarmActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlertDialog alert = new AlertDialog.Builder(this).create();
        alert.setIcon(R.drawable.alarm);						//设置对话框的图标
        alert.setTitle("传递正能量：");							//设置对话框的标题
        alert.setMessage("要么出众，要么出局");			    //设置要显示的内容
        //添加确定按钮
        alert.setButton(DialogInterface.BUTTON_POSITIVE,"确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                });
        alert.show();  									        // 显示对话框

    }
}

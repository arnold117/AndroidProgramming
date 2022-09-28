package nchu.bme.longpresstest;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = (ImageView) findViewById(R.id.imageView); //获取图片组件
        imageView.setOnLongClickListener(new View.OnLongClickListener() { //创建长按监听事件
            @Override
            public boolean onLongClick(View v) {
                registerForContextMenu(v); //将长按事件注册菜单中
                openContextMenu(v); //打开菜单
                return true;
            }
        });
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) { //创建菜单
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add("收藏"); //为菜单添加参数
        menu.add("举报");
    }
}

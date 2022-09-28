package nchu.bme.radarscan;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Animation rotate = AnimationUtils.loadAnimation(this, R.anim.needle_anim_right);//初始化动画
        imageView = (ImageView) findViewById(R.id.iv_bg); //绑定控件的id
        imageView.startAnimation(rotate);//给图片设置动画
    }
}
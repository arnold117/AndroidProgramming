package nchu.bme.paintalien;
import android.graphics.drawable.AnimationDrawable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private AnimationDrawable animationDrawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.max);//实例化控件对象
        animationDrawable = (AnimationDrawable) imageView.getDrawable();
    }
    @Override
    protected void onResume() {
        super.onResume();
        animationDrawable.start();//开启帧动画
    }
    @Override
    protected void onPause() {
        super.onPause();
        animationDrawable.stop();//关闭帧动画
    }
}

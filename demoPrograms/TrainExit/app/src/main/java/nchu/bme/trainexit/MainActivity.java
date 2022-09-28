package nchu.bme.trainexit;

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
        final Animation animation= AnimationUtils.loadAnimation(this,R.anim.anim_translate);  //初始化动画
        imageView = (ImageView) findViewById(R.id.iv_train);  //绑定id
        imageView.startAnimation(animation);  //给图片设置动画
    }
}
package nchu.bme.viewflipper_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;
public class MainActivity extends AppCompatActivity  {
    ViewFlipper flipper;
    Animation[] animation=new Animation[4];
    private  int[] images=new int[]{R.drawable.i1,R.drawable.i2,R.drawable.i3,R.drawable.i4};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flipper=(ViewFlipper)findViewById(R.id.flipper);
        for(int i=0;i<images.length;i++)
        {
            ImageView imageView=new ImageView(this);
            imageView.setImageResource(images[i]);
            flipper.addView(imageView);
        }
        flipper.setInAnimation(this,R.anim.slide_in_right);
        flipper.setOutAnimation(this,R.anim.slide_out_left);
        flipper.setFlipInterval(3000);
        flipper.startFlipping();
        animation[0]=AnimationUtils.loadAnimation(this,R.anim.slide_in_left);
        animation[1]=AnimationUtils.loadAnimation(this,R.anim.slide_out_left);
        animation[2]=AnimationUtils.loadAnimation(this,R.anim.slide_in_right);
        animation[3]=AnimationUtils.loadAnimation(this,R.anim.slide_out_right);
    }
}


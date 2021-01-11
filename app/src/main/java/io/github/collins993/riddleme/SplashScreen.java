package io.github.collins993.riddleme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.concurrent.Executor;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIMER = 5000;

    RelativeLayout layoutMove;
    Animation sideAnim, bottomAnim;
    TextView title;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        //Initialization
        layoutMove = findViewById(R.id.layout);
        title = findViewById(R.id.title);
        image = findViewById(R.id.splash_image);
        sideAnim = AnimationUtils.loadAnimation(this, R.anim.side_anim);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_anim);
        //Assign to Elements
        title.setAnimation(sideAnim);
        image.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(SplashScreen.this, GameScreen.class));
                finish();
            }
        }, SPLASH_TIMER);


    }
}
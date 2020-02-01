package androidapp.yashthaluri.com.yeoman;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidapp.yashthaluri.com.yeoman.databinding.ActivitySplashScreenBinding;

public class SplashScreenActivity extends AppCompatActivity {
    ActivitySplashScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_splash_screen);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this,WelcomeActivity.class);
                startActivity(intent);
                finish();
            }
        },5000);

        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.splash_anim);
        binding.splashLogo.startAnimation(myanim);

    }
}

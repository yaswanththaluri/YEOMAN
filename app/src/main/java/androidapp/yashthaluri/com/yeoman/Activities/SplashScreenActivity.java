package androidapp.yashthaluri.com.yeoman.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidapp.yashthaluri.com.yeoman.Models.ProfileHelper;
import androidapp.yashthaluri.com.yeoman.R;
import androidapp.yashthaluri.com.yeoman.databinding.ActivitySplashScreenBinding;

public class SplashScreenActivity extends AppCompatActivity {
    ActivitySplashScreenBinding binding;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        database = FirebaseDatabase.getInstance();
        reference = database.getReference();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (user!=null)
                {
                    getUserDetails();
                }
                else
                {
                    Intent intent = new Intent(SplashScreenActivity.this,WelcomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },5000);

        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.splash_anim);
        binding.splashLogo.startAnimation(myanim);

    }


    public void getUserDetails()
    {
        try
        {
            user = auth.getCurrentUser();
            String uid = user.getUid();
            reference.child("users").child(uid).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                {
                    ProfileHelper profileHelper = dataSnapshot.getValue(ProfileHelper.class);

                    if (profileHelper == null)
                    {
                        Intent i1 = new Intent(SplashScreenActivity.this, ProfileActivity.class);
                        startActivity(i1);
                    }
                    else
                    {
                        String isProfileFilled = profileHelper.getIsProfileFilled();

                        if (isProfileFilled.equals("yes"))
                        {
                            Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                            startActivity(i);
                        }
                        else
                        {
                            Intent i = new Intent(SplashScreenActivity.this, ProfileActivity.class);
                            startActivity(i);
                        }
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        catch (Exception e)
        {
            Intent i = new Intent(this, ProfileActivity.class);
            startActivity(i);
        }
    }
}

package androidapp.yashthaluri.com.yeoman.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidapp.yashthaluri.com.yeoman.Models.ProfileHelper;
import androidapp.yashthaluri.com.yeoman.R;
import androidapp.yashthaluri.com.yeoman.WeekendFarmerBooking;
import androidapp.yashthaluri.com.yeoman.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private FirebaseUser user;
    private FirebaseAuth auth;
    ActivityMainBinding binding;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private String role="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        binding.profileAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, EditProfileDetailActivity.class);
                startActivity(i);
            }
        });

        binding.bookLabour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (role.equals("Labour"))
                {
                    Intent i = new Intent(MainActivity.this, MarkAbsentActivity.class);
                    startActivity(i);
                }
                else if (role.equals("Farmer"))
                {
                    Intent i = new Intent(MainActivity.this, BookActivity.class);
                    startActivity(i);
                }
            }
        });

        binding.myBookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MyBookings.class);
                startActivity(i);
            }
        });

        binding.logoutUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                finishAffinity();
            }
        });

        binding.card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (role.equals("Farmer")) {
                    Intent i = new Intent(MainActivity.this, WeekendFarmerBooking.class);
                    startActivity(i);
                }
            }
        });

        binding.card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(i);
            }
        });

        if (role.equals("Farmer"))
        {
            binding.card4Img.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        fillProfileData();
    }

    public void fillProfileData()
    {
        reference.child("users").child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ProfileHelper helper = dataSnapshot.getValue(ProfileHelper.class);
                binding.dshbrdProfileName.setText(helper.getUserName());
                role = helper.getRole();
                if (role.equals("Labour"))
                {
                    binding.card2Img.setImageResource(R.drawable.iconabsent);
                }
                else if (role.equals("Farmer"))
                {
                    binding.card2Img.setImageResource(R.drawable.iconlabour);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        if(user.getPhotoUrl()==null)
        {
            binding.dshbrdProfilePic.setImageResource(R.drawable.leaf);
        }
        else
        {
            Glide.with(this).applyDefaultRequestOptions(RequestOptions.circleCropTransform()).load(user.getPhotoUrl()).into(binding.dshbrdProfilePic);

        }
    }
}

package androidapp.yashthaluri.com.yeoman.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidapp.yashthaluri.com.yeoman.Adapter.DetailsWorkerAdapter;
import androidapp.yashthaluri.com.yeoman.Models.DetailWorkerModel;
import androidapp.yashthaluri.com.yeoman.Models.ProfileHelper;
import androidapp.yashthaluri.com.yeoman.R;
import androidapp.yashthaluri.com.yeoman.databinding.ActivityDetailWorkersShowBinding;

public class DetailWorkersShowActivity extends AppCompatActivity {
    ActivityDetailWorkersShowBinding binding;
    DetailsWorkerAdapter detailsWorkerAdapter;
    public static ArrayList<DetailWorkerModel>detailWorkerModels = new ArrayList<DetailWorkerModel>();
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private String villageName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_detail_workers_show);

        Bundle bundle = getIntent().getExtras();
        villageName = bundle.getString("villageName");

        binding.detailRecyclerview.setHasFixedSize(true);
        binding.detailRecyclerview.setLayoutManager(new GridLayoutManager(this,2));
        detailsWorkerAdapter= new DetailsWorkerAdapter(detailWorkerModels, this);
        binding.detailRecyclerview.setAdapter(detailsWorkerAdapter);


        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("users");

    }

    private void data() {
        Log.i("village name selecte", villageName);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    ProfileHelper helper = ds.getValue(ProfileHelper.class);
                    if (helper.getVillage().equals(villageName) && helper.getRole().equals("Labour"))
                    {
                        Log.i("user", helper.getUserName());
                        detailWorkerModels.add(new DetailWorkerModel(R.drawable.leaf,helper.getUserName(),"Farming,Cropping,","4.7"));
                        binding.detailRecyclerview.setAdapter(detailsWorkerAdapter);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        data();
        detailWorkerModels.clear();
    }

    @Override
    protected void onResume() {
        super.onResume();
        detailWorkerModels.clear();
    }
}

package androidapp.yashthaluri.com.yeoman.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Adapter;

import java.util.ArrayList;

import androidapp.yashthaluri.com.yeoman.Adapter.DetailsWorkerAdapter;
import androidapp.yashthaluri.com.yeoman.Models.DetailWorkerModel;
import androidapp.yashthaluri.com.yeoman.R;
import androidapp.yashthaluri.com.yeoman.databinding.ActivityDetailWorkersShowBinding;

public class DetailWorkersShowActivity extends AppCompatActivity {
    ActivityDetailWorkersShowBinding binding;
    DetailsWorkerAdapter detailsWorkerAdapter;
  public static ArrayList<DetailWorkerModel>detailWorkerModels = new ArrayList<DetailWorkerModel>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_detail_workers_show);
         binding.detailRecyclerview.setHasFixedSize(true);
         binding.detailRecyclerview.setLayoutManager(new GridLayoutManager(this,2));
         detailsWorkerAdapter= new DetailsWorkerAdapter(detailWorkerModels, this);
         binding.detailRecyclerview.setAdapter(detailsWorkerAdapter);
         data();



    }

    private void data() {
    detailWorkerModels.add(new DetailWorkerModel(R.drawable.leaf,"Manikyapavan","Farming,Cropping,","4.7"));
        detailWorkerModels.add(new DetailWorkerModel(R.drawable.leaf,"Manikyapavan","Farming","4.7"));
        detailWorkerModels.add(new DetailWorkerModel(R.drawable.leaf,"Manikyapavan","Farming","4.7"));
        detailWorkerModels.add(new DetailWorkerModel(R.drawable.leaf,"Manikyapavan","Farming","4.7"));
        detailWorkerModels.add(new DetailWorkerModel(R.drawable.leaf,"Manikyapavan","Farming","4.7"));
        detailWorkerModels.add(new DetailWorkerModel(R.drawable.leaf,"Manikyapavan","Farming","4.7"));
        detailWorkerModels.add(new DetailWorkerModel(R.drawable.leaf,"Manikyapavan","Farming","4.7"));
        detailWorkerModels.add(new DetailWorkerModel(R.drawable.leaf,"Manikyapavan","Farming","4.7"));
    }


}

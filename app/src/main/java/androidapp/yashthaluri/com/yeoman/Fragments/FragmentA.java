package androidapp.yashthaluri.com.yeoman.Fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import androidapp.yashthaluri.com.yeoman.Adapter.DetailsWorkerAdapter;
import androidapp.yashthaluri.com.yeoman.Adapter.RecyclerViewAdapter;
import androidapp.yashthaluri.com.yeoman.Models.DetailWorkerModel;
import androidapp.yashthaluri.com.yeoman.Models.JobDetailsHelper;
import androidapp.yashthaluri.com.yeoman.R;


public class FragmentA extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    RecyclerViewAdapter recyclerViewAdapter;
    public static ArrayList<JobDetailsHelper>jobDetailsHelpers = new ArrayList<JobDetailsHelper>();


    RecyclerView recyclerView;

    public FragmentA() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_a, container, false);
return view;


        //return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);
       recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewAdapter= new RecyclerViewAdapter(jobDetailsHelpers, getContext());
        recyclerView.setAdapter(recyclerViewAdapter);
        data();

    }

    private void data() {
        jobDetailsHelpers.add(new JobDetailsHelper("Manikyapavan","Software Intern at Eclerx"));
        jobDetailsHelpers.add(new JobDetailsHelper("Manikyapavan","Software Intern at Eclerx"));
        jobDetailsHelpers.add(new JobDetailsHelper("Manikyapavan","Software Intern at Eclerx"));
        jobDetailsHelpers.add(new JobDetailsHelper("Manikyapavan","Software Intern at Eclerx"));
        jobDetailsHelpers.add(new JobDetailsHelper("Manikyapavan","Software Intern at Eclerx"));
        jobDetailsHelpers.add(new JobDetailsHelper("Manikyapavan","Software Intern at Eclerx"));
        jobDetailsHelpers.add(new JobDetailsHelper("Manikyapavan","Software Intern at Eclerx"));
        jobDetailsHelpers.add(new JobDetailsHelper("Manikyapavan","Software Intern at Eclerx"));

    }
}
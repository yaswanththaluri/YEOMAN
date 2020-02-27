package androidapp.yashthaluri.com.yeoman.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidapp.yashthaluri.com.yeoman.Adapter.DetailsWorkerAdapter;
import androidapp.yashthaluri.com.yeoman.Adapter.RecyclerViewAdapter;
import androidapp.yashthaluri.com.yeoman.Models.BookJobHelper;
import androidapp.yashthaluri.com.yeoman.Models.DetailWorkerModel;
import androidapp.yashthaluri.com.yeoman.Models.FarmerBookingHistoryHelper;
import androidapp.yashthaluri.com.yeoman.Models.JobDetailsHelper;
import androidapp.yashthaluri.com.yeoman.Models.ProfileHelper;
import androidapp.yashthaluri.com.yeoman.R;


public class FragmentA extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    RecyclerViewAdapter recyclerViewAdapter;
    public static ArrayList<JobDetailsHelper>jobDetailsHelpers = new ArrayList<JobDetailsHelper>();
    private DatabaseReference reference;
    private FirebaseAuth auth;
    private FirebaseUser user;

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


        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewAdapter= new RecyclerViewAdapter(jobDetailsHelpers, getContext());
        recyclerView.setAdapter(recyclerViewAdapter);

    }




    public static void getAllBookings(final String childToAccess)
    {
        FirebaseAuth  auth= FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference().child(childToAccess).child(user.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren())
                {
                    if (childToAccess.equals("FarmerBookings"))
                    {
                        FarmerBookingHistoryHelper helper = ds.getValue(FarmerBookingHistoryHelper.class);
                        jobDetailsHelpers.add(new JobDetailsHelper(helper.getLabourName(),helper.getWorkDesc()));
                    }
                    else
                    {
                        BookJobHelper helper = ds.getValue(BookJobHelper.class);
                        jobDetailsHelpers.add(new JobDetailsHelper(helper.getFarmerName(),helper.getWorkDesc()));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public static void getRole()
    {
        FirebaseAuth  auth= FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference().child("users").child(user.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ProfileHelper helper = dataSnapshot.getValue(ProfileHelper.class);
                if (helper.getRole().equals("Farmer"))
                {
                    getAllBookings("FarmerBookings");
                }
                else if(helper.getRole().equals("Labour"))
                {
                    getAllBookings("labourJobs");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        getRole();
    }
}
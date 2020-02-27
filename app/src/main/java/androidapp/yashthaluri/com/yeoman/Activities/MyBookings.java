package androidapp.yashthaluri.com.yeoman.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidapp.yashthaluri.com.yeoman.Adapter.BookingsAdapter;
import androidapp.yashthaluri.com.yeoman.Models.BookJobHelper;
import androidapp.yashthaluri.com.yeoman.Models.FarmerBookingHistoryHelper;
import androidapp.yashthaluri.com.yeoman.Models.MyBookingsHelper;
import androidapp.yashthaluri.com.yeoman.Models.ProfileHelper;
import androidapp.yashthaluri.com.yeoman.R;

public class MyBookings extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseUser user;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private BookingsAdapter mNotifyAdapter;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bookings);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference().child("notifications");

        list = findViewById(R.id.bookingList);

        List<MyBookingsHelper> notify = new ArrayList<>();
        mNotifyAdapter = new BookingsAdapter(MyBookings.this, R.layout.bokkeditem, notify);

        list.setAdapter(mNotifyAdapter);
    }

    public  void getAllBookings(final String childToAccess)
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
                        mNotifyAdapter.add(new MyBookingsHelper(helper.getPhoneNumber(), helper.getImageUrl(),helper.getLabourName()));
                    }
                    else
                    {
                        BookJobHelper helper = ds.getValue(BookJobHelper.class);
                        mNotifyAdapter.add(new MyBookingsHelper(helper.getPhoneNumber(), helper.getImageUrl(),helper.getFarmerName()));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void getRole()
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
    protected void onStart() {
        super.onStart();
        getRole();
    }
}

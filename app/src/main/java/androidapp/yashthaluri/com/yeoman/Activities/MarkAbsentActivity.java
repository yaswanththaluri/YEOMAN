package androidapp.yashthaluri.com.yeoman.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import androidapp.yashthaluri.com.yeoman.R;
import androidapp.yashthaluri.com.yeoman.databinding.ActivityMarkAbsentBinding;

public class MarkAbsentActivity extends AppCompatActivity {


    private Calendar calendar;
    private SimpleDateFormat simpleDateFormat;
    private int year, month, day;
    ActivityMarkAbsentBinding binding;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private ArrayList<String> absentRecArray;
    private ArrayAdapter<String> arrayAdapter;
    private ListView absListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_mark_absent);

        binding.selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectDate();
            }
        });
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        absListView = binding.absList;
        absentRecArray = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<String>(this, R.layout.absentitem, R.id.dateAbsent, absentRecArray);


        binding.applyLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
                Date selectedDate = null;
                try {
                    selectedDate = formatter.parse(binding.selectDate.getText().toString());
                } catch (ParseException e) {
                    Toast.makeText(MarkAbsentActivity.this, "All fields are mandatory.", Toast.LENGTH_SHORT).show();
                }
                if (selectedDate.before(new Date()))
                {
                    Toast.makeText(MarkAbsentActivity.this, "Date should not be in past.", Toast.LENGTH_SHORT).show();
                }
                else
                applyForLeave(binding.selectDate.getText().toString().replace(" ", ""));
            }
        });
    }


    private void selectDate() {
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int y, int m, int d) {

                switch (m + 1) {
                    case 1:
                        binding.selectDate.setText(d + "/01/" + y);
                        break;
                    case 2:
                        binding.selectDate.setText(d + "/02/" + y);
                        break;
                    case 3:
                        binding.selectDate.setText(d + "/03/" + y);
                        break;
                    case 4:
                        binding.selectDate.setText(d + "/04/" + y);
                        break;
                    case 5:
                        binding.selectDate.setText(d + "/05/" + y);
                        break;
                    case 6:
                        binding.selectDate.setText(d + "/06/" + y);
                        break;
                    case 7:
                        binding.selectDate.setText(d + "/07/" + y);
                        break;
                    case 8:
                        binding.selectDate.setText(d + "/08/" + y);
                        break;
                    case 9:
                        binding.selectDate.setText(d + "/09/" + y);
                        break;
                    case 10:
                        binding.selectDate.setText(d + "/10/" + y);
                        break;
                    case 11:
                        binding.selectDate.setText(d + "/11/" + y);
                        break;
                    case 12:
                        binding.selectDate.setText(d + "/12/" + y);
                        break;
                }
            }
        }, year, month, day);
        dialog.show();
    }


    public void applyForLeave(String date)
    {
        reference.child("LeaveApplications").child(user.getUid()).child(date.replace('/', '-')).setValue("Absent");
        Toast.makeText(MarkAbsentActivity.this, "Leave Application success", Toast.LENGTH_SHORT).show();
        binding.selectDate.setText("");
    }


    public void getAbsentRecords()
    {
        reference.child("LeaveApplications").child(user.getUid()).orderByKey().addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        arrayAdapter.clear();
                        for (DataSnapshot d: dataSnapshot.getChildren())
                        {
                            absentRecArray.add(d.getKey());
                            absListView.setAdapter(arrayAdapter);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                }
        );
    }


    @Override
    protected void onStart() {
        super.onStart();
        getAbsentRecords();
    }
}

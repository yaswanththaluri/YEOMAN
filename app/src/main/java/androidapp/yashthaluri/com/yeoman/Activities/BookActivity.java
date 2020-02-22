package androidapp.yashthaluri.com.yeoman.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import androidapp.yashthaluri.com.yeoman.Models.ProfileHelper;
import androidapp.yashthaluri.com.yeoman.R;
import androidapp.yashthaluri.com.yeoman.databinding.ActivityBookBinding;

public class BookActivity extends AppCompatActivity {
    ActivityBookBinding binding;
    private Calendar calendar;
    private SimpleDateFormat simpleDateFormat;
    private int year, month, day;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private List<String> categories;
    private Spinner spinner;
    private ArrayAdapter<String> dataAdapter;
    private String empTypeFilter="None";
    private String unsTypeFilter="None";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_book);
        simpleDateFormat = new SimpleDateFormat("dd:MMM:YYYY");
        binding.selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectDate();
            }
        });
        spinner = binding.selectVillage;
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("users");


        categories = new ArrayList<String>();

        binding.Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String village = binding.selectVillage.getSelectedItem().toString();
                String date = binding.selectDate.getText().toString();
                empTypeFilter = binding.selectEmpType.getSelectedItem().toString();
                if(empTypeFilter.equals("Unskilled Labour"))
                    unsTypeFilter = binding.unsType.getSelectedItem().toString();
                else
                    unsTypeFilter = "None";
                if (village!=null && date!=null)
                {
                    Intent i = new Intent(BookActivity.this, DetailWorkersShowActivity.class);
                    i.putExtra("villageName", village);
                    i.putExtra("searchDate", date);
                    i.putExtra("employementType", empTypeFilter);
                    i.putExtra("unskilledType", unsTypeFilter);
                    startActivity(i);
                }
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
                        binding.selectDate.setText(d + " Jan " + y);
                        break;
                    case 2:
                        binding.selectDate.setText(d + " Feb " + y);
                        break;
                    case 3:
                        binding.selectDate.setText(d + " March " + y);
                        break;
                    case 4:
                        binding.selectDate.setText(d + " April " + y);
                        break;
                    case 5:
                        binding.selectDate.setText(d + " May " + y);
                        break;
                    case 6:
                        binding.selectDate.setText(d + " June " + y);
                        break;
                    case 7:
                        binding.selectDate.setText(d + " July " + y);
                        break;
                    case 8:
                        binding.selectDate.setText(d + " Aug " + y);
                        break;
                    case 9:
                        binding.selectDate.setText(d + " Sep " + y);
                        break;
                    case 10:
                        binding.selectDate.setText(d + " Oct " + y);
                        break;
                    case 11:
                        binding.selectDate.setText(d + " Nov " + y);
                        break;
                    case 12:
                        binding.selectDate.setText(d + " Dec " + y);
                        break;
                }
            }
        }, year, month, day);
        dialog.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        populateVillageNames();

    }

    public void populateVillageNames()
    {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot ds : dataSnapshot.getChildren())
                    {
                        ProfileHelper helper = ds.getValue(ProfileHelper.class);
                        Log.i("village name", ""+helper.getVillage());
                        if(!categories.contains(helper.getVillage()))
                        {
                            categories.add(helper.getVillage());
                            dataAdapter = new ArrayAdapter<String>(BookActivity.this, android.R.layout.simple_spinner_item, categories);
                            spinner.setAdapter(dataAdapter);
                        }
                    }
                }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}

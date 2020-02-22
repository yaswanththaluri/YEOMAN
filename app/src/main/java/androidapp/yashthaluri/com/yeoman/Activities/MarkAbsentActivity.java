package androidapp.yashthaluri.com.yeoman.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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

        binding.applyLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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


    public void applyForLeave(String date)
    {
        reference.child("LeaveApplications").child(user.getUid()).child(date).setValue("Absent");
        Toast.makeText(MarkAbsentActivity.this, "Leave Application success", Toast.LENGTH_SHORT).show();
        binding.selectDate.setText("");
    }

}

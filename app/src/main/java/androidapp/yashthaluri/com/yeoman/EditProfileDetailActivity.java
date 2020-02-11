package androidapp.yashthaluri.com.yeoman;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import androidapp.yashthaluri.com.yeoman.databinding.ActivityEditProfileDetailBinding;

public class EditProfileDetailActivity extends AppCompatActivity {
    ActivityEditProfileDetailBinding binding;
    private Calendar calendar;
    private SimpleDateFormat simpleDateFormat;
    private int year, month, day;
    private LinearLayout info;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_edit_profile_detail);
        
        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.profileDetailsShow.setVisibility(View.INVISIBLE);
                binding.visibleEditprofile.setVisibility(View.VISIBLE);
            }
        });
        simpleDateFormat = new SimpleDateFormat("dd:MMM:YYYY");

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        dialog = new ProgressDialog(this);
        dialog.setCanceledOnTouchOutside(false);

        binding.dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectBirthDate();
            }
        });

        binding.submitBTN.setEnabled(false);
        addChangeListener();
        binding.submitBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.submitBTN.isEnabled()) {
                    if (TextUtils.isEmpty(binding.fullName.getText().toString())) {
                        binding.fullName.setError("Please enter your First Name");
                        binding.fullName.requestFocus();
                    } else if (TextUtils.isEmpty(binding.dob.getText().toString())) {
                        binding.dob.setError("Please select your Date of Birth");
                        binding.dob.requestFocus();
                    } else if (TextUtils.isEmpty(binding.pincode.getText().toString())) {
                        binding.pincode.setError("Please enter your PIN CODE");
                        binding.pincode.requestFocus();
                    } else if (TextUtils.isEmpty(binding.address.getText().toString())) {
                        binding.address.setError("Please enter your address");
                        binding.address.requestFocus();

                    }
                    else if (TextUtils.isEmpty(binding.state.getText().toString())) {
                        binding.state.setError("Please enter your address");
                        binding.state.requestFocus();
                    }
                    else if (TextUtils.isEmpty(binding.AadharNumber.getText().toString())) {
                        binding.AadharNumber.setError("Please enter your Last Name");
                        binding.AadharNumber.requestFocus();
                    }
                    else {

                        uploadProfileDetails();

                    }
                } else {
                    if (TextUtils.isEmpty(binding.fullName.getText().toString())) {
                        binding.fullName.setError("Please enter your first name");
                    }

                    if (TextUtils.isEmpty(binding.pincode.getText().toString())) {
                        binding.pincode.setError("Please enter your MobileNumber");

                    }

                }
            }

        });

    }


    private void addChangeListener() {
        binding.fullName.addTextChangedListener(watcher);
        binding.AadharNumber.addTextChangedListener(watcher);
        binding.pincode.addTextChangedListener(watcher);
        binding.address.addTextChangedListener(watcher);
        binding.dob.addTextChangedListener(watcher);
        binding.state.addTextChangedListener(watcher);

    }

    private final TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (binding.fullName.getText().toString().length() == 0 ||binding.state.getText().toString().length() ==0 || binding.AadharNumber.getText().toString().length() == 0 || binding.pincode.getText().toString().length() == 0 || binding.address.getText().toString().length() == 0 || binding.dob.getText().toString().length() == 0) {
                binding.submitBTN.setEnabled(false);

            } else {

                binding.submitBTN.setBackgroundColor(getResources().getColor(R.color.blue_dark));
                binding.submitBTN.setEnabled(true);
            }

        }
    };
    private void selectBirthDate() {
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int y, int m, int d) {

                switch (m + 1) {
                    case 1:
                        binding.dob.setText(d + " Jan " + y);
                        break;
                    case 2:
                        binding.dob.setText(d + " Feb " + y);
                        break;
                    case 3:
                        binding.dob.setText(d + " March " + y);
                        break;
                    case 4:
                        binding.dob.setText(d + " April " + y);
                        break;
                    case 5:
                        binding.dob.setText(d + " May " + y);
                        break;
                    case 6:
                        binding.dob.setText(d + " June " + y);
                        break;
                    case 7:
                        binding.dob.setText(d + " July " + y);
                        break;
                    case 8:
                        binding.dob.setText(d + " Aug " + y);
                        break;
                    case 9:
                        binding.dob.setText(d + " Sep " + y);
                        break;
                    case 10:
                        binding.dob.setText(d + " Oct " + y);
                        break;
                    case 11:
                        binding.dob.setText(d + " Nov " + y);
                        break;
                    case 12:
                        binding.dob.setText(d + " Dec " + y);
                        break;
                }
            }
        }, year, month, day);
        dialog.show();
    }


    public void uploadProfileDetails()
    {
        dialog.setMessage("Uploading Details...Please wait!");
        dialog.show();
        String fullName = binding.fullName.getText().toString();
        String aadhar = binding.AadharNumber.getText().toString();
        String gender = binding.gender.getSelectedItem().toString();
        String dob = binding.dob.getText().toString();
        String pinCode = binding.pincode.getText().toString();
        String address = binding.address.getText().toString();
        String city = binding.city.getText().toString();
        String state = binding.state.getText().toString();
        String aaharURL = "www.none.com";

        try
        {
            ProfileHelper helper = new ProfileHelper(fullName, "user", aadhar, gender, address, city, state, pinCode, aaharURL, "yes", "no", "english");
            databaseReference.child("users").child(user.getUid()).setValue(helper);
        }
        catch (Exception e)
        {
            Toast.makeText(EditProfileDetailActivity.this, "Error in saving details", Toast.LENGTH_SHORT).show();
        }

        dialog.dismiss();
    }



}

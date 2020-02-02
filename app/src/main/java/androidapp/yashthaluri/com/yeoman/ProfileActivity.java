package androidapp.yashthaluri.com.yeoman;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;
import androidx.databinding.DataBindingUtil;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import androidapp.yashthaluri.com.yeoman.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {
    ActivityProfileBinding binding;


    private Calendar calendar;
    private SimpleDateFormat simpleDateFormat;
    private int year, month, day;
    private LinearLayout info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_profile);
        simpleDateFormat = new SimpleDateFormat("dd:MMM:YYYY");
        binding.dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectBirthDate();
            }
        });
        binding.skipBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialog1();
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
                    } else if (TextUtils.isEmpty(binding.lastName.getText().toString())) {
                        binding.lastName.setError("Please enter your Last Name");
                        binding.lastName.requestFocus();
                    } else if (TextUtils.isEmpty(binding.dob.getText().toString())) {
                        binding.dob.setError("Please select your Date of Birth");
                        binding.dob.requestFocus();
                    } else if (TextUtils.isEmpty(binding.MobileNumber.getText().toString())) {
                        binding.MobileNumber.setError("Please enter your MobileNumber");
                        binding.MobileNumber.requestFocus();
                    } else if (TextUtils.isEmpty(binding.address.getText().toString())) {
                        binding.address.setError("Please enter your address");
                        binding.address.requestFocus();

                    }
                    else if (TextUtils.isEmpty(binding.state.getText().toString())) {
                        binding.state.setError("Please enter your address");
                        binding.state.requestFocus();
                    }
                    else if (binding.gender.getSelectedItem().toString().equals("Select")) {
                        Toast.makeText(ProfileActivity.this, "Please select your gender", Toast.LENGTH_SHORT).show();
                    } else if (!binding.privacyCheckbox1.isChecked() && !binding.privacyCheckbox2.isChecked()) {
                        Toast.makeText(ProfileActivity.this, "Please check both the terms and condition", Toast.LENGTH_SHORT).show();
                    } else {

                        Toast.makeText(ProfileActivity.this, "Details Submitted", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    if (TextUtils.isEmpty(binding.fullName.getText().toString())) {
                        binding.fullName.setError("Please enter your first name");
                    }
                    if (TextUtils.isEmpty(binding.lastName.getText().toString())) {
                        binding.lastName.setError("Please enter your last name");
                    }
                    if (TextUtils.isEmpty(binding.MobileNumber.getText().toString())) {
                        binding.MobileNumber.setError("Please enter your MobileNumber");
                    }

                }
            }

        });

    }

    private void addChangeListener() {
        binding.fullName.addTextChangedListener(watcher);
        binding.lastName.addTextChangedListener(watcher);
        binding.MobileNumber.addTextChangedListener(watcher);
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
            if (binding.fullName.getText().toString().length() == 0 ||binding.state.getText().toString().length() ==0 || binding.lastName.getText().toString().length() == 0 || binding.MobileNumber.getText().toString().length() == 0 || binding.address.getText().toString().length() == 0 || binding.dob.getText().toString().length() == 0) {
                binding.submitBTN.setEnabled(false);

            } else {

                binding.submitBTN.setBackgroundColor(getResources().getColor(R.color.blue_dark));
                binding.submitBTN.setEnabled(true);
            }

        }
    };

    private void ShowDialog1() {
    }

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

    }


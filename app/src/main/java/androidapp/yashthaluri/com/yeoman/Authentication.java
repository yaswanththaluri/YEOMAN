package androidapp.yashthaluri.com.yeoman;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import androidapp.yashthaluri.com.yeoman.databinding.ActivityAuthenticationBinding;

public class Authentication extends AppCompatActivity {

    ActivityAuthenticationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_authentication);

        binding.getOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mobile = binding.authMobileNo.getText().toString();

                if (mobile.length()==10)
                {
                    sendVeificationMessage(mobile);
                }
                else
                {
                    Toast.makeText(Authentication.this, "Please enter a valid number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void sendVeificationMessage(String mobileno)
    {
    }
}

package androidapp.yashthaluri.com.yeoman;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import androidapp.yashthaluri.com.yeoman.databinding.ActivityAuthenticationBinding;

public class Authentication extends AppCompatActivity {

    ActivityAuthenticationBinding binding;
    private String mVerificationId;


    private ProgressDialog progressDialog;

    //firebase auth object
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_authentication);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);

        mAuth = FirebaseAuth.getInstance();

        binding.getOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mobile = binding.authMobileNo.getText().toString();

                if (mobile.length()==10)
                {
                    binding.mobileInpLay.setVisibility(View.INVISIBLE);
                    binding.otpLay.setVisibility(View.VISIBLE);
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
        progressDialog.setMessage("Sending OTP...Please wait!");
        progressDialog.show();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + mobileno,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallbacks);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            //Getting the code sent by SMS
            String code = phoneAuthCredential.getSmsCode();
            progressDialog.cancel();
            if (code != null) {
                binding.otpDig1.setText(code.charAt(0)+"");
                binding.otpDig2.setText(code.charAt(1)+"");
                binding.otpDig3.setText(code.charAt(2)+"");
                binding.otpDig4.setText(code.charAt(3)+"");
                binding.otpDig5.setText(code.charAt(4)+"");
                binding.otpDig6.setText(code.charAt(5)+"");

                verifyVerificationCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            progressDialog.cancel();
            Toast.makeText(Authentication.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            progressDialog.cancel();
            //storing the verification id that is sent to the user
            mVerificationId = s;
        }
    };


    private void verifyVerificationCode(String code) {
        //creating the credential
        progressDialog.setMessage("Verifying code...!");
        progressDialog.show();
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);

        //signing the user
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(Authentication.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //verification successful we will start the profile activity
                            Intent intent = new Intent(Authentication.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);

                        } else {

                            //verification unsuccessful.. display an error message
                            progressDialog.cancel();
                            String message = "Somthing is wrong, we will fix it soon...";

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                message = "Invalid code entered...";
                            }

                            Toast.makeText(Authentication.this, "Wrong code", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}

package androidapp.yashthaluri.com.yeoman.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import androidapp.yashthaluri.com.yeoman.Models.ProfileHelper;
import androidapp.yashthaluri.com.yeoman.R;
import androidapp.yashthaluri.com.yeoman.databinding.ActivityEditProfileDetailBinding;

public class EditProfileDetailActivity extends AppCompatActivity {
    ActivityEditProfileDetailBinding binding;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private ProgressDialog dialog;
    private boolean editFabFlag = false;
    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 71;
    private FirebaseStorage storage;
    private StorageReference reference;
    private String empType;
    private String unsType;
    private String absNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_edit_profile_detail);
        
        binding.fabEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        dialog = new ProgressDialog(this);
        dialog.setCanceledOnTouchOutside(false);

        storage = FirebaseStorage.getInstance();
        reference = storage.getReference();


        binding.submitBTN1.setEnabled(false);
        addChangeListener();
        binding.submitBTN1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.submitBTN1.isEnabled()) {
                    if (TextUtils.isEmpty(binding.fullName.getText().toString())) {
                        binding.fullName.setError("Please enter your First Name");
                        binding.fullName.requestFocus();
                    }  else if (TextUtils.isEmpty(binding.pincode.getText().toString())) {
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

        binding.fabEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editFabFlag)
                {
                    binding.fabEdit.setImageResource(R.drawable.edit);
                    stopFocusForEditFields();
                    binding.submitBTN1.setVisibility(View.INVISIBLE);
                }
                else
                {
                    binding.fabEdit.setImageResource(R.drawable.canceledit);
                    showFocusForEditFields();
                    binding.submitBTN1.setVisibility(View.VISIBLE);
                }
                editFabFlag= !editFabFlag;
            }
        });

        binding.profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadProfilePic();
            }
        });

    }


    private void addChangeListener() {
        binding.fullName.addTextChangedListener(watcher);
        binding.AadharNumber.addTextChangedListener(watcher);
        binding.pincode.addTextChangedListener(watcher);
        binding.address.addTextChangedListener(watcher);
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
            if (binding.fullName.getText().toString().length() == 0 ||binding.state.getText().toString().length() ==0 || binding.AadharNumber.getText().toString().length() == 0 || binding.pincode.getText().toString().length() == 0 || binding.address.getText().toString().length() == 0) {
                binding.submitBTN1.setEnabled(false);

            } else {

                binding.submitBTN1.setBackgroundColor(getResources().getColor(R.color.blue_dark));
                binding.submitBTN1.setEnabled(true);
            }

        }
    };

    public void uploadProfileDetails()
    {
        dialog.setMessage("Uploading Details...Please wait!");
        dialog.show();
        String fullName = binding.fullName.getText().toString();
        String aadhar = binding.AadharNumber.getText().toString();
        String gender = binding.gender.getSelectedItem().toString();
        String pinCode = binding.pincode.getText().toString();
        String address = binding.address.getText().toString();
        String city = binding.city.getText().toString();
        String state = binding.state.getText().toString();
        String aaharURL = "www.none.com";

        try
        {
            ProfileHelper helper = new ProfileHelper(fullName, "user", aadhar, gender, address, city, state, pinCode, aaharURL, "yes", "no", "english", empType, unsType, absNo);
            databaseReference.child("users").child(user.getUid()).setValue(helper);
        }
        catch (Exception e)
        {
            Toast.makeText(EditProfileDetailActivity.this, "Error in saving details", Toast.LENGTH_SHORT).show();
        }

        dialog.dismiss();
    }


    @Override
    protected void onStart() {
        super.onStart();
        stopFocusForEditFields();
        prePopulateData();
    }

    public void prePopulateData()
    {
        databaseReference.child("users").child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ProfileHelper helper = dataSnapshot.getValue(ProfileHelper.class);
                binding.fullName.setText(helper.getUserName());
                binding.pincode.setText(helper.getZipCode());
                binding.address.setText(helper.getAddress());
                binding.city.setText(helper.getVillage());
                binding.state.setText(helper.getState());
                binding.AadharNumber.setText(helper.getAadharNo());
                empType = helper.getEmpType();
                unsType = helper.getUnSkilledType();
                absNo = helper.getNoAbsentees();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void stopFocusForEditFields()
    {
        binding.fullName.setFocusable(false);
        binding.gender.setFocusable(false);
        binding.pincode.setFocusable(false);
        binding.address.setFocusable(false);
        binding.city.setFocusable(false);
        binding.state.setFocusable(false);
        binding.AadharNumber.setFocusable(false);
    }

    public void showFocusForEditFields()
    {
        binding.pincode.setFocusableInTouchMode(true);
        binding.address.setFocusableInTouchMode(true);
        binding.city.setFocusableInTouchMode(true);
        binding.state.setFocusableInTouchMode(true);
    }

    public void uploadProfilePic()
    {
        AlertDialog.Builder dialog = new AlertDialog.Builder(EditProfileDetailActivity.this);

        dialog.setTitle("Confirmation");

        dialog.setMessage("Are You sure you want to change the profile pic");

        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);
            }
        });

        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();

            try {
                //getting image from gallery
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(EditProfileDetailActivity.this.getContentResolver(), filePath);

                //Setting image to ImageView
//                i.setImageBitmap(bitmap);
                Glide.with(this).applyDefaultRequestOptions(RequestOptions.circleCropTransform()).load(bitmap).into(binding.profileImage);
                select();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void select()
    {
        final ProgressDialog pd = new ProgressDialog(EditProfileDetailActivity.this);
        pd.setMessage("Uploading.....");
        pd.setCanceledOnTouchOutside(false);
        pd.show();
        if(filePath != null) {
            pd.show();

            FirebaseUser user = auth.getCurrentUser();

            final StorageReference childRef = reference.child("ProfileImages").child(user.getUid());

            //uploading the image
            UploadTask uploadTask = childRef.putFile(filePath);

            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    pd.dismiss();
                    Toast.makeText(EditProfileDetailActivity.this, "Upload successful", Toast.LENGTH_SHORT).show();
                    childRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Uri download = uri;
                            setUri(download);
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    pd.dismiss();
                    Toast.makeText(EditProfileDetailActivity.this, "Upload Failed -> " + e, Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            Toast.makeText(EditProfileDetailActivity.this, "Select an image", Toast.LENGTH_SHORT).show();
        }
    }

    public void setUri(Uri download)
    {
        FirebaseUser user = auth.getCurrentUser();

        UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                .setPhotoUri(download).build();

        user.updateProfile(profile)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(EditProfileDetailActivity.this, "Saved Successfully", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(EditProfileDetailActivity.this, "Failed saving path", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}

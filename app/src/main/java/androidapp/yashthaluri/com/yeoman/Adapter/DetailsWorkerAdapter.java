package androidapp.yashthaluri.com.yeoman.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

import androidapp.yashthaluri.com.yeoman.Models.BookJobHelper;
import androidapp.yashthaluri.com.yeoman.Models.DetailWorkerModel;
import androidapp.yashthaluri.com.yeoman.Models.FarmerBookingHistoryHelper;
import androidapp.yashthaluri.com.yeoman.Models.ProfileHelper;
import androidapp.yashthaluri.com.yeoman.R;

public class DetailsWorkerAdapter extends RecyclerView.Adapter<DetailsWorkerAdapter.DetailWorkerViewHolder> {
    ArrayList<DetailWorkerModel> detailWorkerModels;
    Context context;
    int currPos;

    public DetailsWorkerAdapter(ArrayList<DetailWorkerModel> detailWorkerModels, Context context) {
        this.detailWorkerModels = detailWorkerModels;
        this.context = context;
    }

    @NonNull
    @Override
    public DetailWorkerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_detailscard,parent,false);

        return new DetailWorkerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailWorkerViewHolder holder, int position) {
        currPos = position;
        holder.detailImage.setImageResource(detailWorkerModels.get(position).getBitmap());
        holder.PersonName.setText(detailWorkerModels.get(position).getName());
        holder.PersonSkills.setText(detailWorkerModels.get(position).getSkills());
        holder.PersonRating.setText(detailWorkerModels.get(position).getRating());
    }

    @Override
    public int getItemCount() {
        return detailWorkerModels.size();
    }


    public class DetailWorkerViewHolder extends RecyclerView.ViewHolder {
        ImageView detailImage;
        TextView PersonName, PersonSkills, PersonRating;



        public DetailWorkerViewHolder(@NonNull View itemView, ImageView detailImage, TextView personName, TextView personSkills, TextView personRating) {
            super(itemView);
            this.detailImage = detailImage;
            PersonName = personName;
            PersonSkills = personSkills;
            PersonRating = personRating;
        }
        public DetailWorkerViewHolder(@NonNull View itemView) {
            super(itemView);
            detailImage = itemView.findViewById(R.id.detail_user_image);
            PersonName = itemView.findViewById(R.id.user_name);
            PersonSkills = itemView.findViewById(R.id.user_skillset);
            PersonRating = itemView.findViewById(R.id.rating_text);

            Button b = itemView.findViewById(R.id.rewardAddToCartBTN);

            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final FirebaseDatabase database = FirebaseDatabase.getInstance();
                    final DatabaseReference reference = database.getReference();
                    final DetailWorkerModel detailWorkerModel = detailWorkerModels.get(currPos);
                    reference.child("users").child(FirebaseAuth.getInstance().getUid()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            ProfileHelper profileHelper = dataSnapshot.getValue(ProfileHelper.class);
                            BookJobHelper helper = new BookJobHelper(profileHelper.getUserName(), ""+detailWorkerModel.getSearchedDate(), "Pending", "Pending", "Note");

                            reference.child("labourJobs").child(detailWorkerModel.getUid()).push().setValue(helper);

                            FarmerBookingHistoryHelper farmerBookingHistoryHelper = new FarmerBookingHistoryHelper(detailWorkerModel.getName(), detailWorkerModel.getUid(), "Not marked", "Pending", "Note", detailWorkerModel.getSearchedDate());
                            reference.child("FarmerBookings").child(FirebaseAuth.getInstance().getUid()).push().setValue(farmerBookingHistoryHelper);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }
            });
        }


    }
}

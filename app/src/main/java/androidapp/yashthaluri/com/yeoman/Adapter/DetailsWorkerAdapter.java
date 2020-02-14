package androidapp.yashthaluri.com.yeoman.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import androidapp.yashthaluri.com.yeoman.Models.DetailWorkerModel;
import androidapp.yashthaluri.com.yeoman.R;

public class DetailsWorkerAdapter extends RecyclerView.Adapter<DetailsWorkerAdapter.DetailWorkerViewHolder> {
    ArrayList<DetailWorkerModel> detailWorkerModels;
    Context context;

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


        }


    }
}

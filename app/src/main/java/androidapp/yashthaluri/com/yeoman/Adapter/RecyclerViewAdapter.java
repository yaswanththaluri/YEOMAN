package androidapp.yashthaluri.com.yeoman.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import androidapp.yashthaluri.com.yeoman.Models.JobDetailsHelper;
import androidapp.yashthaluri.com.yeoman.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {
    ArrayList<JobDetailsHelper> jobDetailsHelpers;

    Context context;

    public RecyclerViewAdapter(ArrayList<JobDetailsHelper> jobDetailsHelpers, Context context) {
        this.jobDetailsHelpers = jobDetailsHelpers;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_a,parent,false);

        return new RecyclerViewAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.name.setText(jobDetailsHelpers.get(position).getName());
        holder.desc.setText(jobDetailsHelpers.get(position).getDesc());

    }

    @Override
    public int getItemCount() {
        return jobDetailsHelpers.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
        TextView name,desc;

        public RecyclerViewHolder(@NonNull View itemView, TextView name, TextView desc) {
            super(itemView);
            this.name = name;
            this.desc = desc;
        }
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            desc=itemView.findViewById(R.id.desc);



        }

    }
}


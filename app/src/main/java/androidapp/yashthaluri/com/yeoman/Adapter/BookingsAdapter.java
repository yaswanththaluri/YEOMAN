package androidapp.yashthaluri.com.yeoman.Adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.net.URI;
import java.util.List;

import androidapp.yashthaluri.com.yeoman.Models.MyBookingsHelper;
import androidapp.yashthaluri.com.yeoman.R;

public class BookingsAdapter extends ArrayAdapter<MyBookingsHelper> {

    public BookingsAdapter(@NonNull Context context, int resource, @NonNull List<MyBookingsHelper> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null)
        {
            convertView = ((Activity)getContext()).getLayoutInflater().inflate(R.layout.bokkeditem, parent, false);
        }

        ImageView img = (ImageView) convertView.findViewById(R.id.bookedImg);
        TextView name = (TextView)convertView.findViewById(R.id.bookedName);
        TextView number = (TextView)convertView.findViewById(R.id.bookedPhoneNo);

        MyBookingsHelper notify = getItem(position);

        Uri uri = Uri.parse(notify.getName());
        img.setImageURI(uri);
        name.setText(notify.getName());
        number.setText(notify.getPhoneNumber());


        return convertView;
    }
}

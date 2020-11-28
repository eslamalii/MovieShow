package com.example.Movie.Data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Movie.Model.Cast;
import com.example.Movie.R;
import com.example.Movie.Util.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CastRecyclerViewAdapter extends RecyclerView.Adapter<CastRecyclerViewAdapter.ViewHolder> {
    ArrayList<Cast> castDataList = new ArrayList<>();

    @NonNull
    @Override
    public CastRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listView = layoutInflater.inflate(R.layout.cast_row, parent, false);
        return new ViewHolder(listView);

    }

    @Override
    public void onBindViewHolder(@NonNull CastRecyclerViewAdapter.ViewHolder holder, int position) {
        Cast castData = castDataList.get(position);
        String CastImage = Constants.IMAGE_URL + castData.getProfile_path();

        Picasso.get()
                .load(CastImage)
                .into(holder.castImageView);

        holder.castName.setText(castData.getName());
        holder.castKnown.setText(castData.getKnown_for_department());
    }

    @Override
    public int getItemCount() {
        return castDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView castImageView;
        TextView castName;
        TextView castKnown;

        public ViewHolder(@NonNull View view) {
            super(view);

            castImageView = view.findViewById(R.id.castImageID);
            castName = view.findViewById(R.id.castNameID);
            castKnown = view.findViewById(R.id.castKnownID);
        }
    }

    public void setMovieCasting(ArrayList<Cast> casts) {
        this.castDataList = casts;
        notifyDataSetChanged();
    }

}

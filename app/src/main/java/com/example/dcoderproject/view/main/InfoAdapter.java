package com.example.dcoderproject.view.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dcoderproject.R;
import com.example.dcoderproject.data.model.Info;
import com.example.dcoderproject.data.model.InfoEntity;
import com.example.dcoderproject.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.InfoViewHolder> implements Filterable {

    private Context context;
    private List<InfoEntity> infoList;
    private List<InfoEntity> infoFilteredList;

    public InfoAdapter(Context context, List<InfoEntity> infoList){
        this.context = context;
        this.infoList = infoList;
        this.infoFilteredList = new ArrayList<>(infoList);
    }

    @NonNull
    @Override
    public InfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_info, parent, false);

        // Return a new holder instance
        InfoViewHolder viewHolder = new InfoViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull InfoViewHolder holder, int position) {

        InfoEntity data = infoList.get(position);

        String nameInitial = AppUtils.getTheUsernameInitials(data.getUsername());

        holder.imageInitialTextView.setText(nameInitial);

        holder.username.setText(data.getUsername());
        holder.title.setText(data.getTitle());
        holder.description.setText(data.getDescription());
        holder.starRating.setText(String.valueOf(data.getStars()));
        holder.forkRating.setText(String.valueOf(data.getForks()));
    }

    @Override
    public int getItemCount() {
        return infoList.size();
    }

    @Override
    public Filter getFilter() {
        return infoFilter;
    }

    public static class InfoViewHolder extends RecyclerView.ViewHolder{

        private TextView username, title, description, starRating,forkRating , imageInitialTextView;

        public InfoViewHolder(@NonNull View itemView) {
            super(itemView);


            username = itemView.findViewById(R.id.usernameTextView);
            title = itemView.findViewById(R.id.titleTextView);
            description = itemView.findViewById(R.id.descriptionTextView);
            starRating = itemView.findViewById(R.id.starRatingTextView);
            forkRating = itemView.findViewById(R.id.forkRatingTextView);
            imageInitialTextView = itemView.findViewById(R.id.imageInitialTextView);
        }
    }

    private Filter infoFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<InfoEntity> filteredList = new ArrayList<>();
            if (constraint == null | constraint.length() == 0) {

                filteredList.addAll(infoFilteredList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (InfoEntity item : infoFilteredList) {

                    if (item.getUsername().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            infoList.clear();
            infoList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}

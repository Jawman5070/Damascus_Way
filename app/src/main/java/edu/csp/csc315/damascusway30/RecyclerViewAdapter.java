package edu.csp.csc315.damascusway30;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements Filterable  {

    private static final String TAG = "RecyclerViewAdapter";

    //Variable to build RecyclerViewAdapter - this gets passed to the filter
    private List<Resident> mResidents;
    //Variable to hold the list for the adapter so that real-time searches can be backspaced and there is a copy of the full list to revert to
    private List<Resident> mResidentsFull;
    private Context mContext;

    public RecyclerViewAdapter(Context context, List<Resident> residents) {
        this.mContext = context;
        //This gets passed to the filter first and then passed to adapter after being filtered
        this.mResidents = residents;
        //This is a reference copy of the full list from the beginning of the search
        mResidentsFull = new ArrayList<>(residents);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Glide.with(mContext)
                .asBitmap()
                //Will need the resident methods to get this
                .load(mResidents.get(position).getPhotoUrl())
                .into(holder.residentImage);

        //Will need the resident methods to get this
        holder.residentName.setText(mResidents.get(position).toString());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + mResidents.get(position));

                //Will need the resident methods to get this
                Toast.makeText(mContext, mResidents.get(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mResidents.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView residentImage;
        TextView residentName;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            residentImage = itemView.findViewById(R.id.image);
            residentName = itemView.findViewById(R.id.image_name);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
    //Filter Results
    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Resident> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(mResidentsFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Resident resident : mResidentsFull) {
                    if (resident.toString().toLowerCase().contains(filterPattern)) {
                        filteredList.add(resident);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mResidents.clear();
            mResidents.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}


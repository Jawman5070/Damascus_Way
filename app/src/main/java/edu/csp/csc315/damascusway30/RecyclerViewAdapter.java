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

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements Filterable {

    private static final String TAG = "RecyclerViewAdapter";

    //Variables used for hard coded values
    //private ArrayList<String> mImageNames;
    //private ArrayList<String> mImages;

    //Variable to build RecyclerViewAdapter - this gets passed to the Adapter
    //private ArrayList<Resident> mResidents;
    private List<Resident> mResidents;
    //Variable to hold the list for the filter
    //private ArrayList<Resident> mResidentFull;
    private List<Resident> mResidentFull;
    private Context mContext;

    public RecyclerViewAdapter(Context context, List<Resident> residents){
        mContext = context;
        mResidents = residents;

        //This gets passed to the filter first and then passed to adapter after being filtered
        mResidentFull = residents;

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
                .load(mResidents.getPicture().get(position))
                .into(holder.residentImage);

        //Will need the resident methods to get this
        holder.residentName.setText(mResidents.getName().get(position));

        holder.parentLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.d(TAG, "onClick: clicked on: " + mResidents.get(position));

                //Will need the resident methods to get this
                Toast.makeText(mContext, mResidents.getName().get(position), Toast.LENGTH_SHORT).show();
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

    private Filter filter = new Filter(){

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Resident> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(mResidentFull);
            }
            else {
                String filterPatter = constraint.toString().toLowerCase().trim();

                for(Resident resident : mResidentFull){
                    //Will need getName() method from Residents class
                    if(resident.getName().toLowerCase().contains(filterPatter)){
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

/*    RecyclerViewAdapter used with hard coded values
    public RecyclerViewAdapter(Context context, ArrayList<String> imageNames, ArrayList<String> images) {

        mImageNames = imageNames;
        mImages = images;
        mContext = context;

    }
*/

}


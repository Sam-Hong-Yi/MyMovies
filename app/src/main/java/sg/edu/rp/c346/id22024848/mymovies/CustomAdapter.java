package sg.edu.rp.c346.id22024848.mymovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Movie> versionList;

    public CustomAdapter(Context context, int resource, ArrayList<Movie> objects){
        super(context, resource, objects);

        parent_context=context;
        layout_id=resource;
        versionList=objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding

        TextView rowTitle=rowView.findViewById(R.id.rowTitle);
        TextView rowGenre=rowView.findViewById(R.id.rowGenre);
        ImageView rowRating =rowView.findViewById(R.id.imageViewRating);
        TextView rowYear=rowView.findViewById(R.id.rowYear);



        // Obtain the Android Version information based on the position
        Movie currentVersion = versionList.get(position);

        // Set values to the TextView to display the corresponding information
        rowTitle.setText(currentVersion.getTitle());
        rowYear.setText(Integer.toString(currentVersion.getYear()));

        rowGenre.setText(currentVersion.getGenre());
        String rating=(currentVersion.getRating());
        if(rating.equals("G")){
            rowRating.setImageResource(R.drawable.rating_g);
        }
        else if(rating.equals("PG")){
            rowRating.setImageResource(R.drawable.rating_pg);
        }
        else if(rating.equals("PG13")){
            rowRating.setImageResource(R.drawable.rating_pg13);
        }
        else if(rating.equals("NC16")){
            rowRating.setImageResource(R.drawable.rating_nc16);
        }
        else if(rating.equals("M18")){
            rowRating.setImageResource(R.drawable.rating_m18);
        }
        else{
            rowRating.setImageResource(R.drawable.rating_r21);
        }






        return rowView;
    }

}

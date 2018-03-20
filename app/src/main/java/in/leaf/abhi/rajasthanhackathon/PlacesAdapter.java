package in.leaf.abhi.rajasthanhackathon;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

import in.leaf.abhi.rajasthanhackathon.pojos.Place;
import in.leaf.abhi.rajasthanhackathon.pojos.Temp_Rating;

/**
 * Created by 500060150 on 20-03-2018.
 */

public class PlacesAdapter extends BaseExpandableListAdapter {
    ArrayList<Place> places;
    Context context;
    DatabaseReference databaseReference;
    String category;
    ArrayList<LatLng> coordinates;
    PlacesAdapter(ArrayList<Place> places, ArrayList<LatLng> coordinates,Context context, DatabaseReference databaseReference, String category) {
        this.places=places;
        this.context=context;
        this.category=category;
        this.databaseReference=databaseReference;
        this.coordinates=coordinates;
        System.out.println("places size : "+places.size());
    }
    @Override
    public int getGroupCount() {
        return places.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return 1;
    }

    @Override
    public Object getGroup(int i) {
        return places.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {

        return null;
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean b, View view, ViewGroup viewGroup) {
        ConstraintLayout groupHeader;
        groupHeader=(ConstraintLayout) LayoutInflater.from(context).inflate(R.layout.places_layout,null);
        TextView tV=(TextView)groupHeader.getChildAt(0);
        tV.setText(places.get(groupPosition).getName());
        return groupHeader;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        ConstraintLayout childHeader;
        final int groupPosition=i;
        childHeader=(ConstraintLayout)LayoutInflater.from(context).inflate(R.layout.child_layout,null);
        final RatingBar rB=(RatingBar)childHeader.getChildAt(0);
        Button button=(Button)childHeader.getChildAt(1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.child("temp_rate")
                        .child(((Place)getGroup(groupPosition)).getCity())
                        .child(((Place)getGroup(groupPosition)).getName())
                        .setValue(new Temp_Rating(String.valueOf(rB.getRating()),category));
            }
        });
        return childHeader;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}

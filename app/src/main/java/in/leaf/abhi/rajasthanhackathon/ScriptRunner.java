package in.leaf.abhi.rajasthanhackathon;

/**
 * Created by 500060150 on 19-03-2018.
 */

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.ArrayList;
import java.util.Scanner;

import in.leaf.abhi.rajasthanhackathon.pojos.Category;
import in.leaf.abhi.rajasthanhackathon.pojos.Place;
import in.leaf.abhi.rajasthanhackathon.pojos.Temp;


/**
 * Created by 500060150 on 16-12-2017.
 */

class ScriptRunner extends AsyncTask<Object,Void,String> implements ExpandableListView.OnGroupExpandListener  {
    PlacesAdapter placesAdapter;
    ExpandableListView expandableListView;
    Context context;
    ProgressBar progressBar;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    LatLng currentLocation;
    Temp temp;
    ScriptRunner(ExpandableListView expandableListView,PlacesAdapter placesAdapter,Context context,ProgressBar progressBar) {
        this.expandableListView=expandableListView;
        this.placesAdapter=placesAdapter;
        this.context=context;
        this.progressBar=progressBar;
        firebaseDatabase=FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference();
    }
    @Override
    public String doInBackground(Object... params) {
        String id=(String)params[0];
        String category=(String)params[1];
        String city=(String)params[2];
        currentLocation=(LatLng)params[3];
        Log.i("tag","Script Runner : "+id);
        Log.i("tag","Script Runner : "+category);
        Log.i("tag","Script Runner : "+city);
        OkHttpClient client = new OkHttpClient();
        String url="http://192.168.43.169/hack/python_script.py?userid='"+id+"'&category='"+category+"'&city='"+city+"'";
        Log.i("tag","url : "+url);
        Request request=new Request.Builder().url(url).build();
        Response response;
        String data=null;
        int counter=0;
        System.out.println("Trying");
        Log.i("tag","trying");
        try {
            Log.i("tag","before executing");
            temp=new Temp(category,city);
            reference.child("temp").setValue(temp);
            response=client.newCall(request).execute();
            Log.i("tag","after executing");
            data=response.body().string();
            Log.i("tag",data);
        }catch(Exception e) {
            System.out.println("EXception occured while downloading questions");
            e.printStackTrace();
        }
        return data;
    }

    @Override
    protected void onPostExecute(String result) {
        Log.i("tag",result);
        Toast.makeText(context,result,Toast.LENGTH_LONG).show();
        String[] resultArray=result.split("\r\n");
        String[] name=resultArray[0].split(",");
        String[] coordinates=resultArray[1].split(",");

        ArrayList<Place> places=new ArrayList<>();
        ArrayList<LatLng> crdnates=new ArrayList();
        for(int i=0;(i<name.length);i++) {
            places.add(new Place(name[i],temp.getCity()));
        }
        for(int j=0;j<coordinates.length;j+=2) {
            LatLng temp=new LatLng(Double.parseDouble(coordinates[j]),Double.parseDouble(coordinates[j+1]));
            crdnates.add(temp);
        }
        Log.i("tag",name[0]);
        placesAdapter=new PlacesAdapter(places,crdnates,context,firebaseDatabase.getReference(),temp.getCategory());
        expandableListView.setAdapter(placesAdapter);
        Log.i("tag","downloading finished");
        progressBar.setVisibility(View.GONE);
        expandableListView.setVisibility(View.VISIBLE);
        expandableListView.setOnGroupExpandListener(this);
    }


    @Override
    public void onGroupExpand(int i) {
        Intent intent=new Intent(context,MapsActivity.class);
        intent.putExtra("currentlocation",currentLocation);
        intent.putExtra("coordinates",placesAdapter.coordinates.get(i));
        context.startActivity(intent);
    }

}


package in.leaf.abhi.rajasthanhackathon;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class CategorySelect extends Fragment implements View.OnClickListener{
    String userid;

    public CategorySelect() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup v=(ViewGroup)inflater.inflate(R.layout.fragment_category_select, container, false);
        Button b1=(Button)v.getChildAt(0);
        b1.setOnClickListener(this);

        Button b2=(Button)v.getChildAt(0);
        b2.setOnClickListener(this);
        Button b3=(Button)v.getChildAt(0);
        b3.setOnClickListener(this);
        Button b4=(Button)v.getChildAt(0);
        b4.setOnClickListener(this);
        Button b5=(Button)v.getChildAt(0);
        b5.setOnClickListener(this);
        Button b6=(Button)v.getChildAt(0);
        b6.setOnClickListener(this);

        Log.i("tag","listeners set");

        return v;
    }

    @Override
    public void onClick(View view) {
        Log.i("tag","onclick executed");
        String choice;
        Button clickedB=(Button)view;
        switch(clickedB.getId()) {
            case  R.id.category1 :
                choice=getResources().getString(R.string.category1);
                break;
            case R.id.category2:
                choice=getResources().getString(R.string.category2);
                break;
            case R.id.category3:
                choice=getResources().getString(R.string.category3);
                break;
            case R.id.category4:
                choice=getResources().getString(R.string.category4);
                break;
            case R.id.category5:
                choice=getResources().getString(R.string.category5);
                break;
            default :
                choice=null;
                break;
        }

        Intent i=new Intent(getContext(),TopPlacesActivity.class);
        i.putExtra("category",choice);
        i.putExtra("userid",userid);
        startActivity(i);

    }


}

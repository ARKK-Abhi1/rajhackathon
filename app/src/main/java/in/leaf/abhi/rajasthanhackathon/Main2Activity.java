package in.leaf.abhi.rajasthanhackathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {

    String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bundle bundle=getIntent().getExtras();
        userid=bundle.getString("ID");
    }

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

        Intent i=new Intent(this,TopPlacesActivity.class);
        i.putExtra("category",choice);
        i.putExtra("userid",userid);
        startActivity(i);

    }
}

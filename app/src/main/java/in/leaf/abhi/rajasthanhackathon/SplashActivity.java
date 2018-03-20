package in.leaf.abhi.rajasthanhackathon;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    private static long SPLASH_DISPLAY_TIME=1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(SplashActivity.this,GoogleSignInActivity.class);
                //can add some downloaded content here
                startActivity(i);
                SplashActivity.this.finish();
            }
        },SPLASH_DISPLAY_TIME);
    }
}

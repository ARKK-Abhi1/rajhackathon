package in.leaf.abhi.rajasthanhackathon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import in.leaf.abhi.rajasthanhackathon.pojos.User;

public class GoogleSignInActivity extends AppCompatActivity {
    private final int RC_SiGN_IN=1;
    Button skipButton;
    GoogleSignInAccount account;
    GoogleSignInClient mGoogleSignInClient;
    FirebaseDatabase database;
    DatabaseReference usersReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_sign_in);
        database=FirebaseDatabase.getInstance();
        usersReference=database.getReference("Users");


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        Log.i("tag","inside on create");

        findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("tag","button clicked");
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent,RC_SiGN_IN);
            }
        });

    }

    @Override
    public void onActivityResult(int requestcode,int resultcode,Intent data) {
        Log.i("tag","onACtivityREsult");
        if(requestcode==RC_SiGN_IN) {
            Log.i("tag","successuful request code");
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            Log.i("tag","successfully signed in");
            Log.i("tag",account.getDisplayName());
            Log.i("tag",account.getEmail());
            Log.i("tag",account.getId());

            usersReference.child(account.getId()).setValue(new User(account.getDisplayName(),account.getEmail()));
            Intent i=new Intent(this,Main2Activity.class);
            i.putExtra("ID",account.getId());
            startActivity(i);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("tag", "signInResult:failed code=" + e.getStatusCode());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        account = GoogleSignIn.getLastSignedInAccount(this);
        if(account!=null)
            Log.i("tag","already signed in");
        else
            Log.i("tag","not signed in");
    }
}

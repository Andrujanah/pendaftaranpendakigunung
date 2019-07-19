package com.tubes.pendaftaranpendakigunung;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MainMenu extends AppCompatActivity {

    public static final String GOOGLE_ACCOUNT = "AndroidClarified";
    private GoogleSignInClient googleSignInClient;
    Button signOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        signOut =findViewById(R.id.sign_out);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    // ...
                    case R.id.sign_out:
                        signOut();
                        break;
                    // ...
                }
            }
        });

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personId = acct.getId();

        }
    }

    public void insertData(View view) {

        Intent keInsertActivity = new Intent(MainMenu.this, InsertActivity.class);
        //mengirim data ke activity insert data,
        // flag 0 untuk insert data, flag 1 untuk edit data
        keInsertActivity.putExtra("flag", 0);
        startActivity(keInsertActivity);
    }

    public void editData(View view) {
        Intent keInsertActivity = new Intent(MainMenu.this, InsertActivity.class);
        //mengirim data ke activity insert data,
        // flag 0 untuk insert data, flag 1 untuk edit data
        keInsertActivity.putExtra("flag", 1);
        startActivity(keInsertActivity);
    }

    public void listData(View view){
        Intent keListActivity = new Intent(MainMenu.this, ListActivity.class);
        startActivity(keListActivity);
    }

    private void signOut() {
        googleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(MainMenu.this, "Terimakasih " , Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
    }

}

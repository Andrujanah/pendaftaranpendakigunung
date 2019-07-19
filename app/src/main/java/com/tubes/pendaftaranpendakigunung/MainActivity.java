package com.tubes.pendaftaranpendakigunung;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;
import com.tubes.pendaftaranpendakigunung.Model.Parsing.GetLogin;
import com.tubes.pendaftaranpendakigunung.REST.RetrofitInterface;
import com.tubes.pendaftaranpendakigunung.REST.RetrofitClient;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class MainActivity extends AppCompatActivity {

    RetrofitInterface rfInterface;
    LinearLayout layoutProgress;
    EditText edUser, edPass;
    Button btnLogin;
    Button sign_out;

    //login
    private static final String TAG = "AndroidClarified";
    private SignInButton googleSignInButton;
    private GoogleSignInClient googleSignInClient;
    //end login

    private GoogleApiClient signout;
    //end logout


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sign_out = findViewById(R.id.sign_out);
        btnLogin = findViewById(R.id.btnLogin);
        layoutProgress = findViewById(R.id.layoutProgress);
        rfInterface = RetrofitClient.getClient().create(RetrofitInterface.class);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginProcess();
            }
        });
        //login google
        googleSignInButton = findViewById(R.id.sign_in_button);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);
        googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = googleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, 101);
            }
        });
        //end login Google
    }

    private boolean validateInputs() {
        edUser = findViewById(R.id.edUser);
        edPass = findViewById(R.id.edPass);

        if (edUser.getText().toString().length() <= 0
                || edPass.getText().toString().length() <= 0) {
            return false;
        } else {
            return true;
        }
    }

    private void loginProcess(){
        if(validateInputs()) {
            Map<String,String> parameter = new HashMap<>();
            parameter.put("username",edUser.getText().toString());
            parameter.put("password",edPass.getText().toString());

            layoutProgress.setVisibility(View.VISIBLE);

            Call<GetLogin> getLoginCall = rfInterface.getLogin(parameter);
            getLoginCall.enqueue(new Callback<GetLogin>() {
                @Override
                public void onResponse(Call<GetLogin> call, Response<GetLogin> response) {
                    if (response.code() == 200){
                        String nama =response.body().getLogin().getNamaUser();
                        Toast.makeText(MainActivity.this, "Selamat Datang " + nama, Toast.LENGTH_LONG).show();
                        keMainMenu();
                    } else if (response.code() == 400) {
                        Toast.makeText(MainActivity.this, "User Not Found", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Address Not Found", Toast.LENGTH_SHORT).show();
                    }
                    layoutProgress.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<GetLogin> call, Throwable t) {
                    Log.d("failure", t.getMessage());
                    layoutProgress.setVisibility(View.GONE);
                }
            });
        } else {
            Toast.makeText(this, "Input are Incorrect", Toast.LENGTH_LONG).show();
        }
    }

    private void keMainMenu() {
        Intent intent = new Intent(MainActivity.this, MainMenu.class);
        startActivity(intent);
    }

    //LOGIN
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case 101:
                    try {
                        // The Task returned from this call is always completed, no need to attach
                        // a listener.
                        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                        GoogleSignInAccount account = task.getResult(ApiException.class);
                        onLoggedIn(account);
                    } catch (ApiException e) {
                        // The ApiException status code indicates the detailed failure reason.
                        Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
                    }
                    break;
            }
    }

    private void onLoggedIn(GoogleSignInAccount googleSignInAccount) {
        Intent intent = new Intent(this, MainMenu.class);
       intent.putExtra(MainMenu.GOOGLE_ACCOUNT, googleSignInAccount);

        startActivity(intent);
        finish();
    }

    @Override
    public void onStart() {
        super.onStart();
        GoogleSignInAccount alreadyloggedAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (alreadyloggedAccount != null) {
            Toast.makeText(this, "Already Logged In", Toast.LENGTH_SHORT).show();
            onLoggedIn(alreadyloggedAccount);
        } else {
            Log.d(TAG, "Not logged in");
        }
    }
}

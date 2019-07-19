package com.tubes.pendaftaranpendakigunung;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tubes.pendaftaranpendakigunung.Model.CRUDMessage;
import com.tubes.pendaftaranpendakigunung.REST.RetrofitClient;
import com.tubes.pendaftaranpendakigunung.REST.RetrofitInterface;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertActivity extends AppCompatActivity {

    Integer flagCode;
    Button btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        btnSimpan = findViewById(R.id.btn_SimpanData);


        Bundle data = getIntent().getExtras();
        if (data != null) {
            flagCode = data.getInt("flag");
            if (flagCode == 1) {
                btnSimpan.setText("Edit");
            }
        } else {
            flagCode = 0;
            btnSimpan.setText("Simpan");
        }
    }

    public void simpanPendaki(View view) {
        EditText inputID = findViewById(R.id.inpIdPendaki);
        EditText inputPendaki = findViewById(R.id.inpNamaPendaki);
        EditText inputAlamat= findViewById(R.id.inpAlamatPendaki);
        EditText inputNoTelp= findViewById(R.id.inpNoTelp);

        String idPendaki = inputID.getText().toString();
        String namaPendaki = inputPendaki.getText().toString();
        String alamatPendaki = inputAlamat.getText().toString();
        String noTelp = inputNoTelp.getText().toString();

        RetrofitInterface retrofitInterface = RetrofitClient.getClient().create(RetrofitInterface.class);

        if (flagCode == 0) {
            Call<CRUDMessage> insertPendaki = retrofitInterface.insertPendaki(namaPendaki,alamatPendaki,noTelp);
            insertPendaki.enqueue(new Callback<CRUDMessage>() {
                @Override
                public void onResponse(Call<CRUDMessage> call, Response<CRUDMessage> response) {
                    Toast.makeText(InsertActivity.this,
                            "Insert Berita " + response.body().getMessage(),
                            Toast.LENGTH_SHORT).show();
                    finish();
                }

                @Override
                public void onFailure(Call<CRUDMessage> call, Throwable t) {
                    Log.d("aing Failure", t.getMessage());
                }
            });
        } else {
            Call<CRUDMessage> editPendaki = retrofitInterface.editPendaki(idPendaki,namaPendaki,alamatPendaki,noTelp);
            editPendaki.enqueue(new Callback<CRUDMessage>() {
                @Override
                public void onResponse(Call<CRUDMessage> call, Response<CRUDMessage> response) {
                    Toast.makeText(InsertActivity.this,
                            "Insert Berita " + response.body().getMessage(),
                            Toast.LENGTH_SHORT).show();
                    finish();
                }

                @Override
                public void onFailure(Call<CRUDMessage> call, Throwable t) {
                    Log.d("aing Failure", t.getMessage());
                }
            });
        }
    }//end fungsi simpanBerita
}

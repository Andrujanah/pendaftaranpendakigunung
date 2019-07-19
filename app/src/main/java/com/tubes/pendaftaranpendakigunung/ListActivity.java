package com.tubes.pendaftaranpendakigunung;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.tubes.pendaftaranpendakigunung.Adapter.PendakiAdapter;
import com.tubes.pendaftaranpendakigunung.Model.Parsing.getAllPendaki;
import com.tubes.pendaftaranpendakigunung.Model.Pendaki;
import com.tubes.pendaftaranpendakigunung.REST.RetrofitClient;
import com.tubes.pendaftaranpendakigunung.REST.RetrofitInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        final RecyclerView recyclerView = findViewById(R.id.recyclerPendaki);
        final LinearLayout layoutProgress = findViewById(R.id.layoutProgress);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        RetrofitInterface retrofitInterface = RetrofitClient.getClient().create(RetrofitInterface.class);

        layoutProgress.setVisibility(View.VISIBLE);
        Call<getAllPendaki> getAllPendaki = retrofitInterface.getAllPendaki();
        getAllPendaki.enqueue(new Callback<getAllPendaki>() {
            @Override
            public void onResponse(Call<getAllPendaki> call, Response<getAllPendaki> response) {
                List<Pendaki> pendakiList = response.body().getPendaki();
                RecyclerView.Adapter adapter = new PendakiAdapter(pendakiList);
                recyclerView.setAdapter(adapter);

                layoutProgress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<getAllPendaki> call, Throwable t) {
                Log.d("failure", t.getMessage());
                layoutProgress.setVisibility(View.GONE);
            }
        });
    }
}

package com.tubes.pendaftaranpendakigunung.Adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tubes.pendaftaranpendakigunung.Model.Pendaki;
import com.tubes.pendaftaranpendakigunung.R;

import java.util.List;

public class PendakiAdapter extends RecyclerView.Adapter<PendakiAdapter.PendakiViewHolder> {
List<Pendaki> listPendaki;

    public class PendakiViewHolder extends RecyclerView.ViewHolder{
        public TextView listNamaPendaki, listAlamatPendaki, listNoTelp,listWaktuPosted;

        public PendakiViewHolder (@NonNull View itemVIew){
            super(itemVIew);
            listNamaPendaki = itemVIew.findViewById(R.id.listNamaPendaki);
            listAlamatPendaki= itemVIew.findViewById(R.id.listAlamatPendaki);
            listNoTelp = itemVIew.findViewById(R.id.listNoTelp);
            listWaktuPosted = itemVIew.findViewById(R.id.listWaktuPosted);
        }
    }

    public PendakiAdapter(List<Pendaki>listPendaki){
        this.listPendaki = listPendaki;
    }

    @NonNull
    @Override
    public PendakiAdapter.PendakiViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup , int i){
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_pendaki,viewGroup,false);
        PendakiViewHolder pendakiViewHolder = new PendakiViewHolder(mView);
        return pendakiViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final PendakiAdapter.PendakiViewHolder viewHolder, int i){
        viewHolder.listNamaPendaki.setText(listPendaki.get(i).getNama_pendaki());
        viewHolder.listAlamatPendaki.setText(listPendaki.get(i).getAlamat_pendaki());
        viewHolder.listNoTelp.setText(listPendaki.get(i).getNo_telp());
        viewHolder.listWaktuPosted.setText(listPendaki.get(i).getWaktu_posted() + "No. " + listPendaki.get(i).getId_pendaki());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(viewHolder.itemView.getContext(), "clicked on Row ",Toast.LENGTH_SHORT);
            }
        });
    }
    @Override
    public int getItemCount(){return listPendaki.size();}
}

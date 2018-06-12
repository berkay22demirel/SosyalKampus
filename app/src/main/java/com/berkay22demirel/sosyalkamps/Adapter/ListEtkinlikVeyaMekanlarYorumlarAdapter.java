package com.berkay22demirel.sosyalkamps.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.berkay22demirel.sosyalkamps.Paylasim;
import com.berkay22demirel.sosyalkamps.R;
import com.berkay22demirel.sosyalkamps.Yorum;

import java.util.List;

/**
 * Created by BerkayDemirel on 11.06.2018.
 */

public class ListEtkinlikVeyaMekanlarYorumlarAdapter extends ArrayAdapter<Yorum> {
    LayoutInflater layoutInflater;
    Context context;
    List<Yorum> yorumlarListesi;

    public ListEtkinlikVeyaMekanlarYorumlarAdapter(Context context, int resource, List<Yorum> yorumlarListesi) {
        super(context, resource, yorumlarListesi);
        this.context = context;
        this.yorumlarListesi = yorumlarListesi;
    }

    public void setYorumlarListesi(List<Yorum> yorumlarListesi) {
        this.yorumlarListesi = yorumlarListesi;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.list_item_yorumlar,parent,false);

        TextView textViewEposta = (TextView) v.findViewById(R.id.textViewListItemYorumlarYorumYapanEposta);
        TextView textViewYorum = (TextView) v.findViewById(R.id.textViewListItemYorumlarYorum);
        Yorum y = yorumlarListesi.get(position);
        textViewEposta.setText(y.getYorumYapanEposta());
        textViewYorum.setText(y.getYapilanYorum());

        return v;
    }

}

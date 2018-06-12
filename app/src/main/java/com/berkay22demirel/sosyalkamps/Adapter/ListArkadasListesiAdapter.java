package com.berkay22demirel.sosyalkamps.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.berkay22demirel.sosyalkamps.Kullanici;
import com.berkay22demirel.sosyalkamps.Paylasim;
import com.berkay22demirel.sosyalkamps.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BerkayDemirel on 07.06.2018.
 */

public class ListArkadasListesiAdapter extends ArrayAdapter<Kullanici> {
    LayoutInflater layoutInflater;
    Context context;
    List<Kullanici> arkadasListesi;

    public ListArkadasListesiAdapter(Context context, int resource, List<Kullanici> arkadasListesi) {
        super(context, resource, arkadasListesi);
        this.context = context;
        this.arkadasListesi = arkadasListesi;
    }

    public void setArkadasListesi(List<Kullanici> arkadasListesi) {
        this.arkadasListesi = arkadasListesi;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.list_item_arkadas_listesi,parent,false);

        TextView textViewAdSoyad = (TextView) v.findViewById(R.id.textViewArkadasListesiListItemAdSoyad);
        TextView textViewBio = (TextView) v.findViewById(R.id.textViewArkadasListesiListItemBio);
        Kullanici k = arkadasListesi.get(position);
        textViewAdSoyad.setText(k.getAd() + " " + k.getSoyad());
        textViewBio.setText(k.getBio());

        return v;
    }
}

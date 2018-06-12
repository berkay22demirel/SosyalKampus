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

import java.util.List;

/**
 * Created by BerkayDemirel on 10.06.2018.
 */

public class ListProfilPaylasimlarAdapter extends ArrayAdapter<Paylasim> {
    LayoutInflater layoutInflater;
    Context context;
    List<Paylasim> paylasimlarListesi;

    public ListProfilPaylasimlarAdapter(Context context, int resource, List<Paylasim> paylasimlarListesi) {
        super(context, resource, paylasimlarListesi);
        this.context = context;
        this.paylasimlarListesi = paylasimlarListesi;
    }

    public void setPaylasimlarListesi(List<Paylasim> paylasimlarListesi) {
        this.paylasimlarListesi = paylasimlarListesi;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.list_item_profil_paylasimlar, parent, false);

        TextView textViewPaylasimlar = (TextView) v.findViewById(R.id.textViewListItemProfilIcerik);
        Paylasim p = paylasimlarListesi.get(position);
        textViewPaylasimlar.setText(p.getYazi());

        return v;
    }

}

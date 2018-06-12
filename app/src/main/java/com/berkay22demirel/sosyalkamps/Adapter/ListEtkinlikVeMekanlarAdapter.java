package com.berkay22demirel.sosyalkamps.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.berkay22demirel.sosyalkamps.Isletmeci.Isletmeci;
import com.berkay22demirel.sosyalkamps.Paylasim;
import com.berkay22demirel.sosyalkamps.R;

import java.util.List;

/**
 * Created by BerkayDemirel on 11.06.2018.
 */

public class ListEtkinlikVeMekanlarAdapter extends ArrayAdapter<Isletmeci> {
    LayoutInflater layoutInflater;
    Context context;
    List<Isletmeci> etkinlikVeMekanlarListesi;

    public ListEtkinlikVeMekanlarAdapter(Context context, int resource, List<Isletmeci> etkinlikVeMekanlarListesi) {
        super(context, resource, etkinlikVeMekanlarListesi);
        this.context = context;
        this.etkinlikVeMekanlarListesi = etkinlikVeMekanlarListesi;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.list_item_etkinlik_ve_mekanlar, parent, false);

        TextView textViewMekanAdi = (TextView) v.findViewById(R.id.textViewEtkinlikVeMekanlarListItemMekanAdi);
        TextView textViewMekanAdresi = (TextView) v.findViewById(R.id.textViewEtkinlikVeMekanlarListItemMekanAdresi);
        Isletmeci m = etkinlikVeMekanlarListesi.get(position);
        textViewMekanAdi.setText(m.getMekanAdi());
        textViewMekanAdresi.setText(m.getMekanAdres());

        return v;
    }
}

package com.berkay22demirel.sosyalkamps.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.berkay22demirel.sosyalkamps.Kullanici;
import com.berkay22demirel.sosyalkamps.Mesaj;
import com.berkay22demirel.sosyalkamps.R;

import java.util.List;

/**
 * Created by BerkayDemirel on 11.06.2018.
 */

public class ListMesajAdapter extends ArrayAdapter<Mesaj> {
    LayoutInflater layoutInflater;
    Context context;
    List<Mesaj> mesajListesi;

    public ListMesajAdapter(Context context, int resource, List<Mesaj> mesajListesi) {
        super(context, resource, mesajListesi);
        this.context = context;
        this.mesajListesi = mesajListesi;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.list_item_mesaj,parent,false);

        TextView textViewGonderen = (TextView) v.findViewById(R.id.textViewListItemMesajGonderen);
        TextView textViewIcerik = (TextView) v.findViewById(R.id.textViewListItemMesajIcerik);
        Mesaj m = mesajListesi.get(position);
        textViewGonderen.setText("Gönderen : " + m.getKimdenEposta());
        textViewIcerik.setText(m.getIcerik());

        return v;
    }
}

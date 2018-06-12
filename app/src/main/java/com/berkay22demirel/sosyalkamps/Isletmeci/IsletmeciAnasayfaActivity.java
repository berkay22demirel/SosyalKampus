package com.berkay22demirel.sosyalkamps.Isletmeci;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.berkay22demirel.sosyalkamps.Adapter.PagerIsletmeciAdapter;
import com.berkay22demirel.sosyalkamps.Background.IsletmeciAnasayfaAsyncTask;
import com.berkay22demirel.sosyalkamps.Background.YorumGetirAsyncTask;
import com.berkay22demirel.sosyalkamps.Interface.TaskComplateIsletmeciGetir;
import com.berkay22demirel.sosyalkamps.Interface.TaskComplateYorum;
import com.berkay22demirel.sosyalkamps.R;
import com.berkay22demirel.sosyalkamps.Yorum;

import java.util.List;

public class IsletmeciAnasayfaActivity extends AppCompatActivity implements TaskComplateIsletmeciGetir {
    TabLayout tabLayout;
    ViewPager viewPager;
    PagerIsletmeciAdapter adapter;

    Intent gelenIntent;
    Bundle gelenBundle;

    Isletmeci gelenIsletmeci;
    Isletmeci isletmeci;
    List<Yorum> yorumListesi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isletmeci_anasayfa);

        gelenIntent = getIntent();
        gelenBundle = gelenIntent.getExtras();
        gelenIsletmeci = (Isletmeci) gelenBundle.getSerializable("isletmeci");

        tabLayout = (TabLayout) findViewById(R.id.tab_layout_isletmeci);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.mekan_profil_logo));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.yorumlar_logo));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) findViewById(R.id.pager_isletmeci);

        IsletmeciAnasayfaAsyncTask isletmeciAnasayfaAsyncTask = new IsletmeciAnasayfaAsyncTask(this);
        isletmeciAnasayfaAsyncTask.execute(gelenIsletmeci.getEposta());
        
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onTaskCompleteIsletmeciGetir(Isletmeci isletmeci,List<Yorum> yorumListesi) {
        this.isletmeci = isletmeci;
        this.yorumListesi = yorumListesi;

        adapter = new PagerIsletmeciAdapter(getSupportFragmentManager(),tabLayout.getTabCount(),gelenIsletmeci,yorumListesi,isletmeci);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorWhite));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition() == 0){
                    getSupportActionBar().setTitle("Profil");
                }
                else if(tab.getPosition() == 1){
                    getSupportActionBar().setTitle("Yorumlar");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}

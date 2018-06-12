package com.berkay22demirel.sosyalkamps;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.TabLayout;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.berkay22demirel.sosyalkamps.Adapter.PagerAdapter;
import com.berkay22demirel.sosyalkamps.Background.AnasayfaAsyncTask;
import com.berkay22demirel.sosyalkamps.Interface.TaskComplateAnasayfa;
import com.berkay22demirel.sosyalkamps.Isletmeci.Isletmeci;

import java.util.List;

public class AnasayfaActivity extends AppCompatActivity implements TaskComplateAnasayfa {
    TabLayout tabLayout;
    ViewPager viewPager;
    PagerAdapter adapter;

    Kullanici gelenKullanici;

    Intent intentGirisYap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anasayfa);

        intentGirisYap = getIntent();
        tabLayout = (TabLayout) findViewById(R.id.tab_layout_isletmeci);

        AnasayfaAsyncTask anasayfaAsyncTask = new AnasayfaAsyncTask(AnasayfaActivity.this);
        anasayfaAsyncTask.execute(intentGirisYap.getStringExtra("eposta"));

    }


    @Override
    public void onTaskComplete(Kullanici kullanici, List<Paylasim> profilPaylasimListesi, List<Paylasim> paylasimListesi, List<Isletmeci> etkinlikVeMekanlarListesi) {
        gelenKullanici = kullanici;

        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.zaman_tuneli_logo));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.profil_logo));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.etkinlik_ve_mekanlar_logo));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) findViewById(R.id.pager);
        adapter = new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount(),gelenKullanici,profilPaylasimListesi,paylasimListesi,etkinlikVeMekanlarListesi,gelenKullanici.eposta);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorWhite));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition() == 0){
                    getSupportActionBar().setTitle("Zaman Tüneli");
                }
                else if(tab.getPosition() == 1){
                    getSupportActionBar().setTitle("Profil");
                }
                else if(tab.getPosition() == 2){
                    getSupportActionBar().setTitle("Etkinlik ve Mekanlar");
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_anasayfa,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_anasayfa_mesajlar:
                Intent intentMesaj = new Intent(AnasayfaActivity.this, KullaniciMesajlarActivity.class);
                Bundle bundleMesaj = new Bundle();
                bundleMesaj.putSerializable("kullanici",gelenKullanici);
                intentMesaj.putExtras(bundleMesaj);
                startActivity(intentMesaj);
                break;
            case R.id.menu_anasayfa_ayarlar:
                Intent intentDuzenle = new Intent(AnasayfaActivity.this, KullaniciProfilDuzenleActivity.class);
                Bundle bundleDuzenle = new Bundle();
                bundleDuzenle.putSerializable("kullanici",gelenKullanici);
                intentDuzenle.putExtras(bundleDuzenle);
                startActivity(intentDuzenle);
                break;
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

}

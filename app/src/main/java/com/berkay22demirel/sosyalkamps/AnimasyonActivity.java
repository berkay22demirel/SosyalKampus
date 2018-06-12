package com.berkay22demirel.sosyalkamps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class AnimasyonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animasyon);
        getSupportActionBar().hide();

        anaGirisAc();
    }

    private void anaGirisAc(){
        Animation anim = AnimationUtils.loadAnimation(this,R.anim.animasyon_activity);
        ImageView girisLogo = (ImageView) findViewById(R.id.imageViewAnimasyonActivityGirisLogo);
        anim.reset();
        girisLogo.clearAnimation();
        girisLogo.startAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }
            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(AnimasyonActivity.this,AnaGirisActivity.class);
                startActivity(intent);
                AnimasyonActivity.this.finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}

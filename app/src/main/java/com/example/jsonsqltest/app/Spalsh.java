package com.example.jsonsqltest.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
  Created by Jørgen Johansen on 23.11.2015.
 */
public class Spalsh extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        // Plukker opp imageview fra xml-filen
        final ImageView iv = (ImageView)findViewById(R.id.imageView);
        // Lage animasjon varriabel og laster inn egenskapene som er definert i xml-filen
        final Animation an = AnimationUtils.loadAnimation(getBaseContext(),R.anim.rotate);
        // Her var tanken at det skulle være en fade-out effekt, men dette fikk jeg ikke riktig til
        final Animation an2 = AnimationUtils.loadAnimation(getBaseContext(),R.anim.rotate);

        // Starter animasjonen på imageviewet med an- animasjon som deklarert over
        iv.startAnimation(an);
        // Setter på en "animasjonslytter" som implementerer noen metoder - boilerplate(ferdig generert). I mitt tilfelle bruker jeg onAnimationEnd
        an.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //Starter animasjon 2 på imageviewet
                iv.startAnimation(an2);
                // Ferdiggjør animasjonen
                finish();
                // Fyrer opp en ny Intent som tar brukeren til MainActivity klassen
                Intent i = new Intent(getBaseContext(),MainActivity.class);
                // Kjører Intent i
                startActivity(i);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}

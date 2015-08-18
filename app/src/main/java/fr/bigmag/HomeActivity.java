package fr.bigmag;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_home);

        findViewById(R.id.home_comment_marche).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, TutorialActivity.class);
                startActivity(i);
            }
        });

        findViewById(R.id.home_promos).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, WebviewActivity.class);
                i.putExtra("url", "http://bigmag.numeria-communication.fr/category/offres-partenaires/");
                startActivity(i);
            }
        });

        findViewById(R.id.home_actualites).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, WebviewActivity.class);
                i.putExtra("url", "http://bigmag.numeria-communication.fr/category/actualites/");
                startActivity(i);
            }
        });

        findViewById(R.id.home_trouver_mcdo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, MapsActivity.class);
                startActivity(i);
            }
        });
    }
}

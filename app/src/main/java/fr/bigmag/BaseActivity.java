package fr.bigmag;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.bealder.sdk.manager.BealderParameters;


public class BaseActivity extends AppCompatActivity {
    protected DrawerLayout mDrawerLayout;
    protected View mDrawer;

    protected ActionBarDrawerToggle mDrawerToggle;

    protected void onCreate(Bundle savedInstanceState, int layoutId) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawer = findViewById(R.id.drawer);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                // getActionBar().setTitle(mTitle);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // getActionBar().setTitle(mDrawerTitle);
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Do nothing.
                // (otherwise events are propagated to the activity below)
            }
        });

        findViewById(R.id.menu_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BaseActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });


        findViewById(R.id.menu_mesoffres).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BaseActivity.this, MessagesActivity.class);
                startActivity(intent);
            }
        });


        findViewById(R.id.menu_comment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BaseActivity.this, TutorialActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.menu_promo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BaseActivity.this, WebviewActivity.class);
                intent.putExtra("url", "http://bigmag.numeria-communication.fr/category/promos-mc-do/");
                startActivity(intent);
            }
        });


        findViewById(R.id.menu_offres).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BaseActivity.this, WebviewActivity.class);
                intent.putExtra("url", "http://bigmag.numeria-communication.fr/category/offres-partenaires/");
                startActivity(intent);
            }
        });


        findViewById(R.id.menu_actualites).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BaseActivity.this, WebviewActivity.class);
                intent.putExtra("url", "http://bigmag.numeria-communication.fr/category/actualites/");
                startActivity(intent);
            }
        });


        findViewById(R.id.menu_suivez).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/bigmagmagazine?ref=hl"));
                startActivity(intent);
            }
        });


        findViewById(R.id.menu_apropos).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BaseActivity.this, WebviewActivity.class);
                intent.putExtra("url", "http://bigmag.numeria-communication.fr/a-propos/");
                startActivity(intent);
            }
        });


        findViewById(R.id.menu_mentions).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BaseActivity.this, WebviewActivity.class);
                intent.putExtra("url", "http://bigmag.numeria-communication.fr/mentions-legales/");
                startActivity(intent);
            }
        });


        findViewById(R.id.menu_trouver).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BaseActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        if(item.getItemId() == R.id.action_comment) {
            Intent intent = new Intent(BaseActivity.this, TutorialActivity.class);
            startActivity(intent);
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // - require -
        BealderParameters.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // - require -
        BealderParameters.onStop();
    }
}
package fr.bigmag;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.Menu;
import android.view.MenuItem;

import fr.bigmag.fragment.TutorialSlideFragment;


public class TutorialActivity extends SlideActivity {
    /**
     * The number of pages to show in this tutorial.
     */
    private final int NUM_PAGES = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_tutorial);

        initPager(new ScreenSlidePagerAdapter(getSupportFragmentManager()));
    }

    protected class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            bundle.putInt("page", position);

            Fragment page = new TutorialSlideFragment();
            page.setArguments(bundle);
            return page;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tutorial, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        if(item.getItemId() == R.id.action_close) {
            finish();
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }
}
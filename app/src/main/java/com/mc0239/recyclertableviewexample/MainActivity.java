package com.mc0239.recyclertableviewexample;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.mc0239.recyclertableviewexample.database.AppDatabase;
import com.mc0239.recyclertableviewexample.samples.FragmentSampleCheckbox;
import com.mc0239.recyclertableviewexample.samples.FragmentSampleEdittext;
import com.mc0239.recyclertableviewexample.samples.FragmentSampleInteractive;
import com.mc0239.recyclertableviewexample.samples.FragmentSampleLivedata;
import com.mc0239.recyclertableviewexample.samples.FragmentSampleRadiobutton;
import com.mc0239.recyclertableviewexample.samples.FragmentSampleUsage;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    private Fragment
            fragmentSampleUsage,
            fragmentSampleCheckbox,
            fragmentSampleRadiobutton,
            fragmentSampleEdittext,
            fragmentSampleLiveData,
            fragmentSampleInteractive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        drawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentSampleUsage = new FragmentSampleUsage();
        fragmentSampleCheckbox = new FragmentSampleCheckbox();
        fragmentSampleRadiobutton = new FragmentSampleRadiobutton();
        fragmentSampleEdittext = new FragmentSampleEdittext();
        fragmentSampleLiveData = new FragmentSampleLivedata();
        fragmentSampleInteractive = new FragmentSampleInteractive();

        AppDatabase.getDatabase(this).generateSampleData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        menuItem.setChecked(true);
        drawerLayout.closeDrawers();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        int mid = menuItem.getItemId();

        if (mid == R.id.nav_sample) {
            transaction.replace(R.id.fragment_frame, fragmentSampleUsage);
        } else if (mid == R.id.nav_sample_checkbox) {
            transaction.replace(R.id.fragment_frame, fragmentSampleCheckbox);
        } else if (mid == R.id.nav_sample_radio) {
            transaction.replace(R.id.fragment_frame, fragmentSampleRadiobutton);
        } else if (mid == R.id.nav_sample_edittext) {
            transaction.replace(R.id.fragment_frame, fragmentSampleEdittext);
        } else if (mid == R.id.nav_sample_livedata) {
            transaction.replace(R.id.fragment_frame, fragmentSampleLiveData);
        } else if (mid == R.id.nav_sample_interactive) {
            transaction.replace(R.id.fragment_frame, fragmentSampleInteractive);
        } else {
            Log.w(getClass().getSimpleName(), "Navigation menu selection not handled.");
        }

        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.disallowAddToBackStack();
        transaction.commit();

        return true;
    }
}

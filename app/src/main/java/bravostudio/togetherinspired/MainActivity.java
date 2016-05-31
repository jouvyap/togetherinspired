package bravostudio.togetherinspired;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import bravostudio.togetherinspired.Adapter.SectionsPagerAdapter;
import bravostudio.togetherinspired.Interface.ApiEndpointInterface;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    public static final int LOGIN_RESULT_ACTIVITY = 1;
    public static final String BASE_URL = "http://jouvyap.com/together/";

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    public Retrofit retrofit;
    public ApiEndpointInterface apiEndpointInterface;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private View mNavigationHeader;
    private Menu mNavigationMenu;
    private boolean loggedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mNavigationHeader = navigationView.getHeaderView(0);
        mNavigationMenu = navigationView.getMenu();
        TextView loginLabel = (TextView) mNavigationHeader.findViewById(R.id.navHeaderLabelLogin);
        loginLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivityForResult(loginIntent, LOGIN_RESULT_ACTIVITY);
            }
        });

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiEndpointInterface = retrofit.create(ApiEndpointInterface.class);

        updateNavigationHeader(null, null);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public ApiEndpointInterface getApiEndpointInterface(){
        return apiEndpointInterface;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_reward) {
            Toast.makeText(MainActivity.this, "Reward Clicked", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_store) {
            Toast.makeText(MainActivity.this, "Store Clicked", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_profile){
            Toast.makeText(MainActivity.this, "Change Profile Clicked", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_logout){
            logout();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case (LOGIN_RESULT_ACTIVITY):{
                if (resultCode == Activity.RESULT_OK) {
                    boolean loggedIn = data.getBooleanExtra(LoginActivity.RETURN_INTENT, false);
                    String nameLoggedIn = data.getStringExtra(LoginActivity.RETURN_NAME);
                    String emailLoggedIn = data.getStringExtra(LoginActivity.RETURN_EMAIL);
                    if(loggedIn){
                        login(nameLoggedIn, emailLoggedIn);
                    }
                }
                break;
            }
        }
    }

    private void updateNavigationHeader(String name, String email){
        TextView nameLabel = (TextView) mNavigationHeader.findViewById(R.id.navHeaderLabelName);
        TextView emailLabel = (TextView) mNavigationHeader.findViewById(R.id.navHeaderLabelEmail);
        TextView loginLabel = (TextView) mNavigationHeader.findViewById(R.id.navHeaderLabelLogin);
        MenuItem rewardMenu = mNavigationMenu.findItem(R.id.nav_reward);
        MenuItem storeMenu = mNavigationMenu.findItem(R.id.nav_store);

        if(loggedIn){
            nameLabel.setText(name);
            emailLabel.setText(email);
            loginLabel.setVisibility(View.INVISIBLE);
            rewardMenu.setIcon(R.mipmap.ic_card_giftcard_black_24dp);
            rewardMenu.setEnabled(true);
            storeMenu.setIcon(R.mipmap.ic_shopping_basket_black_24dp);
            storeMenu.setEnabled(true);
            mNavigationMenu.setGroupVisible(R.id.menu_group_user, true);
        } else{
            nameLabel.setText("Login first");
            emailLabel.setText("to unlock all features");
            loginLabel.setVisibility(View.VISIBLE);
            rewardMenu.setIcon(R.mipmap.ic_lock_black_24dp);
            rewardMenu.setEnabled(false);
            storeMenu.setIcon(R.mipmap.ic_lock_black_24dp);
            storeMenu.setEnabled(false);
            mNavigationMenu.setGroupVisible(R.id.menu_group_user, false);
        }
    }

    private void login(String name, String email){
        Toast.makeText(MainActivity.this, "Login success", Toast.LENGTH_SHORT).show();
        loggedIn = true;
        updateNavigationHeader(name, email);
    }

    private void logout(){
        Toast.makeText(MainActivity.this, "Logout success", Toast.LENGTH_SHORT).show();
        loggedIn = false;
        updateNavigationHeader(null, null);
    }

}

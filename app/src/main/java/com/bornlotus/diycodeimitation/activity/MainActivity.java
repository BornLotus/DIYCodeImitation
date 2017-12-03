package com.bornlotus.diycodeimitation.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.bornlotus.diycodeimitation.R;
import com.bornlotus.diycodeimitation.activity.api.http.HttpServiceManager;
import com.bornlotus.diycodeimitation.activity.fragment.NewsFragment;
import com.bornlotus.diycodeimitation.activity.fragment.SitesFragment;
import com.bornlotus.diycodeimitation.activity.fragment.TopicsFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {


    private Toolbar mToolbar;
    private DrawerLayout mDrawer;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<Fragment> list;
    private List<String> titles;
    private NavigationView navigationView;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

    }

    private void initViews(){
        mDrawer = findViewById(R.id.drawerLayout);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                toastShort(item.getTitle().toString());
                mDrawer.closeDrawers();
                return true;
            }
        });
        initToolbar();
        initViewPager();
    }

    private void initViewPager(){
        viewPager = findViewById(R.id.viewPager);
        list = new ArrayList<>(3);
        list.add(new TopicsFragment());
        list.add(new NewsFragment());
        list.add(new SitesFragment());
        viewPager.setAdapter(adapter);

        tabLayout = findViewById(R.id.tabLayout);
        titles = new ArrayList<>(3);
        titles.add(getResources().getString(R.string.tab_topics));
        titles.add(getResources().getString(R.string.tab_news));
        titles.add(getResources().getString(R.string.tab_sites));
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initToolbar() {
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.app_main_name);
        setSupportActionBar(mToolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setHomeButtonEnabled(true);
            ab.setDisplayHomeAsUpEnabled(true);
        }
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,mDrawer,
                mToolbar, R.string.drawer_open,R.string.drawer_close);
        toggle.syncState();
        mDrawer.addDrawerListener(toggle);
    }

    FragmentStatePagerAdapter adapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    };

    @Override
    public void onRefresh() {
        HttpServiceManager.newInstance().getTopicList("",0,0,0,null);
    }
}

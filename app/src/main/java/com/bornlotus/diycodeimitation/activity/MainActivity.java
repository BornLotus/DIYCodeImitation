package com.bornlotus.diycodeimitation.activity;

import android.os.Bundle;
import android.os.Looper;
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
import android.util.Log;
import android.view.MenuItem;

import com.bornlotus.diycodeimitation.R;
import com.bornlotus.diycodeimitation.activity.api.http.HttpServiceManager;
import com.bornlotus.diycodeimitation.activity.api.module.topic.Topic;
import com.bornlotus.diycodeimitation.activity.ui.NewsFragment;
import com.bornlotus.diycodeimitation.activity.ui.SitesFragment;
import com.bornlotus.diycodeimitation.activity.ui.TopicsFragment;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {


    public static final String TAG = "MainActivity";

    private Toolbar mToolbar;
    private DrawerLayout mDrawer;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<Fragment> list;
    private List<String> titles;
    private NavigationView navigationView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TopicsFragment topicsFragment;
    private NewsFragment newsFragment;
    private SitesFragment sitesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initData();

    }

    private void initData() {
        swipeRefreshLayout.setRefreshing(true);
        HttpServiceManager.newInstance().getTopicList(null, 1, 1, 10,
                new Observer<List<Topic>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("current thread is main thread? " +
                                (Thread.currentThread() == Looper.getMainLooper().getThread()));
                    }

                    @Override
                    public void onNext(List<Topic> topics) {
                        topicsFragment.notifyDataChanged(topics);
                        swipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initViews() {
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

    private void initViewPager() {
        viewPager = findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(3);
        list = new ArrayList<>(3);
        topicsFragment = new TopicsFragment();
        newsFragment = new NewsFragment();
        sitesFragment = new SitesFragment();
        list.add(topicsFragment);
        list.add(newsFragment);
        list.add(sitesFragment);
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
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer,
                mToolbar, R.string.drawer_open, R.string.drawer_close);
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
        Log.e(TAG, "onRefresh: data loading...");
        onRefreshData();
    }

    private void onRefreshData() {
        topicsFragment.refreshPageIndex();
        HttpServiceManager.newInstance().getTopicList(null, 1,
                topicsFragment.getPageIndex() * topicsFragment.getPageCount(),
                topicsFragment.getPageCount(),
                new Observer<List<Topic>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Topic> topics) {
                        swipeRefreshLayout.setRefreshing(false);
                        topicsFragment.notifyDataChanged(topics);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: is called");
                    }
                });
    }
}

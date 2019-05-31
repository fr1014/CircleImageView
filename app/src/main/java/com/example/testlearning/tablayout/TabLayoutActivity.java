package com.example.testlearning.tablayout;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.testlearning.R;
import com.example.testlearning.utils.ToastUtil;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class TabLayoutActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private FragmentAdapter mFragmentAdapter;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mActionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_navigationview);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mDrawerLayout = findViewById(R.id.dl_main_drawer);
        mNavigationView = findViewById(R.id.nv_main_navigation);

        //设置Toolbar菜单图标和点击动画
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
        mActionBarDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);

        //设置退出按钮
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (mNavigationView != null){
            mNavigationView.setItemIconTintList(null);
            mNavigationView.setNavigationItemSelectedListener(menuItem -> {
                menuItem.setCheckable(true);
                String title = menuItem.getTitle().toString();
                ToastUtil.toastShort(this,title);
                //关闭导航菜单
                mDrawerLayout.closeDrawers();
                return true;
            });
        }

        mViewPager = findViewById(R.id.viewpager);
        initViewPager();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initViewPager() {
        mTabLayout = findViewById(R.id.tabs);

        List<String> titles = new ArrayList<>();
        titles.add("精选");
        titles.add("铃铛");
        titles.add("叮当");
        titles.add("测试");

        for (int i = 0;i<titles.size();i++){
            mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(i)));
        }

        List<Fragment> fragments = new ArrayList<>();
        for (int i=0;i<titles.size();i++){
            fragments.add(new ListFragment());
        }
        //给ViewPager设置适配器
        mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,fragments,titles);
        mViewPager.setAdapter(mFragmentAdapter);
        //将TabLayout和ViewPager关联起来
        mTabLayout.setupWithViewPager(mViewPager);
    }

}
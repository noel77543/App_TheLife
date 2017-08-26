package com.sung.noel.tw.thelife.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.sung.noel.tw.thelife.R;
import com.sung.noel.tw.thelife.ViewHandler;
import com.sung.noel.tw.thelife.base.BaseRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements BaseRecyclerViewAdapter.onItemClickListener {

    @BindView(R.id.toolbar_title)
    public TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.type_list)
    RecyclerView typeRecyclerView;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(android.R.id.tabhost)
    FragmentTabHost tabHost;

    private ViewHandler viewHandler;

    //main 的水平recyclerview
    private String[] mainTitles;
    private int[] mainImages;
    private List<Mains> maintypeList;
    private MainTypeAdapter adapter;

    //划動的fragment及tab
    private List<Fragment> fragmentList;
    private MainViewPagerAdapter viewPagerAdapter;

    //viewpagerFragment 用
    private Class tabClass[];
    private Fragment tabFragments[];
    private String tabTitles[];
    private int tabImages[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        init();

    }

    //------------------------------------
    private void init() {
        viewHandler = new ViewHandler(this);
        adapter = new MainTypeAdapter(this);
        viewPagerAdapter = new MainViewPagerAdapter(this, getSupportFragmentManager());
        maintypeList = new ArrayList<>();
        mainTitles = getResources().getStringArray(R.array.main_type);

        //// TODO: 2017/8/26  先隨便放icon之後畫好再回來改
        mainImages = new int[]{
                R.drawable.news_tab_global,
                R.drawable.news_tab_global,
                R.drawable.news_tab_global
        };
        fragmentList = new ArrayList<Fragment>();
        tabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        initMainType();
        initTabs();
        initEvent();
    }

    //-------------------------------
    private void initMainType() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        adapter.setOnItemClickListener(this);
        adapter.setData(getMainTypeData(maintypeList));
        typeRecyclerView.setLayoutManager(layoutManager);
        typeRecyclerView.setAdapter(adapter);
//        typeRecyclerView.getChildAt(0).setSelected(true);
    }
//-------------------------------

    private List<Mains> getMainTypeData(List<Mains> maintypeList) {

        for (int i = 0; i < mainTitles.length; i++) {
            maintypeList.add(new Mains(mainImages[i], mainTitles[i]));

        }
        return maintypeList;
    }
//-------------------------------

    private void initTabs() {
        tabHost.getTabWidget().setDividerDrawable(null);
        //// TODO: 2017/8/26  測試  之後要修改為 MAIN
        selectedType(MainType.TYPE_MAIN);

    }

//-------------------------------

    private View getTabView(int index) {
        View view = LayoutInflater.from(this).inflate(R.layout.list_tab, null);

        ImageView image = (ImageView) view.findViewById(R.id.ic_image);
        TextView title = (TextView) view.findViewById(R.id.tv_title);

        image.setImageResource(tabImages[index]);
        title.setText(tabTitles[index]);

        return view;
    }

//-------------------------------

    private void initEvent() {

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                viewPager.setCurrentItem(tabHost.getCurrentTab());
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabHost.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

//-------------------------------

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    //-------------------------------
    private void selectedType(int type) {
        tabHost.clearAllTabs();
        fragmentList.clear();

        switch (type) {
            case MainType.TYPE_MAIN:
                Log.e("TYPE_MAIN", "TYPE_MAIN");
                tabClass = viewHandler.getMainClass();
                tabFragments = viewHandler.getMainFragment();
                tabImages = viewHandler.getMainImages();
                tabTitles = viewHandler.getMainTitle();
                break;

            case MainType.TYPE_NEWS:
                Log.e("TYPE_NEWS", "TYPE_NEWS");

                break;
            case MainType.TYPE_GARBAGE:
                Log.e("TYPE_GARBAGE", "TYPE_GARBAGE");

                tabClass = viewHandler.getGarbageClass();
                tabFragments = viewHandler.getGarbageFragment();
                tabImages = viewHandler.getGarbageImages();
                tabTitles = viewHandler.getGarbageTitle();
                break;
        }
        for (int i = 0; i < tabFragments.length; i++) {
            TabHost.TabSpec tabSpec = tabHost.newTabSpec(tabTitles[i]).setIndicator(getTabView(i));
            tabHost.addTab(tabSpec, tabClass[i], null);
            fragmentList.add(tabFragments[i]);
            tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.WHITE);
        }
        viewPagerAdapter.setData(fragmentList);
        viewPager.setAdapter(viewPagerAdapter);
    }

    //------------------------------------
    @Override
    public void onItemClick(View view, int position) {
        selectedType(position);
        Log.e("onItemClick", "onItemClick");
    }
}

package com.sung.noel.tw.thelife;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.sung.noel.tw.thelife.garbage.listmode.GarbageListModeFragment;
import com.sung.noel.tw.thelife.garbage.mapmode.GarbageMapModeFragment;
import com.sung.noel.tw.thelife.main.MainFragment;
import com.sung.noel.tw.thelife.news.global.NewsGlobalFragment;
import com.sung.noel.tw.thelife.news.taiwan.NewsTaiwanFragment;


/**
 * Created by User on 2017/8/26.
 */

public class ViewHandler {
    private Context context;

    public ViewHandler(Context context) {
        this.context = context;
    }




    //MainTabs

    public Class[] getMainClass() {
        return mainClass;
    }
    public Fragment[] getMainFragment() {
        return mainFragment;
    }
    public String[] getMainTitle() {
        return mainTitle;
    }
    public int[] getMainImages() {
        return mainImages;
    }

    private Class mainClass[] = {MainFragment.class};
    private Fragment mainFragment[] = {new MainFragment()};
    private String[] mainTitle = new String[]{"首頁"};
    private int mainImages[] = new int[]{R.drawable.garbage_tab_list};


    //GarbageTabs
    public Class[] getGarbageClass() {
        return garbageClass;
    }
    public Fragment[] getGarbageFragment() {
        return garbageFragment;
    }
    public String[] getGarbageTitle() {
        return garbageTitle;
    }
    public int[] getGarbageImages() {
        return garbageImages;
    }

    private Class garbageClass[] = {GarbageListModeFragment.class, GarbageMapModeFragment.class};
    private Fragment garbageFragment[] = {new GarbageListModeFragment(), new GarbageMapModeFragment()};
    private String[] garbageTitle = new String[]{"附近站點", "秀給我看"};
    private int garbageImages[] = new int[]{R.drawable.garbage_tab_list, R.drawable.garbage_tab_map};
















//    //NewsTabs
//    public Class newsClass[] = {NewsTaiwanFragment.class, NewsGlobalFragment.class};
//    public Fragment newsFragment[] = {new NewsTaiwanFragment(), new NewsGlobalFragment()};
//    @SuppressLint("StringFormatInvalid")
//    public String[] newsTitle = {context.getString(R.string.news_title_taiwan, context.getString(R.string.news_title_global))};
//    public int newsImages[] = {R.drawable.news_tab_taiwan, R.drawable.news_tab_global};

}

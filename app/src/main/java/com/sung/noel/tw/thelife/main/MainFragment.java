package com.sung.noel.tw.thelife.main;

import android.content.Context;

import com.sung.noel.tw.thelife.R;
import com.sung.noel.tw.thelife.activity.MainActivity;
import com.sung.noel.tw.thelife.base.BaseCustomFragment;

/**
 * Created by User on 2017/8/26.
 */

public class MainFragment extends BaseCustomFragment {

    private MainActivity mainActivity;
    //--------------------------------------------------
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) context;
    }

    @Override
    protected void init() {
        setView(R.layout.fragment_main);

    }
}

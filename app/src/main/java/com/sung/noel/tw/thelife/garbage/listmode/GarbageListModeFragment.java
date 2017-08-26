package com.sung.noel.tw.thelife.garbage.listmode;

import android.content.Context;

import com.sung.noel.tw.thelife.R;
import com.sung.noel.tw.thelife.base.BaseCustomFragment;
import com.sung.noel.tw.thelife.activity.MainActivity;

/**
 * Created by User on 2017/8/26.
 */

public class GarbageListModeFragment extends BaseCustomFragment {
    private MainActivity mainActivity;

    //--------------------------------------------------
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) context;
    }

    @Override
    protected void init() {
        setView(R.layout.fragment_garbage_list_mode);

    }
}

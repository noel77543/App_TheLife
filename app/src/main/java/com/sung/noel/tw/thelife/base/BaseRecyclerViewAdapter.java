package com.sung.noel.tw.thelife.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by User on 2017/8/26.
 */

public abstract class BaseRecyclerViewAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    protected onItemClickListener mOnItemClickListener = null;

    //--------------------------------------------------

    /**
     * 監聽Item的ClickListener
     */
    public interface onItemClickListener {
        void onItemClick(View view, int position);
    }
    //--------------------------------------------------

    /**
     * 設定RecyclerView的onItemClickListener
     *
     * @param listener
     */
    public void setOnItemClickListener(onItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}

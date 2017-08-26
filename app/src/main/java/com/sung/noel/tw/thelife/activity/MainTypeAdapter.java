package com.sung.noel.tw.thelife.activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sung.noel.tw.thelife.R;
import com.sung.noel.tw.thelife.base.BaseRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by User on 2017/8/26.
 */

public class MainTypeAdapter extends BaseRecyclerViewAdapter {
    private List<Mains> mainsList;
    private Context context;


    public MainTypeAdapter(Context context) {
        this.context = context;
        mainsList = new ArrayList<>();
    }

    //----------------------------
    public void setData(List<Mains> mainsList) {
        this.mainsList = mainsList;
        this.notifyDataSetChanged();
    }
    //----------------------------

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_main, parent, false));
    }
    //----------------------------

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ((ViewHolder) holder).btnMaintype.setCompoundDrawablesWithIntrinsicBounds(mainsList.get(position).getIconRes(), 0, 0, 0);
            ((ViewHolder) holder).btnMaintype.setText(mainsList.get(position).getTitle());
        }
    }
    //----------------------------

    @Override
    public int getItemCount() {
        return mainsList.size();
    }
    //----------------------------

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.btn_maintype)
        Button btnMaintype;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            //列表item點下事件
            btnMaintype.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(view, getLayoutPosition());
                    }
                }
            });
        }
    }

    //------------------------------

}

package com.sung.noel.tw.thelife.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sung.noel.tw.thelife.activity.MainActivity;

import butterknife.ButterKnife;

/**
 * Created by User on 2017/8/26.
 */

public abstract class BaseCustomFragment extends Fragment {

    protected AppCompatActivity activity;
    protected View view;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            activity = (MainActivity) context;
        }
    }
    // ----------------------------------------------------
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {

            // initial.
            init();
        } else {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        }
        return view;
    }
    protected abstract void init();

    /**
     * 前往Fragment
     *
     * @param fragment       要進入的Fragment
     * @param addToBackStack 是否需要返回
     * @param bundle         傳送的資料
     */
    public void replaceFragment(Fragment fragment, boolean addToBackStack, Bundle bundle) {

        fragment.setArguments(bundle);
        baseReplace(fragment, addToBackStack);
    }

    // ----------------------------------------------------

    /**
     * 是Fragment裡面又有包含Fragment的狀況時，使用此method
     *
     * @param layoutId       子Fragment的id
     * @param fragment
     * @param addToBackStack
     * @param bundle
     */
    public void replaceFragment(int layoutId, Fragment fragment, boolean addToBackStack, Bundle bundle) {

        fragment.setArguments(bundle);
        baseReplace(layoutId, fragment, addToBackStack);
    }

    // ----------------------------------------------------

    /**
     * 前往Fragment
     *
     * @param fragment       要進入的Fragment
     * @param addToBackStack 是否需要返回
     */
    public void replaceFragment(Fragment fragment, boolean addToBackStack) {

        baseReplace(fragment, addToBackStack);
    }

    // ----------------------------------------------------

    /**
     * 是Fragment裡面又有包含Fragment的狀況時，使用此method
     *
     * @param layoutId       子Fragment的id
     * @param fragment
     * @param addToBackStack
     */
    public void replaceFragment(int layoutId, Fragment fragment, boolean addToBackStack) {

        baseReplace(layoutId, fragment, addToBackStack);
    }

    // ----------------------------------------------------
    @Override
    public void onDestroy() {
        super.onDestroy();
        Runtime.getRuntime().gc();
        System.gc();
    }

    // ----------------------------------------------------

    /**
     * base fragment replace.
     */
    private void baseReplace(Fragment fragment, boolean addToBackStack) {

        FragmentTransaction transaction;
        transaction = getFragmentManager().beginTransaction();

        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.replace(android.R.id.tabcontent, fragment);
        transaction.commit();

        if (getFragmentManager() != null) {
            getFragmentManager().executePendingTransactions();
        }
    }

    // ----------------------------------------------------

    /**
     * base fragment replace.
     */
    private void baseReplace(int layoutId, Fragment fragment, boolean addToBackStack) {

        FragmentTransaction transaction;
        transaction = getFragmentManager().beginTransaction();

        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.replace(layoutId, fragment);
        transaction.commit();

        if (getChildFragmentManager() != null) {
            getChildFragmentManager().executePendingTransactions();
        }
    }

    // ----------------------------------------------------
    public void setView(int resource) {
        this.view = LayoutInflater.from(getActivity()).inflate(resource, null);
        ButterKnife.bind(this, view);
    }


    /**
     * 清除所有存入的FragmentBackStack
     */
    public void cleanStack() {

        int backStackCount = getActivity().getSupportFragmentManager().getBackStackEntryCount();
        for (int i = 0; i < backStackCount; i++) {
            // Get the back stack fragment id.
            int backStackId = getActivity().getSupportFragmentManager().getBackStackEntryAt(i).getId();
            getActivity().getSupportFragmentManager().popBackStack(backStackId, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }
}
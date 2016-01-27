package com.wpmac.zhihuiyun.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wpmac.zhihuiyun.R;
import com.wpmac.zhihuiyun.debug.L;

/**
 * Created by wpmac on 16/1/26.
 */
public class PointQueryFragment extends Fragment {

    private View mView;
    private static PointQueryFragment pointQueryFragment;

    public PointQueryFragment() {
    }

    public static PointQueryFragment getInstance(){
        if(pointQueryFragment==null){
            pointQueryFragment= new PointQueryFragment();
        }
        return pointQueryFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            L.i("PointUpdateFragment  onCreateView");
            mView = inflater.inflate(R.layout.fragment_point_query, container,
                    false);

        } else {
            L.i("PointUpdateFragment   no  onCreateView");
        }

        return mView;
    }
}

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
public class PointCheckFragment extends Fragment {
    private View mView;
    private static PointCheckFragment pointCheckFragment;

    public PointCheckFragment() {
    }

    public static PointCheckFragment getInstance(){
        if(pointCheckFragment==null){
            pointCheckFragment= new PointCheckFragment();
        }
        return pointCheckFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            L.i("PointCheckFragment  onCreateView");
            mView = inflater.inflate(R.layout.fragment_point_check, container,
                    false);

        } else {
            L.i("PointCheckFragment   no  onCreateView");
        }

        return mView;
    }
}

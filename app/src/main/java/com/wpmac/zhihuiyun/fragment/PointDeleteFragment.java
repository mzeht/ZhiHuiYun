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
public class PointDeleteFragment extends Fragment {



    private View mView;
    private static PointDeleteFragment pointDeleteFragment;

    public PointDeleteFragment() {
    }

    public static PointDeleteFragment getInstance(){
        if(pointDeleteFragment==null){
            pointDeleteFragment= new PointDeleteFragment();
        }
        return pointDeleteFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            L.i("PointDeleteFragment  onCreateView");
            mView = inflater.inflate(R.layout.fragment_point_delete, container,
                    false);

        } else {
            L.i("PointDeleteFragment   no  onCreateView");
        }

        return mView;
    }
}

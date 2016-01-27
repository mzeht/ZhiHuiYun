package com.wpmac.zhihuiyun.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wpmac.zhihuiyun.R;
import com.wpmac.zhihuiyun.adapter.GroupsPagerAdapter;
import com.wpmac.zhihuiyun.helper.GroupHelper;

/**
 * Created by wpmac on 16/1/26.
 */
public class MetersFragment extends Fragment {

    public MetersFragment() {
    }

    public static MetersFragment getInstance(){
        return  new MetersFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_groups,null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.groups_viewpager);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        final GroupsPagerAdapter adapter = new GroupsPagerAdapter(getChildFragmentManager());
        adapter.addCategory(GroupHelper.METER_ADD);
        adapter.addCategory(GroupHelper.METER_UPDATE);
        adapter.addCategory(GroupHelper.METER_DELETE);
        adapter.addCategory(GroupHelper.METER_QUERY);
        viewPager.setAdapter(adapter);

    }
}

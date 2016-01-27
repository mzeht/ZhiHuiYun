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
public class DevicesFragment extends Fragment {
    public DevicesFragment() {
    }

    public static DevicesFragment getInstance(){
        return  new DevicesFragment();
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
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.groups_viewpager);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        final GroupsPagerAdapter adapter = new GroupsPagerAdapter(getChildFragmentManager());
        adapter.addCategory(GroupHelper.DEVICE_ADD);
        adapter.addCategory(GroupHelper.DEVICE_UPDATE);
        adapter.addCategory(GroupHelper.DEVICE_DELETE);
        adapter.addCategory(GroupHelper.DEVICE_QUERY);
        adapter.addCategory(GroupHelper.NETWORK_CHECK);
        viewPager.setAdapter(adapter);

    }
}

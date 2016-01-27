package com.wpmac.zhihuiyun.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wpmac.zhihuiyun.R;
import com.wpmac.zhihuiyun.adapter.GroupsPagerAdapter;
import com.wpmac.zhihuiyun.helper.GroupHelper;

import butterknife.ButterKnife;

import static butterknife.ButterKnife.findById;

/**
 * Created by wpmac on 16/1/25.
 */
public class AreaFragmnet extends Fragment  {

    public AreaFragmnet() {
    }

    public static AreaFragmnet getInstance(){
        return new AreaFragmnet();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_groups, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TabLayout tabLayout = findById(view, R.id.tab_layout);
        ViewPager viewPager = findById(view, R.id.groups_viewpager);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        final GroupsPagerAdapter adapter = new GroupsPagerAdapter(getChildFragmentManager());
        adapter.addCategory(GroupHelper.AREA_ADD);
        adapter.addCategory(GroupHelper.AREA_DELETE);
        adapter.addCategory(GroupHelper.AREA_UPDATE);
        adapter.addCategory(GroupHelper.AREA_QUERY);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

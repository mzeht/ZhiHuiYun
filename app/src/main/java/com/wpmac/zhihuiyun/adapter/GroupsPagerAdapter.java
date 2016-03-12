package com.wpmac.zhihuiyun.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.wpmac.zhihuiyun.fragment.AreaAddfragment;
import com.wpmac.zhihuiyun.fragment.AreaDeleteFragment;
import com.wpmac.zhihuiyun.fragment.AreaqueryFragmnet;
import com.wpmac.zhihuiyun.fragment.AreaupdataFragment;
import com.wpmac.zhihuiyun.fragment.DeviceAddFragment;
import com.wpmac.zhihuiyun.fragment.DeviceDeleteFragment;
import com.wpmac.zhihuiyun.fragment.DeviceNetWorkFragment;
import com.wpmac.zhihuiyun.fragment.DeviceQueryFragment;
import com.wpmac.zhihuiyun.fragment.DeviceUpdateFragment;
import com.wpmac.zhihuiyun.fragment.MeasAddFragment;
import com.wpmac.zhihuiyun.fragment.MeasDeleteFragment;
import com.wpmac.zhihuiyun.fragment.MeasQueryFragment;
import com.wpmac.zhihuiyun.fragment.MeasUpdateFragment;
import com.wpmac.zhihuiyun.fragment.MeterAddFragment;
import com.wpmac.zhihuiyun.fragment.MeterDeleteFragment;
import com.wpmac.zhihuiyun.fragment.MeterQueryFragment;
import com.wpmac.zhihuiyun.fragment.MeterUpdateFragment;
import com.wpmac.zhihuiyun.fragment.PointCheckFragment;
import com.wpmac.zhihuiyun.fragment.PointDeleteFragment;
import com.wpmac.zhihuiyun.fragment.PointQueryFragment;
import com.wpmac.zhihuiyun.fragment.PointUpdateFragment;
import com.wpmac.zhihuiyun.fragment.PointUploadFragment;
import com.wpmac.zhihuiyun.helper.GroupHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by katsuyagoto on 15/06/19.
 */
public class GroupsPagerAdapter extends FragmentPagerAdapter {

    private final List<String> mCategoryNames = new ArrayList<>();

    public GroupsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addCategory(String categoryName) {
        mCategoryNames.add(categoryName);
    }

    @Override
    public Fragment getItem(int position) {
        String categoryName = mCategoryNames.get(position);
        switch (categoryName) {
            case GroupHelper.AREA_ADD:
                return AreaAddfragment.getInstance();
            case GroupHelper.AREA_DELETE:
                return AreaDeleteFragment.getInstance();
            case GroupHelper.AREA_QUERY:
                return AreaqueryFragmnet.getInstance();
            case GroupHelper.AREA_UPDATE:
                return AreaupdataFragment.getInstance();
            case GroupHelper.DEVICE_ADD:
                return DeviceAddFragment.getInstance();
            case GroupHelper.DEVICE_DELETE:
                return DeviceDeleteFragment.getInstance();
            case GroupHelper.DEVICE_QUERY:
                return DeviceQueryFragment.getInstance();
            case GroupHelper.DEVICE_UPDATE:
                return DeviceUpdateFragment.getInstance();
            case GroupHelper.NETWORK_CHECK:
                return DeviceNetWorkFragment.getInstance();
            case GroupHelper.METER_ADD:
                return MeterAddFragment.getInstance();
            case GroupHelper.METER_DELETE:
                return MeterDeleteFragment.getInstance();
            case GroupHelper.METER_QUERY:
                return MeterQueryFragment.getInstance();
            case GroupHelper.METER_UPDATE:
                return MeterUpdateFragment.getInstance();
            case GroupHelper.MEAS_ADD:
                return MeasAddFragment.getInstance();
            case GroupHelper.MEAS_CHECK:
                return MeasQueryFragment.getInstance();
            case GroupHelper.MEAS_DELETE:
                return MeasDeleteFragment.getInstance();
            case GroupHelper.MEAS_UPDATE:
                return MeasUpdateFragment.getInstance();
            case GroupHelper.POINT_CHECK:
                return PointCheckFragment.getInstance();
            case GroupHelper.POINT_QUERY:
                return PointQueryFragment.getInstance();
            case GroupHelper.POINT_UPDATE:
                return PointUpdateFragment.getInstance();
            case GroupHelper.POINT_UPLOAD:
                return PointUploadFragment.getInstance();
            case GroupHelper.POINT_DELETE:
                return PointDeleteFragment.getInstance();

            default:
        }
        return new Fragment();
    }

    @Override
    public int getCount() {
        return mCategoryNames.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mCategoryNames.get(position);
    }
}

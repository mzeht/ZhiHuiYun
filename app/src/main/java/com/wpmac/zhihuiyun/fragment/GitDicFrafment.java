package com.wpmac.zhihuiyun.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.wpmac.zhihuiyun.R;
import com.wpmac.zhihuiyun.Utils.ActivityForResultUtil;
import com.wpmac.zhihuiyun.adapter.DevListviewAdapter;
import com.wpmac.zhihuiyun.adapter.MeasListviewAdapter;
import com.wpmac.zhihuiyun.adapter.MeterListviewAdapter;
import com.wpmac.zhihuiyun.comment.AbstractRequestListener;
import com.wpmac.zhihuiyun.comment.CustomError;
import com.wpmac.zhihuiyun.debug.L;
import com.wpmac.zhihuiyun.manager.ConnectManager;
import com.wpmac.zhihuiyun.model.GetDicBean;
import com.wpmac.zhihuiyun.model.GetDicParam;
import com.wpmac.zhihuiyun.model.devicTypeBean;
import com.wpmac.zhihuiyun.model.dicDataBean;
import com.wpmac.zhihuiyun.model.measTypeBean;
import com.wpmac.zhihuiyun.model.meterTypeBean;
import com.wpmac.zhihuiyun.view.DialogWidget;
import com.wpmac.zhihuiyun.view.ToastWidget;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wpmac on 16/1/25.
 */
public class GitDicFrafment extends Fragment {

    private SearchView mSearchView;
    private EditText editText;
    private Button serachButton;
    private ListView devlistView, meterlistview, measlistview;
    private GetDicBean getDicBean;
    private Dialog dialog;
    private measTypeBean measTypeBean;
    private meterTypeBean meterTypeBean;
    private devicTypeBean devicTypeBean;
    private dicDataBean dataBean;
    private List<devicTypeBean> devicList = new ArrayList<devicTypeBean>();
    private List<meterTypeBean> meterList = new ArrayList<meterTypeBean>();
    private List<measTypeBean> measList = new ArrayList<measTypeBean>();
    private DevListviewAdapter devListviewAdapter;
    private MeterListviewAdapter meterListviewAdapter;
    private MeasListviewAdapter measListviewAdapter;



    private static GitDicFrafment gitDicFrafment;
    public static GitDicFrafment getInstance(){
        if(gitDicFrafment == null){
            gitDicFrafment = new GitDicFrafment();
        }
        return gitDicFrafment;
    }
    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            L.i("GitDicFrafment  onCreateView");
            mView = inflater.inflate(R.layout.fragment_git_dic, container,
                    false);

        } else {
            L.i("GitDicFrafment   no  onCreateView");
        }

        findview();
        setEvent();
        return mView;
    }

    private void setEvent() {
        serachButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog == null) {
                    dialog = DialogWidget.createDialog(getActivity());
                    dialog.setCanceledOnTouchOutside(false);
                }
                dialog.show();
                String text = editText.getText().toString().trim();
                GetDicParam param = new GetDicParam();
                param.companyCode = text;
                ConnectManager.getInstance().getDic(param, callback);
            }
        });
    }

    private final AbstractRequestListener<GetDicBean> callback = new AbstractRequestListener<GetDicBean>() {

        @Override
        public void onComplete(GetDicBean bean) {
            getDicBean = bean;
            mHandler.sendEmptyMessage(ActivityForResultUtil.REQUEST_DATA_SUCCESS);
        }

        @Override
        public void onError(CustomError customError) {

            Message msg = mHandler.obtainMessage();
            msg.obj = customError;
            msg.what = ActivityForResultUtil.REQUEST_DATA_ERROR;
            mHandler.sendMessage(msg);

        }

        @Override
        public void onFault(CustomError fault) {

            Message msg = mHandler.obtainMessage();
            msg.obj = fault;
            msg.what = ActivityForResultUtil.REQUEST_DATA_FAULT;
            mHandler.sendMessage(msg);
        }

    };


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ActivityForResultUtil.REQUEST_DATA_SUCCESS:
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
//                    ToastWidget.createDialog(getApplicationContext(),getDicBean.getMessage());
                    if (getDicBean.getSuccess().toString().equals("true")) {
                        ToastWidget.createDialog(getActivity(), "数据请求成功");
                        dataBean = getDicBean.getData();
                        devicList = dataBean.getDevicType();
                        measList = dataBean.getMeasType();
                        meterList = dataBean.getMeterType();
                    } else {
                        ToastWidget.createDialog(getActivity(), getDicBean.getMessage());
                        devicList.clear();
                        measList.clear();
                        meterList.clear();
                    }



                    devListviewAdapter = new DevListviewAdapter(getActivity(), devicList);
                    devlistView.setAdapter(devListviewAdapter);
                    meterListviewAdapter = new MeterListviewAdapter(getActivity(), meterList);
                    meterlistview.setAdapter(meterListviewAdapter);
                    measListviewAdapter = new MeasListviewAdapter(getActivity(), measList);
                    measlistview.setAdapter(measListviewAdapter);
                    devListviewAdapter.setDatasource(getActivity(), devicList);
                    devListviewAdapter.notifyDataSetChanged();
                    measListviewAdapter.setDatasource(getActivity(), measList);
                    measListviewAdapter.notifyDataSetChanged();
                    meterListviewAdapter.setDatasource(getActivity(), meterList);
                    meterListviewAdapter.notifyDataSetChanged();
                    break;

                case ActivityForResultUtil.REQUEST_DATA_ERROR:
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }

                    if (msg.obj instanceof CustomError) {

                        CustomError fault = (CustomError) msg.obj;
                        ToastWidget.createDialog(getActivity(), fault.getMessage());
                    }

                    break;
                case ActivityForResultUtil.REQUEST_DATA_FAULT:
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }

                    if (msg.obj instanceof CustomError) {

                        CustomError fault = (CustomError) msg.obj;
                        ToastWidget.createDialog(getActivity(), fault.getMessage());
                    }

                    break;
            }
        }
    };

    private void findview() {
        editText = (EditText) mView.findViewById(R.id.search_edittext);
        serachButton = (Button) mView.findViewById(R.id.search_button);

        devlistView = (ListView) mView.findViewById(R.id.devicType_listview);


        meterlistview = (ListView) mView.findViewById(R.id.meterType_listview);


        measlistview = (ListView) mView.findViewById(R.id.measType_listview);

    }
}

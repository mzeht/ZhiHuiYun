package com.wpmac.zhihuiyun.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.wpmac.zhihuiyun.R;
import com.wpmac.zhihuiyun.Utils.ActivityForResultUtil;
import com.wpmac.zhihuiyun.Utils.Utils;
import com.wpmac.zhihuiyun.comment.AbstractRequestListener;
import com.wpmac.zhihuiyun.comment.CustomError;
import com.wpmac.zhihuiyun.debug.L;
import com.wpmac.zhihuiyun.manager.ConnectManager;
import com.wpmac.zhihuiyun.model.Meter;
import com.wpmac.zhihuiyun.model.MeterAddParam;
import com.wpmac.zhihuiyun.model.PostMeterAddBean;
import com.wpmac.zhihuiyun.model.SimpleResponseBean;
import com.wpmac.zhihuiyun.view.ToastWidget;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wpmac on 16/1/26.
 */
public class MeterAddFragment extends Fragment {

    @Bind(R.id.company_code_edittext)
    EditText companyCodeEdittext;
    @Bind(R.id.meter_name_edittext)
    EditText meterNameEdittext;
    @Bind(R.id.area_code_edittext)
    EditText areaCodeEdittext;
    @Bind(R.id.locationLat_edittext)
    EditText locationLatEdittext;
    @Bind(R.id.locationLon_edittext)
    EditText locationLonEdittext;
    @Bind(R.id.uploadDevice_edittext)
    EditText uploadDeviceEdittext;
    @Bind(R.id.type_edittext)
    EditText typeEdittext;
    @Bind(R.id.submit_button)
    Button submitButton;
    private View mView;
    private static MeterAddFragment meterAddFragment;
    private Dialog dialog;
    private SimpleResponseBean simpleResponseBean;

    public MeterAddFragment() {
    }

    public static MeterAddFragment getInstance() {
        if (meterAddFragment == null) {
            meterAddFragment = new MeterAddFragment();
        }
        return meterAddFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            L.i("MeterAddFragment  onCreateView");
            mView = inflater.inflate(R.layout.fragment_meter_add, container,
                    false);

        } else {
            L.i("MeterAddFragment   no  onCreateView");
        }
        ButterKnife.bind(this, mView);
        setListener();
        return mView;
    }

    private void setListener() {
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostMeterAdd();
            }
        });


    }

    private void PostMeterAdd() {

        MeterAddParam param = new MeterAddParam();
        PostMeterAddBean postMeterAddBean = new PostMeterAddBean();
        postMeterAddBean.companyCode=companyCodeEdittext.getText().toString().trim();
        postMeterAddBean.meter = new Meter();
        postMeterAddBean.meter.code=companyCodeEdittext.getText().toString().trim();
        postMeterAddBean.meter.name=meterNameEdittext.getText().toString().trim();
        postMeterAddBean.meter.area=areaCodeEdittext.getText().toString().trim();
        postMeterAddBean.meter.locationLat=locationLatEdittext.getText().toString().trim();
        postMeterAddBean.meter.locationLon=locationLonEdittext.getText().toString().trim();
        postMeterAddBean.meter.uploadDevice=uploadDeviceEdittext.getText().toString().trim();
        postMeterAddBean.meter.type=typeEdittext.getText().toString().trim();
        String json= new Gson().toJson(postMeterAddBean);
        ConnectManager.getInstance().PostMeterAdd(param,json,callback);

    }

    private AbstractRequestListener<SimpleResponseBean> callback = new AbstractRequestListener<SimpleResponseBean>() {
        @Override
        public void onComplete(SimpleResponseBean bean) {
            simpleResponseBean = bean;
            tHandler.sendEmptyMessage(ActivityForResultUtil.REQUEST_DATA_SUCCESS);
        }

        @Override
        public void onError(CustomError customError) {

            Message msg = tHandler.obtainMessage();
            msg.obj = customError;
            msg.what = ActivityForResultUtil.REQUEST_DATA_ERROR;
            tHandler.sendMessage(msg);

        }

        @Override
        public void onFault(CustomError fault) {

            Message msg = tHandler.obtainMessage();
            msg.obj = fault;
            msg.what = ActivityForResultUtil.REQUEST_DATA_FAULT;
            tHandler.sendMessage(msg);
        }

    };

    private Handler tHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ActivityForResultUtil.REQUEST_DATA_SUCCESS:
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                    if (Utils.isValidValue(simpleResponseBean.getSuccess())) {
                        ToastWidget.createDialog(getActivity(), simpleResponseBean.getMessage());
                    }
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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

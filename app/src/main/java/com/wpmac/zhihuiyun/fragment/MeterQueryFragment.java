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

import com.wpmac.zhihuiyun.R;
import com.wpmac.zhihuiyun.Utils.ActivityForResultUtil;
import com.wpmac.zhihuiyun.Utils.Utils;
import com.wpmac.zhihuiyun.comment.AbstractRequestListener;
import com.wpmac.zhihuiyun.comment.CustomError;
import com.wpmac.zhihuiyun.debug.L;
import com.wpmac.zhihuiyun.manager.ConnectManager;
import com.wpmac.zhihuiyun.model.MeterQueryBean;
import com.wpmac.zhihuiyun.model.MeterQueryParam;
import com.wpmac.zhihuiyun.view.ToastWidget;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wpmac on 16/1/26.
 */
public class MeterQueryFragment extends Fragment {

    @Bind(R.id.company_code_edittext)
    EditText companyCodeEdittext;
    @Bind(R.id.meter_code_edittext)
    EditText meterCodeEdittext;
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
    private static MeterQueryFragment meterQueryFragment;
    private Dialog dialog;
    private MeterQueryBean meterQueryBean;

    public MeterQueryFragment() {
    }

    public static MeterQueryFragment getInstance() {
        if (meterQueryFragment == null) {
            meterQueryFragment = new MeterQueryFragment();
        }
        return meterQueryFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            L.i("MeterQueryFragment  onCreateView");
            mView = inflater.inflate(R.layout.fragment_meter_query, container,
                    false);

        } else {
            L.i("MeterQueryFragment   no  onCreateView");
        }
        ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetMeterQuery();
            }
        });
    }

    private void GetMeterQuery() {
        MeterQueryParam param = new MeterQueryParam();
        param.companyCode=companyCodeEdittext.getText().toString().trim();
        param.meterCode=meterCodeEdittext.getText().toString().trim();
        ConnectManager.getInstance().GetMeterQuery(param,callback);

    }

    private AbstractRequestListener<MeterQueryBean> callback = new AbstractRequestListener<MeterQueryBean>() {
        @Override
        public void onComplete(MeterQueryBean bean) {
            meterQueryBean = bean;
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
                    if (Utils.isValidValue(meterQueryBean.getSuccess())) {
                        ToastWidget.createDialog(getActivity(), meterQueryBean.getMessage());
                    }
                    if(meterQueryBean.getSuccess().equals("true")){
                        meterNameEdittext.setText(meterQueryBean.meter.name);
                        areaCodeEdittext.setText(meterQueryBean.meter.area);
                        locationLatEdittext.setText(meterQueryBean.meter.locationLat);
                        locationLonEdittext.setText(meterQueryBean.meter.locationLon);
                        uploadDeviceEdittext.setText(meterQueryBean.meter.uploadDevice);
                        typeEdittext.setText(meterQueryBean.meter.type);

                        meterNameEdittext.setEnabled(false);
                        areaCodeEdittext.setEnabled(false);
                        locationLatEdittext.setEnabled(false);
                        locationLonEdittext.setEnabled(false);
                        uploadDeviceEdittext.setEnabled(false);
                        typeEdittext.setEnabled(false);
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
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

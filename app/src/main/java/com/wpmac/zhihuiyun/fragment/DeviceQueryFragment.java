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
import android.widget.LinearLayout;

import com.wpmac.zhihuiyun.R;
import com.wpmac.zhihuiyun.Utils.ActivityForResultUtil;
import com.wpmac.zhihuiyun.Utils.Utils;
import com.wpmac.zhihuiyun.comment.AbstractRequestListener;
import com.wpmac.zhihuiyun.comment.CustomError;
import com.wpmac.zhihuiyun.debug.L;
import com.wpmac.zhihuiyun.manager.ConnectManager;
import com.wpmac.zhihuiyun.model.Device;
import com.wpmac.zhihuiyun.model.DeviceQueryBean;
import com.wpmac.zhihuiyun.model.DeviceQueryParam;
import com.wpmac.zhihuiyun.view.DialogWidget;
import com.wpmac.zhihuiyun.view.ToastWidget;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wpmac on 16/1/26.
 */
public class DeviceQueryFragment extends Fragment {

    @Bind(R.id.company_code_edittext)
    EditText companyCodeEdittext;
    @Bind(R.id.device_code_edittext)
    EditText deviceCodeEdittext;
    @Bind(R.id.device_name_edittext)
    EditText deviceNameEdittext;
    @Bind(R.id.area_code_edittext)
    EditText areaCodeEdittext;
    @Bind(R.id.locationLat_edittext)
    EditText locationLatEdittext;
    @Bind(R.id.locationLon_edittext)
    EditText locationLonEdittext;
    @Bind(R.id.allowControl_edittext)
    EditText allowControlEdittext;
    @Bind(R.id.device_type_edittext)
    EditText deviceTypeEdittext;
    @Bind(R.id.device_query_result_ll)
    LinearLayout deviceQueryResultLl;
    @Bind(R.id.submit_button)
    Button submitButton;
    private View mView;
    private static DeviceQueryFragment deviceQueryFragment;
    private DeviceQueryBean deviceQueryBean;
    private Device device;
    private Dialog dialog;

    public DeviceQueryFragment() {
    }

    public static DeviceQueryFragment getInstance() {
        if (deviceQueryFragment == null) {
            deviceQueryFragment = new DeviceQueryFragment();
        }
        return deviceQueryFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            L.i("DeviceQueryFragment  onCreateView");
            mView = inflater.inflate(R.layout.fragment_device_query, container,
                    false);

        } else {
            L.i("DeviceQueryFragment   no  onCreateView");
        }
        ButterKnife.bind(this, mView);
        setlistener();
        return mView;
    }

    private void setlistener() {

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetDeviceQuery();
            }
        });

    }

    private void GetDeviceQuery() {

        if (dialog == null) {
            dialog = new DialogWidget().createDialog(getActivity());
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }

        DeviceQueryParam param = new DeviceQueryParam();
        param.companyCode = companyCodeEdittext.getText().toString().trim();
        param.deviceCode = deviceCodeEdittext.getText().toString().trim();
        ConnectManager.getInstance().DeviceQuery(param, callback);
    }


    private AbstractRequestListener<DeviceQueryBean> callback = new AbstractRequestListener<DeviceQueryBean>() {
        @Override
        public void onComplete(DeviceQueryBean bean) {
            deviceQueryBean = bean;
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
                    if (Utils.isValidValue(deviceQueryBean.getSuccess())) {

                        if (deviceQueryBean.getSuccess().equals("true")) {
                            deviceQueryResultLl.setVisibility(View.VISIBLE);
                            device =deviceQueryBean.device;
                            deviceNameEdittext.setText(device.name);
                            deviceNameEdittext.setEnabled(false);
                            areaCodeEdittext.setText(device.code);
                            areaCodeEdittext.setEnabled(false);
                            locationLatEdittext.setText(device.locationLat);
                            locationLatEdittext.setEnabled(false);
                            locationLonEdittext.setText(device.locationLon);
                            locationLonEdittext.setEnabled(false);
                            if(device.allowControl.equals("true")){
                                allowControlEdittext.setText("是");
                            }else{
                                allowControlEdittext.setText("否");
                            }
                            allowControlEdittext.setEnabled(false);
                            deviceTypeEdittext.setText(device.type);
                            deviceTypeEdittext.setEnabled(false);

                        } else {
                            ToastWidget.createDialog(getActivity(), deviceQueryBean.getMessage());

                        }
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

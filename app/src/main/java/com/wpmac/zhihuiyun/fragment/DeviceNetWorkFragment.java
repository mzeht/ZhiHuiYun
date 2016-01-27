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
import com.wpmac.zhihuiyun.model.NetStatusBean;
import com.wpmac.zhihuiyun.model.NetStatusParam;
import com.wpmac.zhihuiyun.view.ToastWidget;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wpmac on 16/1/26.
 */
public class DeviceNetWorkFragment extends Fragment {
    @Bind(R.id.company_code_edittext)
    EditText companyCodeEdittext;
    @Bind(R.id.device_code_edittext)
    EditText deviceCodeEdittext;
    @Bind(R.id.device_status_edittext)
    EditText deviceStatusEdittext;
    @Bind(R.id.device_query_result_ll)
    LinearLayout deviceQueryResultLl;
    @Bind(R.id.submit_button)
    Button submitButton;
    private View mView;
    private static DeviceNetWorkFragment netWorkFragment;
    private Dialog dialog;
    private NetStatusBean netStatusBean;
    private Device device;

    public DeviceNetWorkFragment() {
    }

    public static DeviceNetWorkFragment getInstance() {
        if (netWorkFragment == null) {
            netWorkFragment = new DeviceNetWorkFragment();
        }
        return netWorkFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            L.i("DeviceNetWorkFragment  onCreateView");
            mView = inflater.inflate(R.layout.fragment_network_check, container,
                    false);

        } else {
            L.i("DeviceNetWorkFragment   no  onCreateView");
        }

        ButterKnife.bind(this, mView);
        setListener();
        return mView;
    }

    private void setListener() {
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetDeviceNetworkStatus();

            }
        });


    }

    private void GetDeviceNetworkStatus() {
        NetStatusParam param = new NetStatusParam();
        param.companyCode=companyCodeEdittext.getText().toString().trim();
        param.deviceCode=deviceCodeEdittext.getText().toString().trim();
        ConnectManager.getInstance().GetDeviceNetworkStatus(param,callback);
    }
    private AbstractRequestListener<NetStatusBean> callback = new AbstractRequestListener<NetStatusBean>() {
        @Override
        public void onComplete(NetStatusBean bean) {
            netStatusBean = bean;
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
                    if (Utils.isValidValue(netStatusBean.getSuccess())) {

                        if (netStatusBean.getSuccess().equals("true")) {
                            deviceQueryResultLl.setVisibility(View.VISIBLE);

                            if(Utils.isValidValue(device.status)){
                                deviceStatusEdittext.setText(device.status);
                            }
                            deviceStatusEdittext.setEnabled(false);


                        } else {
                            ToastWidget.createDialog(getActivity(), netStatusBean.getMessage());

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
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wpmac.zhihuiyun.R;
import com.wpmac.zhihuiyun.Utils.ActivityForResultUtil;
import com.wpmac.zhihuiyun.comment.AbstractRequestListener;
import com.wpmac.zhihuiyun.comment.CustomError;
import com.wpmac.zhihuiyun.debug.L;
import com.wpmac.zhihuiyun.manager.ConnectManager;
import com.wpmac.zhihuiyun.model.Device;
import com.wpmac.zhihuiyun.model.DeviceAddParam;
import com.wpmac.zhihuiyun.model.PostDeviceAddBean;
import com.wpmac.zhihuiyun.model.SimpleResponseBean;
import com.wpmac.zhihuiyun.view.DialogWidget;
import com.wpmac.zhihuiyun.view.ToastWidget;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wpmac on 16/1/26.
 */
public class DeviceAddFragment extends Fragment {
    @Bind(R.id.form_code_edittext)
    EditText companyCodeEdittext;
    @Bind(R.id.form_2_edittext)
    EditText deviceCodeEdittext;
    @Bind(R.id.form_3_edittext)
    EditText deviceNameEdittext;
    @Bind(R.id.form_4_edittext)
    EditText longitudeEdittext;
    @Bind(R.id.form_5_edittext)
    EditText latitudeEdittext;
    @Bind(R.id.allowControl_checkbox)
    CheckBox isAllowControlCheckbox;
    @Bind(R.id.form_7_edittext)
    EditText deviceTypeEdittext;
    @Bind(R.id.submit_button)
    Button submitButton;
    @Bind(R.id.form_8_edittext)
    EditText areaCodeEditText;
    private View mView;
    private static DeviceAddFragment deviceAddFragment;
    private SimpleResponseBean simpleResponseBean;
    private Dialog dialog;

    public DeviceAddFragment() {
    }

    public static DeviceAddFragment getInstance() {
        if (deviceAddFragment == null) {
            deviceAddFragment = new DeviceAddFragment();
        }
        return deviceAddFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            L.i("DeviceAddFragment  onCreateView");
            mView = inflater.inflate(R.layout.fragment_device_add, container,
                    false);
        } else {
            L.i("DeviceAddFragment   no  onCreateView");
        }
        ButterKnife.bind(this, mView);
        setlistener();
        return mView;
    }

    private void setlistener() {
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=companyCodeEdittext.getText().toString().trim();
                String error="";
                if(s1.length()==0){
                    error=error+"\r\n"+"公司编码不能为空";
                }

                if(error.length()==0){
                    postDeviceAdd();
                }else{
                    Toast.makeText(getActivity(),error,Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void postDeviceAdd() {
        if(dialog==null){
            dialog=new DialogWidget().createDialog(getActivity());
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }
        DeviceAddParam param = new DeviceAddParam();
        PostDeviceAddBean deviceAddBean= new PostDeviceAddBean();
        deviceAddBean.companyCode=companyCodeEdittext.getText().toString().trim();
        deviceAddBean.device=new Device();
        deviceAddBean.device.code=deviceCodeEdittext.getText().toString().trim();
        deviceAddBean.device.name=deviceNameEdittext.getText().toString().trim();
        deviceAddBean.device.area=areaCodeEditText.getText().toString().trim();
        deviceAddBean.device.locationLat=longitudeEdittext.getText().toString().trim();
        deviceAddBean.device.locationLon=latitudeEdittext.getText().toString().trim();
        if(isAllowControlCheckbox.isChecked()){
            deviceAddBean.device.allowControl="true";
        }else{
            deviceAddBean.device.allowControl="false";
        }
        deviceAddBean.device.type=deviceTypeEdittext.getText().toString().trim();
        String json= new Gson().toJson(deviceAddBean);
        ConnectManager.getInstance().PostDeviceAdd(param,json,callback);


    }

    private AbstractRequestListener<SimpleResponseBean> callback = new AbstractRequestListener<SimpleResponseBean>() {
        @Override
        public void onComplete(SimpleResponseBean bean) {
            simpleResponseBean=bean;
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
            msg.what=ActivityForResultUtil.REQUEST_DATA_FAULT;
            mHandler.sendMessage(msg);

        }
    };

    Handler mHandler= new Handler(){
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){


                case ActivityForResultUtil.REQUEST_DATA_SUCCESS:
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                    Toast.makeText(getActivity(),simpleResponseBean.getMessage(),Toast.LENGTH_SHORT).show();
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

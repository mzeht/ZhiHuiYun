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

import com.google.gson.Gson;
import com.wpmac.zhihuiyun.R;
import com.wpmac.zhihuiyun.Utils.ActivityForResultUtil;
import com.wpmac.zhihuiyun.comment.AbstractRequestListener;
import com.wpmac.zhihuiyun.comment.CustomError;
import com.wpmac.zhihuiyun.debug.L;
import com.wpmac.zhihuiyun.manager.ConnectManager;
import com.wpmac.zhihuiyun.model.Device;
import com.wpmac.zhihuiyun.model.DeviceUpdateParam;
import com.wpmac.zhihuiyun.model.PostDeviceAddBean;
import com.wpmac.zhihuiyun.model.SimpleResponseBean;
import com.wpmac.zhihuiyun.view.DialogWidget;
import com.wpmac.zhihuiyun.view.ToastWidget;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wpmac on 16/1/26.
 */
public class DeviceUpdateFragment extends Fragment {
    @Bind(R.id.company_code_edittext)
    EditText companyCodeEdittext;
    @Bind(R.id.form_2_edittext)
    EditText form2Edittext;
    @Bind(R.id.device_name_edittext)
    EditText deviceNameEdittext;
    @Bind(R.id.area_code_edittext)
    EditText areaCodeEdittext;
    @Bind(R.id.locationLat_edittext)
    EditText locationLatEdittext;
    @Bind(R.id.locationLon_edittext)
    EditText locationLonEdittext;
    @Bind(R.id.allowControl_checkbox)
    CheckBox allowControlCheckbox;
    @Bind(R.id.form_7_edittext)
    EditText deviceTypeEdittext;
    @Bind(R.id.submit_button)
    Button submitButton;
    private View mView;

    private Dialog dialog;
    private SimpleResponseBean simpleResponseBean ;
    private PostDeviceAddBean postDeviceAddBean;
    public DeviceUpdateFragment() {
    }

    public static DeviceUpdateFragment getInstance() {
        return new DeviceUpdateFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            L.i("DeviceUpdateFragment  onCreateView");
            mView = inflater.inflate(R.layout.fragment_device_update, container,
                    false);

        } else {
            L.i("DeviceUpdateFragment   no  onCreateView");
        }
        ButterKnife.bind(this, mView);
        setlistener();
        return mView;
    }

    private void setlistener() {

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PostDeviceUpdate();
            }
        });
    }

    private void PostDeviceUpdate() {

        if(dialog==null){
            dialog = new DialogWidget().createDialog(getActivity());
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }

        DeviceUpdateParam param = new DeviceUpdateParam();
        //body和Deciveadd相同 直接复用PostDeviceAddBean
        PostDeviceAddBean deviceAddBean= new PostDeviceAddBean();
        deviceAddBean.companyCode=companyCodeEdittext.getText().toString().trim();
        deviceAddBean.device=new Device();
        deviceAddBean.device.code=form2Edittext.getText().toString().trim();
        deviceAddBean.device.name=deviceNameEdittext.getText().toString().trim();
        deviceAddBean.device.area=areaCodeEdittext.getText().toString().trim();
        deviceAddBean.device.locationLat=locationLatEdittext.getText().toString().trim();
        deviceAddBean.device.locationLon=locationLonEdittext.getText().toString().trim();
        if(allowControlCheckbox.isChecked()){

            deviceAddBean.device.allowControl="true";
        }else{
            deviceAddBean.device.allowControl="false";
        }
        deviceAddBean.device.type=deviceTypeEdittext.getText().toString().trim();
        String json=new Gson().toJson(deviceAddBean);
        ConnectManager.getInstance().PostDeviceUpdate(param,json,callback);

    }

    private final AbstractRequestListener<SimpleResponseBean> callback = new AbstractRequestListener<SimpleResponseBean>() {

        @Override
        public void onComplete(SimpleResponseBean bean) {
            simpleResponseBean = bean;
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

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case ActivityForResultUtil.REQUEST_DATA_SUCCESS:
                    if(dialog!=null && dialog.isShowing())
                    {
                        dialog.dismiss();
                    }
                        ToastWidget.createDialog(getActivity(),simpleResponseBean.getMessage());
                    break;

                case ActivityForResultUtil.REQUEST_DATA_ERROR:
                    if(dialog!=null && dialog.isShowing())
                    {
                        dialog.dismiss();
                    }

                    if (msg.obj instanceof CustomError) {

                        CustomError fault = (CustomError) msg.obj;
                        ToastWidget.createDialog(getActivity(), fault.getMessage());
                    }

                    break;
                case ActivityForResultUtil.REQUEST_DATA_FAULT:
                    if(dialog!=null && dialog.isShowing())
                    {
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

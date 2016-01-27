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
import com.wpmac.zhihuiyun.model.Area;
import com.wpmac.zhihuiyun.model.PostAreaAddBean;
import com.wpmac.zhihuiyun.model.PostAreaUpdateParam;
import com.wpmac.zhihuiyun.model.SimpleResponseBean;
import com.wpmac.zhihuiyun.view.DialogWidget;
import com.wpmac.zhihuiyun.view.ToastWidget;

/**
 * Created by wpmac on 16/1/25.
 */
public class AreaupdataFragment extends Fragment {
    private EditText companyCodeEditText,areaCodeEditText,areaNameEditText;
    private Button button;
    private SimpleResponseBean simpleResponseBean;
    private Dialog dialog;



    public AreaupdataFragment() {
    }
    private static AreaupdataFragment areaupdataFragment;
    public static AreaupdataFragment getInstance(){
        if(areaupdataFragment == null){
            areaupdataFragment = new AreaupdataFragment();
        }
        return areaupdataFragment;
    }
    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            L.i("AreaupdataFragment  onCreateView");
            mView = inflater.inflate(R.layout.fragment_area_updata, container,
                    false);

        } else {
            L.i("AreaupdataFragment   no  onCreateView");
        }
        findView();
        setlistener();

        return mView;
    }

    private void findView() {
        companyCodeEditText = (EditText) mView.findViewById(R.id.form_code_edittext);
        areaCodeEditText = (EditText) mView.findViewById(R.id.form_2_edittext);
        areaNameEditText = (EditText) mView.findViewById(R.id.form_3_edittext);
        button = (Button) mView.findViewById(R.id.submit_button);

    }

    private void setlistener() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=companyCodeEditText.getText().toString().trim();
                String s2=areaCodeEditText.getText().toString().trim();
                String s3=areaNameEditText.getText().toString().trim();
                String error="";
                if(s1.length()==0){
                    error=error+"\r\n"+"companycode不能为空";
                }
                if(s3.length()>20||s2.length()<1){
                    error=error+"\r\n"+"区域名称长度应该为1-20";
                }
                if(error.length()>0){
                    ToastWidget.createDialog(getActivity(),error).show();
                }else{
                    postAreaUpdate();
                }
            }
        });
    }

    private void postAreaUpdate() {
        if(dialog==null){
            dialog= DialogWidget.createDialog(getActivity());
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }

        PostAreaUpdateParam param= new PostAreaUpdateParam();
        PostAreaAddBean postAreaAddBean= new PostAreaAddBean();
        postAreaAddBean.companyCode=companyCodeEditText.getText().toString().trim();
        postAreaAddBean.area= new Area();
        postAreaAddBean.area.parentCode=areaCodeEditText.getText().toString().trim();
        postAreaAddBean.area.name=areaNameEditText.getText().toString().trim();
        String json=new Gson().toJson(postAreaAddBean);
        ConnectManager.getInstance().PostAreaUpdate(param,json,callback);
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
}

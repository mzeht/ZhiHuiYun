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
import android.widget.ImageView;
import android.widget.TextView;

import com.wpmac.zhihuiyun.R;
import com.wpmac.zhihuiyun.Utils.ActivityForResultUtil;
import com.wpmac.zhihuiyun.Utils.Utils;
import com.wpmac.zhihuiyun.comment.AbstractRequestListener;
import com.wpmac.zhihuiyun.comment.CustomError;
import com.wpmac.zhihuiyun.configuration.Constants;
import com.wpmac.zhihuiyun.debug.L;
import com.wpmac.zhihuiyun.manager.ConnectManager;
import com.wpmac.zhihuiyun.model.RegistrationBean;
import com.wpmac.zhihuiyun.model.RegistrationParam;
import com.wpmac.zhihuiyun.view.DialogWidget;
import com.wpmac.zhihuiyun.view.ToastWidget;

/**
 * Created by wpmac on 16/1/25.
 */
public class CompanyFragment extends Fragment {

    private EditText companyCodeEditText,companyPasswordEditText,userNameEditText,addressEditText,
            contactsEditText,phoneNumberEditText,emailEditText;
    private Button submitButton;
    private ImageView backImageView;
    private TextView titileTextView;
    private RegistrationBean registrationBean;
    private Dialog dialog;

    private static CompanyFragment companyFragment;
    public static CompanyFragment getInstance(){
        if(companyFragment == null){
            companyFragment = new CompanyFragment();
        }
        return companyFragment;
    }
    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            L.i("CompanyFragment  onCreateView");
            mView = inflater.inflate(R.layout.fragment_company_register, container,
                    false);

        } else {
            L.i("CompanyFragment   no  onCreateView");
        }
        findView();
        setListener();

        return mView;
    }

    private void setListener() {

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=companyCodeEditText.getText().toString().trim();
                String s2=companyPasswordEditText.getText().toString().trim();
                String s3=userNameEditText.getText().toString().trim();
                String s4=addressEditText.getText().toString().trim();
                String s5=contactsEditText.getText().toString().trim();
                String s6=phoneNumberEditText.getText().toString().trim();
                String s7=emailEditText.getText().toString().trim();
                String error="";
                if(s1.length()>20||s1.length()<6){
                    error=error+"\r\n"+"code长度应该为6-20";
                }
                if(s2.length()>10||s2.length()<2){
                    error=error+"\r\n"+"密码长度应该为6-20";
                }
                if(s3.length()<2||s3.length()>10){
                    error=error+"\r\n"+"用户名长度应该为2-10";
                }
                if(s4.length()<1||s4.length()>50){
                    error=error+"\r\n"+"地址长度应该为6-20";
                }
                if(s5.length()<1||s5.length()>50){
                    error=error+"\r\n"+"联系人长度应该为6-20";
                }
                if(s6.length()<1||s6.length()>20) {
                    error = error + "\r\n" + "联系电话长度应该为6-20";
                }
                if(!Utils.isEmail(s7)){
                    error = error + "\r\n" + "邮箱格式不正确";
                }
                if(error.length()>0){
                    ToastWidget.createDialog(getActivity(),error).show();
                }else{
                    postRegistration();
                }
            }
        });
    }

    private void postRegistration() {


        if(dialog==null)
        {
            dialog = DialogWidget.createDialog(getActivity());
            dialog.setCanceledOnTouchOutside(false);
        }
        dialog.show();
        RegistrationParam param = new RegistrationParam();
        param.code=companyCodeEditText.getText().toString().trim();
        param.pwd=companyPasswordEditText.getText().toString().trim();
        param.name=userNameEditText.getText().toString().trim();
        param.address=addressEditText.getText().toString().trim();
        param.linkman=contactsEditText.getText().toString().trim();
        param.linkphone=phoneNumberEditText.getText().toString().trim();
        param.email=emailEditText.getText().toString().trim();
        param.systemToken= Constants.SYSTEMTOKEN;
        ConnectManager.getInstance().submitCompanyRegistration(param,callback);

    }

    private final AbstractRequestListener<RegistrationBean> callback = new AbstractRequestListener<RegistrationBean>() {

        @Override
        public void onComplete(RegistrationBean bean) {
            registrationBean = bean;
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
                    if(registrationBean.getSuccess().toString().equals("true")){
                        L.i("companycode",registrationBean.getCompanyCode());
                        ToastWidget.createDialog(getActivity(),registrationBean.getMessage()+"\r\n"+registrationBean.getCompanyCode());
                    }else{
                        ToastWidget.createDialog(getActivity(),registrationBean.getMessage());
                    }
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



    private void findView() {

        companyCodeEditText= (EditText) mView.findViewById(R.id.form_code_edittext);
        companyPasswordEditText= (EditText) mView.findViewById(R.id.form_2_edittext);
        userNameEditText= (EditText) mView.findViewById(R.id.form_3_edittext);
        addressEditText= (EditText) mView.findViewById(R.id.form_4_edittext);
        contactsEditText= (EditText) mView.findViewById(R.id.form_5_edittext);
        phoneNumberEditText= (EditText) mView.findViewById(R.id.form_6_edittext);
        emailEditText= (EditText) mView.findViewById(R.id.form_email_edittext);
        submitButton= (Button) mView.findViewById(R.id.submit_button);
    }


}

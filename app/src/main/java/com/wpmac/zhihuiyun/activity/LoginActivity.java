package com.wpmac.zhihuiyun.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wpmac.zhihuiyun.R;
import com.wpmac.zhihuiyun.Utils.ActivityForResultUtil;
import com.wpmac.zhihuiyun.Utils.PasswordUtil;
import com.wpmac.zhihuiyun.Utils.Utils;
import com.wpmac.zhihuiyun.comment.AbstractRequestListener;
import com.wpmac.zhihuiyun.comment.CustomError;
import com.wpmac.zhihuiyun.configuration.Constants;
import com.wpmac.zhihuiyun.debug.L;
import com.wpmac.zhihuiyun.manager.ConnectManager;
import com.wpmac.zhihuiyun.model.GetLogidBean;
import com.wpmac.zhihuiyun.model.GetLogidParam;
import com.wpmac.zhihuiyun.model.GetSystemTokenBean;
import com.wpmac.zhihuiyun.model.GetSystemTokenParam;
import com.wpmac.zhihuiyun.view.DialogWidget;
import com.wpmac.zhihuiyun.view.ToastWidget;

import java.io.IOException;


/**
 * Created by wpmac on 16/1/17.
 */

public class LoginActivity extends AppCompatActivity {


    private EditText mUsernameView;
    private EditText mPasswordView;
    private GetLogidBean getLogidBean;
    private GetSystemTokenBean tokenBean;
    private Dialog dialog;
    public static LoginActivity _instance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mUsernameView = (EditText) findViewById(R.id.name);

        mPasswordView = (EditText) findViewById(R.id.password);
        mUsernameView.setText("admin");
        mPasswordView.setText("123456");
        _instance=this;

        Button mSignInButton = (Button) findViewById(R.id.sign_in_button);
        mSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mUsernameView.getText().toString().trim().length()==0){
                    Toast.makeText(LoginActivity.this,"用户名不能为空",Toast.LENGTH_SHORT).show();
                }else if(mPasswordView.getText().toString().trim().length()==0){
                    Toast.makeText(LoginActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                }else{
                    try {
                        attemptLogin();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        String loginId = "a1b2c3d4e5f6g7h8", password = "1234567890";
        System.out.println("loginId：" + loginId + "，password：" + password + "，加密结果：" + PasswordUtil.getEncPwd(loginId, password));
        String encPwd = PasswordUtil.getEncPwd(loginId, password);
        System.out.println("loginId：" + loginId + "，encPwd：" + encPwd + "，解密结果：" + PasswordUtil.getDecPwd(loginId, encPwd));

    }


    private void attemptLogin() throws IOException {
//        String name=mUsernameView.getText().toString();
//        String password=mPasswordView.getText().toString();

        GetLogidParam param = new GetLogidParam();
        param.systemId= mUsernameView.getText().toString().trim();
        // TODO Auto-generated method stub

        if(dialog==null)
        {
            dialog = DialogWidget.createDialog(LoginActivity.this);
            dialog.setCanceledOnTouchOutside(false);
        }
        dialog.show();

//        param.systemId= "";
        ConnectManager.getInstance().getLogidData(param,callback);
    }

    private final AbstractRequestListener<GetLogidBean> callback = new AbstractRequestListener<GetLogidBean>() {

        @Override
        public void onComplete(GetLogidBean bean) {
            getLogidBean = bean;
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
                   if(!Utils.isValidValue(getLogidBean.getLoginId())){
                       ToastWidget.createDialog(LoginActivity.this,getLogidBean.getMessage());
                   }else{
                        Constants.LOGINID= getLogidBean.getLoginId().toString();
                       ToastWidget.createDialog(LoginActivity.this,"获取loginid成功");
                       getSystemToken();
                   }
                   break;

               case ActivityForResultUtil.REQUEST_DATA_ERROR:
                   if(dialog!=null && dialog.isShowing())
                   {
                       dialog.dismiss();
                   }

                   if (msg.obj instanceof CustomError) {

                       CustomError fault = (CustomError) msg.obj;
                       ToastWidget.createDialog(LoginActivity.this, fault.getMessage());
                   }

                   break;
               case ActivityForResultUtil.REQUEST_DATA_FAULT:
                   if(dialog!=null && dialog.isShowing())
                   {
                       dialog.dismiss();
                   }

                   if (msg.obj instanceof CustomError) {

                       CustomError fault = (CustomError) msg.obj;
                       ToastWidget.createDialog(LoginActivity.this, fault.getMessage());
                   }

                   break;
           }
        }
    };

    private void getSystemToken() {
        GetSystemTokenParam param = new GetSystemTokenParam();
        param.loginId=Constants.LOGINID;
        String password=mPasswordView.getText().toString();
        String encpwd=PasswordUtil.getEncPwd(Constants.LOGINID, password);
        param.password= encpwd;
//        param.password= password;
        L.i("systemID",mUsernameView.getText().toString());
        L.i("明文密码",mPasswordView.getText().toString());
        L.i("loginID",Constants.LOGINID);
        L.i("password 加密结果为",encpwd);
        L.i("password 解密结果为",PasswordUtil.getDecPwd(Constants.LOGINID,encpwd));
        ConnectManager.getInstance().getSystemToken(param,Tokencallback);
    }


    private final AbstractRequestListener<GetSystemTokenBean> Tokencallback = new AbstractRequestListener<GetSystemTokenBean>() {

        @Override
        public void onComplete(GetSystemTokenBean bean) {
            tokenBean = bean;
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

    private Handler tHandler= new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case ActivityForResultUtil.REQUEST_DATA_SUCCESS:
                    if(dialog!=null && dialog.isShowing())
                    {
                        dialog.dismiss();
                    }
                    if(!Utils.isValidValue(tokenBean.getSystemToken())){
                        ToastWidget.createDialog(LoginActivity.this,tokenBean.getMessage());

                    }else{
                        Constants.SYSTEMTOKEN=tokenBean.getSystemToken().toString();
                        L.i("systemToken",Constants.SYSTEMTOKEN);
                        ToastWidget.createDialog(LoginActivity.this,"获取systemToken成功");
                        toTurnActivity();
                    }
                    break;

                case ActivityForResultUtil.REQUEST_DATA_ERROR:
                    if(dialog!=null && dialog.isShowing())
                    {
                        dialog.dismiss();
                    }
                    ToastWidget.createDialog(LoginActivity.this,tokenBean.getMessage());

                    if (msg.obj instanceof CustomError) {

                        CustomError fault = (CustomError) msg.obj;
                        ToastWidget.createDialog(LoginActivity.this, fault.getMessage());
                    }

                    break;
                case ActivityForResultUtil.REQUEST_DATA_FAULT:
                    if(dialog!=null && dialog.isShowing())
                    {
                        dialog.dismiss();
                    }
                    ToastWidget.createDialog(LoginActivity.this,tokenBean.getMessage());

                    if (msg.obj instanceof CustomError) {

                        CustomError fault = (CustomError) msg.obj;
                        ToastWidget.createDialog(LoginActivity.this, fault.getMessage());
                    }

                    break;
            }

        }
    };

    private void toTurnActivity() {
        Intent intent = new Intent();
        intent.setClass(LoginActivity.this,MainActivity.class);
        intent.putExtra("name",mUsernameView.getText().toString());
        startActivity(intent);
    }



}


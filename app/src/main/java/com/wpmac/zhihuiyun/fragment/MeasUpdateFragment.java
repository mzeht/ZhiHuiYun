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
import com.wpmac.zhihuiyun.model.MeasUpdateParam;
import com.wpmac.zhihuiyun.model.PostMeasUpdateBean;
import com.wpmac.zhihuiyun.model.SimpleResponseBean;
import com.wpmac.zhihuiyun.model.UpdateMeas;
import com.wpmac.zhihuiyun.view.ToastWidget;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wpmac on 16/1/26.
 */
public class MeasUpdateFragment extends Fragment {


    @Bind(R.id.company_code_edittext)
    EditText companyCodeEdittext;
    @Bind(R.id.code_edittext)
    EditText codeEdittext;
    @Bind(R.id.name_edittext)
    EditText nameEdittext;
    @Bind(R.id.uploadMeter_edittext)
    EditText uploadMeterEdittext;
    @Bind(R.id.type_edittext)
    EditText typeEdittext;
    @Bind(R.id.submit_button)
    Button submitButton;
    private View mView;
    private static MeasUpdateFragment measUpdateFragment;
    private Dialog dialog;
    private SimpleResponseBean simpleResponseBean;

    public MeasUpdateFragment() {
    }

    public static MeasUpdateFragment getInstance() {
        if (measUpdateFragment == null) {
            measUpdateFragment = new MeasUpdateFragment();
        }
        return measUpdateFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            L.i("MeasUpdateFragment  onCreateView");
            mView = inflater.inflate(R.layout.fragment_meas_update, container,
                    false);

        } else {
            L.i("MeasUpdateFragment   no  onCreateView");
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
                PostMeasUpdate();
            }
        });
    }

    private void PostMeasUpdate() {
        MeasUpdateParam param= new MeasUpdateParam();
        PostMeasUpdateBean postMeasUpdateBean= new PostMeasUpdateBean();
        postMeasUpdateBean.companyCode=companyCodeEdittext.getText().toString().trim();
        postMeasUpdateBean.meas= new UpdateMeas();
        postMeasUpdateBean.meas.code=codeEdittext.getText().toString().trim();
        postMeasUpdateBean.meas.name=nameEdittext.getText().toString().trim();
        postMeasUpdateBean.meas.uploadMeter=uploadMeterEdittext.getText().toString().trim();
        postMeasUpdateBean.meas.type=typeEdittext.getText().toString().trim();
        String json=new Gson().toJson(postMeasUpdateBean);
        ConnectManager.getInstance().PostMeasUpdate(param,json,callback);
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
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

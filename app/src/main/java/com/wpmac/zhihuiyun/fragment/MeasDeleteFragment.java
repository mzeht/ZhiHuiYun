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
import com.wpmac.zhihuiyun.manager.ConnectManager;
import com.wpmac.zhihuiyun.model.DeleteMeas;
import com.wpmac.zhihuiyun.model.MeasDeleteParam;
import com.wpmac.zhihuiyun.model.PostMeasDeleteBean;
import com.wpmac.zhihuiyun.model.SimpleResponseBean;
import com.wpmac.zhihuiyun.view.ToastWidget;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wpmac on 16/1/26.
 */
public class MeasDeleteFragment extends Fragment {

    @Bind(R.id.company_code_edittext)
    EditText companyCodeEdittext;
    @Bind(R.id.meter_code_edittext)
    EditText meterCodeEdittext;
    @Bind(R.id.meas_code_edittext)
    EditText measCodeEdittext;
    @Bind(R.id.submit_button)
    Button submitButton;
    private View mView;
    private static MeasDeleteFragment measDeleteFragment;
    private SimpleResponseBean simpleResponseBean;
    private Dialog dialog;

    public MeasDeleteFragment() {
    }

    public static MeasDeleteFragment getInstance() {
        if (measDeleteFragment == null) {
            measDeleteFragment = new MeasDeleteFragment();
        }
        return measDeleteFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_meas_delete, container, false);
        } else {
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
                PostMeasDelete();

            }
        });
    }

    private void PostMeasDelete() {

        MeasDeleteParam param = new MeasDeleteParam();
        PostMeasDeleteBean postMeasDeleteBean= new PostMeasDeleteBean();
        postMeasDeleteBean.companyCode=companyCodeEdittext.getText().toString().trim();
        postMeasDeleteBean.meas= new DeleteMeas();
        postMeasDeleteBean.meas.measCode=measCodeEdittext.getText().toString().trim();
        postMeasDeleteBean.meas.meterCode=meterCodeEdittext.getText().toString().trim();
        String json = new Gson().toJson(postMeasDeleteBean);
        ConnectManager.getInstance().PostMeasDelete(param,json,callback);

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

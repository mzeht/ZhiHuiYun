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
import android.widget.ListView;
import android.widget.TextView;

import com.wpmac.zhihuiyun.R;
import com.wpmac.zhihuiyun.Utils.ActivityForResultUtil;
import com.wpmac.zhihuiyun.Utils.Utils;
import com.wpmac.zhihuiyun.comment.AbstractRequestListener;
import com.wpmac.zhihuiyun.comment.CustomError;
import com.wpmac.zhihuiyun.debug.L;
import com.wpmac.zhihuiyun.manager.ConnectManager;
import com.wpmac.zhihuiyun.model.AreaQueryBean;
import com.wpmac.zhihuiyun.model.AreaQueryParam;
import com.wpmac.zhihuiyun.model.AreaQueryareas;
import com.wpmac.zhihuiyun.model.CityChildren;
import com.wpmac.zhihuiyun.view.ToastWidget;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wpmac on 16/1/25.
 */
public class AreaqueryFragmnet extends Fragment {

    @Bind(R.id.form_code_edittext)
    EditText companyCodeEdittext;
    @Bind(R.id.form_2_edittext)
    EditText areaCodeEdittext;
    @Bind(R.id.company_name_textview)
    TextView companyNameTextview;
    @Bind(R.id.company_code_textview)
    TextView companyCodeTextview;
    @Bind(R.id.parent_code_textview)
    TextView parentCodeTextview;
    @Bind(R.id.area_query_listview)
    ListView areaQueryListview;
    @Bind(R.id.query_data_ll)
    LinearLayout queryDataLl;
    @Bind(R.id.submit_button)
    Button submitButton;

    private AreaQueryBean areaQueryBean;
    private AreaQueryareas queryareas;
    private List<CityChildren> cityChildren;
    private Dialog dialog;


    public AreaqueryFragmnet() {
    }

    private static AreaqueryFragmnet areaqueryFragmnet;

    public static AreaqueryFragmnet getInstance() {
        if (areaqueryFragmnet == null) {
            areaqueryFragmnet = new AreaqueryFragmnet();
        }
        return areaqueryFragmnet;
    }

    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            L.i("AreaqueryFragmnet  onCreateView");
            mView = inflater.inflate(R.layout.fragment_area_query, container,
                    false);

        } else {
            L.i("AreaqueryFragmnet   no  onCreateView");
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
                String s2=areaCodeEdittext.getText().toString().trim();
                if(s1.length()==0||s2.length()==0){

                }else{
                    areaQuery();
                }

            }
        });
    }

    private void areaQuery() {

        AreaQueryParam param = new AreaQueryParam();
        param.areaCode=areaCodeEdittext.getText().toString().trim();
        param.companyCode=companyCodeEdittext.getText().toString().trim();

        ConnectManager.getInstance().AreaQuery(param,callback);
    }


    private AbstractRequestListener<AreaQueryBean> callback=new AbstractRequestListener<AreaQueryBean>() {
        @Override
        public void onComplete(AreaQueryBean bean) {
            areaQueryBean = bean;
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

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ActivityForResultUtil.REQUEST_DATA_SUCCESS:
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                    if (Utils.isValidValue(areaQueryBean.getSuccess())) {
                        if(areaQueryBean.getSuccess().equals("true")){
                            queryareas=areaQueryBean.getAreas();
                            cityChildren=queryareas.getChildren();
                        }
                        ToastWidget.createDialog(getActivity(), areaQueryBean.getMessage());

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

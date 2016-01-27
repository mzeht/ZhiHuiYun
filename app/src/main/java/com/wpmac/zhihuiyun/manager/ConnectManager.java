package com.wpmac.zhihuiyun.manager;


import com.wpmac.zhihuiyun.Utils.HttpConnectionUtil;
import com.wpmac.zhihuiyun.comment.AbstractRequestListener;
import com.wpmac.zhihuiyun.comment.CustomError;
import com.wpmac.zhihuiyun.configuration.Constants;
import com.wpmac.zhihuiyun.model.AreaDeleteParam;
import com.wpmac.zhihuiyun.model.AreaQueryBean;
import com.wpmac.zhihuiyun.model.AreaQueryParam;
import com.wpmac.zhihuiyun.model.DeviceAddParam;
import com.wpmac.zhihuiyun.model.DeviceDeleteParam;
import com.wpmac.zhihuiyun.model.DeviceQueryBean;
import com.wpmac.zhihuiyun.model.DeviceQueryParam;
import com.wpmac.zhihuiyun.model.DeviceUpdateParam;
import com.wpmac.zhihuiyun.model.GetDicBean;
import com.wpmac.zhihuiyun.model.GetDicParam;
import com.wpmac.zhihuiyun.model.GetLogidBean;
import com.wpmac.zhihuiyun.model.GetLogidParam;
import com.wpmac.zhihuiyun.model.GetMeasQueryBean;
import com.wpmac.zhihuiyun.model.GetSystemTokenBean;
import com.wpmac.zhihuiyun.model.GetSystemTokenParam;
import com.wpmac.zhihuiyun.model.MeasAddParam;
import com.wpmac.zhihuiyun.model.MeasDeleteParam;
import com.wpmac.zhihuiyun.model.MeasQueryParam;
import com.wpmac.zhihuiyun.model.MeasUpdateParam;
import com.wpmac.zhihuiyun.model.MeterAddParam;
import com.wpmac.zhihuiyun.model.MeterDeletParam;
import com.wpmac.zhihuiyun.model.MeterQueryBean;
import com.wpmac.zhihuiyun.model.MeterQueryParam;
import com.wpmac.zhihuiyun.model.MeterUpdateParam;
import com.wpmac.zhihuiyun.model.NetStatusBean;
import com.wpmac.zhihuiyun.model.NetStatusParam;
import com.wpmac.zhihuiyun.model.PostAreaAddParam;
import com.wpmac.zhihuiyun.model.PostAreaUpdateParam;
import com.wpmac.zhihuiyun.model.RegistrationBean;
import com.wpmac.zhihuiyun.model.RegistrationParam;
import com.wpmac.zhihuiyun.model.SimpleResponseBean;

import org.json.JSONException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wpmac on 16/1/18.
 */
public class ConnectManager {

    private static ConnectManager mControlManager;

    public boolean mConnectionState = false;

    public Object lock = new Object();
    // 线程池
    private ExecutorService exector;

    private String json_error = "获取数据失败!",network_error="网络异常!";
    private ConnectManager() {
        exector = Executors.newFixedThreadPool(5);

    }

    /**
     * 控制器单例
     *
     * @return
     */
    public static ConnectManager getInstance() {
        if (mControlManager == null) {
            mControlManager = new ConnectManager();
        }
        return mControlManager;
    }

    /**
     * 请求loginid
     *
     * @param params
     * @param callback
     */
    public void getLogidData(final GetLogidParam params,
                                  final AbstractRequestListener<GetLogidBean> callback) {
        exector.execute(new Runnable() {

            @Override
            public void run() {

                try {
                    String method = params.getParam().getString("method");
                    String response = HttpConnectionUtil.openUrl(
                            Constants.SERVER_URL + method, "GET",
                            params.getParam(),false);
                    GetLogidBean bean = GetLogidBean.getLogidBean(response);
                    if (callback != null) {
                        callback.onComplete(bean);
                    }
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onFault(new CustomError(network_error));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onError(new CustomError(json_error));
                    }
                }
            }
        });

    }

    /**
     * 请求systemToken
     *
     * @param params
     * @param callback
     */
    public void getSystemToken(final GetSystemTokenParam params,
                             final AbstractRequestListener<GetSystemTokenBean> callback) {
        exector.execute(new Runnable() {

            @Override
            public void run() {

                try {
                    String method = params.getParam().getString("method");
                    String response = HttpConnectionUtil.openUrl(
                            Constants.SERVER_URL + method, "GET",
                            params.getParam(),false);
                    GetSystemTokenBean bean = GetSystemTokenBean.getSystemTokenBean(response);
                    if (callback != null) {
                        callback.onComplete(bean);
                    }
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onFault(new CustomError(network_error));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onError(new CustomError(json_error));
                    }
                }
            }
        });

    }
/*

post企业注册
 */

    public void  submitCompanyRegistration(
            final RegistrationParam params,
            final AbstractRequestListener<RegistrationBean> callback) {
        exector.execute(new Runnable() {

            @Override
            public void run() {

                try {
                    String method = params.getParam().getString("method");

                    String response = HttpConnectionUtil.openUrlByPost(
                            Constants.SERVER_URL + method,method,params.getParam());
                    RegistrationBean bean =  RegistrationBean
                            .registrationBean(response);
                    if (callback != null) {
                        callback.onComplete(bean);
                    }
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onFault(new CustomError(network_error));
                    }
                }catch (JSONException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onError(new CustomError(json_error));
                    }
                }
            }
        });
    }


    /**
     * 请求字典
     *
     * @param params
     * @param callback
     */
    public void getDic(final GetDicParam params,
                               final AbstractRequestListener<GetDicBean> callback) {
        exector.execute(new Runnable() {

            @Override
            public void run() {

                try {
                    String method = params.getParam().getString("method");
                    String response = HttpConnectionUtil.openUrl(
                            Constants.SERVER_URL + method, "GET",
                            params.getParam(),true);
                    GetDicBean bean = GetDicBean.getDicBean(response);
                    if (callback != null) {
                        callback.onComplete(bean);
                    }
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onFault(new CustomError(network_error));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onError(new CustomError(json_error));
                    }
                }
            }
        });

    }

    /**
     * 区域增加
     * @param params
     * @param callback
     */
    public void  PostAreaAdd(
            final PostAreaAddParam params,
            final String json,
            final AbstractRequestListener<SimpleResponseBean> callback) {
        exector.execute(new Runnable() {

            @Override
            public void run() {

                try {
                    String method = params.getParam().getString("method");

                    String response = HttpConnectionUtil.submitJsonByPost(
                            Constants.SERVER_URL + method,params.getParam(),json);
                    SimpleResponseBean bean =  SimpleResponseBean
                            .SimpleResponseBean(response);
                    if (callback != null) {
                        callback.onComplete(bean);
                    }
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onFault(new CustomError(network_error));
                    }
                }catch (JSONException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onError(new CustomError(json_error));
                    }
                }
            }
        });

    }

    /**
     * 区域修改
     * json
     * @param params
     * @param callback
     */
    public void  PostAreaUpdate(
            final PostAreaUpdateParam params,
            final String json,
            final AbstractRequestListener<SimpleResponseBean> callback) {
        exector.execute(new Runnable() {

            @Override
            public void run() {

                try {
                    String method = params.getParam().getString("method");

                    String response = HttpConnectionUtil.submitJsonByPost(
                            Constants.SERVER_URL + method,params.getParam(),json);
                    SimpleResponseBean bean =  SimpleResponseBean
                            .SimpleResponseBean(response);
                    if (callback != null) {
                        callback.onComplete(bean);
                    }
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onFault(new CustomError(network_error));
                    }
                }catch (JSONException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onError(new CustomError(json_error));
                    }
                }
            }
        });

    }


    /**
     * 区域删除
     * @param params
     * @param callback
     */
    public void  postAreaDelete(
            final AreaDeleteParam params,
            final AbstractRequestListener<SimpleResponseBean> callback) {
        exector.execute(new Runnable() {

            @Override
            public void run() {

                try {
                    String method = params.getParam().getString("method");

                    String response = HttpConnectionUtil.openUrlByPost(
                            Constants.SERVER_URL + method,method,params.getParam());
                    SimpleResponseBean bean =  SimpleResponseBean
                            .SimpleResponseBean(response);
                    if (callback != null) {
                        callback.onComplete(bean);
                    }
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onFault(new CustomError(network_error));
                    }
                }catch (JSONException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onError(new CustomError(json_error));
                    }
                }
            }
        });

    }

    /**
     * 区域查询
     *
     * @param params
     * @param callback
     */
    public void AreaQuery(final AreaQueryParam params,
                               final AbstractRequestListener<AreaQueryBean> callback) {
        exector.execute(new Runnable() {

            @Override
            public void run() {

                try {
                    String method = params.getParam().getString("method");
                    String response = HttpConnectionUtil.openUrl(
                            Constants.SERVER_URL + method, "GET",
                            params.getParam(),false);
                    AreaQueryBean bean = AreaQueryBean.AreaQueryBean(response);
                    if (callback != null) {
                        callback.onComplete(bean);
                    }
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onFault(new CustomError(network_error));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onError(new CustomError(json_error));
                    }
                }
            }
        });

    }


    /**
     * 设备添加
     * json
     * @param params
     * @param callback
     */
    public void  PostDeviceAdd(
            final DeviceAddParam params,
            final String json,
            final AbstractRequestListener<SimpleResponseBean> callback) {
        exector.execute(new Runnable() {

            @Override
            public void run() {

                try {
                    String method = params.getParam().getString("method");

                    String response = HttpConnectionUtil.submitJsonByPost(
                            Constants.SERVER_URL + method,params.getParam(),json);
                    SimpleResponseBean bean =  SimpleResponseBean
                            .SimpleResponseBean(response);
                    if (callback != null) {
                        callback.onComplete(bean);
                    }
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onFault(new CustomError(network_error));
                    }
                }catch (JSONException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onError(new CustomError(json_error));
                    }
                }
            }
        });

    }

    /**
     * 设备修改
     * json
     * @param params
     * @param callback
     */
    public void  PostDeviceUpdate(
            final DeviceUpdateParam params,
            final String json,
            final AbstractRequestListener<SimpleResponseBean> callback) {
        exector.execute(new Runnable() {

            @Override
            public void run() {

                try {
                    String method = params.getParam().getString("method");

                    String response = HttpConnectionUtil.submitJsonByPost(
                            Constants.SERVER_URL + method,params.getParam(),json);
                    SimpleResponseBean bean =  SimpleResponseBean
                            .SimpleResponseBean(response);
                    if (callback != null) {
                        callback.onComplete(bean);
                    }
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onFault(new CustomError(network_error));
                    }
                }catch (JSONException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onError(new CustomError(json_error));
                    }
                }
            }
        });

    }

    /**
     * 设备删除
     * @param params
     * @param callback
     */
    public void  postDeviceDelete(
            final DeviceDeleteParam params,
            final AbstractRequestListener<SimpleResponseBean> callback) {
        exector.execute(new Runnable() {

            @Override
            public void run() {

                try {
                    String method = params.getParam().getString("method");

                    String response = HttpConnectionUtil.openUrlByPost(
                            Constants.SERVER_URL + method,method,params.getParam());
                    SimpleResponseBean bean =  SimpleResponseBean
                            .SimpleResponseBean(response);
                    if (callback != null) {
                        callback.onComplete(bean);
                    }
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onFault(new CustomError(network_error));
                    }
                }catch (JSONException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onError(new CustomError(json_error));
                    }
                }
            }
        });

    }

    /**
     * 设备查询
     *
     * @param params
     * @param callback
     */
    public void DeviceQuery(final DeviceQueryParam params,
                       final AbstractRequestListener<DeviceQueryBean> callback) {
        exector.execute(new Runnable() {

            @Override
            public void run() {

                try {
                    String method = params.getParam().getString("method");
                    String response = HttpConnectionUtil.openUrl(
                            Constants.SERVER_URL + method, "GET",
                            params.getParam(),true);
                    DeviceQueryBean bean = DeviceQueryBean.deviceQueryBean(response);
                    if (callback != null) {
                        callback.onComplete(bean);
                    }
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onFault(new CustomError(network_error));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onError(new CustomError(json_error));
                    }
                }
            }
        });

    }

//    GetDeviceNetworkStatus
    /**
     * 网关状态查询
     *
     * @param params
     * @param callback
     */
    public void GetDeviceNetworkStatus(final NetStatusParam params,
                          final AbstractRequestListener<NetStatusBean> callback) {
        exector.execute(new Runnable() {

            @Override
            public void run() {

                try {
                    String method = params.getParam().getString("method");
                    String response = HttpConnectionUtil.openUrl(
                            Constants.SERVER_URL + method, "GET",
                            params.getParam(),false);
                    NetStatusBean bean = NetStatusBean.netStatusBean(response);
                    if (callback != null) {
                        callback.onComplete(bean);
                    }
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onFault(new CustomError(network_error));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onError(new CustomError(json_error));
                    }
                }
            }
        });

    }


    /**
     * 表记增加
     * @param params
     * @param callback
     */
    public void  PostMeterAdd(
            final MeterAddParam params,
            final String json,
            final AbstractRequestListener<SimpleResponseBean> callback) {
        exector.execute(new Runnable() {

            @Override
            public void run() {

                try {
                    String method = params.getParam().getString("method");

                    String response = HttpConnectionUtil.submitJsonByPost(
                            Constants.SERVER_URL + method,params.getParam(),json);
                    SimpleResponseBean bean =  SimpleResponseBean
                            .SimpleResponseBean(response);
                    if (callback != null) {
                        callback.onComplete(bean);
                    }
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onFault(new CustomError(network_error));
                    }
                }catch (JSONException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onError(new CustomError(json_error));
                    }
                }
            }
        });

    }


    /**
     * 表记修改
     * @param params
     * @param callback
     */
    public void  PostMeterUpdate(
            final MeterUpdateParam params,
            final String json,
            final AbstractRequestListener<SimpleResponseBean> callback) {
        exector.execute(new Runnable() {

            @Override
            public void run() {

                try {
                    String method = params.getParam().getString("method");

                    String response = HttpConnectionUtil.submitJsonByPost(
                            Constants.SERVER_URL + method,params.getParam(),json);
                    SimpleResponseBean bean =  SimpleResponseBean
                            .SimpleResponseBean(response);
                    if (callback != null) {
                        callback.onComplete(bean);
                    }
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onFault(new CustomError(network_error));
                    }
                }catch (JSONException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onError(new CustomError(json_error));
                    }
                }
            }
        });

    }

//    PostMeterDelete

    /**
     * 表记删除
     * @param params
     * @param callback
     */
    public void  PostMeterDelete(
            final MeterDeletParam params,
            final AbstractRequestListener<SimpleResponseBean> callback) {
        exector.execute(new Runnable() {

            @Override
            public void run() {

                try {
                    String method = params.getParam().getString("method");

                    String response = HttpConnectionUtil.openUrlByPost(
                            Constants.SERVER_URL + method,method,params.getParam());
                    SimpleResponseBean bean =  SimpleResponseBean
                            .SimpleResponseBean(response);
                    if (callback != null) {
                        callback.onComplete(bean);
                    }
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onFault(new CustomError(network_error));
                    }
                }catch (JSONException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onError(new CustomError(json_error));
                    }
                }
            }
        });

    }

//    GetMeterQuery
    /**
     * 网关状态查询
     *
     * @param params
     * @param callback
     */
    public void GetMeterQuery(final MeterQueryParam params,
                                       final AbstractRequestListener<MeterQueryBean> callback) {
        exector.execute(new Runnable() {

            @Override
            public void run() {

                try {
                    String method = params.getParam().getString("method");
                    String response = HttpConnectionUtil.openUrl(
                            Constants.SERVER_URL + method, "GET",
                            params.getParam(),false);
                    MeterQueryBean bean = MeterQueryBean.meterQueryBean(response);
                    if (callback != null) {
                        callback.onComplete(bean);
                    }
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onFault(new CustomError(network_error));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onError(new CustomError(json_error));
                    }
                }
            }
        });

    }
    //PostMeasAdd

    public void  PostMeasAdd(
            final MeasAddParam params,
            final String json,
            final AbstractRequestListener<SimpleResponseBean> callback) {
        exector.execute(new Runnable() {

            @Override
            public void run() {

                try {
                    String method = params.getParam().getString("method");

                    String response = HttpConnectionUtil.submitJsonByPost(
                            Constants.SERVER_URL + method,params.getParam(),json);
                    SimpleResponseBean bean =  SimpleResponseBean
                            .SimpleResponseBean(response);
                    if (callback != null) {
                        callback.onComplete(bean);
                    }
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onFault(new CustomError(network_error));
                    }
                }catch (JSONException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onError(new CustomError(json_error));
                    }
                }
            }
        });

    }
    //PostMeasUpdate

    public void  PostMeasUpdate(
            final MeasUpdateParam params,
            final String json,
            final AbstractRequestListener<SimpleResponseBean> callback) {
        exector.execute(new Runnable() {

            @Override
            public void run() {

                try {
                    String method = params.getParam().getString("method");

                    String response = HttpConnectionUtil.submitJsonByPost(
                            Constants.SERVER_URL + method,params.getParam(),json);
                    SimpleResponseBean bean =  SimpleResponseBean
                            .SimpleResponseBean(response);
                    if (callback != null) {
                        callback.onComplete(bean);
                    }
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onFault(new CustomError(network_error));
                    }
                }catch (JSONException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onError(new CustomError(json_error));
                    }
                }
            }
        });

    }

//    PostMeasDelete


    public void  PostMeasDelete(
            final MeasDeleteParam params,
            final String json,
            final AbstractRequestListener<SimpleResponseBean> callback) {
        exector.execute(new Runnable() {

            @Override
            public void run() {

                try {
                    String method = params.getParam().getString("method");

                    String response = HttpConnectionUtil.submitJsonByPost(
                            Constants.SERVER_URL + method,params.getParam(),json);
                    SimpleResponseBean bean =  SimpleResponseBean
                            .SimpleResponseBean(response);
                    if (callback != null) {
                        callback.onComplete(bean);
                    }
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onFault(new CustomError(network_error));
                    }
                }catch (JSONException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onError(new CustomError(json_error));
                    }
                }
            }
        });

    }
//    PostMeasQuey

    public void  PostMeasQuey(
            final MeasQueryParam params,
            final String json,
            final AbstractRequestListener<GetMeasQueryBean> callback) {
        exector.execute(new Runnable() {

            @Override
            public void run() {

                try {
                    String method = params.getParam().getString("method");

                    String response = HttpConnectionUtil.submitJsonByPost(
                            Constants.SERVER_URL + method,params.getParam(),json);
                    GetMeasQueryBean bean =  GetMeasQueryBean
                            .getMeasQueryBean(response);
                    if (callback != null) {
                        callback.onComplete(bean);
                    }
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onFault(new CustomError(network_error));
                    }
                }catch (JSONException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onError(new CustomError(json_error));
                    }
                }
            }
        });

    }










}

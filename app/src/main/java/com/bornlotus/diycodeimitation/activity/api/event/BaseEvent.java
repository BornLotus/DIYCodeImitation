package com.bornlotus.diycodeimitation.activity.api.event;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * 所有 Event 的基类
 * <p>
 * T 为对应的实体类
 * <p>
 * HTTP Status
 * -1 - 可能是网络未连接
 * 200, 201 - 请求成功，或执行成功
 * 400 - 参数不符合 API 的要求、或者数据格式验证没有通过，请配合 Response Body 里面的 error 信息确定问题。
 * 401 - 用户认证失败，或缺少认证信息，比如 access_token 过期，或没传，可以尝试用 refresh_token 方式获得新的 access_token。
 * 403 - 当前用户对资源没有操作权限
 * 404 - 资源不存在。
 * 500 - 服务器异常
 */

public class BaseEvent<T> {

    protected String uuid = "";         //通用唯一识别码
    protected boolean ok = false;       //是否获取数据(T)成功
    protected Integer code = -1;        //状态码
    protected T t;                      //实体类


    public BaseEvent(@NonNull String uuid) {
        this.uuid = uuid;
    }

    public BaseEvent(@NonNull String uuid, @NonNull Integer code, @NonNull T t) {
        this.uuid = uuid;
        this.code = code;
        this.t = t;
    }

    public BaseEvent setEvent(@NonNull Integer code, @Nullable T t) {
        this.ok = t != null;
        this.code = code;
        this.t = t;
        return this;
    }

    public boolean isOk() {
        return this.ok;
    }

    public T getBean() {
        return this.t;
    }

    public String getUUID() {
        return this.uuid;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getCodeDescribe() {
        switch (code) {
            case -1:
                return "可能是网络问题或者初始化失败";
            case 200:
            case 201:
                return "请求成功或执行成功";
            case 400:
                return "参数不符合API的要求、或者数据格式验证没有通过";
            case 401:
                return "用户验证失败，或缺少验证信息，比如access_token过期或没传，可以尝试refresh_token方式获取新的额access_token";
            case 402:
                return "用户尚未登录";
            case 403:
                return "当前用户对资源无权限";
            case 404:
                return "资源不存在";
            case 500:
                return "服务端异常";
                default:
                    return "未知异常(" + code + ")";
        }
    }

}
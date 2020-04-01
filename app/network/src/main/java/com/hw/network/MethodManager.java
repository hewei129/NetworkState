package com.hw.network;


import java.lang.reflect.Method;

/**
 * @author hewei(David)
 * @date 2020-03-25  15:35
 * @Copyright ©  Shanghai Yejia Digital Technology Co., Ltd.
 * @description
 */

class MethodManager {
    private Class<?> type;
    private NetType netType;
    private Method method;

    public MethodManager(Class<?> type, NetType netType, Method method) {
        this.type = type;
        this.netType = netType;
        this.method = method;
    }

    public MethodManager() {

    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public NetType getNetType() {
        return netType;
    }

    public void setNetType(NetType netType) {
        this.netType = netType;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}

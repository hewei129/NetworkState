package com.hw.network;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkRequest;
import android.os.Build;

import com.hw.network.utils.Constants;


/**
 * @author hewei(David)
 * @date 2020-03-25  15:47
 * @Copyright ©  Shanghai Yejia Digital Technology Co., Ltd.
 * @description
 */
public class NetworkManager {
    private static volatile NetworkManager manager;
    private Application application;
    private NetworkStateReceiver receiver;

    private NetworkManager() {
        receiver = new NetworkStateReceiver();
    }

    public static NetworkManager getInstance() {
        if (manager == null) {
            synchronized (NetworkManager.class) {
                if (manager == null) {
                    manager = new NetworkManager();
                }
            }
        }

        return manager;
    }

    public Application getApplication() {
        if (application == null) {
            throw new RuntimeException("please call init method in your app");
        }
        return application;
    }


    public void init(Application app) {
        this.application = app;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            NetworkCallbackImpl networkCallback = new NetworkCallbackImpl();
            NetworkRequest.Builder builder = new NetworkRequest.Builder();
            NetworkRequest request = builder.build();
            ConnectivityManager connmagr = (ConnectivityManager) NetworkManager.getInstance()
                    .getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connmagr != null) {
                connmagr.registerNetworkCallback(request, networkCallback);
                //                connmagr.unregisterNetworkCallback(networkCallback);
            }

        } else {
            //广播注册
            IntentFilter filter = new IntentFilter();
            filter.addAction(Constants.ANDROID_NET_ACTION);
            application.registerReceiver(receiver, filter);
        }
    }

    public void registerObserver(Object register) {
        receiver.registerObserver(register);
    }

    public void unRegisterObserver(Object register) {
        receiver.unRegisterObserver(register);
    }

    public void unRegisterAllObserver() {
        receiver.unRegisterAllObserver();
    }


}

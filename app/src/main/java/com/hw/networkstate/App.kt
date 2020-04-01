package com.hw.networkstate

import android.app.Application
import com.hw.network.NetworkManager


/**
 * @author hewei(David)
 * @date 2020/4/1  2:44 PM
 * @Copyright ©  Shanghai Yejia Digital Technology Co., Ltd.
 * @description
 */

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        NetworkManager.getInstance().init(this)
    }
}
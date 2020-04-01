@file:Suppress("DEPRECATION")

package com.hw.network.utils

import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import androidx.annotation.RequiresPermission
import com.hw.network.NetType
import com.hw.network.NetworkManager


/**
 * @author hewei(David)
 * @date 2020-03-18  15:25
 * @Copyright ©  Shanghai Yejia Digital Technology Co., Ltd.
 * @description
 */

/**
 * 判断网络是否可用
 *
 * @param context Context对象
 */
@SuppressWarnings("deprecation")
fun isNetworkReachable(context: Context): Boolean {
    val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val networkCapabilities = manager.getNetworkCapabilities(manager.activeNetwork)
        if (networkCapabilities != null) {
            return (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                    || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                    || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET))
        }
    } else {
        val networkInfo = manager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
    return false
}

/**
 * Wifi是否已连接
 *
 * @return true:已连接 false:未连接
 */
@RequiresPermission(ACCESS_NETWORK_STATE)
fun isWifiConnected(context: Context): Boolean {
    val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val networkCapabilities = manager.getNetworkCapabilities(manager.activeNetwork)
        if (networkCapabilities != null) {
            return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
        }
    } else {
        val networkInfo: NetworkInfo? = manager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected && networkInfo.type == ConnectivityManager.TYPE_WIFI
    }
    return false
}


/**
 * 是否为流量
 */
@RequiresPermission(ACCESS_NETWORK_STATE)
fun isMobileData(context: Context): Boolean {
    val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val networkCapabilities = manager.getNetworkCapabilities(manager.activeNetwork)
        if (networkCapabilities != null) {
            return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
        }
    } else {
        val networkInfo = manager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected && networkInfo.type == ConnectivityManager.TYPE_MOBILE
    }
    return false
}

/**
 * 获取网络类型
 * @return
 */
fun getNetType(): NetType? {
    val connmagr =
        NetworkManager.getInstance().application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            ?: return NetType.NONE
    val activeNetworkInfo = connmagr.activeNetworkInfo
    if (activeNetworkInfo != null) {
        val type = activeNetworkInfo.type
        if (type == ConnectivityManager.TYPE_MOBILE) {
            val extraInfo = activeNetworkInfo.extraInfo
            if (extraInfo != null && !extraInfo.isEmpty()) {
                return if (extraInfo.equals("cmnet", ignoreCase = true)) {
                    NetType.CMNET
                } else {
                    NetType.CMWAP
                }
            }
        } else if (type == ConnectivityManager.TYPE_WIFI) {
            return NetType.WIFI
        }
    }
    return NetType.NONE
}


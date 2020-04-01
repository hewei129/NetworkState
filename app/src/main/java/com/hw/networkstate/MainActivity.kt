package com.hw.networkstate

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.hw.network.NetType
import com.hw.network.NetworkManager
import com.hw.network.annotation.NetWork

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //注册
        NetworkManager.getInstance().registerObserver(this)

    }

    @NetWork(netType = NetType.AUTO)
    fun network(netType: NetType?) {
        when (netType) {
            NetType.WIFI -> {
                Log.e("NETWORK", "wifi")
                Log.e("NETWORK", "4G")
            }
            NetType.CMNET, NetType.CMWAP -> {
                Log.e("NETWORK", "4G")
            }
            NetType.AUTO -> {
            }
            NetType.NONE -> {
                Log.e("NETWORK", "无网络")
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        //注销
        NetworkManager.getInstance().unRegisterObserver(this)
    }
}

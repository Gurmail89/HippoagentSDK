package com.hippo.agentsdk

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.hippoagent.HippoConfig
import com.hippoagent.HippoConfigAttributes
import com.hippoagent.HippoNotificationConfig
import com.hippoagent.LoginCallback
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*FirebaseInstanceId.getInstance().instanceId
            .addOnSuccessListener(this@MainActivity) { instanceIdResult ->
                val newToken = instanceIdResult.token
                Log.e("newToken", newToken)
                initSDK(newToken)
            }*/

        initSDK("qwertyuio")

        cbOpenChat.setOnClickListener {
            HippoConfig.getInstance().openMainScreen(this@MainActivity)
        }

        cbOpenBroadcast.setOnClickListener {
            HippoConfig.getInstance().openBroadcast(this@MainActivity)
        }

        cbOpenSavedPlan.setOnClickListener {
            HippoConfig.getInstance().openSavedReply(this@MainActivity)
        }

        cbLogout.setOnClickListener {
            HippoConfig.getInstance().logoutHippo(this@MainActivity)
        }



        Log.e("Deeplink =", "getIntent().getData()=" + intent.data)
        Log.e("Deeplink =", "getIntent().getExtras()=" + intent.extras)

//        val jsonObj = JSONObject("")
        HippoNotificationConfig().handleHippoPushNotification(this@MainActivity, intent.extras)
    }

    private fun initSDK(newToken: String) {

        val attributes = HippoConfigAttributes.Builder()
//                .setAppType(9)
//                .setAppKey("e79b5d25236d51010dcf456d94077208")
//                .setAuthToken("9efdcdbe0c12be25238bf75857319b36")
                .setAppType(1)
                .setAppSecretKey("f093958133bcda12af89c0f265f5204e")
                .setAuthToken("7246a2cfec555402369cede599e58cdc")
                .setDeviceToken(newToken)
                .setEnvironment("live")
                .setProvider("com.fuguagent.provider")
                .setIsWhitelabel(true)
                .setAppName("buddy")
                .setShowLog(true)
                .build()

        HippoConfig.initHippoConfig(this@MainActivity, attributes, object : LoginCallback{
            override fun onError() {

            }

            override fun hasData() {
                cbOpenChat.visibility = View.VISIBLE
            }

            override fun success() {
                cbOpenChat.visibility = View.VISIBLE
            }

            override fun failed() {

            }

        })
    }


}




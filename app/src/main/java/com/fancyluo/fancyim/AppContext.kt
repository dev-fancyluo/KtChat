package com.fancyluo.fancyim

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.AudioManager
import android.media.SoundPool
import android.support.v4.app.TaskStackBuilder
import cn.bmob.v3.Bmob
import com.fancyluo.fancyim.intefaces.EMMessageListenerAdapter
import com.fancyluo.fancyim.ui.activity.ChatActivity
import com.fancyluo.fancyim.ui.activity.MainActivity
import com.hyphenate.EMConnectionListener
import com.hyphenate.EMError
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMOptions
import com.hyphenate.chat.EMTextMessageBody
import org.jetbrains.anko.runOnUiThread

/**
 * fancyLuo
 * date: 2017/11/21 20:10
 * desc:
 */
class AppContext : Application() {

    private val soundPool = SoundPool(2, AudioManager.STREAM_MUSIC, 0)
    val shortMusic by lazy { soundPool.load(instance, R.raw.short_music, 0) }
    val longMusic by lazy { soundPool.load(instance, R.raw.long_music, 0) }

    companion object {
        @JvmStatic
        lateinit var instance: AppContext
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initHx()
        initBmob()
    }

    private fun initBmob() {
        Bmob.initialize(this, "244e708577f0ac5ba47eee84a92c0d5b")
    }

    private fun initHx() {
        //初始化
        EMClient.getInstance().init(applicationContext, EMOptions())
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(BuildConfig.DEBUG)

        initMsgListener()
        initConnectionListener()
    }

    private fun initConnectionListener() {
        EMClient.getInstance().addConnectionListener(object : EMConnectionListener {
            override fun onConnected() {}

            override fun onDisconnected(error: Int) {
                runOnUiThread {
                    when (error) {
                        EMError.USER_REMOVED -> toMainActivity(error)
                        EMError.USER_LOGIN_ANOTHER_DEVICE -> toMainActivity(error)
                    }
                }
            }
        })
    }

    private fun toMainActivity(error: Int) {
        // 账号在其他设备登陆
        val intent = Intent(instance, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.putExtra("error", error)
        startActivity(intent)
    }

    private fun initMsgListener() {
        EMClient.getInstance().chatManager().addMessageListener(object : EMMessageListenerAdapter() {
            override fun onMessageReceived(p0: MutableList<EMMessage>?) {
                if (isForeground()) {
                    // 前台播放短提示音
                    soundPool.play(shortMusic, 1f, 1f, 0, 0, 1f)
                } else {
                    // 后台播放长提示音
                    soundPool.play(longMusic, 1f, 1f, 0, 0, 1f)
                    // 后台显示通知框
                    showNotification(p0)
                }
            }
        })
    }

    private fun showNotification(p0: MutableList<EMMessage>?) {
        p0?.forEach {
            val intent = Intent(instance, ChatActivity::class.java)
            intent.putExtra("username", it.conversationId())

            val taskStackBuilder = TaskStackBuilder.create(instance)
                    .addParentStack(ChatActivity::class.java).addNextIntent(intent)
            val pendingIntent = taskStackBuilder.getPendingIntent(
                    0, PendingIntent.FLAG_UPDATE_CURRENT)

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val notification = Notification.Builder(instance)
                    .setContentTitle("新消息到达")
                    .setContentText((it.body as EMTextMessageBody).message)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_avatar_default))
                    .notification

            notificationManager.notify(1, notification)
        }
    }

    /**
     * 判断应用是否在前台
     */
    private fun isForeground(): Boolean {
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        activityManager.runningAppProcesses.forEach {
            if (it.processName == packageName) {
                return it.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
            }
        }
        return false
    }

}
package org.albert.x08_viewmodel.helper

import android.content.Context
import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import org.albert.x08_viewmodel.service.PseudoLocationManager
import org.albert.x08_viewmodel.service.PseudoMessagingConnection
import org.albert.x08_viewmodel.service.PseudoMessagingManager

class ThirdFragmentHelper(private val context: Context, private val lifecycle: Lifecycle) : DefaultLifecycleObserver {
    init {
        lifecycle.addObserver(this)
    }

    private val tag = this::class.simpleName

    private val pseudoLocationManager = PseudoLocationManager(context) {lat, lon ->
        currentLat = lat
        currentLon = lon
        Log.d(tag, "lat:$currentLat, lon:$currentLon")
    }

    var currentLat = 0.0
    var currentLon = 0.0

    private val pseudoMessagingManager = PseudoMessagingManager(context)
    private var conn: PseudoMessagingConnection? = null

    fun sendMessage() {
        Log.d(tag, "SENDING MSG")
        conn?.send("$currentLat : $currentLon")
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        pseudoLocationManager.start()
        pseudoMessagingManager.connect { connection ->
            // prevents leaks
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED))
                conn = connection
            else
                connection.disconnect()
        }
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        pseudoLocationManager.stop()
        conn?.disconnect()
    }
}
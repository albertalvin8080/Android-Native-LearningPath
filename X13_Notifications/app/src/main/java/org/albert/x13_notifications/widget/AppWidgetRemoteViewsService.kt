package org.albert.x13_notifications.widget

import android.content.Intent
import android.widget.RemoteViewsService

class AppWidgetRemoteViewsService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent?): RemoteViewsFactory {
        // applicationContext because we don't want to tie this factory to any specific activity context.
        return AppWidgetRemoteViewsFactory(applicationContext)
    }
}
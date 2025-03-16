package org.albert.x13_notifications

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import androidx.core.app.TaskStackBuilder
import org.albert.x13_notifications.widget.AppWidgetRemoteViewsService

/**
 * Implementation of App Widget functionality.
 */
class FirstAppWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        if (action == AppWidgetManager.ACTION_APPWIDGET_UPDATE) {
            val manager = AppWidgetManager.getInstance(context)
            val componentName = ComponentName(context, FirstAppWidget::class.java)
            manager.notifyAppWidgetViewDataChanged(
                manager.getAppWidgetIds(componentName),
                R.id.authors_list
            )
        }

        super.onReceive(context, intent)
    }

    companion object {
        fun sendRefreshBroadcast(context: Context) {
            val intent = Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE).apply {
                // Explicitly set the component name to handle the intent.
                component = ComponentName(context, FirstAppWidget::class.java)
            }
            context.sendBroadcast(intent)
        }
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val widgetText = context.getString(R.string.appwidget_text)
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.first_app_widget)
    views.setTextViewText(R.id.appwidget_text, widgetText)
    views.setRemoteAdapter(
        R.id.authors_list,
        Intent(context, AppWidgetRemoteViewsService::class.java)
    )

    val pendingIntent = TaskStackBuilder.create(context)
        .addNextIntentWithParentStack(Intent(context, SecondActivity::class.java))
        .getPendingIntent(0, PendingIntent.FLAG_MUTABLE)

    views.setPendingIntentTemplate(R.id.authors_list, pendingIntent)

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}
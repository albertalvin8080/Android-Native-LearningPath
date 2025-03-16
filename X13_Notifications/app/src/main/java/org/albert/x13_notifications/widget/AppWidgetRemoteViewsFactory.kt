package org.albert.x13_notifications.widget

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import org.albert.x13_notifications.R
import org.albert.x13_notifications.data.DataManager

class AppWidgetRemoteViewsFactory(private val context: Context) :
    RemoteViewsService.RemoteViewsFactory {
    override fun onCreate() {
    }

    override fun onDataSetChanged() {
    }

    override fun onDestroy() {
    }

    override fun getCount(): Int {
        return DataManager.authors.size
    }

    override fun getViewAt(position: Int): RemoteViews {
        val rv = RemoteViews(context.packageName, R.layout.authors_widget)
        rv.setTextViewText(R.id.author_name, DataManager.authors[position].name)

        val extras =
            Bundle().apply { putString("KEY_AUTHOR_NAME", DataManager.authors[position].name) }
        val fillInIntent = Intent().apply {
            putExtras(extras)
        }
        // When you click on a widget_item, the fillInIntent will be used to complete
        // PendingIntentTemplate.
        rv.setOnClickFillInIntent(R.id.widget_item, fillInIntent)

        return rv
    }

    override fun getLoadingView(): RemoteViews? {
        return null
    }

    override fun getViewTypeCount(): Int {
        return 1
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    // ids don't change overtime
    override fun hasStableIds(): Boolean {
        return true
    }
}
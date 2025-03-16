package org.albert.x13_notifications.notify

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.Person
import androidx.core.app.RemoteInput
import androidx.core.app.TaskStackBuilder
import org.albert.x13_notifications.MainActivity
import org.albert.x13_notifications.R
import org.albert.x13_notifications.SecondActivity
import org.albert.x13_notifications.notify.ReminderNotification.KEY_TEXT_REPLY

object ReminderNotification {
    private const val CHANNEL_ID = "reminder_channel_id"
    private const val CHANNEL_NAME = "Reminder Notifications"
    private const val CHANNEL_DESCRIPTION = "Immediate reminder notifications"
    private const val NOTIFICATION_ID = 1
    public const val KEY_TEXT_REPLY = "KEY_TEXT_REPLY"

    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance).apply {
                description = CHANNEL_DESCRIPTION
            }
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
        askPermissions(context)
    }

    private fun askPermissions(context: Context) {
        if (ActivityCompat.checkSelfPermission(
                context, Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                context as MainActivity, arrayOf(Manifest.permission.POST_NOTIFICATIONS), 1
            )
        }
    }

    @SuppressLint("MissingPermission")
    fun showImmediateNotification(
        context: Context, title: String, message: String, replyText: String? = null
    ) {
        val pendingIntent = PendingIntent.getActivity(
            context,
            0, // requestCode
            Intent.createChooser(
                Intent(Intent.ACTION_SEND).setType("text/plain").putExtra(
                        Intent.EXTRA_TEXT, message
                    ), "Share Note Reminder" // title
            ),
//            PendingIntent.FLAG_UPDATE_CURRENT
            PendingIntent.FLAG_IMMUTABLE, // Targeting S+ 31 and above
        )

        val intent = PendingIntent.getActivity(
            context,
            0,
            Intent(context, SecondActivity::class.java),
            PendingIntent.FLAG_IMMUTABLE,
        )
        val pendingIntentWithSyntheticBackStack = TaskStackBuilder.create(context)
            .addNextIntentWithParentStack(Intent(context, SecondActivity::class.java))
            .getPendingIntent(0, PendingIntent.FLAG_MUTABLE) // MUTABLE

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_announcement_24).setContentTitle(title)
            .setContentText(message).setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true) // Automatically dismiss the notification after being tapped.
            .addAction(
                R.drawable.baseline_assistant_navigation_24, "SHARE", // title
                pendingIntent
            ).setContentIntent(pendingIntentWithSyntheticBackStack)
//            .setStyle(NotificationCompat.BigTextStyle()
//                .setBigContentTitle("I'm Big Content Title")
//                .bigText("""
//                    Summa Blasphemia
//                    Summa Blasphemia
//                    Summa Blasphemia
//                    Summa Blasphemia
//                    Summa Blasphemia
//                """.trimIndent())
//                .setSummaryText("Summary Text")
//            )
            .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.dark_sign))
//            .setStyle(NotificationCompat.BigPictureStyle()
//                .bigPicture(BitmapFactory.decodeResource(context.resources, R.drawable.dark_sign))
//                .bigLargeIcon(null as Bitmap?) // cast due to method ambiguity
//            )
//            .setStyle(NotificationCompat.InboxStyle()
//                .addLine("Detestatio Sacrorum")
//                .addLine("Summa Blasphemia")
//                .addLine("Exemplaris Exvomvnicationis")
//                .addLine("Requiem AEternam")
//            )
            .apply {
                val user = Person.Builder().setName("YOU").build()

                val msg1 = NotificationCompat.MessagingStyle.Message(
                    "Hello there",
                    System.currentTimeMillis(),
                    Person.Builder().setName("Fortissax").build()
                )
                val msg2 = NotificationCompat.MessagingStyle.Message(
                    "What the...",
                    System.currentTimeMillis(),
                    Person.Builder().setName("Godwyn").build()
                )
                val msg3 = NotificationCompat.MessagingStyle.Message(
                    "Ops", System.currentTimeMillis(), Person.Builder().setName("Ranni").build()
                )

                setOnlyAlertOnce(true)

                val remoteInput = RemoteInput.Builder(KEY_TEXT_REPLY).setLabel("REPLY").build()

                val replyIntent = Intent(context, MessageReplyReceiver::class.java)
                val replyIntentPending = PendingIntent.getBroadcast(
                    context, 100, replyIntent, PendingIntent.FLAG_MUTABLE
                )

                // ReplyAction = RemoteInput + ReplyIntent
                val replyAction = NotificationCompat.Action.Builder(
                        R.drawable.dark_sign, "REPLY", replyIntentPending
                    ).addRemoteInput(remoteInput).build()

                addAction(replyAction)

                // NOTE: setStyle() expects a NotificationCompat.Style, not MessagingStyle itself.
                setStyle(NotificationCompat.MessagingStyle(user).run {
                    setConversationTitle("Night of the black knives")
                    addMessage(msg1)
                    addMessage(msg2)
                    addMessage(msg3)
                    if (replyText != null)
                        addMessage(
                            NotificationCompat.MessagingStyle.Message(
                                replyText,
                                System.currentTimeMillis(),
                                Person.Builder().setName("You").build()
                            )
                        )
                    this@run // return the context object
                })
            }

        Log.d("NOTIFY", "BUILDER TRUE")
        with(NotificationManagerCompat.from(context)) {
            notify(NOTIFICATION_ID, builder.build())
        }
    }
}

//<receiver android:name=".notify.MessageReplyReceiver"
//  android:exported="false"
//  android:directBootAware="true">
//      <intent-filter>
//          <action android:name="android.intent.action.RESPOND_VIA_MESSAGE"/>
//      </intent-filter>
//</receiver>
class MessageReplyReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val bundle = RemoteInput.getResultsFromIntent(intent) ?: return

        val textReply =
            bundle.getCharSequence(KEY_TEXT_REPLY)?.toString() ?: ""
        ReminderNotification.showImmediateNotification(
            context, "After Reply", "some message", textReply
        )

        bundle.clear()
    }
}
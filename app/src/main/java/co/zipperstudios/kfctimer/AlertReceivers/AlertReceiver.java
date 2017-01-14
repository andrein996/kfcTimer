package co.zipperstudios.kfctimer.AlertReceivers;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;

import co.zipperstudios.kfctimer.ui.MainActivity;
import co.zipperstudios.kfctimer.R;

/**
 * Created by Andrei on 7/20/2016.
 */

public class AlertReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String product = intent.getStringExtra("product");
        String quantity = intent.getStringExtra("quantity");
        String productPackage = intent.getStringExtra("choice");
        String uniqueID = intent.getStringExtra("id");

        createNotification(context, uniqueID, product, quantity, productPackage);
    }

    private void createNotification(Context context, String uniqueID, String product, String quantity, String productPackage) {

        PendingIntent notificIntent = PendingIntent.getActivity(context, Integer.parseInt(uniqueID),
                new Intent(context, MainActivity.class), 0);

        String expired = context.getResources().getString(R.string.alertExpired).concat(" ")
                .concat(quantity).concat(productPackage)
                .concat(" ").concat(product);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setContentTitle(context.getResources().getString(R.string.notificationTitle))        // titlu sus
                .setTicker(context.getResources().getString(R.string.notificationTicker))             // idk yet
                .setContentText(expired)        // desciere mesaj
                .setSmallIcon(R.drawable.ic_action_name);

        mBuilder.setContentIntent(notificIntent);

        mBuilder.setAutoCancel(true);

        Notification notificationAlarm = mBuilder.build();

        notificationAlarm.sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

        NotificationManager mNotification =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        mNotification.notify(Integer.parseInt(uniqueID), notificationAlarm);
    }
}

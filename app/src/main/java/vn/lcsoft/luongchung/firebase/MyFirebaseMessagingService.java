package vn.lcsoft.luongchung.firebase;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import vn.lcsoft.luongchung.tluschedule.MainActivity;
import vn.lcsoft.luongchung.tluschedule.R;
import vn.lcsoft.luongchung.tluschedule.Thongbao;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private String kieu="Image";
    private String LuuThoiGianTietHoc="Chuaco";
    private String html="<html> Không có thông báo !</html>";
    private String link="https://luongchung.github.io";
    private String TAG = "LUONGCHUNGCHAT";
    private int lockVS=-1;
    NotificationManager notifManager;
    NotificationChannel mChannel;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage){
        if(remoteMessage.getData().size() > 0) {
            kieu=remoteMessage.getData().get("Kieu");
            html=remoteMessage.getData().get("Html");
            link=remoteMessage.getData().get("Link");



//            try {
//                lockVS= Integer.parseInt(remoteMessage.getData().get("Version"));
//            }catch (Exception ex){
//            }
//            if(lockVS!=-1){
//            SharedPreferences sharedPreferences= getApplication().getBaseContext().getSharedPreferences(LuuThoiGianTietHoc,MODE_PRIVATE);
//            SharedPreferences.Editor editor=sharedPreferences.edit();
//            editor.putInt("versioncode",lockVS);
//            editor.commit();
//            }

        }
        //Check if the message contains notification
        if(remoteMessage.getNotification() != null) {
            displayCustomNotificationForOrders(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
        }
    }
    private void displayCustomNotificationForOrders(String title, String description) {
        if (notifManager == null) {
            notifManager = (NotificationManager) getSystemService
                    (Context.NOTIFICATION_SERVICE);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationCompat.Builder builder;
            Intent intent = new Intent(this, Thongbao.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("kieu",kieu);
            intent.putExtra("html",html);
            intent.putExtra("link",link);
            PendingIntent pendingIntent;
            int importance = NotificationManager.IMPORTANCE_HIGH;
            if (mChannel == null) {
                mChannel = new NotificationChannel
                        ("0", title, importance);
                mChannel.setDescription(description);
                mChannel.enableVibration(true);
                notifManager.createNotificationChannel(mChannel);
            }
            builder = new NotificationCompat.Builder(this, "0");

            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pendingIntent = PendingIntent.getActivity(this, 1251, intent, PendingIntent.FLAG_ONE_SHOT);
            builder.setContentTitle(title)
                    .setSmallIcon(getNotificationIcon()) // required
                    .setContentText(description)  // required
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .setLargeIcon(BitmapFactory.decodeResource
                            (getResources(), R.mipmap.ic_launcher))
                    .setBadgeIconType(R.mipmap.ic_launcher_round)
                    .setContentIntent(pendingIntent)
                    .setSound(RingtoneManager.getDefaultUri
                            (RingtoneManager.TYPE_NOTIFICATION));
            Notification notification = builder.build();
            notifManager.notify(0, notification);
        } else {

            Intent intent = new Intent(this, Thongbao.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("kieu",kieu);
            intent.putExtra("html",html);
            intent.putExtra("link",link);

            PendingIntent pendingIntent = null;
            pendingIntent = PendingIntent.getActivity(this, 1251, intent, PendingIntent.FLAG_ONE_SHOT);

            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setContentTitle(title)
                    .setContentText(description)
                    .setAutoCancel(true)
                    .setColor(ContextCompat.getColor(getBaseContext(), R.color.colorPrimary))
                    .setSound(defaultSoundUri)
                    .setSmallIcon(getNotificationIcon())
                    .setContentIntent(pendingIntent)
                    .setStyle(new NotificationCompat.BigTextStyle().setBigContentTitle(title).bigText(description));

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(1251, notificationBuilder.build());
        }
    }
    private int getNotificationIcon() {
        boolean useWhiteIcon = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP);
        return useWhiteIcon ? R.mipmap.ic_launcher : R.mipmap.ic_launcher;
    }


}

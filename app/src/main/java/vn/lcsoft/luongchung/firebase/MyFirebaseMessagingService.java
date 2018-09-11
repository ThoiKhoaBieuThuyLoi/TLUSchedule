package vn.lcsoft.luongchung.firebase;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import vn.lcsoft.luongchung.tluschedule.R;
import vn.lcsoft.luongchung.tluschedule.Thongbao;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "Firebase_xem_tin";
    private String urlImage="https://raw.githubusercontent.com/luongchung/luongchung.github.io/master/images/wru.jpg";
    private String kieu="Image";
    private String html="<html> Không có thông báo !</html>";
    private String link="https://luongchung.github.io";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage){
        if(remoteMessage.getData().size() > 0) {
            urlImage=remoteMessage.getData().get("Image");
            kieu=remoteMessage.getData().get("Kieu");
            html=remoteMessage.getData().get("Html");
            link=remoteMessage.getData().get("Link");
        }
        //Check if the message contains notification
        if(remoteMessage.getNotification() != null) {
            sendNotification(remoteMessage.getNotification().getBody(),remoteMessage.getNotification().getTitle());
        }
    }
    private void sendNotification(String body, String ti) {
        Intent intent = new Intent(this, Thongbao.class);
        intent.putExtra("kieu",kieu);
        intent.putExtra("html",html);
        intent.putExtra("link",link);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri notificationSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notifiBuilder = null;
        try {
            notifiBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentTitle(body)
                    .setContentText(ti)
                    .setAutoCancel(true)
                    .setSound(notificationSound)
                    .setContentIntent(pendingIntent)
                    .setStyle(new NotificationCompat.BigPictureStyle()
                            .bigPicture(Picasso.with(this).load(urlImage).get())
                    );
        } catch (IOException e) {
            e.printStackTrace();
        }
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0 /*ID of notification*/, notifiBuilder.build());
    }
}

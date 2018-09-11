package vn.lcsoft.luongchung.tluschedule;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.login.widget.ProfilePictureView;
import com.squareup.picasso.Picasso;

public class InfoTacGia extends AppCompatActivity {
    LinearLayout btnFace,btnGithub,btnCall,btnGmail;
    ImageView avatar;
    TextView txtMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_tac_gia);
        addControlls();
        addEvents();
    }

    private void addEvents() {
        btnFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/100004154587803"));
                    startActivity(intent);
                } catch(Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com/L.u.o.n.g.C.h.u.n.g.W.R.U")));
                }
            }
        });
        btnGithub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW,    Uri.parse("http://github.com/luongchung")));
            }
        });
        btnGmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent=new Intent(Intent.ACTION_SEND);
                    String[] recipients={"chunglv42@wru.vn"};
                    intent.putExtra(Intent.EXTRA_EMAIL, recipients);
                    intent.putExtra(Intent.EXTRA_SUBJECT,"ĐÓNG GÓP Ý KIẾN APP TKB THỦY LỢI");
                    intent.putExtra(Intent.EXTRA_TEXT,"Mời điền nội dung...");
                    intent.setType("text/html");
                    intent.setPackage("com.google.android.gm");
                    startActivity(Intent.createChooser(intent, "Send mail"));
                }catch (Exception ex){

                }
            }
        });
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = "+8401234443401";
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);
            }
        });
        String userID="100004154587803";
        Picasso.with(this)
                .load("https://graph.facebook.com/" + userID+ "/picture?type=large")
                .into(avatar);
    }

    private void addControlls() {
        txtMain=findViewById(R.id.txtTenTacgia);
        btnFace=findViewById(R.id.btnFaceBook);
        btnGithub=findViewById(R.id.btnGithub);
        btnGmail=findViewById(R.id.btnGmail);
        btnCall=findViewById(R.id.btnCall);
        avatar=findViewById(R.id.id_avatarUser);
        Typeface font = Typeface.createFromAsset(this.getAssets(),"fonts/fontmain.ttf");
        txtMain.setTypeface(font);
    }
}

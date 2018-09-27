package vn.lcsoft.luongchung.tluschedule;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.widget.ProfilePictureView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.squareup.picasso.Picasso;

import vn.lcsoft.luongchung.models.Phanhoi;

public class InfoTacGia extends AppCompatActivity {
    private String userID="100004154587803";
    DatabaseReference mDatabase;
    LinearLayout btnFace,btnGithub,btnCall,btnGmail;
    ImageView avatar;
    TextView txtMain,txtNgPH,txtNDPH;
    Button btnPhanHoi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_tac_gia);
        addControlls();
        addEvents();
    }

    private void addEvents() {
        btnPhanHoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtNgPH.getText().length()==0){
                    Toast.makeText(InfoTacGia.this, "Bạn chưa nhập tên bạn!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(txtNDPH.getText().length()==0){
                    Toast.makeText(InfoTacGia.this, "Bạn chưa nhập nội dung!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Phanhoi phanhoi = new Phanhoi(FirebaseInstanceId.getInstance().getToken(),txtNgPH.getText().toString(),txtNDPH.getText().toString());
                txtNgPH.setText("");
                txtNDPH.setText("");
                try {
                    mDatabase.child("Phanhoi").push().setValue(phanhoi);
                    Toast.makeText(InfoTacGia.this, "Gửi phản hồi thành công!", Toast.LENGTH_SHORT).show();
                    finish();
                }catch (Exception ex){
                    Toast.makeText(InfoTacGia.this, "Gửi thất bại! Vui lòng kiểm tra lại...", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
                String phone = "0834443401";
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);
            }
        });

        Picasso.with(this)
                .load("https://graph.facebook.com/" + userID+ "/picture?type=large")
                .into(avatar);
    }

    private void addControlls() {
        txtNDPH=findViewById(R.id.txtNDphanhoi);
        txtNgPH=findViewById(R.id.txtTenNgPhanHoi);
        btnPhanHoi=findViewById(R.id.btnPhanHoi);
        txtMain=findViewById(R.id.txtTenTacgia);
        btnFace=findViewById(R.id.btnFaceBook);
        btnGithub=findViewById(R.id.btnGithub);
        btnGmail=findViewById(R.id.btnGmail);
        btnCall=findViewById(R.id.btnCall);
        avatar=findViewById(R.id.id_avatarUser);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Typeface font = Typeface.createFromAsset(this.getAssets(),"fonts/fontmain.ttf");
        txtMain.setTypeface(font);
    }
}

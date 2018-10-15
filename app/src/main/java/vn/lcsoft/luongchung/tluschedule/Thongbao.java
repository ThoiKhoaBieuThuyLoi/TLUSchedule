package vn.lcsoft.luongchung.tluschedule;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

public class Thongbao extends AppCompatActivity {
    WebView wv_Thongbao;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongbao);
        addControls();
    }
    private void addControls() {
        intent=getIntent();
        wv_Thongbao=findViewById(R.id.wv_thongbao);
        xuLyDuLieu(intent);
    }
    private void xuLyDuLieu(Intent intent) {
        TextView txtTB=findViewById(R.id.txtThongBao);
        Typeface font = Typeface.createFromAsset(this.getAssets(),"fonts/fontmain.ttf");
        txtTB.setTypeface(font);

        String tmp=intent.getStringExtra("kieu");
        String link=intent.getStringExtra("link");
        String html=intent.getStringExtra("html");
        if(tmp.equals("Html")&& html!=null){
            wv_Thongbao.loadData(html, "text/html; charset=UTF-8", null);
        }
        if(tmp.equals("Link") && link!=null){
            wv_Thongbao.loadUrl(link);
        }
    }
}
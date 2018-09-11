package vn.lcsoft.luongchung.tluschedule;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class HuongDan extends AppCompatActivity {
    TextView hd,hd1,hd2,hd3;
    WebView mWebView;
    Button btnYoutube;
    private String videoID="PG3Sjy2x02M";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huong_dan);
        mWebView=findViewById(R.id.webview1);
        hd=findViewById(R.id.hd);
        hd1=findViewById(R.id.hd1);
        hd2=findViewById(R.id.hd2);
        hd3=findViewById(R.id.hd3);
        Typeface font = Typeface.createFromAsset(this.getAssets(),"fonts/fontmain.ttf");
        hd.setTypeface(font);
        hd1.setTypeface(font);
        hd2.setTypeface(font);
        hd3.setTypeface(font);
        btnYoutube=findViewById(R.id.btnYoutube);
        btnYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.getSettings().setJavaScriptEnabled(true);
                mWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
                mWebView.loadUrl("http://www.youtube.com/embed/" + videoID + "?autoplay=1&vq=small");
                mWebView.setWebChromeClient(new WebChromeClient());
            }
        });


    }
}

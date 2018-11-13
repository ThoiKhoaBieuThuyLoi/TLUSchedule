package vn.lcsoft.luongchung.tluschedule;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import dmax.dialog.SpotsDialog;
import vn.lcsoft.luongchung.adapters.AdapterTinTuc;
import vn.lcsoft.luongchung.models.TinTuc_m;

public class TinTuc extends AppCompatActivity {
    AlertDialog dialog;
    TextView txtMainTinTuc;
    String Link;
    AdapterTinTuc adapterTinTuc;
    ListView lv_TinTuc;
    ArrayList<TinTuc_m> arrTinTucM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tin_tuc);
        Typeface font = Typeface.createFromAsset(this.getAssets(),"fonts/fontmain.ttf");
        txtMainTinTuc=findViewById(R.id.txtMainTintuc);
        txtMainTinTuc.setTypeface(font);
        lv_TinTuc= findViewById(R.id.lv_TinTuc);
        arrTinTucM = new ArrayList<>();
        dialog = new SpotsDialog(TinTuc.this,R.style.Custom);
        dialog.setCanceledOnTouchOutside(false);
        Link=(getString(R.string.linktintuc));
        new GetData().execute();
    }
    @SuppressLint("StaticFieldLeak")
    public class GetData extends AsyncTask<Void, Void, Void> {

        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            try { DocTinTuc(); }
            catch (Exception ignored){ }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try {
                adapterTinTuc = new AdapterTinTuc(TinTuc.this, R.layout.item_tintuc, arrTinTucM);
                lv_TinTuc.setAdapter(adapterTinTuc);
                dialog.dismiss();
            }catch (Exception ignored){ }
        }
    }
    private void DocTinTuc() {
        Document doc;
        try {
            doc = Jsoup.connect(Link).timeout(5*1000).get();
            Elements elements = doc.select("div.related");
            for (int i = 0; i < elements.size(); i++) {
                Element e = elements.get(i);
                Elements a = e.select("figure.figure img");
                String linkHinh = a.attr("src"); //link hình ảnh
                if (linkHinh.charAt(0)=='/')
                {
                    linkHinh=getString(R.string.linkhinh)+linkHinh;
                }
                Elements a1 = e.select("h3.related-title a");
                String urlLink = a1.attr("href"); //link click
                String TieuDe=a1.text(); //Tiêu đề
                Elements a2 = e.select("span.ChannelTeaserDesc");
                String TomTat = a2.text(); //Tóm tắt
                Log.d("tb_app", "Tóm tắt"+TomTat);
                Log.d("tb_app", "Link Hình"+linkHinh);
                Log.d("tb_app", "Link click"+urlLink);
                TinTuc_m tinTucM =new TinTuc_m(TieuDe,TomTat,linkHinh,urlLink);
                arrTinTucM.add(tinTucM);

            }
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}

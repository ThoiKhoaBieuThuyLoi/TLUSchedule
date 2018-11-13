package vn.lcsoft.luongchung.tluschedule;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import vn.lcsoft.luongchung.models.Lich_PhanMang;
import vn.lcsoft.luongchung.models.MonHoc;


public class ThemTuDong extends AppCompatActivity {
    String linkMain="http://dangky.tlu.edu.vn";
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    String txtHtml,tmpURL="";
    WebView webview;
    AlertDialog dialog;
    Dialog dialog_custom;
    String DATABASE_NAME="dbthoikhoabieu.sqlite";
    SQLiteDatabase sqLiteDatabase=null;
    BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_tu_dong);
        try {
            addControls();
        } catch (ParseException e) {e.printStackTrace(); }
        addEvents();
    }
    private void addEvents() {

        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                dialog.dismiss();
                tmpURL=url;
                webview.loadUrl("javascript:window.HtmlViewer.showHTML" +
                        "('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');");
            }
        });
        dialog.show();
        webview.loadUrl(linkMain);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                SharedPreferences sharedPreferences= getSharedPreferences(getString(R.string.luuURL),MODE_PRIVATE);
                String url=sharedPreferences.getString("URL",getString(R.string.linkdangky));
                if (item.getItemId() == R.id.btnHuongDan) {
                        Intent intentHuongDan=new Intent(ThemTuDong.this,HuongDan.class);
                        startActivity(intentHuongDan);
                }
                if(item.getItemId() == R.id.btnSuaLink) {
                    Intent intentChangeLink=new Intent(ThemTuDong.this,ChangeLink.class);
                    startActivity(intentChangeLink);
                }
                if(item.getItemId() == R.id.loadlai) {
                    dialog.show();
                    webview.loadUrl(url);
                }
                if(item.getItemId() == R.id.btnNhapDL)
                {
                    nhapdulieu();
                }
                return false;
            }
        });
    }


    private void nhapdulieu() {
        new AlertDialog.Builder(ThemTuDong.this).setTitle("CHÚ Ý")
                .setMessage(getString(R.string.hoi))
                .setPositiveButton("có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Delete_Data_old();
                        try {
                            xulyImport();
                        } catch (ParseException e) {
                            Toast.makeText(ThemTuDong.this, "Lỗi nhập dữ liệu.", Toast.LENGTH_LONG).show();
                        }
                        Intent intent=new Intent(ThemTuDong.this,MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }).setNegativeButton("không",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            xulyImport();
                        } catch (ParseException e) {
                            Toast.makeText(ThemTuDong.this, "Lỗi nhập dữ liệu.", Toast.LENGTH_LONG).show();
                        }
                        Intent intent=new Intent(ThemTuDong.this,MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }
        ).show();
    }

    private void Delete_Data_old() {
        //xóa hết dữ liệu cũ trong database
        try
        {
            sqLiteDatabase=openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
            sqLiteDatabase.execSQL("DELETE FROM tbthoikhoabieu");
            sqLiteDatabase.execSQL("DELETE FROM tbimage");
            sqLiteDatabase.close();
        }
        catch (Exception ignored) { }
    }
    private void xulyImport() throws ParseException {
        try
        {
            sqLiteDatabase=openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
            ContentValues row_sql;
            ArrayList<MonHoc> arrMonHoc=new ArrayList<>(); //Mảng chứa các môn học được bóc tách.
            ArrayList<Lich_PhanMang> arrtam=new ArrayList<>();
            //<editor-fold desc="Bóc tách dữ liệu trang">
            Document doc= Jsoup.parse(txtHtml).clone();
            Elements elements = doc.select("td.tableborder tr.cssListItem");
            Elements elements1 = doc.select("td.tableborder tr.cssListAlternativeItem");
            for (int i = 0; i < elements.size(); i++) {
                Element e = elements.get(i);
                Elements row=e.select("td");
                String _TenLop =row.get(1).text();
                String _TenHocPhan=row.get(2).text();
                String _ThoiGian=row.get(3).text();
                String _Diadiem=row.get(4).text();
                String _SoTinChi=row.get(7).text();
                MonHoc monHoc=new MonHoc(_TenLop,_TenHocPhan,_ThoiGian,_Diadiem,_SoTinChi,"");
                arrMonHoc.add(monHoc);
            }
            for (int i = 0; i < elements1.size(); i++) {
                Element e = elements1.get(i);
                Elements row=e.select("td");
                String _TenLop =row.get(1).text();
                String _TenHocPhan=row.get(2).text();
                String _ThoiGian=row.get(3).text();
                String _Diadiem=row.get(4).text();
                String _SoTinChi=row.get(7).text();
                MonHoc monHoc=new MonHoc(_TenLop,_TenHocPhan,_ThoiGian,_Diadiem,_SoTinChi,"");
                arrMonHoc.add(monHoc);
            }
            //</editor-fold>
            //<editor-fold desc="Lấy dữ liệu phân mảnh vào mảng tạm">
            for (int i=0;i<arrMonHoc.size();i++) {
                MonHoc monHoc =arrMonHoc.get(i);
                String TenMonHoc=monHoc.getTenLop();
                String TenLop=monHoc.getTenHocPhan();
                String DiaDiem=monHoc.getDiaDiems();
                String GiangVien=monHoc.getTenGiangVien();
                String SoTinChi=monHoc.getSoTinChi();
                for (int j=0;j<arrMonHoc.get(i).getThoiGians().size();j++)
                {

                    String NgayBD=arrMonHoc.get(i).getThoiGians().get(j).getNgayBD();
                    String NgayKT=arrMonHoc.get(i).getThoiGians().get(j).getNgayKT();
                    for (int k=0;k<arrMonHoc.get(i).getThoiGians().get(j).getThuHocs().size();k++) {
                        String ThuHoc=arrMonHoc.get(i).getThoiGians().get(j).getThuHocs().get(k).getThu();
                        String TietBD=arrMonHoc.get(i).getThoiGians().get(j).getThuHocs().get(k).getTietBD();
                        String TietKT=arrMonHoc.get(i).getThoiGians().get(j).getThuHocs().get(k).getTietKT();
                        Date ngaybd=formatter.parse(NgayBD);
                        Date ngaykt =formatter.parse(NgayKT);
                        Lich_PhanMang a=new Lich_PhanMang(1,TenMonHoc,TenLop,DiaDiem,GiangVien,ngaybd,ngaykt,ThuHoc,TietBD,TietKT,SoTinChi);
                        arrtam.add(a);
                    }
                }
            }
            //</editor-fold>
            //<editor-fold desc="Loop từ ngày bắt đầu đến ngày kết thúc môn">
            Calendar cal = Calendar.getInstance();
            for (int i=0;i<arrtam.size();i++) //lặp từng môn phân mảnh
            {
                Calendar c = Calendar.getInstance();
                Calendar kt = Calendar.getInstance();
                kt.setTime(arrtam.get(i).getNgayKetThuc());
                kt.add(Calendar.DATE, 1);
                c.setTime(arrtam.get(i).getNgayBatDau());
                while (c.getTime().before(kt.getTime()))
                {
                    cal.setTime(c.getTime());
                    int thu=cal.get(Calendar.DAY_OF_WEEK);
                    if(thu== Integer.parseInt(arrtam.get(i).getThuHoc().trim()))
                    {
                        row_sql = new ContentValues();
                        row_sql.put("TenMonHoc",arrtam.get(i).getTenMonHoc()+"");
                        row_sql.put("TenLopTinChi",arrtam.get(i).getTenLopTinChi()+"" );
                        row_sql.put("DiaDiem",arrtam.get(i).getDiaDiem()+"" );
                        row_sql.put("GiangVien",arrtam.get(i).getGiangVien()+"" );
                        row_sql.put("SoTinChi",arrtam.get(i).getSoTinChi()+"");
                        row_sql.put("NgayHoc",formatter.format(c.getTime()));
                        row_sql.put("ThuHoc",arrtam.get(i).getThuHoc());
                        row_sql.put("TietBatDau",arrtam.get(i).getTietBatDau());
                        row_sql.put("TietKetThuc",arrtam.get(i).getTietKetThuc());
                        sqLiteDatabase.insert("tbthoikhoabieu", null, row_sql);
                    }
                    c.add(Calendar.DATE, 1);
                }
            }
        }
        catch (Exception ignored)
        {}
    }
    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    private void addControls() throws ParseException {
        SharedPreferences sharedPreferences= getSharedPreferences(getString(R.string.luuURL),MODE_PRIVATE);
        linkMain= sharedPreferences.getString("URL",getString(R.string.linkdangky));
        dialog = new ProgressDialog(this, AlertDialog.THEME_DEVICE_DEFAULT_DARK);
        dialog_custom =new Dialog(ThemTuDong.this);
        navigationView=findViewById(R.id.navigation);
        BottomNavigationHelper.disableShiftMode(navigationView);
        webview =findViewById(R.id.wv_TuDong);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setSupportZoom(true);
        webview.addJavascriptInterface(this, "HtmlViewer");
    }
    @JavascriptInterface
    public void showHTML(String html) {
        txtHtml=html;
    }
}

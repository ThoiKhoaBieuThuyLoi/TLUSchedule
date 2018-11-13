package vn.lcsoft.luongchung.tluschedule;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import vn.lcsoft.luongchung.adapters.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private InterstitialAd mInterstitialAd;
    private LinearLayout btn1,btn2,btn3,btn4,btn5;
    private String DATABASE_NAME="dbthoikhoabieu.sqlite";
    private String DB_PATH="/databases/";
    private Button btnHDdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        events();
        setupTabPaper();
        addthoigiantiethoc();
        xuLySaoChepSQLite();
    }
    private void events() {
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,ThemTuDong.class);
                startActivity(intent);

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,TinTuc.class);
                startActivity(intent);
              //  Toast.makeText(MainActivity.this, "Chức năng này đang bảo trì !", Toast.LENGTH_SHORT).show();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,LoginChat.class);
                startActivity(intent);
               // Toast.makeText(MainActivity.this, "Chức năng này đang bảo trì !", Toast.LENGTH_SHORT).show();

            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Tính năng này sắm ra mắt ! \n Đang phát triển....", Toast.LENGTH_SHORT).show();
//                Intent intent =new Intent(MainActivity.this,InfoTacGia.class);
//                startActivity(intent);

            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(MainActivity.this, "Chức năng này đang bảo trì !", Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(MainActivity.this,InfoTacGia.class);
                startActivity(intent);

            }
        });
        btnHDdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,HuongDan.class);
                startActivity(intent);
            }
        });
    }
    private void init() {
//        FacebookSdk.sdkInitialize(getApplicationContext());
        TextView txtNameMain = findViewById(R.id.txtNameMain);
        btnHDdd= findViewById(R.id.btnHDdd);
        TextView txt1 = findViewById(R.id.txt1);
        TextView txt2 = findViewById(R.id.txt2);
        TextView txt3 = findViewById(R.id.txt3);
        TextView txt4 = findViewById(R.id.txt4);
        TextView txt5 = findViewById(R.id.txt5);

        btn1= findViewById(R.id.btn1);
        btn2= findViewById(R.id.btn2);
        btn3= findViewById(R.id.btn3);
        btn4= findViewById(R.id.btn4);
        btn5= findViewById(R.id.btn5);

        Typeface font = Typeface.createFromAsset(this.getAssets(),"fonts/fontmain.ttf");
        txtNameMain.setTypeface(font);
        txt1.setTypeface(font);
        txt2.setTypeface(font);
        txt3.setTypeface(font);
        txt4.setTypeface(font);
        txt5.setTypeface(font);

        quangcao();
    }


    private void quangcao() {
        MobileAds.initialize(MainActivity.this, "ca-app-pub-5001443737686857~4542932552");
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {

            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the interstitial ad is closed.
            }
        });
        mInterstitialAd.setAdUnitId("ca-app-pub-5001443737686857/7029495090");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

    }
    private void setupTabPaper() {

        TabLayout tabLayout = findViewById(R.id.tablayout);
        ViewPager viewPager = findViewById(R.id.viewpager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragments(new FragmentToday(), "HÔM NAY");
        viewPagerAdapter.addFragments(new FragmentAll(), "TẤT CẢ");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }
    private void addthoigiantiethoc() {
        String luuThoiGianTietHoc = "Chuaco";
        SharedPreferences sharedPreferences= getSharedPreferences(luuThoiGianTietHoc,MODE_PRIVATE);
        Boolean kt=sharedPreferences.getBoolean("landau",true);
        if(kt)
        {
            SharedPreferences.Editor editor=sharedPreferences.edit();
            String []arrTG =getResources().getStringArray(R.array.ThoiGianTiet);
            for (int i=1;i<=arrTG.length;i++)
            {
                editor.putString(String.valueOf(i),arrTG[i-1]);
            }
            editor.putBoolean("landau",false);
            editor.apply();
        }
    }
    private void xuLySaoChepSQLite() {
        File dbfile= getDatabasePath(DATABASE_NAME);
        if (!dbfile.exists())
        {
            try
            {
                saoChepDatabaseTuAsset();
            }
            catch (Exception ex)
            {
                Toast.makeText(this,ex.toString(),Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void saoChepDatabaseTuAsset() {
        try {
            // Load database từ assets
            InputStream myInput = getAssets().open(DATABASE_NAME);
            // Đường dẫn tới file database
            String outFileName = layDuongDanLuuTru();

            File f= new File(getApplicationInfo().dataDir+DB_PATH);
            if(!f.exists())
            {
                f.mkdir();//chưa có đường dẫn thì tạo đường dẫn database
            }
            // Tạo một outputstream theo kiểu file
            OutputStream myOutput = new FileOutputStream(outFileName);
            //chuyển các byte từ input sang output
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer))>0){
                myOutput.write(buffer, 0, length);
            }
            //Close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();
           // Toast.makeText(MainActivity.this,"Sao chép thành công!",Toast.LENGTH_LONG).show();
        }
        catch (Exception ex)
        {
            Toast.makeText(MainActivity.this,"Lỗi sao chép database",Toast.LENGTH_LONG).show();
        }
    }
    private String layDuongDanLuuTru() {
        return getApplicationInfo().dataDir+DB_PATH+DATABASE_NAME;
    }

    @Override
    public void onBackPressed() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            super.onBackPressed();
        }
    }

}

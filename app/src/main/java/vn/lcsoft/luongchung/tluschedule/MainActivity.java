package vn.lcsoft.luongchung.tluschedule;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import vn.lcsoft.luongchung.adapters.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView txtNameMain;
    private ViewPagerAdapter viewPagerAdapter;
    private TextView txt1,txt2,txt3,txt4;
    private LinearLayout btn1,btn2,btn3,btn4;
    private String LuuThoiGianTietHoc="Chuaco";
    private SQLiteDatabase sqLiteDatabase=null;
    private String DATABASE_NAME="dbthoikhoabieu.sqlite";
    private String DB_PATH="/databases/";
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
               // Intent intent =new Intent(MainActivity.this,InfoTacGia.class);
               // startActivity(intent);
                Toast.makeText(MainActivity.this, "Chức năng này đang bảo trì !", Toast.LENGTH_SHORT).show();

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
    }
    private void init() {
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
       // String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        //Log.d("LUONGCHUNGTEST", "New Token: " + refreshedToken);
        FacebookSdk.sdkInitialize(getApplicationContext());
        txtNameMain=  findViewById(R.id.txtNameMain);
        txt1= findViewById(R.id.txt1);
        txt2= findViewById(R.id.txt2);
        txt3= findViewById(R.id.txt3);
        txt4= findViewById(R.id.txt4);

        btn1= findViewById(R.id.btn1);
        btn2= findViewById(R.id.btn2);
        btn3= findViewById(R.id.btn3);
        btn4= findViewById(R.id.btn4);

        Typeface font = Typeface.createFromAsset(this.getAssets(),"fonts/fontmain.ttf");
        txtNameMain.setTypeface(font);
        txt1.setTypeface(font);
        txt2.setTypeface(font);
        txt3.setTypeface(font);
        txt4.setTypeface(font);
    }
    private void setupTabPaper() {

        tabLayout =  findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragments(new FragmentToday(), "HÔM NAY");
        viewPagerAdapter.addFragments(new FragmentAll(), "TẤT CẢ");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);



        Typeface fontTypeFace= Typeface.createFromAsset(getApplication().getAssets(),
                "fonts/fontmain.ttf");


    }
    private void addthoigiantiethoc() {
        SharedPreferences sharedPreferences= getSharedPreferences(LuuThoiGianTietHoc,MODE_PRIVATE);
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
            editor.commit();
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
}

package vn.lcsoft.luongchung.tluschedule;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ChiTietLichHoc extends AppCompatActivity {
    private TextView TenMon, TenLopTC, GiangVien, DiaDiem, TietHoc, ThoiGian, SoTC;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_lich_hoc);
        addControls();
    }

    @SuppressLint("SetTextI18n")
    private void addControls() {
        Typeface font = Typeface.createFromAsset(this.getAssets(), "fonts/fontmain.ttf");
        TextView txtMain = findViewById(R.id.txt_main);
        txtMain.setTypeface(font);
        TenMon = findViewById(R.id.id_tenmon);
        TenLopTC =  findViewById(R.id.id_tenloptinchi);
        GiangVien =  findViewById(R.id.id_giangvien);
        DiaDiem =  findViewById(R.id.id_diadiem);
        ThoiGian =  findViewById(R.id.id_thoigianhoc);
        SoTC = findViewById(R.id.id_sotinchi);
        TietHoc =  findViewById(R.id.id_tiethoc);
        intent = getIntent();
        TenMon.setText(intent.getStringExtra("TenMonHoc"));
        TenLopTC.setText("Tên lớp tín chỉ: " + intent.getStringExtra("TenLopTC"));
        DiaDiem.setText("Địa điểm: " + intent.getStringExtra("DiaDiem"));
        GiangVien.setText("Giảng viên: " + intent.getStringExtra("TenGV"));
        SoTC.setText("Số tín chỉ: " + intent.getStringExtra("SoTC"));
        TietHoc.setText("Tiết học: " + intent.getStringExtra("TietBD") + "-->" + intent.getStringExtra("TietKT"));
        ThoiGian.setText("Ngày học: " + intent.getStringExtra("NgayHoc"));
    }
}
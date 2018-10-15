package vn.lcsoft.luongchung.tluschedule;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ChangeLink extends AppCompatActivity {
    Button btnChangeLink,btnBanDau;
    TextView txtLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_link);
        txtLink=findViewById(R.id.txturldangky);
        btnChangeLink=findViewById(R.id.btnLuuURL);
        btnBanDau=findViewById(R.id.btnDatLai);

        btnChangeLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences= getSharedPreferences(getString(R.string.luuURL),MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("URL",txtLink.getText().toString());
                editor.commit();
                String url=sharedPreferences.getString("URL",getString(R.string.linkdangky));
                Toast.makeText(ChangeLink.this, "Dổi link thành công! " +url, Toast.LENGTH_LONG).show();
                finish();
            }
        });
        btnBanDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences= getSharedPreferences(getString(R.string.luuURL),MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("URL",getString(R.string.linkdangky));
                editor.commit();
                String url=sharedPreferences.getString("URL",getString(R.string.linkdangky));
                Toast.makeText(ChangeLink.this, "Đã đặt lại ban đầu! " +url, Toast.LENGTH_LONG).show();

            }
        });
    }
}

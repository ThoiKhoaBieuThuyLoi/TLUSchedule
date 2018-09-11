package vn.lcsoft.luongchung.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.List;

import vn.lcsoft.luongchung.models.lich_chuan;
import vn.lcsoft.luongchung.tluschedule.ChiTietLichHoc;
import vn.lcsoft.luongchung.tluschedule.R;

/**
 * Created by LUONG CHUNG on 7/7/2017.
 */

public class AdapterLichHoc extends ArrayAdapter<lich_chuan> {
    String LuuThoiGianTietHoc="Chuaco";
    Activity context;
    int resource;
    List<lich_chuan> objects;
    public AdapterLichHoc(Activity context, int resource, List<lich_chuan> objects) {
        super(context, resource, objects);
        this.context=context;
        this.objects=objects;
        this.resource=resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=this.context.getLayoutInflater();
        View view=layoutInflater.inflate(this.resource,null);
        SharedPreferences sharedPreferences= getContext().getSharedPreferences(LuuThoiGianTietHoc, Context.MODE_PRIVATE);

        TextView txt_Thoigian= view.findViewById(R.id.txt_thoigian);
        TextView txt_tenmonhoc= view.findViewById(R.id.txt_tenmonhoc);
        TextView txt_diadiem= view.findViewById(R.id.txt_diadiem);

        final lich_chuan lichHomNay=objects.get(position);
        String kt=sharedPreferences.getString(lichHomNay.getTietBatDau(),"");
        txt_Thoigian.setText(kt);
        txt_tenmonhoc.setText(lichHomNay.getTenMonHoc());
        txt_diadiem.setText(lichHomNay.getDiaDiem());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xulyclickitem(lichHomNay);
            }
        });
        return view;
    }

    private void xulyclickitem(lich_chuan lichHomNay) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
       // Toast.makeText(context.getApplication(),"thoi gian"+simpleDateFormat.format(lichHomNay.getNgay()),Toast.LENGTH_LONG).show();
        Intent intent = new Intent(context,ChiTietLichHoc.class);
        intent.putExtra("ID", lichHomNay.getId());
        intent.putExtra("TenGV", lichHomNay.getGiangVien());
        intent.putExtra("TietKT", lichHomNay.getTietKetThuc());
        intent.putExtra("SoTC", lichHomNay.getSoTinChi());
        intent.putExtra("TenLopTC", lichHomNay.getTenLopTinChi());
        intent.putExtra("DiaDiem", lichHomNay.getDiaDiem());
        intent.putExtra("NgayHoc", simpleDateFormat.format(lichHomNay.getNgay()));
        intent.putExtra("TenMonHoc", lichHomNay.getTenMonHoc());
        intent.putExtra("ThuHoc", lichHomNay.getThuHoc());
        intent.putExtra("TietBD", lichHomNay.getTietBatDau());
        intent.putExtra("Note", lichHomNay.getNote());

        context.startActivityForResult(intent, 1996);

    }
}

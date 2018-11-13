package vn.lcsoft.luongchung.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;


import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import vn.lcsoft.luongchung.models.lich_chuan;
import vn.lcsoft.luongchung.tluschedule.ChiTietLichHoc;
import vn.lcsoft.luongchung.tluschedule.R;

/**
 * Created by LUONG CHUNG on 7/9/2017.
 */

public class AdapterEx extends BaseExpandableListAdapter {
    private Activity context;
    private List<String> Me;
    private HashMap<String,List<lich_chuan>> Con;

    public AdapterEx(Activity context, List<String> me, HashMap<String, List<lich_chuan>> con) {
        this.context = context;
        Me = me;
        Con = con;
    }

    @Override
    public int getGroupCount() {
        return Me.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return Con.get(Me.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return Me.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return Con.get(Me.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert layoutInflater != null;
        view=layoutInflater.inflate(R.layout.item_group,null);
        TextView txt_TenMe= view.findViewById(R.id.txt_Me);
        String ten= (String) getGroup(i);
        txt_TenMe.setText(ten);
        if (getChildrenCount(i) == 0) {
            view.setVisibility(View.INVISIBLE);
        }
        return view;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert layoutInflater != null;
        view=layoutInflater.inflate(R.layout.item_lichhoc,null);
        final lich_chuan lc= (lich_chuan) getChild(i,i1);
        String luuThoiGianTietHoc = "Chuaco";
        SharedPreferences sharedPreferences= view.getContext().getSharedPreferences(luuThoiGianTietHoc, Context.MODE_PRIVATE);
        TextView txt_TenMonHoc=  view.findViewById(R.id.txt_tenmonhoc);
        TextView txt_Diadiem=  view.findViewById(R.id.txt_diadiem);
        TextView txt_thoigian=  view.findViewById(R.id.txt_thoigian);
        txt_TenMonHoc.setText(lc.getTenMonHoc());
        txt_Diadiem.setText(lc.getDiaDiem());
        String kt=sharedPreferences.getString(lc.getTietBatDau(),"");
        txt_thoigian.setText(kt);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xulyclickitem(lc);

            }
        });
        return view;
    }

    private void xulyclickitem(lich_chuan lichHomNay) {
        Intent intent = new Intent(this.context,ChiTietLichHoc.class);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
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
        context.startActivityForResult(intent, 1997);
    }
    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}

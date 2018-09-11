package vn.lcsoft.luongchung.tluschedule;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.baoyz.widget.PullRefreshLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import dmax.dialog.SpotsDialog;
import vn.lcsoft.luongchung.adapters.AdapterLichHoc;
import vn.lcsoft.luongchung.models.lich_chuan;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentToday extends Fragment implements Comparator<lich_chuan> {


    private String TAG="LUONGCHUNG_CHECK";
    private AlertDialog dialog;
    private ArrayList<lich_chuan> arrLich_HomNay;
    private ListView listView;
    private RelativeLayout rl;
    private AdapterLichHoc adapterLichHoc;
    private String DATABASE_NAME="dbthoikhoabieu.sqlite";
    private SQLiteDatabase sqLiteDatabase=null;
    private PullRefreshLayout layoutRefresh;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_fragment_today, container, false);
        listView=view.findViewById(R.id.lv_lichhomnay);
        rl=view.findViewById(R.id.bk_image);
        dialog = new SpotsDialog(getActivity(),R.style.Custom);
        dialog.setCanceledOnTouchOutside(false);
        layoutRefresh = view.findViewById(R.id.swipeRefreshLayoutNoiBat);
        layoutRefresh.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                arrLich_HomNay.clear();
                new GetData_all().execute();
                layoutRefresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        layoutRefresh.setRefreshing(false);
                    }
                },2000);

            }

        });
        arrLich_HomNay=new ArrayList<>();
        new GetData_all().execute();
        return view;
    }
    @Override
    public int compare(lich_chuan lich_chuan, lich_chuan t1) {
        if (Integer.parseInt(lich_chuan.getTietBatDau())>Integer.parseInt(t1.getTietBatDau()))
            return 1;
        else if(Integer.parseInt(lich_chuan.getTietBatDau())==Integer.parseInt(t1.getTietBatDau()))
            return 0;
        else
            return -1;
    }
    public class GetData_all extends AsyncTask<Void, Void, Void> {
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            getData_all();
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (arrLich_HomNay.size()==0) rl.setBackgroundResource(R.drawable.nghihoc);
            else rl.setBackgroundResource(0);
            adapterLichHoc=new AdapterLichHoc(getActivity(),R.layout.item_lichhoc,arrLich_HomNay);
            listView.setAdapter(adapterLichHoc);
            dialog.dismiss();

        }
    }
    private void getData_all() {
        try
        {
            Date date1=new Date();
            arrLich_HomNay.clear();
            sqLiteDatabase=getActivity().openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE,null);
            String sql="SELECT * FROM tbthoikhoabieu";
            Cursor cursor=sqLiteDatabase.rawQuery(sql,null);

            SimpleDateFormat sf= new SimpleDateFormat("dd/MM/yyyy");
            Calendar cal = Calendar.getInstance();
            int thu=cal.get(Calendar.DAY_OF_WEEK);
            Date ngayhomnay=cal.getTime();
            while (cursor.moveToNext())
            {
                try {
                    date1= sf.parse(cursor.getString(5));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (sf.format(ngayhomnay).equals(sf.format(date1)) && thu==Integer.parseInt(cursor.getString(6)))
                {

                    Log.d(TAG,"Ngày hôm nay"+sf.format(ngayhomnay) + "Ngay lịch học:" +sf.format(date1));
                    Log.d(TAG, "ID ==: "+cursor.getString(0));
                    Log.d(TAG, "Tên Mon Hoc ==: "+cursor.getString(1));
                    Log.d(TAG, "Tên lớp tín chỉ ==: "+cursor.getString(2));
                    Log.d(TAG, "Địa điểm"+cursor.getString(3));
                    Log.d(TAG, "Giảng Viên: =="+cursor.getString(4));
                    Log.d(TAG, "Ngày học==: "+cursor.getString(5));
                    Log.d(TAG, "Thứ học==: "+cursor.getString(6));
                    Log.d(TAG, "Tiết bắt đầu==: "+cursor.getString(7));
                    Log.d(TAG, "Tiết kết thúc: =="+cursor.getString(8));
                    Log.d(TAG, "Số tín chỉ:== "+cursor.getString(9));
                    Log.d(TAG, "=============================================");
                    arrLich_HomNay.add(new lich_chuan(Integer.parseInt(
                            cursor.getString(0)),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4), date1,
                            cursor.getString(6),
                            cursor.getString(7),
                            cursor.getString(8),
                            cursor.getString(9),
                            cursor.getString(10)));
                }

            }
            Collections.sort(arrLich_HomNay,new FragmentToday());
            cursor.close();
        }
        catch (Exception ignored){}
    }

}

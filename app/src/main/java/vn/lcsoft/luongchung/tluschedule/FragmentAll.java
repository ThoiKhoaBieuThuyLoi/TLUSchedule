package vn.lcsoft.luongchung.tluschedule;


import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;

import com.baoyz.widget.PullRefreshLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import dmax.dialog.SpotsDialog;
import vn.lcsoft.luongchung.adapters.AdapterEx;
import vn.lcsoft.luongchung.models.lich_chuan;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAll extends Fragment implements Comparator<lich_chuan> {
    private PullRefreshLayout pullRefreshLayout;
    AlertDialog dialog;
    RelativeLayout rl;
    private ExpandableListView listView;
    private AdapterEx adapterEx;
    private List<String> listDataHeader;
    SimpleDateFormat sf= new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
    private HashMap<String,List<lich_chuan>> listHash;
    String DATABASE_NAME="dbthoikhoabieu.sqlite";
    SQLiteDatabase sqLiteDatabase=null;
    Date Date_Min=new Date();
    Date Date_Max=new Date();
    ArrayList<lich_chuan> arr_tatca;

    public FragmentAll() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_fragment_all, container, false);
        listView = view.findViewById(R.id.lv_tatca);
        listView.setFastScrollEnabled(true);
        pullRefreshLayout= view.findViewById(R.id.id_pullall);
        dialog = new SpotsDialog(getActivity(),R.style.Custom);
        dialog.setCanceledOnTouchOutside(false);
        rl=view.findViewById(R.id.bk_image1);
        arr_tatca= new ArrayList<>();
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();
        try {
            Date_Min=sf.parse("13/11/2090");
            Date_Max=sf.parse("13/11/1996");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pullRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullRefreshLayout.setRefreshing(false);
                    }
                },2000);

            }

        });
        new GetData().execute();
        return view;
    }
    private  void  getngayminmax()    {
        try
        {
            sqLiteDatabase=getActivity().openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE,null);
            String sql="SELECT NgayHoc FROM tbthoikhoabieu";
            Cursor cursor=sqLiteDatabase.rawQuery(sql,null);
            String date_tmp="";
            Date date_ht=new Date();
            Calendar ca=Calendar.getInstance();
            String dateht=sf.format(ca.getTime());
            try {
                Date_Min=sf.parse(dateht);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            while (cursor.moveToNext())
            {
                date_tmp=cursor.getString(0);
                try {
                    date_ht=sf.parse(date_tmp);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (date_ht.after(Date_Max))
                {
                    Date_Max=date_ht;
                }
            }
            cursor.close();
        }
        catch (Exception ex) { }
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
    public class GetData extends AsyncTask<Void, Void, Void> {
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
            getngayminmax();

        }
        @Override
        protected Void doInBackground(Void... voids) {
            initData();
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            new GetData1().execute();
            dialog.dismiss();

        }
    }
    public class GetData1 extends AsyncTask<Void, Void, Void> {

        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            locham();
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            adapterEx=new AdapterEx(getActivity(),listDataHeader,listHash);
            listView.setAdapter(adapterEx);
            if (listDataHeader.size()==0)
            {
                rl.setBackgroundResource(R.drawable.pk2);
            }

            for (int i=0;i<listDataHeader.size();i++)//mở tất cả
            {
                listView.expandGroup(i);
            }

        }
    }
    private  void locham(){
        Calendar bd= Calendar.getInstance();
        Calendar kt= Calendar.getInstance();
        bd.setTime(Date_Min);
        kt.setTime(Date_Max);
        kt.add(Calendar.DATE, 1);
        ArrayList<lich_chuan> chuanArrayList;
        Log.d("Luongvanchung96","min:"+dateFormat.format(bd.getTime()));
        Log.d("Luongvanchung96","max:"+dateFormat.format(kt.getTime()));
        while (bd.getTime().before(kt.getTime()))
        {
            chuanArrayList=new ArrayList<>();
            // String a ="Thứ "+bd.get(Calendar.DAY_OF_WEEK)+", Ngày "+bd.get(Calendar.DATE)+" Tháng "+bd.get(Calendar.MONTH)+" Năm "+bd.get(Calendar.YEAR);
            String a ="Thứ "+bd.get(Calendar.DAY_OF_WEEK)+", Ngày "+dateFormat.format(bd.getTime());
            listDataHeader.add(a);
            for (int i=0;i<arr_tatca.size();i++)
            {
                if (bd.getTime().equals(arr_tatca.get(i).getNgay()))
                {
                    chuanArrayList.add(arr_tatca.get(i));
                }
            }
            ///săp xếp thứ tự lịch
            Collections.sort(chuanArrayList,new FragmentAll());
            listHash.put(a,chuanArrayList);
            bd.add(Calendar.DATE, 1);
        }
        Log.d("Luongvanchung96","xong: init");
    }
    private void initData() {
        try
        {
            //<editor-fold desc="lấy hêt các bản ghi trong csdl">
            sqLiteDatabase=getActivity().openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE,null);
            String sql="SELECT * FROM tbthoikhoabieu";
            Cursor cursor=sqLiteDatabase.rawQuery(sql,null);
            SimpleDateFormat sf= new SimpleDateFormat("dd/MM/yyyy");
            Date date1 =new Date();
            while (cursor.moveToNext())
            {
                try {
                    date1= sf.parse(cursor.getString(5));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                arr_tatca.add(new lich_chuan(Integer.parseInt(
                        cursor.getString(0)), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3),
                        cursor.getString(4), date1, cursor.getString(6),
                        cursor.getString(7), cursor.getString(8), cursor.getString(9),cursor.getString(10)));


            }
            cursor.close();
            //</editor-fold>
        }
        catch (Exception ex)
        {

        }
    }

}

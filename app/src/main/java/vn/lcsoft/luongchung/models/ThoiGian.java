package vn.lcsoft.luongchung.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by LUONG CHUNG on 5/1/2017.
 */

public class ThoiGian implements Serializable {
    private String ngayBD;
    private String ngayKT;
    private ArrayList<ThuHoc> thuHocs;

    ThoiGian(String ngayBD, String ngayKT, String txtthuHoc) {
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
        this.thuHocs = getArray_ThuHoc(txtthuHoc);
    }





    public String getNgayBD() {
        return ngayBD;
    }


    public String getNgayKT() {
        return ngayKT;
    }


    public ArrayList<ThuHoc> getThuHocs() {
        return thuHocs;
    }



    private ArrayList<ThuHoc> getArray_ThuHoc(String s){
        ArrayList<ThuHoc> temp=new ArrayList<>();
        int k=DemSoChuoi("Thứ",s);
        for (int i=0;i<k;i++)
        {
            System.out.println("id thứ:"+(i+1));
            String _StrThu;
            String _tietBD;
            String _tietKT;
            String _thu;
            if (i==k-1)
            {
                _StrThu=s.substring(layvitrichuoi("Thứ",s,i),s.length()-1);
                _thu=_StrThu.substring(layvitrichuoi("Thứ",_StrThu,0)+4,layvitrichuoi("tiết",_StrThu,0)).trim();
                int p=DemSoChuoi(",",_StrThu);
                _tietBD=_StrThu.substring(layvitrichuoi("tiết",_StrThu,0)+4,(layvitrichuoi(",",_StrThu,0))).trim();
                _tietKT=_StrThu.substring(layvitrichuoi(",",_StrThu,p-1)+1,layvitrichuoi("(",_StrThu,0)-1).trim();
                temp.add(new ThuHoc(i+1,_thu,_tietBD,_tietKT));



            }
            else
            {
                _StrThu=s.substring(layvitrichuoi("Thứ",s,i),layvitrichuoi("Thứ",s,i+1));
                int p=DemSoChuoi(",",_StrThu);
                _tietBD=_StrThu.substring(layvitrichuoi("tiết",_StrThu,0)+4,(layvitrichuoi(",",_StrThu,0))).trim();
                _tietKT=_StrThu.substring(layvitrichuoi(",",_StrThu,p-1)+1,layvitrichuoi("(",_StrThu,0)-1).trim();
                _thu=_StrThu.substring(layvitrichuoi("Thứ",_StrThu,0)+4,layvitrichuoi("tiết",_StrThu,0)).trim();
                temp.add(new ThuHoc(i+1,_thu,_tietBD,_tietKT));
            }
        }
        return temp;
    }
    private int layvitrichuoi(String Tu, String Chuoi, int vt)    {
        int dem=0;
        for (int i=0;i<Chuoi.length()-(Tu.length()-1);i++)
        {
            int kt=0;
            for (int j=0;j<Tu.length();j++)
            {
                if (Tu.charAt(j)==Chuoi.charAt(i+j))
                {
                    kt++;
                }
            }
            if (kt>=Tu.length())
            {
                if (dem==vt)
                {
                    return i;
                }
                dem++;
            }
        }
        return 0;
    }
    private int DemSoChuoi(String tu, String chuoi)   {
        int dem=0;
        for (int i=0;i<chuoi.length()-(tu.length()-1);i++)
        {
            int kt=0;
            for (int j=0;j<tu.length();j++)
            {
                if (tu.charAt(j)==chuoi.charAt(i+j))
                {
                    kt++;
                }
            }
            if (kt>=tu.length())
            {
                dem++;
            }

        }
        return dem;

    }
}

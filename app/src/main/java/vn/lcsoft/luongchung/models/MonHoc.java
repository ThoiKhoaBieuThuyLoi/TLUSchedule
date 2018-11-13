package vn.lcsoft.luongchung.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by LUONG CHUNG on 5/1/2017.
 */

public class MonHoc implements Serializable {
    private String tenLop;
    private String tenHocPhan;
    private ArrayList<ThoiGian> thoiGians;
    private String diaDiems;
    private String soTinChi;
    private String tenGiangVien;

    public String getTenGiangVien() {
        return tenGiangVien;
    }


    public MonHoc(String tenLop, String tenHocPhan, String txtThoiGian, String txtDiaDiem, String soTinChi, String tenGiangVien) {
        this.tenLop = tenLop;
        this.tenHocPhan = tenHocPhan;
        this.thoiGians = getArray_ThoiGian(txtThoiGian);
        this.diaDiems = txtDiaDiem;
        this.soTinChi = soTinChi;
        this.tenGiangVien=tenGiangVien;
    }

    //<editor-fold desc="hàm get set">

    public String getTenLop() {
        return tenLop;
    }


    public String getTenHocPhan() {
        return tenHocPhan;
    }


    public ArrayList<ThoiGian> getThoiGians() {
        return thoiGians;
    }


    public String getDiaDiems() {
        return diaDiems;
    }


    public String getSoTinChi() {
        return soTinChi;
    }

    //</editor-fold>

    private ArrayList<ThoiGian> getArray_ThoiGian(String s){
        ArrayList<ThoiGian> temp=new ArrayList<>();
        int k=DemSoChuoi(s);
        for (int i=0;i<k;i++)
        {
            String _GiaiDoan;
            String _ngayBD;
            String _ngayKT;
            String _chuoithuhoc;
            if (i==k-1)
            {
                _GiaiDoan=s.substring(layvitrichuoi("Từ",s,i),s.length()-1);
                _ngayBD=_GiaiDoan.substring(layvitrichuoi("Từ",_GiaiDoan,0)+3,(layvitrichuoi("đến",_GiaiDoan,0))).trim();
                _ngayKT=_GiaiDoan.substring(layvitrichuoi("đến",_GiaiDoan,0)+3,(layvitrichuoi(":",_GiaiDoan,0))).trim();
                _chuoithuhoc=_GiaiDoan.substring(layvitrichuoi("Thứ",_GiaiDoan,0),_GiaiDoan.length()).trim();
                temp.add(new ThoiGian(_ngayBD,_ngayKT,_chuoithuhoc));


            }
            else
            {
                _GiaiDoan=s.substring(layvitrichuoi("Từ",s,i),layvitrichuoi("Từ",s,i+1));
                _ngayBD=_GiaiDoan.substring(layvitrichuoi("Từ",_GiaiDoan,0)+3,(layvitrichuoi("đến",_GiaiDoan,0))).trim();
                _ngayKT=_GiaiDoan.substring(layvitrichuoi("đến",_GiaiDoan,0)+3,(layvitrichuoi(":",_GiaiDoan,0))).trim();
                _chuoithuhoc=_GiaiDoan.substring(layvitrichuoi("Thứ",_GiaiDoan,0),_GiaiDoan.length()).trim();
                temp.add(new ThoiGian(_ngayBD,_ngayKT,_chuoithuhoc));
            }
        }
        return temp;
    }//xu ly thoi gian


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
    private int DemSoChuoi(String chuoi)   {
        int dem=0;
        for (int i = 0; i<chuoi.length()-("đến".length()-1); i++)
        {
            int kt=0;
            for (int j = 0; j< "đến".length(); j++)
            {
                if ("đến".charAt(j)==chuoi.charAt(i+j))
                {
                    kt++;
                }
            }
            if (kt>= "đến".length())
            {
                dem++;
            }

        }
        return dem;

    }

}


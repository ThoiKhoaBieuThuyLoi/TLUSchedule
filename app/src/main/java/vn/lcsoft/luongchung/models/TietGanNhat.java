package vn.lcsoft.luongchung.models;

import java.util.Date;

/**
 * Created by luongchung on 9/6/17.
 */

public class TietGanNhat {
    private Date ngayHoc;
    private String tietBatDau;
    private String tenMon;
    private String diaDiem;
    public TietGanNhat(Date ngayHoc, String tietBatDau, String tenMon, String diaDiem) {
        this.ngayHoc = ngayHoc;
        this.tietBatDau = tietBatDau;
        this.tenMon = tenMon;
        this.diaDiem = diaDiem;
    }
    public TietGanNhat() {}
    public String getDiaDiem() {
        return diaDiem;
    }
    public void setDiaDiem(String diaDiem) {
        this.diaDiem = diaDiem;
    }
    public Date getNgayHoc() {
        return ngayHoc;
    }
    public void setNgayHoc(Date ngayHoc) {
        this.ngayHoc = ngayHoc;
    }
    public String getTietBatDau() {
        return tietBatDau;
    }
    public void setTietBatDau(String tietBatDau) {
        this.tietBatDau = tietBatDau;
    }
    public String getTenMon() {
        return tenMon;
    }
    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }
    @Override
    public String toString() {
        return "TietGanNhat{" +
                "ngayHoc=" + ngayHoc +
                ", tietBatDau='" + tietBatDau + '\'' +
                ", tenMon='" + tenMon + '\'' +
                ", diaDiem='" + diaDiem + '\'' +
                '}';
    }
}

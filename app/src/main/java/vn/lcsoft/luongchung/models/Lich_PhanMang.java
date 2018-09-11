package vn.lcsoft.luongchung.models;

import java.util.Date;

/**
 * Created by LUONG CHUNG on 7/10/2017.
 */

public class Lich_PhanMang {
    private int iD;
    private String tenMonHoc;
    private String tenLopTinChi;
    private String diaDiem;
    private String giangVien;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private String thuHoc;
    private String tietBatDau;
    private String tietKetThuc;
    private String soTinChi;

    public Lich_PhanMang(int iD, String tenMonHoc, String tenLopTinChi, String diaDiem,
                         String giangVien, Date ngayBatDau, Date ngayKetThuc, String thuHoc,
                         String tietBatDau, String tietKetThuc, String soTinChi) {
        this.iD = iD;
        this.tenMonHoc = tenMonHoc;
        this.tenLopTinChi = tenLopTinChi;
        this.diaDiem = diaDiem;
        this.giangVien = giangVien;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.thuHoc = thuHoc;
        this.tietBatDau = tietBatDau;
        this.tietKetThuc = tietKetThuc;
        this.soTinChi = soTinChi;
    }

    public Lich_PhanMang() {
    }

    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public String getTenMonHoc() {
        return tenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }

    public String getTenLopTinChi() {
        return tenLopTinChi;
    }

    public void setTenLopTinChi(String tenLopTinChi) {
        this.tenLopTinChi = tenLopTinChi;
    }

    public String getDiaDiem() {
        return diaDiem;
    }

    public void setDiaDiem(String diaDiem) {
        this.diaDiem = diaDiem;
    }

    public String getGiangVien() {
        return giangVien;
    }

    public void setGiangVien(String giangVien) {
        this.giangVien = giangVien;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getThuHoc() {
        return thuHoc;
    }

    public void setThuHoc(String thuHoc) {
        this.thuHoc = thuHoc;
    }

    public String getTietBatDau() {
        return tietBatDau;
    }

    public void setTietBatDau(String tietBatDau) {
        this.tietBatDau = tietBatDau;
    }

    public String getTietKetThuc() {
        return tietKetThuc;
    }

    public void setTietKetThuc(String tietKetThuc) {
        this.tietKetThuc = tietKetThuc;
    }

    public String getSoTinChi() {
        return soTinChi;
    }

    public void setSoTinChi(String soTinChi) {
        this.soTinChi = soTinChi;
    }
}

package vn.lcsoft.luongchung.models;

import java.util.Date;


public class lich_chuan {
    private int id;
    private String tenMonHoc;
    private String tenLopTinChi;
    private String diaDiem;
    private String giangVien;
    private Date ngay;
    private String thuHoc;
    private String tietBatDau;
    private String tietKetThuc;
    private String soTinChi;
    private String note;

    public lich_chuan(int id, String tenMonHoc, String tenLopTinChi, String diaDiem, String giangVien, Date ngay, String thuHoc, String tietBatDau, String tietKetThuc, String soTinChi, String note) {
        this.id = id;
        this.tenMonHoc = tenMonHoc;
        this.tenLopTinChi = tenLopTinChi;
        this.diaDiem = diaDiem;
        this.giangVien = giangVien;
        this.ngay = ngay;
        this.thuHoc = thuHoc;
        this.tietBatDau = tietBatDau;
        this.tietKetThuc = tietKetThuc;
        this.soTinChi = soTinChi;
        this.note = note;
    }

    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenMonHoc() {

        return tenMonHoc;
    }


    public String getTenLopTinChi() {
        return tenLopTinChi;
    }


    public String getDiaDiem() {
        return diaDiem;
    }


    public String getGiangVien() {
        return giangVien;
    }


    public Date getNgay() {
        return ngay;
    }


    public String getThuHoc() {
        return thuHoc;
    }


    public String getTietBatDau() {
        return tietBatDau;
    }


    public String getTietKetThuc() {
        return tietKetThuc;
    }


    public String getSoTinChi() {
        return soTinChi;
    }
}

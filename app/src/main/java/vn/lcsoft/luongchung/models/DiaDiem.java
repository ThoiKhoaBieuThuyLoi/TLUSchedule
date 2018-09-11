package vn.lcsoft.luongchung.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by LUONG CHUNG on 5/1/2017.
 */

public class DiaDiem implements Serializable {
    private int idGD;
    private ArrayList<PhongHoc> phongHocs;

    public DiaDiem(int idGD, ArrayList<PhongHoc> phongHocs) {
        this.idGD = idGD;
        this.phongHocs = phongHocs;
    }

    public DiaDiem() {
    }

    public int getIdGD() {
        return idGD;
    }

    public void setIdGD(int idGD) {
        this.idGD = idGD;
    }

    public ArrayList<PhongHoc> getPhongHocs() {
        return phongHocs;
    }

    public void setPhongHocs(ArrayList<PhongHoc> phongHocs) {
        this.phongHocs = phongHocs;
    }
}

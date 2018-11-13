package vn.lcsoft.luongchung.models;

/**
 * Created by LUONG CHUNG on 7/4/2017.
 */

public class TinTuc_m {
    private String tieuDe;
    private String tomTat;
    private String urlHinh;
    private String urlLink;

    public TinTuc_m(String tieuDe, String tomTat, String urlHinh, String urlLink) {
        this.tieuDe = tieuDe;
        this.tomTat = tomTat;
        this.urlHinh = urlHinh;
        this.urlLink = urlLink;
    }



    public String getTieuDe() {
        return tieuDe;
    }


    public String getTomTat() {
        return tomTat;
    }


    public String getUrlHinh() {
        return urlHinh;
    }


    public String getUrlLink() {
        return urlLink;
    }

}

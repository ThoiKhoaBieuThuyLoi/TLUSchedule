package vn.lcsoft.luongchung.models;

public class User {
    private String id;
    private String name;
    private String email;
    private String gioitinh;
    private String sinhnhat;
    private String keyFirebase;

    public String getKeyFirebase() {
        return keyFirebase;
    }

    public void setKeyFirebase(String keyFirebase) {
        this.keyFirebase = keyFirebase;
    }

    public User() {
    }

    public User(String id, String name, String email, String gioitinh, String sinhnhat,String key) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gioitinh = gioitinh;
        this.sinhnhat = sinhnhat;
        this.keyFirebase=key;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getSinhnhat() {
        return sinhnhat;
    }

    public void setSinhnhat(String sinhnhat) {
        this.sinhnhat = sinhnhat;
    }
}

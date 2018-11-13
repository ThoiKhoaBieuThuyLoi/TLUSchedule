package vn.lcsoft.luongchung.models;

public class User {
    private String id;
    private String name;
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
}

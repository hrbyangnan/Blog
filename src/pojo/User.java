package pojo;

public class User {
    private int id;
    private String name;
    private String password;
    private String realName;
    private String country;
    private String birthday;
    private String information;
    private String avatar;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getInfomation() {
        return information;
    }

    public void setInfomation(String infomation) {
        this.information = infomation;
    }

    public String getAvatar(){return avatar;}

    public void setAvatar(String avatar) {this.avatar = avatar;}
}



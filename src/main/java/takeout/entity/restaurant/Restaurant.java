package takeout.entity.restaurant;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "restaurant")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Restaurant {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id;//编号

    @Column(name = "identification")
    private String identification; //识别码

    @Column(name = "password")
    private String password; //密码

    @Column(name = "name")
    private String name;//餐厅名称

    @Column(name = "address")
    private String address; //地址

    @Column(name="longitude")
    private double longitude;//经度

    @Column(name = "latitude")
    private double latitude;//纬度

    @Column(name = "mobilePhone")
    private String mobilePhone; //手机号

    @Column(name = "balance")
    private double balance; //余额

    @Column(name = "faceUrl")
    private String faceUrl; //餐厅头像

    @Column(name = "license")
    private String license; //餐厅营业执照

    @Column(name= "status")
    private String status;//applying,using,withdraw

    public Restaurant() {
    }

    public Restaurant(String identification, String password, String name, String address, double longitude, double latitude, String mobilePhone, double balance, String faceUrl, String license, String status) {
        this.identification = identification;
        this.password = password;
        this.name = name;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.mobilePhone = mobilePhone;
        this.balance = balance;
        this.faceUrl = faceUrl;
        this.license = license;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getFaceUrl() {
        return faceUrl;
    }

    public void setFaceUrl(String faceUrl) {
        this.faceUrl = faceUrl;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

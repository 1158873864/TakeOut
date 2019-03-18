package takeout.parameters.restaurant;


import javax.persistence.Column;

public class RestaurantVO {

    private String password; //密码

    private String name;//餐厅名称

    private String address; //地址

    private double longitude;//经度

    private double latitude;//纬度

    private String mobilePhone; //手机号

    private String faceUrl; //餐厅头像

    private String license; //餐厅营业执照

    public RestaurantVO(String password, String name, String address, double longitude, double latitude, String mobilePhone, String faceUrl, String license) {
        this.password = password;
        this.name = name;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.mobilePhone = mobilePhone;
        this.faceUrl = faceUrl;
        this.license = license;
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
}

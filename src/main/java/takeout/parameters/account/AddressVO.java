package takeout.parameters.account;


public class AddressVO {
    private String name;//地址名称

    private String user_id; //user的id

    private double longitude;//经度

    private double latitude;//纬度

    public AddressVO(String name, String user_id, double longitude, double latitude) {
        this.name = name;
        this.user_id = user_id;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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
}

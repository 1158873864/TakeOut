package takeout.entity.account;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.awt.geom.Point2D;

@Entity
@Table(name = "address")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Address {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id;//等级号

    @Column(name="name")
    private String name;//地址名称

    @Column(name="longitude")
    private double longitude;//经度

    @Column(name = "latitude")
    private double latitude;//纬度

    @ManyToOne
    private User user;

    public Address() {
    }

    public Address(String name, double longitude, double latitude, User user) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

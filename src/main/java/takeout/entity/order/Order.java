package takeout.entity.order;

import org.hibernate.annotations.GenericGenerator;
import takeout.entity.account.User;
import takeout.entity.restaurant.Goods;
import takeout.entity.restaurant.Restaurant;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import takeout.entity.restaurant.Package;
@Entity
@Table(name = "o")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Order {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id;//编号

    @Column(name = "price")
    private double price; //订单价格

    @Column(name = "date")
    private Date date; //日期

    @Column(name = "status")
    private String status; //状态：未支付、已取消、已送达、派送中、已退订

    @Column(name = "schedule")
    private int schedule;

    @OneToOne
    private User user;

    @OneToOne
    private Restaurant restaurant;

    @OneToMany
    private List<Goods> goodsList;

    @OneToMany
    private List<Package> packageList;

    public Order(double price, Date date, String status, int schedule, User user, Restaurant restaurant, List<Goods> goodsList, List<Package> packageList) {
        this.price = price;
        this.date = date;
        this.status=status;
        this.schedule=schedule;
        this.user = user;
        this.restaurant = restaurant;
        this.goodsList = goodsList;
        this.packageList = packageList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getSchedule() {
        return schedule;
    }

    public void setSchedule(int schedule) {
        this.schedule = schedule;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    public List<Package> getPackageList() {
        return packageList;
    }

    public void setPackageList(List<Package> packageList) {
        this.packageList = packageList;
    }
}

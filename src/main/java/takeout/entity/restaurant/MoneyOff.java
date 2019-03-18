package takeout.entity.restaurant;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "moneyOff")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class MoneyOff {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id;//编号

    @Column(name = "price")
    private double price; //达到的价格

    @Column(name = "off")
    private double off; //减价

    @Column(name= "utilDate")
    private Date utilDate;//截止日期

    @ManyToOne
    private Restaurant restaurant;//所属餐厅

    public MoneyOff() {
    }

    public MoneyOff(double price, double off, Date utilDate,Restaurant restaurant) {

        this.price = price;
        this.off = off;
        this.utilDate=utilDate;
        this.restaurant = restaurant;
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

    public double getOff() {
        return off;
    }

    public void setOff(double off) {
        this.off = off;
    }

    public Date getUtilDate() {
        return utilDate;
    }

    public void setUtilDate(Date utilDate) {
        this.utilDate = utilDate;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
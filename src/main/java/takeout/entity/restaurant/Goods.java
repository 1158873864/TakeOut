package takeout.entity.restaurant;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "goods")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Goods {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id;//编号

    @Column(name = "name")
    private String name; //商品名称

    @Column(name = "price")
    private double price; //单价

    @Column(name = "discount")
    private double discount; //单品折扣

    @Column(name = "number")
    private int number;//剩余数量

    @Column(name= "utilDate")
    private Date utilDate;//截止日期

    @Column(name = "imageUrl")
    private String imageUrl; //商品图片

    @ManyToOne
    private Restaurant restaurant;//所属餐厅

    public Goods() {
    }

    public Goods(String name, double price, double discount, int number, Date utilDate,String imageUrl, Restaurant restaurant) {
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.number = number;
        this.utilDate=utilDate;
        this.imageUrl = imageUrl;
        this.restaurant = restaurant;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getUtilDate() {
        return utilDate;
    }

    public void setUtilDate(Date utilDate) {
        this.utilDate = utilDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
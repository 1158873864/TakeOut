package takeout.entity.restaurant;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "package")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Package {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id;//编号

    @Column(name = "name")
    private String name; //套餐名称

    @Column(name = "price")
    private double price; //价格

    @Column(name = "number")
    private int number;//套餐数量

    @Column(name = "imageUrl")
    private String imageUrl;//套餐图片

    @Column(name= "utilDate")
    private Date utilDate;//截止日期

    @ManyToOne
    private Restaurant restaurant;//对应餐厅

    @OneToMany
    private List<Goods> goodsList;

    public Package() {
    }

    public Package(String name, double price, int number, String imageUrl, Date utilDate, Restaurant restaurant, List<Goods> goodsList) {
        this.name = name;
        this.price = price;
        this.number = number;
        this.imageUrl=imageUrl;
        this.utilDate=utilDate;
        this.restaurant = restaurant;
        this.goodsList = goodsList;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

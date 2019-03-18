package takeout.parameters.restaurant;

import takeout.entity.restaurant.Restaurant;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.util.Date;

public class GoodsVO {

    private String id;//id

    private String name; //商品名称

    private double price; //单价

    private double discount; //单品折扣

    private int number;//剩余数量

    private Date utilDate;//截止日期

    private String imageUrl; //商品图片

    private String restaurantId;//餐厅id

    public GoodsVO(String id,String name, double price, double discount, int number, Date utilDate, String imageUrl, String restaurantId) {
        this.id=id;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.number = number;
        this.utilDate = utilDate;
        this.imageUrl = imageUrl;
        this.restaurantId = restaurantId;
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

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }
}

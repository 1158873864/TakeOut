package takeout.parameters.restaurant;

import takeout.entity.restaurant.Restaurant;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.util.Date;

public class MoneyOffVO {
    private String id;

    private double price; //达到的价格

    private double off; //减价

    private Date utilDate;//截止日期

    private String restaurantId;//所属餐厅id

    public MoneyOffVO(String id, double price, double off, Date utilDate, String restaurantId) {
        this.id = id;
        this.price = price;
        this.off = off;
        this.utilDate = utilDate;
        this.restaurantId = restaurantId;
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

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }
}

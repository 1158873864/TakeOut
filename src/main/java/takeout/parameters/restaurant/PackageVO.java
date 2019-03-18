package takeout.parameters.restaurant;

import takeout.entity.restaurant.Goods;
import takeout.entity.restaurant.Restaurant;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

public class PackageVO {

    private String id;

    private String name; //套餐名称

    private double price; //价格

    private int number;//套餐数量

    private String imageUrl;//套餐图片

    private Date utilDate;//截止日期

    private String restaurantId;//对应餐厅id

    private List<String> goodsIdList;

    public PackageVO(String id,String name, double price, int number, String imageUrl, Date utilDate, String restaurantId, List<String> goodsIdList) {
        this.id=id;
        this.name = name;
        this.price = price;
        this.number = number;
        this.imageUrl = imageUrl;
        this.utilDate = utilDate;
        this.restaurantId = restaurantId;
        this.goodsIdList = goodsIdList;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public List<String> getGoodsIdList() {
        return goodsIdList;
    }

    public void setGoodsIdList(List<String> goodsIdList) {
        this.goodsIdList = goodsIdList;
    }
}

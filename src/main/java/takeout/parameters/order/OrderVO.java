package takeout.parameters.order;

import java.util.List;

public class OrderVO {
    private String id;//编号

    private String  userId;

    private String  restaurantId;

    private String addressId;

    private List<String> goodsIdList;

    private List<String> packageIdList;

    public OrderVO(String id, String userId, String restaurantId, String addressId, List<String> goodsIdList, List<String> packageIdList) {
        this.id = id;
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.addressId=addressId;
        this.goodsIdList = goodsIdList;
        this.packageIdList = packageIdList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public List<String> getGoodsIdList() {
        return goodsIdList;
    }

    public void setGoodsIdList(List<String> goodsIdList) {
        this.goodsIdList = goodsIdList;
    }

    public List<String> getPackageIdList() {
        return packageIdList;
    }

    public void setPackageIdList(List<String> packageIdList) {
        this.packageIdList = packageIdList;
    }
}

//package takeout.entity.order;
//
//import org.hibernate.annotations.GenericGenerator;
//import takeout.entity.account.User;
//import takeout.entity.restaurant.Goods;
//import takeout.entity.restaurant.Restaurant;
//import takeout.entity.restaurant.Package;
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//@Table(name = "shoppingCart")
//@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
//public class ShoppingCart {
//    @Id
//    @GeneratedValue(generator = "jpa-uuid")
//    private String id;//编号
//
//    @Column(name = "price")
//    private double price; //购物车价格
//
//    @OneToOne
//    private User user;
//
//    @OneToOne
//    private Restaurant restaurant;
//
//    @OneToMany
//    private List<Goods> goodsList;
//
//    @OneToMany
//    private List<Package> packageList;
//
//    public ShoppingCart(double price,User user, Restaurant restaurant, List<Goods> goodsList, List<Package> packageList) {
//        this.price = price;
//        this.user = user;
//        this.restaurant = restaurant;
//        this.goodsList = goodsList;
//        this.packageList = packageList;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public Restaurant getRestaurant() {
//        return restaurant;
//    }
//
//    public void setRestaurant(Restaurant restaurant) {
//        this.restaurant = restaurant;
//    }
//
//    public List<Goods> getGoodsList() {
//        return goodsList;
//    }
//
//    public void setGoodsList(List<Goods> goodsList) {
//        this.goodsList = goodsList;
//    }
//
//    public List<Package> getPackageList() {
//        return packageList;
//    }
//
//    public void setPackageList(List<Package> packageList) {
//        this.packageList = packageList;
//    }
//}

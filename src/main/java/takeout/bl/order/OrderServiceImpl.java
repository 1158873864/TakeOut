package takeout.bl.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import takeout.blservice.order.OrderService;
import takeout.dao.account.AddressDao;
import takeout.dao.account.UserDao;
import takeout.dao.manager.ManagerDao;
import takeout.dao.order.OrderDao;
import takeout.dao.restaurant.GoodsDao;
import takeout.dao.restaurant.PackageDao;
import takeout.dao.restaurant.RestaurantDao;
import takeout.entity.account.Address;
import takeout.entity.account.User;
import takeout.entity.manager.Manager;
import takeout.entity.order.Order;
import takeout.entity.restaurant.Goods;
import takeout.entity.restaurant.Package;
import takeout.entity.restaurant.Restaurant;
import takeout.exception.NotExistException;
import takeout.parameters.order.OrderVO;
import takeout.util.DistanceUtil;
import takeout.util.Ratio;
import takeout.util.ScheduleUtil;

import java.awt.*;
import java.awt.geom.Point2D;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{

    private final OrderDao orderDao;

    private final UserDao userDao;

    private final RestaurantDao restaurantDao;

    private final GoodsDao goodsDao;

    private final PackageDao packageDao;

    private final ManagerDao managerDao;

    private final AddressDao addressDao;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao, UserDao userDao, RestaurantDao restaurantDao, GoodsDao goodsDao, PackageDao packageDao, ManagerDao managerDao, AddressDao addressDao) {
        this.orderDao = orderDao;
        this.userDao = userDao;
        this.restaurantDao = restaurantDao;
        this.goodsDao = goodsDao;
        this.packageDao = packageDao;
        this.managerDao = managerDao;
        this.addressDao = addressDao;
    }


    @Override
    public void submit(OrderVO orderVO) throws NotExistException {
        Restaurant restaurant=restaurantDao.getOne(orderVO.getRestaurantId());
        User user=userDao.getOne(orderVO.getUserId());
        List<Goods> goods=new ArrayList<>();
        List<Package> packages=new ArrayList<>();
        for(int i=0;i<orderVO.getGoodsIdList().size();i++){
             goods.add(goodsDao.getOne(orderVO.getGoodsIdList().get(i)));
        }
        for(int i=0;i<orderVO.getPackageIdList().size();i++){
            packages.add(packageDao.getOne(orderVO.getPackageIdList().get(i)));
        }
        double price=0;
        for(int i=0;i<goods.size();i++){
            price+=goods.get(i).getPrice()*goods.get(i).getDiscount();
        }
        for(int i=0;i<packages.size();i++){
            price+=packages.get(i).getPrice();
        }
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(8);
        Date date = formatter.parse(dateString, pos);
        Order order=new Order(price,date,"未支付",0,user,restaurant,goods,packages);
        orderDao.save(order);
    }

    @Override
    public double calPrice(OrderVO orderVO) throws NotExistException {
        List<Goods> goods=new ArrayList<>();
        List<Package> packages=new ArrayList<>();
        for(int i=0;i<orderVO.getGoodsIdList().size();i++){
            goods.add(goodsDao.getOne(orderVO.getGoodsIdList().get(i)));
        }
        for(int i=0;i<orderVO.getPackageIdList().size();i++){
            packages.add(packageDao.getOne(orderVO.getPackageIdList().get(i)));
        }
        double price=0;
        for(int i=0;i<goods.size();i++){
            price+=goods.get(i).getPrice()*goods.get(i).getDiscount();
        }
        for(int i=0;i<packages.size();i++){
            price+=packages.get(i).getPrice();
        }
        return price;
    }

    @Override
    public double calSchedule(OrderVO orderVO) throws NotExistException {
        User user=userDao.getOne(orderVO.getUserId());
        Restaurant restaurant=restaurantDao.getOne(orderVO.getRestaurantId());
        Address address=addressDao.getOne(orderVO.getAddressId());
        Point2D userAddress= new Point2D.Double(address.getLongitude(), address.getLatitude());
        Point2D restaurantAddress=new Point2D.Double(restaurant.getLongitude(),restaurant.getLatitude());
        double distance= DistanceUtil.getDistance(userAddress,restaurantAddress);
        double schedule= ScheduleUtil.calSchedule(distance);
        return schedule;
    }

    @Override
    public String pay(String id) throws NotExistException {
        Optional<Order> optionalOrder=orderDao.findById(id);
        if(optionalOrder.isPresent()){
            Order order=optionalOrder.get();
            User user=order.getUser();
            Restaurant restaurant=order.getRestaurant();
            Manager manager=managerDao.getOne("admin");
            if(user.getBalance()<order.getPrice()){
                return "余额不足";
            }
            else{
                user.setBalance(user.getBalance()-order.getPrice());
                restaurant.setBalance(restaurant.getBalance()+order.getPrice()*(1- Ratio.getRatio()));
                manager.setBalance(manager.getBalance()+order.getPrice()*Ratio.getRatio());
                order.setStatus("派送中");
                userDao.save(user);
                restaurantDao.save(restaurant);
                managerDao.save(manager);
                orderDao.save(order);
                return "1";
            }
        }else {
            throw new NotExistException("order ID", id);
        }

    }

    @Override
    public void cancel(String id) throws NotExistException {
        Optional<Order> optionalOrder=orderDao.findById(id);
        if(optionalOrder.isPresent()){
            Order order=optionalOrder.get();
            if(order.getStatus().equals("未支付")){
                order.setStatus("已取消");
                orderDao.save(order);
            }
        }else {
            throw new NotExistException("order ID", id);
        }
    }

    @Override
    public void finish(String id) throws NotExistException {
        Optional<Order> optionalOrder=orderDao.findById(id);
        if(optionalOrder.isPresent()){
            Order order=optionalOrder.get();
            if(order.getStatus().equals("派送中")){
                order.setStatus("已送达");
                orderDao.save(order);
            }
        }else {
            throw new NotExistException("order ID", id);
        }
    }

    @Override
    public void unsubscribe(String id) throws NotExistException {
        Optional<Order> optionalOrder=orderDao.findById(id);
        if(optionalOrder.isPresent()){
            Order order=optionalOrder.get();
            if(order.getStatus().equals("派送中")){

                order.setStatus("已退订");
                orderDao.save(order);
            }
        }else {
            throw new NotExistException("order ID", id);
        }
    }

    @Override
    public void add(Order order) throws NotExistException {

    }

    @Override
    public void delete(String id) throws NotExistException {

    }

    @Override
    public void update(OrderVO orderVO) throws NotExistException {

    }

    @Override
    public Order findById(String id) throws NotExistException {
        return null;
    }

    @Override
    public List<Order> findsAll() throws NotExistException {
        return null;
    }

    @Override
    public List<Order> findByUser(String id) throws NotExistException {
        return null;
    }

    @Override
    public List<Order> findByRestaurant(String id) throws NotExistException {
        return null;
    }

    @Override
    public List<Order> findByRestaurantAndUser(String restaurantId, String userId) {
        return null;
    }
}

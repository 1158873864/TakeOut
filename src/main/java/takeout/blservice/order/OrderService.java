package takeout.blservice.order;

import takeout.entity.order.Order;
import takeout.exception.NotExistException;
import takeout.parameters.order.OrderVO;

import java.util.List;

public interface OrderService {
    void submit(OrderVO orderVO) throws NotExistException;
    double calPrice(OrderVO orderVO) throws NotExistException;
    double calSchedule(OrderVO orderVO) throws NotExistException;
    String pay(String id)throws NotExistException;
    void cancel(String id) throws NotExistException;
    void finish(String id) throws NotExistException;
    void unsubscribe(String id) throws NotExistException;
    void add(Order order)throws NotExistException;
    void delete(String id) throws NotExistException;
    void update(OrderVO orderVO) throws NotExistException;
    Order findById(String id) throws NotExistException;
    List<Order> findsAll() throws NotExistException;
    List<Order> findByUser(String id) throws NotExistException;
    List<Order> findByRestaurant(String id) throws NotExistException;
    List<Order> findByRestaurantAndUser(String restaurantId,String userId);
}

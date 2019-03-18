package takeout.dao.order;

import org.springframework.data.jpa.repository.JpaRepository;
import takeout.entity.account.User;
import takeout.entity.order.Order;
import takeout.entity.restaurant.Goods;
import takeout.entity.restaurant.Restaurant;

import java.util.List;

public interface OrderDao extends JpaRepository<Order, String> {
     List<Order> findByUserOrderByDateDesc(User user);
     List<Order> findByRestaurantOrderByDateDesc(Restaurant restaurant);
}

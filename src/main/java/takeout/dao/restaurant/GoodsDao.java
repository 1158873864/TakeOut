package takeout.dao.restaurant;

import org.springframework.data.jpa.repository.JpaRepository;
import takeout.entity.restaurant.Goods;
import takeout.entity.restaurant.Restaurant;

import java.util.List;

public interface GoodsDao extends JpaRepository<Goods, String> {
     List<Goods> findByRestaurant(Restaurant restaurant);
}

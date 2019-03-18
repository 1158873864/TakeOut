package takeout.dao.restaurant;

import org.springframework.data.jpa.repository.JpaRepository;
import takeout.entity.restaurant.MoneyOff;
import takeout.entity.restaurant.Restaurant;

import java.util.List;

public interface MoneyOffDao extends JpaRepository<MoneyOff, String> {
     List<MoneyOff> findByRestaurant(Restaurant restaurant);
}

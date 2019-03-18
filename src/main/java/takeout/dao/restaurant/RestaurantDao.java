package takeout.dao.restaurant;

import org.springframework.data.jpa.repository.JpaRepository;
import takeout.entity.restaurant.Restaurant;

import java.util.List;

public interface RestaurantDao extends JpaRepository<Restaurant, String> {

}

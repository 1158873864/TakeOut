package takeout.dao.restaurant;

import org.springframework.data.jpa.repository.JpaRepository;
import takeout.entity.restaurant.Package;
import takeout.entity.restaurant.Restaurant;

import java.util.List;

public interface PackageDao extends JpaRepository<Package, String> {
    List<Package> findByRestaurant(Restaurant restaurant);
}

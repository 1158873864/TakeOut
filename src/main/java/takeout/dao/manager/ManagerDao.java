package takeout.dao.manager;

import org.springframework.data.jpa.repository.JpaRepository;
import takeout.entity.account.User;
import takeout.entity.manager.Manager;
import takeout.entity.order.Order;
import takeout.entity.restaurant.Restaurant;

import java.util.List;

public interface ManagerDao extends JpaRepository<Manager, String> {
     List<Manager> findManagerByUsername(String username);
}

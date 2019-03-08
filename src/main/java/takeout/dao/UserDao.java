package takeout.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import takeout.entity.User;

import java.util.List;

public interface UserDao extends JpaRepository<User, String> {
    List<User> findUserByMobilePhone(String mobilePhone);
    List<User> findUserByEmail(String email);
}

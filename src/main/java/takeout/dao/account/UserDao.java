package takeout.dao.account;

import org.springframework.data.jpa.repository.JpaRepository;
import takeout.entity.account.User;

import java.util.List;

public interface UserDao extends JpaRepository<User, String> {
    List<User> findUserByMobilePhone(String mobilePhone);
    List<User> findUserByEmail(String email);
}

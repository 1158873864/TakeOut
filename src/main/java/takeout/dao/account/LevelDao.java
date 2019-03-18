package takeout.dao.account;

import org.springframework.data.jpa.repository.JpaRepository;
import takeout.entity.account.Level;

public interface LevelDao extends JpaRepository<Level, Integer> {

}

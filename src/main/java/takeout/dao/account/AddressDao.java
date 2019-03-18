package takeout.dao.account;

import org.springframework.data.jpa.repository.JpaRepository;
import takeout.entity.account.Address;
import takeout.entity.account.Level;

public interface AddressDao extends JpaRepository<Address, String> {

}

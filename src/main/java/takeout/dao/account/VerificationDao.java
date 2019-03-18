package takeout.dao.account;

import org.springframework.data.jpa.repository.JpaRepository;
import takeout.entity.account.Verification;

import java.util.List;

public interface VerificationDao extends JpaRepository<Verification, String> {
    List<Verification> findByEmail(String email);
}

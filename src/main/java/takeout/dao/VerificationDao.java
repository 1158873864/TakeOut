package takeout.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import takeout.entity.User;
import takeout.entity.Verification;

import java.util.List;

public interface VerificationDao extends JpaRepository<Verification, String> {
    List<Verification> findVerificationByEmail(String email);
}

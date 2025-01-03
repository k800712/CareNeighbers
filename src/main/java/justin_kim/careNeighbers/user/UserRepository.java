package justin_kim.careNeighbers.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<Object> findByUsernameOrEmail(String usernameOrEmail, String usernameOrEmail1);
}

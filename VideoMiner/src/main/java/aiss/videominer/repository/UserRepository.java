package aiss.videominer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import aiss.videominer.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}

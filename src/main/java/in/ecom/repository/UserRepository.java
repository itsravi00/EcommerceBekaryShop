package in.ecom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ecom.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findUserByEmail(String email);

	
}

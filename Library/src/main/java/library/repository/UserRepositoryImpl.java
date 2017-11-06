package library.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import library.model.User;
@Repository
public interface UserRepositoryImpl extends JpaRepository<User, Integer>, UserRepository {
	@Override
	List<User> findByLastName(String lastName);
	@Override
	Optional<User> findById(int id);
	@Override
	User findOneByUsername(String username);
}

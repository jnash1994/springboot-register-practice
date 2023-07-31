package Nash.example.register.model;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserModel, Integer> {
    UserModel findByEmail(String email);
    UserModel findByUsername(String username);
}

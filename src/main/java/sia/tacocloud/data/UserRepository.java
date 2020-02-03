package sia.tacocloud.data;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.User;

import javax.validation.constraints.Size;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

    boolean existsByUsername(@Size(max = 50) String username);

}

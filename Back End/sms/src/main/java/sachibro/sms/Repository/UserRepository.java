package sachibro.sms.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sachibro.sms.Model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
    User save(User user);
    User findById(int id);
    User deleteById(int id);
    User findByUsernameAndPosition(String Username, String Position);

}

package sachibro.sms.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sachibro.sms.Model.Teacher;

import java.util.ArrayList;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Integer> {
    Teacher save(Teacher teacher);
    ArrayList<Teacher> getAllByUsernameNotNull();
    Teacher getTeacherByName(String Name);
    Teacher findByUsername(String Username);
    Teacher findById(int id);
}


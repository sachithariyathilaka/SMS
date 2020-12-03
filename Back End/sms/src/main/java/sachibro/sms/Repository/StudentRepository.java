package sachibro.sms.Repository;

import org.springframework.data.repository.CrudRepository;
import sachibro.sms.Model.Student;

import java.util.ArrayList;

public interface StudentRepository extends CrudRepository<Student, Integer> {
    Student save(Student student);
    ArrayList<Student> getAllByUsernameNotNull();
    Student getByName(String Name);
}

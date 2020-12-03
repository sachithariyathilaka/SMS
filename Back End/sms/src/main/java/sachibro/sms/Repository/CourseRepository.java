package sachibro.sms.Repository;

import org.springframework.data.repository.CrudRepository;
import sachibro.sms.Model.Course;

import java.util.ArrayList;

public interface CourseRepository extends CrudRepository<Course, Integer> {
    Course save(Course course);
    ArrayList<Course> getAllByIdNotNull();
    Course getByCourseName(String Name);
    ArrayList<Course> getAllByTeacher(String teacher);
}

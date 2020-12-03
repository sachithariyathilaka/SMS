package sachibro.sms.Repository;

import org.springframework.data.repository.CrudRepository;
import sachibro.sms.Model.StudentsInCourse;

import java.util.ArrayList;

public interface StudentsInCourseRepository extends CrudRepository<StudentsInCourse, Integer> {
    StudentsInCourse save(StudentsInCourse studentsInCourse);
    ArrayList<StudentsInCourse> getAllByStudent(String student);
    ArrayList<StudentsInCourse> getAllByCourse(String course);
}

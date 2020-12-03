package sachibro.sms.Repository;

import org.springframework.data.repository.CrudRepository;
import sachibro.sms.Model.Attendance;

import java.util.ArrayList;

public interface AttendanceRepository extends CrudRepository<Attendance, Integer> {
    Attendance save(Attendance attendance);
    boolean deleteByCourseAndDateAndStudent(String course, String date, String student);
    ArrayList<Attendance> getAllByCourseAndAndDateAndAttendance(String course, String date, boolean attendance);
    ArrayList<Attendance> getAllByCourse(String course);

}

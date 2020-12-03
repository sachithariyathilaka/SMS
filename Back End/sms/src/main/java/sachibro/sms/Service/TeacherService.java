package sachibro.sms.Service;

import sachibro.sms.Dto.CourseAndDate;
import sachibro.sms.Dto.CourseAndDateAndStudent;
import sachibro.sms.Dto.CourseAndType;
import sachibro.sms.Dto.CourseAndTypeAndDate;
import sachibro.sms.Model.Course;
import sachibro.sms.Model.Notes;
import sachibro.sms.Model.Teacher;

import java.util.ArrayList;

public interface TeacherService {

    boolean teacherLogin(String Username, String Password) throws Exception;
    Teacher getUserByName(String username);
    Teacher getTeacherById(int id);
    Notes saveNote(Notes notes);
    ArrayList<String> getDatesForCourse(CourseAndType courseAndType);
    String getNotesForDate(CourseAndTypeAndDate courseAndTypeAndDate);
    ArrayList<Course> getCoursesByTeacherId(int id);
    boolean markAllAttendance(CourseAndDate courseAndDate);
    boolean markAttendance(CourseAndDateAndStudent courseAndDateAndStudent);
    ArrayList<String> getAllStudentsForAttendance(CourseAndDate courseAndDate);
    ArrayList<String> getAttendanceDatesForCourse(String course);
}

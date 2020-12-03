package sachibro.sms.Service;

import sachibro.sms.Dto.ChangePassword;
import sachibro.sms.Model.*;

import java.util.ArrayList;
import java.util.List;

public interface UserService {

    User userRegister(User user);

    Teacher teacherRegister(Teacher teacher);

    Student studentRegister(Student student);

    boolean userLogin(String Username, String Password) throws Exception;

    User getUserByName(String username);

    User changeAdminPassword(ChangePassword changePassword);

    ArrayList<Teacher> getAllTeachers();

    Course registerCourse(Course course);

    StudentsInCourse assignStudent(StudentsInCourse studentsInCourse);

    ArrayList<Student> getAllStudents();

    ArrayList<Course> getAllCourses();

    Teacher getTeacherByName(String Name);

    Student getStudentByName(String Name);

    Course getCourseByName(String Name);

    ArrayList<Course> getCoursesByTeacher(String teacher);

    ArrayList<StudentsInCourse> getCoursesByStudent(String student);

    ArrayList<StudentsInCourse> getStudentByCourse(String course);
}

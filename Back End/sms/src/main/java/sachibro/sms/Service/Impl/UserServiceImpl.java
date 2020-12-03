package sachibro.sms.Service.Impl;

import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sachibro.sms.Dto.ChangePassword;
import sachibro.sms.Model.*;
import sachibro.sms.Repository.*;
import sachibro.sms.Service.UserService;

import java.util.ArrayList;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentsInCourseRepository studentsInCourseRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    private static final Logger logger= Logger.getLogger(String.valueOf(Application.class));

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if (user == null) {
            logger.info(getClass().toString()+" << User not found with username: " + username + ">>");
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }

    @Override
    public User userRegister(User user) {
        logger.info(getClass().toString() +" << User Register Done >>");
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        newUser.setName(user.getName());
        newUser.setPosition(user.getPosition());
        return userRepository.save(newUser);
    }

    @Override
    public Teacher teacherRegister(Teacher teacher) {
        logger.info(getClass().toString() +" << Teacher Register Done >>");
        User newUser = new User();
        newUser.setName(teacher.getName());
        newUser.setPosition("Teacher");
        newUser.setUsername(teacher.getUsername());
        newUser.setPassword(teacher.getPassword());
        userRegister(newUser);
        Teacher newTeacher = new Teacher();
        newTeacher.setUsername(teacher.getUsername());
        newTeacher.setPassword(bcryptEncoder.encode(teacher.getPassword()));
        newTeacher.setName(teacher.getName());
        newTeacher.setEducation(teacher.getEducation());
        newTeacher.setEmail(teacher.getEmail());
        newTeacher.setMobile(teacher.getMobile());
        newTeacher.setNic(teacher.getNic());
        newTeacher.setSubject(teacher.getSubject());
        return teacherRepository.save(newTeacher);
    }

    @Override
    public Student studentRegister(Student student) {
        logger.info(getClass().toString() +" << Student Register Done >>");
        User newUser = new User();
        newUser.setName(student.getName());
        newUser.setPosition("Student");
        newUser.setUsername(student.getUsername());
        newUser.setPassword(student.getPassword());
        userRegister(newUser);
        Student newStudent = new Student();
        newStudent.setUsername(student.getUsername());
        newStudent.setPassword(bcryptEncoder.encode(student.getPassword()));
        newStudent.setName(student.getName());
        newStudent.setParent(student.getParent());
        newStudent.setEmail(student.getEmail());
        newStudent.setMobile(student.getMobile());
        newStudent.setAge(student.getAge());
        newStudent.setSchool(student.getSchool());
        return studentRepository.save(newStudent);
    }

    @Override
    public boolean userLogin(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            logger.info(getClass().toString()+" << User Authenticated >>");
            return true;
        } catch (BadCredentialsException e) {
            logger.info(getClass().toString()+" << Bad Credentials >>");
            return false;
        }
    }

    @Override
    public User getUserByName(String username) {
        logger.info(getClass().toString() +" << Get User By Name >>");
        User user = userRepository.findByUsername(username);
        return user;
    }

    @Override
    public User changeAdminPassword(ChangePassword changePassword) {
        logger.info(getClass().toString() +" << Change Admin Password >>");
        User user = userRepository.findById(changePassword.getId());
        user.setPassword(bcryptEncoder.encode(changePassword.getPassword()));
        userRepository.deleteById(changePassword.getId());
        return userRepository.save(user);
    }

    @Override
    public ArrayList<Teacher> getAllTeachers() {
        return teacherRepository.getAllByUsernameNotNull();
    }

    @Override
    public Course registerCourse(Course course) {
        logger.info(getClass().toString() +" << Course Register Done >>");
        Course newCourse = new Course();
        newCourse.setCourseName(course.getCourseName());
        newCourse.setDuration(course.getDuration());
        newCourse.setHall(course.getHall());
        newCourse.setLocation(course.getLocation());
        newCourse.setStartDate(course.getStartDate());
        newCourse.setTime(course.getTime());
        newCourse.setTeacher(course.getTeacher());
        return courseRepository.save(newCourse);
    }

    @Override
    public StudentsInCourse assignStudent(StudentsInCourse studentsInCourse) {
        logger.info(getClass().toString() +" << Student Assign Done >>");
        StudentsInCourse newStudentsInCourse = new StudentsInCourse();
        newStudentsInCourse.setCourse(studentsInCourse.getCourse());
        newStudentsInCourse.setStudent(studentsInCourse.getStudent());
        return studentsInCourseRepository.save(newStudentsInCourse);
    }

    @Override
    public ArrayList<Student> getAllStudents() {
        return studentRepository.getAllByUsernameNotNull();
    }

    @Override
    public ArrayList<Course> getAllCourses() {
        return courseRepository.getAllByIdNotNull();
    }

    @Override
    public Teacher getTeacherByName(String Name) {
        return teacherRepository.getTeacherByName(Name);
    }

    @Override
    public Student getStudentByName(String Name) {
        return studentRepository.getByName(Name);
    }

    @Override
    public Course getCourseByName(String Name) {
        return courseRepository.getByCourseName(Name);
    }

    @Override
    public ArrayList<Course> getCoursesByTeacher(String teacher) {
        return courseRepository.getAllByTeacher(teacher);
    }

    @Override
    public ArrayList<StudentsInCourse> getCoursesByStudent(String student) {
        return studentsInCourseRepository.getAllByStudent(student);
    }

    @Override
    public ArrayList<StudentsInCourse> getStudentByCourse(String course) {
        return studentsInCourseRepository.getAllByCourse(course);
    }
}

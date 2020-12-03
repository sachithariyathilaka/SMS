package sachibro.sms.Controller;

import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import sachibro.sms.Config.JwtRequest;
import sachibro.sms.Config.JwtResponse;
import sachibro.sms.Dto.ChangePassword;
import sachibro.sms.Dto.UserReturn;
import sachibro.sms.Model.*;
import sachibro.sms.Repository.UserRepository;
import sachibro.sms.Service.Impl.UserServiceImpl;
import sachibro.sms.Utill.JwtTokenUtil;

import java.util.logging.Logger;

@RestController
@CrossOrigin
public class UserController {

    private static final Logger logger= Logger.getLogger(String.valueOf(Application.class));

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    public ResponseEntity<?> loginUser(@RequestBody JwtRequest credentials) throws Exception {
        logger.info(getClass().toString() + " << User Login Controller >>");
        User newUser = userRepository.findByUsernameAndPosition(credentials.getUsername(), "Admin");
        if(newUser != null){
            boolean auth = userServiceImpl.userLogin(credentials.getUsername(), credentials.getPassword());
            if(auth){
                final UserDetails userDetails = userServiceImpl.loadUserByUsername(credentials.getUsername());
                final User user = userServiceImpl.getUserByName(credentials.getUsername());
                final String token = jwtTokenUtil.generateToken(userDetails);
                return ResponseEntity.ok(new UserReturn(token, user.getId()));
            } else{
                logger.info(getClass().toString()+" << User Credentials Invalid >>");
                return ResponseEntity.ok(new JwtResponse("Invalid"));
            }
        } else {
            logger.info(getClass().toString()+" << User Credentials Invalid >>");
            return ResponseEntity.ok(new JwtResponse("Invalid"));
        }

    }

    @RequestMapping(value = "/userRegister", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody User user) throws Exception {
        logger.info(getClass().toString()+" << User Register Controller >>");
        return ResponseEntity.ok(userServiceImpl.userRegister(user));
    }

    @RequestMapping(value = "/teacherRegister", method = RequestMethod.POST)
    public ResponseEntity<?> saveTeacher(@RequestBody Teacher teacher) throws Exception {
        logger.info(getClass().toString()+" << Teacher Register Controller >>");
        return ResponseEntity.ok(userServiceImpl.teacherRegister(teacher));
    }

    @RequestMapping(value = "/studentRegister", method = RequestMethod.POST)
    public ResponseEntity<?> saveStudent(@RequestBody Student student) throws Exception {
        logger.info(getClass().toString()+" << Student Register Controller >>");
        return ResponseEntity.ok(userServiceImpl.studentRegister(student));
    }

    @RequestMapping(value = "/changeAdminPswd", method = RequestMethod.POST)
    public ResponseEntity<?> changePassword(@RequestBody ChangePassword changePassword) throws Exception {
        logger.info(getClass().toString()+" << Change Admin Password Controller >>");
        return ResponseEntity.ok(userServiceImpl.changeAdminPassword(changePassword));
    }

    @RequestMapping(value = "/getAllTeachers", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTeachers() throws Exception {
        logger.info(getClass().toString()+" << Get All Teachers Controller >>");
        return ResponseEntity.ok(userServiceImpl.getAllTeachers());
    }

    @RequestMapping(value = "/registerCourse", method = RequestMethod.POST)
    public ResponseEntity<?> registerCourse(@RequestBody Course course) throws Exception {
        logger.info(getClass().toString()+" << Register Course Controller >>");
        return ResponseEntity.ok(userServiceImpl.registerCourse(course));
    }

    @RequestMapping(value = "/assignStudent", method = RequestMethod.POST)
    public ResponseEntity<?> assignStudent(@RequestBody StudentsInCourse studentsInCourse) throws Exception {
        logger.info(getClass().toString()+" << Assign Student Controller >>");
        return ResponseEntity.ok(userServiceImpl.assignStudent(studentsInCourse));
    }

    @RequestMapping(value = "/getAllStudents", method = RequestMethod.GET)
    public ResponseEntity<?> getAllStudents() throws Exception {
        logger.info(getClass().toString()+" << Get All Students Controller >>");
        return ResponseEntity.ok(userServiceImpl.getAllStudents());
    }

    @RequestMapping(value = "/getAllCourses", method = RequestMethod.GET)
    public ResponseEntity<?> getAllCourses() throws Exception {
        logger.info(getClass().toString()+" << Get All Courses Controller >>");
        return ResponseEntity.ok(userServiceImpl.getAllCourses());
    }

    @RequestMapping(value = "/getTeacherByName", method = RequestMethod.POST)
    public ResponseEntity<?> getTeacherByName(@RequestBody String name) throws Exception {
        logger.info(getClass().toString()+" << Get Teacher By Name Controller >>");
        return ResponseEntity.ok(userServiceImpl.getTeacherByName(name));
    }

    @RequestMapping(value = "/getStudentByName", method = RequestMethod.POST)
    public ResponseEntity<?> getStudentByName(@RequestBody String name) throws Exception {
        logger.info(getClass().toString()+" << Get Student By Name Controller >>");
        return ResponseEntity.ok(userServiceImpl.getStudentByName(name));
    }

    @RequestMapping(value = "/getCourseByName", method = RequestMethod.POST)
    public ResponseEntity<?> getCourseByName(@RequestBody String name) throws Exception {
        logger.info(getClass().toString()+" << Get Course By Name Controller >>");
        return ResponseEntity.ok(userServiceImpl.getCourseByName(name));
    }

    @RequestMapping(value = "/getCoursesByTeacher", method = RequestMethod.POST)
    public ResponseEntity<?> getCoursesByTeacher(@RequestBody String teacher) throws Exception {
        logger.info(getClass().toString()+" << Get Courses By Teacher Controller >>");
        return ResponseEntity.ok(userServiceImpl.getCoursesByTeacher(teacher));
    }

    @RequestMapping(value = "/getCoursesByStudent", method = RequestMethod.POST)
    public ResponseEntity<?> getCoursesByStudent(@RequestBody String student) throws Exception {
        logger.info(getClass().toString()+" << Get Courses By Student Controller >>");
        return ResponseEntity.ok(userServiceImpl.getCoursesByStudent(student));
    }

    @RequestMapping(value = "/getStudentsByCourse", method = RequestMethod.POST)
    public ResponseEntity<?> getStudentsByCourse(@RequestBody String course) throws Exception {
        logger.info(getClass().toString()+" << Get Students By Course Controller >>");
        return ResponseEntity.ok(userServiceImpl.getStudentByCourse(course));
    }

}

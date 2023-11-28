package com.auca.StudentRegistrationInSpringboot.MyController;

import com.auca.StudentRegistrationInSpringboot.MyModel.AcademicUnit;
import com.auca.StudentRegistrationInSpringboot.MyModel.Semester;
import com.auca.StudentRegistrationInSpringboot.MyModel.Student;
import com.auca.StudentRegistrationInSpringboot.MyModel.StudentRegistration;
import com.auca.StudentRegistrationInSpringboot.MyService.SemesterService;
import com.auca.StudentRegistrationInSpringboot.MyService.StudentService;
import com.auca.StudentRegistrationInSpringboot.MyService.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/student" , produces = (MediaType.APPLICATION_JSON_VALUE), consumes = (MediaType.APPLICATION_JSON_VALUE))
public class StudentController {
    private static final long serialVersionUID = 1L;
    @Autowired
    private StudentService studentService;
    @Autowired
    private SemesterService semesterService;
    @Autowired
    private UnitService academicUnitService;
    //creating
    @PostMapping(value = "/RegisteredStudent")
    public ResponseEntity<?> createStudent(@RequestBody Student student){
        if(student != null ){
            String message = studentService.saveStudent(student);
            if(message != null){
                return new ResponseEntity<>("wow!!, Student registered Successfully",HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>("sorry!!, Student Not registered",HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else {
            return new ResponseEntity<>("please check your data, same thing went wrong",HttpStatus.BAD_GATEWAY);
        }
    }

    //list
    @GetMapping(value = "/DisplayStudents")
    public ResponseEntity<List<Student>> listStudents() {
        List<Student> students = studentService.listStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
    //update
    @PutMapping(value = "/RenovateByStudentReg_number/{registrationNumber}")
    public ResponseEntity<String> updateStudent(@PathVariable String registrationNumber, @RequestBody Student student) {
        if (student != null) {
            String message = studentService.updateStudent(registrationNumber, student);
            if (message != null) {
                return new ResponseEntity<>("wow!!, Student renovated Successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("sorry!, Student Not renovated Successfully", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("please check your data, same thing went wrong", HttpStatus.BAD_REQUEST);
        }
    }
    //delete
    @DeleteMapping(value = "/RemoveStudent/{registrationNumber}")
    public ResponseEntity<String> deleteStudent(@PathVariable String registrationNumber) {
        if (registrationNumber != null) {
            String message = studentService.deleteStudent(registrationNumber);
            if (message != null) {
                return new ResponseEntity<>("wow!!, Student remove Successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("sorry!, Student Not remove Successfully", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("please check your data, same thing went wrong", HttpStatus.BAD_REQUEST);
        }
    }

    // List students by semester
    @GetMapping(value = "/DisplayBySemester/{semesterId}")
    public ResponseEntity<List<StudentRegistration>> listStudentsBySemester(@PathVariable String semesterId) {
        Semester semester = semesterService.getSemesterById(semesterId);

        if (semester != null) {
            List<StudentRegistration> students = studentService.getStudentsBySemester(semester);
            return new ResponseEntity<>(students, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/listByDepartmentAndSemester/{departmentCode}/{semesterId}")
    public ResponseEntity<List<StudentRegistration>> listStudentsByDepartmentAndSemester(
            @PathVariable String departmentCode,
            @PathVariable String semesterId) {

        AcademicUnit department = academicUnitService.getAcademicUnitByCode(departmentCode);
        Semester semester = semesterService.getSemesterById(semesterId);

        if (department != null && semester != null) {
            List<StudentRegistration> students = studentService.getStudentsByDepartmentAndSemester(department, semester);
            return new ResponseEntity<>(students, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

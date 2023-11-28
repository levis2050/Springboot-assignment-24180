package com.auca.StudentRegistrationInSpringboot.MyController;

import com.auca.StudentRegistrationInSpringboot.MyModel.*;
import com.auca.StudentRegistrationInSpringboot.MyService.CourseService;
import com.auca.StudentRegistrationInSpringboot.MyService.SemesterService;
import com.auca.StudentRegistrationInSpringboot.MyService.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(value = "/CoursePostman" , produces = (MediaType.APPLICATION_JSON_VALUE), consumes = (MediaType.APPLICATION_JSON_VALUE))
public class CourseController {
    private static final long serialVersionUID = 1L;
    @Autowired
    private CourseService courseService;
    @Autowired
    private UnitService unitService;
    @Autowired
    private SemesterService semesterService;
    //create
    @PostMapping(value = "/RegisterCourse")
    public ResponseEntity<?> createCourse(@RequestBody Course course){
        if(course != null ){
            String message = courseService.saveCourse(course);
            if(message != null){
                return new ResponseEntity<>("Wow!!!, semester registered Successfully", HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>("sorry!, Course Not Saved",HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else {
            return new ResponseEntity<>("try again, misbehave",HttpStatus.BAD_GATEWAY);
        }
    }

    //list
    @GetMapping(value = "/DisplayCourse")
    public ResponseEntity<List<Course>> listCourses() {
        List<Course> courses = courseService.listCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @PutMapping(value = "/RenovateCourse/{code}")
    public ResponseEntity<String> updateStudent(@PathVariable CourseDefinition code, @RequestBody Course course) {
        if (course != null) {
            String message = courseService.updateCourse(code, course);
            if (message != null) {
                return new ResponseEntity<>("wow!!!, Course renovated Successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("renovate course is not proceeded well", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("please check your data, same thing went wrong", HttpStatus.BAD_REQUEST);
        }
    }
    //delete
    @DeleteMapping(value = "/RemoveCourse/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Integer id) {
        if (id != null) {
            String message = courseService.deleteCourse(id);
            if (message != null) {
                return new ResponseEntity<>("wow!!!, Course Definition removed Successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("sorry, Course Definition is not removed Successfully", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("please check your data, same thing went wrong", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/DisplayByDepartmentAndSemester/{departmentCode}/{semesterId}")
    public ResponseEntity<List<Course>> listStudentsByDepartmentAndSemester(
            @PathVariable String departmentCode,
            @PathVariable String semesterId) {

        AcademicUnit department = unitService.getAcademicUnitByCode(departmentCode);
        Semester semester = semesterService.getSemesterById(semesterId);

        if (department != null && semester != null) {
            List<Course> crs = courseService.getCoursesByDepartmentAndSemester(department, semester);
            return new ResponseEntity<>(crs, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

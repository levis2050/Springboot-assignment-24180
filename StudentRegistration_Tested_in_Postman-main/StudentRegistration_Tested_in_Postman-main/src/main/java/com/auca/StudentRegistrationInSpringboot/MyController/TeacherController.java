package com.auca.StudentRegistrationInSpringboot.MyController;

import com.auca.StudentRegistrationInSpringboot.MyModel.Teacher;
import com.auca.StudentRegistrationInSpringboot.MyService.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(value = "/teacherPostman" , produces = (MediaType.APPLICATION_JSON_VALUE), consumes = (MediaType.APPLICATION_JSON_VALUE))
public class TeacherController {
    private static final long serialVersionUID = 1L;
    @Autowired
    private TeacherService teacherService;
    //creating
    @PostMapping(value = "/RegisterTeacher")
    public ResponseEntity<?> createTeacher(@RequestBody Teacher teacher){
        if(teacher != null ){
            String message = teacherService.saveTeacher(teacher);
            if(message != null){
                return new ResponseEntity<>("wow!!, Teacher registered Successfully", HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>("sorry!!, Teacher Not registered",HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else {
            return new ResponseEntity<>("please check your data, same thing went wrong",HttpStatus.BAD_GATEWAY);
        }
    }

    //list
    @GetMapping(value = "/DisplayTeachers")
    public ResponseEntity<List<Teacher>> listTeachers() {
        List<Teacher> teachers = teacherService.listTeacher();
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }
    //update
    @PutMapping(value = "/RenovateTeacher/{tr_code}")
    public ResponseEntity<String> updateTeacher(@PathVariable String tr_code, @RequestBody Teacher teacher) {
        if (teacher != null) {
            String message = teacherService.updateTeacher(tr_code, teacher);
            if (message != null) {
                return new ResponseEntity<>("wow!!, Teacher renovated Successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("sorry, Teacher Not renovated Successfully", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("please check your data, same thing went wrong", HttpStatus.BAD_REQUEST);
        }
    }
    //delete
    @DeleteMapping(value = "/RemoveTeacher/{tr_code}")
    public ResponseEntity<String> deleteTeacher(@PathVariable String tr_code) {
        if (tr_code != null) {
            String message = teacherService.deleteTeacher(tr_code);
            if (message != null) {
                return new ResponseEntity<>("Wow!!, Teacher removed Successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Sorry!!, Teacher Not removed Successfully", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("please check your data, same thing went wrong", HttpStatus.BAD_REQUEST);
        }
    }
}

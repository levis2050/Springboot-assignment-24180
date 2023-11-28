package com.auca.StudentRegistrationInSpringboot.MyController;

import com.auca.StudentRegistrationInSpringboot.MyModel.Semester;
import com.auca.StudentRegistrationInSpringboot.MyService.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(value = "/semesterPostman" , produces = (MediaType.APPLICATION_JSON_VALUE), consumes = (MediaType.APPLICATION_JSON_VALUE))
public class SemesterController {
    private static final long serialVersionUID = 1L;
    @Autowired
    private SemesterService semService;
    //creating
    @PostMapping(value = "/RegisterSemester")
    public ResponseEntity<?> createSemester(@RequestBody Semester semester){
        if(semester != null ){
            String message = semService.saveSemester(semester);
            if(message != null){
                return new ResponseEntity<>("Semester Saved Successfully", HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>("Semester Not Saved",HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else {
            return new ResponseEntity<>("please check your data, same thing went wrong",HttpStatus.BAD_GATEWAY);
        }
    }

    //list
    @GetMapping(value = "/DisplaySemesters")
    public ResponseEntity<List<Semester>> listSemester() {
        List<Semester> sems = semService.listSemesters();
        return new ResponseEntity<>(sems, HttpStatus.OK);
    }
    //update
    @PutMapping(value = "/RenovateSemester/{code}")
    public ResponseEntity<String> updateSemester(@PathVariable String code, @RequestBody Semester semester) {
        if (semester != null) {
            String message = semService.updateSemester(code, semester);
            if (message != null) {
                return new ResponseEntity<>("Semester renovated Successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("sorry!, Semester is not removed Successfully", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("please check your data, same thing went wrong", HttpStatus.BAD_REQUEST);
        }
    }
    //delete
    @DeleteMapping(value = "/RemoveSemester/{code}")
    public ResponseEntity<String> deleteSemester(@PathVariable String code) {
        if (code != null) {
            String message = semService.deleteSemester(code);
            if (message != null) {
                return new ResponseEntity<>("wow!!, Semester removed Successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("sorry!, Semester Not removed Successfully", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("please check your data, same thing went wrong", HttpStatus.BAD_REQUEST);
        }
    }
}

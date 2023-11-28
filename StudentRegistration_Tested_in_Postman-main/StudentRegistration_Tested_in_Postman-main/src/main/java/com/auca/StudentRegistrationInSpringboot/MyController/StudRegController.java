package com.auca.StudentRegistrationInSpringboot.MyController;

import com.auca.StudentRegistrationInSpringboot.MyModel.AcademicUnit;
import com.auca.StudentRegistrationInSpringboot.MyModel.Semester;
import com.auca.StudentRegistrationInSpringboot.MyModel.StudentRegistration;
import com.auca.StudentRegistrationInSpringboot.MyService.StudRegistrationService;
import com.auca.StudentRegistrationInSpringboot.MyService.SemesterService;
import com.auca.StudentRegistrationInSpringboot.MyService.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(value = "/studentRegistration" , produces = (MediaType.APPLICATION_JSON_VALUE), consumes = (MediaType.APPLICATION_JSON_VALUE))

public class StudRegController {
    private static final long serialVersionUID = 1L;
    @Autowired
    private StudRegistrationService regService;
    @Autowired
    private UnitService unitService;
    @Autowired
    private SemesterService semesterService;
    //creating
    @PostMapping(value = "/RegisterStudentRegistration")
    public ResponseEntity<?> createReg(@RequestBody StudentRegistration studentReg) {
        if (studentReg != null) {
            String message = regService.saveRegistration(studentReg);
            if (message != null && message.startsWith("wow!!, Student Registered Successfully")) {
                return new ResponseEntity<>(message, HttpStatus.CREATED);
            } else if (message != null && message.startsWith("Student with ID")) {
                return new ResponseEntity<>(message, HttpStatus.CONFLICT);
            } else {
                return new ResponseEntity<>("wow!!, Student Not Registered", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("please check your data, same thing went wrong", HttpStatus.BAD_GATEWAY);
        }
    }

    //list
    @GetMapping(value = "/DisplayRegistrations")
    public ResponseEntity<List<StudentRegistration>> listRegs() {
        List<StudentRegistration> studentReg = regService.listStudentsReg();
        return new ResponseEntity<>(studentReg, HttpStatus.OK);
    }
    //update
    @PutMapping(value = "/RenovateRegistration/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable Integer id, @RequestBody StudentRegistration regStudent) {
        if (regStudent != null) {
            String message = regService.updateStudentReg(id, regStudent);
            if (message != null) {
                return new ResponseEntity<>("wow!!, Student Registration renovated Successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("sorry!, Student Registration Not renovated Successfully", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("please check your data, same thing went wrong", HttpStatus.BAD_REQUEST);
        }
    }
    //delete
    @DeleteMapping(value = "/RemoveRegistration/{id}")
    public ResponseEntity<String> deleteStudReg(@PathVariable Integer id) {
        if (id != null) {
            String message = regService.deleteStudentReg(id);
            if (message != null) {
                return new ResponseEntity<>("wow!!, Student Registration removed Successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("sorry!!, Student Registration Not removed Successfully", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("please check your data, same thing went wrong", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/DisplayByDepartmentAndSemester/{departmentCode}/{semesterId}")
    public ResponseEntity<List<StudentRegistration>> listRegistrationsByDepartmentAndSemester(
            @PathVariable String departmentCode,
            @PathVariable String semesterId) {

        AcademicUnit department = unitService.getAcademicUnitByCode(departmentCode);
        Semester semester = semesterService.getSemesterById(semesterId);

        if (department != null && semester != null) {
            List<StudentRegistration> registrations = regService.getRegistrationsByDepartmentAndSemester(department, semester);
            return new ResponseEntity<>(registrations, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/DisplayBySemester/{semesterId}")
    public ResponseEntity<List<StudentRegistration>> listRegistrationsBySemester(

            @PathVariable String semesterId) {
        Semester semester = semesterService.getSemesterById(semesterId);

        if (semester != null) {
            List<StudentRegistration> registrations = regService.getRegistrationsBySemester(semester);
            return new ResponseEntity<>(registrations, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

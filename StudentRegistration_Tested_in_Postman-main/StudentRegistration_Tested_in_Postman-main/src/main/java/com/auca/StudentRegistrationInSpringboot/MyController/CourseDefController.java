package com.auca.StudentRegistrationInSpringboot.MyController;

import com.auca.StudentRegistrationInSpringboot.MyModel.CourseDefinition;
import com.auca.StudentRegistrationInSpringboot.MyService.CourseDefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(value = "/courseDefinitionPostman" , produces = (MediaType.APPLICATION_JSON_VALUE), consumes = (MediaType.APPLICATION_JSON_VALUE))
public class CourseDefController {
    private static final long serialVersionUID = 1L;
    @Autowired
    private CourseDefService crsDefService;
    //create Course Definition
    @PostMapping(value = "/RegisterCourseDefinition")
    public ResponseEntity<?> createDef(@RequestBody CourseDefinition crsDef){
        if(crsDef != null ){
            String message = crsDefService.saveDef(crsDef);
            if(message != null){
                return new ResponseEntity<>("Wow!!!, CourseDefinition registered Successfully", HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>("sorry, CourseDefinition is not removed Successfully",HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else {
            return new ResponseEntity<>("please check your data, same thing went wrong",HttpStatus.BAD_GATEWAY);
        }
    }

    //list Course Definition
    @GetMapping(value = "/DisplayCourseDefinition")
    public ResponseEntity<List<CourseDefinition>> listDefs() {
        List<CourseDefinition> defs = crsDefService.listDefs();
        return new ResponseEntity<>(defs, HttpStatus.OK);
    }
    //update course Definition
    @PutMapping(value = "/RenovateDefinition/{code}")
    public ResponseEntity<String> updateDef(@PathVariable String code, @RequestBody CourseDefinition crsDef) {
        if (crsDef != null) {
            String message = crsDefService.updateDef(code,crsDef);
            if (message != null) {
                return new ResponseEntity<>("wow!!!, Course Definition renovated Successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("renovate course is not proceeded well", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("please check your data, same thing went wrong", HttpStatus.BAD_REQUEST);
        }
    }
    //delete
    @DeleteMapping(value = "/deleteCourseDefinition/{code}")
    public ResponseEntity<String> deleteDef(@PathVariable String code) {
        if (code != null) {
            String message = crsDefService.deleteDef(code);
            if (message != null) {
                return new ResponseEntity<>("wow!!!, Course removed Successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("sorry, Course is not removed Successfully", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("please check your data, same thing went wrong", HttpStatus.BAD_REQUEST);
        }
    }
}

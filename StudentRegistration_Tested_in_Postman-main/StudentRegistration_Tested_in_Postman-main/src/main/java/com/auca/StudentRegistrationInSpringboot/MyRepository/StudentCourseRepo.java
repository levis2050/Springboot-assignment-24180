package com.auca.StudentRegistrationInSpringboot.MyRepository;

import com.auca.StudentRegistrationInSpringboot.MyModel.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentCourseRepo extends JpaRepository<StudentCourse, Integer> {
    boolean existsByCourse(Course course);
    List<StudentCourse> findByStudentRegistration(StudentRegistration studentRegistration);
    List<StudentCourse> findByCourseAndStudentRegistration(Course course, StudentRegistration studentRegistration);
}

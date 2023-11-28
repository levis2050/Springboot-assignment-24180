package com.auca.StudentRegistrationInSpringboot.MyRepository;

import com.auca.StudentRegistrationInSpringboot.MyModel.CourseDefinition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseDefRepo extends JpaRepository<CourseDefinition,String> {
}

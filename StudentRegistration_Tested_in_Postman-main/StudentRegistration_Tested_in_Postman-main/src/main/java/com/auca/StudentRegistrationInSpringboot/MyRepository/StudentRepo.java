package com.auca.StudentRegistrationInSpringboot.MyRepository;

import com.auca.StudentRegistrationInSpringboot.MyModel.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student, String> {
}

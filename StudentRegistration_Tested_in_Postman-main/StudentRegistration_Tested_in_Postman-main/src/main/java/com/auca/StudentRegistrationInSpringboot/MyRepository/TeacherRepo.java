package com.auca.StudentRegistrationInSpringboot.MyRepository;

import com.auca.StudentRegistrationInSpringboot.MyModel.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher, int> {
}

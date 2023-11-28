package com.auca.StudentRegistrationInSpringboot.MyRepository;

import com.auca.StudentRegistrationInSpringboot.MyModel.AcademicUnit;
import com.auca.StudentRegistrationInSpringboot.MyModel.Semester;
import com.auca.StudentRegistrationInSpringboot.MyModel.StudentRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRegistrationRepo extends JpaRepository<StudentRegistration,Integer> {
    boolean existsByDepartmentAndSemester(AcademicUnit department, Semester semester);
    boolean existsByStudentId(String studentId);
    StudentRegistration findByStudentId(String studentId);

    List<StudentRegistration> findBySemester(Semester semester);
    List<StudentRegistration> findByDepartmentAndSemester(AcademicUnit department, Semester semester);
    List<StudentRegistration> findBySemesterId(String semesterId);

}

package com.auca.StudentRegistrationInSpringboot.MyRepository;

import com.auca.StudentRegistrationInSpringboot.MyModel.AcademicUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepo extends JpaRepository<AcademicUnit,String> {
}

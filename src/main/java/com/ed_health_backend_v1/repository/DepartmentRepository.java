package com.ed_health_backend_v1.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ed_health_backend_v1.modal.Department;
import com.ed_health_backend_v1.modal.Faculty;
import com.ed_health_backend_v1.modal.School;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,UUID>{

    List<Department> findAllByFacultySchool(School school);

    List<Department> findAllByFaculty(Faculty faculty);

}

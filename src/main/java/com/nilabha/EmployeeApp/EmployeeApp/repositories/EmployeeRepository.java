package com.nilabha.EmployeeApp.EmployeeApp.repositories;

import com.nilabha.EmployeeApp.EmployeeApp.entity.EmployeeEnitity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEnitity,Long> {

}

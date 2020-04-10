package com.ms.dp.cache.aside.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms.dp.cache.aside.pojo.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {


}

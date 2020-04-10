package com.ms.dp.cache.aside.service;

import com.ms.dp.cache.aside.pojo.Employee;

public interface EmployeeService {

	Employee findOne(Integer id);

	Employee save(Employee e);

	Employee update(Employee e);

}
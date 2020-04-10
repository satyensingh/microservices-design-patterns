package com.ms.dp.cache.aside.service;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ms.dp.cache.aside.pojo.Employee;
import com.ms.dp.cache.aside.repo.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository repository;
	private Logger log = Logger.getLogger("EmployeeService");

	@Override
	@Cacheable("employees")
	@Transactional(readOnly = true)
	public Employee findOne(Integer id) {
		log.info("Find employee by id - "+id);
		return repository.findById(id).get();
	}

	@Override
	@CachePut("employees")
	@Transactional
	public Employee save(Employee e) {
		return repository.save(e);
	}

	@Override
	@CachePut("employees")
	@Transactional
	public Employee update(Employee e) {
		return repository.save(e);
	}
}

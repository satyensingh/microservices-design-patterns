package com.ms.dp.cache.aside;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import com.ms.dp.cache.aside.pojo.Employee;
import com.ms.dp.cache.aside.service.EmployeeService;

@SpringBootApplication
@EnableCaching
public class CacheAsideApplication implements CommandLineRunner {

	@Autowired
	private EmployeeService service;
	private Logger log = Logger.getLogger("CacheAsideApplication");

	public static void main(String[] args) {
		SpringApplication.run(CacheAsideApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("=== Save Few Employees =====");
		service.save(new Employee(1, "Satyen Singh", 10000));
		service.save(new Employee(2, "Dinesh Singh", 20000));

		log.info("=== Find employees ||| Cache HIT/MISS =====");
		log.info("emp#-1 --> "+ service.findOne(1));
		log.info("emp#-2 --> "+ service.findOne(2));
		log.info("emp#-1 --> "+ service.findOne(1));
		log.info("emp#-2 --> "+ service.findOne(2));
		log.info("emp#-1 --> "+ service.findOne(1));
		log.info("emp#-2 --> "+ service.findOne(2));

		// write
		log.info("=== Adding new employee ====");
		Employee e = new Employee();
		e.setEmpId(3);
		e.setEmpName("Satyen Singh");

		service.save(e);

		log.info("=== Find new joinee =====");
		e = service.findOne(3); // this is a detached object
		log.info("emp#-3 --> "+ e);

		log.info("=== Update new employee ====");
		e.setEmpName("Vikram Singh"); 
		service.update(e);

		log.info("=== Check :: Update new employee ====");
		e = service.findOne(3); // check if update is successful
		log.info("emp#-3 --> "+ e);
		
		log.info("=== Find employees ||| Cache HIT/MISS =====");
		log.info("emp#-1 --> "+ service.findOne(1));
		log.info("emp#-2 --> "+ service.findOne(2));
		log.info("emp#-1 --> "+ service.findOne(1));
		log.info("emp#-2 --> "+ service.findOne(2));
		log.info("emp#-1 --> "+ service.findOne(1));
		log.info("emp#-2 --> "+ service.findOne(2));
	}
}
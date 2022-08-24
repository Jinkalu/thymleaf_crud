package com.emp.main.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emp.main.model.Employee2;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee2, Long>{
	
			
		
	
}

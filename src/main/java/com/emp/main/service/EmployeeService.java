package com.emp.main.service;

import java.util.List;

import org.springframework.data.domain.Page;
import com.emp.main.model.Employee2;



public interface EmployeeService {
		List<Employee2> getAllEmp();
		void saveEmp(Employee2 e);

		Employee2 getById(long id);

		void update(Employee2 e);

   		 void delete(long id);
   		 Page<Employee2> findPageIn(int pageNo,int pageSize,String sortfield,String sortDir);
}

package com.emp.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import com.emp.main.model.Employee2;
import com.emp.main.repo.EmployeeRepo;
@Service
public class ServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepo eRepo;
	
	@Override
	public List<Employee2> getAllEmp() {
		
		return eRepo.findAll();
	}

	@Override
	public void saveEmp(Employee2 e) {
		
		eRepo.save(e);
		
	}

	@Override
	public Employee2 getById(long id) {
		Employee2 e= eRepo.getReferenceById(id);
		return e;
	}

	@Override
	public void update(Employee2 e) {



	}

	@Override
	public void delete(long id) {

		eRepo.deleteById(id);
	}

	@Override
	public Page<Employee2> findPageIn(int pageNo, int pageSize, String sortfield, String sortDir) {
		//sorting
		Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortfield).ascending():Sort.by(sortfield).descending();

		//pagination
		Pageable pa=PageRequest.of(pageNo-1, pageSize,sort);
		return this.eRepo.findAll(pa);

	}



}

package com.example.crudwiththymeleaf.service;

import com.example.crudwiththymeleaf.model.Employee;
import com.example.crudwiththymeleaf.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class EmployeeServiceImpl  implements  EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Get all employee
     * @return list employee
     */
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    /**
     * Save employee into database
     * @param employee
     */
    @Override
    public void saveEmployee(Employee employee) {
        this.employeeRepository.save(employee);
    }

    /**
     * Deleted employee by id
     * @param id
     * @return
     */
    @Override
    public Employee getEmployeeById(long id) {
        Optional < Employee > optional = employeeRepository.findById(id);
        Employee employee = null;
        // If employee have in database
        if (optional.isPresent()) {
            //assign employee into employee variable using method get of option
            employee = optional.get();
        } else {
            throw new RuntimeException(" Employee not found for id :: " + id);
        }
        return employee;
    }

    /**
     * Function handle about delete employee with id specify
     * @param id
     */
    @Override
    public void deleteEmployeeById(long id) {
        this.employeeRepository.deleteById(id);
    }

    /**
     * Function handle Pagination
     * @param pageNo
     * @param pageSize
     * @param sortField
     * @param sortDirection
     * @return
     */
    @Override
    public Page < Employee > findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.employeeRepository.findAll(pageable);
    }

}

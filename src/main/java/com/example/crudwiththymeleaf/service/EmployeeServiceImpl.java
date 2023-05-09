package com.example.crudwiththymeleaf.service;

import com.example.crudwiththymeleaf.model.Employee;
import com.example.crudwiththymeleaf.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class EmployeeServiceImpl  implements  EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public void saveEmployee(Employee employee) {
        this.employeeRepository.save(employee);
    }

    /**
     * Xóa employee theo id
     * @param id
     * @return
     */
    @Override
    public Employee getEmployeeById(long id) {
        Optional<Employee> optional = employeeRepository.findById(id);
        Employee employee = null;
        if(optional.isPresent()){
            //lấy giá trị của đối tượng optional gắn vào employee
            employee = optional.get();
        }else
        {
            throw new RuntimeException("Employee not found for id: "  + id);
        }
        return employee;
    }

    @Override
    public void deleteEmployeeById(long id) {
        this.employeeRepository.deleteById(id);

    }

}

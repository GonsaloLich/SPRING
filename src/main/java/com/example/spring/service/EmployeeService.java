package com.example.spring.service;

import com.example.spring.exeptions.EmployeeAlreadyAddedException;
import com.example.spring.exeptions.EmployeeNotFoundException;
import com.example.spring.exeptions.EmployeeStorageIsFullException;
import com.example.spring.model.Employee;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {
    private static final int MAX_COUNT = 10;

    private final Map<String, Employee> employees = new HashMap<>(MAX_COUNT);

    public void add(String firstName, String lastName, int salary, int department) throws EmployeeAlreadyAddedException, WrongNameException {
        if(StringUtils.isAlpha(firstName) || !StringUtils.isAlpha(lastName)){
            throw new WrongNameException ("Name of last name must contain letters");
        }


        if (employees.size() >= MAX_COUNT ) {
            throw new EmployeeStorageIsFullException();
        }
        Employee employee = new Employee(StringUtils.capitalize(firstName),
                StringUtils.capitalize(lastName),
                salary,
                department);
        var key = makeKey(firstName, lastName);
        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(key, employee);


    }

    public void remove(String firstName, String lastName) {
        var key = makeKey(firstName, lastName);
        var removed = employees.remove(key);
        if (removed == null) {
            throw new EmployeeNotFoundException();
        }


        /*
        var it = employees.iterator();
        boolean removed = false;
        while (it.hasNext()) {
            var employee = it.next();
            if(employee.getFirstName().equals(firstname) && employee.getLastName().equals(lastName)){
                it.remove();
                removed = true;
            }
        }
        if(!removed) {
            throw new EmployeeNotFoundException();
        }

         */

    }


    public Employee find(String firstName, String lastName) {
        var key = makeKey(firstName, lastName);
        var employee = employees.get(key);
        if (employee != null) {
            return employee;
  //      if (employees.containsKey(key)) {
  //            return employees.get(key);
        }
        throw new EmployeeNotFoundException();
    }

    public Collection<Employee> getAll(){
        return Collections.unmodifiableCollection(employees.values());
    }
    private static String makeKey(String firstName, String lastName) {
        return (firstName + "_" + lastName).toLowerCase();
    }
}

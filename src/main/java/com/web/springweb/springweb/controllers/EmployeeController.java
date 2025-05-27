package com.web.springweb.springweb.controllers;

import com.web.springweb.springweb.dto.EmployeeDTO;
import com.web.springweb.springweb.entities.EmployeeEntity;
import com.web.springweb.springweb.repositories.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/employees/{employeeID}")
 public EmployeeEntity getEmployeeByID(@PathVariable Long employeeID){

      return employeeRepository.findById(employeeID).orElse(null);
 }

 @GetMapping("/employees")
 public List<EmployeeEntity> getAllEmployees(@RequestParam(required = false) Integer age, @RequestParam(required = false) String sortBy){

     return employeeRepository.findAll();
 }

 @PostMapping("/employees")
 public EmployeeEntity createEmployee(@RequestBody EmployeeEntity emp){
     return employeeRepository.save(emp);
 }

}

package com.web.springweb.springweb.controllers;

import com.web.springweb.springweb.dto.EmployeeDTO;
import com.web.springweb.springweb.entities.EmployeeEntity;
import com.web.springweb.springweb.repositories.EmployeeRepository;
import com.web.springweb.springweb.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees/{employeeID}")
 public ResponseEntity<EmployeeDTO> getEmployeeByID(@PathVariable Long employeeID){

       Optional<EmployeeDTO> employeeDTO =  employeeService.getEmployeeByID(employeeID);
       return employeeDTO
               .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
               .orElse(ResponseEntity.notFound().build());
 }


 @GetMapping("/employees")
 public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(required = false) Integer age, @RequestParam(required = false) String sortBy){

     return ResponseEntity.ok(employeeService.getAllEmployees());
 }


 @PostMapping("/employees")
 public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO emp){
     EmployeeDTO employeeDTO = employeeService.createEmployee(emp);
     return new ResponseEntity<>(employeeDTO, HttpStatus.CREATED);
 }


 @PutMapping("/employees/{employeeID}")
 public ResponseEntity <EmployeeDTO> updateEmployeeByID(@RequestBody EmployeeDTO employee, @PathVariable Long employeeID){

        return ResponseEntity.ok(employeeService.updateEmployeeByID(employee, employeeID));
 }


 @PatchMapping("/employees/{employeeID}")
 public ResponseEntity <EmployeeDTO> updatePartialEmployeeByID(@RequestBody Map<String, Object> employeeUpdates, @PathVariable Long employeeID)  {
        EmployeeDTO employeeDTO =  employeeService.updatePartialEmployeeByID(employeeID, employeeUpdates);
        if( employeeDTO == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employeeDTO);
 }


@DeleteMapping("/employees/{employeeID}")
public ResponseEntity<Boolean> deleteEmployee(@PathVariable long employeeID){
        if(employeeService.deleteEmployee(employeeID)){
            ResponseEntity.ok(true);
        }
        return ResponseEntity.notFound().build();
}

}

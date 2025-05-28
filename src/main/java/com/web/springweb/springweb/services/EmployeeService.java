package com.web.springweb.springweb.services;

import com.web.springweb.springweb.dto.EmployeeDTO;
import com.web.springweb.springweb.entities.EmployeeEntity;
import com.web.springweb.springweb.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper mapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper mapper) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    public Optional<EmployeeDTO> getEmployeeByID(Long employeeID) {
        Optional<EmployeeEntity> employeeEntity= employeeRepository.findById(employeeID);
        return employeeEntity.map((employeeEntity1 -> mapper.map(employeeEntity1, EmployeeDTO.class)));
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities.stream().map((EmployeeEntity employee) -> mapper.map(employee, EmployeeDTO.class)).collect(Collectors.toList());
    }

    public EmployeeDTO createEmployee(EmployeeDTO emp) {
        EmployeeEntity employeeEntity = mapper.map(emp, EmployeeEntity.class);
        return mapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployeeByID(EmployeeDTO employee, Long employeeID) {

        EmployeeEntity employeeEntity = mapper.map(employee, EmployeeEntity.class);
        employeeEntity.setId(employeeID);
        return mapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);
    }

    public boolean isExistsByEmployeeID(long employeeID){

        return !employeeRepository.existsById(employeeID);
    }

    public boolean deleteEmployee(long employeeID) {

        if(isExistsByEmployeeID(employeeID)){
            return false;
        }
        try {
            employeeRepository.deleteById(employeeID);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return true;
    }

    public EmployeeDTO updatePartialEmployeeByID(Long employeeID, Map<String, Object> employeeUpdates) {

        if(isExistsByEmployeeID(employeeID)){
            return null;
        }

        EmployeeEntity employeeEntity = employeeRepository.findById(employeeID).get();
        employeeUpdates.forEach((field, value) -> {
            Field fieldToBeUpdated  = ReflectionUtils.findField(EmployeeEntity.class, field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);
        });

        return mapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);
    }
}

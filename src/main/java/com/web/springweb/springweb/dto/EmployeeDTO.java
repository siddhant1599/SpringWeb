package com.web.springweb.springweb.dto;

import java.time.LocalDate;

public class EmployeeDTO {

    private Long id;

    private String name;

    private String email;

    private Integer age;

    private LocalDate dateOfJoining;

    private Boolean isActive;

    public EmployeeDTO(Boolean isActive, LocalDate dateOfJoining, Integer age, String email, String name, Long id) {
        this.isActive = isActive;
        this.dateOfJoining = dateOfJoining;
        this.age = age;
        this.email = email;
        this.name = name;
        this.id = id;
    }

    public EmployeeDTO(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}

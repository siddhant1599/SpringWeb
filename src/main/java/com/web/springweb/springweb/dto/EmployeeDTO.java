package com.web.springweb.springweb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.web.springweb.springweb.annotation.EmployeeRoleValidation;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class EmployeeDTO {

    private Long id;

    @NotBlank
    @NotNull(message = "Required field for employee: name")
    @Size(min = 3, max = 10, message = "Invalid name size")
    private String name;

    @Email(message = "should be a valid email")
    private String email;

    @Min(value = 18, message = "minimum age should be 18")
    private Integer age;

    @PastOrPresent(message = "Date of Joining should be not in future")
    private LocalDate dateOfJoining;

    @NotBlank
    //@Pattern(regexp = "^ADMIN|USER$", message = "Role of Employee should either be USER or ADMIN")
    @EmployeeRoleValidation
    private String role;

    @Positive
    @Digits(integer = 6, fraction = 2)
    @DecimalMin("100.50")
    private Double salary;

    @AssertTrue
    @JsonProperty("isActive")
    private Boolean isActive;
}

package com.nilabha.EmployeeApp.EmployeeApp.dto;

import com.nilabha.EmployeeApp.EmployeeApp.annotations.EmployeeRoleValidation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class EmployeeDTO {
private Long id;
@NotBlank(message = "The name of the person can't be left blank")
@Size(min = 3,max = 20,message = "Name should be in this range:[3,10")
private String name;
@NotBlank(message = "Email can't be blank")
@Email(message = "Email should be a valid email")
private String email;
@NotNull(message = "Age can't be blank")
@Max(value = 80,message = "Age can't be more than 80 years")
@Min(value=18,message = "Age can't be less than 20 years")
private Integer age;
@NotBlank(message = "role field can't be blank")
@EmployeeRoleValidation
private String role;
@NotNull
@Positive
@Digits(integer = 6,fraction = 2,message = "The salary can only be in form of XXXXXX-YY")
@DecimalMax(value = "100000.99")
@DecimalMin(value = "1000.99")
private Double salary;
@PastOrPresent(message = "Date can only be from past and present only, no future dates are allowed")
private LocalDate dateOfJoining;
@AssertTrue(message = "The active field can only be true not false")
private Boolean isActive;
}

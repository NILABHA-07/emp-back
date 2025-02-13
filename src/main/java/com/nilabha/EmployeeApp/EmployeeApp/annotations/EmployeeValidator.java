package com.nilabha.EmployeeApp.EmployeeApp.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.Null;

import java.util.List;

public class EmployeeValidator implements ConstraintValidator<EmployeeRoleValidation, String> {
    @Override
    public boolean isValid(String inputRole, ConstraintValidatorContext constraintValidatorContext) {
        if (inputRole == null) {
            return false;
        }
        List<String> roles = List.of("ADMIN", "USER","MANAGER");
        return roles.contains(inputRole);
    }
}

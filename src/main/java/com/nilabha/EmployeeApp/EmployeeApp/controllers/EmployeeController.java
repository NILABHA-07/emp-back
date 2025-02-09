package com.nilabha.EmployeeApp.EmployeeApp.controllers;

import com.nilabha.EmployeeApp.EmployeeApp.dto.EmployeeDTO;
import com.nilabha.EmployeeApp.EmployeeApp.exceptions.ResourceNotFoundException;
import com.nilabha.EmployeeApp.EmployeeApp.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id){
        Optional<EmployeeDTO> employeeDTO=employeeService.getEmployeeById(id);
        return employeeDTO
                .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
                .orElseThrow(()->new ResourceNotFoundException("Employee is not found with id: "+id));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
        return  ResponseEntity.ok(employeeService.getAllEmployee());
    }
    @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody @Valid EmployeeDTO employeeDTO){
        EmployeeDTO savedEmployee=employeeService.createNewEmployee(employeeDTO);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }


}

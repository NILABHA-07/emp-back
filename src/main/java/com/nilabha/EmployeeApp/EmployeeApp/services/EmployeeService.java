package com.nilabha.EmployeeApp.EmployeeApp.services;

import com.nilabha.EmployeeApp.EmployeeApp.dto.EmployeeDTO;
import com.nilabha.EmployeeApp.EmployeeApp.entity.EmployeeEnitity;
import com.nilabha.EmployeeApp.EmployeeApp.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
private final EmployeeRepository employeeRepository;
private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<EmployeeDTO> getEmployeeById(Long id){
        return employeeRepository.findById(id).map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDTO.class));
    }

    public List<EmployeeDTO> getAllEmployee(){
        return employeeRepository.findAll().stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDTO.class))
                .collect(Collectors.toList());

    }
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO){
        EmployeeEnitity toSaveEntity=modelMapper.map(employeeDTO, EmployeeEnitity.class);
        EmployeeEnitity savedEntity =employeeRepository.save(toSaveEntity);
        return modelMapper.map(savedEntity,EmployeeDTO.class);
    }




}

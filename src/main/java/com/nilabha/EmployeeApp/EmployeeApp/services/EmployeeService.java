package com.nilabha.EmployeeApp.EmployeeApp.services;

import com.nilabha.EmployeeApp.EmployeeApp.dto.EmployeeDTO;
import com.nilabha.EmployeeApp.EmployeeApp.entity.EmployeeEnitity;
import com.nilabha.EmployeeApp.EmployeeApp.exceptions.ResourceNotFoundException;
import com.nilabha.EmployeeApp.EmployeeApp.repositories.EmployeeRepository;
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
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        return employeeRepository.findById(id).map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class));
    }

    public List<EmployeeDTO> getAllEmployee() {
        return employeeRepository.findAll().stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class))
                .collect(Collectors.toList());

    }

    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        EmployeeEnitity toSaveEntity = modelMapper.map(employeeDTO, EmployeeEnitity.class);
        EmployeeEnitity savedEntity = employeeRepository.save(toSaveEntity);
        return modelMapper.map(savedEntity, EmployeeDTO.class);
    }

    public Boolean existById(Long id) {
        boolean exists = employeeRepository.existsById(id);
        if (!exists) throw new ResourceNotFoundException("No results found with id :" + id);
        return true;
    }

    public EmployeeDTO updateEmployeeById(Long employeeId, EmployeeDTO employeeDTO) {
        existById(employeeId);
        EmployeeEnitity employeeEnitity = modelMapper.map(employeeDTO, EmployeeEnitity.class);
        employeeEnitity.setId(employeeId);
        EmployeeEnitity savedEntity = employeeRepository.save(employeeEnitity);
        return modelMapper.map(savedEntity, EmployeeDTO.class);

    }

    public String deleteEmployeeById(Long employeeId) {
        existById(employeeId);
        employeeRepository.deleteById(employeeId);
        return "Deleted";

    }

    public EmployeeDTO updatePartialEmployee(Long employeeId, Map<String, Object> updates) {
        existById(employeeId);
        EmployeeEnitity employeeEnitity = employeeRepository.findById(employeeId).get();
        updates.forEach((field, value) -> {
            Field fieldsToBeUpdated = ReflectionUtils.findField(EmployeeEnitity.class, field);
            fieldsToBeUpdated.setAccessible(Boolean.TRUE);
            ReflectionUtils.setField(fieldsToBeUpdated, employeeEnitity, value);
        });
        return modelMapper.map(employeeEnitity, EmployeeDTO.class);
    }

}

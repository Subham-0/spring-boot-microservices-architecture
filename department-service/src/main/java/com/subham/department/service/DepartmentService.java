package com.subham.department.service;

import com.subham.department.entity.Department;
import com.subham.department.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public Department saveDepartment(Department department){
        log.info("Inside saveDepartment method of DepartmentService");
        return departmentRepository.save(department);
    }

    public Department findDepartmentById(Long departmentId){
        log.info("Inside findDepartmentById method of DepartmentService");
        return departmentRepository.findByDepartmentId(departmentId);
    }
}

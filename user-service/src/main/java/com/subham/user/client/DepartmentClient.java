package com.subham.user.client;

import com.subham.user.VO.Department;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DEPARTMENT-SERVICE")
public interface DepartmentClient {

    @GetMapping("/departments/{id}")
    Department getDepartment(@PathVariable("id") Long id);
}

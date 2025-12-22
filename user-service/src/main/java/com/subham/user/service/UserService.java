package com.subham.user.service;

import com.subham.user.VO.Department;
import com.subham.user.VO.ResponseTemplateVO;
import com.subham.user.client.DepartmentClient;
import com.subham.user.entity.User;
import com.subham.user.repository.UserRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final DepartmentClient departmentClient;


    public User saveUser(User user){
        log.info("Inside saveUser of UserService");
        return userRepository.save(user);
    }

    @CircuitBreaker(
            name = "departmentService",
            fallbackMethod = "getDepartmentFallback"
    )
    public ResponseTemplateVO getUserWithDepartment(Long userId){
        log.info("Inside getUserWithDepartment of UserService");

        ResponseTemplateVO vo = new ResponseTemplateVO();

        User user = userRepository.findByUserId(userId);

        Department department =
                departmentClient.getDepartment(user.getDepartmentId());

        vo.setUser(user);
        vo.setDepartment(department);

        return vo;
    }

    public ResponseTemplateVO getDepartmentFallback(Long userId, Throwable ex) {
        log.error("Department service is DOWN", ex);

        User user = userRepository.findByUserId(userId);

        Department dept = new Department();
        dept.setDepartmentName("Department service unavailable");

        ResponseTemplateVO vo = new ResponseTemplateVO();
        vo.setUser(user);
        vo.setDepartment(dept);

        return vo;
    }

}

package com.akdenizbank.mls.user.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.akdenizbank.mls.user.EmployeeUser;
import com.akdenizbank.mls.user.repository.EmployeeUserRepository;

// What the F is problem here why it dosent work ? aAAAA 
@Service
public class EmployeeUserService {

    private static EmployeeUserRepository employeeUserRepository;

    public EmployeeUserService(EmployeeUserRepository employeeUserRepository) {
        EmployeeUserService.employeeUserRepository = employeeUserRepository;
    }

    public static EmployeeUser create(EmployeeUser employeeUser) {
        return employeeUserRepository.save(employeeUser);
    }

    public EmployeeUser getById(String id) {
        return employeeUserRepository.findById(id).orElse(null);
    }

    public Page<EmployeeUser> getAll(Pageable pageable) {
        return employeeUserRepository.findAll(pageable);
    }

    public void delete(String id) {
        EmployeeUser employeeUserInDB = getById(id);
        if (employeeUserInDB == null) {
            throw new RuntimeException("No Such Employee");
        }
        employeeUserRepository.delete(employeeUserInDB);
    }

}
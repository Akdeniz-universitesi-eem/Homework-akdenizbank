package com.akdenizbank.mls.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import com.akdenizbank.mls.user.EmployeeUser;
import com.akdenizbank.mls.user.service.EmployeeUserService;
import com.akdenizbank.mls.xaction.UpdateEmployeeXAction;
import com.akdenizbank.mls.generic.rest.GenericApiResponse;

@RestController
@RequestMapping("/api/v1/employee-users")
public class EmployeeUserController {

    private EmployeeUserService employeeUserService;

    @Autowired
    public EmployeeUserController(EmployeeUserService employeeUserService) {
        this.employeeUserService = employeeUserService;
    }

    @PostMapping
    public GenericApiResponse createEmployeeUser(@RequestBody EmployeeUser employeeUser) {
        EmployeeUser createdEmployeeUser = EmployeeUserService.create(employeeUser);
        return new GenericApiResponse(200, "Employee User Created Successfully", "2135646548", createdEmployeeUser);
    }

    @GetMapping
    public GenericApiResponse getAllEmployeeUsers(Pageable pageable) {
        Page<EmployeeUser> employeeUsersPage = employeeUserService.getAll(pageable);
        return new GenericApiResponse(200, "Success", "32861549", employeeUsersPage);
    }

    @GetMapping("/{id}")
    public GenericApiResponse getEmployeeUserById(@PathVariable String id) {
        EmployeeUser employeeUserInDB = employeeUserService.getById(id);
        if (employeeUserInDB == null) {
            throw new RuntimeException("No Such Employee User");
        }
        return new GenericApiResponse(200, "Success", "234861423", employeeUserInDB);
    }

    @PatchMapping("/{id}")
    public GenericApiResponse updateEmployeeUser(@PathVariable String id, @RequestBody UpdateEmployeeXAction xaction) {
        EmployeeUser employeeUserInDB = employeeUserService.getById(id);
        if (employeeUserInDB == null) {
            throw new RuntimeException("No Such Employee User");
        }

        employeeUserInDB.setEmail(xaction.getEmail());
        employeeUserInDB.setRole(xaction.getRole());

        employeeUserInDB = EmployeeUserService.create(employeeUserInDB);
        return new GenericApiResponse(200, "Success", "456897", employeeUserInDB);
    }

    @DeleteMapping("/{id}")
    public GenericApiResponse deleteEmployeeUser(@PathVariable String id) {
        employeeUserService.delete(id);
        return new GenericApiResponse(200, "Success", "9237549");
    }
}

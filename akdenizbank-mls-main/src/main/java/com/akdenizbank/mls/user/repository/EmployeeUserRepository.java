package com.akdenizbank.mls.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.akdenizbank.mls.user.EmployeeUser;

public interface EmployeeUserRepository extends JpaRepository<EmployeeUser, String> {

}

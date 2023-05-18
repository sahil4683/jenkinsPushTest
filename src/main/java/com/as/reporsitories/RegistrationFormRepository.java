package com.as.reporsitories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.as.domain.Registration;

public interface RegistrationFormRepository extends JpaRepository<Registration, Long> {

	Registration findByProjectName(String projectName);

}
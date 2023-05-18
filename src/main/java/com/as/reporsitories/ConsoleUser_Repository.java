package com.as.reporsitories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.as.entities.ConsoleUser;

public interface ConsoleUser_Repository extends JpaRepository<ConsoleUser, Integer> {

	ConsoleUser findByUsername(String username);
	
	ConsoleUser findById(Long id);
}

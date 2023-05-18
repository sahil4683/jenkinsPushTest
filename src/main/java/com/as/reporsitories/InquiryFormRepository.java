package com.as.reporsitories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.as.domain.Inquiry;

public interface InquiryFormRepository extends JpaRepository<Inquiry, Long> {
	
	Page<Inquiry> findByCustomerNameContainingIgnoreCase(String nama, Pageable pageable);
	
	Inquiry findByCustomerNameAndMobileNumber(String customerName, Long mobileNumber);

}

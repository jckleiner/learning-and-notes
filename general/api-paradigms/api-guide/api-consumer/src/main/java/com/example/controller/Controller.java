package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.companies.CompanyApiClient;
import com.example.api.model.Company;


@RestController
public class Controller {

	@Autowired
	private CompanyApiClient companyApiClient;


	@GetMapping("/{companyId}")
	public ResponseEntity<Company> callServerWithFeignClient(@PathVariable String companyId) {
		ResponseEntity<Company> companyResponseEntity = companyApiClient.getCompany(companyId);

		return companyResponseEntity;
	}

}

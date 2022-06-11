package com.example.myservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.companies.CompanyApiClient;
import com.example.api.model.Company;


@RestController
public class Controller {

	@Autowired
	private CompanyApiClient companyApiClient;


	public Controller(CompanyApiClient companyApiClient) {
		this.companyApiClient = companyApiClient;
	}


	@GetMapping("/")
	public ResponseEntity<Company> call() {

		System.out.println("calling...");
		ResponseEntity<Company> companyResponseEntity = companyApiClient.getCompany("1");

		return companyResponseEntity;
	}

}

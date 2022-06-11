package com.example.apiguide.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.apiguide.service.CompanyService;

import de.lmwl.api.companies.CompanyApi;
import de.lmwl.api.model.Company;


@RestController
public class CompanyController implements CompanyApi {

	private final CompanyService companyService;


	@Autowired
	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}


	@Override
	public ResponseEntity<List<Company>> getCompanies() {
		List<Company> companies = companyService.getCompanies();

		return ResponseEntity.ok(companies);
	}


	@Override
	public ResponseEntity<Company> getCompany(String id) {
		Company company = companyService.getCompany(id);

		return ResponseEntity.ok(company);
	}
}

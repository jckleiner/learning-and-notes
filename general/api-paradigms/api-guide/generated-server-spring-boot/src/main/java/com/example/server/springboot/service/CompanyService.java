package com.example.server.springboot.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.api.model.Company;


@Service
public class CompanyService {

	private final Map<String, Company> companies = new HashMap<>();


	CompanyService() {
		setCompaniesMap();
	}


	public Company getCompany(String id) {
		return companies.get(id);
	}


	public List<Company> getCompanies() {
		return new ArrayList<>(companies.values());
	}


	private void setCompaniesMap() {
		Company google = new Company();
		google.setId("1");
		google.setDepartments(null);
		google.setName("Google");

		Company atlassian = new Company();
		atlassian.setId("2");
		atlassian.setDepartments(null);
		atlassian.setName("Atlassian");

		Company netflix = new Company();
		netflix.setId("3");
		netflix.setDepartments(null);
		netflix.setName("Netflix");

		companies.put("1", google);
		companies.put("2", atlassian);
		companies.put("3", netflix);
	}

}

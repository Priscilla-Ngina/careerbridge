package com.careerBridge.careerBridge.controller;

import com.careerBridge.careerBridge.entity.Company;
import com.careerBridge.careerBridge.entity.Student;
import com.careerBridge.careerBridge.service.CompanyService;
import com.careerBridge.careerBridge.dto.CompanyRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @PostMapping
    public Company addCompany(@Valid @RequestBody CompanyRequest request){
        Company company= new Company(
                request.getCompanyName(),
                request.getEmail(),
                request.getPhoneNumber(),
                request.getLocation(),
                request.getIndustry(),
                request.getWebsite()
        );
        return companyService.saveCompany(request);
    }

    @GetMapping
    public List<Company> getAllCompanies(){
        return companyService.getAllCompanies();
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable Long id){
        return companyService.getCompanyById(id);
    }

    @PutMapping("/{id}")
    public Company updateCompany(@PathVariable Long id,@RequestBody Company company){
        return companyService.updateCompany(id, company);
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable Long id){
        companyService.deleteCompany(id);
    }
}

package com.insurance.pc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.pc.dto.CoverageDto;
import com.insurance.pc.dto.request.coverage.CreateCoverageRequest;
import com.insurance.pc.service.ICoverageService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/coverages")
@CrossOrigin
public class CoverageController {
	
	@Autowired
	private ICoverageService coverageService;
	
	
	@GetMapping("/all")
    public ResponseEntity<List<CoverageDto>> getAllCoverages() {
        List<CoverageDto> coverages = coverageService.getAllCoverages();
        return ResponseEntity.ok(coverages);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoverageDto> getCoverageById(@PathVariable String id) {
        CoverageDto coverage = coverageService.getCoverageById(id);
        return ResponseEntity.ok(coverage);
    }

    @PostMapping("/add-new-coverage")
    public ResponseEntity<CoverageDto> createCoverage(@Valid @ModelAttribute CreateCoverageRequest coverageDto) {
        CoverageDto createdCoverage = coverageService.createCoverage(coverageDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCoverage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CoverageDto> updateCoverage(@PathVariable String id, @ModelAttribute CreateCoverageRequest coverageDto) {
        CoverageDto updatedCoverage = coverageService.updateCoverage(id, coverageDto);
        return ResponseEntity.ok(updatedCoverage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoverage(@PathVariable String id) {
        coverageService.deleteCoverage(id);
        return ResponseEntity.noContent().build();
    }
	

}

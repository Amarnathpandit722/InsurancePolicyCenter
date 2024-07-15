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

import com.insurance.pc.dto.CoverablesDto;
import com.insurance.pc.dto.request.coverables.CreateCoverablesRequest;
import com.insurance.pc.service.ICoverablesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/coverables")
@CrossOrigin
public class CoverablesController {

	@Autowired
	private ICoverablesService coverablesService;

	@PostMapping("/add")
	public ResponseEntity<CoverablesDto> createCoverables(@Valid @ModelAttribute CreateCoverablesRequest coverablesRequest) {
		CoverablesDto createdCoverables = coverablesService.createCoverables(coverablesRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdCoverables);
	}

	// Read operation - Get all coverables
	@GetMapping("/all")
	public ResponseEntity<List<CoverablesDto>> getAllCoverables() {
		List<CoverablesDto> coverablesList = coverablesService.getAllCoverables();
		return ResponseEntity.ok(coverablesList);
	}

	// Read operation - Get coverables by ID
	@GetMapping("/{id}")
	public ResponseEntity<CoverablesDto> getCoverablesById(@PathVariable("id") String id) {
		CoverablesDto coverables = coverablesService.getCoverablesById(id);
        if (coverables != null) {
            return ResponseEntity.ok(coverables);
        } else {
            return ResponseEntity.notFound().build();
        }
        }

	// Update operation
	@PutMapping("/{id}")
	public ResponseEntity<CoverablesDto> updateCoverables(@PathVariable("id") String id,
			@ModelAttribute CreateCoverablesRequest coverables) {
		CoverablesDto updatedCoverables = coverablesService.updateCoverables(id, coverables);
		if (updatedCoverables != null) {
			return ResponseEntity.ok(updatedCoverables);
		}
		return ResponseEntity.notFound().build();
	}

	// Delete operation
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCoverables(@PathVariable("id") String id) {
		coverablesService.deleteCoverables(id);
		return ResponseEntity.noContent().build();

	}

}

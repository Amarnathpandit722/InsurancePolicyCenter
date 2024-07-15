package com.insurance.pc.service;

import java.util.List;

import com.insurance.pc.dto.CoverageDto;
import com.insurance.pc.dto.request.coverage.CreateCoverageRequest;

public interface ICoverageService {

	public List<CoverageDto> getAllCoverages();
	
	 public CoverageDto getCoverageById(String id);
	 
	 public CoverageDto createCoverage(CreateCoverageRequest coverageDto);
	
	 public CoverageDto updateCoverage(String id, CreateCoverageRequest coverageDto) ;
	 
	 public void deleteCoverage(String id);
	
	
}

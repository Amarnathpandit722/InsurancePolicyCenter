package com.insurance.pc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.pc.dto.CoverageDto;
import com.insurance.pc.dto.converter.CoverageDtoConverter;
import com.insurance.pc.dto.request.coverage.CreateCoverageRequest;
import com.insurance.pc.exception.CoverageNotFoundException;
import com.insurance.pc.model.Coverage;
import com.insurance.pc.repository.CoverageRepository;
import com.insurance.pc.service.ICoverageService;

@Service
public class CoverageServiceImpl implements ICoverageService{
	
	@Autowired
	private CoverageRepository coverageRepository;
	
	@Autowired
	private CoverageDtoConverter coverageDtoConverter;

	@Override
	public List<CoverageDto> getAllCoverages() {
		List<Coverage> coverageList= coverageRepository.findAll();
		if(coverageList.isEmpty()) {
			throw new CoverageNotFoundException("No Coverage Found ");
		}
		return coverageDtoConverter.convert(coverageList);
	}

	@Override
	public CoverageDto getCoverageById(String id) {
		Optional<Coverage> optionalCoverage = coverageRepository.findById(id);
		if(optionalCoverage.isEmpty()) {
			throw new CoverageNotFoundException("Coverage Not Found with this Id : "+id);
		}
		return coverageDtoConverter.convert(optionalCoverage.get());
	}

	@Override
	public CoverageDto createCoverage(CreateCoverageRequest request) {
		Coverage coverage = new Coverage();
		coverage.setCoverageType(request.getCoverageType());
		coverage.setDescription(request.getDescription());
		coverageRepository.save(coverage);
		return coverageDtoConverter.convert(coverage);
		
		
		
	}

	@Override
	public CoverageDto updateCoverage(String id, CreateCoverageRequest request) {
		Optional<Coverage> optionalCoverage = coverageRepository.findById(id);
		if(optionalCoverage.isEmpty()) {
			throw new CoverageNotFoundException("Coverage Not Found with this Id : "+id);
		}
		Coverage coverage = optionalCoverage.get();
		coverage.setCoverageType(request.getCoverageType());
		coverage.setDescription(request.getDescription());
		coverageRepository.save(coverage);
		return coverageDtoConverter.convert(coverage);
		
	}

	@Override
	public void deleteCoverage(String id) {
		Optional<Coverage> optionalCoverage = coverageRepository.findById(id);
		if(optionalCoverage.isEmpty()) {
			throw new CoverageNotFoundException("Coverage Not Found with this Id : "+id);
		}
		coverageRepository.delete(optionalCoverage.get());
		
	}
	
	
	
	

}

package com.insurance.pc.dto.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.insurance.pc.dto.CoverageDto;
import com.insurance.pc.model.Coverage;

@Component
public class CoverageDtoConverter {

	
	public CoverageDto convert(Coverage from) {
		return new CoverageDto(from.getId(), from.getCoverageType(), from.getDescription());
	}
	
	
	public List<CoverageDto> convert(List<Coverage>from){
		 return from.stream()
	                .map(this::convert)
	                .collect(Collectors.toList());
		
	}
	
	
	
	
}

package com.insurance.pc.dto.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.insurance.pc.dto.CoverablesDto;
import com.insurance.pc.model.Coverables;

@Component
public class CoverablesDtoConverter {

	public CoverablesDto convert(Coverables from) {
		return new CoverablesDto(from.getId(), from.getCoverablesType(), from.getDescription());
	}
	
	
	
	public List<CoverablesDto> convert (List<Coverables> from ){
		return from.stream().map(this::convert)
				.collect(Collectors.toList());
		
	}
	
	
	
	
	
	
}

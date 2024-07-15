package com.insurance.pc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.pc.dto.CoverablesDto;
import com.insurance.pc.dto.converter.CoverablesDtoConverter;
import com.insurance.pc.dto.request.coverables.CreateCoverablesRequest;
import com.insurance.pc.exception.CoverablesNotFoundException;
import com.insurance.pc.exception.UnabletoAddUpdateCoverablesException;
import com.insurance.pc.model.Coverables;
import com.insurance.pc.repository.CoverablesRepository;
import com.insurance.pc.service.ICoverablesService;

@Service
public class CoverablesServiceImpl implements ICoverablesService {

	@Autowired
	private CoverablesRepository coverablesRepository;
	@Autowired
	private CoverablesDtoConverter covearbaleConverter;
	
	@Override
	public CoverablesDto createCoverables( CreateCoverablesRequest coverablesRequest ) {
		Coverables addcoverables = new Coverables();
		addcoverables.setCoverablesType(coverablesRequest.getCoverablesType());
		addcoverables.setDescription(coverablesRequest.getDescription());
        coverablesRepository.save(addcoverables);
        return covearbaleConverter.convert(addcoverables);

	}

	@Override
	public List<CoverablesDto> getAllCoverables() {
       List<Coverables> coverableList =  coverablesRepository.findAll();

       return covearbaleConverter.convert(coverableList);
	}

	@Override
	public CoverablesDto getCoverablesById(String id) {
        Optional<Coverables> optionalCoverabels =   coverablesRepository.findById(id);
        	if(optionalCoverabels.isEmpty()) {
        		throw new CoverablesNotFoundException("Coveraables Not Found Exception");
        	}
        	return covearbaleConverter.convert(optionalCoverabels.get());
        
        
	}

	@Override
	public CoverablesDto updateCoverables(String id, CreateCoverablesRequest coverable) {
		Optional<Coverables> optionalCoverables = coverablesRepository.findById(id);
	    
	    if (optionalCoverables.isPresent()) {
	        Coverables existingCoverables = optionalCoverables.get();
	        existingCoverables.setCoverablesType(coverable.getCoverablesType());
	        existingCoverables.setDescription(coverable.getDescription());
	        coverablesRepository.save(existingCoverables);
	        return covearbaleConverter.convert(existingCoverables);
	    } else {
	        throw new UnabletoAddUpdateCoverablesException("Unable to Update Ccverables with this id : "+ id);
	    }
	}

	@Override
	public void deleteCoverables(String id) {
        coverablesRepository.deleteById(id);
		
	}

}

package com.insurance.pc.service;

import java.util.List;

import com.insurance.pc.dto.CoverablesDto;
import com.insurance.pc.dto.request.coverables.CreateCoverablesRequest;

public interface ICoverablesService {

	public CoverablesDto createCoverables(CreateCoverablesRequest coverablesRequest);

	public List<CoverablesDto> getAllCoverables();

	public CoverablesDto getCoverablesById(String id);

	public CoverablesDto updateCoverables(String id, CreateCoverablesRequest coverables);

	public void deleteCoverables(String id);
}

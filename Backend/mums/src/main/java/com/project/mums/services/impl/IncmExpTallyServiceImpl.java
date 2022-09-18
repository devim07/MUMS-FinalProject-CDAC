package com.project.mums.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mums.entities.IncmExpTally;
import com.project.mums.payload.IncmExpTallyDto;
import com.project.mums.repository.IncmExpTallyRepo;
import com.project.mums.services.IncmExpTallyService;

@Service
public class IncmExpTallyServiceImpl implements IncmExpTallyService {

	@Autowired
	private  IncmExpTallyRepo incmExpTallyRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	
	
	@Override
	public List<IncmExpTallyDto> getAllByMonthYear(int year, int month) {
		List<IncmExpTally> incmExpTallys=this.incmExpTallyRepo.getAllByMonthYearFromDb(year, month);
		List<IncmExpTallyDto> incmExpTallyDtos =incmExpTallys.stream().map(incmExpTally->this.incmExpTallyToDto(incmExpTally)).collect(Collectors.toList());
		return incmExpTallyDtos;

	}
	
	

	@Override
	public List<IncmExpTallyDto> getAll() {
		List<IncmExpTally> incmExpTallys=this.incmExpTallyRepo.findAll();
		List<IncmExpTallyDto> incmExpTallyDtos =incmExpTallys.stream().map(incmExpTally->this.incmExpTallyToDto(incmExpTally)).collect(Collectors.toList());
	return incmExpTallyDtos;
	}

	
	
	
	private IncmExpTallyDto incmExpTallyToDto(IncmExpTally incmExpTally) {
		IncmExpTallyDto incmExpTallyDto = this.modelMapper.map(incmExpTally,IncmExpTallyDto.class);
		return incmExpTallyDto;
	}
}

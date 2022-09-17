package com.project.mums.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mums.entities.Cust;
import com.project.mums.exceptions.IdMisMatchException;
import com.project.mums.exceptions.ResourceNotFoundException;
import com.project.mums.payload.CustDto;
import com.project.mums.repository.CustRepo;
import com.project.mums.services.CustService;

@Service
public class CustServiceImpl  implements CustService{

	@Autowired
	private CustRepo custRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	
	
	@Override
	public CustDto createCust(CustDto custDto) {
		Cust cust = this.dtoToCust(custDto);
		Cust savedCust = this.custRepo.save(cust);
		return custToDto(savedCust) ;
	}



	@Override
	public CustDto updateCust(CustDto custDto, int custno) {
		if (custno != custDto.getCustno())
			throw new IdMisMatchException(((Integer)custno).toString(),((Integer)custDto.getCustno()).toString());
		Cust cust = this.custRepo.findById(custno)
			.orElseThrow(()->
			new ResourceNotFoundException("Customer","Customer ID",((Integer)custno).toString()));
		cust.setCustname(custDto.getCustname());
		cust.setCity(custDto.getCity());
		cust.setRating(custDto.getRating());
		Cust updatedCust=this.custRepo.save(cust);
		return custToDto(updatedCust);
	}
	
	

	@Override
	public CustDto getCustById(int custno) {
		Cust cust=this.custRepo.findById(custno)
				.orElseThrow(()->
				new ResourceNotFoundException("Customer","Customer ID",((Integer)custno).toString()));
		return custToDto(cust);
	}
	
	

	@Override
	public List<CustDto> getAllCusts() {
		List<Cust> custs=this.custRepo.findAll();
		List<CustDto> custDtos =custs.stream().map(cust->this.custToDto(cust)).collect(Collectors.toList());
				return custDtos;
	}

	
	
	
	private Cust dtoToCust(CustDto custDto) {
		Cust cust = this.modelMapper.map(custDto, Cust.class);
		return cust;
	}
	
	private CustDto custToDto(Cust cust) {
		CustDto custDto = this.modelMapper.map(cust,CustDto.class);
		return custDto;
	}
}
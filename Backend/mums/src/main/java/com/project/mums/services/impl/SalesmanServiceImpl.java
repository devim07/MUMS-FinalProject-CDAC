package com.project.mums.services.impl;

import java.util.stream.Collectors;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mums.entities.Salesman;
import com.project.mums.exceptions.ResourceNotFoundException;
import com.project.mums.payload.SalesmanDto;
import com.project.mums.repository.SalesmanRepo;
import com.project.mums.services.SalesmanService;



@Service
public class SalesmanServiceImpl implements SalesmanService {

	@Autowired
	private SalesmanRepo salesmanRepo;
	
	@Autowired
	private ModelMapper modelMapper;

//	@Override
//	public SalesmanDto createSalesman(SalesmanDto salesmanDto) {
//		Salesman salesman=this.dtoToSalesman(salesmanDto);
//		Salesman savedSalesman=this.salesmanRepo.save(salesman);
//		return salesmanToDto(savedSalesman);
//
//		
//	}

	

	@Override
	public SalesmanDto updateSalesman(SalesmanDto salesmanDto, String salesmanno) {
		Salesman salesman=this.salesmanRepo.findById(salesmanno)
				.orElseThrow(()->
				new ResourceNotFoundException("Salesman", "Salesman NO", salesmanno)); 
		salesman.setSalesmanno(salesmanDto.getSalesmanno());
		salesman.setCommission(salesmanDto.getCommission());
		salesman.setLocation(salesmanDto.getLocation());
		Salesman updatedSalesman=this.salesmanRepo.save(salesman);
		return salesmanToDto(updatedSalesman);
	}

	@Override
	public SalesmanDto getSalesmanByID(String salesmanno) {
		Salesman salesman=this.salesmanRepo.findById(salesmanno)
				.orElseThrow(()->
				new ResourceNotFoundException("Salesman", "Salesman ID", salesmanno)); 
		return salesmanToDto(salesman);
	}

	@Override
	public List<SalesmanDto> getAllSalesman() {
		List<Salesman> salesmans = this.salesmanRepo.findAll();
		List<SalesmanDto> salesmanDtos = salesmans.stream().map(salesman->this.salesmanToDto(salesman)).collect(Collectors.toList());
		return salesmanDtos;
	}

	@Override
	public void deleteSalesman(String salesmanno) {
		Salesman salesman = this.salesmanRepo.findById(salesmanno)
				.orElseThrow(()->
				new ResourceNotFoundException("Salesman", "Salesman ID", salesmanno));
				this.salesmanRepo.delete(salesman);
	}
	
	
	
	private SalesmanDto salesmanToDto(Salesman salesman) {
		SalesmanDto salesmanDto=this.modelMapper.map(salesman, SalesmanDto.class);
		return salesmanDto;
	}

//	private Salesman dtoToSalesman(SalesmanDto salesmanDto) {
//		Salesman salesman=this.modelMapper.map(salesmanDto, Salesman.class);
//		return salesman;
//	}



	@Override
	public SalesmanDto getSalesmanBy(String salesmanno) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}




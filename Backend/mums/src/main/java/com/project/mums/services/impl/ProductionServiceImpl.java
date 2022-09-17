package com.project.mums.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mums.entities.Production;
import com.project.mums.payload.ProductionDto;
import com.project.mums.repository.ProductionRepo;
import com.project.mums.services.ProductionService;

@Service
public class ProductionServiceImpl implements ProductionService {
	
	@Autowired
	private ProductionRepo productionRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<ProductionDto> getAllByMonthYear(int year ,int month) {
		List<Production> productions = this.productionRepo.getAllByMonthYearFromDb(year, month);
		List<ProductionDto> productionDtos = productions.stream().map(production->this.ProducionToDto(production)).collect(Collectors.toList());
		return productionDtos;
	}


	@Override
	public List<ProductionDto> getAllProduction() {
		List<Production> productions = this.productionRepo.findAll();
		List<ProductionDto> productionDtos = productions.stream().map(production->this.ProducionToDto(production)).collect(Collectors.toList());
		return productionDtos;
	}
	
	private ProductionDto ProducionToDto(Production Producion) {
		ProductionDto ProducionDto=this.modelMapper.map(Producion, ProductionDto.class);
		return ProducionDto;
	}

//	private Production dtoToProducion(ProductionDto producionDto) {
//		Production production=this.modelMapper.map(producionDto, Production.class);
//		return production;
//	}
	
}


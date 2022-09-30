package com.project.mums.services.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
		String query="%"+month+"/"+year+"--"+"%";
		List<IncmExpTally> calculations = this.incmExpTallyRepo.getCalculationByMonthYearFromDb(query);
		List<IncmExpTally> incmExpTallys=this.incmExpTallyRepo.getAllByMonthYearFromDb(year, month);
		incmExpTallys.addAll(calculations);
		List<IncmExpTallyDto> incmExpTallyDtos =incmExpTallys.stream().map(incmExpTally->this.incmExpTallyToDto(incmExpTally)).collect(Collectors.toList());
		return incmExpTallyDtos;

	}
	
	

	@Override
	public List<IncmExpTallyDto> getAll() {
		List<IncmExpTally> incmExpTallys=this.incmExpTallyRepo.findAll();
		List<IncmExpTallyDto> incmExpTallyDtos =incmExpTallys.stream().map(incmExpTally->this.incmExpTallyToDto(incmExpTally)).collect(Collectors.toList());
	return incmExpTallyDtos;
	}



	@Override
	public List<IncmExpTallyDto> calculateMonthlyExp(Map<Integer, Integer> data) {
		System.out.println("mon"+data.get(0)+"year"+data.get(1)+ data.get(2)+ data.get(3)+ data.get(4));
		this.incmExpTallyRepo.calcMonthlyExpDb(data.get(0), data.get(1), data.get(2), data.get(3), data.get(4));
		List<IncmExpTallyDto> all = getAllByMonthYear(data.get(1), data.get(0));
		return all;
	}


	@Override
	public List<IncmExpTallyDto> getPast4MonthsIncome() {
		List<IncmExpTallyDto> incomes= new ArrayList<>();
		IncmExpTally inc =null;
		String query;
		int month=(LocalDate.now().getMonthValue())-1;
		int year=LocalDate.now().getYear();
		for(int i=3; i>=0; i--) {
			query="%"+(month-i)+"/"+year+"-- TOTAL INCOME%";
			inc = this.incmExpTallyRepo.getTotalIncomeFromDb(query);
			incomes.add(incmExpTallyToDto(inc));
		}
		return incomes;		
	}



	@Override
	public Double prediction() {
		List<Float> incomes= new ArrayList<>();
		IncmExpTally inc =null;
		String query;
		int month=(LocalDate.now().getMonthValue())-1;
		int year=LocalDate.now().getYear();
		for(int i=0; i<3; i++) {
			query="%"+(month-i)+"/"+year+"-- TOTAL INCOME%";
			inc = this.incmExpTallyRepo.getTotalIncomeFromDb(query);
			incomes.add(inc.getAmount());
		}
		double predict=(0.5*incomes.get(0))+(0.3*incomes.get(1)+(0.2*incomes.get(2)))*1.1;
		predict=((int)(predict/10000)+1)*10000;
		return predict;
	}

	
	
	
	private IncmExpTallyDto incmExpTallyToDto(IncmExpTally incmExpTally) {
		IncmExpTallyDto incmExpTallyDto = this.modelMapper.map(incmExpTally,IncmExpTallyDto.class);
		return incmExpTallyDto;
	}


}

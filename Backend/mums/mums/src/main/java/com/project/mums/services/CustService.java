package com.project.mums.services;

import java.util.List;

import com.project.mums.payload.CustDto;

public interface CustService {

	CustDto createCust (CustDto custDto);
	CustDto updateCust (CustDto custDto, int custno);
	CustDto getCustById (int custno);
	List<CustDto> getAllCusts();
	void deleteCust (int custno);
}

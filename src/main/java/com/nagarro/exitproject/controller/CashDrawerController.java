package com.nagarro.exitproject.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.exitproject.constant.Constants;
import com.nagarro.exitproject.dto.CashDto;
import com.nagarro.exitproject.dto.ObjectDto;
import com.nagarro.exitproject.model.CashDrawer;
import com.nagarro.exitproject.service.CashDrawerService;


@RestController
@RequestMapping(value=Constants.CASH_DRAWER_URL) // /cashdrawer
public class CashDrawerController {
	Logger logger = Logger.getLogger(CashDrawerController.class);
	
	@Autowired
	private CashDrawerService cashDrawerService;
	
	@RequestMapping(value="/employee/{empId}/cash", method=RequestMethod.POST)
	@ResponseBody	
	public ResponseEntity<?> createCashDrawer(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("empId") String eid, 
			@RequestBody CashDto cashdto) {
	    logger.info(cashdto.getCash());
		if(this.cashDrawerService.createNewEntry(eid, cashdto.getCash())) {
			return ResponseEntity.status(HttpStatus.OK).body(Constants.CASH_DRAWER_CREATION_MESSAGE);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Constants.CASH_DRAWER_FAILUR_MESSAGE);
	}
	
	@RequestMapping(value="/employee/{empId}", method=RequestMethod.GET)
	@ResponseBody	
	public ResponseEntity<?> getCashDrawer(HttpServletRequest request, HttpServletResponse response,
			   @PathVariable("empId") String id) {
	    CashDrawer cd = this.cashDrawerService.getDrawer(id);
	    if(cd == null){
	    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Constants.NO_CASH_DRAWER_MESSAGE);
	    }
	    ObjectDto dto = new ObjectDto();
	    dto.setObject(cd);
		return ResponseEntity.status(HttpStatus.OK).body(dto);		
	}
	
	@RequestMapping(value="/employee/{empId}/cash", method=RequestMethod.PUT)
	@ResponseBody	
	public ResponseEntity<?> updateCashDrawer(HttpServletRequest request, HttpServletResponse response,
			   @PathVariable("empId") String eid, @RequestBody CashDto cashdto) {
		if(this.cashDrawerService.updateCash(eid, cashdto.getCash())) {
			return ResponseEntity.status(HttpStatus.OK).body(Constants.CASH_UPDATION_MESSAGE);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Constants.CASH_UPDATION_FAILURE_MESSAGE);
		
	}
	
	
	

}

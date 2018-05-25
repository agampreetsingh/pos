package com.nagarro.exitproject.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.exitproject.constant.Constants;
import com.nagarro.exitproject.model.CashDrawer;
import com.nagarro.exitproject.service.CashDrawerService;


@RestController
@RequestMapping(value=Constants.CASH_DRAWER_URL)
public class CashDrawerController {
	
	@Autowired
	private CashDrawerService cashDrawerService;
	
	@RequestMapping(value="/{empId}/{cash}", method=RequestMethod.POST)
	@ResponseBody	
	public ResponseEntity<?> createCashDrawer(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("empId") String eid, @PathVariable("cash") String cash) {
		if(this.cashDrawerService.createNewEntry(eid, cash)) {
			return ResponseEntity.status(HttpStatus.OK).body("CASH DRAWER SUCCESSFULLY CREATED FOR THE DAY.");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FAILED TO CREATE CASH DRAWER.");
	}
	
	@RequestMapping(value="/{empId}", method=RequestMethod.GET)
	@ResponseBody	
	public ResponseEntity<?> getCashDrawer(HttpServletRequest request, HttpServletResponse response,
			   @PathVariable("empId") String id) {
		System.out.println("get the cash controller.");
	    CashDrawer cd = this.cashDrawerService.getDrawer(id);
	    if(cd == null){
	    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No cash Drawer for the employee for the day.");
	    }
		return ResponseEntity.status(HttpStatus.OK).body(cd);		
	}
	
	@RequestMapping(value="/{empId}/{cash}", method=RequestMethod.PUT)
	@ResponseBody	
	public ResponseEntity<?> updateCashDrawer(HttpServletRequest request, HttpServletResponse response,
			   @PathVariable("empId") String eid, @PathVariable("cash") String cash) {
		if(this.cashDrawerService.updateCash(eid, cash)) {
			return ResponseEntity.status(HttpStatus.OK).body("CASH UPDATED.");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CASH NOT UPDATED.");
		
	}
	
	
	

}

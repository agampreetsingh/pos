package com.nagarro.exitproject.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.exitproject.model.CashDrawer;
import com.nagarro.exitproject.service.CashDrawerService;


@RestController
@RequestMapping(value="/cashdrawer")
public class CashDrawerController {
	
	@Autowired
	private CashDrawerService cashDrawerService;
	private int eid = 1;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	@ResponseBody	
	public ResponseEntity<?> employeeLogin(HttpServletRequest request, HttpServletResponse response
			   ) {
		System.out.println("get the cash controller.");
	    CashDrawer cd = this.cashDrawerService.getDrawer(eid);
	    if(cd == null){
	    	return ResponseEntity.ok().body("No cash Drawer for the employee for the day.");
	    }
		return ResponseEntity.ok().body(cd);
		
	}

}

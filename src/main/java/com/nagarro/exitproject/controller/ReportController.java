package com.nagarro.exitproject.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ServletContextAware;

import com.nagarro.exitproject.constant.Constants;
import com.nagarro.exitproject.service.ReportService;

@RestController
@RequestMapping(value=Constants.REPORT_URL)
public class ReportController implements ServletContextAware{
	Logger logger = Logger.getLogger(ReportController.class);
	
	@Autowired
	private ReportService reportService;

	@Autowired
	private ServletContext ctx;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> generateReport(HttpServletRequest request, HttpServletResponse response) {
		File file = new File(Constants.ABSOLUTE_PATH + "\\order.xlsx");
		InputStreamResource resource = null;
		try {
			 resource  = new InputStreamResource(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			System.out.println("ERROR BUILDING REPORT: " + e);
			
		}
		this.reportService.generateSheet();
		return ResponseEntity.ok().header("Content-Disposition", "attachment; filename=\"order.xlsx\"")
				.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).contentLength(file.length())
				.body(resource);	
	}

	public void setServletContext(ServletContext servletContext) {
		this.ctx = servletContext;
	}
	
	

}

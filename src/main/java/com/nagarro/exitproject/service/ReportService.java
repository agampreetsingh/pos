package com.nagarro.exitproject.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import com.nagarro.exitproject.constant.Constants;
import com.nagarro.exitproject.model.Order;

@Service
public class ReportService implements ServletContextAware{

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ServletContext ctx;
	
	public void setServletContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
		this.ctx = servletContext;
	}

	@Transactional
	public void generateSheet() {
		List<Order> orderList = this.orderService.getOrders();
		
		FileOutputStream fileOut = null;

		String filename = Constants.ABSOLUTE_PATH + "\\order.xlsx";
		
		try {
			FileOutputStream file = new FileOutputStream(new File(filename));
			@SuppressWarnings("resource")
			XSSFWorkbook hwb = new XSSFWorkbook();
			XSSFSheet sheet = hwb.createSheet("new");
			Row rowhead = sheet.createRow((short) 0);
			rowhead.createCell((short) 0).setCellValue("orderId");
			rowhead.createCell((short) 1).setCellValue("amount");
			rowhead.createCell((short) 2).setCellValue("date");
			rowhead.createCell((short) 3).setCellValue("paymentType");
			rowhead.createCell((short) 4).setCellValue("status");
			rowhead.createCell((short) 5).setCellValue("customer");
			rowhead.createCell((short) 6).setCellValue("employee");
			
			int i = 1;
			for(Order order : orderList) {
				Row row = sheet.createRow(i++);
				row.createCell(0).setCellValue(order.getId());
				row.createCell(1).setCellValue(order.getAmount());
				row.createCell(2).setCellValue(order.getDate());
				row.createCell(3).setCellValue(order.getPaymentType());
				row.createCell(4).setCellValue(order.getStatus());
				row.createCell(5).setCellValue(order.getCustomer().getName());
				row.createCell(6).setCellValue(order.getEmployee().getName());
			}
			fileOut = new FileOutputStream(filename);
			hwb.write(file);
			fileOut.close();
		} catch (FileNotFoundException e) {
          System.out.println("ERROR CREATING REPORT: " + e);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}

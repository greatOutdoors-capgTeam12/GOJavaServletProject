package com.capgemini.go.presentationLayer;

import java.net.ConnectException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.capgemini.go.dao.GoAdminDao;
import com.capgemini.go.dao.GoAdminDaoImpl;
import com.capgemini.go.dto.ViewSalesReportByUserDTO;
import com.capgemini.go.exception.GoAdminException;

public class reportcheck {
	public static void main(String[] args) throws ConnectException, GoAdminException, ParseException {
		GoAdminDao go=new GoAdminDaoImpl();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("ENTER THE USER ID");
		String user = sc.next();

		System.out.println("ENTER THE DATES in dd/MM/yyyy format");
		String dateEntry = sc.next();
		String dateExit = sc.next();
		Date dentry = new SimpleDateFormat("dd/MM/yyyy").parse(dateEntry);
		Date dexit = new SimpleDateFormat("dd/MM/yyyy").parse(dateExit);

		System.out.println("ENTER THE PRODUCT CATEGORY IN INTEGER");
		System.out.println("1.CAMPING");
		System.out.println("2.GOLF");
		System.out.println("3.MOUNTANEERING");
		System.out.println("4.OUTDOOR");
		System.out.println("5.PERSONAL");
		int category = sc.nextInt();
		List<ViewSalesReportByUserDTO> reports = go.viewSalesReportByUserAndCategory(dentry, dexit, user,
				category);
		int i = 0,n;
		if (reports != null) {
			n = reports.size();
			System.out.printf("%-25s %-25s %-25s %-25s %-25s %-25s %n", "USER ID", "DATE", "ORDER ID",
					"PRODUCT ID", "PRODUCT CATEGORY", "PRODUCT PRICE");
			while (i < n) {
				reports.get(i).printData();
				i++;
			}
		}
	}
}
		



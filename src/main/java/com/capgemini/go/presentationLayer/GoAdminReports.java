package com.capgemini.go.presentationLayer;

import java.io.IOException;
import java.net.ConnectException;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import com.capgemini.go.dto.RetailerDTO;
import com.capgemini.go.dto.SalesRepDTO;
import com.capgemini.go.dto.ViewDetailedSalesReportByProductDTO;
import com.capgemini.go.dto.ViewSalesReportByUserDTO;
import com.capgemini.go.exception.DatabaseException;
import com.capgemini.go.exception.GoAdminException;
import com.capgemini.go.service.GoAdminService;
import com.capgemini.go.service.GoAdminServiceImpl;
import com.capgemini.go.utility.DbConnection;

public class GoAdminReports {

	static void reportInterfaces()throws ConnectException {

		GoAdminService goAdmin = new GoAdminServiceImpl();

		Scanner sc = new Scanner(System.in);
		try {
			System.out.println("------------------WELCOME TO GREAT OUTDOOR-------------------");
			System.out.println("CHOOSE FROM THE BELOW OPTION:");
			System.out.println("	1.)VIEW REPORTS OF SPECIFIC RETAILER				");
			System.out.println("	2.)VIEW REPORTS OF ALL RETAILER						");
			System.out.println("	3.)VIEW REPORTS OF SPECIFIC SALES REPRESENTATIVE	");
			System.out.println("	4.)VIEW REPORTS OF ALL SALES REPRESENTATIVE			");
			System.out
					.println("	5.)VIEW DETAILED REVENUE REPORTS OF PARTICULAR PRODUCT(ALL USER) WITHIN GIVEN DATES");
			System.out.println(
					"	6.)VIEW DETAILED REVENUE REPORTS OF PARTICULAR PRODUCT(SPECIFIC USER) WITHIN GIVEN DATES");
			System.out
					.println("	7.)VIEW DETAILED REVENUE REPORTS OF SPECIFIC USER(ALL PRODUCTS) WITHIN GIVEN DATES");
			System.out.println("	8.)VIEW DETAILED REVENUE REPORTS OF ALL USER(ALL PRODUCTS) WITHIN GIVEN DATES");
			System.out.println(
					"	9.)VIEW MONTH TO MONTH CHANGE REVENUE REPORTS OF PARTICULAR PRODUCT WITHIN GIVEN DATES");

			int choice = sc.nextInt();
			switch (choice) {
			case 1: {
				System.out.println("ENTER THE USER ID");
				String user = sc.next();
				// Object o=GoAdmin.viewRetailerData(user);
				RetailerDTO report = goAdmin.viewRetailerData(user);
				if (report != null) {
					System.out.printf("%-20s %-20s %n", "RETAILER ID", "DISCOUNT");
					goAdmin.viewRetailerData(user).printData();
				}
				// System.out.printf("%-20s %-20s","RETAILER ID","DISCOUNT");
				// System.out.printf("%-20s
				// %-20.2f",GoAdmin.viewRetailerData(user).getUserId(),GoAdmin.viewRetailerData(user).getDiscount());
				break;

			}
			case 2: {
				int i = 0, n;
				List<RetailerDTO> reports = goAdmin.viewAllRetailerData();
				if (reports != null) {
					System.out.printf("%-20s %-20s %n", "RETAILER ID", "DISCOUNT");

					n = reports.size();
					// GoAdmin.viewAllRetailerData().toString();
					// System.out.println(n);
					while (i < n) {
						reports.get(i).printData();
						i++;
					}
				}

				break;
			}
			case 3: {
				System.out.println("ENTER THE USER ID");
				String user = sc.next();

				SalesRepDTO reports = goAdmin.viewSalesRepData(user);
				if (reports != null) {
					System.out.printf("%-25s %-25s %-25s %-25s%n", "USER ID", "TARGET SALES", "TARGET STATUS",
							"CURRENT SALES");
					goAdmin.viewSalesRepData(user).printData();
				}
				// System.out.println(GoAdmin.viewSalesRepData(user));
				break;
			}
			case 4: {

				int i = 0, n;
				List<SalesRepDTO> reports = goAdmin.viewAllSalesRepData();
				if (reports != null) {
					System.out.printf("%-25s %-25s %-25s %-25s %n", "USER ID", "TARGET SALES", "TARGET STATUS",
							"CURRENT SALES");

					n = reports.size();
					while (i < n) {
						goAdmin.viewAllSalesRepData().get(i).printData();
						i++;
					}
				}

				break;

			}
			case 5: {
				int i = 0;
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
				List<ViewSalesReportByUserDTO> reports = goAdmin.viewSalesReportByCategory(dentry, dexit, category);

				if (reports != null) {

					int n = reports.size();
					System.out.printf("%-25s %-25s %-25s %-25s %-25s %-25s %n", "USER ID", "DATE", "ORDER ID",
							"PRODUCT ID", "PRODUCT CATEGORY", "PRODUCT PRICE");
					while (i < n) {
						reports.get(i).printData();
						i++;
					}
				}
				break;

			}
			case 6: {
				int i = 0;
				int n;
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
				List<ViewSalesReportByUserDTO> reports = goAdmin.viewSalesReportByUserAndCategory(dentry, dexit, user,
						category);

				if (reports != null) {
					n = reports.size();
					System.out.printf("%-25s %-25s %-25s %-25s %-25s %-25s %n", "USER ID", "DATE", "ORDER ID",
							"PRODUCT ID", "PRODUCT CATEGORY", "PRODUCT PRICE");
					while (i < n) {
						reports.get(i).printData();
						i++;
					}
				}
				break;

			}
			case 7: {
				int i = 0;
				int n;
				System.out.println("ENTER THE USER ID");
				String user = sc.next();

				System.out.println("ENTER THE DATES in dd/MM/yyyy format");
				String dateEntry = sc.next();
				String dateExit = sc.next();
				Date dentry = new SimpleDateFormat("dd/MM/yyyy").parse(dateEntry);
				Date dexit = new SimpleDateFormat("dd/MM/yyyy").parse(dateExit);

				List<ViewSalesReportByUserDTO> reports = goAdmin.viewSalesReportByUser(dentry, dexit, user);

				if (reports != null) {
					n = reports.size();

					System.out.printf("%-25s %-25s %-25s %-25s %-25s %-25s %n", "USER ID", "DATE", "ORDER ID",
							"PRODUCT ID", "PRODUCT CATEGORY", "PRODUCT PRICE");
					while (i < n) {
						reports.get(i).printData();
						i++;
					}
				}
				break;

			}
			case 8: {
				int i = 0, n;

				System.out.println("ENTER THE DATES in dd/MM/yyyy format");
				String dateEntry = sc.next();
				String dateExit = sc.next();
				Date dentry = new SimpleDateFormat("dd/MM/yyyy").parse(dateEntry);
				Date dexit = new SimpleDateFormat("dd/MM/yyyy").parse(dateExit);

				List<ViewSalesReportByUserDTO> reports = goAdmin.viewSalesReportALLUserAndCategory(dentry, dexit);

				if (reports != null) {
					n = reports.size();
					System.out.printf("%-25s %-25s %-25s %-25s %-25s %-25s %n", "USER ID", "DATE", "ORDER ID",
							"PRODUCT ID", "PRODUCT CATEGORY", "PRODUCT PRICE");
					while (i < n) {
						reports.get(i).printData();
						i++;
					}
				}
				break;

			}
			case 9: {

				int i = 0;
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

				List<ViewDetailedSalesReportByProductDTO> reports = goAdmin.viewDetailedSalesReportByProduct(dentry,
						dexit, category);
				if (reports != null) {
					System.out.printf("%-25s %-25s %-25s %-25s %-25s %n", "MONTH", "REVENUE", "AMOUNT CHANGE",
							"PERCENTAGE GROWTH", "COLOR CODE");
					// reports.get(0).printData();
					int n = reports.size();

					while (i < n) {
						reports.get(i).printData();
						i++;
					}
				}
				break;

			}
			default: {
				System.out.println("INVALID CHOICE");
				break;
			}
			}
		} catch (ParseException | GoAdminException e) {
			System.out.println(e.getMessage());
		}

	}
}

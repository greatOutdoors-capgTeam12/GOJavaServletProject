package com.capgemini.go.presentationLayer;

import java.net.ConnectException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.capgemini.go.dto.ReturnReportRequestDTO;
import com.capgemini.go.exception.GoAdminException;
import com.capgemini.go.service.GoAdminService;
import com.google.protobuf.TextFormat.ParseException;

public class ReturnReportDriver {

	public static void menu(Scanner scanner, GoAdminService goAdmin) throws ConnectException {
		boolean menuEntry = true;
		int reason;
		List<Double> report;
		while (menuEntry == true) {
			System.out.println("*************** RETURN REPORT MENU *****************");
			System.out.println(
					"Press 1 to get Monthly Report \nPress 2 to get Quaterley Report\nPress 3 to get Yearly Report\nPress 0 to go back to main menu");

			int choice = scanner.nextInt();
			scanner.nextLine();
			switch (choice) {
			case 0:
				menuEntry = false;
				break;
			case 1:
				boolean entry = true;
				while (entry == true) {
					System.out.println(
							"Press 1 to get report for Wrong product\nPress 2 to get report for Incomplete Product\nPress 0 to go back.");
					reason = scanner.nextInt();
					scanner.nextLine();
					switch (reason) {
					case 0:
						entry = false;
						break;
					case 1:
						try {
							System.out.println("Enter the starting date in dd/mm/yyyy");
							String start = scanner.nextLine();
							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
							sdf.setLenient(false);
							Date startDate = sdf.parse(start);
							System.out.println("Enter the end date in dd/mm/yyyy");
							String end = scanner.nextLine();
							Date enddate = sdf.parse(end);
							List<Date> dates = new ArrayList<>();
							dates.add(startDate);
							dates.add(enddate);
							ReturnReportRequestDTO request = new ReturnReportRequestDTO(dates, choice, reason);
							report = goAdmin.retrieveReturnReport(request);
							System.out.println(report);
						} catch (java.text.ParseException e) {
							System.out.println("Enter date in dd/mm/yyyy format only");
						} catch (GoAdminException e) {
							
							System.out.println("Error in generating Report >>>" + e.getMessage());
						}
						break;
					case 2:
						try {
							System.out.println("Enter the starting date in dd/mm/yyyy");
							String start = scanner.nextLine();
							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
							sdf.setLenient(false);
							Date startDate = sdf.parse(start);
							System.out.println("Enter the end date in dd/mm/yyyy");
							String end = scanner.nextLine();
							Date enddate = sdf.parse(end);
							List<Date> dates = new ArrayList<>();
							dates.add(startDate);
							dates.add(enddate);
							ReturnReportRequestDTO request = new ReturnReportRequestDTO(dates, choice, reason);
							report = goAdmin.retrieveReturnReport(request);
							System.out.println(report);
						} catch (java.text.ParseException e) {
							System.out.println("Enter date in dd/mm/yyyy format");
						} catch (GoAdminException e) {
							System.out.println("Error in generating Report >>>" + e.getMessage());
						}
						break;
					default:
						System.out.println("Invalid Choice ! Press a choice from 0 to 2");

					}
				}
				break;
			case 2:
				boolean entryQ = true;
				while (entryQ == true) {
					System.out.println(
							"Press 1 to get report for Wrong product\nPress 2 to get report for Incomplete Product\nPress 0 to go back.");
					reason = scanner.nextInt();
					scanner.nextLine();
					switch (reason) {
					case 0:
						entryQ = false;
						break;
					case 1:
						try {
							System.out.println("Enter the year in yyyy format");
							String year = scanner.nextLine();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
							sdf.setLenient(false);
							Date yearOfReport = sdf.parse(year);
							List<Date> dates = new ArrayList<>();
							dates.add(yearOfReport);
							ReturnReportRequestDTO request = new ReturnReportRequestDTO(dates, choice, reason);
							report = goAdmin.retrieveReturnReport(request);
							System.out.println(report);
						} catch (java.text.ParseException e) {
							System.out.println("Enter year in yyyy format only");
						} catch (GoAdminException e) {
							System.out.println("Error in generating Report >>>" + e.getMessage());
						}
						break;
					case 2:
						try {
							System.out.println("Enter the year in yyyy format");
							String year = scanner.nextLine();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
							sdf.setLenient(false);
							Date yearOfReport = sdf.parse(year);
							List<Date> dates = new ArrayList<>();
							dates.add(yearOfReport);
							ReturnReportRequestDTO request = new ReturnReportRequestDTO(dates, choice, reason);
							report = goAdmin.retrieveReturnReport(request);
							System.out.println(report);
						} catch (java.text.ParseException e) {
							System.out.println("Enter year in yyyy format only");
						} catch (GoAdminException e) {
							System.out.println("Error in generating Report >>>" + e.getMessage());
						}
						break;
					default:
						System.out.println("Invalid Choice ! Press a choice from 0 to 2");
					}

				}
				break;
			case 3:
				boolean entryY = true;
				while (entryY == true) {
					System.out.println(
							"Press 1 to get report for Wrong product\nPress 2 to get report for Incomplete Product\nPress 0 to go back.");
					reason = scanner.nextInt();
					scanner.nextLine();
					switch (reason) {
					case 0:
						entryY = false;
						break;
					case 1:
						try {
							System.out.println("Enter the starting Year in yyyy");
							String start = scanner.nextLine();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
							sdf.setLenient(false);
							Date startYear = sdf.parse(start);
							System.out.println("Enter the end Year in yyyy");
							String end = scanner.nextLine();
							Date endYear = sdf.parse(end);
							List<Date> dates = new ArrayList<>();
							dates.add(startYear);
							dates.add(endYear);
							ReturnReportRequestDTO request = new ReturnReportRequestDTO(dates, choice, reason);
							report = goAdmin.retrieveReturnReport(request);
							System.out.println(report);
						} catch (java.text.ParseException e) {
							System.out.println("Enter date in yyyy format only");
						} catch (GoAdminException e) {
							e.printStackTrace();
							System.out.println("Error in generating Report >>>" + e.getMessage());
						}
						break;
					case 2:
						try {
							System.out.println("Enter the starting Year in yyyy");
							String start = scanner.nextLine();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
							sdf.setLenient(false);
							Date startYear = sdf.parse(start);
							System.out.println("Enter the end Year in yyyy");
							String end = scanner.nextLine();
							Date endYear = sdf.parse(end);
							List<Date> dates = new ArrayList<>();
							dates.add(startYear);
							dates.add(endYear);
							ReturnReportRequestDTO request = new ReturnReportRequestDTO(dates, choice, reason);
							report = goAdmin.retrieveReturnReport(request);
							System.out.println(report);
						} catch (java.text.ParseException e) {
							System.out.println("Enter date in yyyy format only");
						} catch (GoAdminException e) {
							e.printStackTrace();
							System.out.println("Error in generating Report >>>" + e.getMessage());
						}
						break;
					default:
						System.out.println("Invalid Choice ! Press a choice from 0 to 2");

					}
				}
				break;
			default:
				System.out.println("Invalid Input. Press a choice from 0 to 3");

			}
		}

	}
}
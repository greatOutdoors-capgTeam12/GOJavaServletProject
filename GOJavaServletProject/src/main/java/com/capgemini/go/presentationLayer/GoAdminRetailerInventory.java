package com.capgemini.go.presentationLayer;

import java.net.ConnectException;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import com.capgemini.go.bean.RetailerInventoryBean;
import com.capgemini.go.service.GoAdminService;
import com.capgemini.go.service.GoAdminServiceImpl;

/**
 * 
 * @author kuroycho
 * 
 *         Driver class for retailer inventory functions
 */

public class GoAdminRetailerInventory {

	private static void printRetialerInventoryBeanList(List<RetailerInventoryBean> list) {
		for (RetailerInventoryBean bean : list) {
			System.out.println(bean);
		}
	}

	private static void DeliveryTimeReports(Scanner scanner) throws ConnectException {
		boolean wantsToExit = false;
		while (!wantsToExit) {
			System.out.println("******************* GREAT OUTDOOR  Menu ********************");
			System.out.println("Press Your Choice according to the User");
			System.out.println(" 1 for Product Delivery Time Reports ... \n "
					+ "2 for Product Category Delivery Time Reports ... \n 3 for Outlier Product within Outlier Category Time Reports ... \n"
					+ "Press 0 to exit from the Application ...");
			int choice = scanner.nextInt();
			scanner.nextLine();
			switch (choice) {
			case 1: {
				GoAdminService goAdmin = new GoAdminServiceImpl();
				System.out.print("Enter Retailer ID: ");
				String retailerId = scanner.nextLine();
				printRetialerInventoryBeanList(goAdmin
						.getDeliveryTimeReport(GoAdminService.ReportType.OUTLIER_ITEM_DELIVERY_TIME, retailerId, 0));
				break;
			}
			case 2: {
				GoAdminService goAdmin = new GoAdminServiceImpl();
				System.out.print("Enter Retailer ID: ");
				String retailerId = scanner.nextLine();
				printRetialerInventoryBeanList(goAdmin.getDeliveryTimeReport(
						GoAdminService.ReportType.OUTLIER_PRODUCT_CATEGORY_DELIVERY_TIME, retailerId, 0));
				break;
			}
			case 3: {
				GoAdminService goAdmin = new GoAdminServiceImpl();
				System.out.print("Enter Retailer ID: ");
				String retailerId = scanner.nextLine();
				printRetialerInventoryBeanList(goAdmin.getDeliveryTimeReport(
						GoAdminService.ReportType.OUTLIER_ITEM_IN_OUTLIER_PRODUCT_CATEGORY_DELIVERY_TIME, retailerId,
						0));
				break;
			}
			case 0: {
				wantsToExit = true;
				break;
			}
			default: {
				System.out.println(
						"************ Your choice is invalid .Enter a valid choice between 0 and 3 *********** ");
				break;
			}
			}
		}
		return;
	}

	private static void ShelfTimeReports(Scanner scanner) throws ConnectException {
		boolean wantsToExit = false;
		while (!wantsToExit) {
			System.out.println("******************* GREAT OUTDOOR  Menu ********************");
			System.out.println("Press Your Choice according to the User");
			System.out.println(
					" 1 for Monthly Shelf Time Reports ... \n 2 for Quarterly Shelf Time Reports ... \n 3 for Yearly Shelf Time Reports ... \n"
							+ "Press 0 to exit from the Application ...");
			int choice = scanner.nextInt();
			scanner.nextLine();
			switch (choice) {
			case 1: {
				GoAdminService goAdmin = new GoAdminServiceImpl();
				System.out.print("Enter Retailer ID: ");
				String retailerId = scanner.nextLine();
				System.out.print("Enter Month: ");
				int month = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Enter Year: ");
				int year = scanner.nextInt();
				scanner.nextLine();
				Calendar c = Calendar.getInstance();
				c.set(Calendar.YEAR, year);
				c.set(Calendar.MONTH, month);
				printRetialerInventoryBeanList(
						goAdmin.getShelfTimeReport(GoAdminService.ReportType.MONTHLY_SHELF_TIME, retailerId, c));
				break;
			}
			case 2: {
				GoAdminService goAdmin = new GoAdminServiceImpl();
				System.out.print("Enter Retailer ID: ");
				String retailerId = scanner.nextLine();
				System.out.print("Enter First Month of Quarter: ");
				int month = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Enter Year: ");
				int year = scanner.nextInt();
				scanner.nextLine();
				Calendar c = Calendar.getInstance();
				c.set(Calendar.YEAR, year);
				c.set(Calendar.MONTH, month);
				printRetialerInventoryBeanList(
						goAdmin.getShelfTimeReport(GoAdminService.ReportType.QUARTERLY_SHELF_TIME, retailerId, c));
				break;
			}
			case 3: {
				GoAdminService goAdmin = new GoAdminServiceImpl();
				System.out.print("Enter Retailer ID: ");
				String retailerId = scanner.nextLine();
				System.out.print("Enter Year: ");
				int year = scanner.nextInt();
				scanner.nextLine();
				Calendar c = Calendar.getInstance();
				c.set(Calendar.YEAR, year);
				printRetialerInventoryBeanList(
						goAdmin.getShelfTimeReport(GoAdminService.ReportType.YEARLY_SHELF_TIME, retailerId, c));
				break;
			}
			case 0: {
				wantsToExit = true;
				break;
			}
			default: {
				System.out.println(
						"************ Your choice is invalid .Enter a valid choice between 0 and 3 *********** ");
				break;
			}
			}
		}
		return;
	}

	public static void menu(Scanner scanner) throws ConnectException {
		boolean wantsToExit = false;
		while (!wantsToExit) {
			System.out.println("******************* GREAT OUTDOOR  Menu ********************");
			System.out.println("Press Your Choice according to the User");
			System.out.println(" 1 for Shelf Time Reports ... \n 2 for Delivery Time Reports ... \n "
					+ "Press 0 to exit from the Application ...");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1: {
				ShelfTimeReports(scanner);
				break;
			}
			case 2: {
				DeliveryTimeReports(scanner);
				break;
			}
			case 0: {
				wantsToExit = true;
				break;
			}
			default: {
				System.out.println(
						"************ Your choice is invalid .Enter a valid choice between 0 and 2 *********** ");
				break;
			}
			}
		}
		return;
	}
}

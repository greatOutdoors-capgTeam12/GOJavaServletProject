package com.capgemini.go.presentationLayer;

import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import com.capgemini.go.exception.SalesRepresentativeException;
import com.capgemini.go.service.SalesRepresentativeService;
import com.capgemini.go.service.SalesRepresentativeServiceImpl;
import com.capgemini.go.utility.GoLog;
import com.capgemini.go.utility.PropertiesLoader;

public class SalesRepPresentation {
	private static Properties exceptionProps = null;
	private static Properties goProps = null;
	private static final String EXCEPTION_PROPERTIES_FILE = "exceptionStatement.properties";
	private static final String GO_PROPERTIES_FILE = "goUtility.properties";

	public SalesRepPresentation() {
	}

	static void interfacesales() {

		boolean salesRepEntry = true;
		Scanner scanner = new Scanner(System.in);

		SalesRepresentativeService salesRepService = new SalesRepresentativeServiceImpl();
		String orderId;
		String reason;
		String productId;
		int qty;
		String userId;
		boolean status;
		while (salesRepEntry == true) {
			System.out.println("************* SALES REPRESENTATIVE MENU ***********");
			System.out.println("Press The key according to the operation you want to perform");
			System.out.println(" 1 RETURN AN ORDER ...\n 2 RETURN A PRODUCT...\n 3 CANCEL AN ORDER...\n 4 CANCEL A PRODUCT...\n 5 CHECK TARGET STATUS...\n 6 CHECK BONUS... \n 0 TO GO BACK TO THE MAIN MENU");
			int salesRepChoice = scanner.nextInt();
			scanner.nextLine();
			switch (salesRepChoice) {
			case 0:
				salesRepEntry = false;
				break;
			case 1:
				System.out.println("Enter the order Id");
				orderId = scanner.nextLine();
				System.out.println("Enter the user id");
				userId = scanner.nextLine();
				System.out.println("Enter the reason");
				reason = scanner.nextLine();
				try {
					exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
					goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);
					if ((salesRepService.validateUser(orderId)).equals(userId)) {
						status = salesRepService.returnOrder(orderId, userId, reason);
						if (status == true) {
							GoLog.logger.info(exceptionProps.getProperty("return_order_processed"));
						} else {
							GoLog.logger.error(exceptionProps.getProperty("failure_order"));
							break;
						}
					} else {
						GoLog.logger.error(exceptionProps.getProperty("validate_user_error"));
						break;
					}
				} catch (SalesRepresentativeException | IOException e) {
					GoLog.logger.error(exceptionProps.getProperty("validate_user_error"));
				}
				break;
			case 2:
				System.out.println("Enter the order Id");
				orderId = scanner.nextLine();
				System.out.println("Enter the user id");
				userId = scanner.nextLine();
				System.out.println("Enter the product id");
				productId = scanner.nextLine();
				System.out.println("Enter the reason");
				reason = scanner.nextLine();
				System.out.println("Enter the quantity");
				qty = scanner.nextInt();
				try {
					exceptionProps = PropertiesLoader.loadProperties(EXCEPTION_PROPERTIES_FILE);
					goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);
					if ((salesRepService.validateUser(orderId)).equals(userId)) {
						status = salesRepService.returnProduct(orderId, userId, productId, qty, reason);
						if (status == true) {
							GoLog.logger.info(exceptionProps.getProperty("return_order_processed"));
						} else {
							GoLog.logger.error(exceptionProps.getProperty("failure_order"));
						}
					} else {
						GoLog.logger.error(exceptionProps.getProperty("validate_user_error"));
						GoLog.logger.error(exceptionProps.getProperty("failure_order"));
					}
				} catch (SalesRepresentativeException | IOException e) {
					GoLog.logger.error(exceptionProps.getProperty("failure_order"));
				}
				break;
			case 3:
				System.out.println("Enter the order Id");
				orderId = scanner.nextLine();
				System.out.println("Enter the user id");
				userId = scanner.nextLine();
				try {
					System.out.println("In presentation layer");
					System.out.println(salesRepService.cancelOrder(orderId, userId));

				}

				catch (Exception e) {
					GoLog.logger.error(exceptionProps.getProperty("order_cant_be_cancelled"));
				}
				break;
			case 4:
				System.out.println("Enter the user Id");
				userId = scanner.nextLine();
				System.out.println("Enter the order id");
				orderId = scanner.nextLine();
				System.out.println("Enter the product Id");
				productId = scanner.nextLine();
				System.out.println("Enter the quantity to cancel");
				qty = scanner.nextInt();
				try {
					System.out.println("In presentation layer");
					System.out.println(salesRepService.cancelProduct(orderId, userId, productId, qty));
				}
				catch (Exception e) {
					GoLog.logger.error(exceptionProps.getProperty("product_cant_be_cancelled"));
				}
				break;
			case 5:
				System.out.println("Enter the user Id");
				userId = scanner.nextLine();
				try {
					System.out.println("In presentation layer");
					System.out.println(salesRepService.checkTargetSales(userId));

				}
				catch (Exception e) {
					GoLog.logger.error(exceptionProps.getProperty("user_id_invalid"));
				}
				break;
			case 6:
				System.out.println("Enter the user Id");
				userId = scanner.nextLine();
				try {
					System.out.println("In presentation layer");
					System.out.println(salesRepService.checkBonus(userId));

				}
				catch (Exception e) {
					GoLog.logger.error(exceptionProps.getProperty("user_id_invalid"));
				}
				break;


			default:
				System.out.println("Invalid Input . Enter a valid choice ");
			}
		}
	}

}

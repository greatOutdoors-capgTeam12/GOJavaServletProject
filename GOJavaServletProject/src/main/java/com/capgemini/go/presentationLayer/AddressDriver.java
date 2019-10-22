package com.capgemini.go.presentationLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.capgemini.go.dao.QuerryMapper;
import com.capgemini.go.dto.AddressDTO;
import com.capgemini.go.exception.DatabaseException;
import com.capgemini.go.exception.RetailerException;
import com.capgemini.go.service.RetailerService;
import com.capgemini.go.service.RetailerServiceImpl;
import com.capgemini.go.utility.DbConnection;

public class AddressDriver {
	public static Connection connection = null;

	@SuppressWarnings("static-access")
	public static void menu(Scanner scanner) {
		
		try {

			RetailerService retailerservice = new RetailerServiceImpl();

			connection = DbConnection.getInstance().getConnection();
			System.out.println("Enter User id");
			String userId = scanner.next();

			AddressDTO address = null;

			System.out.println("Welcom....." + userId);
			System.out.println(".......Enter Your Choices.......");
			System.out.println(".......Press 1 to add address.......");
			System.out.println(".......Press 2 to update address.......");
			System.out.println(".......Press 3 to change order address.......");
			System.out.println(".......Press 4 to delete address.......");
			System.out.println(".......Press 5 to view address.......");
			int retailerchoice = scanner.nextInt();
			scanner.nextLine();
			switch (retailerchoice)

			{
			case 1: {
				System.out.println("Welcome....." + userId + "/n <<<<proceed ahead to add address>>>>");

				System.out.println("Enter your buildingnum");
				String buildingnum2 = scanner.nextLine();
				System.out.println("Enter your city");
				String city2 = scanner.nextLine();
				System.out.println("Enter your state");
				String state2 = scanner.nextLine();
				System.out.println("Enter your country");
				String country2 = scanner.nextLine();
				System.out.println("Enter your zip");
				String zip2 = scanner.nextLine();

				PreparedStatement statement3 = connection.prepareStatement(QuerryMapper.COUNT_USERID_IN_ADDRESSDB);
				statement3.setString(1, userId);
				ResultSet rs3 = statement3.executeQuery();
				rs3.next();

				String addressId = (userId + "ADD" + "OO" + rs3.getInt(2));
				System.out.println("Your addressId is  " + addressId);
				address = new AddressDTO(addressId, userId, buildingnum2, city2, state2, country2, zip2, true);
				System.out.println(retailerservice.addAddress(address));
				break;

			}

			case 2: {
				System.out.println("Welcome....." + userId + "/n <<<<proceed ahead to update address>>>>");
				System.out.println("Enter ADDRESS id");
				String addressId = scanner.nextLine();
				System.out.println("Enter your buildingnum");
				String buildingnum2 = scanner.nextLine();
				System.out.println("Enter your city");
				String city2 = scanner.nextLine();
				System.out.println("Enter your state");
				String state2 = scanner.nextLine();
				System.out.println("Enter your country");
				String country2 = scanner.nextLine();
				System.out.println("Enter your zip");
				String zip2 = scanner.nextLine();
				address = new AddressDTO(addressId, userId, buildingnum2, city2, state2, country2, zip2, true);
				System.out.println(retailerservice.updateAddress(address));
				break;
			}
			case 3: {
				System.out.println("Welcome....." + userId + "/n proceed ahead to change order address");
				System.out.println("Enter ADDRESS id");
				String addressId = scanner.next();
				System.out.println("Enter order id");
				String orderId = scanner.next();
				System.out.println(retailerservice.changeAddress(address, orderId));
				break;
			}
			case 4: {
				System.out.println("Welcome....." + userId + "/n <<<<proceed ahead to delete address>>>>");

				System.out.println("Enter ADDRESS id");
				String addressId = scanner.next();
				address = new AddressDTO(addressId, userId, null, null, null, null, null, false);
				System.out.println(retailerservice.deleteAddress(address));
				break;

			}
			case 5: {
				System.out.println("Welcome....." + userId + "/n <<<<proceed ahead to view address>>>>");

				System.out.println("Enter user id");
				String userid = scanner.next();
				List<AddressDTO> allAddress = retailerservice.viewAllAddress(userid);
				System.out.println(allAddress);
				break;

			}
			default: {

			}

			}
		} catch (DatabaseException | SQLException | RetailerException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("done with this");
		
		
	}
}

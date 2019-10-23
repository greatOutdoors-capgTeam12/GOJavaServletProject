
package com.capgemini.go.presentationLayer;

import java.io.IOException;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import com.capgemini.go.bean.ProductBean;
import com.capgemini.go.dto.CartDTO;
import com.capgemini.go.dto.FrequentOrderedDTO;
import com.capgemini.go.dto.OrderDTO;
import com.capgemini.go.dto.ProductDTO;
import com.capgemini.go.dto.ProductFilterDTO;
import com.capgemini.go.dto.UserDTO;
import com.capgemini.go.dto.WrongProductNotificationDTO;
import com.capgemini.go.exception.AuthenticationException;
import com.capgemini.go.exception.DatabaseException;
import com.capgemini.go.exception.GoAdminException;
import com.capgemini.go.exception.IdGenerationException;
import com.capgemini.go.exception.ProductMasterException;
import com.capgemini.go.exception.RetailerException;
import com.capgemini.go.exception.SalesRepresentativeException;
import com.capgemini.go.exception.UserException;
import com.capgemini.go.service.GoAdminService;
import com.capgemini.go.service.GoAdminServiceImpl;
import com.capgemini.go.service.ProductMasterService;
import com.capgemini.go.service.ProductMasterServiceImpl;
import com.capgemini.go.service.RetailerService;
import com.capgemini.go.service.RetailerServiceImpl;
import com.capgemini.go.service.UserService;
import com.capgemini.go.service.UserServiceImpl;
import com.capgemini.go.utility.Authentication;
import com.capgemini.go.utility.DbConnection;
import com.capgemini.go.utility.GenerateOrderID;
import com.capgemini.go.utility.GoLog;
import com.capgemini.go.utility.PropertiesLoader;
import com.capgemini.go.utility.Validator;

public class GoInteractiveUserInterface {
	

	public static void main(String[] args)  {

		
		Properties goProps = null;
		final String GO_PROPERTIES_FILE = "goUtility.properties";
		try {
			goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);
		} catch (IOException e2) {

			System.out.println(" Error in loading Properties file");
		}

		Scanner scanner = new Scanner(System.in);
		List<ProductBean> activeProductView = new ArrayList<ProductBean>();
		UserService universalUser = new UserServiceImpl();
		try {
			activeProductView = universalUser.getAllProducts( );
		} catch (UserException| ConnectException e1) {
			
			e1.printStackTrace();
			System.out.println(" Error in retrieving Product Database");
		}
		String userAUH;
		boolean entry = true;
		try {
			while (entry == true) {

				System.out.println("******************* GREAT OUTDOOR  Menu ********************");
				System.out.println("Press Your Choice according to the User");
				System.out.println(
						" 1 for GO ADMIN ... \n 2 for SALES REPRESENTATIVE ... \n 3 for RETAILER... \n 4 for PRODUCT MASTER...  \n 5 for ANY USER \n Press 0 to exit from the Application ...");
				int choice = scanner.nextInt();
				scanner.nextLine();
				switch (choice) {
				case 0:
					entry = false;
					break;
				case 1:
					System.out.println("ENTER YOUR USER ID");
					userAUH = scanner.nextLine();
					if (Authentication.authenticateUser(userAUH, Integer.parseInt(goProps.getProperty("GO_ADMIN"))
							 ) == false) {
						break;
					}
					System.out.println("***********************WELCOME TO GREAT OUTDOOR*******************");
					boolean goAdminEntry = true;
					GoAdminService goAdmin = new GoAdminServiceImpl();
					while (goAdminEntry) {
						System.out.println("************************** GO ADMIN MENU ************************");
						System.out.println(
								"Press 1 to Add Product Master \nPress 2 to get Return Notification  \nPress 3 To get Time report \nPress 4 to get frequently ordered product suggestion \nPress 5 to view all Product Masters \nPress 6 for Advanced Admin Function \nPress 7 to  view Sales Reports\nPress 8 to view Return Report\nPress 0 to exit");
						int goAdminChoice = scanner.nextInt();
						scanner.nextLine();
						switch (goAdminChoice) {
						case 0:
							goAdminEntry = false;
							break;
						case 1:
							System.out.println("************* PRODUCT MASTER REGISTRATION ***************");
							System.out.println("Enter the GO ID : ");
							String userId = scanner.nextLine();
							System.out.println("Enter the Product Master Name : ");
							String userName = scanner.nextLine();
							if (!(userName.matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$"))) {
								System.out.println("User name can contain only character");
								break;
							}
							System.out.println("Enter the  Mail : ");
							String userMail = scanner.nextLine();
							System.out.println("Enter the Password : ");
							String userPassword = scanner.nextLine();
							if (Validator.validatePassword(userPassword) == false) {
								System.out.println(
										"Be between 8 and 40 characters long\r\n" + "Contain at least one digit.\r\n"
												+ "Contain at least one lower case character.\r\n"
												+ "Contain at least one upper case character.\r\n"
												+ "Contain at least on special character from [ @ # $ % ! . ].");
								break;
							}
							System.out.println("Enter the Contact Number : ");
							long userContact = 0L;
							try {
								userContact = scanner.nextLong();
								scanner.nextLine();
							} catch (InputMismatchException e) {
								System.out.println("User Contact Only contains Number");
								break;
							}

							try {
								goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);
								userPassword = Authentication.encrypt(userPassword,
										goProps.getProperty("SECURITY_KEY"));
								UserDTO newUser = new UserDTO(userName, userId, userMail, userPassword, userContact,
										Integer.parseInt(goProps.getProperty("RETAILER")), false);
								boolean status = goAdmin.addProductMaster(newUser );
								System.out.println("User Successfully Registered");
								System.out.println("Kindly Please Log-In");
							} catch (AuthenticationException | IOException | GoAdminException e) {

								System.out.println(e.getMessage());
							}
							break;
						case 2:
							try {
								List<WrongProductNotificationDTO> notification = goAdmin.getNotification( );
								System.out.println(
										"**************** Notification for Wrong Product Shipped *************");
								for (WrongProductNotificationDTO notify : notification) {
									System.out.println(notify);
								}
							} catch (GoAdminException  e) {
								System.out.println(e.getMessage());
							}
							break;
						case 3:
							
							GoAdminRetailerInventory.menu(scanner);
							break;
						case 4:
							try {

								System.out.println("Enter the retailer ID");
								String retailerID = scanner.next();
								String prodId = goAdmin.suggestFreqOrderProducts(retailerID );
								System.out.println("********** SUGGESTED PRODUCTS *************");
								System.out.println(prodId);
							} catch (GoAdminException e) {

								System.out.println(e.getMessage());
							}
							break;
						case 5:
							try {
								List<UserDTO> productMasters = goAdmin.viewProductMaster( );
								System.out.println("************ LIST OF PRODUCT MASTERS **************");
								for (UserDTO productMaster : productMasters) {
									System.out.println("Id : \t" + productMaster.getUserId() + "Name : \t"
											+ productMaster.getUserName() + "Mail : \t" + productMaster.getUserMail()
											+ "Phone Number : \t" + productMaster.getUserNumber());
								}
							} catch (GoAdminException e) {
								System.out.println(e.getMessage());
							}
							break;
						case 6:
							try {
								AdminModFunctions.adminFunctions(scanner);
							} catch (SalesRepresentativeException | RetailerException e) {

								System.out.println(e.getMessage());

							}
							break;
						case 7:
							GoAdminReports.reportInterfaces();
							break;
						case 8:
							ReturnReportDriver.menu(scanner, goAdmin);
							break;
						default:
							System.out.println("Invalid Input ! Please Enter Input from Menu");
						}
					}

					break;
				case 2:
					System.out.println("ENTER YOUR USER ID");
					userAUH = scanner.nextLine();
					if (Authentication.authenticateUser(userAUH, Integer.parseInt(goProps.getProperty("SALES_REP"))
							 ) == false) {
						break;
					}
					System.out.println("***********************WELCOME TO GREAT OUTDOOR*******************");
					SalesRepPresentation.interfacesales();
					break;
				case 3:
					System.out.println("ENTER YOUR USER ID");
					userAUH = scanner.nextLine();
					if (Authentication.authenticateUser(userAUH, Integer.parseInt(goProps.getProperty("RETAILER"))
							 ) == false) {
						break;
					}
					System.out.println("***********************WELCOME TO GREAT OUTDOOR*******************");
					boolean RetailerEntry = true;
					RetailerService retailerservice = new RetailerServiceImpl();
					while (RetailerEntry == true) {
						System.out.println("::::::::::: RETAILER MENU :::::::::::");
						System.out.println("SELECT THE KEY ACCORDING TO YOUR REQUIREMENT");
						System.out.println("Press 1 to manage addresss\n"
								+ "Press 2 to add product to frequently ordered list\n"
								+ "Press 3 to change the product address in frequently ordered list\n"
								+ "Press 4 to add item to Cart \n" + "Press 5 to check out \n" + "Press 0 to exit \n");
						int retailerChoice = scanner.nextInt();
						scanner.nextLine();
						switch (retailerChoice) {
						case 0:
							RetailerEntry = false;
							break;
						case 1:
							AddressDriver.menu(scanner);
							break;

						case 2:
							try {

								 

                                String retailerID = userAUH;
                                System.out.println("Enter Product ID");
                                String productID = scanner.next();
                                FrequentOrderedDTO freqOrder = new FrequentOrderedDTO(retailerID, productID);
                                boolean freqOrderStatus = retailerservice.addProductToWishlist(freqOrder);
                                if (freqOrderStatus == true) {
                                    System.out
                                            .println("Product has been successfully added to your frequent order list");
                                }
                            } catch (RetailerException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
						case 3 :
						{
							
							String retailerID = userAUH;
							try {
								System.out.println(retailerservice.fetchfavproduct(retailerID));
							} catch (UserException e) {
								
								e.printStackTrace();
							}
							break;
							
						}
						
						case 4:
							try {
								System.out.println("Enter the Product Id :");
								String productID = scanner.nextLine();
								System.out.println("Enter the Quantity to be added");
								int qty = scanner.nextInt();
								scanner.nextLine();
								CartDTO cartItem = new CartDTO(productID, userAUH, qty);
								boolean addItemToCart = retailerservice.addItemToCart(cartItem  );
								if (addItemToCart == true) {
									System.out.println("Product added to your cart...");
								}
							} catch (RetailerException e) {
								System.out.println("Error in adding item to Cart >>>" + e.getMessage());
							}
							break;
						case 5:
							try {
								System.out.println("Enter your address of delivery");
								String deliveryAddressId = scanner.nextLine();
								long millis = System.currentTimeMillis();
								java.sql.Date orderInitiationTime = new java.sql.Date(millis);
								java.sql.Timestamp orInitTimeStamp = new java.sql.Timestamp(millis);
								String orderId = "";
								try {
									orderId = "ORD" + GenerateOrderID.generate( );
								} catch (IdGenerationException e) {
									System.out.println("Error in generating Order Id >>>" + e.getMessage());
								}
								OrderDTO order = new OrderDTO(orderId, userAUH, deliveryAddressId, false,
										orderInitiationTime, null);
								boolean placeOrdStatus = retailerservice.placeOrder(order );
								if (placeOrdStatus == true) {
									System.out.println("Your Order with order id : \t " + order.getOrderId()
											+ "has been successfully initiated on " + order.getOrderInitiationTime()
											+ "\n It will be delivered to your address");
								}
							} catch (RetailerException e) {
								System.out.println("Error in placing order >>>" + e.getMessage());
							}
							break;
						default:
							System.out.println("Please enter any key from 1 or 2 or 3 or 4 or 5 or 6."
									+ " Don't press any two key simultaneously");
						}
					}

					break;
				case 4:
					System.out.println("ENTER YOUR USER ID");
					userAUH = scanner.nextLine();
					if (Authentication.authenticateUser(userAUH,
							Integer.parseInt(goProps.getProperty("PRODUCT_MASTER"))  ) == false) {
						break;
					}
					boolean productMasterEntry = true;
					ProductMasterService productMaster = new ProductMasterServiceImpl();
					System.out.println("***********************WELCOME TO GREAT OUTDOOR*******************");
					while (productMasterEntry == true) {
						System.out.println("************* PRODUCT MASTER MENU ***********");
						System.out.println("Press The key according to the operation you want to perform");
						System.out.println(
								" 1 to add a product... \n 2 to update a product ... \n 3 to delete a product ... \n 4 to increase product quantity \n Press 0 to go back to main menu");
						int productMasterChoice = scanner.nextInt();
						scanner.nextLine();
						switch (productMasterChoice) {
						case 0:
							productMasterEntry = false;
							break;
						case 1:
							System.out.println("Add a Product Function");
							System.out.println("Enter the Product Id : ");
							String productId = scanner.nextLine();
							System.out.println("Enter the Product Name : ");
							String productName = scanner.nextLine();
							if (!(productName.matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$"))) {
								System.out.println("Product name can contain only character");
								break;
							}
							System.out.println("Enter the Product Price : ");
							double productPrice;
							try {
								productPrice = scanner.nextDouble();
								scanner.nextLine();
							} catch (InputMismatchException e) {
								System.out.println("Price accepts Double value only");
								break;
							}
							if (productPrice < 0) {
								System.out.println("Enter a value greater than 0");
								break;
							}
							System.out.println("Enter the Product Colour : ");
							String productColour = scanner.nextLine();
							System.out.println("Enter the Product Dimension : ");
							String productDimension = scanner.nextLine();
							System.out.println("Enter the Product Quantity : ");
							int productQuantity = scanner.nextInt();
							scanner.nextLine();
							if (productQuantity < 1) {
								System.out.println("Minimum Quantity must be 1");
							}
							System.out.println("Enter the Product Specification : ");
							String productSpecification = scanner.nextLine();
							System.out.println(
									"Enter \n 1 for Camping \n 2 for Golf \n 3 for Mountaineering \n 4 for Outdoor \n 5 for Personal");
							System.out.println("Enter the Product Category : ");
							int productCategory;
							try {
								productCategory = scanner.nextInt();
								scanner.nextLine();
							} catch (InputMismatchException e) {
								System.out.println("Category takes only integer");
								break;
							}
							if (productCategory < 1 || productCategory > 5) {
								System.out.println("Enter a valid product Category");
								break;
							}
							System.out.println("Enter the Product Manufacturer : ");
							String manufacturer = scanner.nextLine();
							try {
								ProductDTO product = new ProductDTO(productId, productPrice, productColour,
										productDimension, productSpecification, manufacturer, productQuantity,
										productCategory, productName);
								boolean status = productMaster.addProduct(product  );
							} catch (ProductMasterException e) {
								System.out.println(e.getMessage());
							}

							break;
						case 2:
							System.out.println("Update a Product Function");
							System.out.println("Press Enter to not update the feature and Press 0 for numeric value ");
							System.out.println("Enter the Product Id : ");
							String updateProductId = scanner.nextLine();
							System.out.println("Enter the Product Name : ");
							String updateProductName = scanner.nextLine();
							System.out.println("Enter the Product Price : ");
							double updateProductPrice;
							try {
								updateProductPrice = scanner.nextDouble();
								scanner.nextLine();
							} catch (InputMismatchException e) {
								System.out.println("Price accepts Double value only");
								break;
							}
							if (updateProductPrice < 0) {
								System.out.println("Enter a value greater than 0");
								break;
							}
							System.out.println("Enter the Product Colour : ");
							String updateProductColour = scanner.nextLine();
							System.out.println("Enter the Product Dimension : ");
							String updateProductDimension = scanner.nextLine();
							System.out.println("Enter the Product Specification : ");
							String updateProductSpecification = scanner.nextLine();
							System.out.println(
									"Enter \n 1 for Camping \n 2 for Golf \n 3 for Mountaineering \n 4 for Outdoor \n 5 for Personal \n 0 to not update");
							System.out.println("Enter the Product Category : ");
							int updateProductCategory = 0;
							try {
								updateProductCategory = scanner.nextInt();
								scanner.nextLine();
							} catch (InputMismatchException e) {
								System.out.println("Category takes only integer value");
								break;
							}
							if (updateProductCategory < 0 || updateProductCategory > 5) {
								System.out.println("Enter Valid Product Category");
								break;
							}

							System.out.println("Enter the Product Manufacturer : ");
							String updateManufacturer = scanner.nextLine();
							try {
								ProductDTO product = new ProductDTO(updateProductId, updateProductPrice,
										updateProductColour, updateProductDimension, updateProductSpecification,
										updateManufacturer, 0, updateProductCategory, updateProductName);
								boolean status = productMaster.updateProduct(product );
							} catch (ProductMasterException e) {
								System.out.println(e.getMessage());
							}
							break;
						case 3:
							System.out.println(" ************** DELETE PRODUCT ***************");
							System.out.println("Enter the Product ID which is to be deleted :");
							String deleteProductId = scanner.nextLine();
							try {
								boolean status = productMaster.deleteProduct(deleteProductId  );
							} catch (ProductMasterException e) {
								System.out.println(e.getMessage());
							}
							break;
						case 4:
							System.out.println("INCREASE PRODUCT QUANTITY");
							System.out.println(" Enter the product id :");
							String prodId = scanner.nextLine();
							System.out.println(" Enter the quantity to be added ");
							int qty = 1;
							try {
								qty = scanner.nextInt();
								scanner.nextLine();
							} catch (InputMismatchException e) {
								System.out.println("Quantity can be integer only");
								break;
							}
							if (qty < 1) {
								System.out.println("Minimum quantity to be added must be 1");
								break;
							}
							try {
								ProductDTO product = new ProductDTO(prodId, 0.0, "", "", "", "", qty, 0, "");
								boolean status = productMaster.addExistingProduct(product);
							} catch (ProductMasterException e) {
								System.out.println(e.getMessage());
							}
							break;
						default:
							System.out.println("Invalid Input . Enter a valid choice ");
						}

					}
					break;
				case 5:
					System.out.println("***********************WELCOME TO GREAT OUTDOOR*******************");
					UserService user = new UserServiceImpl();

					String userPassword = null;
					String userId = null;
					boolean userEntry = true;

					while (userEntry == true) {
						System.out.println("************* USER MENU ***********");
						System.out.println("Press The key according to the operation you want to perform");
						System.out.println(
								" 1 for viewing all product ... \n 2 for searching a product ... \n 3 for filter product using several parameter ... \n 4 Sort product list accorsing to your choice ... \n 5 for login .. \n 6 for register \n 7 for Logout \n 8 for Fetching UserData \n Press 0 to go back to main menu");
						int userChoice = scanner.nextInt();
						scanner.nextLine();
						switch (userChoice) {
						case 0:
							userEntry = false;
							break;
						case 1:

							try {
								List<ProductBean> allProds = user.getAllProducts( );
								System.out.println(allProds.size() + " Record Found ...........");
								for (ProductBean product : allProds) {

									System.out.println(product);
								}
								activeProductView = allProds;
							} catch (UserException e) {

								GoLog.logger.error(e.getMessage());
								System.out.println(e.getMessage());
							}

							break;
						case 2:
							try {
								System.out.println("Enter the Product Name : ");
								String productName = scanner.nextLine();
								List<ProductBean> srchProds = user.searchProduct(productName );
								activeProductView = srchProds;
								System.out.println(srchProds.size() + " Record Found ...........");
								for (ProductBean product : srchProds) {

									System.out.println(product);
								}
							} catch (UserException e) {

								GoLog.logger.error(e.getMessage());
								System.out.println(e.getMessage());
							}

							break;
						case 3:
							try {
								System.out.println("Enter the Product Name : ");
								String productName = scanner.nextLine();
								System.out.println("Enter the Product Colour : ");
								String productColour = scanner.nextLine();
								System.out.println("Enter the low range");
								double low = scanner.nextDouble();
								scanner.nextLine();
								System.out.println("Enter the high range");
								double high = scanner.nextDouble();
								scanner.nextLine();
								System.out.println("Enter the brand name :");
								String manufacturer = scanner.nextLine();
								System.out.println("Enter the product Category");
								System.out.println(
										"Enter \n 1 for Camping \n 2 for Golf \n 3 for Mountaineering \n 4 for Outdoor \n 5 for Personal");
								int category = scanner.nextInt();
								scanner.nextLine();
								ProductFilterDTO proFltr = new ProductFilterDTO(productName, productColour, low, high,
										category, manufacturer);
								List<ProductBean> fltrProds = user.filterProduct(proFltr);
								activeProductView = fltrProds;
								System.out.println(fltrProds.size() + " Record Found ...........");
								for (ProductBean product : fltrProds) {

									System.out.println(product);
								}
							} catch (UserException e) {

								GoLog.logger.error(e.getMessage());
								System.out.println(e.getMessage());
							}
							break;
						case 4:
							System.out.println(
									"Enter 1 to sort by name \n Enter 2 to Sort from low to High Price \n Enter 3 to Sort from High to Low Price \n");
							int input = scanner.nextInt();
							scanner.nextLine();
							if (input < 1 || input > 3) {
								System.out.println("Enter the Value from 1 to 3");
								break;
							}
							if (input == 1) {
								activeProductView.sort((p1, p2) -> p1.getProductName().compareTo(p2.getProductName()));
								for (ProductBean product : activeProductView) {
									System.out.println(product);
								}
							} else if (input == 2) {
								activeProductView.sort((p1, p2) -> {
									if (p1.getPrice() > p2.getPrice())
										return 1;
									else if (p1.getPrice() < p2.getPrice())
										return -1;
									else
										return 0;
								});
								for (ProductBean product : activeProductView) {
									System.out.println(product);
								}
							} else if (input == 3) {
								activeProductView.sort((p1, p2) -> {
									if (p1.getPrice() > p2.getPrice())
										return -1;
									else if (p1.getPrice() < p2.getPrice())
										return 1;
									else
										return 0;
								});
								for (ProductBean product : activeProductView) {
									System.out.println(product);
								}
							}
							break;
						case 5:
							final String GO_PROPERTIES = "goUtility.properties";
							Properties goProp = null;
							System.out.println("************* USER LOG_IN ***************");
							System.out.println("Log-In to the Great Outdoors ");
							System.out.println("Enter the User ID : ");
							userId = scanner.nextLine();
							System.out.println("Enter the Password : ");
							userPassword = scanner.nextLine();

							try {
								goProp = PropertiesLoader.loadProperties(GO_PROPERTIES);
								// userPassword = Authentication.decrypt(userPassword,
								// goProp.getProperty("SECURITY_KEY"));//
								UserDTO existingUser = new UserDTO(null, userId, null, userPassword, 0, 0, false);
								boolean status = user.userLogin(existingUser);
								System.out.println("User Successfully Logged-In");

							} catch (Exception e) {
								System.out.println(e.getMessage());
							}

							break;
						case 6:

							System.out.println("************* USER REGISTRATION ***************");
							System.out.println("Welcome New User");
							System.out.println("Enter the User ID : ");
							userId = scanner.nextLine();
							System.out.println("Enter the User Name : ");
							String userName = scanner.nextLine();
							if (!(userName.matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$"))) {
								System.out.println("user name can contain only character");
								break;
							}
							System.out.println("Enter the User Mail : ");
							String userMail = scanner.nextLine();
							System.out.println("Enter the Password : ");
							userPassword = scanner.nextLine();
							if (Validator.validatePassword(userPassword) == false) {
								System.out.println(
										"Be between 8 and 40 characters long\r\n" + "Contain at least one digit.\r\n"
												+ "Contain at least one lower case character.\r\n"
												+ "Contain at least one upper case character.\r\n"
												+ "Contain at least on special character from [ @ # $ % ! . ].");
								break;
							}
							System.out.println("Enter the Contact Number : ");
							long userContact = 0L;
							try {
								userContact = scanner.nextLong();
								scanner.nextLine();
							} catch (InputMismatchException e) {
								System.out.println("User Contact Only contains Number");
								break;
							}
							System.out.println("Enter the Category [2-Sales Rep or 3-Retailer]");
							int userCategory = scanner.nextInt();
							scanner.nextLine();

							try {
								goProps = PropertiesLoader.loadProperties(GO_PROPERTIES_FILE);
								userPassword = Authentication.encrypt(userPassword,
										goProps.getProperty("SECURITY_KEY"));//
								UserDTO newUser = new UserDTO(userName, userId, userMail, userPassword, userContact,
										userCategory, false);
								boolean status = user.userRegistration(newUser);
								System.out.println("User Successfully Registered");
								System.out.println("Kindly Please Log-In");
							} catch (AuthenticationException | IOException | UserException e) {
								System.out.println(e.getMessage());
							}

							break;
						case 7:

							System.out.println("************* USER LOG_OUT ***************");
							System.out.println("Enter the User ID : ");
							userId = scanner.nextLine();

							try {
								UserDTO userLoggingOut = new UserDTO(null, userId, null, null, 0, 0, false);
								boolean status = user.userLogout(userLoggingOut);
								System.out.println("User Successfully Logged-Out");

							} catch (Exception e) {
								System.out.println(e.getMessage());
							}

							break;
						case 8 : 
							
							System.out.println("************* USER Fetch Details ***************");
							System.out.println("Enter the User ID : ");

							userId = scanner.nextLine();
							try
							{
								
								UserDTO loggedUser = user.fetchUser(userId);
								System.out.println("User Name is " + loggedUser.getUserName());
								System.out.println("User Id is " + loggedUser.getUserId());
								System.out.println("User Category is " + loggedUser.getUserCategory() + " i.e.");
								if(loggedUser.getUserCategory()==2) {
									System.out.println("Sales Representative");
								}
								else {
									System.out.println("Retailer");
								}
							}
							catch(Exception e)
							{
								System.out.println(e.getMessage());
							}

						default:
							System.out.println(" Invalid Input . Please Enter a Valid User Operation");

						}

					}
					break;

				default:
					System.out.println(
							"************ Your choice is invalid .Enter a valid choice from 1 to 4 *********** ");

				}

			}

		} catch (InputMismatchException e) {
			System.out.println("ENTER THE CHOICE IN NUMBER ONLY");
		}
		catch(ConnectException exp)
		{
			System.out.println(exp.getMessage());
		}
		 finally {
			
				 
				scanner.close();
				System.out.println(
						"********************** THANK YOU !! VISIT US AGAIN at www.greatoutdoors.com *******************");
			
			
		}

	}
}

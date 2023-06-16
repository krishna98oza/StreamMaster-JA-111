package com.masai;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.masai.entities.Customer;
import com.masai.entities.Content;
import com.masai.exceptions.DuplicateDataException;
import com.masai.exceptions.InvalidDetailsException;
import com.masai.exceptions.ContentException;
import com.masai.service.CustomerService;
import com.masai.service.CustomerServiceImpl;
import com.masai.service.ContentService;
import com.masai.service.ContentServicesImpl;
import com.masai.utility.Admin;
import com.masai.utility.FileExists;
import com.masai.utility.IDGeneration;

public class Main {

	// admin functionality
	private static void adminFunctionality(Scanner sc, Map<Integer, Content> products, Map<String, Customer> customers) throws InvalidDetailsException, ContentException  {
		// login for admin

		adminLogin(sc);

		ContentService prodService = new ContentServicesImpl();
		CustomerService cusService = new CustomerServiceImpl();
//		TransactionService trnsactionService = new TransactionServiceImpl();
		int choice = 0;
		try {
			do {
				System.out.println("Press 1 add the Content");
				System.out.println("Press 2 view all the Content");
				System.out.println("Press 3 delete the Content");
				System.out.println("Press 4 update the Content");
				System.out.println("Press 5 view all Users");
				System.out.println("Press 6 to log out");
				choice = sc.nextInt();

				switch (choice) {
				case 1:
					String added = adminAddContent(sc, products, prodService);
					System.out.println(added);
					break;
				case 2:

					adminViewAllContents(products, prodService);
					break;
				case 3:

					adminDeleteContent(sc, products, prodService);
					break;
				case 4:

					String upt = adminUpdateContent(sc, products, prodService);
					System.out.println(upt);
					break;
				case 5:
					adminViewAllCustomers(customers, cusService);

					break;
//				case 6:
//					adminViewAllTransactions(transactions, trnsactionService);
//					break;
				case 6:
					System.out.println("admin has successfully logout");
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + choice);
				}

			} while (choice <= 6);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void adminLogin(Scanner sc) throws InvalidDetailsException {

		System.out.println("Enter the user name");
		String userName = sc.next();
		System.out.println("Enter the password");
		String password = sc.next();
		if (userName.equals(Admin.username) && password.equals(Admin.password)) {

			System.out.println("successfully login");
		} else {
			throw new InvalidDetailsException("Invalid Admin Credentials");
		}
	}

	public static String adminAddContent(Scanner sc, Map<Integer, Content> products, ContentService prodService) {

		String str = null;
		System.out.println("please enter the Content details");
		System.out.println("Enter the Content title");
		String title = sc.next();
		System.out.println("Enter the Content duration (in minuts)");
		int duration = sc.nextInt();
		System.out.println("Enter the Content rating");
		double rating = sc.nextDouble();
		System.out.println("Enter the Content category");
		String cate = sc.next();

		Content prod = new Content(IDGeneration.generateId(), title, duration, rating, cate);

		str = prodService.addContent(prod, products);// considering all details are valid

		return str;

	}

	public static void adminViewAllContents(Map<Integer, Content> products, ContentService prodService)
			throws ContentException {
		prodService.viewAllContents(products);
	}

	public static void adminDeleteContent(Scanner sc, Map<Integer, Content> products, ContentService prodService)
			throws ContentException {

		System.out.println("please enter the id of Content to be deleted");
		int id = sc.nextInt();
		prodService.deleteContent(id, products);
	}

	public static String adminUpdateContent(Scanner sc, Map<Integer, Content> products, ContentService prodService)
			throws ContentException {
		String result = null;
		System.out.println("please enter the id of the Content which is to be updated");
		int id = sc.nextInt();
		System.out.println("Enter the updated details ");

		System.out.println("Enter the Content title");
		String title = sc.next();

		System.out.println("Enter the  Content duration");
		int duration = sc.nextInt();

		System.out.println("Enter the Content rating");
		double rating = sc.nextDouble();

		System.out.println("Enter the Content category");
		String cate = sc.next();

		Content update = new Content(id, title, duration, rating, cate);

		result = prodService.updateContent(id, update, products);
		return result;
	}

	public static void adminViewAllCustomers(Map<String, Customer> customers, CustomerService cusService)
			throws ContentException {
		List<Customer> list = cusService.viewAllCustomers(customers);

		for (Customer c : list) {
			System.out.println(c);
		}
	}
	// customer functionality
	public static void customerFunctionality(Scanner sc, Map<String, Customer> customers,
			Map<Integer, Content> products)
			throws InvalidDetailsException {

		CustomerService cusService = new CustomerServiceImpl();
		ContentService prodService = new ContentServicesImpl();
		
		// User login
		System.out.println("please enter the following details to login");
		System.out.println("please enter the email");
		String email = sc.next();
		System.out.println("Enter the password");
		String pass = sc.next();
		customerLogin(email,pass, customers, cusService);

		try {
			int choice = 0;
			do {
				System.out.println("Select the option of your choice");
				System.out.println("Press 1 to view all Contents");
				System.out.println("Press 2 to like a Content");
				System.out.println("Press 3 to add Content to a Favorites");
				System.out.println("Press 4 view Favorite List");
				System.out.println("Press 5 view my details");
				System.out.println("Press 6 to logout");
				choice = sc.nextInt();

				switch (choice) {
				case 1:
					customerViewAllContents(products, prodService);
					break;
				case 2:
					String result = customerBuyContent(sc, email, products, customers, cusService);
					System.out.println(result);
					break;
				case 3:
					String moneyAdded = customerAddMoneyToWallet(sc, email, customers, cusService);
					System.out.println(moneyAdded);
					break;
				case 4:
					double walletBalance = customerViewWalletBalance(email, customers, cusService);
					System.out.println("Wallet balance is: " + walletBalance);
					break;
				case 5:
					customerViewMyDetails(email, customers, cusService);
					break;
				case 6:
					System.out.println("you have successsfully logout");
					break;
				default:
					System.out.println("invalid choice");
					break;
				}

			} while (choice <= 6);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void customerSignup(Scanner sc, Map<String, Customer> customers) throws DuplicateDataException {
		System.out.println("please enter the following details to Signup");
		System.out.println("please enter the user name");
		String name = sc.next();
		System.out.println("Enter the password");
		String pass = sc.next();
		System.out.println("enter the address");
		String address = sc.next();
		System.out.println("Enter the email id");
		String email = sc.next();
		System.out.println("Enter the balance to be added into the wallet");
		double balance = sc.nextDouble();
		Customer cus = new Customer(balance, name, pass, address, email);

		CustomerService cusService = new CustomerServiceImpl();
		cusService.signUp(cus, customers);
		System.out.println("User has Succefully sign up");

	}

	public static void customerLogin(String email,String pass, Map<String, Customer> customers, CustomerService cusService)
			throws InvalidDetailsException {
		cusService.login(email, pass,customers);
		System.out.println("User has successfully login");

	}

	public static void customerViewAllContents(Map<Integer, Content> products, ContentService prodService)
			throws ContentException {
		prodService.viewAllContents(products);
	}

	public static String customerBuyContent(Scanner sc, String email, Map<Integer, Content> products,
			Map<String, Customer> customers, CustomerService cusService)
			throws InvalidDetailsException, ContentException {
		System.out.println("Enter the Show id");
		int id = sc.nextInt();
		System.out.println("Please rate this show");
		double rate = sc.nextInt();
		cusService.buyContent(id, rate, email, products, customers);

		return "Thank you for your ratings";

	}

	public static String customerAddMoneyToWallet(Scanner sc, String email, Map<String, Customer> customers,
			CustomerService cusService) {
		System.out.println("please enter the amount");
		double money = sc.nextDouble();
		boolean added = cusService.addMoneyToWallet(money, email, customers);

		return "Amount: " + money + " successfully added to your wallet";
	}

	public static double customerViewWalletBalance(String email, Map<String, Customer> customers,
			CustomerService cusService) {
		double walletBalance = cusService.viewWalletBalance(email, customers);
		return walletBalance;
	}

	public static void customerViewMyDetails(String email, Map<String, Customer> customers,
			CustomerService cusService) {
		Customer cus = cusService.viewCustomerDetails(email, customers);
		System.out.println("name : " + cus.getUsername());
		System.out.println("address : " + cus.getAddress());
		System.out.println("email : " + cus.getEmail());
		System.out.println("wallet balance : " + cus.getWalletBalance());
	}


	
	
	public static void main(String[] args) {
//file check
		Map<Integer, Content> products = FileExists.productFile();
		Map<String, Customer> customers = FileExists.customerFile();
		
		Scanner sc = new Scanner(System.in);

		System.out.println("Welcome , in Content Management System");

		try {

			int preference = 0;
			do {
				System.out.println("Please enter your preference");
				System.out.println('1' + "--> Admin login");
				System.out.println('2' + "--> User login");
				System.out.println('3' + "--> User sigup");
				System.out.println('0' + "--> Go to Home");
				
				preference = sc.nextInt();
				switch (preference) {
				case 1:
					adminFunctionality(sc, products, customers);
					break;
				case 2:
					customerFunctionality(sc, customers, products);
					break;

				case 3:
					customerSignup(sc, customers);
					break;

				case 0:
					System.out.println("successfully existed from the system");

					break;

				default:
					throw new IllegalArgumentException("Invalid Selection");
				}

			}

			while (preference != 0);

		} catch (Exception e) {

			System.out.println(e.getMessage());
		} finally {
			// serialization (finally always executed)
			try {
				ObjectOutputStream poos = new ObjectOutputStream(new FileOutputStream("Content.ser"));
				poos.writeObject(products);
				ObjectOutputStream coos = new ObjectOutputStream(new FileOutputStream("Customer.ser"));
				coos.writeObject(customers);

        			//	System.out.println("serialized..........");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
		}

	}

}

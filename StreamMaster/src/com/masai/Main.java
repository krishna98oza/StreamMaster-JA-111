package com.masai;

import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;

import com.masai.utility.Admin;
import com.masai.utility.FileExists;
import com.masai.utility.IDGeneration;
import com.masai.entities.Customer;
import com.masai.entities.Product;
import com.masai.entities.Transaction;
import com.masai.service.CustomerService;
import com.masai.service.CustomerServiceImpl;
import com.masai.service.ProductService;
import com.masai.service.ProductServicesImpl;
import com.masai.service.TransactionService;
import com.masai.service.TransactionServiceImpl;
import com.masai.exceptions.DuplicateDataException;
import com.masai.exceptions.InvalidDetailsException;
import com.masai.exceptions.ProductException;
import com.masai.exceptions.TransactionException;


//import com.masai.utility.Admin;

public class Main {
	//About Admin features;

//	@SuppressWarnings("unused")
//	private static void adminFunctionality(Scanner sc, Map<Integer, Product> products, Map<String, Customer> customers,
//			List<Transaction> transactions)  {
//		// admin login
////throws InvalidDetailsException, ProductException, TransactionException
//		adminLogin(sc);
//
//	}
//throws InvalidDetailsException
	public static void adminLogin(Scanner sc)  {

		System.out.println("Enter the user name");
		String userName = sc.next();
		System.out.println("Enter the password");
		String password = sc.next();
		if (userName.equals(Admin.username) && password.equals(Admin.password)) {

			System.out.println("successfully login");
		}
//		else {
//			throw new InvalidDetailsException("Invalid Admin Credentials");
//		}
	}

	
	public static void customerFunctionality(Scanner sc, Map<String, Customer> customers,
			Map<Integer, Product> products, List<Transaction> transactions)
			throws InvalidDetailsException, TransactionException {

		CustomerService cusService = new CustomerServiceImpl();
		ProductService prodService = new ProductServicesImpl();
		TransactionService trnsactionService = new TransactionServiceImpl();

		// Customer login
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
				System.out.println("Press 1 to view all products");
				System.out.println("Press 2 to buy a product");
				System.out.println("Press 3 to add money to a wallet");
				System.out.println("Press 4 view wallet balance");
				System.out.println("Press 5 view my details");
				System.out.println("Press 6 view my transactions");
				System.out.println("Press 7 to logout");
				choice = sc.nextInt();

//				switch (choice) {
//				case 1:
//					customerViewAllProducts(products, prodService);
//					break;
//				case 2:
//					String result = customerBuyProduct(sc, email, products, customers, transactions, cusService);
//					System.out.println(result);
//					break;
//				case 3:
//					String moneyAdded = customerAddMoneyToWallet(sc, email, customers, cusService);
//					System.out.println(moneyAdded);
//					break;
//				case 4:
//					double walletBalance = customerViewWalletBalance(email, customers, cusService);
//					System.out.println("Wallet balance is: " + walletBalance);
//					break;
//				case 5:
//					customerViewMyDetails(email, customers, cusService);
//					break;
//				case 6:
//					customerViewCustomerTransactions(email, transactions, trnsactionService);
//					break;
//				case 7:
//					System.out.println("you have successsfully logout");
//					break;
//				default:
//					System.out.println("invalid choice");
//					break;
//				}
//
//			} while (choice <= 6);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
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
		System.out.println("customer has Succefully sign up");

	}

	public static void customerLogin(String email,String pass, Map<String, Customer> customers, CustomerService cusService)
			throws InvalidDetailsException {
		cusService.login(email, pass,customers);
		System.out.println("Customer has successfully login");

	}



public static void main(String[] args) {
	//file check
			Map<Integer, Product> products = FileExists.productFile();
			Map<String, Customer> customers = FileExists.customerFile();
//	//		System.out.println(products.size());
//	//		System.out.println(customers.size());
//	//		System.out.println(transactions.size());

			Scanner sc = new Scanner(System.in);

			System.out.println("Welcome to Stream Master");
			
			int preference = 0;
			do {
				System.out.println("Please enter your preference, " + " '1' : Admin login , '2' : Producer Signup, '3' : Producer login , "
				+ " '4' : User signup, '5' : User login ,  '0' for exit");
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
				ObjectOutputStream poos = new ObjectOutputStream(new FileOutputStream("Product.ser"));
				poos.writeObject(products);
				ObjectOutputStream coos = new ObjectOutputStream(new FileOutputStream("Customer.ser"));
				coos.writeObject(customers);

				ObjectOutputStream toos = new ObjectOutputStream(new FileOutputStream("Transactions.ser"));
				toos.writeObject(transactions);
			//	System.out.println("serialized..........");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
		}
			
}

}


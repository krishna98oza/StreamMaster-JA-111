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
//	public Main() {
//		// TODO Auto-generated constructor stub
//		System.out.println("Krishna");
//	}
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




public static void main(String[] args) {
	//file check
//			Map<Integer, Product> products = FileExists.productFile();
//			Map<String, Customer> customers = FileExists.customerFile();
//			List<Transaction> transactions = FileExists.transactionFile();
//	//		System.out.println(products.size());
//	//		System.out.println(customers.size());
//	//		System.out.println(transactions.size());

			Scanner sc = new Scanner(System.in);

			System.out.println("Welcome , in Product Management System");
			
			int preference = 0;
			do {
				System.out.println("Please enter your preference, " + " '1' --> Admin login , '2' --> Customer login , "
				+ "'3' for Customer signup, '0' for exit");
				preference = sc.nextInt();
				if(preference ==1) {
					adminLogin(sc);
				}
             }while (preference != 0);
			
}

}


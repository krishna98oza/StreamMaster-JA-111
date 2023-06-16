package com.masai.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.masai.entities.Customer;
import com.masai.entities.Product;
import com.masai.entities.Transaction;
import com.masai.exceptions.DuplicateDataException;
import com.masai.exceptions.InvalidDetailsException;
import com.masai.exceptions.ProductException;
import com.masai.exceptions.TransactionException;

public class CustomerServiceImpl implements CustomerService {

	@Override
	public void signUp(Customer cus, Map<String, Customer> customers) throws DuplicateDataException {

		if (customers.containsKey(cus.getEmail())) {
			throw new DuplicateDataException("Customer already exists , please login");
		} else {

			customers.put(cus.getEmail(), cus);

		}

	}

	@Override
	public boolean login(String email,String password, Map<String, Customer> customers) throws InvalidDetailsException {

		if (customers.containsKey(email) ) {
			
			if(customers.get(email).getPassword().equals(password)) {
				return true;
			}
			else {
				throw new InvalidDetailsException("Invalid Credentials");
			}
			
		} else {
			throw new InvalidDetailsException("you have not sign up yet, please signup");
		}

	}
	
}
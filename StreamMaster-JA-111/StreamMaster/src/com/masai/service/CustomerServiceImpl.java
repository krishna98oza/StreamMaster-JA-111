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
import com.masai.entities.Content;
import com.masai.exceptions.DuplicateDataException;
import com.masai.exceptions.InvalidDetailsException;
import com.masai.exceptions.ContentException;

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

	@Override
	public boolean playShow(int id, String title, Map<Integer, Content> products,
			Map<String, Customer> customers)
			throws InvalidDetailsException, ContentException {

		if (products.size() == 0)
			throw new ContentException("Please select show");

		if (products.containsKey(id)) {

			Content prod = products.get(id);

		} else {
			throw new InvalidDetailsException("This Show not available with id: " + id);
		}

		return false;
	}

	@Override
	public boolean addRatingToShow(double amount, String email, Map<String, Customer> customers) {
		// TODO Auto-generated method stub

		Customer cus = customers.get(email);

		cus.setWalletBalance(cus.getWalletBalance() + amount);

		customers.put(email, cus);

		return true;
	}

	@Override
	public void viewFavorites(Map<Integer, Content> products) throws ContentException {
		// TODO Auto-generated method stub
		if (products != null && products.size() > 0) {
			for (Map.Entry<Integer, Content> me : products.entrySet()) {
				System.out.println(me.getValue());
			}

		} else {
			throw new ContentException("Content List is empty");
		}
	}

	@Override
	public Customer viewCustomerDetails(String email, Map<String, Customer> customers) {

		if (customers.containsKey(email)) {

			return customers.get(email);

		}

		return null;
	}

	@Override
	public List<Customer> viewAllCustomers(Map<String, Customer> customers) throws ContentException {
		// TODO Auto-generated method stub
		List<Customer> list = null;

		if (customers != null && customers.size() > 0) {
			Collection<Customer> coll = customers.values();
			list = new ArrayList<>(coll);
		} else {
			throw new ContentException("Customer list is empty");
		}

		return list;
	}

}

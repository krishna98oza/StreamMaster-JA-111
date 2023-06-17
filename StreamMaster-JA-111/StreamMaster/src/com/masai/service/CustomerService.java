package com.masai.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.masai.entities.Customer;
import com.masai.entities.Content;
import com.masai.exceptions.DuplicateDataException;
import com.masai.exceptions.InvalidDetailsException;
import com.masai.exceptions.ContentException;

public interface CustomerService {

	public boolean login(String email,String password, Map<String, Customer> customers) throws InvalidDetailsException;

	public void signUp(Customer cus, Map<String, Customer> customers) throws DuplicateDataException;

	public boolean playShow(int id, String title, Map<Integer, Content> products,
			Map<String, Customer> customers)
			throws InvalidDetailsException, ContentException;

	public boolean addRatingToShow(double amount, String email, Map<String, Customer> customers);

	public void viewFavorites(Map<Integer, Content> products) throws ContentException;

	public Customer viewCustomerDetails(String email, Map<String, Customer> customers);

	public List<Customer> viewAllCustomers(Map<String, Customer> customers) throws ContentException;

}

package com.masai.entities;

import java.io.Serializable;

public class Customer extends User implements Serializable {

	public Customer(double walletBalance, String username, String password, String address, String email) {
		super(username, password, address, email);
	}

}

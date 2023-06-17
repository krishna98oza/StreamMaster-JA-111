package com.masai.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.masai.entities.Customer;
import com.masai.entities.Content;
import com.masai.exceptions.ContentException;

public class ContentServicesImpl implements ContentService {

	@Override
	public String addContent(Content prod, Map<Integer, Content> products) {
		// TODO Auto-generated method stub

		products.put(prod.getId(), prod);

		return "Content added successfully";

	}

	@Override
	public void viewAllContents(Map<Integer, Content> products) throws ContentException {
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
	public void deleteContent(int id, Map<Integer, Content> products) throws ContentException {

		// System.out.println(products);
		if (products != null && products.size() > 0) {

			if (products.containsKey(id)) {
				products.remove(id);
				System.out.println("product deleted successfully");

			} else {
				throw new ContentException("Content not found");
			}

		} else {
			throw new ContentException("Content list is empty");
		}

	}

	@Override
	public String updateContent(int id, Content prod, Map<Integer, Content> products) throws ContentException {

		if (products != null && products.size() > 0) {

			if (products.containsKey(id)) {
				products.put(id, prod);
				return "Content has successfully updated";
			} else {
				throw new ContentException("Content not found");
			}

		} else {
			throw new ContentException("Content list is empty");
		}

	}
 
}

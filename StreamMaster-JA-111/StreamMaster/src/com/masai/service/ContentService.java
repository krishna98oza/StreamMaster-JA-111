package com.masai.service;

import java.util.List;
import java.util.Map;

import com.masai.entities.Customer;
import com.masai.entities.Content;
import com.masai.exceptions.DuplicateDataException;
import com.masai.exceptions.ContentException;

public interface ContentService {

	public String addContent(Content pro, Map<Integer, Content> products);

	public void viewAllContents(Map<Integer, Content> products) throws ContentException;

	public void deleteContent(int id, Map<Integer, Content> products) throws ContentException;

	public String updateContent(int id, Content prod, Map<Integer, Content> products) throws ContentException;

	
	
}

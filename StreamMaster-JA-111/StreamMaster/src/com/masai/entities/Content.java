package com.masai.entities;

import java.io.Serializable;
import java.util.Objects;

public class Content implements Serializable{

	private int id;
	private String title;
	private int Duration;
	private double rating;
	private String category;

	public Content() {
		super();
	}

	public Content(int id, String title, int Duration, double rating, String category) {
		super();
		this.id = id;
		this.title = title;
		this.Duration = Duration;
		this.rating = rating;
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return title;
	}

	public void setName(String title) {
		this.title = title;
	}

	public double getQty() {
		return Duration;
	}

	public void setQty(int Duration) {
		this.Duration = Duration;
	}

	public double getPrice() {
		return rating;
	}

	public void setPrice(double rating) {
		this.rating = rating;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Content [id=" + id + ", title=" + title + ", Duration=" + Duration + ", rating=" + rating + ", category=" + category
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(category, id, title, rating, Duration);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Content other = (Content) obj;
		return Objects.equals(category, other.category) && id == other.id && Objects.equals(title, other.title)
				&& Double.doubleToLongBits(rating) == Double.doubleToLongBits(other.rating) && Duration == other.Duration;
	}

}

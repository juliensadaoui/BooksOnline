package com.insta.fjee.library.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RENT_BOOK")
public class RentBook implements java.io.Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7777444157790606117L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;
	
	private String isbn;
	
	private Date startDate;

	private Date endDate;
	
	@ManyToOne
	@JoinColumn(name = "USER_ACCOUNT_PK", nullable = false)
	private User user;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "RentBook [ID=" + ID + ", isbn=" + isbn + ", startDate="
				+ startDate + ", endDate=" + endDate + ", user=" + user + "]";
	}
}

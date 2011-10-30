package com.insta.fjee.library.core.dto;

import java.util.Date;

/**
 * 	This class represents a rental of book
 * 
 * @author julien
 *
 */
public class RentBookDTO
{
	private int ID;
	
	private String isbn;
	
	private Date startDate;

	private Date endDate;
	
	private String login;

	/**
	 * 	Return the identifier of rental
	 * 
	 * @return -  identifier of rental
	 */
	public int getID() {
		return ID;
	}

	/**
	 * 	Set the identifier of rental
	 * 
	 * @param iD - identifier of rental
	 */
	public void setID(int iD) {
		ID = iD;
	}

	//Search by ISBN to find a specific book 
	/**
	 * 	Return the ISBN book identifier of rental
	 * 
	 * @return ISBN book identifier of rental
	 */
	public String getIsbn() 
	{
		return isbn;
	}

	/**
	 * 	Set the ISBN book identifier of rental
	 * 
	 * @param isbn - ISBN book identifier of rental
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	/**
	 * 	Return the start date of rental
	 * 	
	 * @return start date of rental
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * 	Set the start date of rental
	 * 
	 * @param startDate - start date of rental
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * 	Return the end date of rental
	 * 
	 * @return - end date of rental
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * 	Set the end date of rental
	 * 
	 * @param endDate - end date of rental
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * 	Return the login user identifier of rental
	 * 
	 * @return login user identifier of rental
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * 	Set the login user identifier of rental
	 * 
	 * @param login - login user identifier of rental
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	@Override
	public String toString() {
		return "RentBookDTO [ID=" + ID + ", isbn=" + isbn + ", startDate="
				+ startDate + ", endDate=" + endDate + ", login=" + login + "]";
	}
	
	
}

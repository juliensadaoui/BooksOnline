package com.insta.fjee.library.stock.dto;

public class ExemplaryDTO implements java.io.Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6467019219819859086L;

	private String isbn;
	
	private int nb;

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getNb() {
		return nb;
	}

	public void setNb(int nb) {
		this.nb = nb;
	}

	@Override
	public String toString() {
		return "ExemplaryDTO [isbn=" + isbn + ", nb=" + nb + "]";
	}

}

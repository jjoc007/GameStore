package com.stefanini.tigo.test.model;

import java.util.Date;

public class CustomerPurchase {

	private Date dateOfPurchase;
	private String purchaseDetails;
	private String purchaseID;

	private Customer customer;

	public CustomerPurchase() {
		super();
	}

	public CustomerPurchase(Date dateOfPurchase, String purchaseDetails, String purchaseID, Customer customer) {
		super();
		this.dateOfPurchase = dateOfPurchase;
		this.purchaseDetails = purchaseDetails;
		this.purchaseID = purchaseID;
		this.customer = customer;
	}

	public Date getDateOfPurchase() {
		return dateOfPurchase;
	}

	public String getPurchaseDetails() {
		return purchaseDetails;
	}

	public String getPurchaseID() {
		return purchaseID;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setDateOfPurchase(Date dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}

	public void setPurchaseDetails(String purchaseDetails) {
		this.purchaseDetails = purchaseDetails;
	}

	public void setPurchaseID(String purchaseID) {
		this.purchaseID = purchaseID;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((dateOfPurchase == null) ? 0 : dateOfPurchase.hashCode());
		result = prime * result + ((purchaseDetails == null) ? 0 : purchaseDetails.hashCode());
		result = prime * result + ((purchaseID == null) ? 0 : purchaseID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerPurchase other = (CustomerPurchase) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (dateOfPurchase == null) {
			if (other.dateOfPurchase != null)
				return false;
		} else if (!dateOfPurchase.equals(other.dateOfPurchase))
			return false;
		if (purchaseDetails == null) {
			if (other.purchaseDetails != null)
				return false;
		} else if (!purchaseDetails.equals(other.purchaseDetails))
			return false;
		if (purchaseID == null) {
			if (other.purchaseID != null)
				return false;
		} else if (!purchaseID.equals(other.purchaseID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CustomerPurchase [dateOfPurchase=" + dateOfPurchase + ", purchaseDetails=" + purchaseDetails
				+ ", purchaseID=" + purchaseID + ", customer=" + customer + "]";
	}

}

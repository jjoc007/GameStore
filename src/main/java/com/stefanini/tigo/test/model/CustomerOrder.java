package com.stefanini.tigo.test.model;

import java.util.Date;

public class CustomerOrder {

	private Date dateOfOrder;
	private String orderDetails;
	private String orderID;

	private Customer customer;

	public CustomerOrder() {
		super();
	}

	public CustomerOrder(Date dateOfOrder, String orderDetails, String orderID, Customer customer) {
		super();
		this.dateOfOrder = dateOfOrder;
		this.orderDetails = orderDetails;
		this.orderID = orderID;
		this.customer = customer;
	}

	public Date getDateOfOrder() {
		return dateOfOrder;
	}

	public String getOrderDetails() {
		return orderDetails;
	}

	public String getOrderID() {
		return orderID;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setDateOfOrder(Date dateOfOrder) {
		this.dateOfOrder = dateOfOrder;
	}

	public void setOrderDetails(String orderDetails) {
		this.orderDetails = orderDetails;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((dateOfOrder == null) ? 0 : dateOfOrder.hashCode());
		result = prime * result + ((orderDetails == null) ? 0 : orderDetails.hashCode());
		result = prime * result + ((orderID == null) ? 0 : orderID.hashCode());
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
		CustomerOrder other = (CustomerOrder) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (dateOfOrder == null) {
			if (other.dateOfOrder != null)
				return false;
		} else if (!dateOfOrder.equals(other.dateOfOrder))
			return false;
		if (orderDetails == null) {
			if (other.orderDetails != null)
				return false;
		} else if (!orderDetails.equals(other.orderDetails))
			return false;
		if (orderID == null) {
			if (other.orderID != null)
				return false;
		} else if (!orderID.equals(other.orderID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CustomerOrder [dateOfOrder=" + dateOfOrder + ", orderDetails=" + orderDetails + ", orderID=" + orderID
				+ ", customer=" + customer + "]";
	}

}

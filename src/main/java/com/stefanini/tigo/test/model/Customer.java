package com.stefanini.tigo.test.model;

public class Customer {

	private String customerAddress;
	private String customerCode;
	private String customerDetails;
	private String customerID;
	private String customerName;

	public Customer() {
		super();
	}

	public Customer(String customerAddress, String customerCode, String customerDetails, String customerID,
			String customerName) {
		super();
		this.customerAddress = customerAddress;
		this.customerCode = customerCode;
		this.customerDetails = customerDetails;
		this.customerID = customerID;
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public String getCustomerDetails() {
		return customerDetails;
	}

	public String getCustomerID() {
		return customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public void setCustomerDetails(String customerDetails) {
		this.customerDetails = customerDetails;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerAddress == null) ? 0 : customerAddress.hashCode());
		result = prime * result + ((customerCode == null) ? 0 : customerCode.hashCode());
		result = prime * result + ((customerDetails == null) ? 0 : customerDetails.hashCode());
		result = prime * result + ((customerID == null) ? 0 : customerID.hashCode());
		result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
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
		Customer other = (Customer) obj;
		if (customerAddress == null) {
			if (other.customerAddress != null)
				return false;
		} else if (!customerAddress.equals(other.customerAddress))
			return false;
		if (customerCode == null) {
			if (other.customerCode != null)
				return false;
		} else if (!customerCode.equals(other.customerCode))
			return false;
		if (customerDetails == null) {
			if (other.customerDetails != null)
				return false;
		} else if (!customerDetails.equals(other.customerDetails))
			return false;
		if (customerID == null) {
			if (other.customerID != null)
				return false;
		} else if (!customerID.equals(other.customerID))
			return false;
		if (customerName == null) {
			if (other.customerName != null)
				return false;
		} else if (!customerName.equals(other.customerName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer [customerAddress=" + customerAddress + ", customerCode=" + customerCode + ", customerDetails="
				+ customerDetails + ", customerID=" + customerID + ", customerName=" + customerName + "]";
	}

}

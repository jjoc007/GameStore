package com.stefanini.tigo.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.stefanini.tigo.test.exceptions.CustomerPurchaseNotFoundException;
import com.stefanini.tigo.test.exceptions.DatabaseException;
import com.stefanini.tigo.test.jdbc.ConnectionFactory;
import com.stefanini.tigo.test.model.Customer;
import com.stefanini.tigo.test.model.CustomerPurchase;

/**
 *
 * @author Juan José Orjuela - j.j.o.c007@gmail.com
 * 
 *         Clase DAO para la tabla customer purchase
 * 
 */
public class CustomerPurchaseDAO implements CustomerPurchaseDAOI {

	private static final String SELECT_BY_ID_STATEMENT = "SELECT purchase_id, date_of_purchase, other_purchase_details, customer_id FROM customer_purchase WHERE purchase_id=? ";
	private static final String SELECT_ALL_STATEMENT = "SELECT purchase_id, date_of_purchase, other_purchase_details, customer_id FROM customer_purchase";
	private static final String INSERT_STATEMENT = "INSERT INTO public.customer_purchase( purchase_id, date_of_purchase, other_purchase_details, customer_id) VALUES (?, ?, ?, ?)";
	private static final String UPDATE_STATEMENT = "UPDATE customer_purchase SET date_of_purchase=?, other_purchase_details=?,  customer_id=? WHERE purchase_id=?";
	private static final String DELETE_STATEMENT = "DELETE FROM customer_purchase WHERE purchase_id=?";

	private CustomerDAOI customerDAO;

	public CustomerPurchaseDAO() {
		super();
		customerDAO = new CustomerDAO();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.stefanini.tigo.test.dao.CustomerPurchaseDAOI#getCustomersPruchase()
	 */
	@Override
	public List<CustomerPurchase> getCustomersPruchase() throws DatabaseException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			dbConnection = ConnectionFactory.getConnection();
			preparedStatement = dbConnection.prepareStatement(SELECT_ALL_STATEMENT);
			rs = preparedStatement.executeQuery();

			List<CustomerPurchase> purchases = new ArrayList<>();

			while (rs.next()) {

				Date dateOfPurchase = rs.getDate("date_of_purchase");
				String purchaseDetails = rs.getString("other_purchase_details");
				String purchaseID = rs.getString("purchase_id");

				Customer customer = customerDAO.getCustomerById(rs.getString("customer_id"));

				purchases.add(new CustomerPurchase(dateOfPurchase, purchaseDetails, purchaseID, customer));

			}

			return purchases;

		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage(),e);
		} finally {
			ConnectionFactory.closeConnection(dbConnection, preparedStatement, rs);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.stefanini.tigo.test.dao.CustomerPurchaseDAOI#getCustomerPurchaseById(java
	 * .lang.String)
	 */
	@Override
	public CustomerPurchase getCustomerPurchaseById(String customerPurchaseID) throws DatabaseException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			dbConnection = ConnectionFactory.getConnection();
			preparedStatement = dbConnection.prepareStatement(SELECT_BY_ID_STATEMENT);
			preparedStatement.setString(1, customerPurchaseID);

			rs = preparedStatement.executeQuery();

			if (rs.next()) {

				Date dateOfPurchase = rs.getDate("date_of_purchase");
				String purchaseDetails = rs.getString("other_purchase_details");
				String purchaseID = rs.getString("purchase_id");

				Customer customer = customerDAO.getCustomerById(rs.getString("customer_id"));

				return new CustomerPurchase(dateOfPurchase, purchaseDetails, purchaseID, customer);

			}

			throw new CustomerPurchaseNotFoundException(
					"El Customer Purchase con id: " + customerPurchaseID + " no se encuentra");

		} catch (SQLException | CustomerPurchaseNotFoundException e) {
			throw new DatabaseException(e.getMessage(),e);
		} finally {
			ConnectionFactory.closeConnection(dbConnection, preparedStatement, rs);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.stefanini.tigo.test.dao.CustomerPurchaseDAOI#updateCustomerPurchase(com.
	 * stefanini.tigo.test.model.CustomerPurchase)
	 */
	@Override
	public void updateCustomerPurchase(CustomerPurchase customerPurchase) throws DatabaseException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		try {
			dbConnection = ConnectionFactory.getConnection();
			preparedStatement = dbConnection.prepareStatement(UPDATE_STATEMENT);

			preparedStatement.setDate(1, new java.sql.Date(customerPurchase.getDateOfPurchase().getTime()));
			preparedStatement.setString(2, customerPurchase.getPurchaseDetails());
			preparedStatement.setString(3, customerPurchase.getCustomer().getCustomerID());
			preparedStatement.setString(4, customerPurchase.getPurchaseID());

			preparedStatement.execute();

		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage(),e);
		} finally {
			ConnectionFactory.closeConnection(dbConnection, preparedStatement, null);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.stefanini.tigo.test.dao.CustomerPurchaseDAOI#saveCustomerPurchase(com.
	 * stefanini.tigo.test.model.CustomerPurchase)
	 */
	@Override
	public void saveCustomerPurchase(CustomerPurchase customerPurchase) throws DatabaseException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		try {
			dbConnection = ConnectionFactory.getConnection();
			preparedStatement = dbConnection.prepareStatement(INSERT_STATEMENT);

			preparedStatement.setString(1, customerPurchase.getPurchaseID());
			preparedStatement.setDate(2, new java.sql.Date(customerPurchase.getDateOfPurchase().getTime()));
			preparedStatement.setString(3, customerPurchase.getPurchaseDetails());
			preparedStatement.setString(4, customerPurchase.getCustomer().getCustomerID());

			preparedStatement.execute();

		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage(),e);
		} finally {
			ConnectionFactory.closeConnection(dbConnection, preparedStatement, null);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.stefanini.tigo.test.dao.CustomerPurchaseDAOI#deleteCustomerPurchase(java.
	 * lang.String)
	 */
	@Override
	public void deleteCustomerPurchase(String customerPurchaseID) throws DatabaseException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		try {
			dbConnection = ConnectionFactory.getConnection();
			preparedStatement = dbConnection.prepareStatement(DELETE_STATEMENT);

			preparedStatement.setString(1, customerPurchaseID);
			preparedStatement.execute();

		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage(),e);
		} finally {
			ConnectionFactory.closeConnection(dbConnection, preparedStatement, null);
		}
	}

}

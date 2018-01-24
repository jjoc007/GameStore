package com.stefanini.tigo.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.stefanini.tigo.test.exceptions.CustomerNotFoundException;
import com.stefanini.tigo.test.exceptions.DatabaseException;
import com.stefanini.tigo.test.jdbc.ConnectionFactory;
import com.stefanini.tigo.test.model.Customer;

/**
 *
 * @author Juan José Orjuela - j.j.o.c007@gmail.com
 * 
 *         Clase DAO para la tabla customer
 * 
 */
public class CustomerDAO implements CustomerDAOI {

	/**
	 * Sentencias
	 */
	private static final String SELECT_BY_ID_STATEMENT = "SELECT customer_id, customer_code, customer_name, customer_address, customer_other_details FROM customer WHERE customer_id=? ";
	private static final String SELECT_ALL_STATEMENT = "SELECT customer_id, customer_code, customer_name, customer_address, customer_other_details FROM customer";
	private static final String INSERT_STATEMENT = "INSERT INTO customer(customer_id, customer_code, customer_name, customer_address, customer_other_details) VALUES (?, ?, ?, ?, ?)";
	private static final String UPDATE_STATEMENT = "UPDATE customer SET customer_code=?, customer_name=?, customer_address=?, customer_other_details=? WHERE customer_id=?";
	private static final String DELETE_STATEMENT = "DELETE FROM customer WHERE customer_id=? ";

	public CustomerDAO() {
		super();
	}

	@Override
	public List<Customer> getCustomers() throws DatabaseException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			dbConnection = ConnectionFactory.getConnection();
			preparedStatement = dbConnection.prepareStatement(SELECT_ALL_STATEMENT);
			rs = preparedStatement.executeQuery();

			List<Customer> customers = new ArrayList<>();

			while (rs.next()) {

				String customerAddress = rs.getString("customer_address");
				String customerCode = rs.getString("customer_code");
				String customerDetails = rs.getString("customer_other_details");
				String customerID = rs.getString("customer_id");
				String customerName = rs.getString("customer_name");

				customers.add(new Customer(customerAddress, customerCode, customerDetails, customerID, customerName));

			}

			return customers;

		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage(),e);
		} finally {
			ConnectionFactory.closeConnection(dbConnection, preparedStatement, rs);
		}
	}

	@Override
	public Customer getCustomerById(String customerID) throws DatabaseException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			dbConnection = ConnectionFactory.getConnection();
			preparedStatement = dbConnection.prepareStatement(SELECT_BY_ID_STATEMENT);
			preparedStatement.setString(1, customerID);

			rs = preparedStatement.executeQuery();

			if (rs.next()) {

				String customerAddress = rs.getString("customer_address");
				String customerCode = rs.getString("customer_code");
				String customerDetails = rs.getString("customer_other_details");
				String customerName = rs.getString("customer_name");

				return new Customer(customerAddress, customerCode, customerDetails, customerID, customerName);

			}

			throw new CustomerNotFoundException("El Customer con id: " + customerID + " no se encuentra");

		} catch (SQLException | CustomerNotFoundException e) {
			throw new DatabaseException(e.getMessage(),e);
		} finally {
			ConnectionFactory.closeConnection(dbConnection, preparedStatement, rs);
		}
	}

	@Override
	public void updateCustomer(Customer customer) throws DatabaseException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		try {
			dbConnection = ConnectionFactory.getConnection();
			preparedStatement = dbConnection.prepareStatement(UPDATE_STATEMENT);

			preparedStatement.setString(1, customer.getCustomerCode());
			preparedStatement.setString(2, customer.getCustomerName());
			preparedStatement.setString(3, customer.getCustomerAddress());
			preparedStatement.setString(4, customer.getCustomerDetails());
			preparedStatement.setString(5, customer.getCustomerID());

			preparedStatement.execute();

		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage(),e);
		} finally {
			ConnectionFactory.closeConnection(dbConnection, preparedStatement, null);
		}

	}

	@Override
	public void saveCustomer(Customer customer) throws DatabaseException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		try {
			dbConnection = ConnectionFactory.getConnection();
			preparedStatement = dbConnection.prepareStatement(INSERT_STATEMENT);

			preparedStatement.setString(1, customer.getCustomerID());
			preparedStatement.setString(2, customer.getCustomerCode());
			preparedStatement.setString(3, customer.getCustomerName());
			preparedStatement.setString(4, customer.getCustomerAddress());
			preparedStatement.setString(5, customer.getCustomerDetails());

			preparedStatement.execute();

		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage(),e);
		} finally {
			ConnectionFactory.closeConnection(dbConnection, preparedStatement, null);
		}

	}

	@Override
	public void deleteCustomer(String customerID) throws DatabaseException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		try {
			dbConnection = ConnectionFactory.getConnection();
			preparedStatement = dbConnection.prepareStatement(DELETE_STATEMENT);
			preparedStatement.setString(1, customerID);
			preparedStatement.execute();

		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage(),e);
		} finally {
			ConnectionFactory.closeConnection(dbConnection, preparedStatement, null);
		}
	}

}

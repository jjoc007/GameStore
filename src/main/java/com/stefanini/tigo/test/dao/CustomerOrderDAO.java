package com.stefanini.tigo.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.stefanini.tigo.test.exceptions.CustomerOrderNotFoundException;
import com.stefanini.tigo.test.exceptions.DatabaseException;
import com.stefanini.tigo.test.jdbc.ConnectionFactory;
import com.stefanini.tigo.test.model.Customer;
import com.stefanini.tigo.test.model.CustomerOrder;

/**
 *
 * @author Juan José Orjuela - j.j.o.c007@gmail.com
 * 
 *         Clase DAO para la tabla customer order
 * 
 */
public class CustomerOrderDAO implements CustomerOrderDAOI {

	private static final String SELECT_BY_ID_STATEMENT = "SELECT order_id, date_of_order, other_order_details, customer_id FROM public.customer_order WHERE order_id=?";
	private static final String SELECT_ALL_STATEMENT = "SELECT order_id, date_of_order, other_order_details, customer_id FROM public.customer_order";
	private static final String INSERT_STATEMENT = "INSERT INTO public.customer_order( order_id, date_of_order, other_order_details, customer_id) VALUES (?, ?, ?, ?)";
	private static final String UPDATE_STATEMENT = "UPDATE public.customer_order SET date_of_order=?, other_order_details=?, customer_id=? WHERE  order_id=?";
	private static final String DELETE_STATEMENT = "DELETE FROM public.customer_order WHERE order_id=?";

	private CustomerDAOI customerDAO;

	public CustomerOrderDAO() {
		super();
		customerDAO = new CustomerDAO();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.stefanini.tigo.test.dao.CustomerOrderDAOI#getCustomerOrders()
	 */
	@Override
	public List<CustomerOrder> getCustomerOrders() throws DatabaseException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			dbConnection = ConnectionFactory.getConnection();
			preparedStatement = dbConnection.prepareStatement(SELECT_ALL_STATEMENT);
			rs = preparedStatement.executeQuery();

			List<CustomerOrder> orders = new ArrayList<>();

			while (rs.next()) {

				Date dateOfOrder = rs.getDate("date_of_order");
				String orderDetails = rs.getString("other_order_details");
				String orderID = rs.getString("order_id");

				Customer customer = customerDAO.getCustomerById(rs.getString("customer_id"));

				orders.add(new CustomerOrder(dateOfOrder, orderDetails, orderID, customer));

			}

			return orders;

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
	 * com.stefanini.tigo.test.dao.CustomerOrderDAOI#getCustomerOrderById(java.lang.
	 * String)
	 */
	@Override
	public CustomerOrder getCustomerOrderById(String customerOrderID) throws DatabaseException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			dbConnection = ConnectionFactory.getConnection();
			preparedStatement = dbConnection.prepareStatement(SELECT_BY_ID_STATEMENT);
			preparedStatement.setString(1, customerOrderID);

			rs = preparedStatement.executeQuery();

			if (rs.next()) {

				Date dateOfOrder = rs.getDate("date_of_order");
				String orderDetails = rs.getString("other_order_details");
				String orderID = rs.getString("order_id");

				Customer customer = customerDAO.getCustomerById(rs.getString("customer_id"));

				return new CustomerOrder(dateOfOrder, orderDetails, orderID, customer);

			}

			throw new CustomerOrderNotFoundException(
					"El Customer Order con id: " + customerOrderID + " no se encuentra");

		} catch (SQLException | CustomerOrderNotFoundException e) {
			throw new DatabaseException(e.getMessage(),e);
		} finally {
			ConnectionFactory.closeConnection(dbConnection, preparedStatement, rs);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.stefanini.tigo.test.dao.CustomerOrderDAOI#updateCustomerOrder(com.
	 * stefanini.tigo.test.model.CustomerOrder)
	 */
	@Override
	public void updateCustomerOrder(CustomerOrder customerOrder) throws DatabaseException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		try {
			dbConnection = ConnectionFactory.getConnection();
			preparedStatement = dbConnection.prepareStatement(UPDATE_STATEMENT);

			preparedStatement.setDate(1, new java.sql.Date(customerOrder.getDateOfOrder().getTime()));
			preparedStatement.setString(2, customerOrder.getOrderDetails());
			preparedStatement.setString(3, customerOrder.getCustomer().getCustomerID());
			preparedStatement.setString(4, customerOrder.getOrderID());

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
	 * com.stefanini.tigo.test.dao.CustomerOrderDAOI#saveCustomerOrder(com.stefanini
	 * .tigo.test.model.CustomerOrder)
	 */
	@Override
	public void saveCustomerOrder(CustomerOrder customerOrder) throws DatabaseException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		try {
			dbConnection = ConnectionFactory.getConnection();
			preparedStatement = dbConnection.prepareStatement(INSERT_STATEMENT);

			preparedStatement.setString(1, customerOrder.getOrderID());
			preparedStatement.setDate(2, new java.sql.Date(customerOrder.getDateOfOrder().getTime()));
			preparedStatement.setString(3, customerOrder.getOrderDetails());
			preparedStatement.setString(4, customerOrder.getCustomer().getCustomerID());

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
	 * com.stefanini.tigo.test.dao.CustomerOrderDAOI#deleteCustomerOrder(java.lang.
	 * String)
	 */
	@Override
	public void deleteCustomerOrder(String customerOrderID) throws DatabaseException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		try {
			dbConnection = ConnectionFactory.getConnection();
			preparedStatement = dbConnection.prepareStatement(DELETE_STATEMENT);

			preparedStatement.setString(1, customerOrderID);
			preparedStatement.execute();

		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage(),e);
		} finally {
			ConnectionFactory.closeConnection(dbConnection, preparedStatement, null);
		}

	}

}

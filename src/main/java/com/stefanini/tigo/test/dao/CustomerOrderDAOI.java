package com.stefanini.tigo.test.dao;

import java.util.List;

import com.stefanini.tigo.test.exceptions.DatabaseException;
import com.stefanini.tigo.test.model.CustomerOrder;

/**
 *
 * @author Juan José Orjuela - j.j.o.c007@gmail.com
 * 
 *         Interface DAO para la tabla customer
 * 
 */
public interface CustomerOrderDAOI {

	/**
	 * 
	 * obtiene todos los registros de Customer order
	 * 
	 * @return
	 * @throws DatabaseException
	 */
	public List<CustomerOrder> getCustomerOrders() throws DatabaseException;

	/**
	 * 
	 * obtiene registro de Customer order por ID
	 * 
	 * @return
	 * @throws DatabaseException
	 */
	public CustomerOrder getCustomerOrderById(String customerOrderID) throws DatabaseException;

	/**
	 * Actualiza registro de customer order
	 * 
	 * @param customerOrder
	 * @throws DatabaseException
	 */
	public void updateCustomerOrder(CustomerOrder customerOrder) throws DatabaseException;

	/**
	 * Inserta registro de customer order
	 * 
	 * @param customerOrder
	 * @throws DatabaseException
	 */
	public void saveCustomerOrder(CustomerOrder customerOrder) throws DatabaseException;

	/**
	 * Elimina registro de customer order
	 * 
	 * @param customerOrder
	 * @throws DatabaseException
	 */
	public void deleteCustomerOrder(String customerOrderID) throws DatabaseException;

}

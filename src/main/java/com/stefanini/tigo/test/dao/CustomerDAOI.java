package com.stefanini.tigo.test.dao;

import java.util.List;

import com.stefanini.tigo.test.exceptions.DatabaseException;
import com.stefanini.tigo.test.model.Customer;

/**
 *
 * @author Juan José Orjuela - j.j.o.c007@gmail.com
 * 
 *         Interface DAO para la tabla customer
 * 
 */
public interface CustomerDAOI {

	/**
	 * 
	 * obtiene todos los registros de Customer
	 * 
	 * @return
	 * @throws DatabaseException
	 */
	public List<Customer> getCustomers() throws DatabaseException;

	/**
	 * 
	 * obtiene registro de Customer por ID
	 * 
	 * @return
	 * @throws DatabaseException
	 */
	public Customer getCustomerById(String customerID) throws DatabaseException;

	/**
	 * Actualiza registro de customer
	 * 
	 * @param customer
	 * @throws DatabaseException
	 */
	public void updateCustomer(Customer customer) throws DatabaseException;

	/**
	 * Inserta registro de customer
	 * 
	 * @param customer
	 * @throws DatabaseException
	 */
	public void saveCustomer(Customer customer) throws DatabaseException;

	/**
	 * Elimina registro de customer
	 * 
	 * @param customer
	 * @throws DatabaseException
	 */
	public void deleteCustomer(String customerID) throws DatabaseException;

}

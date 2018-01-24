package com.stefanini.tigo.test.dao;

import java.util.List;

import com.stefanini.tigo.test.exceptions.DatabaseException;
import com.stefanini.tigo.test.model.CustomerPurchase;

/**
 *
 * @author Juan José Orjuela - j.j.o.c007@gmail.com
 * 
 *         interface DAO para la tabla customer purchase
 * 
 */
public interface CustomerPurchaseDAOI {

	/**
	 * 
	 * obtiene todos los registros de Customer purchase
	 * 
	 * @return
	 * @throws DatabaseException
	 */
	public List<CustomerPurchase> getCustomersPruchase() throws DatabaseException;

	/**
	 * 
	 * obtiene un customer purchase por ID
	 * 
	 * @return
	 * @throws DatabaseException
	 */
	public CustomerPurchase getCustomerPurchaseById(String customerPurchaseID) throws DatabaseException;

	/**
	 * Actualiza registro de customer purchase
	 * 
	 * @param customerPurchase
	 * @throws DatabaseException
	 */
	public void updateCustomerPurchase(CustomerPurchase customerPurchase) throws DatabaseException;

	/**
	 * 
	 * Inserta registro de customer purchase
	 * 
	 * @param customerPurchase
	 * @throws DatabaseException
	 */
	public void saveCustomerPurchase(CustomerPurchase customerPurchase) throws DatabaseException;

	/**
	 * 
	 * elimina registro de costumer purchase
	 * 
	 * @param customerPurchaseID
	 * @throws DatabaseException
	 */
	public void deleteCustomerPurchase(String customerPurchaseID) throws DatabaseException;

}

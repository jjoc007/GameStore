package com.stefanini.tigo.test.main;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.stefanini.tigo.test.dao.CustomerDAO;
import com.stefanini.tigo.test.dao.CustomerDAOI;
import com.stefanini.tigo.test.dao.CustomerOrderDAO;
import com.stefanini.tigo.test.dao.CustomerOrderDAOI;
import com.stefanini.tigo.test.dao.CustomerPurchaseDAO;
import com.stefanini.tigo.test.dao.CustomerPurchaseDAOI;
import com.stefanini.tigo.test.exceptions.ApplicationException;
import com.stefanini.tigo.test.exceptions.DatabaseException;
import com.stefanini.tigo.test.model.Customer;
import com.stefanini.tigo.test.model.CustomerOrder;
import com.stefanini.tigo.test.model.CustomerPurchase;

/**
 *
 * @author Juan José Orjuela - j.j.o.c007@gmail.com
 * 
 *         Clase Flujo principal
 * 
 */
public class Main {
	
	private static final Logger LOGGER = Logger.getLogger( Main.class.getName() );
	
	private Main() {
		super();
	}
	
	public static void main(String[] args) throws ApplicationException {

		try {

			CustomerDAOI customerDAO = new CustomerDAO();
			CustomerOrderDAOI customerOrderDAO = new CustomerOrderDAO();
			CustomerPurchaseDAOI customerPurchaseDAO = new CustomerPurchaseDAO();

			// guardar un customer
			Customer customer = new Customer("cra 12 est no 32 a 77 sur", "0000001", "detalle 1", "1",
					"Juan José Orjuela Castillo");

			customerDAO.saveCustomer(customer);
			LOGGER.info("SE GUARDO REGISTRO CUSTOMER....");

			// guardar un customer order
			CustomerOrder customerOrder = new CustomerOrder(new Date(), "Detalle de la orden", "1", customer);
			customerOrderDAO.saveCustomerOrder(customerOrder);
			LOGGER.info("SE GUARDO REGISTRO ORDER CUSTOMER....");

			// guardar un customer purchase
			CustomerPurchase customerPurchase = new CustomerPurchase(new Date(), "detalle de customer purchase", "1",
					customer);
			customerPurchaseDAO.saveCustomerPurchase(customerPurchase);
			LOGGER.info("SE GUARDO REGISTRO PURCHASE CUSTOMER....");

			// mostrar customer por ID
			Customer customer2 = customerDAO.getCustomerById("1");
			LOGGER.info("MOSTRAR CUSTOMER POR ID....");
			LOGGER.info(customer2.toString());

			// mostrar todos los customers
			List<Customer> customers = customerDAO.getCustomers();
			LOGGER.info("MOSTRAR TODOS CUSTOMERS ....");
			LOGGER.info(customers.toString());

			// mostrar customer order
			CustomerOrder order2 = customerOrderDAO.getCustomerOrderById("1");
			LOGGER.info("MOSTRAR CUSTOMER ORDER POR ID....");
			LOGGER.info(order2.toString());

			// mostrar customer purchase
			CustomerPurchase customerPurchase2 = customerPurchaseDAO.getCustomerPurchaseById("1");
			LOGGER.info("MOSTRAR CUSTOMER PURCHASE POR ID....");
			LOGGER.info(customerPurchase2.toString());

			// actualizar customer
			customer.setCustomerAddress("direccion modificada");
			customer.setCustomerCode("00000002");
			customerDAO.updateCustomer(customer);

			Customer customer3 = customerDAO.getCustomerById("1");
			LOGGER.info("MOSTRAR CUSTOMER ACTUALIZADO....");
			LOGGER.info(customer3.toString());

			// eliminar customer purchase
			customerPurchaseDAO.deleteCustomerPurchase(customerPurchase.getPurchaseID());
			LOGGER.info("SE ELIMINO CUSTOMER PURCHASE....");

			// eliminar customer order
			customerOrderDAO.deleteCustomerOrder(customerOrder.getOrderID());
			LOGGER.info("SE ELIMINO CUSTOMER ORDER....");

			// eliminar customer
			customerDAO.deleteCustomer(customer.getCustomerID());
			LOGGER.info("SE ELIMINO CUSTOMER....");

		} catch (DatabaseException e) {
			throw new ApplicationException("Error",e);
		}

	}

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stefanini.tigo.test.exceptions;

/**
 *
 * @author Juan José Orjuela - j.j.o.c007@gmail.com
 * 
 *         Excepcion que se dispara cuando cuando se genera un error en la
 *         gestion de base de datos
 * 
 */
public class ApplicationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ApplicationException(String message) {
		super(message);
	}

	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

}

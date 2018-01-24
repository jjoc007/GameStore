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
 *         Excepcion que se dispara cuando no se encuentra el identificador de
 *         una base de datos
 * 
 */
public class NotFoundIdDatabaseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotFoundIdDatabaseException(String message) {
		super(message);
	}

	public NotFoundIdDatabaseException(String message, Throwable cause) {
		super(message, cause);
	}

}

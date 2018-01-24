package com.stefanini.tigo.test.jdbc;

import java.text.MessageFormat;

import com.stefanini.tigo.test.exceptions.NotFoundIdDatabaseException;
import com.stefanini.tigo.test.util.Constantes;

/**
 *
 * @author Juan José Orjuela - j.j.o.c007@gmail.com
 * 
 *         Enum que se encarga de generar la url de conexion de base de datos
 *         segun el motor
 * 
 */
public enum EnumDataBaseUrlConnection {

	POSTGRESQL(Constantes.DATA_BASE_POSTGRESQL_ID, "jdbc:postgresql://{0}:{1}/{2}");

	private String idDatabase;
	private String pattern;

	private EnumDataBaseUrlConnection(String idDatabase, String patternUrlConection) {
		this.idDatabase = idDatabase;
		this.pattern = patternUrlConection;
	}

	public String getUrlConection(Object[] params) {

		return MessageFormat.format(pattern, params);

	}

	public static String getUrlConnection(String id, String host, String port, String dbName)
			throws NotFoundIdDatabaseException {

		for (EnumDataBaseUrlConnection d : EnumDataBaseUrlConnection.values()) {
			if (d.idDatabase.equals(id)) {
				Object[] params = new Object[] { host, port, dbName };
				return d.getUrlConection(params);
			}
		}

		throw new NotFoundIdDatabaseException(
				"No se encuentra el tipo de conexion de base de datos que intenta realizar");

	}

}

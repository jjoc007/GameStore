package com.stefanini.tigo.test.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juan José Orjuela - j.j.o.c007@gmail.com
 * 
 *         clase que carga los datos de conexion de base de datos
 * 
 */
public class Parametros {

	private static Properties propiedades;

	private Parametros() {
		super();
	}

	private static void configurar() {

		ClassLoader classLoader = Parametros.class.getClassLoader();

		try (InputStream input = new FileInputStream(classLoader.getResource("config.properties").getFile())) {
			propiedades = new Properties();
			propiedades.load(input);

		} catch (IOException ex) {
			Logger.getLogger(Parametros.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static String getProperty(String key) {

		if (propiedades == null) {
			configurar();
		}

		return propiedades.getProperty(key);

	}

}

package com.stefanini.tigo.test.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

import com.stefanini.tigo.test.exceptions.DatabaseException;
import com.stefanini.tigo.test.exceptions.NotFoundIdDatabaseException;
import com.stefanini.tigo.test.util.Constantes;
import com.stefanini.tigo.test.util.Parametros;

/**
 *
 * @author Juan José Orjuela - j.j.o.c007@gmail.com
 * 
 *         Clase que se encarga de proveer conexiones a la base de datos
 * 
 */
public class ConnectionFactory {

	private static BasicDataSource basicDataSource;

	private ConnectionFactory() {
		super();
	}

	private static void configurarConexion() throws ClassNotFoundException, SQLException, NotFoundIdDatabaseException {

		String idTypeDataBase = Parametros.getProperty(Constantes.KEY_PROPIEDAD_DB_ID_JDBC);
		String driver = Parametros.getProperty(Constantes.KEY_PROPIEDAD_DRIVER_JDBC);
		String host = Parametros.getProperty(Constantes.KEY_PROPIEDAD_HOST_JDBC);
		String port = Parametros.getProperty(Constantes.KEY_PROPIEDAD_PORT_JDBC);
		String dbname = Parametros.getProperty(Constantes.KEY_PROPIEDAD_DB_JDBC);
		String user = Parametros.getProperty(Constantes.KEY_PROPIEDAD_USUARIO_JDBC);
		String password = Parametros.getProperty(Constantes.KEY_PROPIEDAD_CLAVE_JDBC);

		basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(driver);
		basicDataSource.setUsername(user);
		basicDataSource.setPassword(password);
		basicDataSource.setUrl(EnumDataBaseUrlConnection.getUrlConnection(idTypeDataBase, host, port, dbname));

	}

	public static Connection getConnection() throws DatabaseException {

		try {
			if (basicDataSource == null) {
				configurarConexion();
			}

			return basicDataSource.getConnection();

		} catch (SQLException | NotFoundIdDatabaseException | ClassNotFoundException e) {
			throw new DatabaseException(e.getMessage(),e);
		}
	}

	public static void closeConnection(Connection con, PreparedStatement st, ResultSet rs) throws DatabaseException {
		try {
			if (con != null)

				con.close();

			if (st != null)
				st.close();
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			throw new DatabaseException("Error Cerrando conexion: " + e.getMessage(),e);
		}
	}

}

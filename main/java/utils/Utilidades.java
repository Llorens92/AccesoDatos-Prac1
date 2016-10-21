
package utils;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;

/**
 *  @description Clase que establece una conexi�n a BD utilizando la clase DriverManager. lee los datos de u archivo de propiedades
 */
public class Utilidades {

	public String dbms;
	public String dbName;
	public String userName;
	public String password;
	public String urlString;

	private String driver;
	private String serverName;
	private int portNumber;
	private Properties prop;
	
	public Utilidades(String PROPERTIES_FILE)
			throws FileNotFoundException, IOException,
			InvalidPropertiesFormatException {
		super();
		this.setProperties(PROPERTIES_FILE);
	}

	/**
	 * Asignaci�n de propiedades de conexi�n de xml a atributos de clase
	 * @param fileName
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws InvalidPropertiesFormatException
	 */
	private void setProperties(String fileName) throws IOException, InvalidPropertiesFormatException {
		this.prop = new Properties();
	    prop.loadFromXML(Files.newInputStream(Paths.get(fileName)));

		this.dbms = this.prop.getProperty("dbms");
		this.driver = this.prop.getProperty("driver");
		this.dbName = this.prop.getProperty("database_name");
		this.userName = this.prop.getProperty("user_name");
		this.password = this.prop.getProperty("password");
		this.serverName = this.prop.getProperty("server_name");
		this.portNumber = Integer
				.parseInt(this.prop.getProperty("port_number"));

		System.out.println("Set the following properties:");
		System.out.println("dbms: " + dbms);
		System.out.println("driver: " + driver);
		System.out.println("dbName: " + dbName);
		System.out.println("userName: " + userName);
		System.out.println("serverName: " + serverName);
		System.out.println("portNumber: " + portNumber);

	}

	public BasicDataSource getPool() throws SQLException {
		BasicDataSource basicDataSource = new BasicDataSource();
		
		basicDataSource.setDriverClassName(driver);
		basicDataSource.setUsername(this.userName);
		basicDataSource.setPassword(this.password);
		basicDataSource.setUrl("jdbc:" + this.dbms + "://"
			+ this.serverName + ":" + this.portNumber + "/" + this.dbName);
		
		// Opcional, para fijar el tamaño. Por defecto es 10
		basicDataSource.setInitialSize(4);

		// Opcional. Sentencia SQL que le puede servir a BasicDataSource
		// para comprobar que la conexion es correcta.
		basicDataSource.setValidationQuery("select 1");
		
		System.out.println("Pool creado");
		return basicDataSource;
	}

	public static void closePool(BasicDataSource pool) {
		System.out.println("Releasing all open resources ...");
		try {
			if (pool != null) {
				pool.close();
				pool = null;
			}
		} catch (SQLException sqle) {
			System.err.println(sqle);
		}
	}
	
	/**
	 * Metodo para imprimir la informaci�n de una Excepci�n SQL y poder depurar errores f�cilmente
	 * @param ex
	 */
	public static void printSQLException(SQLException e) {
        
        while (e != null) {
			if (e instanceof SQLException) {
				//Estado ANSI
				e.printStackTrace(System.err);
				System.err.println("SQLState: "
						+ ((SQLException) e).getSQLState());
				//C�digo de error propio de cada gestor de BD
				System.err.println("Error Code: "
						+ ((SQLException) e).getErrorCode());
				//Mensaje textual
				System.err.println("Message: " + e.getMessage());

				//Objetos desencadenantes de la excepci�n
				Throwable t = e.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
				//Cualquier otra excepci�n encadenada
				e = e.getNextException();				
				
			}
		}
	}
}

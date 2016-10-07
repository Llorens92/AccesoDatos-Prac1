package ClasesPrincipales;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import main.Utilidades1;

public class Cafes {

	// Consultas a realizar en BD
	private static final String SELECT_CAFES_QUERY = "select CAF_NOMBRE, PROV_ID, PRECIO, VENTAS, TOTAL from CAFES";
	// En una consulta parametrizada ponemos interrogaciones en los valores que
	// a�n desconocemos
	private static final String UPDATE_VENTAS_CAFE = "update CAFES set VENTAS = ? where CAF_NOMBRE = ?";

	private Connection cn;
	private ResultSet rs;
	private Statement st;
	private PreparedStatement pst;

	public Cafes() {
		try {
			cn = new Utilidades1().getConnection();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} catch (SQLException e) {
			Utilidades1.printSQLException(e);
		} finally {
			rs = null;
			st = null;
			pst = null;

		}
	}

	public void cerrar() {
		if (cn != null) {
			Utilidades1.closeConnection(cn);
		}
	}

	private void liberar() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (pst != null) {
				pst.close();
			}
		} catch (SQLException sqle) {
			Utilidades1.printSQLException(sqle);
		}
	}

	/**
	 * Metodo que muestra por pantalla los datos de la tabla cafes
	 * 
	 * @param con
	 * @throws SQLException
	 */
	public void verTabla() {
		try {
			// Creaci�n de la sentencia
			pst = cn.prepareStatement(SELECT_CAFES_QUERY);
			rs = pst.executeQuery();
			// Ejecuci�n de la consulta y obtenci�n de resultados en un
			// ResultSet
			// Recuperaci�n de los datos del ResultSet
			while (rs.next()) {
				String coffeeName = rs.getString("CAF_NOMBRE");
				int supplierID = rs.getInt("PROV_ID");
				float PRECIO = rs.getFloat("PRECIO");
				int VENTAS = rs.getInt("VENTAS");
				int total = rs.getInt("TOTAL");
				System.out.println(coffeeName + ", " + supplierID + ", " + PRECIO + ", " + VENTAS + ", " + total);
			}
			liberar();
		} catch (SQLException sqle) {
			Utilidades1.printSQLException(sqle);
			// En una aplicaci�n real, escribo en el log, no delego porque
			// es error al liberar recursos
		}
	}

	/**
	 * M�todo que actualiza las ventas de un caf� con un PreparedStatement
	 * 
	 * @param cafe
	 * @param ventas
	 */
	public void actualizarVentasCafe(String cafe, int ventas) {
		try {
			pst = cn.prepareStatement(UPDATE_VENTAS_CAFE);
			pst.executeUpdate();
			liberar();
		} catch (SQLException sqle) {
			Utilidades1.printSQLException(sqle);
		}
	}

	public void BuscarCafe(String cafe) {
		try {
			pst = cn.prepareStatement("Select * from cafes where caf_nombre=\"" + cafe + "\"");
			rs = pst.executeQuery();

			while (rs.next()) {
				int supplierID = rs.getInt("PROV_ID");
				float PRECIO = rs.getFloat("PRECIO");
				int VENTAS = rs.getInt("VENTAS");
				int total = rs.getInt("TOTAL");
				System.out.println("Los datos del café " + cafe + " son:");
				System.out
						.println("ID " + supplierID + ", Precio " + PRECIO + ", Ventas " + VENTAS + ", Total " + total);
			}
			liberar();
		} catch (SQLException sqle) {
			Utilidades1.printSQLException(sqle);
		}
	}

	public void BorrarCafe(String cafe) {
		try {
			pst = cn.prepareStatement("DELETE FROM cafes WHERE CAF_NOMBRE=\"" + cafe + "\"");
			pst.executeUpdate();
			liberar();
		} catch (SQLException sqle) {
			Utilidades1.printSQLException(sqle);
		}
	}

	public void InsertarCafe(String nom, int ID, float precio, int ventas, int total) {
		try {
			pst = cn.prepareStatement("INSERT INTO cafes VALUES (\"" + nom + "\"" + ",\"" + ID + "\"" + ",\"" + precio
					+ "\"" + ",\"" + ventas + "\"" + ",\"" + total + "\")");
			pst.executeUpdate();
			liberar();
		} catch (SQLException sqle) {
			Utilidades1.printSQLException(sqle);
		}
	}

	public void cafesProveedor(int id_prov) {
		try {
			pst = cn.prepareStatement(
					"SELECT P.*, C.* FROM CAFES C JOIN PROVEEDORES P ON C.PROV_ID = P.PROV_ID AND C.prov_id=\""
							+ id_prov + "\"");
			rs = pst.executeQuery();
			rs.next();
			String nom = rs.getString("PROV_NOMBRE");
			String calle = rs.getString("CALLE");
			String ciudad = rs.getString("CIUDAD");
			String pais = rs.getString("PAIS");
			int cp = rs.getInt("CP");
			System.out.println("Los datos del proveedor con ID " + id_prov + " son:");
			System.out.println(
					"Nombre " + nom + ", calle " + calle + ", ciudad " + ciudad + ", pais " + pais + ", cp " + cp);
			rs.previous();
			System.out.println("Los cafés suministrados por este proveedor son:");
			while (rs.next()) {
				String nomCaf = rs.getString("CAF_NOMBRE");
				int supplierID = rs.getInt("PROV_ID");
				float PRECIO = rs.getFloat("PRECIO");
				int VENTAS = rs.getInt("VENTAS");
				int total = rs.getInt("TOTAL");
				System.out.println(nomCaf + ":");
				System.out
						.println("ID " + supplierID + ", Precio " + PRECIO + ", Ventas " + VENTAS + ", Total " + total);
			}

			liberar();
		} catch (SQLException sqle) {
			Utilidades1.printSQLException(sqle);
		}
	}
}
package ClasesPrincipales;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.sql.ResultSetMetaData;

import exceptions.AccesoDatosException;
import utils.Utilidades;

public class Libros {

	private static final String PROPERTIES_FILE = "src\\main\\resources\\mysql-properties2.xml";

	private static final String SELECT_LIBROS_QUERY = "select isbn, titulo, autor, editorial, paginas, copias from LIBROS ORDER BY TITULO ASC";

	private static final String SELECT_CAMPOS_QUERY = "SELECT * FROM LIBROS LIMIT 1";

	private Connection cn;
	private ResultSet rs;
	private Statement st;
	private PreparedStatement pst;

	public Libros() throws AccesoDatosException {
		try {
			cn = new Utilidades(PROPERTIES_FILE).getConnection();
		} catch (IOException e) {
			System.err.println(e.getMessage());
			throw new AccesoDatosException("Ocurrio un error al acceder a los datos");
		} catch (SQLException e) {
			Utilidades.printSQLException(e);
			throw new AccesoDatosException("Ocurrio un error al acceder a los datos");
		} finally {
			rs = null;
			st = null;
			pst = null;

		}
	}

	public String[] getCamposLibro() throws AccesoDatosException {

		/* Sentencia sql con parámetros de entrada */
		pst = null;
		/* Conjunto de Resultados a obtener de la sentencia sql */
		rs = null;
		ResultSetMetaData rsmd = null;

		String[] campos = null;

		try {

			// Solicitamos a la conexion un objeto stmt para nuestra consulta
			pst = cn.prepareStatement(SELECT_CAMPOS_QUERY);

			// Le solicitamos al objeto stmt que ejecute nuestra consulta
			// y nos devuelve los resultados en un objeto ResultSet
			rs = pst.executeQuery();
			rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			campos = new String[columns];
			for (int i = 0; i < columns; i++) {
				// Los indices de las columnas comienzan en 1
				campos[i] = rsmd.getColumnLabel(i + 1);
			}

		} catch (SQLException sqle) {
			// En una aplicación real, escribo en el log y delego
			Utilidades.printSQLException(sqle);
			throw new AccesoDatosException("Ocurrió un error al acceder a los datos");

		} finally {
			liberar();
		}

		return campos;
	}

	public void cerrar() {
		if (cn != null) {
			Utilidades.closeConnection(cn);
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
			Utilidades.printSQLException(sqle);
		}
	}

	public void añadirLibro(int isbn, String titulo, String autor, String editorial, int paginas, int copias)
			throws AccesoDatosException {
		try {
			pst = cn.prepareStatement("INSERT INTO libros VALUES (\"" + isbn + "\"" + ",\"" + titulo + "\"" + ",\""
					+ autor + "\"" + ",\"" + editorial + "\"" + ",\"" + paginas + "\",\"" + copias + "\")");
			pst.executeUpdate();
			liberar();
		} catch (SQLException sqle) {
			Utilidades.printSQLException(sqle);
			throw new AccesoDatosException("Ocurrio un error al acceder a los datos");
		}
	}

	public void BorrarLibro(String isbn) throws AccesoDatosException {
		try {
			pst = cn.prepareStatement("DELETE FROM Libros WHERE isbn=\"" + isbn + "\"");
			pst.executeUpdate();
			liberar();
		} catch (SQLException sqle) {
			Utilidades.printSQLException(sqle);
			throw new AccesoDatosException("Ocurrio un error al acceder a los datos");
		}
	}

	public void verCatalogo() throws AccesoDatosException {
		try {
			pst = cn.prepareStatement(SELECT_LIBROS_QUERY);
			rs = pst.executeQuery();
			while (rs.next()) {
				String ISBN = rs.getString("ISBN");
				String titulo = rs.getString("titulo");
				String autor = rs.getString("autor");
				String editorial = rs.getString("editorial");
				int paginas = rs.getInt("paginas");
				int copias = rs.getInt("copias");
				System.out.println(
						ISBN + ", " + titulo + ", " + autor + ", " + editorial + ", " + paginas + ", " + copias);
			}
			liberar();
		} catch (SQLException sqle) {
			Utilidades.printSQLException(sqle);
			throw new AccesoDatosException("Ocurrio un error al acceder a los datos");
		}
	}

	public void actualizarCopias(String isbn, int copias) throws AccesoDatosException {
		try {
			pst = cn.prepareStatement(
					"update Libros set Copias = \"" + copias + "\"" + " where ISBN = \"" + isbn + "\"");
			pst.executeUpdate();
			liberar();
		} catch (SQLException sqle) {
			Utilidades.printSQLException(sqle);
			throw new AccesoDatosException("Ocurrio un error al acceder a los datos");
		}
	}

	public void actualizarCopias(HashMap<String, Integer> HashCopias) throws AccesoDatosException {
		try {
			pst = cn.prepareStatement(SELECT_LIBROS_QUERY, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = pst.executeQuery();
			Iterator<String> isbns = HashCopias.keySet().iterator();
			while (isbns.hasNext()) {
				String isbnPasado = isbns.next();
				while (rs.next()) {
					String ISBN = rs.getString("ISBN");
					if (ISBN.equalsIgnoreCase(isbnPasado)) {
						int copias = rs.getInt("copias");
						pst = cn.prepareStatement("update Libros set Copias = \"" + (copias + HashCopias.get(ISBN))
								+ "\"" + " where ISBN = \"" + isbnPasado + "\"");
						pst.executeUpdate();
					}
				}
				rs.beforeFirst();
			}
			liberar();
		} catch (SQLException sqle) {
			Utilidades.printSQLException(sqle);
			throw new AccesoDatosException("Ocurrio un error al acceder a los datos");
		}
	}

	public void verCatalogoInverso() throws AccesoDatosException {
		try {
			pst = cn.prepareStatement(SELECT_LIBROS_QUERY, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = pst.executeQuery();
			rs.afterLast();
			while (rs.previous()) {
				String ISBN = rs.getString("ISBN");
				String titulo = rs.getString("titulo");
				String autor = rs.getString("autor");
				String editorial = rs.getString("editorial");
				int paginas = rs.getInt("paginas");
				int copias = rs.getInt("copias");
				System.out.println(
						ISBN + ", " + titulo + ", " + autor + ", " + editorial + ", " + paginas + ", " + copias);
			}
			liberar();
		} catch (SQLException sqle) {
			Utilidades.printSQLException(sqle);
			throw new AccesoDatosException("Ocurrio un error al acceder a los datos");
		}
	}

	public void mostrarFilas(ArrayList<Integer> listaFilas) throws AccesoDatosException {
		try {
			pst = cn.prepareStatement(SELECT_LIBROS_QUERY, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = pst.executeQuery();
			Iterator<Integer> filas = listaFilas.iterator();
			while (filas.hasNext()) {
				int filatoShow = filas.next();
				rs.absolute(filatoShow);
				String ISBN = rs.getString("ISBN");
				String titulo = rs.getString("titulo");
				String autor = rs.getString("autor");
				String editorial = rs.getString("editorial");
				int paginas = rs.getInt("paginas");
				int copias = rs.getInt("copias");
				System.out.println(
						ISBN + ", " + titulo + ", " + autor + ", " + editorial + ", " + paginas + ", " + copias);
			}
			liberar();
		} catch (SQLException sqle) {
			Utilidades.printSQLException(sqle);
			throw new AccesoDatosException("Ocurrio un error al acceder a los datos");
		}
	}
	
	public void actualizarPrecioPag(HashMap<String, Float> HashPrecio) throws AccesoDatosException {
		try {
			pst = cn.prepareStatement(SELECT_LIBROS_QUERY, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = pst.executeQuery();
			Iterator<String> isbns = HashPrecio.keySet().iterator();
			while (isbns.hasNext()) {
				String isbnPasado = isbns.next();
				while (rs.next()) {
					String ISBN = rs.getString("ISBN");
					if (ISBN.equalsIgnoreCase(isbnPasado)) {
						int pag = rs.getInt("paginas");
						pst = cn.prepareStatement("update Libros set precio = \"" + (pag*HashPrecio.get(ISBN))
								+ "\"" + " where ISBN = \"" + isbnPasado + "\"");
						pst.executeUpdate();
					}
				}
				rs.beforeFirst();
			}
			liberar();
		} catch (SQLException sqle) {
			Utilidades.printSQLException(sqle);
			throw new AccesoDatosException("Ocurrio un error al acceder a los datos");
		}
	}
}

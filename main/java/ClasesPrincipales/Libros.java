package ClasesPrincipales;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import exceptions.AccesoDatosException;
import utils.Utilidades;

public class Libros {

	private static final String SELECT_LIBROS_QUERY = "select isbn, titulo, autor, editorial, paginas, copias from LIBROS ORDER BY TITULO ASC";	

	private Connection cn;
	private ResultSet rs;
	private Statement st;
	private PreparedStatement pst;

	public Libros() throws AccesoDatosException {
		try {
			cn = new Utilidades().getConnection();
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
				System.out.println(ISBN + ", " + titulo + ", " + autor + ", " + editorial + ", " + paginas+", " + copias);
			}
			liberar();
		} catch (SQLException sqle) {
			Utilidades.printSQLException(sqle);
			throw new AccesoDatosException("Ocurrio un error al acceder a los datos");
		}
	}
	
	public void actualizarCopias(String isbn, int copias) throws AccesoDatosException {
		try {
			pst = cn.prepareStatement("update Libros set Copias = \"" + copias + "\""+" where ISBN = \"" + isbn + "\"");
			pst.executeUpdate();
			liberar();
		} catch (SQLException sqle) {
			Utilidades.printSQLException(sqle);
			throw new AccesoDatosException("Ocurrio un error al acceder a los datos");
		}
	}

}
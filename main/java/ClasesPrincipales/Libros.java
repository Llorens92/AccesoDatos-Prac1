package ClasesPrincipales;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import utils.Utilidades1;

public class Libros {
	
	private Connection cn;
	private ResultSet rs;
	private Statement st;
	private PreparedStatement pst;
	
	public Libros() {
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
	
	public void añadirLibro(String isbn, String titulo, String autor, String editorial, int paginas, int copias) {
		
		try {
			pst = cn.prepareStatement("INSERT INTO libros VALUES (\"" + isbn + "\"" + ",\"" + titulo + "\"" + ",\"" + autor
					+ "\"" + ",\"" + editorial + "\"" + ",\"" + paginas + "\",\"" + copias + "\")");
			pst.executeUpdate();
			liberar();
		} catch (SQLException sqle) {
			Utilidades1.printSQLException(sqle);
		}
	}
}

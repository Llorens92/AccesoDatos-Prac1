package modelo;

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

import org.apache.commons.dbcp2.BasicDataSource;

import DAO.Utilidades;
import logica.AccesoDatosException;

import java.sql.ResultSetMetaData;

public class Libros {

	private static final String PROPERTIES_FILE = "src\\main\\resources\\mysql-properties2.xml";

	private static final String SELECT_LIBROS_QUERY = "select isbn, titulo, autor, editorial, paginas, copias, precio from LIBROS ORDER BY TITULO ASC";

	private static final String SELECT_CAMPOS_QUERY = "SELECT * FROM LIBROS LIMIT 1";

	private BasicDataSource pool;
	private Connection cn;
	private ResultSet rs;
	private Statement st;
	private PreparedStatement pst;

	public Libros() throws AccesoDatosException {
		try {
			this.pool = new Utilidades(PROPERTIES_FILE).getPool();
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
			this.cn = pool.getConnection();
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
			if (cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					Utilidades.printSQLException(e);
					throw new AccesoDatosException("Ocurrio un error al acceder a los datos");
				}
			}
		}

		return campos;
	}

	public void añadirLibro(int isbn, String titulo, String autor, String editorial, int paginas, int copias, float precio)
			throws AccesoDatosException {
		try {
			this.cn = pool.getConnection();
			pst = cn.prepareStatement("INSERT INTO libros VALUES (\"" + isbn + "\"" + ",\"" + titulo + "\"" + ",\""
					+ autor + "\"" + ",\"" + editorial + "\"" + ",\"" + paginas + "\",\"" + copias + "\",\"" + precio +"\")");
			pst.executeUpdate();
		} catch (SQLException sqle) {
			Utilidades.printSQLException(sqle);
			throw new AccesoDatosException("Ocurrio un error al acceder a los datos");
		} finally {
			if (cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					Utilidades.printSQLException(e);
					throw new AccesoDatosException("Ocurrio un error al acceder a los datos");
				}
			}
		}
	}

	public void BorrarLibro(String isbn) throws AccesoDatosException {
		try {
			this.cn = pool.getConnection();
			pst = cn.prepareStatement("DELETE FROM Libros WHERE isbn=\"" + isbn + "\"");
			pst.executeUpdate();
		} catch (SQLException sqle) {
			Utilidades.printSQLException(sqle);
			throw new AccesoDatosException("Ocurrio un error al acceder a los datos");
		} finally {
			if (cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					Utilidades.printSQLException(e);
					throw new AccesoDatosException("Ocurrio un error al acceder a los datos");
				}
			}
		}
	}

	public void verCatalogo() throws AccesoDatosException {
		try {
			this.cn = pool.getConnection();
			pst = cn.prepareStatement(SELECT_LIBROS_QUERY);
			rs = pst.executeQuery();
			while (rs.next()) {
				String ISBN = rs.getString("ISBN");
				String titulo = rs.getString("titulo");
				String autor = rs.getString("autor");
				String editorial = rs.getString("editorial");
				int paginas = rs.getInt("paginas");
				int copias = rs.getInt("copias");
				float precio = rs.getFloat("precio");
				System.out.println(ISBN + ", " + titulo + ", " + autor + ", " + editorial + ", " + paginas + ", "
						+ copias + ", " + precio);
			}
		} catch (SQLException sqle) {
			Utilidades.printSQLException(sqle);
			throw new AccesoDatosException("Ocurrio un error al acceder a los datos");
		} finally {
			if (cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					Utilidades.printSQLException(e);
					throw new AccesoDatosException("Ocurrio un error al acceder a los datos");
				}
			}
		}
	}

	public void actualizarCopias(String isbn, int copias) throws AccesoDatosException {
		try {
			this.cn = pool.getConnection();
			pst = cn.prepareStatement(
					"update Libros set Copias = \"" + copias + "\"" + " where ISBN = \"" + isbn + "\"");
			pst.executeUpdate();
		} catch (SQLException sqle) {
			Utilidades.printSQLException(sqle);
			throw new AccesoDatosException("Ocurrio un error al acceder a los datos");
		} finally {
			if (cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					Utilidades.printSQLException(e);
					throw new AccesoDatosException("Ocurrio un error al acceder a los datos");
				}
			}
		}
	}

	public void actualizarCopias(HashMap<String, Integer> HashCopias) throws AccesoDatosException {
		try {
			this.cn = pool.getConnection();
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
		} catch (SQLException sqle) {
			Utilidades.printSQLException(sqle);
			throw new AccesoDatosException("Ocurrio un error al acceder a los datos");
		} finally {
			if (cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					Utilidades.printSQLException(e);
					throw new AccesoDatosException("Ocurrio un error al acceder a los datos");
				}
			}
		}
	}

	public void verCatalogoInverso() throws AccesoDatosException {
		try {
			this.cn = pool.getConnection();
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
		} catch (SQLException sqle) {
			Utilidades.printSQLException(sqle);
			throw new AccesoDatosException("Ocurrio un error al acceder a los datos");
		} finally {
			if (cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					Utilidades.printSQLException(e);
					throw new AccesoDatosException("Ocurrio un error al acceder a los datos");
				}
			}
		}
	}

	public void mostrarFilas(ArrayList<Integer> listaFilas) throws AccesoDatosException {
		try {
			this.cn = pool.getConnection();
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
		} catch (SQLException sqle) {
			Utilidades.printSQLException(sqle);
			throw new AccesoDatosException("Ocurrio un error al acceder a los datos");
		} finally {
			if (cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					Utilidades.printSQLException(e);
					throw new AccesoDatosException("Ocurrio un error al acceder a los datos");
				}
			}
		}
	}

	public void actualizarPrecioPag(HashMap<String, Float> HashPrecio) throws AccesoDatosException {
		try {
			this.cn = pool.getConnection();
			pst = cn.prepareStatement(SELECT_LIBROS_QUERY, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = pst.executeQuery();
			Iterator<String> isbns = HashPrecio.keySet().iterator();
			while (isbns.hasNext()) {
				String isbnPasado = isbns.next();
				while (rs.next()) {
					String ISBN = rs.getString("ISBN");
					if (ISBN.equalsIgnoreCase(isbnPasado)) {
						int pag = rs.getInt("paginas");
						pst = cn.prepareStatement("update Libros set precio = \"" + (pag * HashPrecio.get(ISBN)) + "\""
								+ " where ISBN = \"" + isbnPasado + "\"");
						pst.executeUpdate();
					}
				}
				rs.beforeFirst();
			}
		} catch (SQLException sqle) {
			Utilidades.printSQLException(sqle);
			throw new AccesoDatosException("Ocurrio un error al acceder a los datos");
		} finally {
			if (cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					Utilidades.printSQLException(e);
					throw new AccesoDatosException("Ocurrio un error al acceder a los datos");
				}
			}
		}
	}

	public void transaccionPrecio(String isbn1, String isbn2, float precioPorPag) throws AccesoDatosException {
		int pag1 = 0;
		int pag2 = 0;
		try {
			this.cn = pool.getConnection();
			pst = cn.prepareStatement(SELECT_LIBROS_QUERY, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = pst.executeQuery();
			while (rs.next()) {
				String ISBN = rs.getString("ISBN");
				if (ISBN.equalsIgnoreCase(isbn1)) {
					pag1 = rs.getInt("paginas");
				}
				if (ISBN.equalsIgnoreCase(isbn2)) {
					pag2 = rs.getInt("paginas");
				}
			}
			cn.setAutoCommit(false);
			pst = cn.prepareStatement(
					"update Libros set precio = \"" + (precioPorPag * pag1) + "\"" + " where ISBN = \"" + isbn1 + "\"");
			pst.executeUpdate();
			st = cn.createStatement();
			st.executeUpdate(
					"update Libros set precio = \"" + (precioPorPag * pag2) + "\"" + " where ISBN = \"" + isbn2 + "\"");
			cn.commit();
		} catch (SQLException sqle) {
			Utilidades.printSQLException(sqle);
			throw new AccesoDatosException("Ocurrio un error al acceder a los datos");
		} finally {
			try {
				cn.setAutoCommit(true);
				if (cn != null) {
					cn.close();
				}
			} catch (SQLException e) {
				Utilidades.printSQLException(e);
				throw new AccesoDatosException("Ocurrio un error");
			}
		}
	}

	public void añadirPaginas(String isbn, int numPag, float precioPorPag) throws AccesoDatosException {
		try {
			boolean salir = false;
			int pagViejas = 0;
			this.cn = pool.getConnection();
			pst = cn.prepareStatement(SELECT_LIBROS_QUERY, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = pst.executeQuery();
			cn.setAutoCommit(false);
			while (rs.next() && !salir) {
				String ISBN = rs.getString("ISBN");
				if (ISBN.equalsIgnoreCase(isbn)) {
					pagViejas = rs.getInt("paginas");
					rs.updateInt("paginas", numPag + pagViejas);
					rs.updateFloat("precio", precioPorPag * (numPag + pagViejas));
					rs.updateRow();
					salir = true;
				}
			}
			cn.commit();
		} catch (SQLException sqle) {
			Utilidades.printSQLException(sqle);
			throw new AccesoDatosException("Ocurrio un error al acceder a los datos");
		}finally {
			try {
				cn.setAutoCommit(true);
				if (cn != null) {
					cn.close();
				}
			} catch (SQLException e) {
				Utilidades.printSQLException(e);
				throw new AccesoDatosException("Ocurrio un error");
			}
		}
	}

	public void duplicarLibro(String isbn1, String isbn2) throws AccesoDatosException {
		try {
			boolean salir = false;
			this.cn = pool.getConnection();
			pst = cn.prepareStatement(SELECT_LIBROS_QUERY, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = pst.executeQuery();
			cn.setAutoCommit(false);
			String editorial = null;
			String titulo = null;
			String autor = null;
			int copias = 0;
			int paginas = 0;
			float precio = 0;
			while (rs.next() && !salir) {
				if (rs.getString("ISBN").equalsIgnoreCase(isbn1)) {
					titulo = rs.getString("titulo");
					autor = rs.getString("autor");
					editorial = rs.getString("editorial");
					paginas = rs.getInt("paginas");
					copias = rs.getInt("copias");
					precio = rs.getFloat("precio");
					salir = true;
				}

			}
			rs.moveToInsertRow();
			rs.updateString("ISBN", isbn2);
			rs.updateString("titulo", titulo);
			rs.updateString("autor", autor);
			rs.updateString("editorial", editorial);
			rs.updateInt("paginas", paginas);
			rs.updateInt("copias", copias);
			rs.updateFloat("precio", precio);
			rs.insertRow();
			cn.commit();
		} catch (SQLException sqle) {
			Utilidades.printSQLException(sqle);
			throw new AccesoDatosException("Ocurrio un error al acceder a los datos");
		}finally {
			try {
				cn.setAutoCommit(true);
				if (cn != null) {
					cn.close();
				}
			} catch (SQLException e) {
				Utilidades.printSQLException(e);
				throw new AccesoDatosException("Ocurrio un error");
			}
		}
	}

	public BasicDataSource getPool(){
		return this.pool;
	}
}
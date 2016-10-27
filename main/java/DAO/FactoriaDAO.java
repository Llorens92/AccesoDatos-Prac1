package DAO;

import logica.AccesoDatosException;

public class FactoriaDAO {
	private static FactoriaDAO instance;
	private static final String libroDAO = "JDBCLibroDAO";
	public static FactoriaDAO getInstance() {
		if (instance == null) {
			instance = new FactoriaDAO();
		}
		return instance;
	}

	private FactoriaDAO() {

	}

	/**
	 * Devuelve un objeto DAO adecuado dependiendo de como est� implementada la
	 * persistencia a datos
	 * 
	 * @return
	 * @throws AccesoDatosException
	 */
	public CafeDAO getCafeDAO() throws AccesoDatosException {
		CafeDAO dao = null;
		if (libroDAO.equals("JDBCLibroDAO")) {
			dao = new JDBCCafeDAO();
		}
		return dao;

	}

}
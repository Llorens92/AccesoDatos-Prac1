package DAO;


import org.apache.commons.dbcp2.BasicDataSource;

import logica.AccesoDatosException;

public interface CafeDAO {
	
	public void liberar() ;

	public void verTabla() throws AccesoDatosException;

	public void actualizarVentasCafe(String cafe, int ventas) throws AccesoDatosException;

	public void BuscarCafe(String cafe) throws AccesoDatosException ;

	public void BorrarCafe(String cafe) throws AccesoDatosException;

	public void InsertarCafe(String nom, int ID, float precio, int ventas, int total) throws AccesoDatosException ;

	public void cafesProveedor(int id_prov) throws AccesoDatosException;
	
	public void transferencia(String nom1, String nom2) throws AccesoDatosException;
	
	public BasicDataSource getPool();
}

package DAO;



import java.util.List;

import logica.AccesoDatosException;
import modelo.Cafe;

public interface CafeDAO {

	public List<Cafe> verTabla() throws AccesoDatosException;

	public void actualizarVentasCafe(String cafe, int ventas) throws AccesoDatosException;

	public void BuscarCafe(String cafe) throws AccesoDatosException ;

	public void BorrarCafe(String cafe) throws AccesoDatosException;

	public void InsertarCafe(String nom, int ID, float precio, int ventas, int total) throws AccesoDatosException ;

	public void cafesProveedor(int id_prov) throws AccesoDatosException;
	
	public void transferencia(String nom1, String nom2) throws AccesoDatosException;
	
	public void cerrar();
}

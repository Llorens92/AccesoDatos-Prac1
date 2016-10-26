package JDBC.JDBCCafes;

import java.util.Scanner;

import ClasesPrincipales.Cafes;
import exceptions.AccesoDatosException;

public class InteraccionCaf {
	public static void Ver(Cafes miCafe) throws AccesoDatosException{
		System.out.println("Los datos de todos los cafes son:");
		miCafe.verTabla();
	}
	
	public static void Buscar(Cafes miCafe) throws AccesoDatosException{
		Scanner lc = new Scanner(System.in);
		System.out.println("Introduzca el nombre del café cuyos datos desea consultar:");
		miCafe.BuscarCafe(lc.next());	
	}	
	
	public static void Insertar (Cafes miCafe) throws AccesoDatosException{
		Scanner lc = new Scanner(System.in);
		System.out.println("Introduzca el nombre del café cuyos datos desea insertar:");
		String nom = lc.next();
		System.out.println("Introduzca el Id del café cuyos datos desea insertar:");
		int id = lc.nextInt();
		System.out.println("Introduzca el precio del café cuyos datos desea insertar:");
		float precio = lc.nextFloat();
		System.out.println("Introduzca las ventas del café cuyos datos desea insertar:");
		int ventas = lc.nextInt();
		System.out.println("Introduzca el total del café cuyos datos desea insertar:");
		int total = lc.nextInt();
		miCafe.InsertarCafe(nom, id, precio, ventas, total);
	}
	
	public static void Borrar(Cafes miCafe) throws AccesoDatosException{
		Scanner lc = new Scanner(System.in);
		System.out.println("Introduzca el nombre del café cuyos datos desea borrar:");
		miCafe.BorrarCafe(lc.next());
	}
	
	public static void BuscarDatosProv(Cafes miCafe) throws AccesoDatosException{
		Scanner lc = new Scanner(System.in);
		System.out.println("Introduzca el id del proveedor cuyos datos desea consultar:");
		miCafe.cafesProveedor(lc.nextInt());
	}

	public static void transferencia (Cafes miCafe) throws AccesoDatosException{
		Scanner lc = new Scanner(System.in);
		System.out.println("Introduzca el nombre del café cuyas ventas desea traspasar a otro:");
		String nom = lc.next();
		System.out.println("Introduzca el nombre del café a cuyas ventas quiera sumar las del anterior:");
		String nom2 = lc.next();
		miCafe.transferencia(nom, nom2);
	}

	public static void updateVentas (Cafes miCafe) throws AccesoDatosException{
		Scanner lc = new Scanner(System.in);
		System.out.println("Introduzca el nombre del café cuyas ventas desea actualizar:");
		String nom = lc.next();
		System.out.println("Introduzca las ventas del café:");
		int ventas = lc.nextInt();
		miCafe.actualizarVentasCafe(nom, ventas);
	}
	
}

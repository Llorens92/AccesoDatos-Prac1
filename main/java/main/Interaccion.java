package main;

import java.util.Scanner;

import ClasesPrincipales.Cafes;

public class Interaccion {
	public static void Ver(Cafes miCafe){
		System.out.println("Los datos de todos los cafes son:");
		miCafe.verTabla();
	}
	
	public static void Buscar(Cafes miCafe){
		Scanner lc = new Scanner(System.in);
		System.out.println("Introduzca el nombre del café cuyos datos desea consultar:");
		miCafe.BuscarCafe(lc.next());	
	}	
	
	public static void Insertar (Cafes miCafe){
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
	
	public static void Borrar(Cafes miCafe){
		Scanner lc = new Scanner(System.in);
		System.out.println("Introduzca el nombre del café cuyos datos desea borrar:");
		miCafe.BorrarCafe(lc.next());
	}
	
	public static void BuscarDatosProv(Cafes miCafe){
		Scanner lc = new Scanner(System.in);
		System.out.println("Introduzca el id del proveedor cuyos datos desea consultar:");
		miCafe.cafesProveedor(lc.nextInt());
	}
}

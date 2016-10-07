package main;

import java.util.Scanner;

import ClasesPrincipales.Cafes;

public class Pruebas {

	public static void main(String[] args) {
		Scanner lc = new Scanner(System.in);
		Cafes miCafe = new Cafes();
		System.out.print("Bienvenido a la aplicación de gestión de cafés:");
		int opcion = 0;
		while (opcion != 6) {
			System.out.println("\n¿Qué desea hacer?");
			Menu.menu();
			switch (lc.nextInt()) {
				case 1:
					Interaccion.Ver(miCafe);
					break;
				case 2:
					Interaccion.Buscar(miCafe);
					break;
				case 3:
					Interaccion.Insertar(miCafe);;
					break;
				case 4:
					Interaccion.Borrar(miCafe);
					break;
				case 5:
					Interaccion.BuscarDatosProv(miCafe);
					break;
				case 6:
					opcion=6;
					miCafe.cerrar();
					break;
				default:
					System.out.println("introduzca un nº del 1 al 6");
			}
		}
	}
}

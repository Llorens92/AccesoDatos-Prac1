package JDBC.JDBCCafes;

import java.util.Scanner;

import ClasesPrincipales.Cafes;
import ClasesPrincipales.Libros;
import exceptions.MercadoException;

public class Pruebas {

	public static void main(String[] args) {
		Scanner lc = new Scanner(System.in);
		System.out.print("Bienvenido a la aplicación de gestión de su base de datos:\n¿Qué desea hacer?");
		int opcion = 0;
		while (opcion != 3) {
			Menu.menuPrincipal();
			switch (lc.nextInt()) {
			case 1:
				try {
					Cafes miCafe = new Cafes();
					int opcionCaf = 0;
					while (opcionCaf != 6) {
						System.out.println("\n¿Qué desea hacer?");
						Menu.menuCafes();
						switch (lc.nextInt()) {
						case 1:
							InteraccionCaf.Ver(miCafe);
							break;
						case 2:
							InteraccionCaf.Buscar(miCafe);
							break;
						case 3:
							InteraccionCaf.Insertar(miCafe);
							break;
						case 4:
							InteraccionCaf.Borrar(miCafe);
							break;
						case 5:
							InteraccionCaf.BuscarDatosProv(miCafe);
							break;
						case 6:
							opcionCaf = 6;
							miCafe.cerrar();
							break;
						default:
							System.out.println("introduzca un nº del 1 al 6");
						}
					}
				} catch (MercadoException ex) {
					System.out.println("Lo sentimos ocurrio un error en la apliacion" + ex.getMessage());
				}
				break;
			case 2:
				try {
					Libros miLibro = new Libros();
					int opcionLib = 0;
					while (opcionLib != 5) {
						System.out.println("\n¿Qué desea hacer?");
						Menu.menuLibros();;
						switch (lc.nextInt()) {
						case 1:
							InteraccionLib.Añadir(miLibro);
							;
							break;
						case 2:
							InteraccionLib.Borrar(miLibro);
							;
							break;
						case 3:
							InteraccionLib.Ver(miLibro);
							;
							break;
						case 4:
							InteraccionLib.update(miLibro);
							break;
						case 5:
							opcionLib = 5;
							miLibro.cerrar();
							break;
						default:
							System.out.println("introduzca un nº del 1 al 5");
						}
					}
				} catch (MercadoException ex) {
					System.out.println("Lo sentimos ocurrio un error en la apliacion" + ex.getMessage());
				}
				break;
			case 3:
				System.out.println("Hasta Pronto!");
				break;
			default:
				System.out.println("introduzca un nº del 1 al 3");
			}
		}
	}
}
package JDBC.JDBCCafes;

import java.util.Scanner;

import ClasesPrincipales.Cafes;
import ClasesPrincipales.Libros;
import exceptions.MercadoException;
import utils.Utilidades;

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
					while (opcionCaf != 8) {
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
							InteraccionCaf.updateVentas(miCafe);;
							break;
						case 6:
							InteraccionCaf.BuscarDatosProv(miCafe);
							break;
						case 7:
							InteraccionCaf.transferencia(miCafe);
							break;
						case 8:
							opcionCaf = 8;
							Utilidades.closePool(miCafe.getPool());
							break;
						default:
							System.out.println("introduzca un nº del 1 al 8");
						}
					}
				} catch (MercadoException ex) {
					System.out.println("Lo sentimos ocurrio un error en la apliacion" + ex.getMessage());
					Utilidades.closePool(miCafe.getPool());
				}
				break;
			case 2:
				try {
					Libros miLibro = new Libros();
					int opcionLib = 0;
					while (opcionLib != 13) {
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
							InteraccionLib.obtener(miLibro);
							break;
						case 6:
							InteraccionLib.VerInverso(miLibro);
							break;
						case 7:
							InteraccionLib.updateVarios(miLibro);
							break;
						case 8:
							InteraccionLib.mostrarFilas(miLibro);
							break;
						case 9:
							InteraccionLib.updatePrecioPag(miLibro);
							break;
						case 10:
							InteraccionLib.transaccionPrecio(miLibro);
							break;
						case 11:
							InteraccionLib.añadirPag(miLibro);
							break;
						case 12:
							InteraccionLib.duplicarLibro(miLibro);
							break;
						case 13:
							opcionLib = 13;
							Utilidades.closePool(miLibro.getPool());
							break;
						default:
							System.out.println("introduzca un nº del 1 al 13");
						}
					}
				} catch (MercadoException ex) {
					System.out.println("Lo sentimos ocurrio un error en la apliacion" + ex.getMessage());
				}
				break;
			case 3:
				System.out.println("Hasta Pronto!");
				opcion=3;
				break;
			default:
				System.out.println("introduzca un nº del 1 al 3");
			}
		}
	}
}
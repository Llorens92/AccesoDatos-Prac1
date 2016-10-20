package JDBC.JDBCCafes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import ClasesPrincipales.Cafes;
import ClasesPrincipales.Libros;
import exceptions.AccesoDatosException;

public class InteraccionLib {
	public static void Añadir(Libros miLibro) throws AccesoDatosException {
		Scanner lc = new Scanner(System.in);
		System.out.println("Introduzca el ISBN del libro cuyos datos desea añadir:");
		int ISBN = lc.nextInt();
		System.out.println("Introduzca el titulo del libro cuyos datos desea añadir:");
		String titulo = lc.next();
		System.out.println("Introduzca el autor del libro cuyos datos desea añadir:");
		String autor = lc.next();
		System.out.println("Introduzca la editorial del libro cuyos datos desea añadir:");
		String editorial = lc.next();
		System.out.println("Introduzca el total de paginas del libro cuyos datos desea añadir:");
		int paginas = lc.nextInt();
		System.out.println("Introduzca el total de copias del libro cuyos datos desea añadir:");
		int copias = lc.nextInt();
		System.out.println(("INSERT INTO libros VALUES (\"" + ISBN + "\"" + ",\"" + titulo + "\"" + ",\"" + autor + "\""
				+ ",\"" + editorial + "\"" + ",\"" + paginas + "\",\"" + copias + "\")"));
		miLibro.añadirLibro(ISBN, titulo, autor, editorial, paginas, copias);
		miLibro.verCatalogo();
	}

	public static void Borrar(Libros miLibro) throws AccesoDatosException {
		Scanner lc = new Scanner(System.in);
		System.out.println("Introduzca el ISBN del libro cuyos datos desea borrar:");
		miLibro.BorrarLibro(lc.next());
		miLibro.verCatalogo();
	}

	public static void Ver(Libros miLibro) throws AccesoDatosException {
		miLibro.verCatalogo();
	}

	public static void update(Libros miLibro) throws AccesoDatosException {
		Scanner lc = new Scanner(System.in);
		System.out.println("Introduzca el ISBN del libro cuyas copias desea actualizar:");
		String ISBN = lc.next();
		System.out.println("Introduzca el nuevo total de copias del libro cuyos datos desea actualizar:");
		int copias = lc.nextInt();
		miLibro.actualizarCopias(ISBN, copias);
		miLibro.verCatalogo();
		;
	}

	public static void obtener(Libros miLibro) throws AccesoDatosException {
		String[] campos = miLibro.getCamposLibro();
		for (int i = 0; i < campos.length; i++) {
			System.out.print(campos[i] + ",");
		}
	}

	public static void VerInverso(Libros miLibro) throws AccesoDatosException {
		miLibro.verCatalogoInverso();
	}

	public static void updateVarios(Libros miLibro) throws AccesoDatosException {
		Scanner lc = new Scanner(System.in);
		System.out.println("¿De cuantos libros distintos desea actualizar el número de copias?");
		int num = lc.nextInt();
		HashMap<String, Integer> hashCopias = new HashMap<String, Integer>();
		for (int i = 0; i < num; i++) {
			System.out.println("Introduzca el ISBN del libro cuyas copias desea actualizar:");
			String ISBN = lc.next();
			System.out.println("Introduzca el nuevo total de copias del libro cuyos datos desea actualizar:");
			int copias = lc.nextInt();
			hashCopias.put(ISBN, copias);
		}
		miLibro.actualizarCopias(hashCopias);
		miLibro.verCatalogo();
	}

	public static void mostrarFilas(Libros miLibro) throws AccesoDatosException {
		Scanner lc = new Scanner(System.in);
		System.out.println("¿Cuantas filas distintas quiere mostrar?");
		int num = lc.nextInt();
		ArrayList<Integer> arrayFilas = new ArrayList<Integer>();
		for (int i = 0; i < num; i++) {
			System.out.println("Introduzca el nº de fila cuyos datos desea visualizar:");
			int num2 = lc.nextInt();
			arrayFilas.add(Integer.valueOf(num2));
		}
		miLibro.mostrarFilas(arrayFilas);
	}

	public static void updatePrecioPag(Libros miLibro) throws AccesoDatosException {
		Scanner lc = new Scanner(System.in);
		System.out.println("¿De cuantos libros distintos desea actualizar el precio por pagina?");
		int num = lc.nextInt();
		HashMap<String, Float> hashPrecios = new HashMap<String, Float>();
		for (int i = 0; i < num; i++) {
			System.out.println("Introduzca el ISBN del libro cuyo precio quiere actualizar:");
			String ISBN = lc.next();
			System.out.println("Introduzca el precio por pagina del libro cuyos datos desea actualizar:");
			float precio = lc.nextFloat();
			hashPrecios.put(ISBN, precio);
		}
		miLibro.actualizarPrecioPag(hashPrecios);
		miLibro.verCatalogo();
	}

	public static void transaccionPrecio(Libros miLibro) throws AccesoDatosException {
		Scanner lc = new Scanner(System.in);
		System.out.println("Introduzca el ISBN del primer libro cuyo precio quiere actualizar:");
		String isbn1 = lc.next();
		System.out.println("Introduzca el ISBN del segundo libro cuyo precio quiere actualizar:");
		String isbn2 = lc.next();
		System.out.println("Introduzca el precio por pagina del libro cuyos datos desea actualizar:");
		float precio = lc.nextFloat();
		miLibro.transaccionPrecio(isbn1, isbn2, precio);
		miLibro.verCatalogo();
	}

	public static void añadirPag(Libros miLibro) throws AccesoDatosException {
		Scanner lc = new Scanner(System.in);
		System.out.println("Introduzca el ISBN del libro cuyas paginas quiere actualizar:");
		String isbn = lc.next();
		System.out.println("Introduzca el nº de páginas que desea añadir al libro que quiere actualizar:");
		int pag = lc.nextInt();
		System.out.println("Introduzca el precio por pagina del libro cuyos datos desea actualizar:");
		float precio = lc.nextFloat();
		miLibro.añadirPaginas(isbn, pag, precio);
		miLibro.verCatalogo();
	}
	

	public static void duplicarLibro(Libros miLibro) throws AccesoDatosException {
		Scanner lc = new Scanner(System.in);
		System.out.println("Introduzca el ISBN del libro que quiere duplicar:");
		String isbn1 = lc.next();
		System.out.println("Introduzca el ISBN del nuevo libro:");
		String isbn2 = lc.next();
		miLibro.duplicarLibro(isbn1, isbn2);
		miLibro.verCatalogo();
	}

}
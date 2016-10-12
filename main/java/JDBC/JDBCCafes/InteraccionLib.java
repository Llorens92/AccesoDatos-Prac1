package JDBC.JDBCCafes;

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
}

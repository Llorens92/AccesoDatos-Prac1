package JDBC.JDBCCafes;

public class Menu {
	public static void menuPrincipal(){
		System.out.println("\n1-Administrar Cafes.\n"+
				"2-Administrar Libros.\n"
				+ "3-Salir\n");
	}
	
	public static void menuCafes(){
		System.out.println("1-Ver tabla de cafes.\n"+
				"2-Buscar Cafe\n"
				+ "3-Insertar Cafe\n"
				+ "4-Borrar Cafe\n"
				+ "5-Actualizar ventas Cafe\n"
				+ "6-Consultar Cafes de un proveedor (Introducir ID)\n"
				+ "7-Transferencia de ventas de un cafe a otro\n"
				+ "8-Salir\n");
	}
	public static void menuLibros(){
		System.out.println("1-Añadir Libro.\n"+
				"2-Borrar Libro\n"
				+ "3-Ver Catalogo\n"
				+ "4-Actualizar Copias\n"
				+ "5-Obtener campos libro\n"
				+ "6-Ver Catalogo Inverso\n"
				+ "7-Actualizar Copias (Varios)\n"
				+ "8-Mostrar filas introducidas\n"
				+ "9-Actualizar precio de uno o varios libros\n"
				+ "10-Actualizar precio de dos libros\n"
				+ "11-Actualizar nº de páginas y precio de un libro\n"
				+ "12-Duplicar libro\n"
				+ "13-Salir\n");
	}
}
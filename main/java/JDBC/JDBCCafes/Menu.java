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
				+ "5-Consultar Cafes de un proveedor (Introducir ID)\n"
				+ "6-Salir\n");
	}
	public static void menuLibros(){
		System.out.println("1-Añadir Libro.\n"+
				"2-Borrar Libro\n"
				+ "3-Ver Catalogo\n"
				+ "4-Actualizar Copias\n"
				+ "5-Salir\n");
	}
}
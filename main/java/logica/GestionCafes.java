package logica;

import java.util.List;

import DAO.CafeDAO;
import modelo.Cafe;
import modelo.CafeException;

public class GestionCafes {
	public List<Cafe> actualizaLibreria(int num1,int num2) throws CafeException {
			List<Cafe> cafes=null;
			Cafe micafe=new Cafe();
			Cafe.setIsbn(num1);
			Cafe.setCopias(num2);			
			CafeDAO miCafe = CafeDAO.getInstance().getLibroDAO();			
			miCafe.actualizarCopias(Cafe);
			cafes=miCafe.verCatalogo();		
			return cafes;

		

	}
}
}
